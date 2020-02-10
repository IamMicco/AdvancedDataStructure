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
    
    @Override
    public void display () {
        
        System.out.println(getQuestion());
    }
    
    public void setAnswer () {
        
        
    }
}
