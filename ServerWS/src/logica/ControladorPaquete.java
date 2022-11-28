package logica;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import excepciones.ExistePaqueteException;
import excepciones.NoExistenActividadesEnDepartamentoException;
import excepciones.NoExistenPaquetesException;
import excepciones.NoExistenActividadesEnElPaqueteException;

public class ControladorPaquete implements IPaquete {
	public ControladorPaquete() {
    }
	
	public String[] listarActividadesNoPertenecientesAPaqueteConfirmada(String departamento, String paquete)throws NoExistenActividadesEnDepartamentoException {
		ManejadorDepartamentos mDep = ManejadorDepartamentos.getinstance();
		Departamento dep = mDep.obtenerDepartamento(departamento);
		String actsNoPertenecientes [] = dep.listarActividadesNoPertenecientesAPaqueteConfirmada(paquete); //arregrlar esta funcion
		return actsNoPertenecientes;
	}
	
	public String[] listarPaquetesNoComprados()throws NoExistenPaquetesException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		String paquetes[] = mPaquetes.listarPaquetes();
		Set<String> paqNo = new HashSet<String>();
		for (int i = 0; i < paquetes.length; i++) {
			PaqueteAT paq = mPaquetes.obtenerPaquete(paquetes[i]);
			if (!paq.fueComprado()) {
				paqNo.add(paquetes[i]);
			}
		}
		String[] Array = paqNo.toArray(new String[0]);
		return Array;
	}
	
	public void darAltaPaquete(String nombre, String descripcion, int descuento, int periodo, LocalDate fecha) throws ExistePaqueteException {
		ManejadorPaquetes mPaq = ManejadorPaquetes.getinstance();
		if (mPaq.existePaquete(nombre)) {
			throw new ExistePaqueteException("El paquete con nombre " + nombre + " ya esta registrado en el sistema");
		}
		
		
		PaqueteAT nuevo = new PaqueteAT(nombre, descripcion, descuento, periodo, fecha);
		mPaq.agregarPaquete(nuevo);
		
	}
	
	public String[] listarPaquetes() throws NoExistenPaquetesException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		String paquetes[] = mPaquetes.listarPaquetes();
		return paquetes;
	}
	
	public DTPaqueteAT obtenerDatosPaquete(String nombre) {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		DTPaqueteAT dtp = mPaquetes.obtenerDatosPaquete(nombre);
		return dtp;
	}
	 
	public String[] listarActividadesDePaquete(String nombre) throws NoExistenActividadesEnElPaqueteException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		PaqueteAT paq = mPaquetes.obtenerPaquete(nombre);
		String actividades [] = paq.listarActividades();
		return actividades;
	}
	
	public String[] listarActividadesNoPertenecientesAPaquete(String departamento, String paquete) throws NoExistenActividadesEnDepartamentoException {
		ManejadorDepartamentos mDep = ManejadorDepartamentos.getinstance();
		Departamento dep = mDep.obtenerDepartamento(departamento);
		String actsNoPertenecientes [] = dep.listarActividadesNoPertenecientesAPaquete(paquete); //arregrlar esta funcion
		return actsNoPertenecientes;
	}
	
	public void ingresarActividadAlPaquete(String actividad, String paquete) {
		ManejadorActividadT mat = ManejadorActividadT.getinstance();
		ActividadAT act = mat.obtenerActividad(actividad);
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		PaqueteAT paq = mPaquetes.obtenerPaquete(paquete);
		int Descuento = paq.getDescuento();
		int precioact = paq.getCostoPorPersona();
		int preActivi = act.getCostoPT();
		int nuevoPrecio = precioact  + (preActivi - preActivi * (Descuento / 100));
		paq.setCostoPorPersona(nuevoPrecio);
		paq.agregarActividad(act);
		act.agregarPaquete(paq);
	}

	@Override
	public void setURLPaquete(String nomPaq, String URL) {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		PaqueteAT paq = mPaquetes.obtenerPaquete(nomPaq);
		paq.setURLImagen(URL);
	}

	@Override
	public String getURLPaquete(String nomPaq) {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		PaqueteAT paq = mPaquetes.obtenerPaquete(nomPaq);
		return paq.getURLImagen();
	}
	
	public String[] listarCategoriasPaquete(String nomPaq) throws NoExistenActividadesEnElPaqueteException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		PaqueteAT paq = mPaquetes.obtenerPaquete(nomPaq);
		String[] catsP = paq.listarCategoriasPaquete(paq);
		return catsP;
	}
	
}
