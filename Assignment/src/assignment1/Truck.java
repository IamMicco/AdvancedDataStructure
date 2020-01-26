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
        /**
         * returns weight of truck
         */
        return weight;
    }
    
    public int getAxles () {
        /**
         * returns number of axles on truck
         */
        return axles;
    }
    
    public void setToll (int toll) {
        /**
         * sets the toll amount
         */
        this.toll = toll;
    }
    
    public int getToll () {
        /**
         * returns toll amount
         */
        return this.toll;
    }
}
