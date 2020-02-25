package assignment3.question1;

import java.util.PriorityQueue;

public class AssignmentLog {

    private PriorityQueue<Assignment> log;

    public void addProject (Assignment newAssignment) {


    }

    public void addProject (int courseCode, String task, int dueDate) {

        Assignment as = new Assignment();
        as.setCourseCode(courseCode);
        as.setTask(task);
        as.setDueDate(dueDate);
        log.add(as);
    }

    public Assignment getNextProject () {

        return log.poll();
    }

    public void removeNextProject () {

        try {
            log.remove();
        } catch (Exception e) { 
            System.out.println ("Log is currently empty");
        }
    }
}