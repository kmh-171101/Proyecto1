package servidor;

import java.util.ArrayList;

public class DataGenericArray {
	private String nombre;
	private ArrayList<DataActividad> actividades=null;
	private ArrayList<DataSalida> salidas=null;
	
	public DataGenericArray() {
		
	}
	
	public DataGenericArray(String nombre,ArrayList<DataActividad> actividades,ArrayList<DataSalida> salidas ) {
		this.nombre=nombre;
		this.actividades=actividades;
		this.salidas=salidas;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<DataActividad> getActividades() {
		return actividades;
	}
	public void setActividades(ArrayList<DataActividad> actividades) {
		this.actividades = actividades;
	}
	public ArrayList<DataSalida> getSalidas() {
		return salidas;
	}
	public void setSalidas(ArrayList<DataSalida> salidas) {
		this.salidas = salidas;
	}
}
