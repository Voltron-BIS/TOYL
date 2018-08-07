package cpt111.toyl.Timer.AddTimer.AddSubTimer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import cpt111.toyl.R;

public class TimerLengthDialog extends DialogFragment {

    public interface OnInputSelected {
        void sendInput(String name, long length);
    }

    public OnInputSelected mOnInputSelected;


    // UI fields
    private TextView name;
    private NumberPicker hours, minutes, seconds;
    private Button saveButton;

    private long length;
    private String nameString;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timer_add_subtimer, container, false);

        // set up UI Components
        setUpUIComponents(view);

        saveButton.setOnClickListener(new View.OnClickListener() {

            // TODO: Test code, needs to be redone with proper stuff
            @Override
            public void onClick(View view) {

                nameString = name.getText().toString();
                length = convertToMillis(hours, minutes, seconds);

                if(validateData()) {
                    mOnInputSelected.sendInput(nameString, length);
                    getDialog().dismiss();
                }
            }
        });



        return view;

    }

    public void setUpUIComponents(View view) {
        name = view.findViewById(R.id.add_sub_timer_name);
        hours = view.findViewById(R.id.hour_picker);
        setPicker(hours);
        minutes = view.findViewById(R.id.min_picker);
        setPicker(minutes);
        seconds = view.findViewById(R.id.sec_picker);
        setPicker(seconds);
        saveButton = view.findViewById(R.id.dialog_test_button);
    }

    public boolean validateData() {
        // name is not empty
        if(TextUtils.isEmpty(nameString)) {
            displayToast("Name is required");
            return false;
        }

        // timer is 00:00:00
        if(length == 0) {

            displayToast("Timer length cannot be zero");

            return false;
        }
        return true;
    }

    public long convertToMillis(NumberPicker hours, NumberPicker minutes, NumberPicker seconds) {
        //TODO: Use constants for values
        long milliseconds = (hours.getValue() * 3_600_000) + (minutes.getValue() * 60_000) + (seconds.getValue() * 1_000);

        return milliseconds;
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

    public void displayToast(String text) {
        Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
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
