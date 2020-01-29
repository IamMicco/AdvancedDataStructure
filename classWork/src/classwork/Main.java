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
    }
}
