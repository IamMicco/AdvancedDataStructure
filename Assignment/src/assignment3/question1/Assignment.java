package assignment3.question1;

public class Assignment extends Comparable<T> {

    private int course;
    private String task;
    private int date;

    public void setCourseCode (int courseCode) {

        this.course = courseCode;
    }

    public int getCourseCode () {

        return course;
    }

    public void setTask (String task) {

        this.task = task;
    }

    public String getTask () {

        return task;
    }

    public void setDueDate (int date) {

        this.date = date;
    }

    public int getDueDate () {

        return date;
    }


}