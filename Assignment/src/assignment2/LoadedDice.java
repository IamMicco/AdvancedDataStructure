package assignment2;

import java.util.Random;

/**
 *
 * @author Michael Nwabuobi
 */
public class LoadedDice extends Random {
    
    public LoadedDice () {
        
        super();
    }
    
    @Override
    public int nextInt(int num) {
        
        int nextInt = super.nextInt(num);
        int random = (int) (Math.random() * 2);
        System.out.println(String.format("This is the random value: %d", random));
        if (random == 1) return ++num;
        else return nextInt;
    }
    
    public static void main (String[] args) {
        
        LoadedDice dice = new LoadedDice();
        System.out.println(dice.nextInt(100));
    }
}
