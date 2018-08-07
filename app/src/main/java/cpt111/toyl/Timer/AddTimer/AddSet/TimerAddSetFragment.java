package cpt111.toyl.Timer.AddTimer.AddSet;

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
import cpt111.toyl.Timer.AddTimer.AddSubTimer.TimerLengthDialog;
import cpt111.toyl.Timer.Home.TimerListFragment;
import cpt111.toyl.Timer.Model.AbstractTimer;
import cpt111.toyl.Timer.Model.CompoundTimer;
import cpt111.toyl.Timer.Model.SimpleTimer;
import cpt111.toyl.Timer.AddTimer.TimerAddListRecyclerViewAdapter;

import static android.support.v7.widget.RecyclerView.VERTICAL;
import static java.lang.Integer.parseInt;

public class TimerAddSetFragment extends Fragment implements TimerLengthDialog.OnInputSelected {


    public interface OnSavedSet {
        void saveSet(String name, int repetitions, List<SimpleTimer> timers);
    }

    public OnSavedSet onSavedSet;


    // list of timers that will be added to the set
    private List<SimpleTimer> listOfTimersToAdd = new ArrayList<>();
    // items in form
    private EditText timerName;
    private EditText repetitions;
    private Button addSubTimerButton;
    private Button addSetButton;
    private Button saveTimer;


    // listener
    private TimerListFragment.OnListFragmentInteractionListener mListener;

    // mandatory constructor
    public TimerAddSetFragment() {
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

        // hide add set button as not used for this view
        addSetButton.setVisibility(View.INVISIBLE);

        // set up recycler view adapter
        setUpAdapter(view, context);

        // add sub-timer button listener
        addSubTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSubTimer();
            }
        });

        // save button button listener
        saveTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSet();
            }
        });

        getActivity().setTitle("Add Set");

         return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onSavedSet = (OnSavedSet) getTargetFragment();
        } catch (ClassCastException e) {
            //TODO: change it to a proper error log
            System.out.println("Error attaching dialog");
        }
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
        recyclerView.setAdapter(new TimerAddSetRecyclerViewAdapter(listOfTimersToAdd, mListener));
    }

    // link UI components to fragment
    public void setUpUIComponents(View view) {
        timerName = view.findViewById(R.id.add_timer_name);
        timerName.setHint("Set Name");
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
        dialog.setTargetFragment(TimerAddSetFragment.this, 1);
        dialog.show(getFragmentManager(), "TimerLengthDialog");
    }

    // save compound timer functionality
    public void saveSet() {

        onSavedSet.saveSet(timerName.getText().toString(), parseInt(repetitions.getText().toString()), listOfTimersToAdd);
        // data validation
//        if(validData()){
//            MainActivity activity = (MainActivity) getActivity();
//
//            CompoundTimer toAdd = new CompoundTimer(timerName.getText().toString(), parseInt(repetitions.getText().toString()), listOfTimersToAdd);
//            activity.listOfTimers.addCompoundTimer(toAdd);
//
//            // go back to timers list
////            activity.getSupportFragmentManager().popBackStackImmediate();
//        }
        getActivity().getSupportFragmentManager().popBackStackImmediate();
        System.out.println("Set will be saved");
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
        System.out.println("Timer size" + listOfTimersToAdd.size());
    }


}

