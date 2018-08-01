package cpt111.toyl.Timer.AddTimer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Locale;

import cpt111.toyl.R;
import cpt111.toyl.Timer.AddTimer.TimerAddFragment;

public class TimerSelectTypeDialog extends DialogFragment {

    public interface OnInputSelected {
        void setType(boolean isCountDown);
    }

    public OnInputSelected mOnInputSelected;


    // widgets
//    private TextView name;
//    private NumberPicker hours, minutes, seconds;
    private Button addStopwatch, addTimer;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timer_select_type, container, false);

        addStopwatch = view.findViewById(R.id.timer_type_stopwatch);
        addTimer = view.findViewById(R.id.timer_type_timer);


        // Fragment myFragment = new TimerAddFragment();
        //                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
        final Fragment addTimerFragment = new TimerAddFragment();
        // set the target fragment
        setTargetFragment(addTimerFragment, 1);

        mOnInputSelected = (OnInputSelected) getTargetFragment();


        addStopwatch.setOnClickListener(new View.OnClickListener() {

            // TODO: Test code, needs to be redone with proper stuff
            @Override
            public void onClick(View view) {

                // Todo: change to proper constants
                mOnInputSelected.setType(false);
                getFragmentManager().beginTransaction().replace(R.id.container, addTimerFragment).addToBackStack(null).commit();
                getDialog().dismiss();
            }
        });

        addTimer.setOnClickListener(new View.OnClickListener() {

            // TODO: Test code, needs to be redone with proper stuff
            @Override
            public void onClick(View view) {

                // Todo: change to proper constants
                mOnInputSelected.setType(true);
                getFragmentManager().beginTransaction().replace(R.id.container, addTimerFragment).addToBackStack(null).commit();
                getDialog().dismiss();
            }
        });

        return view;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        try {
//            mOnInputSelected = (OnInputSelected) getTargetFragment();
//        } catch (ClassCastException e) {
//            //TODO: change it to a proper error log
//            System.out.println("Error attaching dialog");
//        }
    }

}
