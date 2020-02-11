package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class Choice {
    
    private String answer;
    private int numAnswer;
    private boolean state;
    
    public Choice (String answer, boolean state) {
        
        this.answer = answer;
        this.state = state;
    }
    
    public Choice (int answer, boolean state) {
        
        this.numAnswer = answer;
    }
    
    public String getAnswer () {
        
        return this.answer;
    }
    
    public int getnumAnswer () {
        
        return this.numAnswer;
    }
    
    public boolean getState () {
        
        return this.state;
    }
}
