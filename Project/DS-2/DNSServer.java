package distributedsystems;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class DNSServer {

    public static HashMap<Integer, PortDirectory> serverDirectory = new HashMap<>();
    public static HashSet<Integer> servers = new HashSet<>();
    public static int receivingPort;
    private int PORT;

    public DNSServer () {

        PORT = Config.DNSServerPort;
    }

    public void start () {

        try {

            final ServerSocket serverSock = new ServerSocket(this.PORT);

            Socket sock = null;
            Thread thread = null;
            while (true) {

                sock = serverSock.accept();
                receivingPort = sock.getPort();
                System.out.println(receivingPort);
                thread = new DNSThread(sock);
                thread.start();
            }
        } catch (Exception e) {

            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) {
        
        /**
         * args[0]: Enter DNSserver name for logfile name entry
         */
        DNSServer DNSServer = new DNSServer();
        DNSServer.start();
    }
}