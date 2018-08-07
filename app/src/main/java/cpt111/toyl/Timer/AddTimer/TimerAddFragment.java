package cpt111.toyl.Timer.AddTimer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.List;

import cpt111.toyl.MainActivity;
import cpt111.toyl.R;
import cpt111.toyl.Timer.AddTimer.AddSet.TimerAddSetFragment;
import cpt111.toyl.Timer.AddTimer.AddSubTimer.TimerLengthDialog;
import cpt111.toyl.Timer.Home.TimerListFragment;
import cpt111.toyl.Timer.Model.AbstractTimer;
import cpt111.toyl.Timer.Model.CompoundTimer;
import cpt111.toyl.Timer.Model.Set;
import cpt111.toyl.Timer.Model.SimpleTimer;


import static android.support.v7.widget.RecyclerView.VERTICAL;
import static java.lang.Integer.parseInt;


public class TimerAddFragment extends Fragment implements TimerLengthDialog.OnInputSelected, TimerAddSetFragment.OnSavedSet {

    // list of timers that will be added to compound timer being created
    private ArrayList<AbstractTimer> listOfTimersToAdd = new ArrayList<>();

    // items in form
    private EditText timerName;
    private EditText repetitions;
    private Button addSubTimerButton;
    private Button addSetButton;
    private Button saveTimer;

    // listener
    private TimerListFragment.OnListFragmentInteractionListener mListener;

    // mandatory constructor
    public TimerAddFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate fragment
        View view = inflater.inflate(R.layout.fragment_compound_timer_add, container, false);
        Context context = view.getContext();

        // set up UI Components
        setUpUIComponents(view);

        // set up recycler view adapter
        setUpAdapter(view, context);

        // add sub-timer button listener
        addSubTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSubTimer();
            }
        });


        // add set button listener
        addSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSet();
            }
        });


        // save button button listener
        saveTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCompoundTimer();
            }
        });

         return view;
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

    // Set up recycler view adapter
    public void setUpAdapter(View view, Context context) {
        // list of timers included in compound timer
        RecyclerView recyclerView = view.findViewById(R.id.timer_add_list);
        // add line between rows in list
        recyclerView.addItemDecoration(new DividerItemDecoration(context, VERTICAL));
        // set layout
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        // set adapter
        recyclerView.setAdapter(new TimerAddListRecyclerViewAdapter(listOfTimersToAdd, mListener));
    }

    // link UI components to fragment
    public void setUpUIComponents(View view) {
        timerName = view.findViewById(R.id.add_timer_name);
        addSubTimerButton = view.findViewById(R.id.add_sub_timer);

        addSetButton = view.findViewById(R.id.add_set);

        saveTimer = view.findViewById(R.id.timer_add_save);
        repetitions = view.findViewById(R.id.repetitions);
    }

    // Add sub-timer functionality
    public void addSubTimer() {
        TimerLengthDialog dialog = new TimerLengthDialog();
        // set this as target fragment so the info can be sent from the dialog
        // request code can be any number, apparently
        dialog.setTargetFragment(TimerAddFragment.this, 1);
        dialog.show(getFragmentManager(), "TimerLengthDialog");
    }


    // Add set functionality
    public void addSet() {
        TimerAddSetFragment fragment = new TimerAddSetFragment();
//        TimerLengthDialog dialog = new TimerLengthDialog();
        // set this as target fragment so the info can be sent from the dialog
        // request code can be any number, apparently
        fragment.setTargetFragment(TimerAddFragment.this, 1);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
        //fragment.show(getFragmentManager(), "TimerLengthDialog");
    }


    // save compound timer functionality
    public void saveCompoundTimer() {
        // data validation
        if(validData()){
            MainActivity activity = (MainActivity) getActivity();


            CompoundTimer toAdd = new CompoundTimer(timerName.getText().toString(), parseInt(repetitions.getText().toString()), listOfTimersToAdd);

            activity.listOfTimers.addCompoundTimer(toAdd);

            // go back to timers list
            activity.getSupportFragmentManager().popBackStackImmediate();
        }
    }

    // validate data before saving
    public boolean validData() {

        // name is not empty
        if (TextUtils.isEmpty(timerName.getText())) {
            displayToast("Name is required");
            return false;
        }

        // repetitions != empty, int, >0
        if(TextUtils.isEmpty(repetitions.getText())) {
            displayToast("Repetitions is required");
            return false;
        } else if (parseInt(repetitions.getText().toString()) < 1) {
            displayToast("Repetitions must be > 0");
            return false;
        }

        // timer list isn't empty
        if(listOfTimersToAdd.size() == 0) {
            displayToast("At least 1 nested timer is required");
            return false;
        }

        return true;
    }

    public void displayToast(String text) {
       Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }


    // interface methods TODO clean up/better names
    @Override
    public void sendInput(String name, long length) {

        // add subtimer to list of timers to be added to the compound timer
        listOfTimersToAdd.add(new SimpleTimer(name, length));
    }


    @Override
    public void saveSet(String name, int repetitions, List<SimpleTimer> timers) {
        // add set to list of timers to be added to the compound timer
        listOfTimersToAdd.add(new Set(name, timers, repetitions));
    }

}

