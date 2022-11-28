package logica;

import java.time.LocalDate;

public class DTPaqueteAT {
	private String nombre;
	private String descripcion;
	private int validez;
	private int descuento;
	private int costoPorPersona;
	private LocalDate fechaAlta;
	
	public DTPaqueteAT(String nombre, String descripcion, int validez, int descuento, int costoporpersona, LocalDate fechaAlta) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setValidez(validez);
		this.setDescuento(descuento);
		this.setCostoPorPersona(costoporpersona);
		this.setFechaAlta(fechaAlta);
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

	public int getValidez() {
		return validez;
	}

	public void setValidez(int validez) {
		this.validez = validez;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public int getCostoPorPersona() {
		return costoPorPersona;
	}

	public void setCostoPorPersona(int costoporpersona) {
		costoPorPersona = costoporpersona;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
}
