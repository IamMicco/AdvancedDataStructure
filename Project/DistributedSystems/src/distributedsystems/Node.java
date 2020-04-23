package distributedsystems;

/**
 *
 * @author Michael
 */
public class Node {
    
    public static byte[] buffer;
    public String name = "Server1";
    
    public static void b (String word) {
        
        buffer = word.getBytes();
        for (int i = 0; i < buffer.length; i++) {
            
            System.out.println(buffer[i]);
        }
    }
    
    public static class Ball {
        
        public static String name;
        
        
    }
    
    public static void p (String word) {
        
        Ball.name = word;
        System.out.println(Ball.name);
    }
    
    public static void p () {
        
        System.out.println(Ball.name);
    }
    
    public static void main (String[] args) {
        
        //b("Hello");
        Node n = new Node();
        Node h = new Node();
        n.p("Hello");
        h.p();
        System.out.println(String.format("%s.log", n.name));
    }
}
