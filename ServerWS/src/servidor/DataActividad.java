package servidor;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataActividad {
	private String nombre;
	private String descripcion;
	private String enlace;
	private int duracion;
	private int costoPT;
	private String ciudad;
	private int anio;
	private int mes;
	private int dia;
	private int cantFavoritos;
	private logica.ActividadAT.Estado estadoActual;
	private ArrayList<String> categoria;
	
	public DataActividad() {
		
	}


	public DataActividad(String nombre, String descripcion, String enlace, int duracion, int costoPT, String ciudad, int anio, int mes, int dia, logica.ActividadAT.Estado estadoActual2, int cantFavoritos, String[] cat) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setEnlace(enlace);
		this.setCiudad(ciudad);
		this.setCostoPT(costoPT);
		this.setDuracion(duracion);
		this.setAnio(anio);
		this.setMes(mes);
		this.setDia(dia);
		this.estadoActual = estadoActual2;
		this.setCantFavoritos(cantFavoritos);
		ArrayList<String> este=new ArrayList<>();
		for(int i=0;i<cat.length;i++) {
			este.add(cat[i]);
		}
		this.categoria = este;
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

	public logica.ActividadAT.Estado getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(logica.ActividadAT.Estado estadoActual) {
		this.estadoActual = estadoActual;
	}

	public ArrayList<String> getCategoria() {
		return categoria;
	}

	public void setCategoria(ArrayList<String> categoria) {
		this.categoria = categoria;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}
	
	public int getCantFavoritos() {
		return cantFavoritos;
	}
	
	public void setCantFavoritos(int cant) {
		this.cantFavoritos = cant; 
	}
}
