package assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Nwabuobi
 */
public class MultipleChoiceQuestion extends ChoiceQuestion {
    
    private List<Choice> result;
    
    public MultipleChoiceQuestion () {
        
        result = new ArrayList<>();
    }
    
    public List getMulAnswers () {
        
        List<Choice> choices = getChoices();
        for (Choice choice : choices) {
            
            if (choice.getState()) result.add(choice);
        }
        return result;
    }
}
