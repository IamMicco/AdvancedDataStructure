package assignment2;

import java.util.*;

/**
 *
 * @author Michael Nwabuobi
 */
public class MultipleChoiceQuestion extends ChoiceQuestion {
    
    private final List<Choice> correctAnswers;

    
    public MultipleChoiceQuestion () {
        
        correctAnswers = new ArrayList<>();
    }
    
    private void getMultiAnswers () {
        
        List<Choice> choices = getChoices();
        for (Choice choice : choices) {
            
            if (choice.getState()) correctAnswers.add(choice);
        }
    }
    
    public boolean checkAnswers (List<String> userAnswers) {
        
        boolean status;
        getMultiAnswers();
        for (String answer : userAnswers) {
            
            if (correctAnswers.contains(answer)) status = true;
            else status = false;
            
            if (!status) return false;
        }
        return true;
    }
}
