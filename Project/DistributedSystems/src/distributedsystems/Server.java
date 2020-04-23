package distributedsystems;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.logging.*;

public class Server {

    private String serverName;
    private final int PORT;
    private static ArrayList<Integer> servers = new ArrayList<>();
    private ArrayList<Integer> clients;
    private boolean active;
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Server(int port) {

        PORT = port;
        servers.add(PORT);
        active = false;
        clients = new ArrayList<>();
    }

    public void setName(String _name) {

        this.serverName = _name;
    }

    public int getPortNumber() {

        return this.PORT;
    }

    public boolean checkIfActive() {

        return this.active;
    }

    public void broadCastStatus() {

    }

    public void update() {

    }

    public void commit() {

    }

    public void rollback() {

    }

    public void serverConnect(String message) {

        for (int server : servers) {

            if (server != PORT) {

                try {

                    final Socket sock = new Socket(Config.ipAddress, server);

                    final ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
                    final ObjectInputStream input = new ObjectInputStream(sock.getInputStream());

                    Message msg = null, resp = null;
                    do {
                        
                        msg = new Message(message);
                        output.writeObject(msg);
                        resp = (Message)input.readObject();
                        System.out.println(String.format("\nServer says: %s\n", resp.message));
                    } while (!msg.message.toUpperCase().equals("EXIT"));
                } catch (UnknownHostException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                } catch (ClassNotFoundException e) {

                    e.printStackTrace();
                }
            }
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
         * args[1]: Enter server name for logfile name entry
         */
        Server server1 = new Server(Integer.parseInt(args[0]));
        server1.setName(args[1]);
        server1.start();
    }
}