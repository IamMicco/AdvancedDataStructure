/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstpiapp;

import java.util.Scanner;

/**
 *
 * @author Michael Nwabuobi
 */

public class FirstPiApp {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello Pi world");
        System.out.println("What is your name");
        String name = sc.next();
        System.out.println(String.format("Hello, %s", name));
    }
}
