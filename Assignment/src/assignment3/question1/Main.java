package assignment3.question1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main (String[] args) throws IOException {

        AssignmentLog  log = new AssignmentLog();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("How many courses do you want to add");
            int iteration = Integer.parseInt(br.readLine());

            for (int i = 0; i < iteration; i++) {

                System.out.println("Enter course number:");
                int courseCode = Integer.parseInt(br.readLine());
                System.out.println("Enter task: ");
                String task = br.readLine();
                System.out.println("Enter due date: mm/dd/yyyy");
                String dueDate = br.readLine();
                log.addProject(courseCode, task, dueDate);
            }

            System.out.println("\n");
            System.out.println("How many classes to you want to pull");
            int iteration2 = Integer.parseInt(br.readLine());
            for (int j = 0; j < iteration2; j++) {

                Assignment as = log.getNextProject();
                System.out.println(as);
                System.out.println("\n");
            }
        } 
    }
}