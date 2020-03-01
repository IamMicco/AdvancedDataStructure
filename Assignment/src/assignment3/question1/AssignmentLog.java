package assignment3.question1;

public class AssignmentLog {

    private PriorityQueue<Assignment> log;

    public void addProject (Assignment newAssignment) {

        log = new PriorityQueue<>(Assignment.class, 10);
    }

    public void addProject (int courseCode, String task, String dueDate) {

        Assignment as = new Assignment();
        as.setCourseCode(courseCode);
        as.setTask(task);
        as.setDueDate(dueDate);
        log.add(as);
    }

    public Assignment getNextProject () {

        return log.remove();
    }

    public void removeNextProject () {

        log.remove();
    }
}