/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classwork;

/**
 *
 * @author Michael Nwabuobi
 */
public class Sale {

    private String name;
    private double price;
    
    public Sale (String name, double price) {
        
        setName(name);
        setPrice(price);
    }
    
    public void setName (String name) {
        
        this.name = name;
    }
    
    public String getName () {
        
        return this.name;
    }
    
    public void setPrice (double price) {
        
        this.price = price;
    }
    
    public double getPrice () {
        
        return this.price;
    }
    
    public String toString () {
        
        return (name + " the price is $" + price);
    }
}
