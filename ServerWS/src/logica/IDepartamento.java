package logica;


import excepciones.NoExistenActividadesEnDepartamentoException;
import excepciones.NoExistenDepartamentosException;

public interface IDepartamento {
	public abstract String[] listarDepartamentos() throws NoExistenDepartamentosException;
	
	public abstract void darAltaDepartamento();
	
	public abstract void darAltaDepartamentoCargarDatos(String nombre, String descripcion, String web);
	
	public abstract String[] listarActividadesDepartamento(String depto) throws NoExistenActividadesEnDepartamentoException;
	
	public abstract DTDepartamento getDatosDepartamento(String nombre_dep);
	
	public abstract void ingresarDepartamento(DTDepartamento depto);
	
	public abstract  boolean existeDepartamento();
	
	public abstract void modificarDatosDepartamento();
}
