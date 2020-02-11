package assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Nwabuobi
 */
public class ChoiceQuestion extends Question {
    
    private List<Choice> choices;
    
    public ChoiceQuestion () {
        
        choices = new ArrayList<>();
    }
    
    public void addChoice (String choice, boolean correct) {
        
        choices.add(new Choice(choice, correct));
    }
    
    public List getChoices () {
        
        return choices;
    }
    
    public void print () {
        
        System.out.println(choices);
    }
    
    public boolean getAnswer () {
        
        for (Choice choice : choices) {
            
            if (choice.getState()) return true;
        }
        return false;
    }
}
