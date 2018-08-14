package cpt111.toyl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class StatisticDisplayFragment extends android.support.v4.app.Fragment {

    public StatisticDisplayFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistic_display, container, false);
        String iCStr,sIStr,lIStr,tCStr,tPVal,aDVal,statTitle;


        if (getArguments() != null) {
            //gets bundle
            Bundle bundle = getArguments();


            //extracts bundle content to strings
            iCStr = bundle.getString("0");
            sIStr = bundle.getString("1");
            lIStr = bundle.getString("2");
            tCStr = bundle.getString("3");
            tPVal = bundle.getString("4");
            aDVal = bundle.getString("5");
            statTitle = bundle.getString("6");

            //identifies textviews
            TextView val1 = (TextView) rootView.findViewById(R.id.iCVal);
            TextView val2 = (TextView) rootView.findViewById(R.id.sIVal);
            TextView val3 = (TextView) rootView.findViewById(R.id.lIVal);
            TextView val4 = (TextView) rootView.findViewById(R.id.tCVal);
            TextView val5 = (TextView) rootView.findViewById(R.id.tPVal);
            TextView val6 = (TextView) rootView.findViewById(R.id.aDVal);
            TextView val7 = (TextView) rootView.findViewById(R.id.statTitle);

            //sets text
            val1.setText(iCStr);
            val2.setText(sIStr);
            val3.setText(lIStr);
            val4.setText(tCStr);
            val5.setText(tPVal);
            val6.setText(aDVal);
            val7.setText(statTitle);

        }

        return rootView;
    }

}
