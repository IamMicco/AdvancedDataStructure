package distributedsystems;

import java.util.ArrayList;

public class Message implements java.io.Serializable {

    public String message = null;
    public String instruction = null;
    public ArrayList<String> commands;
    public int PORT;
    public boolean isServer = false;

    public Message (String _message) {

        message = _message;
    }

    public Message (ArrayList<String> _commands, int port) {

        commands = _commands;
        PORT = port;
        isServer = true;
    }

    public Message () {}
}