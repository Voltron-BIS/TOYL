package cpt111.toyl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class StatisticVisualisationFragment extends android.support.v4.app.Fragment implements OnChartGestureListener {

    BarChart barChart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Get the root view
        View rootView = inflater.inflate(R.layout.fragment_statistic_visualisation, container, false);

        LinearLayout cols = (LinearLayout) rootView.findViewById(R.id.cols);

        barChart = (BarChart) rootView.findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        //for use with version 3.0.3
        //List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(34f, 0));
        barEntries.add(new BarEntry(44f, 1));
        barEntries.add(new BarEntry(55f, 2));
        barEntries.add(new BarEntry(24f, 3));
        barEntries.add(new BarEntry(14f, 4));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("Domestic");
        theDates.add("Study");
        theDates.add("Family");
        theDates.add("Fitness");
        theDates.add("Recreation");


        BarData theData = new BarData(theDates, barDataSet);
        barChart.setData(theData);

        /*for use with version 3.0.3
        BarData theData = new BarData(barDataSet);
        barChart.setData(theData);
         */

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        Legend l = barChart.getLegend();
        l.setEnabled(false);


        barChart = rootView.findViewById(R.id.bargraph);
        barChart.setOnChartGestureListener(this);


        return rootView;


    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    public void onChartSingleTapped(MotionEvent me) {


        //creates text to change
        String statTitle = "DYNAMICLY LOADED DETAILS";
        String iCStr = "7";
        String sIStr = "8 mins";
        String lIStr = "2 hours";
        String tCStr = "45";
        String tPVal = "11";
        String aDVal = "47";


        //creates bundle to pass to next fragment
        Bundle bundle = new Bundle();
        bundle.putString("0",iCStr);
        bundle.putString("1",sIStr);
        bundle.putString("2",lIStr);
        bundle.putString("3",tCStr);
        bundle.putString("4",tPVal);
        bundle.putString("5",aDVal);
        bundle.putString("6",statTitle);

        //cretes fragment manager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        //creates second fragment and sets arguments
        StatisticDisplayFragment secondFragment = new StatisticDisplayFragment();
        secondFragment.setArguments(bundle);

        //sets fragment to be replaced and commits
        fragmentTransaction.replace(R.id.container,secondFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

}




