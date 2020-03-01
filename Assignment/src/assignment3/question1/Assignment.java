package assignment3.question1;

import java.util.Date;
import java.util.Calendar;

public class Assignment implements Comparable<Assignment> {

    private int course;
    private String task;
    private Date date;

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

    public void setDueDate (String date) {

        String[] dateArr = date.split("/");
        int[] dateIntArr = new int[3];
        Calendar cal = Calendar.getInstance();
        int i = 0;
        for (String value : dateArr) {

            dateIntArr[i++] = Integer.parseInt(value);
        }
        cal.set(Calendar.YEAR, dateIntArr[2]);
        cal.set(Calendar.MONTH, dateIntArr[0] - 1);
        cal.set(Calendar.DAY_OF_MONTH, dateIntArr[1]);
        this.date = cal.getTime();
    }

    public Date getDueDate () {

        return date;
    }

    public String tillDueDate () {

        long difference = this.date.getTime() - System.currentTimeMillis();
        int seconds = (int) difference / 1000 % 60;
        int minutes = (int) difference / (60 * 1000) % 60;
        int hours = (int) difference / (60 * 60 * 1000) % 24;
        int days = (int) difference / (24 * 60 * 60 * 1000);
        return String.format("Assignment due in %d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
    }

    @Override
    public int compareTo (Assignment as) {

        return this.date.compareTo(as.getDueDate());
    }

    public String toString () {

        return String.format("Course number: %d, Task: %s, Due date: %s", course, task, date.toString());
    }
}