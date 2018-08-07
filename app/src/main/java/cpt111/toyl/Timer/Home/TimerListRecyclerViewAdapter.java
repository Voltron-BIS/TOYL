package cpt111.toyl.Timer.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cpt111.toyl.R;
import cpt111.toyl.Timer.Home.TimerListFragment.OnListFragmentInteractionListener;
import cpt111.toyl.Timer.Model.AbstractTimer;
import cpt111.toyl.Timer.Model.CompoundTimer;

import cpt111.toyl.Timer.Model.SimpleTimer;
import cpt111.toyl.Timer.ViewTimer.TimerViewFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.util.concurrent.TimeUnit;

// adapter type is the class created at the bottom of this file
public class TimerListRecyclerViewAdapter extends RecyclerView.Adapter<TimerListRecyclerViewAdapter.ViewHolder> {

    private ArrayList<CompoundTimer> listOfTimers;
    private final OnListFragmentInteractionListener mListener;


    public TimerListRecyclerViewAdapter(ArrayList<CompoundTimer> list, OnListFragmentInteractionListener listener) {
        this.listOfTimers = list;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_timerlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.timerName.setText(listOfTimers.get(position).getName());

        // get subtimers/sets within compound timer
        List<AbstractTimer> timers = listOfTimers.get(position).getListOfTimers();

        long timerLength = 0;

        for(int i = 0; i < timers.size(); ++i) {
            timerLength += timers.get(i).getLength();
        }

        // multiply by # of repeats in compound timer
        timerLength *= listOfTimers.get(position).getRepeats();

        holder.timerLength.setText(getDurationBreakdown(timerLength));


        // on click listener to open view timer
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                // Opening new fragment (SimpleTimer view)

                Fragment myFragment = new TimerViewFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("Position", position);


                //bundle.putString("Name", listOfTimers.get(position).getName());
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listOfTimers.size();
    }


    // where we set up the view for the "list"
    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView timerName;
        TextView timerLength;
        RelativeLayout parentLayout;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            timerName = view.findViewById(R.id.item_number);
            timerLength = view.findViewById(R.id.content);
            parentLayout = view.findViewById(R.id.timer_list_parent);
        }
    }

    public String getDurationBreakdown(long millis) {

        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);

    }
}
