package logica;

import java.util.Map;
import java.util.Set;

import excepciones.NoExistenActividadesException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Proveedor extends Usuario {
	
	private String descripcion;
	private String linkWeb;
	private Map<String, ActividadAT> actividades = new HashMap<String, ActividadAT>();
	
	public Proveedor(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String password) {
		super(nickname, nombre, apellido, email, fechaNac, password);
	}

	public Proveedor(String nickname, String nombre, String apellido, String email, LocalDate fechaNac,
			String descripcion, String link, String password) {
		super(nickname, nombre, apellido, email, fechaNac, password);
		this.descripcion = descripcion;
		linkWeb = link;
	}

	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getLinkWeb() {
		return linkWeb;
	}
	
	public void setLinkWeb(String link) {
		linkWeb = link;
	}
	
	public List<DTActividad> listarActividadesTuristicas() {
		ArrayList<DTActividad> list = new ArrayList<DTActividad>();
		Iterator<Map.Entry<String, ActividadAT>> iterador = actividades.entrySet().iterator();
		while (iterador.hasNext()) {
			DTActividad acti = iterador.next().getValue().getDatosActividad();
			list.add(acti);
		}
		return list;
	}
	
	public String[] listarNombresActividadesTuristicas() {
        String[] devolver = new String[actividades.size()];
        Iterator<Map.Entry<String, ActividadAT>> iterador = actividades.entrySet().iterator();
		if (actividades.size() > 0) {
        	int indice = 0;
	        while (iterador.hasNext()) {
	        	devolver[indice] = iterador.next().getValue().getNombre();
				indice++;
			}
		}
        return devolver;
	}
	
	
	
	public void agregarActividad(ActividadAT actiOf) {
		actividades.put(actiOf.getNombre(), actiOf);
	}

	public DTActividad[] listarActividadesTuristicasConfirmadas() throws NoExistenActividadesException {
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			Set<DTActividad> sourceSet = new HashSet<DTActividad>();
			for (int i = 0; i < actividades.length; i++) {
				ActividadAT actividad = (ActividadAT) actividades[i];
				if (actividad.getEstado() == logica.ActividadAT.Estado.Confirmada) {
					DTActividad dtact = actividad.getDatosActividad();
					sourceSet.add(dtact);
				}
			}
			DTActividad[] targetArray = sourceSet.toArray(new DTActividad[0]);
			if (targetArray.length == 0) {
				throw new NoExistenActividadesException("No existen actividades agregadas");
			} else {
				return targetArray;
			}	
    	} else {
        	throw new NoExistenActividadesException("No existen actividades agregadas");
        }
	}
}