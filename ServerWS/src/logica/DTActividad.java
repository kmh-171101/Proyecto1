package logica;

import java.time.LocalDate;
//import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class DTActividad {
	private String nombre;
	private String descripcion;
	private String enlace;
	private int duracion;
	private int costoPT;
	private String ciudad;
	private LocalDate fechaAlta;
	private int cantFavoritos = 0;
	private logica.ActividadAT.Estado estadoActual;
	private String[] categoria;
	@SuppressWarnings("unused")
	private Map<String, DTSalidasAT> dtSalidas = new HashMap<String, DTSalidasAT>();
	
	public DTActividad(String nombre, String descripcion, String enlace, int duracion, int costoPT, String ciudad, LocalDate fechaAlta, logica.ActividadAT.Estado estadoActual2, int cantFavoritos, String[] cat) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setEnlace(enlace);
		this.setCiudad(ciudad);
		this.setCostoPT(costoPT);
		this.setDuracion(duracion);
		this.setFechaAlta(fechaAlta);
		this.estadoActual = estadoActual2;
		this.cantFavoritos = cantFavoritos;
		this.categoria = cat;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCostoPT() {
		return costoPT;
	}

	public void setCostoPT(int costoPT) {
		this.costoPT = costoPT;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	@Override
	public String toString() {
		return "Nombre Actividad =" + nombre + "\n Descripcion=" + descripcion + "\n Duracion=" + duracion
				+ "\n CostoPT=" + costoPT + "\n Ciudad=" + ciudad + "\n FechaAlta=" + fechaAlta + "\n Estado Actual=" + estadoActual;
	}


	public logica.ActividadAT.Estado getEstadoActual() {
		return estadoActual;
	}


	public String[] getCategoria() {
		return categoria;
	}


	public int getCantFavoritos() {
		return cantFavoritos;
	}


	public void setCantFavoritos(int cantFavoritos) {
		this.cantFavoritos = cantFavoritos;
	}
	
	
}