package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class DTSalidasAT {
	private String nombre;
	private int cantMaxT;
	private LocalDate fechaAlta;
	private LocalDate fecha;
	private LocalTime hora;
	private String lugar;
	private int cantTotalTuristas;
	
	public DTSalidasAT(String nombre, int cantmaxt, LocalDate fechaalta, LocalDate fecha, LocalTime hora, String lugar, int canttotalturistas) {
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
	
	public int getCantMaxT() {
		return cantMaxT;
	}
	
	public String getFechaAlta() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String fecha = fechaAlta.format(formatter);
		return fecha;
	}
	public String getFecha() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String fecha = this.fecha.format(formatter);
		return fecha;
	}
	public String getHora() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm:ss");
		String hora = this.hora.format(dtf);
		return hora;
	}
	
	public int getCantTotalTuristas() {
		return cantTotalTuristas;
	}
	
	public String getLugar() {
		return lugar;
	}
	@Override
	public String toString() {
		return "Salida Turistica \n Nombre=" + nombre + "\n Cantidad Maxima Turistas=" + cantMaxT + "\n Fecha De Alta=" + fechaAlta + "\n Fecha A Realizarse=["
				+ fecha + "," + hora + "] \n Lugar=" + lugar + "\n Cantidad Total De Turistas=" + cantTotalTuristas;
	}
	public LocalDate getFechaFech() {
		return fecha;
	}
	
	public LocalTime getHoraHora() {
		return hora;
	}
	public LocalDate getFechaAltaFech() {
		return fechaAlta;
	}
	
	
}


