/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classwork;

import java.util.*;

/**
 *
 * @author Michael Nwabuobi
 */
public class Main {
    
    public static void main (String[] args) {
        
        ArrayList<Object> storeItems = new ArrayList<Object>();
        
        Sale simple = new Sale("Floor mat", 10.00);
        DiscountSale discount = new DiscountSale("floor mat", 11.00, 10);
        
        storeItems.add(simple);
        storeItems.add(discount);
        
        //System.out.println(storeItems);
        Object o = storeItems.get(1);
        Object o2 = storeItems.get(0);
        //if (o instanceof Sale) System.out.println(((Sale) o).getName());
        //else if (o instanceof DiscountSale) System.out.println(((DiscountSale) o).getName());
        
        try {
            
            if (Class.forName("DiscountSale") == o.getClass()) System.out.println(((DiscountSale) o).getName());
            System.out.println(o.getClass() == o2.getClass());
        } catch (ClassNotFoundException e) {
            
            
        }
        /**
         * This tests out the functionality of a copy constructor
         */
        DiscountSale ds1 = new DiscountSale("Laptop", 1300, 100);
        System.out.println(ds1);
        DiscountSale ds2 = new DiscountSale(ds1);
        //ds2.setPrice(90);
        System.out.println(ds2);
        
        DiscountSale ds = new DiscountSale("Laptop", 1300, 10.0);
        Sale s = new Sale("Laptop", 1300);
        
        if (ds.equalDeals(s)) System.out.println("Equal Deals");
        else System.out.println("Not equal");
        
        Sale s2 = new DiscountSale("Chain", 1500, 5.0);
        s2.announcement();
        
        ds.announcement();
        s.announcement();
        
        Sale s3 = s2.clone();
        
        ArrayList<Sale> saleArray = new ArrayList<Sale>();
        saleArray.add(s);
        saleArray.add(s2);
        ArrayList<Sale> saleArray2 = (ArrayList<Sale>) saleArray.clone();
        //s2.setPrice(1000000);
        System.out.println(saleArray.toString());
    }
}
