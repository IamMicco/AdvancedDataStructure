package distributedsystems;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private final int PORT;
    private static ArrayList<Integer> servers = new ArrayList<>();
    private ArrayList<Integer> clients;
    private boolean active;

    public Server (int port) {

        PORT = port;
        servers.add(PORT);
        active = false;
        clients = new ArrayList<>();
    }

    public int getPortNumber () {

        return this.PORT;
    }

    public boolean checkIfActive () {

        return this.active;
    }

    public void broadCastStatus () {


    }

    public void update () {


    }

    public void commit () {


    }

    public void rollback () {


    }

    public void log () {


    }

    public void start () {

        try {

            final ServerSocket serverSock = new ServerSocket(this.PORT);

            Socket sock = null;
            ClientThread thread = null;
            while (true) {

                sock = serverSock.accept();
                thread = new ClientThread(sock);
                thread.start();
            }
        } catch (Exception e) {

            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) {
        
        /**
         * args[0]: Port number for the server to run on
         */
        Server server1 = new Server(Integer.parseInt(args[0]));
        server1.start();
    }
}