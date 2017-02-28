package ikpmd.com.studentprogress.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ikpmd.com.studentprogress.Fragments.MainResultFragment;
import ikpmd.com.studentprogress.Helpers.DatabaseHelper;
import ikpmd.com.studentprogress.Helpers.GsonRequest;
import ikpmd.com.studentprogress.Helpers.VolleyHelper;
import ikpmd.com.studentprogress.MainActivity;
import ikpmd.com.studentprogress.Models.CourseModel;
import ikpmd.com.studentprogress.R;

public class ResultsViewAdapter extends RecyclerView.Adapter<ResultsViewAdapter.ViewHolder>{
    private static final String ENDPOINT = "http://10.0.2.2/ikpmd/api/updateGrade?";
    private ArrayList<CourseModel> mDataset;
    private Fragment mfragment;
    private DatabaseHelper dbHelper;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nameTextView, ectsTextView, termTextView, gradeTextView;
        public ImageButton deleteButton;

        public ViewHolder(View v) {
            super(v);
            nameTextView = (TextView) itemView.findViewById(R.id.course_name);
            ectsTextView = (TextView) itemView.findViewById(R.id.course_ects);
            termTextView = (TextView) itemView.findViewById(R.id.course_term);
            gradeTextView = (TextView) itemView.findViewById(R.id.course_grade);
            deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ResultsViewAdapter(ArrayList<CourseModel> myDataset, Fragment fragment) {
        mDataset = myDataset;
        mfragment = fragment;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ResultsViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        context = parent.getContext();
        dbHelper = DatabaseHelper.getHelper(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View courseView = inflater.inflate(R.layout.item_result, parent, false);

        ViewHolder vh = new ViewHolder(courseView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nameTextView.setText(mDataset.get(position).name);
        holder.ectsTextView.setText(mDataset.get(position).ects + "");
        holder.termTextView.setText(mDataset.get(position).term + "");
        holder.gradeTextView.setText(mDataset.get(position).grade + "");
        holder.deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(v.getContext())
                        .setMessage("Weet je zeker dat je dit resultaat wilt verwijderen?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeResult(mDataset.get(position).name);
                                notifyDataSetChanged();
                                MainResultFragment f = (MainResultFragment) mfragment ;
                                if (f != null) {
                                    f.update();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null).show();
            }
        });

        if(mDataset.get(position).grade.matches(".*\\d+.*")){
            if( Float.parseFloat(mDataset.get(position).grade.replace(",",".")) >= 5.5){
                holder.gradeTextView.setTextColor(Color.GREEN);
            }else{
                holder.gradeTextView.setTextColor(Color.RED);
            }
        }
        else if( mDataset.get(position).grade.toLowerCase().equals("v") || mDataset.get(position).grade.toLowerCase().equals("voldoende")){
            holder.gradeTextView.setTextColor(Color.GREEN);
        }
        else{
            holder.gradeTextView.setTextColor(Color.RED);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void removeResult( String course) {
        Type type = new TypeToken<List<CourseModel>>(){}.getType();

        String url = ENDPOINT + "grade=NULL" + "&name=" + course;

        GsonRequest<List<CourseModel>> request = new GsonRequest<List<CourseModel>>(url,
                type, null, new Response.Listener<List<CourseModel>>() {
            @Override
            public void onResponse(List<CourseModel> response) {
                processRequestSuccess(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                processRequestError(error);
            }
        });

        VolleyHelper.getInstance(context).addToRequestQueue(request);
    }

    private void processRequestSuccess(List<CourseModel> response) {
    }

    private void processRequestError(VolleyError error){
        Log.e("addResultFragment", error.toString());
    }

}
