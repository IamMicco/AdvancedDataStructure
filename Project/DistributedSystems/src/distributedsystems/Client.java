package distributedsystems;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    public static ArrayList<String> commands = new ArrayList<>();

    private static String readSomeText() {

        try {

            System.out.println("Enter a line of text, or type \"EXIT\" to quit.");
            System.out.print(" > ");	
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            return in.readLine();
        }
        catch(Exception e){
            
            return "";
        }
	}


    public static void main(String[] args) {

        if (args.length < 1) {

            System.err.println("Not enough arguments.\n");
            System.err.println("Usage: Distrbuted Systems Client <Server name or IP\n");
            System.exit(-1);
        }
        
        try {
            /**
             * args[0]: Enter server port number
             */
            int serverPort = Integer.parseInt(args[0]);
            final Socket sock = new Socket(Config.ipAddress, serverPort);  
            System.out.println(String.format("Connected to %s on port %d", Config.ipAddress, serverPort));

            final ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
            final ObjectInputStream input = new ObjectInputStream(sock.getInputStream());
            
            Message msg = null, resp = null;
            do {

                System.out.println("Enter 'append' to add value.");
                System.out.println("Enter 'view' to view list.");
                System.out.println("Enter 'commit' to save to storage.");
                System.out.println("Enter 'pull' to retreive from storage.");
                System.out.println("Enter 'revert' to go to previous version.");
                System.out.println("Enter 'delete' to go to delete value from list.");
                
                msg = new Message();
                msg.instruction = readSomeText();
                output.writeObject(msg);
                System.out.println(String.format("Message: %s | Instruction: %s", msg.message, msg.instruction));
                if (msg.instruction.equalsIgnoreCase("view")) {

                    Linked_List list = (Linked_List) input.readObject();
                    int[] listArr = list.displayList();
                    for (int i = 0; i < listArr.length; i++) {

                        if (i == 0) System.out.print(String.format("[%d, ", listArr[i]));
                        else if (i == (listArr.length - 1)) System.out.print(String.format("%d]\n", listArr[i]));
                        else System.out.print(String.format("%d, ", listArr[i]));
                    }
                } else if (msg.instruction.equalsIgnoreCase("append")) {

                    commands.add(msg.instruction);
                    resp = (Message)input.readObject();
                    System.out.println(String.format("\n%s\n", resp.message));
                    msg = new Message();
                    msg.message = readSomeText();
                    msg.instruction = "null";
                    commands.add(msg.message);
                    output.writeObject(msg);
                } else if (msg.instruction.equalsIgnoreCase("delete")) {

                    commands.add(msg.instruction);
                    resp = (Message)input.readObject();
                    System.out.println(String.format("\n%s\n", resp.message));
                    msg = new Message();
                    msg.message = readSomeText();
                    commands.add(msg.message);
                    msg.instruction = "null";
                    output.writeObject(msg);
                } else if (msg.instruction.equalsIgnoreCase("commit")) {

                    commands.add(msg.instruction);
                    output.writeObject(commands);
                    commands = new ArrayList<String>();
                }
                
            } while (!msg.instruction.toUpperCase().equals("EXIT"));

            sock.close();

        } catch (Exception e) {
            
            System.err.println("Error: " + e.getMessage());
	        e.printStackTrace(System.err);
        }
    }
}