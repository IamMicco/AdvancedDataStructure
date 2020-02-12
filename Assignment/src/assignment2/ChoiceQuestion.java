package assignment2;

import java.util.*;

/**
 *
 * @author Michael Nwabuobi
 */
public class ChoiceQuestion extends Question {
    
    private List<Choice> choices;
    public Scanner sc;
    
    public ChoiceQuestion () {
        
        choices = new ArrayList<>();
        sc = new Scanner(System.in);
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
    
    public boolean checkAnswer (String userAnswer) {
        
        for (Choice choice : choices) {
            
            if (choice.getState() && choice.getAnswer().equals(userAnswer)) return true;
        }
        return false;
    }
    
    @Override
    public void display () {
        
        super.display();
        for (Choice choice : choices) {
            
            System.out.println(String.format("-> %s", choice.getAnswer()));
        }
    }
}
