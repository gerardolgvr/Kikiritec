package mx.com.collegedays.collegedays.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Calendar;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;
import mx.com.collegedays.collegedays.Activities.RegistroClase;
import mx.com.collegedays.collegedays.Adapters.ClaseAdapter;
import mx.com.collegedays.collegedays.Models.Clase;
import mx.com.collegedays.collegedays.R;

public class ClasesFragment extends Fragment implements RealmChangeListener<RealmResults<Clase>>, AdapterView.OnItemClickListener{

    private CalendarView calendarView;
    private ListView listView;
    private static ClaseAdapter adapter;
    private FloatingActionButton fab;
    private static String day;

    private static Realm realm;
    private static RealmResults<Clase> clases;
    private RealmResults<Clase> clases1;

    public ClasesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clases, container, false);

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        setDia(dayOfWeek);

        //Db
        realm = Realm.getDefaultInstance();
        clases = realm.where(Clase.class).equalTo("dia", day).findAll();
        clases = clases.sort("horaClase", Sort.ASCENDING);

        //UI Components
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_add_clase);
        //Creamos adaptador personalizado
        adapter = new ClaseAdapter( getActivity(), clases, R.layout.list_view_clase_item);
        //Creamos listView
        listView = (ListView) view.findViewById(R.id.listViewClases);
        //Asociamos adptador al listview
        listView.setAdapter(adapter);



        //listenners
        listView.setOnItemClickListener(this);
        //registerForContextMenu(listView);
        clases.addChangeListener(this);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                setDia(dayOfWeek);


                clases = realm.where(Clase.class).equalTo("dia", day).findAll();
                clases = clases.sort("horaClase", Sort.ASCENDING);

                adapter.setData(clases);

                //Toast.makeText(getActivity(), " "+day, Toast.LENGTH_SHORT).show(); //muestro el dia
                //Toast.makeText(getActivity(),"" +(long)clases.size(), Toast.LENGTH_SHORT).show();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistroClase.class );
                intent.putExtra("esNuevo", true);
                intent.putExtra("dia", day);
                startActivity( intent );
            }
        });

        return view;
    }

    public void setDia(int dayOfWeek){
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                day = "LUNES";
                break;
            case Calendar.TUESDAY:
                day = "MARTES";
                break;
            case Calendar.WEDNESDAY:
                day = "MIERCOLES";
                break;
            case Calendar.THURSDAY:
                day =  "JUEVES";
                break;
            case Calendar.FRIDAY:
                day = "VIERNES";
                break;
            case Calendar.SATURDAY:
                day = "SABADO";
                break;
            case Calendar.SUNDAY:
                day = "DOMINGO";
                break;
        }
    }

    public static void update() {

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        realm = Realm.getDefaultInstance();
        //consulta que devuelve todas las clases de la bd
        clases = realm.where(Clase.class).equalTo("dia", day).findAll();
        clases = clases.sort("horaClase", Sort.ASCENDING);

        Intent intent = new Intent(getActivity(), RegistroClase.class);
        intent.putExtra("esNuevo", false);
        intent.putExtra("dia", day.toUpperCase());
        intent.putExtra("id", clases.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onChange(RealmResults<Clase> elements) {
        adapter.setData(elements);
    }
}
