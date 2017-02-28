package ikpmd.com.studentprogress.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ikpmd.com.studentprogress.Adapters.CourseViewAdapter;
import ikpmd.com.studentprogress.Adapters.ResultsViewAdapter;
import ikpmd.com.studentprogress.Helpers.DatabaseHelper;
import ikpmd.com.studentprogress.Helpers.DatabaseInfo;
import ikpmd.com.studentprogress.Helpers.GsonRequest;
import ikpmd.com.studentprogress.Helpers.VolleyHelper;
import ikpmd.com.studentprogress.Models.CourseModel;
import ikpmd.com.studentprogress.R;


public class MainResultFragment extends Fragment {
    private static final String ENDPOINT = "http://10.0.2.2/ikpmd/api/showCourses"; //localhost
    private View view;
    private DatabaseHelper dbHelper;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CourseModel> dataset = new ArrayList<CourseModel>();

    public MainResultFragment() {
    }

    public static MainResultFragment newInstance() {
        MainResultFragment fragment = new MainResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getHelper(this.getContext());
        fetchResults();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_result, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_results);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());

        // specify an adapter
        mAdapter = new ResultsViewAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();

        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    private void fetchResults() {
        Type type = new TypeToken<List<CourseModel>>(){}.getType();

        GsonRequest<List<CourseModel>> request = new GsonRequest<List<CourseModel>>(ENDPOINT,
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

        VolleyHelper.getInstance(this.getContext()).addToRequestQueue(request);
    }
    private void processRequestSuccess(List<CourseModel> subjects ){

        // putting all received classes in my database.
        for (CourseModel cm : subjects) {
            ContentValues cv = new ContentValues();
            cv.put(DatabaseInfo.CourseColumn.ID, cm.id);
            cv.put(DatabaseInfo.CourseColumn.NAME, cm.name);
            cv.put(DatabaseInfo.CourseColumn.GRADE, cm.grade);
            cv.put(DatabaseInfo.CourseColumn.ECTS, cm.ects);
            cv.put(DatabaseInfo.CourseColumn.MANDATORY , cm.mandatory);
            cv.put(DatabaseInfo.CourseColumn.TERM , cm.term);
            dbHelper.insert(DatabaseInfo.CourseTables.COURSE, null, cv);
        }

        //Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.COURSE, new String[]{"*"}, "grade", new String[]{"not null"}, null, null, null);
        Cursor rs = dbHelper.rawQuery("SELECT id, name, ects, term, mandatory, grade FROM Course WHERE grade IS NOT NULL;", null);

        try {
            while (rs.moveToNext()) {
                int idIndex = rs.getColumnIndex("id");
                int nameIndex = rs.getColumnIndex("name");
                int gradeIndex = rs.getColumnIndex("grade");
                int ectsIndex = rs.getColumnIndex("ects");
                int mandatoryIndex = rs.getColumnIndex("mandatory");
                int termIndex = rs.getColumnIndex("term");

                int id = rs.getInt(idIndex);
                String name = rs.getString(nameIndex);
                String grade = rs.getString(gradeIndex);
                int ects = rs.getInt(ectsIndex);
                boolean mandatory = rs.getInt(mandatoryIndex) > 0;
                int term = rs.getInt(termIndex);

                dataset.add(new CourseModel(id, name, ects, term, mandatory, grade));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rs.close();
        }
        mAdapter = new ResultsViewAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void processRequestError(VolleyError error){
        Snackbar.make(view, "Geen resultaten!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fetchResults();
                    }
                })
                .show();
        Log.e("MainResultsFragment", error.toString());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
