package assignment2;

import java.util.Scanner;

/**
 *
 * @author Michael Nwabuobi
 */
public class NumericQuestion extends Question {
    
    private final double unitError = 0.01;
    public Scanner sc;
    
    public NumericQuestion () {
        
        sc = new Scanner(System.in);
    }
    
    public boolean checkAnswer (int userInput) {
        
        super.setUserAnswer(new Choice(userInput));
        double uAnswer = getUserAnswer();
        if (uAnswer <= (getNumAnswer() + 0.01) && uAnswer >= (getNumAnswer() - 0.01))
            return true;
        return false;
    }
}
