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
    
    public Choice (String answer, boolean state) {
        
        this.answer = answer;
        this.state = state;
    }
    
    public String getAnswer () {
        
        return this.answer;
    }
    
    public boolean getState () {
        
        return this.state;
    }
}
