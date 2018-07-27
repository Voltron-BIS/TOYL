package cpt111.toyl.Timer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cpt111.toyl.R;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class TimerAddFragment extends Fragment implements TimerLengthDialog.OnInputSelected {

    private String timerName;

    // test data variables
    private ArrayList<String> mTimerNames = new ArrayList<>();
    private ArrayList<String> mTimerLengths = new ArrayList<>();

    private Button addSubTimerButton;
    private TextView testText;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private TimerListFragment.OnListFragmentInteractionListener mListener;

    public TimerAddFragment() {

    }

    public static TimerAddFragment newInstance(int columnCount) {
        TimerAddFragment fragment = new TimerAddFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compound_timer_add, container, false);
        Context context = view.getContext();

        addSubTimerButton = view.findViewById(R.id.add_sub_timer);
        testText = view.findViewById(R.id.test_Length);

        addSubTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimerLengthDialog dialog = new TimerLengthDialog();
                // set this as target fragment so the info can be sent from the dialog
                // request code can be any number, apparently
                dialog.setTargetFragment(TimerAddFragment.this, 1);
                dialog.show(getFragmentManager(), "TimerLengthDialog");

            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.timer_add_list);
        // add line between rows in list
        recyclerView.addItemDecoration(new DividerItemDecoration(context, VERTICAL));

        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        // set adapter
        recyclerView.setAdapter(new TimerListRecyclerViewAdapter(mTimerNames, mTimerLengths, mListener));

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

    // interface method
    @Override
    public void sendInput(String input) {

        testText.setText(input);

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

