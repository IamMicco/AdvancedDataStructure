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
public class DiscountSale extends Sale {
    
    
    private double discount;
    
    public DiscountSale (String name, double price, double discount) {
        
        super(name, price);
        setDiscount(discount);
    }
    
    public void setDiscount (double discount) {
        
        this.discount = discount;
    }
    
    public String toString () {
        
        return (getName() + " Price = $" + getPrice()
                + "Discoount = " + discount + "%\n");
    }
}
