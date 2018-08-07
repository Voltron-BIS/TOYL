package cpt111.toyl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.time.ZoneId;
import java.util.Arrays;

public class OverlaysSelectFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    //private OnListFragmentInteractionListener mListener;
    // Variable declarations.
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataSet;

    public OverlaysSelectFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static OverlaysSelectFragment newInstance(int columnCount) {
        OverlaysSelectFragment fragment = new OverlaysSelectFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.overlays_select_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        super.onCreateOptionsMenu(menu, inflater);
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overlaysselectfragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.overlays_recycler_view);

        // Improve performance by marking it as a fixed size
        mRecyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        // Get the list of time zones
        mDataSet = ZoneId.getAvailableZoneIds().toArray(new String[0]);
        Arrays.sort(mDataSet);

        OverlaysSelectItemClickListener listener = new OverlaysSelectItemClickListener() {

            public void onClick(View view, int position) {
                // Get the selected item
                //RelativeLayout item = (RelativeLayout) view.findViewById(R.id.zone_item);
                RecyclerView parent = (RecyclerView) view.getParent();
                RecyclerView.ViewHolder holder = parent.findViewHolderForAdapterPosition(position);
                RelativeLayout item = (RelativeLayout) holder.itemView.findViewById(R.id.zone_item);
                ColorDrawable bg = (ColorDrawable) item.getBackground();

                // if the background color is green then remove it
                if(bg != null && bg.getColor() == Color.GREEN) {
                    item.setBackgroundColor(Color.WHITE);
                }
                else if(bg != null && bg.getColor() == Color.WHITE){
                    item.setBackgroundColor(Color.GREEN);
                }
                else if(bg == null) {
                    item.setBackgroundColor(Color.GREEN);
                }
            }
        };

        // Specify an adapter
        mAdapter = new OverlaysSelectFragmentRecyclerViewAdapter(mDataSet, getActivity(),listener);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String item);
    }
    */
}
