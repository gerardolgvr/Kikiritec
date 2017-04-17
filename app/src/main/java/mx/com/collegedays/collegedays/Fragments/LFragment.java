package mx.com.collegedays.collegedays.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.com.collegedays.collegedays.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LFragment extends Fragment {
    private String day;
    private TextView txtDay;

    public LFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_l, container, false);

        txtDay = (TextView) view.findViewById(R.id.textView);
        //txtDay.setText(day);
        return view;
    }

}
