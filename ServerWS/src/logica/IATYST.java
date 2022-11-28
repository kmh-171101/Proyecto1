package logica;

import java.time.LocalDate;
import java.time.LocalTime;

import excepciones.ExcedeCapacidadException;
import excepciones.ExisteActividadException;
import excepciones.ExisteCategoriaException;
import excepciones.NoExisteDepartamentoException;
import excepciones.NoExisteProveedorException;
import excepciones.NoExistenActividadesException;
import excepciones.NoExistenCategoriasException;
import excepciones.NoExistenPaquetesEnLaActividadException;
import excepciones.NoExistenSalidasEnActividadException;
import excepciones.NoHaySalidasDisponiblesException;
import excepciones.YaTieneInscripcionAEstaSalidaException;

public interface IATYST {
	public abstract void darAltaActividadTuristica(String nombreU, String departamento, String ciudad, String descripcion, String enlace, String proveedor, int duracion, int costo, LocalDate date, String [] nombreCat) throws ExisteActividadException, NoExisteDepartamentoException, NoExisteProveedorException;

	public abstract void agregarCategoriaActividad(String act, String cat);
	
	public abstract String[] listarSalidasActividad(String act) throws NoExistenSalidasEnActividadException;
	
	public abstract DTSalidasAT obtenerDatosSalida(String nombreAT, String nombreST);
	
	public abstract DTSalidasAT obtenerDatosSalidaTur(String nickname, String nombre);
	
	public abstract boolean existeSalida(String nombreAT, String nombreST);
	
	public abstract void darAltaSalida(String nombreAT, String nombreST, LocalDate Fecha, String salida, int cantMax, LocalTime hora, LocalDate FechaAlta);

	public abstract DTSalidasAT[] listarDatosSalidasVigentes(String nombreActividad) throws NoHaySalidasDisponiblesException;

	public abstract void darAltaSalidacarga(String nombreAT, String nombreSal, LocalDate fecha, LocalTime hora, int turisMax, String lugar, LocalDate fechaAlta);
	
	public abstract void inscribirASalida(String nombreTurista, String nombreActividad, String nombreSalida, int integrantes, LocalDate now) throws ExcedeCapacidadException, YaTieneInscripcionAEstaSalidaException;

	public abstract DTActividad obtenerDatosActividad(String nombre);
	
	public abstract String[] listarPaquetesActividad(String nombre) throws NoExistenPaquetesEnLaActividadException;
	
	public abstract void darAltaCategoria(String nombre) throws ExisteCategoriaException;

	public abstract String[] listarCategorias()throws NoExistenCategoriasException;
	
	public abstract String[] listarCategoriasActividad(String nombre)throws NoExistenCategoriasException;
	
	public abstract String[] listarActividadesAgregadas()throws NoExistenActividadesException;
	
	public abstract void cambiarEstado(logica.ActividadAT.Estado nuevoEstado, String act);
	
	public abstract String[] listarActividadesCategoria(String cat)throws NoExistenActividadesException;

	public abstract void setURLActividad(String nomAct, String URL);
	
	public abstract String getURLActividad(String nomAct);
	
	public abstract void setURLSalida(String nomSal, String nomAct, String URL);
	
	public abstract String getURLSalida(String nomSal, String nomAct);
	
	public abstract boolean existeCategoria(String nomCat);
	
	public abstract boolean existeSalida(String salida);
	
	public abstract String[] listarActividadesConfirmadas()throws NoExistenActividadesException;
	
	public abstract String obtenerNombreActividad(String nombreSalida);
}
