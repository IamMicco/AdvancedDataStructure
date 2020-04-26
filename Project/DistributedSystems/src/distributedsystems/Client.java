package distributedsystems;

import java.io.*;
import java.net.Socket;

public class Client {

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

        if (args.length != 1) {

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

                    resp = (Message)input.readObject();
                    System.out.println(String.format("\n%s\n", resp.message));
                    msg = new Message();
                    msg.message = readSomeText();
                    msg.instruction = "null";
                    output.writeObject(msg);
                }
                
            } while (!msg.instruction.toUpperCase().equals("EXIT"));

            sock.close();

        } catch (Exception e) {
            
            System.err.println("Error: " + e.getMessage());
	        e.printStackTrace(System.err);
        }
    }
}