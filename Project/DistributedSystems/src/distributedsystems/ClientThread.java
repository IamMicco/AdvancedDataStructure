package distributedsystems;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientThread extends Thread {

    private final Socket socket;
    private String commitFileName = String.format("%s.txt", Server.serverName);
    private VersionControl versionControl;

    public ClientThread (Socket _socket) {

        socket = _socket;
        versionControl = new VersionControl();
    }

    public void run () {

        try {
            
            System.out.println("** New connection from " + socket.getInetAddress() + ":" + socket.getPort() + " **");

            final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Message msg = null;

            do {
                
                msg = (Message)input.readObject();
                if (msg.commands == null && !msg.isServer) {

                    if (msg.instruction.equalsIgnoreCase("append")) {

                        output.writeObject(new Message("Enter data to add: "));
                        msg = (Message) input.readObject();
                        Server.list.append(Integer.parseInt(msg.message));
                        msg.instruction = "append";
                        Server.log(msg.instruction, socket.getInetAddress(), socket.getPort());
                        // setServerConnect(msg);
                        System.out.println(String.format("Message: %s | Instruction: %s", msg.message, msg.instruction));

                    } else if (msg.instruction.equalsIgnoreCase("view")) {

                        output.writeObject(Server.list);
                        Server.log(msg.instruction, socket.getInetAddress(), socket.getPort());
                    } else if (msg.instruction.equalsIgnoreCase("commit")) {

                        try {
                            
                            FileInputStream file = new FileInputStream(commitFileName);
                            ObjectInputStream in = new ObjectInputStream(file);

                            versionControl = (VersionControl) in.readObject(); 
                            versionControl.setPreviousVersion(versionControl.getLatestVersion());
                            versionControl.setLatestVersion(Server.list);

                            in.close();
                            file.close();

                        } catch (FileNotFoundException e) {

                            versionControl.setLatestVersion(Server.list);
                        }

                        try {
                            
                            FileOutputStream file = new FileOutputStream(commitFileName);
                            ObjectOutputStream out = new ObjectOutputStream(file);

                            out.writeObject(versionControl);

                            out.close();
                            file.close();
                            msg.instruction = "commit";
                            Server.log(msg.instruction, socket.getInetAddress(), socket.getPort());
                            // setServerConnect(msg);
                            
                        } catch (IOException e) {}
                        @SuppressWarnings("unchecked")
                        ArrayList<String> commands = (ArrayList<String>) input.readObject();
                        Server.serverDNSBroadCast(new Message (commands, Server.PORT));
                    } else if (msg.instruction.equalsIgnoreCase("pull")) {

                        try {
                            
                            FileInputStream file = new FileInputStream(commitFileName);
                            ObjectInputStream in = new ObjectInputStream(file);

                            versionControl = (VersionControl) in.readObject(); 
                            Server.list = versionControl.getLatestVersion();

                            in.close();
                            file.close();
                            Server.log(msg.instruction, socket.getInetAddress(), socket.getPort());
                            // setServerConnect(msg);

                        } catch (FileNotFoundException e) {

                            System.out.println("No data to pull from storage");
                        }
                    } else if (msg.instruction.equalsIgnoreCase("revert")) {

                        try {
                            
                            FileInputStream file = new FileInputStream(commitFileName);
                            ObjectInputStream in = new ObjectInputStream(file);

                            versionControl = (VersionControl) in.readObject(); 
                            if (versionControl.getPreviousVersion() == null) throw new FileNotFoundException();
                            versionControl.setLatestVersion(versionControl.getPreviousVersion());
                            Server.list = versionControl.getLatestVersion();
                            versionControl.setPreviousVersion(null);

                            in.close();
                            file.close();
                            msg.instruction = "revert";
                            Server.log(msg.instruction, socket.getInetAddress(), socket.getPort());
                            // setServerConnect(msg);

                        } catch (FileNotFoundException e) {

                            versionControl.setLatestVersion(Server.list);
                            System.out.println("No version to return to");
                        }
                    } else if (msg.instruction.equalsIgnoreCase("delete")) {

                        output.writeObject(new Message("Enter data to delete: "));
                        msg = (Message) input.readObject();
                        Server.list.remove(Integer.parseInt(msg.message));
                        msg.instruction = "delete";
                        Server.log(msg.instruction, socket.getInetAddress(), socket.getPort());
                        // setServerConnect(msg);
                    }
                } else {
                    msg.instruction = "null";
                    for (int i = 0; msg.commands.size() > 0;) {

                        if (msg.commands.get(i).equalsIgnoreCase("append")) {

                            String cmd = msg.commands.remove(i);
                            Server.list.append(Integer.parseInt(msg.commands.remove(i)));
                            Server.log(cmd, socket.getInetAddress(), socket.getPort());
                        } else if (msg.commands.get(i).equalsIgnoreCase("commit")) {

                            try {
                            
                                FileInputStream file = new FileInputStream(commitFileName);
                                ObjectInputStream in = new ObjectInputStream(file);
    
                                versionControl = (VersionControl) in.readObject(); 
                                versionControl.setPreviousVersion(versionControl.getLatestVersion());
                                versionControl.setLatestVersion(Server.list);
    
                                in.close();
                                file.close();
    
                            } catch (FileNotFoundException e) {
    
                                versionControl.setLatestVersion(Server.list);
                            }
    
                            try {
                                
                                FileOutputStream file = new FileOutputStream(commitFileName);
                                ObjectOutputStream out = new ObjectOutputStream(file);
    
                                out.writeObject(versionControl);
    
                                out.close();
                                file.close();
                                String cmd = msg.commands.remove(i);
                                Server.log(cmd, socket.getInetAddress(), socket.getPort());
                                
                            } catch (IOException e) {}
                            System.out.println(String.format("Command ArrayList size is", msg.commands.size()));
                        } else if (msg.commands.get(i).equalsIgnoreCase("revert")) {

                            try {
                            
                                FileInputStream file = new FileInputStream(commitFileName);
                                ObjectInputStream in = new ObjectInputStream(file);
    
                                versionControl = (VersionControl) in.readObject(); 
                                if (versionControl.getPreviousVersion() == null) throw new FileNotFoundException();
                                versionControl.setLatestVersion(versionControl.getPreviousVersion());
                                Server.list = versionControl.getLatestVersion();
                                versionControl.setPreviousVersion(null);
    
                                in.close();
                                file.close();
                                String cmd = msg.commands.remove(i);
                                Server.log(cmd, socket.getInetAddress(), socket.getPort());
    
                            } catch (FileNotFoundException e) {
    
                                versionControl.setLatestVersion(Server.list);
                                System.out.println("No version to return to");
                            }
                        } else if (msg.commands.get(i).equalsIgnoreCase("delete")) {

                            String cmd = msg.commands.remove(i);
                            Server.list.remove(Integer.parseInt(msg.commands.remove(i)));
                            Server.log(cmd, socket.getInetAddress(), socket.getPort());
                        }
                    }
                }
                
                System.out.println(String.format("[%s:%d] %s", socket.getInetAddress(), socket.getPort(), msg.message));

            } while (!msg.instruction.toUpperCase().equals("EXIT"));

            System.out.println(String.format("** Closing connection with %s: %d **", socket.getInetAddress(), socket.getPort()));
            socket.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    // public void setServerConnect(Message msg) {

    //     Server.setDNSConnection();
    //     for (int server : Server.servers) {

    //         if (server != Server.PORT) {

    //             try {

    //                 final Socket sock = new Socket(Config.ipAddress, server);

    //                 final ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
    //                 final ObjectInputStream input = new ObjectInputStream(sock.getInputStream());

    //                 Message resp = null;

    //                 output.writeObject(msg);
    //                 resp = (Message)input.readObject();
    //                 System.out.println(String.format("\nServer says: %s\n", resp.message));
                    
    //                 sock.close();
    //             } catch (UnknownHostException e) {

    //                 e.printStackTrace();
    //             } catch (IOException e) {

    //                 e.printStackTrace();
    //             } catch (ClassNotFoundException e) {

    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    // }
}