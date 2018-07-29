package cpt111.toyl;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Objects;

import static cpt111.toyl.R.layout.fragment_time_scheduler;

public class TimeSchedulerFragment extends Fragment {
    private static final String TAG = "TimeSchedulerFragment";

    private Button mDisplaydate;
    private TextView mDisplayDay;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    public TimeSchedulerFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(fragment_time_scheduler, container, false);

        mDisplaydate = rootView.findViewById(R.id.t_s_datepicker);
        mDisplayDay = rootView.findViewById(R.id.day_name);

        //Get current date and set.
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
        String day_name = "";

        month = month + 1;
        String todays_date = day + "/" + month + "/" + year;

        switch (day_of_week) {
            case 1:
                day_name = "Sunday";
                break;
            case 2:
                day_name = "Monday";
                break;
            case 3:
                day_name = "Tuesday";
                break;
            case 4:
                day_name = "Wednesday";
                break;
            case 5:
                day_name = "Thursday";
                break;
            case 6:
                day_name = "Friday";
                break;
            case 7:
                day_name = "Saturday";
                break;
        }
        mDisplaydate.setText(todays_date);
        mDisplayDay.setText(day_name);


        mDisplaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                // TODO: Use date string to retrieve tasks of the date selected.
                String date = day + "/" + month + "/" + year;

                mDisplaydate.setText(date);

            }
        };
        return rootView;

    }

}

