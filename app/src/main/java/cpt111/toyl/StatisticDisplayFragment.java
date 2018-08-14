package cpt111.toyl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatisticDisplayFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistic_display, container, false);

        //gets bundle
        //Bundle bundle = getArguments();

//temporarily creating bundle
        //gets Text from editTexts
        String first = "text1";
        String last = "text2";

        //creates bundle to pass to next fragment
        Bundle bundle = new Bundle();
        bundle.putString("FirstName",first);
        bundle.putString("LastName",last);
//end of bundle creation see Visualisation fragment

        //extracts bundle content to strings
        String firstName = bundle.getString("FirstName");
        String lastName = bundle.getString("LastName");

        //identifies textviews
        TextView firstText = (TextView) rootView.findViewById(R.id.iCVal);
        TextView lastText = (TextView) rootView.findViewById(R.id.sIVal);

        //sets text
        firstText.setText(firstName);
        lastText.setText(lastName);

        return rootView;
    }
}
