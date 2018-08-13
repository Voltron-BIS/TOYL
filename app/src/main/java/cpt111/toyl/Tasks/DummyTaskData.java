package cpt111.toyl.Tasks;


import java.util.ArrayList;
import cpt111.toyl.Tasks.Model.Tasks;


public class DummyTaskData {

    ArrayList<Tasks> listOfTasks = new ArrayList<>();

    public void addTasks(Tasks task) {
        listOfTasks.add(task);
        System.out.println("Size of list is " + listOfTasks.size());
    }

    public ArrayList<Tasks> getTask() {
        return listOfTasks;
    }

    public void deleteTask(int location) {
        listOfTasks.remove(location);
    }

}