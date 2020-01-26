package assignment1;

public class Authentication {
    
    private static int[] number;
    private static int[] replica;

    public static int[] generateSequence () {
        /**
         * generates the coded digits used to mask the real pin numbers
         */
        number = new int[10];

        for (int i = 0; i < number.length; i++) {
            
            number [i] = (int)(Math.random() * 9 + 1);
        }
        
        return getNumber();
    }

    public static boolean verify (int[] correctPIN, int[] userResponsePIN) {
        /**
         * checks to verify user input with stored pin
         */
        for (int i = 0; i < userResponsePIN.length; i++) {
            
            if (correctPIN[i] != userResponsePIN[i]) return false;
        }
        
        return true;
    }
    
    private static int[] getNumber () {
        /**
         * returns replica of coded digit array
         */
        replica = new int[10];
        
        for (int i = 0; i < number.length; i++) {
            
            replica[i] = number[i];
        }
        
        return replica;
    }
}
