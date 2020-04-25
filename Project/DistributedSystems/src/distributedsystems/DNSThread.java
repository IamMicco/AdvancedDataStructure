package distributedsystems;

import java.io.*;
import java.net.Socket;

public class DNSThread extends Thread {

    private final Socket socket;

    public DNSThread (Socket _socket) {

        socket = _socket;
    }

    public void run () {

        try {
            
            System.out.println("** New connection from " + socket.getInetAddress() + ":" + socket.getPort() + " **");

            final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Message msg = null;

            msg = (Message)input.readObject();
            DNSServer.servers.add(Integer.parseInt(msg.message));
            output.writeObject(DNSServer.servers);

            System.out.println(String.format("** Closing connection with %s: %d **", socket.getInetAddress(), socket.getPort()));
            socket.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}