package cpt111.toyl;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OverlaysSelectFragmentRecyclerViewAdapter extends RecyclerView.Adapter<OverlaysSelectFragmentRecyclerViewAdapter.ViewHolder>{
    // Variable declaration.
    private String[] mDataSet;

    // Provide a reference to the views for each item
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mZoneIdView;
        public TextView mZoneNowView;
        public ViewHolder(View v) {
            super(v);
            this.mZoneIdView = (TextView) v.findViewById(R.id.zone_id_text_view);
            this.mZoneNowView = (TextView) v.findViewById(R.id.zone_id_now);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the selected item
            RelativeLayout item = (RelativeLayout) v.findViewById(R.id.zone_item);
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
    }

    // Constructor
    public OverlaysSelectFragmentRecyclerViewAdapter(String[] mDataSet) {
        this.mDataSet = mDataSet;
    }

    // Create new views invoked by the layout manager
    @Override
    public OverlaysSelectFragmentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        // Create a new view
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_overlaysselectfragment_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the item from the dataset at the selected index
        // Replace the view content with the new content
        Instant timeStamp = Instant.now();
        ZonedDateTime zonedDateTime = timeStamp.atZone(ZoneId.of(mDataSet[position]));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss");
        String time = zonedDateTime.format(format);
        holder.mZoneIdView.setText(mDataSet[position]);
        holder.mZoneNowView.setText(time);
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}