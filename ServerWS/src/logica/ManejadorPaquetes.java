package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import excepciones.NoExistenPaquetesException;

public class ManejadorPaquetes {
	private Map<String, PaqueteAT> paquetes;
	private static ManejadorPaquetes instancia = null;

    private ManejadorPaquetes() {
        paquetes = new HashMap<String, PaqueteAT>();
    }

    public static ManejadorPaquetes getinstance() {
        if (instancia == null) {
            instancia = new ManejadorPaquetes();
        }
        return instancia;
    }
    
    public boolean existePaquete(String nombreDep) {
		return ((PaqueteAT) paquetes.get(nombreDep)) != null;
	}
    
    public void agregarPaquete(PaqueteAT dep) {
        String nombreAT = dep.getNombre();
        paquetes.put(nombreAT, dep);
    }
    
    public String[] listarPaquetes() throws NoExistenPaquetesException {
    	if (!paquetes.isEmpty()) {
			Collection<PaqueteAT> paqs = paquetes.values();
			Object[] paquetes = paqs.toArray();
			String[] result = new String[paquetes.length];
			for (int i = 0; i < paquetes.length; i++) {
				PaqueteAT paq = (PaqueteAT) paquetes[i];
				result[i] = paq.getNombre();
			}
			return result;
    	} else {
        	throw new NoExistenPaquetesException("No existen paquetes registrados");
        }
    }
    
    public DTPaqueteAT obtenerDatosPaquete(String nombre) {
    	PaqueteAT paq = paquetes.get(nombre);
    	DTPaqueteAT dtp = paq.getDatosPaquete();
    	return dtp;
    }
    
    public PaqueteAT obtenerPaquete(String nombre) {
    	return ((PaqueteAT) paquetes.get(nombre));
    }
}
