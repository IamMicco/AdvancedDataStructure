/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2;

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
    
    public Sale (Sale otherSale) {
        /**
         * This is a copy constructor
         */
        if (otherSale == null) {
            
            System.out.println("Error");
            System.exit(0);
        }
        else {
            
            this.name = otherSale.name;
            this.price = otherSale.price;
        }
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
    
    public double bill () {
        
        return price;
    }
    
    public boolean equalDeals (Sale otherSale) {
        
        if(otherSale == null) return false;
        else return (name.equals(otherSale.name) && this.bill() == otherSale.bill());
    }
    
    public boolean equal (Object otherObj) {
        /**
         * This class is meant to override the equal method
         * of class type Object which the parent to all class objects
         * in java, and it needs to have type Object being passes as a
         * parameter
         */
        if (otherObj == null) return false;
        else {
            
            if (this.getClass() != otherObj.getClass()) return false;
            else return (((Sale)otherObj).name.equals(this.name) && 
                    ((Sale)otherObj).price == this.price);
        }
    }
    
    public static void announcement () {
        
        System.out.println("This is sale");
    }
    
    public Sale clone () {
        /**
         * This makes use of the copy constructor to 
         * clone the values of an object
         */
        return new Sale(this);
    }
    
    public String toString () {
        
        return (name + " the price is $" + price);
    }
}