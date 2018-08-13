package cpt111.toyl.Tasks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cpt111.toyl.R;
import cpt111.toyl.Tasks.Model.Tasks;

public class TaskTrackerRecyclerViewAdapter extends RecyclerView.Adapter<TaskTrackerRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Tasks> values;

    public TaskTrackerRecyclerViewAdapter(ArrayList<Tasks> myDataset) {
        values = myDataset;
    }

    public void add(int position, Tasks item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.fragment_task_tracker_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Tasks task = values.get(position);
        holder.txtName.setText(task.getName());
        holder.txtDescription.setText(task.getDescription());
        holder.txtName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtDescription;
        public View layout;
        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtName = (TextView) v.findViewById(R.id.task_name);
            txtDescription = (TextView) v.findViewById(R.id.task_description);
        }
    }
}