/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.Scanner;

/**
 *
 * @author Michael Nwabuobi
 */
public class TollRoad {
    
    public static void main (String[] args) {
        
        Scanner sc = new Scanner(System.in);
        TollBooth t = new TollBooth();
        String entry = "null";
        
        while (!entry.equals("4")) {
            
            System.out.println("Welcome to Houston Toll Road");
            System.out.println("Make one of the follwing selections");
            System.out.println("1- Scan truck info and display Toll");
            System.out.println("2- Calculate and Display Booths total");
            System.out.println("3- Remove cash drawer");
            System.out.println("4- Exit program");
            entry = sc.next();
            
            switch (entry) {
                
                case "1": 
                    t.Scan();
                    break;       
                case "2":                 
                    t.collectTotals();
                    break;                 
                case "3":                  
                    t.cashDrawer();
                    break;
            }
        }           
    }
}
