package cpt111.toyl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;


public class TimeSchedulerFragmentRecyclerViewAdapter extends RecyclerView.Adapter<TimeSchedulerFragmentRecyclerViewAdapter.ViewHolder> {

    //Variables
    //-----------------------------------------------
    private String[] mTime;
    private String[] mName;
    private String[] mCategory;
    private String[] mDescription;
    private Context mContext;

    //Constructor
    //-----------------------------------------------
    public TimeSchedulerFragmentRecyclerViewAdapter(Context mContext,String[] mTime, String[] mName,String[] mCategory, String[] mDescription){
        this.mTime = mTime;
        this.mName = mName;
        this.mCategory = mCategory;
        this.mDescription = mDescription;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_time_scheduler_timeline_item, parent, false);
        return new ViewHolder(view);
    }

    //Attach vars to View
    //-----------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mtaskname.setText(mName[position]);
        holder.mt_s_time.setText(mTime[position]);

    }

    @Override
    public int getItemCount() {
        return mTime.length;
    }

    //View holder for Recyclerview
    //-----------------------------------------------
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mtaskname;
        TextView mt_s_time;

        public ViewHolder(View itemView) {
            super(itemView);
            mt_s_time = itemView.findViewById(R.id.t_s_time);
            mtaskname = itemView.findViewById(R.id.t_s_r_taskname);
        }
    }



}
