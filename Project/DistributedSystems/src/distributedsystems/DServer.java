package distributedsystems;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class DServer {

    public static HashSet<Integer> servers = new HashSet<>();
    public static int broadCastPORT;
    private int PORT;

    public DServer () {

        PORT = Config.DServerPort;
    }

    public void start () {

        try {

            final ServerSocket serverSock = new ServerSocket(this.PORT);

            Socket sock = null;
            Thread thread = null;
            while (true) {

                sock = serverSock.accept();
                thread = new DThread(sock);
                thread.start();
            }
        } catch (Exception e) {

            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) {
        
        /**
         * args[0]: Enter Dserver name for logfile name entry
         */
        DServer DServer = new DServer();
        DServer.start();
    }
}