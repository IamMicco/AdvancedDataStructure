package assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Nwabuobi
 */
public class QuizApp {
    
    public final List<String> s1;
    public String str;
    
    public QuizApp () {
        
        s1 = new ArrayList<>();
        str = "Hello how are you doing";
    }
    
    public void sub () {
        
        s1.add("air");
        s1.add("ballon");
        s1.add("carnival");
    }
    
    public static void main (String[] args) {
        
//        MultipleChoiceQuestion question = new MultipleChoiceQuestion();
//        question.addChoice("Michael", true);
//        question.addChoice("James", false);
//        question.print();
//        
//        NumericQuestion question2 = new NumericQuestion();
//        question2.setNumAnswer(12);
//        System.out.println(question2.getNumAnswer());
        
        QuizApp app = new QuizApp();
        app.sub();
        String val = "Hello how are you doing";
        
        if (app.s1.contains("carnival")) System.out.println("yahhhhhhh");
        System.out.println(app.s1);
        if (app.str.equals(val)) System.out.println("Hurrrrayyyyyyy");
    }
}
