package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class Rectangle implements Shape {
    
    private double width, height;
    
    public Rectangle (int width, int height) {
        
        this.width = width;
        this.height = height;
    }
    
    public double getWidth () {
        
        return this.width;
    }
    
    public double getHeight () {
        
        return this.height;
    }
    
    public double area () {
        
        return width * height;
    }
    
    public String toString () {
        
        return String.format("A Rectangle with width %.2f and height %.2f would yield an area of %.2f",
                width, height, area());
    }
}
