package cpt111.toyl.Timer.Model;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class SimpleTimer extends AbstractTimer {

    private long elapsed;

    public SimpleTimer(String name, long length) {
        super.setName(name);
        super.setLength(length);
        // remaining time == length at initialisation
        super.setRemainingTime(length);
        // starts at 0
        this.elapsed = 0;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }


    @Override
    public List getChildList() {
        List<SimpleTimer> emptyList = new ArrayList<>();
        return emptyList;
    }

    public int getRepeats() {
        return 1;
    }
}
