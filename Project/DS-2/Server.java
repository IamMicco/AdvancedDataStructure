package distributedsystems;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Server {

    private String serverName;
    public static int PORT;
    public static HashSet<Integer> servers;
    public static HashMap<Integer, PortDirectory> serverDirectory;
    public static PortDirectory directory;
    private HashSet<Integer> clients;
    public static Linked_List list;
    private boolean active;
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Server(int port) {

        PORT = port;
        setDNSConnection();
        active = false;
        list = new Linked_List();
        clients = new HashSet<>();
        directory = new PortDirectory();
        directory.setPort(PORT);
        // System.out.println(servers.size());
    }

    public void setName(String _name) {

        this.serverName = _name;
    }

    public boolean checkIfActive() {

        return this.active;
    }

    public static void setDNSConnection () {

        try {

            final Socket sock = new Socket(Config.ipAddress, Config.DNSServerPort);

            final ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
            final ObjectInputStream input = new ObjectInputStream(sock.getInputStream());

            Message msg = null;
            directory.setDNSPort(sock.getLocalPort());
            // msg = new Message(directory);
            output.writeObject(Server.directory);
            @SuppressWarnings("unchecked")
            HashSet<Integer> resp = (HashSet<Integer>) input.readObject();
            @SuppressWarnings("unchecked")
            HashMap<Integer, PortDirectory> resp2 = (HashMap<Integer, PortDirectory>) input.readObject();
            servers = resp;
            serverDirectory = resp2;
            System.out.println("Transfer from DNS Server complete");
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

    public void pause (int seconds) {

        long time = seconds * 1000;
        long currTime = System.currentTimeMillis();
        do {
            
        } while ((currTime + time) <= System.currentTimeMillis());
    }

    public void start () {

        try {

            final ServerSocket serverSock = new ServerSocket(PORT);

            Socket sock = null;
            Thread thread = null;
            int SP = -1;
            while (true) {

                sock = serverSock.accept();
                setDNSConnection();
                pause(2);
                for (int port : servers) {

                    if (serverDirectory.containsKey(port)) SP = serverDirectory.get(port).getServerPort();
                    System.out.println(String.format("SP is: %d", SP));
                }
                System.out.println(String.format("Requesting port is: %d", sock.getPort()));
                if (SP != -1) {
                    
                    System.out.println("Went the Server route");
                    thread = new ServerThread(sock);
                }
                else {
                    
                    System.out.println("Went the Client route");
                    System.out.println(String.format("Port: %d", sock.getPort()));
                    thread = new ClientThread(sock);
                }
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
        // server1.setName(args[1]);
        System.out.println(servers);
        server1.start();
    }
}