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
public class Choice {
    
    private String answer;
    private boolean state;
    
    public void setAnswer(String answer) {
        
        this.answer = answer;
    }
    
    public void setState (boolean state) {
        
        this.state = state;
    }
    
    public String getAnswer () {
        
        return this.answer;
    }
    
    public boolean getState () {
        
        return this.state;
    }
}
