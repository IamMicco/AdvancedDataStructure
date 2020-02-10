/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class Question {
    
    String question;
    String answer;
    
    public Question () {
        
        
    }
    
    public void setQuestion (String question) {
        
        this.question = question;
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
