package distributedsystems;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {

    private final Socket socket;

    public ClientThread (Socket _socket) {

        socket = _socket;
    }

    public void run () {

        try {
            
            System.out.println("** New connection from " + socket.getInetAddress() + ":" + socket.getPort() + " **");

            final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Message msg = null;
            int count = 0;

            do {
                
                msg = (Message)input.readObject();
                System.out.println(String.format("[%d:%d] %s", socket.getInetAddress(), socket.getPort(), msg.message));

                count++;
                output.writeObject(new Message(String.format("Recieved message #%s", count)));

            } while (!msg.message.toUpperCase().equals("EXIT"));

            System.out.println(String.format("** Closing connection with %d: %d **", socket.getInetAddress(), socket.getPort()));
            socket.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}