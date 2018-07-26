package cpt111.toyl.Timer;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import cpt111.toyl.EmptyTestFragment;
import cpt111.toyl.R;

import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TimerListFragment extends Fragment {


    // test data variables
    private ArrayList<String> mTimerNames = new ArrayList<>();
    private ArrayList<String> mTimerLengths = new ArrayList<>();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TimerListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TimerListFragment newInstance(int columnCount) {
        TimerListFragment fragment = new TimerListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTimersTest();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timerlist_list, container, false);
        Context context = view.getContext();

        RecyclerView recyclerView = view.findViewById(R.id.timer_list);
        // add line between rows in list
        recyclerView.addItemDecoration(new DividerItemDecoration(context, VERTICAL));

        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        // set adapter
        recyclerView.setAdapter(new TimerListRecyclerViewAdapter(mTimerNames, mTimerLengths, mListener));

        // click action on button to add timer
        FloatingActionButton addTimer = view.findViewById(R.id.timer_add);
        addTimer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("Add button was pressed");

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                //TODO: Testing with existing fragment but need new one... if it works

                Fragment myFragment = new TimerAddFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
            }
        });

        return view;

        }


    private void setTimersTest() {
        mTimerNames.add("Test Timer 1");
        mTimerLengths.add("2:00");

        mTimerNames.add("Morning Workout");
        mTimerLengths.add("20:00");

        mTimerNames.add("This is a fake timer");
        mTimerLengths.add("1:00");

        mTimerNames.add("We'll see if it works");
        mTimerLengths.add("29:00");

        System.out.println("Size is " + mTimerLengths.size());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }
}
