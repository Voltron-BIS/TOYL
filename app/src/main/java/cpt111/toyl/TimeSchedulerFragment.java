package cpt111.toyl;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private Context mContext;
    private RecyclerView mRecyclerView;


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
        mRecyclerView = rootView.findViewById(R.id.scheduler_timeline);


        //Get current date and set.
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
        //call day name function
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

        //Date set Listener
        //-----------------------------------------------
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


        // Recyclerview
        //-----------------------------------------------
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        //Recyclerview Test data is in place
        Task_Data_Array task_data[] = Get_Test_Info();

        //Data formating for recyclerview
        String[] Time = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
        String[] Name = {"","","","","","","","","","","","","","","","","","","","","","","",""};
        String[] Category = {"","","","","","","","","","","","","","","","","","","","","","","",""};
        String[] Description = {"","","","","","","","","","","","","","","","","","","","","","","",""};
        int i = 0;
        while (i<23){
            if (task_data.length == 0) break;
            if (task_data.length>i){
                int j = 0;
                while (j<23) {
                    String item1 = task_data[i].task_Time;
                    String item2 = Time[j];
                    if (item1.equals(item2)) {
                        Name[j] = task_data[i].task_Name;
                        Category[j] = task_data[i].task_Category;
                        Description[j] = task_data[i].task_Description;
                        break;
                    }
                    else j = j + 1;
                }
            }
            i = i + 1;
        }

        TimeSchedulerFragmentRecyclerViewAdapter mAdapter = new TimeSchedulerFragmentRecyclerViewAdapter(getContext(),Time,Name,Category,Description,rootView);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;

    }




//function for setting day name
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

    public static class Task_Data_Array {
        public String task_Name;
        public String task_Category;
        public String task_Description;
        public String task_Time;
        Task_Data_Array(String task_Name, String task_Category, String task_Description, String task_Time)
        {
            this.task_Name = task_Name;
            this.task_Category = task_Category;
            this.task_Description = task_Description;
            this.task_Time = task_Time;
        }
    }

    private static Task_Data_Array[] Get_Test_Info(){
        final Task_Data_Array[] test;
        test = new Task_Data_Array[3];

        test[0] = new Task_Data_Array("Test task 1","test 1", "Do a backflip ","2");
        test[1] = new Task_Data_Array("Test task 2","test 2", "Do lots for frontflips ","4");
        test[2] = new Task_Data_Array("Test task 3","tests 3", "do more sideflips ","8");


        return test;
    }

// called from adapter to set task info
    public static void Set_D_values(String Time,View rootView){
        TextView Displaytask_name;
        TextView Displaytask_category;
        TextView Displaydescription;
        Displaytask_name = rootView.findViewById(R.id.task_name);
        Displaytask_category = rootView.findViewById(R.id.task_category);
        Displaydescription = rootView.findViewById(R.id.task_description);
        Log.d(TAG, "Set_D_values: has been called the Position is: "+ Time);
        Task_Data_Array test[] = Get_Test_Info();
        int i = 0;
        while (i<test.length){

            if (Time.equals(test[i].task_Time)){
                Displaytask_name.setText(test[i].task_Name);
                Displaytask_category.setText(test[i].task_Category);
                Displaydescription.setText(test[i].task_Description);
                break;
            }
            else i = i +1;
            }



//        Task_Data_Array test[] = Get_Test_Info();


    }

    //-----------------------------------------------

    //Test Timeline Data
    //-----------------------------------------------

//    public class Test_info_recycler {
//        public int task_Time_R;
//        Test_info_recycler(int task_Time_R)
//        {
//
//            this.task_Time_R = task_Time_R;
//        }
//    }
//
//    private Test_info_recycler Get_Recyclerview_Date(){
//        final Test_info_recycler[] Test_info_recycler;
//        Test_info_recycler = new Test_info_recycler[24];
//
//        Test_info_recycler[0] = new Test_info_recycler(1);
//        Test_info_recycler[1] = new Test_info_recycler(4);
//        Test_info_recycler[2] = new Test_info_recycler(8);
//
//        return Test_info_recycler[];
//    }


    //-----------------------------------------------

}


