package cpt111.toyl;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static cpt111.toyl.R.layout.fragment_time_scheduler;


public class TimeSchedulerFragment extends Fragment {
    private static final String TAG = "TimeSchedulerFragment";

    private Button mDisplaydate;
    private TextView mDisplayDay;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplaytask_name;
    private TextView mDisplaytask_category;
    private TextView mDisplaydescription;

    //test Task info
    //-----------------------------------------------
    private Button mTestbutton1;
    private Button mTestbutton2;
    private Button mTestbutton3;

    public class Test_info {
        public String task_Name;
        public String task_Category;
        public String task_Description;
        Test_info(String task_Name, String task_Category, String task_Description)
        {
            this.task_Name = task_Name;
            this.task_Category = task_Category;
            this.task_Description = task_Description;
        }
    }



    //-----------------------------------------------

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
        mDisplaytask_name = rootView.findViewById(R.id.task_name);
        mDisplaytask_category = rootView.findViewById(R.id.task_category);
        mDisplaydescription = rootView.findViewById(R.id.task_description);
        mTestbutton1 = rootView.findViewById(R.id.test1);
        mTestbutton2 = rootView.findViewById(R.id.test2);
        mTestbutton3 = rootView.findViewById(R.id.test3);


        //test Task info
        //-----------------------------------------------






        //Get current date and set.
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
        String day_name = Day(day_of_week);
        month = month + 1;
        String todays_date = day + "/" + month + "/" + year;


        mDisplaydate.setText(todays_date);
        mDisplayDay.setText(day_name);



        mDisplaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: work out how to get date out side onClick.
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

                GregorianCalendar GregorianCalendar = new GregorianCalendar(year, month, day);
                int day_of_week = GregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);

                String day_name = Day(day_of_week);

                month = month + 1;
                // TODO: Use date string to retrieve tasks of the date selected?.
                String date = day + "/" + month + "/" + year;

                mDisplaydate.setText(date);
                mDisplayDay.setText(day_name);


            }
        };

        //test Task info buttons
        mTestbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Test_info task = Get_Test_Info(0);
                mDisplaytask_name.setText(task.task_Name);
                mDisplaytask_category.setText(task.task_Category);
                mDisplaydescription.setText(task.task_Description);
            }
        });

        mTestbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Test_info task = Get_Test_Info(1);
                mDisplaytask_name.setText(task.task_Name);
                mDisplaytask_category.setText(task.task_Category);
                mDisplaydescription.setText(task.task_Description);
            }
        });

        mTestbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Test_info task = Get_Test_Info(2);
                mDisplaytask_name.setText(task.task_Name);
                mDisplaytask_category.setText(task.task_Category);
                mDisplaydescription.setText(task.task_Description);
            }
        });



        return rootView;

    }

    private String Day(int day){
        String day_name = "";
        switch (day) {
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
        return day_name;
    }

    //test Task info
    //-----------------------------------------------

    private Test_info Get_Test_Info(int Task){
        final Test_info[] test;
        test = new Test_info[3];

        test[0] = new Test_info("Test task 1","test 1", "Do a backflip ");
        test[1] = new Test_info("Test task 2","test 2", "Do lots for frontflips ");
        test[2] = new Test_info("Test task 3","tests 3", "do more sideflips ");


        return test[Task];
    }
    //-----------------------------------------------

}


