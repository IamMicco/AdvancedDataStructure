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
             * args[0]: IP Address (localhost would work fine)
             * args[1]: Enter 0 to DistributedSystems.servers.size - 1 to pick from the list of online servers to connect to
             */
            int serverPort = DistributedSystems.servers.get(Integer.parseInt(args[1]));
            final Socket sock = new Socket(args[0], serverPort);  
            System.out.println(String.format("Connected to %s on port %d", args[0], serverPort));

            final ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
            final ObjectInputStream input = new ObjectInputStream(sock.getInputStream());
            
            Message msg = null, resp = null;
            do {
                
                msg = new Message(readSomeText());
                output.writeObject(msg);
                resp = (Message)input.readObject();
                System.out.println(String.format("\nServer says: %s\n", resp.message));
            } while (!msg.message.toUpperCase().equals("EXIT"));

            sock.close();

        } catch (Exception e) {
            
            System.err.println("Error: " + e.getMessage());
	        e.printStackTrace(System.err);
        }
    }
}