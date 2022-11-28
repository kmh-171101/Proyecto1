package logica;

import java.util.HashMap;
import java.util.Map;

public class ManejadorDepartamentos {
	private Map<String, Departamento> departamentos;
	private static ManejadorDepartamentos instancia = null;

    private ManejadorDepartamentos() {
        departamentos = new HashMap<String, Departamento>();
    }

    public static ManejadorDepartamentos getinstance() {
        if (instancia == null) {
            instancia = new ManejadorDepartamentos();
        }
        return instancia;
 
    }
    public boolean existeDepartamento(String nombreDep) {
		return ((Departamento) departamentos.get(nombreDep)) != null;
	}

    public Map<String, Departamento> getDepartamentos() {
    	return departamentos;
    }
    
    public void agregarDepartamento(Departamento dep) {
        String nombreAT = dep.getNombre();
        departamentos.put(nombreAT, dep);
    }

	public Departamento obtenerDepartamento(String departamento) {
		return ((Departamento) departamentos.get(departamento));
	}
    
    public void modificarDepartamentoExistente(DTDepartamento departamento) {
    	Departamento dep = (Departamento) departamentos.get(departamento.getNombre());
    	dep.modificarDatos(departamento);
    }
}
