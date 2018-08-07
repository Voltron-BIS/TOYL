package cpt111.toyl.Timer.Model;

import java.util.List;

public class CompoundTimer {

    private String name;
    private int repeats;
    // Object as it can be simple timer or set
    private List<AbstractTimer> listOfTimers;

    public CompoundTimer(String name, int repeats, List<AbstractTimer> listOfTimers) {
        this.name = name;
        this.repeats = repeats;
        this.listOfTimers = listOfTimers;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public void setListOfTimers(List<AbstractTimer> listOfTimers) {

        this.listOfTimers = listOfTimers;
    }

    public void addTimer(AbstractTimer timer) {
        listOfTimers.add(timer);
    }


    public List<AbstractTimer> getListOfTimers() {
        return listOfTimers;
    }


}
