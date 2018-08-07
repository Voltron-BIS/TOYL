package cpt111.toyl.Timer.Model;


import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;


public abstract class AbstractTimer implements Parent<SimpleTimer> {

    private String name;
    private long length;

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    private long remainingTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setLength(long length) {
        this.length = length;
    }

    public long getLength() {
        return length;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public abstract int getRepeats();


}



