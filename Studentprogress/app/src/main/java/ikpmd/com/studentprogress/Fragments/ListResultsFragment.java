package ikpmd.com.studentprogress.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import ikpmd.com.studentprogress.Helpers.DatabaseHelper;
import ikpmd.com.studentprogress.Helpers.DatabaseInfo;
import ikpmd.com.studentprogress.Helpers.GsonRequest;
import ikpmd.com.studentprogress.Helpers.VolleyHelper;
import ikpmd.com.studentprogress.Models.CourseModel;
import ikpmd.com.studentprogress.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListResultsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListResultsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//    private static final String ENDPOINT = "http://localhost/ikpmd/api/showCourses";
    private static final String ENDPOINT = "http://10.0.2.2/ikpmd/api/showCourses";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private DatabaseHelper dbHelper;

    public ListResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListResultsFragment newInstance(String param1, String param2) {
        ListResultsFragment fragment = new ListResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getHelper(this.getContext());
        fetchCourses();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_results, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void fetchCourses() {
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
            cv.put(DatabaseInfo.CourseColumn.NAME, cm.name);
            cv.put(DatabaseInfo.CourseColumn.GRADE, cm.grade);
            cv.put(DatabaseInfo.CourseColumn.ECTS, cm.ects);
            cv.put(DatabaseInfo.CourseColumn.MANDATORY , cm.mandatory);
            cv.put(DatabaseInfo.CourseColumn.TERM , cm.term);
            dbHelper.insert(DatabaseInfo.CourseTables.COURSE, null, cv);
        }

        Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.COURSE, new String[]{"*"}, null, null, null, null, null);
        rs.moveToFirst();
        DatabaseUtils.dumpCursor(rs);

    }

    private void processRequestError(VolleyError error){
        Log.e("ViewResultFragment", error.toString());
    }


}
