package distributedsystems;

public class Message implements java.io.Serializable {

    public String message = null;
    public String instruction = null;

    public Message (String _message) {

        message = _message;
    }

    public Message () {}
}