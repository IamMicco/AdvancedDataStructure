package assignment2;

/**
 *
 * @author Michael Nwabuobi
 */
public class QuizApp {
    public static void main (String[] args) {
        
        MultipleChoiceQuestion question = new MultipleChoiceQuestion();
        question.addChoice("Michael", true);
        question.addChoice("James", false);
        question.print();
        
        NumericQuestion question2 = new NumericQuestion();
        question2.setNumAnswer(12);
        System.out.println(question2.getNumAnswer());
    }
}
