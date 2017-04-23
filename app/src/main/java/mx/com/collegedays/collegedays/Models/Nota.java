package mx.com.collegedays.collegedays.Models;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import mx.com.collegedays.collegedays.App.App;

public class Nota extends RealmObject{
	@PrimaryKey
	private int id;
	@Required
	private String tituloDeNota;
	@Required
	private String contenido;
	private String fechaCreacion;
	private String horaCreacion;

    public Nota(){
        //Required for Realm
    }

	public Nota(String tituloDeNota, String contenido){
        this.id = App.NotaID.incrementAndGet();
		this.tituloDeNota = tituloDeNota;
		this.contenido = contenido;

		Calendar calendario = new GregorianCalendar();
		this.horaCreacion = calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE);
		this.fechaCreacion = calendario.get(Calendar.DATE) + "/" + (calendario.get(Calendar.MONTH) + 1) + "/" + calendario.get(Calendar.YEAR);
	}
    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

	public void setTituloDeNota(String tituloDeNota) {
		this.tituloDeNota = tituloDeNota;
	}
	
	public String getTituloDeNota() {
		return this.tituloDeNota;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public String getContenido() {
		return this.contenido;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setHoraCreacion(String horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	public String getHoraCreacion() {
		return this.horaCreacion;
	}
}

