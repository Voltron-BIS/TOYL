package cpt111.toyl.Timer.Home;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import cpt111.toyl.MainActivity;
import cpt111.toyl.R;

import cpt111.toyl.Timer.AddTimer.TimerAddFragment;
import cpt111.toyl.database.TestTimerViewModel;
import cpt111.toyl.database.entities.SimplerTimerEntity;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class TimerListFragment extends Fragment {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private TestTimerViewModel mTimerViewModel;

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

        // temp code to connect data
        MainActivity activity = (MainActivity)getActivity();
        //list = activity.listOfTimers.getTimers();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timerlist_list, container, false);
        Context context = view.getContext();


        MainActivity activity = (MainActivity) getActivity();

        RecyclerView recyclerView = view.findViewById(R.id.timer_list);
        // add line between rows in list
        recyclerView.addItemDecoration(new DividerItemDecoration(context, VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // set adapter
        final TimerListRecyclerViewAdapter adapter = new TimerListRecyclerViewAdapter(new ArrayList<SimplerTimerEntity>());
        recyclerView.setAdapter(adapter);

        // Get a new or existing ViewModel from the ViewModelProvider.
        mTimerViewModel = ViewModelProviders.of(this).get(TestTimerViewModel.class);

        // Add an observer on the LiveData returned by getAllTimers.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mTimerViewModel.getAllTimers().observe(this,  new Observer<List<SimplerTimerEntity>>() {
            @Override
            public void onChanged(@Nullable final List<SimplerTimerEntity> timers) {
                // Update the cached copy of the words in the adapter.
                adapter.setTimers(timers);
                System.out.println("TIMERS SIZE " + timers.size());
            }
        });




        // click action on button to add timer
        FloatingActionButton addTimer = view.findViewById(R.id.timer_add);
        addTimer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);




                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new TimerAddFragment();
                myFragment.setTargetFragment(TimerListFragment.this, NEW_WORD_ACTIVITY_REQUEST_CODE);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();

            }
        });

        return view;

        }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            //public SimplerTimerEntity(String name, String description, boolean isCountdownTimer, long length, int repeats)
            SimplerTimerEntity word = new SimplerTimerEntity(data.getStringExtra(TimerAddFragment.EXTRA_REPLY), 1000);
            mTimerViewModel.insert(word);
        } else {
            Toast.makeText(
                    getContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
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
