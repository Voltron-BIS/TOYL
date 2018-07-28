package cpt111.toyl;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatisticDisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatisticDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticDisplayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    float timeSpent[] = {98.8f,98.8f,98.8f,98.8f,98.8f,98.8f};
    String categoryName[] = {"Domestic","Fitness","Study","Family","Sleep","Work"};

    private OnFragmentInteractionListener mListener;

    public StatisticDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticDisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticDisplayFragment newInstance(String param1, String param2) {
        StatisticDisplayFragment fragment = new StatisticDisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the root view
        View rootView = inflater.inflate(R.layout.fragment_statistic_display, container, false);

        LinearLayout cols = (LinearLayout) rootView.findViewById(R.id.cols);

        final int[] CHART_COLORS_1 = {
                Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0),
                Color.rgb(106, 150, 31), Color.rgb(179, 100, 53), Color.rgb(255, 0, 0)
        };


            //populating list of pie entries
            List<PieEntry> pieEntries = new ArrayList<>();
            for (int i = 0; i < timeSpent.length; i++) {
                pieEntries.add(new PieEntry(timeSpent[i], categoryName[i]));
            }



            PieDataSet dataSet = new PieDataSet(pieEntries, "Time Spent");
            dataSet.setColors(CHART_COLORS_1);
            PieData data = new PieData(dataSet);

            //get the chart;
            PieChart chart = (PieChart) rootView.findViewById(R.id.piechart);
            chart.setData(data);
            chart.setHoleRadius(25f);
            chart.setTransparentCircleAlpha(0);
            chart.setCenterTextSize(10);
            chart.setCenterText("Times Spent");
            chart.animateY(1000);
            chart.invalidate();



        return rootView;

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
     //   mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
