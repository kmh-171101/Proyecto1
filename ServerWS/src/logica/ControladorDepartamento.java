package logica;

import java.util.Collection;
import java.util.Map;

import excepciones.NoExistenActividadesEnDepartamentoException;
import excepciones.NoExistenDepartamentosException;
/*import excepciones.NoExistenActividadesEnDepartamentoException;*/

public class ControladorDepartamento implements IDepartamento {
	
	private DTDepartamento departamento;
	
	public DTDepartamento getDatosDepartamento(String nombre_dep) {
		ManejadorDepartamentos mDepartamentos = ManejadorDepartamentos.getinstance();
		Departamento dep = mDepartamentos.obtenerDepartamento(nombre_dep);
		DTDepartamento nuevo = new DTDepartamento(dep.getNombre(), dep.getDescripcion(), dep.getURL());
		return nuevo;
	}
	
	public void darAltaDepartamentoCargarDatos(String nombre, String descripcion, String web) {
		Departamento nuevo = new Departamento(nombre, descripcion, web);
		ManejadorDepartamentos mDepartamentos = ManejadorDepartamentos.getinstance();
		mDepartamentos.agregarDepartamento(nuevo);
	}
	
	@Override
	public String[] listarDepartamentos() throws NoExistenDepartamentosException {
		ManejadorDepartamentos mDep = ManejadorDepartamentos.getinstance();
		Map<String, Departamento> deps = mDep.getDepartamentos();
		if (!deps.isEmpty()) {
            Collection<Departamento> depas = deps.values();
            Object[] departamentos = depas.toArray();
            Departamento[] depComp = new Departamento[departamentos.length];
            String[] devolver = new String[departamentos.length];
            for (int i = 0; i < departamentos.length; i++) {
                depComp[i] = (Departamento) departamentos[i];
                devolver[i] = depComp[i].getNombre();
            }
	        return devolver;
        } else {
        	throw new NoExistenDepartamentosException("No existen departamentos registrados para realizar el alta");
        }
	}


	public String[] listarActividadesDepartamento(String depto) throws NoExistenActividadesEnDepartamentoException {
		ManejadorDepartamentos mDep = ManejadorDepartamentos.getinstance();
		Departamento dep = mDep.obtenerDepartamento(depto);
		String actividades [] = dep.listarActividadesDepartamento();
		return actividades;
	}

	public DTDepartamento getDepartamento() {
		return departamento;
	}
	
	public void ingresarDepartamento(DTDepartamento depar) {
		departamento = depar;
	}
	
	public boolean existeDepartamento() {
		String dep = departamento.getNombre();
		ManejadorDepartamentos mDepartamentos = ManejadorDepartamentos.getinstance();
		return mDepartamentos.existeDepartamento(dep);
	}

	public void cancelarAltaDepartamento() {
		departamento = null;
	}
	
	public void modificarDatosDepartamento() {
		DTDepartamento departamento = getDepartamento();
		ManejadorDepartamentos mDepartamentos = ManejadorDepartamentos.getinstance();
		mDepartamentos.modificarDepartamentoExistente(departamento);
	}
	
	public void darAltaDepartamento() {
		DTDepartamento departamento = getDepartamento();
		Departamento dep = new Departamento(departamento);
		ManejadorDepartamentos mDepartamentos = ManejadorDepartamentos.getinstance();
		mDepartamentos.agregarDepartamento(dep);
	}

}




