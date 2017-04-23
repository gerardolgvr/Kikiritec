package mx.com.collegedays.collegedays.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import mx.com.collegedays.collegedays.Activities.MainActivity;
import mx.com.collegedays.collegedays.Activities.NotasActivity;
import mx.com.collegedays.collegedays.Adapters.NotaAdapter;
import mx.com.collegedays.collegedays.Models.Nota;
import mx.com.collegedays.collegedays.R;


public class NotasFragment extends Fragment implements RealmChangeListener<RealmResults<Nota>>, AdapterView.OnItemClickListener {

    private static Realm realm;

    private static TextView noNotas;
    private ListView listView;
    private static NotaAdapter adapter;

    static RealmResults<Nota> notas;

    public NotasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notas, container, false);

        noNotas = (TextView) view.findViewById(R.id.hayNotas);

        //Db
        realm = Realm.getDefaultInstance();
        notas = realm.where(Nota.class).findAll();

        //Creamos nuestro adaptador personalizado
        adapter = new NotaAdapter(getActivity(), notas, R.layout.list_view_nota_item);
        //Creamos nuestro listview
        listView = (ListView) view.findViewById(R.id.listViewNota);
        //asociamos adaptador con el listview
        listView.setAdapter(adapter);

        //escuchador de eventos cuando hacemos click a un item del list view (Por terminar, parte del editar o solo ver)
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onChange(RealmResults<Nota> element) {
        adapter.notifyDataSetChanged();
    }

    public static void updateFragmentNotas(){
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }

        //Db
        realm = Realm.getDefaultInstance();
        //consulta que devuelve todas las notas de la bd
        notas = realm.where(Nota.class).findAll();
        //cuestion del textview que dice notas
        if(notas.size() > 0){
            //si hay notas guardadas lo hacemos invisible
            noNotas.setVisibility(View.INVISIBLE);
        } else {
            //de lo contrario seguimos mostrando el textview con el mensaje
            noNotas.setVisibility(View.VISIBLE);
            noNotas.setText("No hay notas guardadas");
        }
    }


    //Cuando hacemos click en un item del listview podremos verlo y editar, por eso el intent
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), NotasActivity.class);
        intent.putExtra("id", notas.get(position).getId());
        startActivity(intent);
    }

    /*
    Menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(notas.get(info.position).getTituloDeNota());
        getMenuInflater().inflate(R.menu.context_menu_main_activity, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.deleteNote:
                return true;
            case R.id.editNote:
                deleteNota(notas.get(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }*/
}
