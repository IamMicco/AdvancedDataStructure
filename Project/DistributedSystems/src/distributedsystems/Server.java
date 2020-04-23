package distributedsystems;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class Server {

    private final int PORT;
    private static ArrayList<Integer> servers = new ArrayList<>();
    private ArrayList<Integer> clients;
    private boolean active;
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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

        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);
        
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.INFO);
        logr.addHandler(ch);

        try {
            
            FileHandler fh = new FileHandler("myLogger.log", true);
            fh.setFormatter(new SimpleFormatter() {

                private static final String format = "[%1$tF %1$tT] [%2$-4s] %3$s %4$d %n";

                @Override
                public synchronized String format(LogRecord lr) {

                    return String.format(format, 
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage(),
                            1289);
                }
            });
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        } catch (IOException e) {
            
            logr.log(Level.SEVERE, "File logger not working.", e);
        }

        logr.info("My first log");
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