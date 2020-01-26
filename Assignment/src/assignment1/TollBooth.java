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
        /**
         * calculates total amount for the toll
         * Note: weight is assumed to be in tons
         */
        return (5 * axles) + ((10 * weight) / 2);
    }
    
    public void Scan () {
        /**
         * Scans for truck weight and number of axes
         */
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
        /**
         * prints the total number of trucks and amount in receipts
         */
        System.out.println("Totals since last collection - Receipts: $" + this.totalReceipts + " Trucks: " + array.size());
    }
    
    public void cashDrawer () {
        /**
         * prints the total number of trucks and amount in receipts
         * and clears the memory of previous transactions
         */
        System.out.println("***   Collecting receipts   ***");
        System.out.println("Totals since the last collection - Receipts: $" + this.totalReceipts + " Trucks: " + array.size());
        this.totalReceipts = 0;
        this.array = new ArrayList<>();
    }
}