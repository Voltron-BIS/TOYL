package cpt111.toyl.Tasks.Model;


public class Tasks {

    private String name;
    private String description;
    private String category;
    private int start_date;
    private int due_date;
    private String status;

    public Tasks (String name, String description, String category, int start_date, int due_date, String status){
        this.name = name;
        this.description = description;
        this.category = category;
        this.start_date = start_date;
        this.due_date = due_date;
        this.status = status;
    }

    //methods


    //sets
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStart_date(int start_date) {
        this.start_date = start_date;
    }

    public void setDue_date(int due_date) {
        this.due_date = due_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //gets
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStart_date() {
        return start_date;
    }

    public int getDue_date() {
        return due_date;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }
}
