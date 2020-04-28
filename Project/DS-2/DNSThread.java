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

            PortDirectory directory = (PortDirectory) input.readObject();
            System.out.println(" Directory port number: " + directory.getPort());
            // System.out.println(String.format("ReceivingPort in thread: %d", DNSServer.receivingPort));
            // directory.setDNSPort(DNSServer.receivingPort);
            DNSServer.serverDirectory.put(directory.getPort(), directory);
            DNSServer.servers.add(directory.getPort());
            // DNSServer.servers.add(Integer.parseInt(msg.message));
            output.writeObject(DNSServer.servers);
            output.writeObject(DNSServer.serverDirectory);

            System.out.println(String.format("** Closing connection with %s: %d **", socket.getInetAddress(), socket.getPort()));
            socket.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}