package mx.com.collegedays.collegedays.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import mx.com.collegedays.collegedays.Models.Nota;
import mx.com.collegedays.collegedays.R;

import static mx.com.collegedays.collegedays.Activities.MainActivity.notas;

public class NotasActivity extends AppCompatActivity {

    private EditText txtTitle;
    private EditText txtNote;
    private Button btnAddNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        setToolbar();
        setUI();
    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setUI(){
        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtNote = (EditText) findViewById(R.id.txtNote);
        btnAddNote = (Button) findViewById(R.id.btnAddNote);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notaValida()){

                    Nota nota = new Nota(txtTitle.getText().toString(), txtNote.getText().toString());
                    notas.add(nota);
                    String hora = nota.getHoraCreacion();
                    Toast.makeText(NotasActivity.this, "Nota Guardada "+notas.size()+ " "+hora+ " "+nota.getFechaCreacion(), Toast.LENGTH_SHORT).show();
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
}
