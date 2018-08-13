package cpt111.toyl.Tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cpt111.toyl.MainActivity;
import cpt111.toyl.R;
import cpt111.toyl.Tasks.Model.Tasks;

public class TaskTrackerAddTaskFragment extends Fragment {

    private EditText taskName;
    private EditText taskDescription;
    private Button SaveTask;
    private Button Cancel;

    public TaskTrackerAddTaskFragment() {
        //constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_tracker_add_task, container, false);

        //UI Linking Stuff
        taskName = view.findViewById(R.id.newTaskName);
        taskDescription = view.findViewById(R.id.newTaskDescription);
        SaveTask = view.findViewById(R.id.newTaskSave);
        Cancel = view.findViewById(R.id.newTaskCancel);

        //Save Button click
        SaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewTask();
            }
        });

        //Cancel Button Click
        Cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    public void addNewTask(){
        MainActivity activity = (MainActivity) getActivity();

        //create task object
        Tasks newtask = new Tasks(taskName.getText().toString(),taskDescription.getText().toString(),"default",0,0,"live");
        //add to listoftasks
        activity.listOfTasks.addTasks(newtask);

        activity.getSupportFragmentManager().popBackStackImmediate();
    }

    public void cancel(){
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportFragmentManager().popBackStackImmediate();
    }
}
