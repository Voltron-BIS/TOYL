package cpt111.toyl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class StatisticVisualisationFragment extends android.support.v4.app.Fragment {

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
        barEntries.add(new BarEntry(34f,0));
        barEntries.add(new BarEntry(44f,1));
        barEntries.add(new BarEntry(55f,2));
        barEntries.add(new BarEntry(24f,3));
        barEntries.add(new BarEntry(14f,4));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("Domestic");
        theDates.add("Study");
        theDates.add("Family");
        theDates.add("Fitness");
        theDates.add("Recreation");



        BarData theData = new BarData(theDates,barDataSet);
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


        return rootView;


    }
}