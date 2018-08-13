package cpt111.toyl.Tasks;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cpt111.toyl.MainActivity;
import cpt111.toyl.R;
import cpt111.toyl.Tasks.Model.Tasks;

import static android.support.v7.widget.RecyclerView.VERTICAL;


public class TaskTrackerFragment extends Fragment {
    private RecyclerView recyclerView;
    private TaskTrackerRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //Required constructor (empty)
    public TaskTrackerFragment() {
        //constructor
    }
    @Nullable

    //the "main" class - Build main view for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_tracker, container, false);
        Context context = view.getContext();
        MainActivity activity = (MainActivity) getActivity();

        //list view
        RecyclerView recyclerView = view.findViewById(R.id.tasksRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, VERTICAL));

        mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new TaskTrackerRecyclerViewAdapter(activity.listOfTasks.getTask());
        recyclerView.setAdapter(mAdapter);

        // click action on button to add timer
        FloatingActionButton addTask = view.findViewById(R.id.newtaskbutton);
        addTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new cpt111.toyl.Tasks.TaskTrackerAddTaskFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
            }
        }
        );

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
}