package distributedsystems;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Server {

    private String serverName;
    public static int PORT;
    public static HashSet<Integer> servers;
    private HashSet<Integer> clients;
    public static Linked_List list;
    private boolean active;
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Server(int port) {

        PORT = port;
        setDNSConnection(PORT);
        active = false;
        list = new Linked_List();
        clients = new HashSet<>();
        // System.out.println(servers.size());
    }

    public void setName(String _name) {

        this.serverName = _name;
    }

    public boolean checkIfActive() {

        return this.active;
    }

    public static void setDNSConnection (int serverPort) {

        try {

            final Socket sock = new Socket(Config.ipAddress, Config.DNSServerPort);

            final ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
            final ObjectInputStream input = new ObjectInputStream(sock.getInputStream());

            Message msg = null;

            msg = new Message(String.format("%d", PORT));
            output.writeObject(msg);
            @SuppressWarnings("unchecked")
            HashSet<Integer> resp = (HashSet<Integer>) input.readObject();
            servers = resp;
            System.out.println("Transfer from DNS Server conplete");
            // System.out.println(servers);
            
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
    }

    public void log (String logType) {

        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);
        
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new SimpleFormatter() {

            private static final String format = "[%1$tF %1$tT] [%2$-4s] %3$s %4$d %n";

            @Override
            public synchronized String format(LogRecord lr) {

                return String.format(format, 
                        new Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage(),
                        PORT);
            }
        });
        ch.setLevel(Level.INFO);
        logr.addHandler(ch);

        try {
            
            FileHandler fh = new FileHandler(String.format("%s.log", this.serverName), true);
            fh.setFormatter(new SimpleFormatter() {

                private static final String format = "[%1$tF %1$tT] [%2$-4s] %3$s %4$d %n";

                @Override
                public synchronized String format(LogRecord lr) {

                    return String.format(format, 
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage(),
                            PORT);
                }
            });
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        } catch (IOException e) {
            
            logr.log(Level.SEVERE, "File logger not working.", e);
        }

        logr.info(logType);
    }

    public void start () {

        try {

            final ServerSocket serverSock = new ServerSocket(PORT);

            Socket sock = null;
            Thread thread = null;
            while (true) {

                sock = serverSock.accept();
                setDNSConnection(PORT);
                if (servers.contains(sock.getPort())) thread = new ServerThread(sock);
                else thread = new ClientThread(sock);
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
         * args[1]: Enter server name for logfile name entry
         */
        Server server1 = new Server(Integer.parseInt(args[0]));
        server1.setName(args[1]);
        server1.start();
    }
}