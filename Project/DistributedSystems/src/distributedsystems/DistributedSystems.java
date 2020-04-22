package distributedsystems;

import java.util.ArrayList;

public class DistributedSystems {

    public static ArrayList<Integer> servers;

    public DistributedSystems () {

        servers = new ArrayList<>();
    }

    public static void addServers (int serverPortNumber) {

        servers.add(serverPortNumber);
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
