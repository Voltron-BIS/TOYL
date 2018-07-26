package cpt111.toyl.Timer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cpt111.toyl.R;

public class TimerAddFragment extends Fragment {

    private String timerName;

    public TimerAddFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mContainer = inflater.inflate(R.layout.fragment_timer_add, container, false);
        TextView textView = (TextView) mContainer.findViewById(R.id.add_timer_text);
        textView.setText("This is the add timer fragment");
//
//        View mContainer = inflater.inflate(R.layout.fragment_timer_view, container, false);
//        TextView textView = (TextView) mContainer.findViewById(R.id.timer_view_text);
//        textView.setText(getArguments().getString("Name"));

        return mContainer;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
}
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View mContainer = inflater.inflate(R.layout.fragment_timer_add, container, false);
//        TextView textView = (TextView) mContainer.findViewById(R.id.add_timer_text);
//        textView.setText("This is the add timer fragment");
//
//        // Inflate the layout for this fragment
//        return mContainer;
//    }
//
//}
//
