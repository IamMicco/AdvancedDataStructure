package assignment1;

/**
 *
 * @author Michael Nwabuobi
 */

import java.util.Scanner;


public class Assignment {

    private int[] PIN;
    private int[] password;
    private int[] guide;
    private int[] replica;

    public Assignment () {

        this.PIN = new int[5];
        this.password = new int[5];
        setPIN();
    }
    
    public void setPIN () {

        this.guide = Authentication.generateSequence();
        for (int i = 0; i < this.PIN.length; i++) {
            
            this.password[i] = this.guide[this.PIN[i]];
        }
    }

    public void setPIN (int[] pin) {

        this.PIN = pin;
        this.guide = Authentication.generateSequence();
        for (int i = 0; i < this.PIN.length; i++) {
            
            this.password[i] = this.guide[this.PIN[i]];
        }
    }
    
    public int[] getPassword () {
        
        replica = new int[this.PIN.length];
        
        for (int i = 0; i < this.PIN.length; i++) {
            
            replica[i] = this.password[i];
        }
        
        return replica;
    }
    
    public void checkPassword () {
        
        Scanner sc = new Scanner(System.in);
        
        int count = 0;
        boolean checker = false;
        while (!checker && count < 3){
        
            System.out.println("Enter pin");
            System.out.println("Note: default pin is 00000");
            setPIN();
            for (int k = 0; k < 10; k++) {
                
                System.out.println(this.guide[k]);      // number represent 0123456789
            }
            String s = sc.next();
            int[] userResponsePIN = new int[s.length()];

            for (int i = 0; i < s.length(); i++) {
                
                userResponsePIN[i] = Character.digit(s.charAt(i), 10);
            }
            
            checker = Authentication.verify(getPassword(), userResponsePIN);
            count++;
            
            if (checker == false) {
                
                if (count < 3) System.out.println("Wrong pin, try again");
                else System.out.println("Wrong pin, no more tries");
            } else {
                
                System.out.println("Welcome");
                System.out.println("Do you want to change password? yes/no");
                String choice = sc.next();
                if (choice.equals("yes")) {
                    
                    System.out.println("Enter new 5 digit pin");
                    String newPIN = sc.next();
                    int[] newPin = new int[5];
                    
                    for (int i = 0; i < s.length(); i++) {
                
                        newPin[i] = Character.digit(newPIN.charAt(i), 10);
                    }
                    setPIN(newPin);
                    checker = false;
                    count = 0;
                }
            }
        }
    }

    public static void main(String[] args) {

        Assignment as = new Assignment(); 
        as.checkPassword();
    }
}
