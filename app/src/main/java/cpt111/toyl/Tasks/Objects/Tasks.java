package cpt111.toyl.Tasks.Objects;

public class Tasks {
    private String name;
    private String description;
    private int start_date;
    private int due_date;
    private int status;
    private int priority;
    private int estimate;

    public Tasks(String name, String description, int start_date, int due_date, int status, int priority, int estimate ) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.due_date = due_date;
        this.status = status;
        this.priority = priority;
        this.estimate = estimate;
    }

    public int getStart_Date() {
        return start_date;
    }

    public void setStart_Date(int date) {
        this.start_date = date;
    }

    public int getDue_Date() { return due_date; }

    public void setDue_Date(int date) {
        this.due_date = date;
    }
}
