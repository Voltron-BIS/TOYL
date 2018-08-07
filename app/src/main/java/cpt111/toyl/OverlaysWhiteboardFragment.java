package cpt111.toyl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.OffsetDateTime;
import java.time.ZoneId;


public class OverlaysWhiteboardFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER.
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    public OverlaysWhiteboardFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OverlaysWhiteboardFragment newInstance(String param1, String param2) {
        OverlaysWhiteboardFragment fragment = new OverlaysWhiteboardFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_overlays_whiteboard, container, false);

        LinearLayout cols = (LinearLayout) rootView.findViewById(R.id.cols);

        // Initialise test time zones
        String[] zones = {"Australia/Sydney","Europe/Zurich","Asia/Kolkata"};
        //String[] zones = {"Australia/Sydney"};

        for(String zone : zones) {

            // Initialise the dataset for the current time zone
            //mDataSet = new ArrayList<OffsetDateTime>();

            LinearLayout col = new LinearLayout(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(50,0,0,0);
            col.setLayoutParams(params);
            col.setBackgroundResource(R.drawable.border);
            col.setOrientation(LinearLayout.VERTICAL);
            col.setId(colId);
            col.setTag(new String("Col" + colId));
            colIds.add(colId);
            //colId = colId + 10000;
            colId++;
            cols.addView(col);

            // Add the timezone label
            TextView zoneLabel = new TextView(getActivity());
            zoneLabel.setText(zone);
            zoneLabel.setTextSize(12);
            zoneLabel.setGravity(Gravity.CENTER);
            zoneLabel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            zoneLabel.setRotation(90);
            cols.addView(zoneLabel);


            // Get the current time
            OffsetDateTime timeStamp = OffsetDateTime.now(ZoneId.of(zone));
            OffsetDateTime timeStampMinus24 = timeStamp.minusDays(1);
            OffsetDateTime timeStampPlus24 = timeStamp.plusDays(1);
            //OffsetDateTime timeStampPlus24 = timeStamp;
            OffsetDateTime timeIndex = timeStampMinus24;

            while(timeIndex.isBefore(timeStampPlus24)) {
                //mDataSet.add(timeIndex);
                String hour = "";
                TextView textView = new TextView(getActivity());

                // If the hour is less than 10 than pad it with a zero
                if(timeIndex.getHour() < 10) {
                    hour = "0" + String.valueOf(timeIndex.getHour());
                }
                else {
                    hour = String.valueOf(timeIndex.getHour());
                }

                Integer hourFlag = commonTimes.get(hourId);

                // If the current hour is within a reasonable meeting time range then add it to
                // the common times array
                if(timeIndex.getHour() >= 6 && timeIndex.getHour() <= 22) {

                    // If not previously initialised then set to 0
                    if(hourFlag == null) {
                        commonTimes.put(hourId,0);
                    }

                }
                else {
                    // If not previously initialised then set to 0
                    if(hourFlag == null) {
                        commonTimes.put(hourId,1);
                    }
                    else {
                        commonTimes.put(hourId,++hourFlag);
                    }
                }

                // If the current hour is midnight then add a divider above the current text view
                /*
                if(hour.equals("00")) {
                    int hrHeight = (int) getResources().getDisplayMetrics().density * 2;
                    View hr = new View(getContext());
                    hr.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, hrHeight));
                    hr.setBackgroundColor(Color.parseColor("#FF0000"));
                    col.addView(hr);
                }
                */

                textView.setText(hour);
                textView.setTextSize(12);
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                col.addView(textView);
                timeIndex = timeIndex.plusHours(1);
            }

        }


        // Loop through the common times and if highlight any common times across all time zones
        Set set = commonTimes.entrySet();
        Iterator i = set.iterator();
        Integer key;
        Integer value;
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            key = (Integer) me.getKey();
            value = (Integer) me.getValue();

            // If the current time index is common across all zones then highlight the time
            if(value == 0) {
                for(int k = 0; k < colIds.size(); k++) {
                    //cols.findViewById(k).findViewById(k + key).setBackgroundColor(Color.parseColor("#00FF00"));
                    //LinearLayout currCol = (LinearLayout) cols.findViewById(colIds.get(k));
                    LinearLayout currCol = (LinearLayout) cols.findViewWithTag(new String("Col" + colIds.get(k)));
                    //LinearLayout currCol = (LinearLayout) cols.getChildAt(k);
                    TextView currHour = (TextView) currCol.getChildAt(key);
                    currHour.setBackgroundColor(Color.parseColor("#00FF00"));



                }
            }

        }

        // Attach a listener to the fab
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.overlays_fab);
        fab.setOnClickListener(this);

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
        */
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
        //mListener = null;
    }

    @Override
    public void onClick(View view) {
        // Initialise the fragment manager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new OverlaysSelectFragment();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, fragment).commit();
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
    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    */
}
