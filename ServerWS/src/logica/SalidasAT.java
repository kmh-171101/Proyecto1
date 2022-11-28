package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SalidasAT {
	private String nombre;
	private int cantMaxT;
	private LocalDate fechaAlta;
	private LocalDate fecha;
	private LocalTime hora;
	private String lugar;
	private int cantTotalTuristas;
	private String urlImage = "";
	
	private List<Inscripcion> listInscripciones = new ArrayList<Inscripcion>();
	
	public SalidasAT(String nombre, int cantmaxt, LocalDate fechaalta, LocalDate fecha, LocalTime hora, String lugar, int canttotalturistas) {
		this.nombre = nombre;
		this.cantMaxT = cantmaxt;
		this.fechaAlta = fechaalta;
		this.fecha = fecha;
		this.hora = hora;
		this.lugar = lugar;
		this.cantTotalTuristas = canttotalturistas;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	
	public int getCantMax() {
		return cantMaxT;
	}
	public int getCantTotalTuristas() {
		return cantTotalTuristas;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setCantTotalTuristas(int cantAct) {
		cantTotalTuristas = cantAct;
	}
	
	public DTSalidasAT getDatosSalida() {
		DTSalidasAT salDT = new DTSalidasAT(nombre, cantMaxT, fechaAlta, fecha, hora, lugar, cantTotalTuristas);
		return salDT;
	}

	public void agregarInscripcion(Inscripcion nueva) {
		listInscripciones.add(nueva);
		cantTotalTuristas = cantTotalTuristas + nueva.getCantTuristas();
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	

	
}
