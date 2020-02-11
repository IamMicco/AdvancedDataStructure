package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class NumericQuestion extends Question {
    
    private final double unitError = 0.01;
    
    public boolean checkAnswer (Choice userAnswer) {
        
        super.setUserAnswer(userAnswer);
        double uAnswer = getUserAnswer();
        if (uAnswer <= (getNumAnswer() + 0.01) || uAnswer >= (getNumAnswer() - 0.01))
            return true;
        return false;
    }
}
