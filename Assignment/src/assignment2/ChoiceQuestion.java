package assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Nwabuobi
 */
public class ChoiceQuestion extends Question {
    
    List<Choice> choices;
    
    public ChoiceQuestion () {
        
        choices = new ArrayList<>();
    }
    
    public void addChoice (String choice, boolean correct) {
        
        choices.add(new Choice(choice, correct));
    }
    
    public String getAnswer () {
        
        for (int i = 0; i < choices.size(); i++) {
            
            Choice choice = choices.get(i);
            if (choice.getState()) return choice.getAnswer();
        }
        return null;
    }
}
