package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class Question {
    
    private String question;
    private String answer;
    
    public Question () {
        
        
    }
    
    public void setQuestion (String question) {
        
        this.question = question;
    }
    
    public String getQuestion () {
        
        return this.question;
    }
    
    public void addAnswer (String answer) {
        
        this.answer = answer;
    }
    
    public void display () {
        
        
    }
    
    public boolean checkAnswer (String response) {
        
        return true;  // This has no meaning
    }
}
