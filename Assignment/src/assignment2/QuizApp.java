package assignment2;

import java.io.IOException;

/**
 *
 * @author Michael Nwabuobi
 */
public class QuizApp {
    
    public static void main (String[] args) throws IOException {
        
        ChoiceQuestion q = new ChoiceQuestion();
        q.setQuestion("How many heads do I have?");
        q.addChoice("1", true);
        q.addChoice("2", false);
        q.display();
        String choiceQuestionAnswer = q.sc.next();
        System.out.println(q.checkAnswer(choiceQuestionAnswer));
        
        MultipleChoiceQuestion m = new MultipleChoiceQuestion();
        m.setQuestion("How many people are in room 215 at 11:00 am on Tuesdays?");
        m.addChoice("1", false);
        m.addChoice("2", false);
        m.addChoice("3", false);
        m.addChoice("4", true);
        m.addChoice("5", false);
        m.display();
        String MultipleQuestionAnswer = m.sc.next();
        System.out.println(m.checkAnswer(MultipleQuestionAnswer));
        
        NumericQuestion n = new NumericQuestion();
        n.setQuestion("What year is it?");
        n.setNumAnswer(2020);
        n.display();
        int numericQuestionAnswer = n.sc.nextInt();
        System.out.println(n.checkAnswer(numericQuestionAnswer));
        
        FillInQuestion f = new FillInQuestion();
        f.setQuestion("The inventor of Java was _________");
        f.setAnswer("James Gosling");
        f.display();
        String fillInQuestionAnswer = f.br.readLine();
        System.out.println(f.equals(fillInQuestionAnswer));
        
        FreeResponseQuestion fr = new FreeResponseQuestion();
        fr.setQuestion("What is the size of an integer in a computer memory?");
        fr.setAnswer("4 bits");
        fr.display();
        String freeResponseQuestionAnswer = fr.br.readLine();
        System.out.println(fr.equals(freeResponseQuestionAnswer));
    }
}