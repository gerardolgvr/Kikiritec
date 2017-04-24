package mx.com.collegedays.collegedays.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import mx.com.collegedays.collegedays.App.App;

/**
 * Created by augusto on 23/04/17.
 */

public class Clase extends RealmObject{

    @PrimaryKey
    private int id;
    @Required
    private String nombreDeClase;
    private String profesor;
    private String aula;
    @Required
    private String duracion;
    @Required
    private String horaClase;
    private String dia;



    public Clase(){
        //Required for Realm
    }

    public Clase(String nombreDeClase, String profesor, String aula, String duracion, String horaClase, String dia){
        this.id = App.ClaseID.incrementAndGet();
        this.nombreDeClase = nombreDeClase;
        this.profesor = profesor;
        this.aula = aula;
        this.duracion = duracion;
        this.horaClase = horaClase;
        this.dia = dia;
    }

    public String getNombreDeClase() {
        return nombreDeClase;
    }

    public void setNombreDeClase(String nombreDeClase) {
        this.nombreDeClase = nombreDeClase;
    }

    public String getHoraClase() {
        return horaClase;
    }

    public void setHoraClase(String horaInicio) {
        this.horaClase = horaInicio;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
