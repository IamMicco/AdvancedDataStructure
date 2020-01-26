package assignment1;

public class Authentication {
    
    private static int[] number;
    private static int[] replica;

    public static int[] generateSequence () {
        
        number = new int[10];

        for (int i = 0; i < number.length; i++) {
            
            number [i] = (int)(Math.random() * 9 + 1);
        }
        
        return getNumber();
    }

    public static boolean verify (int[] correctPIN, int[] userResponsePIN) {
        
        for (int i = 0; i < userResponsePIN.length; i++) {
            
            if (correctPIN[i] != userResponsePIN[i]) return false;
        }
        
        return true;
    }
    
    private static int[] getNumber () {
        
        replica = new int[10];
        
        for (int i = 0; i < number.length; i++) {
            
            replica[i] = number[i];
        }
        
        return replica;
    }
}
