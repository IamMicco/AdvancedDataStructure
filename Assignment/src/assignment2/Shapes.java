package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class Shapes {
    public static void main (String[] args) {
        
        Circle c = new Circle(4);
        System.out.println(c.toString());
        
        Rectangle r = new Rectangle(4, 7);
        System.out.println(r.toString());
    }
}
