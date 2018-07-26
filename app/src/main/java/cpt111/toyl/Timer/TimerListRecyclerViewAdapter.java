package cpt111.toyl.Timer;

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
import cpt111.toyl.Timer.TimerListFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;

// adapter type is the class created at the bottom of this file
public class TimerListRecyclerViewAdapter extends RecyclerView.Adapter<TimerListRecyclerViewAdapter.ViewHolder> {

    // log for debugging
    private static final String TAG = "RecyclerViewAdapter";

    // temp fake data
    private ArrayList<String> mTimerNames = new ArrayList<>();
    private ArrayList<String> mTimerLengths = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;

    public TimerListRecyclerViewAdapter(ArrayList<String> mTimerNames, ArrayList<String> mTimerLengths, OnListFragmentInteractionListener listener) {
        this.mTimerNames = mTimerNames;
        this.mTimerLengths = mTimerLengths;
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
        Log.d(TAG, "onBindHolder: called.");

        holder.timerName.setText(mTimerNames.get(position));
        holder.timerLength.setText(mTimerLengths.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mTimerNames.get(position));


                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                // Opening new fragment (Timer view)
                Fragment myFragment = new TimerViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Name", mTimerNames.get(position));
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mTimerNames.size();
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
