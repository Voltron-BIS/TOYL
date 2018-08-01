package cpt111.toyl.Timer.Model;

import java.util.ArrayList;

public class CompoundTimer extends AbstractTimer{

    private String name;
    private boolean isCountDown;
    private int repeats;

    // Object as it can be simple timer or compound timer
    private ArrayList<AbstractTimer> listOfTimers;

    public CompoundTimer(String name, boolean isCountDown, int repeats, ArrayList<AbstractTimer> listOfTimers) {
        super.setName(name);
        this.isCountDown = isCountDown;
        this.repeats = repeats;
        this.listOfTimers = listOfTimers;
    }

    public void addTimer(AbstractTimer timer) {
        listOfTimers.add(timer);
    }

    public boolean getIsCountDown() {
        return isCountDown;
    }


    public ArrayList<AbstractTimer> getListOfTimers() {
        return listOfTimers;
    }
}
