package ikpmd.com.studentprogress.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import ikpmd.com.studentprogress.Helpers.DatabaseHelper;
import ikpmd.com.studentprogress.Helpers.GsonRequest;
import ikpmd.com.studentprogress.Helpers.VolleyHelper;
import ikpmd.com.studentprogress.Models.CourseModel;
import ikpmd.com.studentprogress.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddResultFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String ENDPOINT = "http://10.0.2.2/ikpmd/api/updateGrade?";
    private DatabaseHelper dbHelper;
    private Spinner spinner;
    private Button addResultButton, cancelButton;
    private String course = null;
    private TextView resultTextView;
    private View view;

    public AddResultFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddResultFragment.
     */
    public static AddResultFragment newInstance() {
        AddResultFragment fragment = new AddResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        dbHelper = DatabaseHelper.getHelper(this.getContext());
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_result, container, false);
        spinner = (Spinner) view.findViewById(R.id.course_spinner);
        addResultButton = (Button) view.findViewById(R.id.add_result_button);
        cancelButton = (Button) view.findViewById(R.id.cancel_action);
        resultTextView = (TextView) view.findViewById(R.id.input_result);
        addResultButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
        loadSpinnerData();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Adding result
     */
    private void addResult( String course, String result) {
        Type type = new TypeToken<List<CourseModel>>(){}.getType();

        String url = ENDPOINT + "grade=" + result + "&name=" + course;

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

        VolleyHelper.getInstance(this.getContext()).addToRequestQueue(request);
    }

    private void processRequestSuccess(List<CourseModel> response) {
        InputMethodManager imm = (InputMethodManager)
        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                resultTextView.getWindowToken(), 0);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, new MainResultFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void processRequestError(VolleyError error){
        Log.e("addResultFragment", error.toString());
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    public void loadSpinnerData() {

        List<String> courses = dbHelper.getCoursesWithoutResult();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, courses);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        course = spinner.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.add_result_button:
                String result = resultTextView.getText().toString();
                if(result.isEmpty())
                {
                    Toast.makeText(getContext(), "Vul resultaat in.", Toast.LENGTH_LONG).show();
                    break;
                }else{
                    addResult(course, result);
                    Snackbar.make(view, "Cijfer toegevoegd", Snackbar.LENGTH_LONG)
                        .show();
                    break;
                }
            case R.id.cancel_action:
                getFragmentManager().popBackStack();
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
    }
}
