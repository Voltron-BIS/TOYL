package cpt111.toyl.Timer;

import java.util.ArrayList;

import cpt111.toyl.Timer.Model.CompoundTimer;

public class Dummy_TimerTempData {

    //temporary data that would technically be stored in db instead of here
    //TODO: to be changed to compound timers and then moved to db
    ArrayList<CompoundTimer> timers = new ArrayList<>();


    public void addCompoundTimer(CompoundTimer timer) {
        timers.add(timer);
        System.out.println("Size of list is " + timers.size());
    }

    public ArrayList<CompoundTimer> getTimers() {
        return timers;
    }

    public void deleteTimer(int location) {
        timers.remove(location);
    }

}


/*
TODO: What is next?

- Implement dummy data
- Set up add simple timer
- Set up view timer
- Set up running timer test
- SimpleTimer likely to be split into an abstract class with 2 children (timer/stopwatch)
- ArrayList to be of type AbstractTimer and then instanceOf will be used to separate implementation

/// New todo
Work out the stopwatch stuff
Add repetitions
Data validation?
Nested compound timers

 */