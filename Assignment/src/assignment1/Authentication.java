package assignment1;

public class Authentication {
    
    private static int[] number;
    private static int[] replica;
    private static int[] temp;

    public static int[] generateSequence () {
        
        number = new int[10];

        for (int i = 0; i < number.length; i++) {
            
            number [i] = (int)(Math.random() * 50 + 1);
        }
        
        return getNumber();
    }

    public static boolean verify (int[] correctPIN, int[] userResponsePIN) {
        
        temp = new int[5];
        for (int i = 0; i < userResponsePIN.length; i++) {
            
            temp[i] = number[userResponsePIN[i]];
            if (correctPIN[i] != temp[i]) return false;
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
