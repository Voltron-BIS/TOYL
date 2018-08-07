package cpt111.toyl.Timer.ViewTimer;


import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import cpt111.toyl.R;
import cpt111.toyl.Timer.Home.TimerListFragment.OnListFragmentInteractionListener;
import cpt111.toyl.Timer.Model.AbstractTimer;
import cpt111.toyl.Timer.Model.CompoundTimer;
import cpt111.toyl.Timer.Model.Set;
import cpt111.toyl.Timer.Model.SimpleTimer;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimerViewRecyclerViewAdapter extends ExpandableRecyclerAdapter<AbstractTimer, SimpleTimer, TimerViewRecyclerViewAdapter.AbstractTimerHolder, TimerViewRecyclerViewAdapter.TimerViewHolder> {

    private List<AbstractTimer> timerList;

    public TimerViewRecyclerViewAdapter(List<AbstractTimer> timerList) {
        super(timerList);
        this.timerList = timerList;
    }

    @Override
    public AbstractTimerHolder onCreateParentViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_timerlist, parent, false);
        return new AbstractTimerHolder(view);
    }

    @Override
    public TimerViewHolder onCreateChildViewHolder(ViewGroup child, int viewType) {
        View view = LayoutInflater.from(child.getContext())
                .inflate(R.layout.fragment_timerlist, child, false);
        return new TimerViewHolder(view);
    }

    //@Override
    public void onBindParentViewHolder(@NonNull AbstractTimerHolder compoundTimerHolder, int parentPosition, @NonNull AbstractTimer timer) {
        compoundTimerHolder.bind(timer);
    }

    public void onBindChildViewHolder(@NonNull TimerViewHolder timerViewHolder, int parentPosition, int childPosition, @NonNull SimpleTimer timer) {
        timerViewHolder.bind(timer);
    }



    public String getDurationBreakdown(long millis) {

        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);

    }

    public class AbstractTimerHolder extends ParentViewHolder {

        private TextView mTimerName;
        private TextView mTimerLength;

        public AbstractTimerHolder(View itemView) {
            super(itemView);
            mTimerName = itemView.findViewById(R.id.item_number);
            mTimerLength = itemView.findViewById(R.id.content);
        }

        public void bind(AbstractTimer timer) {


            if (timer.getChildList().size() > 0) {
                mTimerName.setTypeface(null, Typeface.BOLD);
                mTimerLength.setTypeface(null, Typeface.BOLD);
                String name = timer.getName() + " (" + timer.getRepeats()+ ")";
                mTimerName.setText(name);
            } else {
                mTimerName.setText(timer.getName());
            }
            mTimerLength.setText(getDurationBreakdown(timer.getLength()));
        }
    }

    public class TimerViewHolder extends ChildViewHolder {
        private TextView mSubTimerName;
        private TextView mTimerLength;

        public TimerViewHolder(View itemView) {
            super(itemView);
            mSubTimerName = itemView.findViewById(R.id.item_number);
            mTimerLength = itemView.findViewById(R.id.content);
            mSubTimerName.setPadding(50,0,0,0);

        }

        public void bind(SimpleTimer timer) {
            mSubTimerName.setText(timer.getName());
            mTimerLength.setText(getDurationBreakdown(timer.getRemainingTime()));
        }
    }


}