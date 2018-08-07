package cpt111.toyl.Timer.Model;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class Set extends AbstractTimer {

    private List<SimpleTimer> listOfTimers;
    private int repeats;
    private int completedRepeats;
    private boolean startedExecution;

    public int getRepeats() {
        return repeats;
    }

    public boolean getStartedExecution() {
        return startedExecution;
    }

    public void setStartedExecution(boolean hasStartedExecution) {
        this.startedExecution = hasStartedExecution;
    }

    public Set(String name, List<SimpleTimer> listOfTimers, int repeats) {
        super.setName(name);

        this.listOfTimers = listOfTimers;

        this.repeats = repeats;
        super.setLength(calculateLength());
        this.completedRepeats = 0;
        this.startedExecution = false;
    }

    public List<SimpleTimer> getListOfTimers() {
        return listOfTimers;
    }

    public void setListOfTimers(List<SimpleTimer> listOfTimers) {
        this.listOfTimers = listOfTimers;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public int getCompletedRepeats() {
        return completedRepeats;
    }

    public void setCompletedRepeats(int completedRepeats) {
        this.completedRepeats = completedRepeats;
    }

    private long calculateLength() {

        long length = 0;
        for (int i = 0; i < listOfTimers.size(); ++i) {
            length += listOfTimers.get(i).getLength();
        }

        return length * repeats;
    }

    @Override
    public List<SimpleTimer> getChildList() {
        return listOfTimers;
    }

}
