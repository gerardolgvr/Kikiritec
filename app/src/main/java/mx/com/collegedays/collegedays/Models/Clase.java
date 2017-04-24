package mx.com.collegedays.collegedays.Models;

/**
 * Created by augusto on 23/04/17.
 */

public class Clase {

    private String nombreDeClase;
    private String horaInicio;
    private String profesor;
    private String aula;
    private String dia;


    public Clase(){

    }

    public String getNombreDeClase() {
        return nombreDeClase;
    }

    public void setNombreDeClase(String nombreDeClase) {
        this.nombreDeClase = nombreDeClase;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
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
}
