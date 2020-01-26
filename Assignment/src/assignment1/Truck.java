/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author Michael Nwabuobi
 */
public class Truck {
    
    private int weight;
    private int axles;
    private int toll;
    
    public Truck (int weight, int axles) {
        
        this.weight = weight;
        this.axles = axles;
    }
    
    public int getWeight () {
        
        return weight;
    }
    
    public int getAxles () {
        
        return axles;
    }
    
    public void setToll (int toll) {
        
        this.toll = toll;
    }
    
    public int getToll () {
        
        return this.toll;
    }
}
