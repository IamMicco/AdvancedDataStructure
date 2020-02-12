package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class Circle implements Shape {
    
    private double radius;
    
    public Circle (int radius) {
        
        this.radius = radius;
    }
    
    public double getRadius () {
        
        return radius;
    }
    
    public double area () {
        
        return Math.PI * Math.pow(radius, 2);
    }
    
    public String toString () {
        
        return String.format("A circle with radius %.2f would result in an area of %.2f", radius, area());
    }
}
