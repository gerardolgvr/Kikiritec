package mx.com.collegedays.collegedays.Activities;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;
import mx.com.collegedays.collegedays.Fragments.NotasFragment;
import mx.com.collegedays.collegedays.Models.Clase;
import mx.com.collegedays.collegedays.R;


import java.util.Calendar;

public class RegistroClase extends AppCompatActivity implements View.OnClickListener {
    private boolean esNuevo;
    private String  dia;
    private TextView lblDia;
    private Button btnCrear;
    private EditText txtClase;
    private EditText txtProfesor;
    private EditText txtAula;
    private TextView txtHorasDeClase;
    private TextView txtHora;
    private Button btnSetHora;

    //Para persistencia de datos
    private Realm realm;
    RealmResults<Clase> clases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_clase);


        setUI();
        // Tomamos los datos del intent
        Bundle data = getIntent().getExtras();

        this.dia = data.getString("dia");
        this.esNuevo = data.getBoolean("esNuevo");



        lblDia.setText( dia );
        btnCrear.setText( (esNuevo)? "Registrar clase" : "Actualizar datos" );

        setBtnSetHoraOnClickListenner();
        setToolbar();

        Toast.makeText(RegistroClase.this, dia, Toast.LENGTH_LONG).show();


    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );

        setSupportActionBar( toolbar );

        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }



    private void setBtnSetHoraOnClickListenner(){

        btnSetHora.setOnClickListener( this );
    }

    public void setUI(){
        lblDia = (TextView) findViewById( R.id.lblDia);
        txtClase = (EditText) findViewById( R.id.txtNombreClase);
        txtProfesor = (EditText) findViewById( R.id.txtProfesor);
        txtAula = (EditText) findViewById( R.id.txtAula );
        txtHorasDeClase = (EditText) findViewById( R.id.txtHoras );
        btnSetHora = (Button) findViewById( R.id.btnSetHora );
        txtHora = (TextView) findViewById( R.id.txtHora );
        btnCrear = (Button) findViewById( R.id.btnCrear );

        btnCrear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if( esNuevo ){
                            createNewClase();
                        }
                        else{
                            //COMPLETAR CON CÓDIGO
                        }
                    }
                }
        );
    }
    public void createNewClase(){
        realm.beginTransaction();
        Clase clase = new Clase(txtClase.getText().toString(),txtProfesor.getText().toString(),
                txtAula.getText().toString(),txtHorasDeClase.getText().toString(),
                txtHorasDeClase.getText().toString(),dia);

        realm.copyToRealm( clase );
        realm.commitTransaction();
        //NotasFragment.updateFragmentNotas();
    }

    @Override
    public void onClick(View v) {
        //Obtenemos instancia de Calendar
        Calendar horaActual = Calendar.getInstance();
        // Obtenemos hora
        int hora = horaActual.get( Calendar.HOUR_OF_DAY );
        int minuto = horaActual.get( Calendar.MINUTE );

        TimePickerDialog d;
         d = new TimePickerDialog(this,
                 new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                         String hora = String.format("%02d:%02d", hourOfDay, minute) ;

                         txtHora.setText( hora );
                     }
                 }
                 , hora, minuto, false);

        d.setTitle( "Hora de la clase");
        d.show();
    }
}
