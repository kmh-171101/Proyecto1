package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.NoExistenActividadesException;

public class ManejadorActividadT {
	private Map<String, ActividadAT> actividades;
	private static ManejadorActividadT instancia = null;
	
	private ManejadorActividadT() {
        actividades = new HashMap<String, ActividadAT>();
    }

    public static ManejadorActividadT getinstance() {
        if (instancia == null) {
            instancia = new ManejadorActividadT();
        }
        return instancia;
    }
    
    public DTActividad obtenerDatosActividad(String nombre) {
    	logica.ActividadAT act = actividades.get(nombre);
    	DTActividad dtact = act.getDatosActividad();
    	return dtact;
    }
    

	public boolean existeActividad(String nombreAt) {
		return ((ActividadAT) actividades.get(nombreAt)) != null;
	}
	public void agregarActividad(ActividadAT act) {
        String nombreAT = act.getNombre();
        actividades.put(nombreAT, act);
    }
	
	public ActividadAT obtenerActividad(String nombre) {
		return ((ActividadAT) actividades.get(nombre));
	}

	public String[] listarActividadesAgregadas() throws NoExistenActividadesException {
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			Set<String> sourceSet = new HashSet<String>();
			for (int i = 0; i < actividades.length; i++) {
				ActividadAT actividad = (ActividadAT) actividades[i];
				if (actividad.getEstado() == logica.ActividadAT.Estado.Agregada) {
					String dtact = actividad.getNombre();
					sourceSet.add(dtact);
				}
			}
			String[] targetArray = sourceSet.toArray(new String[0]);
			if (targetArray.length == 0) {
				throw new NoExistenActividadesException("No existen actividades agregadas");
			} else {
				return targetArray;
			}	
    	} else {
        	throw new NoExistenActividadesException("No existen actividades agregadas");
        }
	}
	
	public String[] listarActividadesConfirmadas() throws NoExistenActividadesException {
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			Set<String> sourceSet = new HashSet<String>();
			for (int i = 0; i < actividades.length; i++) {
				ActividadAT actividad = (ActividadAT) actividades[i];
				if (actividad.getEstado() == logica.ActividadAT.Estado.Confirmada) {
					String dtact = actividad.getNombre();
					sourceSet.add(dtact);
				}
			}
			String[] targetArray = sourceSet.toArray(new String[0]);
			if (targetArray.length == 0) {
				throw new NoExistenActividadesException("No existen actividades confirmadas");
			} else {
				return targetArray;
			}	
    	} else {
        	throw new NoExistenActividadesException("No existen actividades confirmadas");
        }
	}

	public String[] listarActividadesCategoria(String cat) throws NoExistenActividadesException {
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			Set<String> sourceSet = new HashSet<String>();
			for (int i = 0; i < actividades.length; i++) {
				ActividadAT actividad = (ActividadAT) actividades[i];
				if (actividad.getEstado().equals(logica.ActividadAT.Estado.Confirmada)) {
					String[] cats = actividad.listarCategoriasAct();
					int indice = 0;
					while (indice < cats.length) {
						if (cats[indice].equals(cat)) {
							String dtact = actividad.getNombre();
							sourceSet.add(dtact);	
						}
						indice++;
					}
				}
			}
			String[] targetArray = sourceSet.toArray(new String[sourceSet.size()]);
			if (targetArray.length == 0) {
				throw new NoExistenActividadesException("No existen actividades agregadas");
			} else {
				return targetArray;
			}
    	} else {
        	throw new NoExistenActividadesException("No existen actividades agregadas");
        }
	}

	public boolean existeSalida(String salida) {
		boolean existe = false;
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			Set<String> sourceSet = new HashSet<String>();
			int indice = 0;
			while (indice < actividades.length && !existe) {
				ActividadAT actividad = (ActividadAT) actividades[indice];
				if(actividad.existeSalida(salida)) {
					existe = true;
				}
				indice++;
			}
		}
		return existe;
	}
	
	public String obtenerNombreActividad(String nombreSalida) {
		boolean encontrada = false;
		ActividadAT actividad = null;
		String nombre = null;
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			Set<String> sourceSet = new HashSet<String>();
			int indice = 0;
			while (!encontrada) {
				actividad = (ActividadAT) actividades[indice];
				if(actividad.existeSalida(nombreSalida)) {
					encontrada = true;
				}
				indice++;
			}
			nombre = actividad.getNombre();
		}
		return nombre;
	}
}
