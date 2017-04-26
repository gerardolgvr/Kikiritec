package mx.com.collegedays.collegedays.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;


import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import mx.com.collegedays.collegedays.Activities.RegistroClase;
import mx.com.collegedays.collegedays.Adapters.ClaseAdapter;
import mx.com.collegedays.collegedays.Models.Clase;
import mx.com.collegedays.collegedays.R;



public class DFragment extends Fragment implements RealmChangeListener<RealmResults<Clase>>, AdapterView.OnItemClickListener{

    private static Realm realm;

    private static TextView dia;
    private ListView listView;
    private static ClaseAdapter adapter;

    static RealmResults<Clase> clases;


    public DFragment() {
        // Required empty public construdctor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d, container, false);

        dia = (TextView) view.findViewById(R.id.textViewDomingo);

        //Db
        realm = Realm.getDefaultInstance();
        clases = realm.where(Clase.class).equalTo("dia", "DOMINGO").findAll();

        //Creamos nuestro adaptador personalizado
        adapter = new ClaseAdapter(getActivity(), clases, R.layout.list_view_clase_item);
        //Creamos listView
        listView = (ListView) view.findViewById(R.id.listViewDomingo);
        //Asociamos adaptador al listView
        listView.setAdapter(adapter);

        //escuchador de eventos cuando hacemos click a un item del list view (Por terminar, parte del editar o solo ver)
        listView.setOnItemClickListener(this);
        registerForContextMenu(listView);
        updateFragmentDomingo();
        return view;
    }

    @Override
    public void onChange(RealmResults<Clase> element) {
        adapter.notifyDataSetChanged();
    }

    public static void updateFragmentDomingo(){
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }

        //Db
        realm = Realm.getDefaultInstance();
        //consulta que devuelve todas las clases de la bd
        clases  = realm.where(Clase.class).findAll();
        //cuestion del textview que dice notas
        if(clases.size() > 0){
            //si hay notas guardadas lo hacemos invisible
            dia.setVisibility(View.INVISIBLE);
        } else {
            //de lo contrario seguimos mostrando el textview con el mensaje
            dia.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), RegistroClase.class);
        intent.putExtra("esNuevo", false);
        intent.putExtra("dia", dia.getText().toString());
        intent.putExtra("id", clases.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, R.id.editClass, Menu.NONE, "Editar Clase");
        menu.add(Menu.NONE, R.id.deleteClass, Menu.NONE, "Borrar Clase");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.deleteClass:
                deleteClase(clases.get(info.position));
                Toast.makeText(getActivity(), "Clase Borrrada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.editClass:
                Intent intent = new Intent(getActivity(), RegistroClase.class);
                intent.putExtra("esNuevo", false);
                intent.putExtra("dia", dia.getText().toString());
                intent.putExtra("id", clases.get(info.position).getId());
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteClase(Clase clase){
        realm.beginTransaction();
        clase.deleteFromRealm();
        realm.commitTransaction();
        updateFragmentDomingo();
    }


}
