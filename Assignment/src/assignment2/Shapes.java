package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class Shapes {
    
    public static void ShowArea(Shape s){

        double area = s.area();
        System.out.println("The area of the shape is " + area);
    }
    
    public static void main (String[] args) {
        
        Circle c = new Circle(4); //Radius of 4
        Rectangle r = new Rectangle (4, 3); //Height =4, width = 3
        ShowArea(c) ;
        ShowArea(r);

        
    }
}
