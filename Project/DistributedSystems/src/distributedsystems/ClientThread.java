package distributedsystems;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {

    private final Socket socket;
    private Linked_List list;
    private String commitFileName = "commit.txt";
    VersionControl versionControl;

    public ClientThread (Socket _socket) {

        socket = _socket;
        list = new Linked_List();
        versionControl = new VersionControl();
    }

    public void run () {

        try {
            
            System.out.println("** New connection from " + socket.getInetAddress() + ":" + socket.getPort() + " **");

            final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Message msg = null;
            int count = 0;

            do {
                
                msg = (Message)input.readObject();
                if (msg.message.equalsIgnoreCase("append")) {

                    System.out.println("Enter data to add: ");
                    msg = (Message) input.readObject();
                    list.append(Integer.parseInt(msg.message));
                } else if (msg.message.equalsIgnoreCase("view")) {

                    System.out.println("Here is the list of inputs entered: ");
                    int[] listArr = list.displayList();
                    for (int i = 0; i < listArr.length; i++) {

                        if (i == 0) System.out.println(String.format("[%d, ", listArr[i]));
                        else if (i == (listArr.length - 1)) System.out.println(String.format("%d]\n", listArr[i]));
                        else System.out.print(String.format("%d, ", listArr[i]));
                    }
                } else if (msg.message.equalsIgnoreCase("commit")) {

                    try {
                        
                        FileInputStream file = new FileInputStream(commitFileName);
                        ObjectInputStream in = new ObjectInputStream(file);

                        versionControl = (VersionControl) in.readObject(); 
                        versionControl.setPreviousVersion(versionControl.getLatestVersion());
                        versionControl.setLatestVersion(list);

                        in.close();
                        file.close();

                    } catch (FileNotFoundException e) {

                        versionControl.setLatestVersion(list);
                    }

                    try {
                        
                        FileOutputStream file = new FileOutputStream(commitFileName);
                        ObjectOutputStream out = new ObjectOutputStream(file);

                        out.writeObject(versionControl);

                        out.close();
                        file.close();
                        
                    } catch (IOException e) {}
                }
                
                System.out.println(String.format("[%d:%d] %s", socket.getInetAddress(), socket.getPort(), msg.message));

                count++;
                output.writeObject(new Message(String.format("Recieved message #%s", count)));

            } while (!msg.message.toUpperCase().equals("EXIT"));

            System.out.println(String.format("** Closing connection with %d: %d **", socket.getInetAddress(), socket.getPort()));
            socket.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}