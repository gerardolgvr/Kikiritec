package mx.com.collegedays.collegedays.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.com.collegedays.collegedays.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotasFragment extends Fragment {


    public NotasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notas, container, false);
        return view;
    }

}
