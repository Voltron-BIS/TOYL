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
        String firstName;
        String lastName;

        if (getArguments() != null) {
            //gets bundle
            Bundle bundle = getArguments();

            //extracts bundle content to strings
            firstName = bundle.getString("FirstName");
            lastName = bundle.getString("LastName");

            //identifies textviews
            TextView firstText = (TextView) rootView.findViewById(R.id.iCVal);
            TextView lastText = (TextView) rootView.findViewById(R.id.sIVal);

            //sets text
            firstText.setText(firstName);
            lastText.setText(lastName);

        }
        else{
            displayToast("no args");
        }

        return rootView;
    }

    public void displayToast(String text) {
        Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

}
