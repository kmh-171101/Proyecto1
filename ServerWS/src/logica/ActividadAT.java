package logica;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.NoExistenSalidasEnActividadException;
import excepciones.NoExistenPaquetesEnLaActividadException;

public class ActividadAT {
	public enum Estado {
		Agregada, Confirmada, Rechazada, Finalizada
	}

	private String nombre;
	private String descripcion;
	private String enlace;
	private int duracion;
	private int costoPT;
	private String ciudad;
	private LocalDate fechaAlta;
	private Estado estadoActual = Estado.Agregada;
	private int cantFavoritos = 0;
	private String[] categoria;
	private String urlImagen = "";
	private Map<String, SalidasAT> mapSalidas = new HashMap<String, SalidasAT>();
	private Map<String, PaqueteAT> mapPaquetes = new HashMap<String, PaqueteAT>();
	
	
	public ActividadAT(String nombre, String descripcion, String enlace, int duracion, int costoPT, String ciudad, LocalDate fechaAlta, String[] nombreCat) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.ciudad = ciudad;
		this.costoPT = costoPT;
		this.duracion = duracion;
		this.fechaAlta = fechaAlta;
		this.categoria = nombreCat;
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
	public Map<String, SalidasAT> getSalidas() {
		return mapSalidas;
	}
	public void setSalidas(Map<String, SalidasAT> salidas) {
		mapSalidas = salidas;
	}
	
	public String[] listarCategoriasAct() {
		return categoria;
	}
	
	public void agregarCategoria(String cat) {
		String[] nuevo = new String[categoria.length + 1];
		for (int i = 0; i < categoria.length; i++) {
			nuevo[i] = categoria[i];
		}
		nuevo[categoria.length] = cat;
		categoria = nuevo;
	}
	
	public DTActividad getDatosActividad() {
		DTActividad dtActividad = new DTActividad(this.nombre, this.descripcion, this.enlace, this.duracion, this.costoPT, this.ciudad, this.fechaAlta, this.estadoActual, this.cantFavoritos, this.categoria);
		return dtActividad;
	}
	
	public String[] listarSalidasActividad() throws NoExistenSalidasEnActividadException {
		if (!mapSalidas.isEmpty()) {
			Collection<SalidasAT> sals = mapSalidas.values();
			Object[] salidas = sals.toArray();
			String[] result = new String[salidas.length];
			for (int i = 0; i < salidas.length; i++) {
				SalidasAT sat = (SalidasAT) salidas[i];
				result[i] = sat.getNombre();
			}
			return result;				
		} else {
        	throw new NoExistenSalidasEnActividadException("No existen salidas registradas para la actividad");
        }
	}
	
	public String[] listarPaquetes() throws NoExistenPaquetesEnLaActividadException {
		if (!mapPaquetes.isEmpty()) {
			Collection<PaqueteAT> paqs = mapPaquetes.values();
			Object[] paquetes = paqs.toArray();
			String[] result = new String[paquetes.length];
			for (int i = 0; i < paquetes.length; i++) {
				PaqueteAT paq = (PaqueteAT) paquetes[i];
				result[i] = paq.getNombre();
			}
			return result;				
		} else {
        	throw new NoExistenPaquetesEnLaActividadException("No existen paquetes registrados para la actividad");
        }
	}

	public boolean perteneceAPaquete(String paquete) {
		return mapPaquetes.containsKey(paquete);
	}
	
	public void agregarPaquete(PaqueteAT paq) {
		mapPaquetes.put(paq.getNombre(), paq);
	}

	public DTSalidasAT obtenerDatosSalida(String nombreST) {
		SalidasAT salAT = (SalidasAT) mapSalidas.get(nombreST);
		return salAT.getDatosSalida();
	}
	
	public void agregarSalida(SalidasAT sal) {
		mapSalidas.put(sal.getNombre(), sal);
	}

	public DTSalidasAT[] obtenerSalidasDisponibles() {
		Collection<SalidasAT> sal = mapSalidas.values();
		Object[] salidas = sal.toArray();
		Set<DTSalidasAT> sourceSet = new HashSet<DTSalidasAT>();
		for (int i = 0; i < salidas.length; i++) {
			SalidasAT salida = (SalidasAT) salidas[i];
			LocalDate fechaActual = LocalDate.now();
			LocalTime horaActual = LocalTime.now();
			if ((salida.getFecha().isAfter(fechaActual) || (salida.getFecha().isEqual(fechaActual) &&  salida.getHora().isAfter(horaActual)))) {
				DTSalidasAT dtSalida = salida.getDatosSalida();
				sourceSet.add(dtSalida);
			}
		}
		DTSalidasAT[] targetArray = sourceSet.toArray(new DTSalidasAT[0]);
		return targetArray;
	}

	public SalidasAT getSalidaNombre(String nombreSalida) {
		return (SalidasAT) mapSalidas.get(nombreSalida);
	}
	
	public boolean existeSalida(String nombreST) {
		return mapSalidas.get(nombreST) != null;
	}
	
	public void darAltaSalida(String nombreST, LocalDate Fecha, String salidalugar, int cantMax, LocalTime hora, LocalDate fechaAlta) {
		SalidasAT sal = new SalidasAT(nombreST, cantMax, fechaAlta, Fecha, hora, salidalugar, 0);
		mapSalidas.put(nombreST, sal);
	}

	public Estado getEstado() {
		return estadoActual;
	}

	public void setEstado(logica.ActividadAT.Estado nuevoEstado) {
		this.estadoActual = nuevoEstado;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public int getCantFavoritos() {
		return cantFavoritos;
	}
	
	public void agregarFavorito() {
		this.cantFavoritos = cantFavoritos + 1;  
	}
	
	public void eliminarFavorito() {
		this.cantFavoritos = cantFavoritos - 1;  
	}
}
