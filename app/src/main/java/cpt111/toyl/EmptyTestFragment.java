package cpt111.toyl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EmptyTestFragment extends Fragment {

    public EmptyTestFragment() {
        //constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty_test, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
}
