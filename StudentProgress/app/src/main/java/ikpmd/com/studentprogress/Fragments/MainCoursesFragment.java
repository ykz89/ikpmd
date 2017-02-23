package ikpmd.com.studentprogress.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ikpmd.com.studentprogress.R;

public class MainCoursesFragment extends Fragment implements View.OnClickListener{
    private Button viewCoursesButton, viewResultsButton;

    public MainCoursesFragment() {
    }

    public static MainCoursesFragment newInstance(String param1, String param2) {
        MainCoursesFragment fragment = new MainCoursesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_results, container, false);

        viewCoursesButton = (Button) view.findViewById(R.id.viewCoursesButton);
        viewResultsButton = (Button) view.findViewById(R.id.viewResultsButton);

        viewCoursesButton.setOnClickListener(this);
        viewResultsButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view){
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.viewCoursesButton:
                fragment = new ListCoursesFragment();
                replaceFragment(fragment);
                break;

            case R.id.viewResultsButton:
                fragment = new ListCoursesFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
