package distributedsystems;

import java.io.*;
import java.net.Socket;

public class DThread extends Thread {

    private final Socket socket;

    public DThread (Socket _socket) {

        socket = _socket;
    }

    public void run () {

        try {
            
            System.out.println("** New connection from " + socket.getInetAddress() + ":" + socket.getPort() + " **");

            final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Message msg = null;

            msg = (Message)input.readObject();
            if (msg.message == null && msg.isServer) {

                for (int port : DServer.servers) {

                    if (port != msg.PORT) {

                        try {

                            final Socket sock = new Socket(Config.ipAddress, port);
                            DServer.broadCastPORT = sock.getLocalPort();
                
                            final ObjectOutputStream serverOutput = new ObjectOutputStream(sock.getOutputStream());
                
                            serverOutput.writeObject(msg);
                            System.out.println("Command transfer from Server complete");
                            // sock.close();
                            
                        } catch (IOException e) {
                            
                            e.printStackTrace();
                        }
                    }
                }
            } 
            // else if (msg.instruction.equalsIgnoreCase("getsocketport")) { 

            //     output.writeObject(DServer.broadCastPORT);
            // } 
            else {

                DServer.servers.add(Integer.parseInt(msg.message));
                output.writeObject(DServer.servers);

            }
            
            System.out.println(String.format("** Closing connection with %s: %d **", socket.getInetAddress(), socket.getPort()));
            socket.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}