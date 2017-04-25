package mx.com.collegedays.collegedays.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import mx.com.collegedays.collegedays.Adapters.ClaseAdapter;
import mx.com.collegedays.collegedays.Models.Clase;
import mx.com.collegedays.collegedays.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DFragment extends Fragment implements RealmChangeListener<RealmResults<Clase>>, AdapterView.OnItemClickListener {
    private static Realm realm;

    private static TextView noClases;
    private ListView listView;
    private static ClaseAdapter adapter;

    static RealmResults<Clase> clases;
    public DFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d, container, false);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onChange(RealmResults<Clase> element) {

    }
}
