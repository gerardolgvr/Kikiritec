package mx.com.collegedays.collegedays.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import io.realm.Realm;
import io.realm.RealmResults;
import mx.com.collegedays.collegedays.Fragments.NotasFragment;
import mx.com.collegedays.collegedays.Models.Nota;
import mx.com.collegedays.collegedays.R;


public class NotasActivity extends AppCompatActivity {

    private Realm realm;
    RealmResults<Nota> notas;


    private EditText txtTitle;
    private EditText txtNote;
    private Button btnAddNote;

    private int notaID;
    private Nota nota;
    boolean editada = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        //Db
        realm = Realm.getDefaultInstance();
        notas = realm.where(Nota.class).findAll();


        setToolbar();
        setUI();

        if(getIntent().getExtras() != null){
            notaID = getIntent().getExtras().getInt("id");
            nota = realm.where(Nota.class).equalTo("id", notaID).findFirst();
            setData(nota);
            editada = true;
        }

    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setUI(){
        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtNote = (EditText) findViewById(R.id.txtNote);
        btnAddNote = (Button) findViewById(R.id.btnAddNote);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notaValida()){
                    if(editada){
                        editingNota(nota);
                        Toast.makeText(NotasActivity.this, "Editada", Toast.LENGTH_SHORT).show();
                    } else {
                        createNewNota();
                        Toast.makeText(NotasActivity.this, "Nota Guardada ", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                } else {
                    Toast.makeText(NotasActivity.this, "Agrega un título", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean notaValida(){
        String titulo;
        titulo = txtTitle.getText().toString();
        if(titulo.matches("")){
            return false;
        } else {
            return true;
        }
    }

    //** CRUD Actions **//
    public void createNewNota(){
        realm.beginTransaction();
        Nota nota = new Nota(txtTitle.getText().toString(), txtNote.getText().toString());
        realm.copyToRealm(nota);
        realm.commitTransaction();
        NotasFragment.updateFragmentNotas();
    }


    public void setData(Nota nota){
        txtTitle.setText(nota.getTituloDeNota());
        txtNote.setText(nota.getContenido());
    }

    public void editingNota(Nota nota){
        realm.beginTransaction();
        nota.setTituloDeNota(txtTitle.getText().toString());
        nota.setContenido(txtNote.getText().toString());
        realm.copyToRealmOrUpdate(nota);
        realm.commitTransaction();
        NotasFragment.updateFragmentNotas();
    }
}
