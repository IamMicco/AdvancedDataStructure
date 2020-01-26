/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Michael Nwabuobi
 */
public class TollBooth {
    
    private int totalReceipts = 0;
    private ArrayList<Truck> array = new ArrayList<>();
    
    public int calculateToll (int weight, int axles) {
        
        return (5 * axles) + ((10 * weight) / 2);       // I do not know what unit the weight is in so I assumed it to be in tons
    }
    
    public void Scan () {
        
        Scanner sc = new Scanner(System.in);
        String choice = "yes";
        while (!choice.equals("no")) {
            
            System.out.println("Enter truck weight");
            int weight = sc.nextInt();
            System.out.println("Enter number of axles");
            int axles = sc.nextInt();
            System.out.println("Enter 'no' to go back to menu or Enter 'yes' to continue");
            choice = sc.next();
            Truck t = new Truck(weight, axles);
            t.setToll(calculateToll(weight, axles));
            array.add(t);
            System.out.println ("Truck arrival - Axles: "+ t.getAxles() +" Total weight: "+ t.getWeight() +" Toll due: $"+ t.getToll());
            this.totalReceipts += t.getToll();
        }
    }
    
    public void collectTotals () {
        
        System.out.println("Totals since last collection - Receipts: $" + this.totalReceipts + " Trucks: " + array.size());
    }
    
    public void cashDrawer () {
        
        System.out.println("***   Collecting receipts   ***");
        System.out.println("Totals since the last collection - Receipts: $" + this.totalReceipts + " Trucks: " + array.size());
        this.totalReceipts = 0;
        this.array = new ArrayList<>();
    }
}