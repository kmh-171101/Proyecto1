package logica;

//import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.NoExistenActividadesEnDepartamentoException;

public class Departamento {
	private String nombre;
	private String descripcion;
	private String url;
	Map<String, ActividadAT> actividades = new HashMap<String, ActividadAT>();
	
	
	public Departamento(String nombre, String descripcion, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
	}
	public Departamento(DTDepartamento departamento) {
		this.nombre = departamento.getNombre();
		this.descripcion = departamento.getDescripcion();
		this.url = departamento.getURL();
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
	public String getURL() {
		return url;
	}
	public void setURL(String uRL) {
		this.url = uRL;
	}
	public void modificarDatos(DTDepartamento departamento) {
		this.descripcion = departamento.getDescripcion();
		this.url = departamento.getURL();
	}
	public void agregarActividad(ActividadAT actiOf) {
		actividades.put(actiOf.getNombre(), actiOf);
	}

	public String[] listarActividadesDepartamento() throws NoExistenActividadesEnDepartamentoException {
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			Set<String> sourceSet = new HashSet<String>();
			for (int i = 0; i < actividades.length; i++) {
				ActividadAT aat = (ActividadAT) actividades[i];
				if (aat.getEstado() == logica.ActividadAT.Estado.Confirmada) {
					String dtact = aat.getNombre();
					sourceSet.add(dtact);
				}
			}
			String[] targetArray = sourceSet.toArray(new String[0]);
			if (targetArray.length == 0) {
				throw new NoExistenActividadesEnDepartamentoException("No existen actividades confirmadas para el departamento");
			} else {
				return targetArray;	
    		}
		} else {
			throw new NoExistenActividadesEnDepartamentoException("No existen actividades confirmadas para el departamento");
		}
	}
	
	public String[] listarActividadesNoPertenecientesAPaquete(String paquete) throws NoExistenActividadesEnDepartamentoException {
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			Set<String> sourceSet = new HashSet<String>();
			for (int i = 0; i < actividades.length; i++) {
				ActividadAT actividad = (ActividadAT) actividades[i];
				if (!actividad.perteneceAPaquete(paquete)) {
					String dtact = actividad.getNombre();
					sourceSet.add(dtact);
				}
			}
			String[] targetArray = sourceSet.toArray(new String[0]);
			return targetArray;		
		} else {
        	throw new NoExistenActividadesEnDepartamentoException("No existen actividades registradas para el departamento");
        }
		
	}
	public String[] listarActividadesNoPertenecientesAPaqueteConfirmada(String paquete) throws NoExistenActividadesEnDepartamentoException {
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			Set<String> sourceSet = new HashSet<String>();
			for (int i = 0; i < actividades.length; i++) {
				ActividadAT actividad = (ActividadAT) actividades[i];
				if (!actividad.perteneceAPaquete(paquete) && actividad.getEstado() == logica.ActividadAT.Estado.Confirmada) {
					String dtact = actividad.getNombre();
					sourceSet.add(dtact);
				}
			}
			String[] targetArray = sourceSet.toArray(new String[0]);
			return targetArray;		
		} else {
        	throw new NoExistenActividadesEnDepartamentoException("No existen actividades registradas para el departamento");
        }
	}
}
