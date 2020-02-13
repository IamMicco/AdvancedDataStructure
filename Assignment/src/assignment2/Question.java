package assignment2;

import java.io.*;

/**
 *
 * @author Michael Nwabuobi
 */
public class Question {
    
    private String question;
    private String answer;
    private double numAnswer;
    private Choice userAnswer;
    BufferedReader br;
    
    public Question () {
        
        br = new BufferedReader(new InputStreamReader(System.in));
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
    
    public void setNumAnswer (double answer) {
        
        this.numAnswer = answer;
    }
    
    public double getNumAnswer () {
        
        return this.numAnswer;
    }
    
    public void setUserAnswer (Choice userAnswer) {
        
        this.userAnswer = userAnswer;
    }
    
    public int getUserAnswer () {
        
        return this.userAnswer.getnumAnswer();
    }
    
    public void display () {
        
        System.out.println(this.question);
    }
    
    @Override
    public String toString () {
        
        return this.question;
    }
    
    @Override
    public boolean equals (Object object) {
        
        return answer.equals(object);
    }
}