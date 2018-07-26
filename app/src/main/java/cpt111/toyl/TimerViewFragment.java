package cpt111.toyl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimerViewFragment extends Fragment {

    private String timerName;

    public TimerViewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mContainer = inflater.inflate(R.layout.fragment_timer_view, container, false);
        TextView textView = (TextView) mContainer.findViewById(R.id.timer_view_text);
        textView.setText(getArguments().getString("Name"));

        return mContainer;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
}