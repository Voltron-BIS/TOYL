package cpt111.toyl.Timer.Model;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class SimpleTimer extends AbstractTimer {


    public SimpleTimer(String name, long length) {
        super.setName(name);
        super.setLength(length);
        super.setRemainingTime(length);
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
