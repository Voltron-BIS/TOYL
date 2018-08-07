package cpt111.toyl.Timer.AddTimer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cpt111.toyl.R;
import cpt111.toyl.Timer.Home.TimerListFragment.OnListFragmentInteractionListener;
import cpt111.toyl.Timer.Model.AbstractTimer;

import cpt111.toyl.Timer.Model.SimpleTimer;

import java.util.ArrayList;
import java.util.List;


import java.util.concurrent.TimeUnit;

// adapter type is the class created at the bottom of this file
public class TimerAddListRecyclerViewAdapter extends RecyclerView.Adapter<TimerAddListRecyclerViewAdapter.ViewHolder> {


    private List<AbstractTimer> timerList;
    private Integer type = 1; // TODO 1 for timer, hypothetically, 2 for stopwatch? need to change when implementing stopwatch
    private final OnListFragmentInteractionListener mListener;

    public TimerAddListRecyclerViewAdapter(List<AbstractTimer> timerList, OnListFragmentInteractionListener listener) {

        this.timerList = timerList;
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

        holder.timerName.setText(timerList.get(position).getName().toString());

        if (type == 1) {

            AbstractTimer timer = (AbstractTimer) timerList.get(position);

            holder.timerLength.setText(getDurationBreakdown(timer.getLength()));
        }

        // TODO to be changed to editing the timer, not viewing

//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//

//                // Opening new fragment (SimpleTimer view)

//                Fragment myFragment = new TimerViewFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("Name", timerList.get(position).getName().toString());
//                myFragment.setArguments(bundle);
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
//            }
//        });

    }

    public String getDurationBreakdown(long millis) {

        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);

    }

    @Override
    public int getItemCount() {
        return timerList.size();
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
}