package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class Question {
    
    private String question;
    private String answer;
    private Choice userAnswer;
    
    public Question () {
        
        
    }
    
    public void setQuestion (String question) {
        
        this.question = question;
    }
    
    public String getQuestion () {
        
        return this.question;
    }
    
    public void setAnswer (String answer) {
        
        this.answer = answer;
    }
    
    public void setUserAnswer (Choice userAnswer) {
        
        this.userAnswer = userAnswer;
    }
    
    public void display () {
        
        System.out.println(this.question);
    }
    
    public boolean checkAnswer (String response) {
        
        if (this.userAnswer.getState()) return true;
        return false;
    }
}
