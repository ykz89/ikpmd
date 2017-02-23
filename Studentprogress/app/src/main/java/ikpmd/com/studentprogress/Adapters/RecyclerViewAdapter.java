package ikpmd.com.studentprogress.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ikpmd.com.studentprogress.Models.CourseModel;
import ikpmd.com.studentprogress.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<CourseModel> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nameTextView, ectsTextView, termTextView, mandatoryTextView;

        public ViewHolder(View v) {
            super(v);
            nameTextView = (TextView) itemView.findViewById(R.id.course_name);
            ectsTextView = (TextView) itemView.findViewById(R.id.course_ects);
            termTextView = (TextView) itemView.findViewById(R.id.course_term);
            mandatoryTextView = (TextView) itemView.findViewById(R.id.course_mandatory);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(ArrayList<CourseModel> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View courseView = inflater.inflate(R.layout.item_course, parent, false);

        ViewHolder vh = new ViewHolder(courseView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nameTextView.setText(mDataset.get(position).name);
        holder.ectsTextView.setText(mDataset.get(position).ects + "");
        holder.termTextView.setText(mDataset.get(position).term + "");
        String mandatoryText = (mDataset.get(position).mandatory)?"Verplicht":"Keuzevak";
        holder.mandatoryTextView.setText(mandatoryText);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
