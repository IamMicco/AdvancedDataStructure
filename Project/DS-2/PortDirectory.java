package distributedsystems;

public class PortDirectory implements java.io.Serializable {

    private int port;
    private int DNSPort;
    private int serverPort;
    private boolean isServer = false;

    public void setPort (int PORT) {

        port = PORT;
    }

    public void setDNSPort (int dnsPort) {

        DNSPort = dnsPort;
    }

    public void setServerPort (int ServerPort) {

        serverPort = ServerPort;
    }

    public void setIsServer (boolean status) {

        isServer = status;
    }

    public int getPort () {

        return port;
    }

    public int getDNSPort () {

        return DNSPort;
    }

    public int getServerPort () {

        return serverPort;
    }

    public boolean getIsStatus () {

        return isServer;
    }
}