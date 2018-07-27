package cpt111.toyl.Timer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.Locale;

import cpt111.toyl.R;

public class TimerLengthDialog extends DialogFragment {

    public interface OnInputSelected {
        void sendInput(String input);
    }

    public OnInputSelected mOnInputSelected;


    // widgets
    private NumberPicker hours, minutes, seconds;
    private Button saveButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timer_select_length_dialog, container, false);

        hours = view.findViewById(R.id.hour_picker);
        setPicker(hours);
        minutes = view.findViewById(R.id.min_picker);
        setPicker(minutes);
        seconds = view.findViewById(R.id.sec_picker);
        setPicker(seconds);

        saveButton = view.findViewById(R.id.dialog_test_button);

        saveButton.setOnClickListener(new View.OnClickListener() {

            // TODO: Test code, needs to be redone with proper stuff
            @Override
            public void onClick(View view) {
                int hoursInt = hours.getValue();
                int minsInt = minutes.getValue();
                int secInt = seconds.getValue();

                String formattedTime = String.format(Locale.getDefault(),"%02d:%02d:%02d", hoursInt, minsInt, secInt);

                mOnInputSelected.sendInput(formattedTime);

                getDialog().dismiss();

            }
        });



        return view;

    }

    public void setPicker(NumberPicker picker) {

        // set range
        picker.setMaxValue(60);
        picker.setMinValue(0);

        // set default value
        picker.setValue(0);

        // set format to 2 digits
        picker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format(Locale.getDefault(), "%02d", i);
            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        } catch (ClassCastException e) {
            //TODO: change it to a proper error log
            System.out.println("Error attaching dialog");
        }
    }
}
