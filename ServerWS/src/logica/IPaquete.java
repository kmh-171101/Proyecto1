package logica;

import java.time.LocalDate;

import excepciones.ExistePaqueteException;
import excepciones.NoExistenActividadesEnDepartamentoException;
import excepciones.NoExistenActividadesEnElPaqueteException;

import excepciones.NoExistenPaquetesException;

public interface IPaquete {

	public abstract void darAltaPaquete(String nombre, String descripcion, int descuento, int periodo, LocalDate fecha) throws ExistePaqueteException;

	public abstract DTPaqueteAT obtenerDatosPaquete(String nombre);
	
	public abstract String[] listarPaquetes() throws NoExistenPaquetesException;
	
	public abstract void ingresarActividadAlPaquete(String actividad, String paquete);
	
	public abstract String[] listarActividadesNoPertenecientesAPaquete(String departamento, String paquete) throws NoExistenActividadesEnDepartamentoException;

	public abstract String[] listarActividadesDePaquete(String nombre) throws NoExistenActividadesEnElPaqueteException;

	public abstract String[] listarPaquetesNoComprados()throws NoExistenPaquetesException;

	public abstract String[] listarActividadesNoPertenecientesAPaqueteConfirmada(String departamento, String paquete)throws NoExistenActividadesEnDepartamentoException;

	public abstract void setURLPaquete(String nomPaq, String URL);
	
	public abstract String getURLPaquete(String nomPaq);
	
	public abstract String[] listarCategoriasPaquete(String nomPaq) throws NoExistenActividadesEnElPaqueteException;
}
