package mx.com.collegedays.collegedays.Models;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Nota{
	
	private String tituloDeNota;
	private String contenido;
	private String fechaCreacion;
	private String horaCreacion;

	public Nota(String tituloDeNota, String contenido){
		this.tituloDeNota = tituloDeNota;
		this.contenido = contenido;

		Calendar calendario = new GregorianCalendar();
		this.horaCreacion = calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE);
		this.fechaCreacion = calendario.get(Calendar.DATE) + "/" + (calendario.get(Calendar.MONTH) + 1) + "/" + calendario.get(Calendar.YEAR);
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

