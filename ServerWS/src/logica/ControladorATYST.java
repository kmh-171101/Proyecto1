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

public class ControladorATYST implements IATYST {
	public ControladorATYST() {
    }
	
	public String obtenerNombreActividad(String nombreSalida) {
		ManejadorActividadT mAct = ManejadorActividadT.getinstance();
		return mAct.obtenerNombreActividad(nombreSalida);
	}
	
	public void agregarCategoriaActividad(String act, String cat) {
		ManejadorActividadT mAT= ManejadorActividadT.getinstance();
		ActividadAT activi = mAT.obtenerActividad(act);
		activi.agregarCategoria(cat);
	}
	
	public String[] listarCategorias()throws NoExistenCategoriasException {
		ManejadorCategorias mCategoria = ManejadorCategorias.getinstance();
		String[] cats = mCategoria.listarCategorias();
		return cats;
	}
	
	public String[] listarCategoriasActividad(String nombre)throws NoExistenCategoriasException{
		ManejadorActividadT mAT= ManejadorActividadT.getinstance();
		ActividadAT act = mAT.obtenerActividad(nombre);
		String[] cats = act.listarCategoriasAct();
		return cats;
	}
	
	public String[] listarActividadesAgregadas()throws NoExistenActividadesException {
		ManejadorActividadT mAT = ManejadorActividadT.getinstance();
		String [] acts = mAT.listarActividadesAgregadas();
		return acts;
	}
	
	public String[] listarActividadesConfirmadas()throws NoExistenActividadesException {
		ManejadorActividadT mAT = ManejadorActividadT.getinstance();
		String [] acts = mAT.listarActividadesConfirmadas();
		return acts;
	}
	
	public void inscribirASalida(String nombreTurista, String nombreActividad, String nombreSalida, int integrantes, LocalDate now) throws ExcedeCapacidadException, YaTieneInscripcionAEstaSalidaException {
		ManejadorActividadT mAT = ManejadorActividadT.getinstance();
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Usuario usr = mUsr.obtenerUsuario(nombreTurista); //turista a vincular
		Turista tur = (Turista) usr;
		ActividadAT act = mAT.obtenerActividad(nombreActividad); //actividad a vincular
		SalidasAT sal = act.getSalidaNombre(nombreSalida); //salida a vincular
		int maximo = sal.getCantMax();
		int actual = sal.getCantTotalTuristas();
		if ((actual + integrantes) > maximo) {
			throw new ExcedeCapacidadException(" No hay suficiente capacidad para ingresar a todos los integrantes \n Vuelva a Intentarlo "
					+ "\n Hay Actualmente " + actual + " integrantes");
		}
		if (tur.estaInscripto(nombreSalida)) {
			throw new YaTieneInscripcionAEstaSalidaException("Ya esta inscripto a esta Salida Turistica \n Vuelva a Intentarlo");
		}
		
		int costoTotal = integrantes * act.getCostoPT();
		Inscripcion nueva = new Inscripcion(now, integrantes, costoTotal, tur, sal);
		
		tur.agregarInscripcion(nueva);
		sal.agregarInscripcion(nueva);
	}
	
	public DTActividad obtenerDatosActividad(String nombre) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		DTActividad dtac = mActividad.obtenerDatosActividad(nombre);
		return dtac;
	}

	public void darAltaActividadTuristica(String nombreAt, String departamento, String ciudad, String descripcion, String enlace, String proveedor, int duracion, int costo, LocalDate date, String[] nombreCat) throws ExisteActividadException, NoExisteDepartamentoException, NoExisteProveedorException {
		ManejadorActividadT mAt = ManejadorActividadT.getinstance();
		ManejadorDepartamentos mDep = ManejadorDepartamentos.getinstance();
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		
		if (mAt.existeActividad(nombreAt)) {
			throw new ExisteActividadException("La Actividad con nombre " + nombreAt + " ya esta registrada");
		}
		if (!mDep.existeDepartamento(departamento)) {
			throw new NoExisteDepartamentoException("El Departamento con nombre " + departamento + " no esta en el sistema");
		}
		if (!mUsr.existeProveedor(proveedor)) {
			throw new NoExisteProveedorException("El Proveedor con nombre " + proveedor + " no esta en el sistema");
		}
		
		ActividadAT nuevo = new ActividadAT(nombreAt, descripcion, enlace, duracion, costo, ciudad, date, nombreCat);
		mAt.agregarActividad(nuevo);
		
		Usuario usr = mUsr.obtenerUsuario(proveedor);
		Proveedor prov = (Proveedor) usr;
		prov.agregarActividad(nuevo);
		
		Departamento dep = mDep.obtenerDepartamento(departamento);
		dep.agregarActividad(nuevo);
	}
	
	public String[] listarSalidasActividad(String nombre) throws NoExistenSalidasEnActividadException {
		ManejadorActividadT mat = ManejadorActividadT.getinstance();
		ActividadAT act = mat.obtenerActividad(nombre);
		String salidas [] = act.listarSalidasActividad();
		return salidas;
	}
	
	public String[] listarPaquetesActividad(String nombre) throws NoExistenPaquetesEnLaActividadException {
		ManejadorActividadT mat = ManejadorActividadT.getinstance();
		ActividadAT act = mat.obtenerActividad(nombre);
		String paquetes [] = act.listarPaquetes();
		return paquetes;
	}
	
	public DTSalidasAT obtenerDatosSalida(String nombreAT, String nombreST) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT actividad = mActividad.obtenerActividad(nombreAT);
		return actividad.obtenerDatosSalida(nombreST);
	}
	
	public DTSalidasAT obtenerDatosSalidaTur(String nickname, String nombre) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Turista tur = mUsr.obtenerTurista(nickname);
		return tur.obtenerDatosSalida(nombre);
	}
	
	public DTSalidasAT[] listarDatosSalidasVigentes(String nombreActividad) throws NoHaySalidasDisponiblesException {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT actividad = mActividad.obtenerActividad(nombreActividad);
		DTSalidasAT[] salidas = actividad.obtenerSalidasDisponibles();
		if (salidas.length == 0) {
			throw new NoHaySalidasDisponiblesException("No hay Salidas disponibles para la Actividad de nombre " + nombreActividad);
		}
		return salidas;
	}
	
	public boolean existeSalida(String nombreAT, String nombreST) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT actividad = mActividad.obtenerActividad(nombreAT);
		return actividad.existeSalida(nombreST);
	}
	
	public void darAltaSalida(String nombreAT, String nombreST, LocalDate Fecha, String salida, int cantMax, LocalTime hora, LocalDate fechaAlta) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT actividad = mActividad.obtenerActividad(nombreAT);
		actividad.darAltaSalida(nombreST, Fecha, salida, cantMax, hora, fechaAlta);
	}

	@Override
	public void darAltaSalidacarga(String nombreAT, String nombreSal, LocalDate fecha, LocalTime hora, int turisMax, String lugar, LocalDate fechaAlta) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT actividad = mActividad.obtenerActividad(nombreAT);
		if (actividad != null) {
			actividad.darAltaSalida(nombreSal, fecha, lugar, turisMax, hora, fechaAlta);
		}
	}
	
	public void darAltaCategoria(String nombre) throws ExisteCategoriaException {
		ManejadorCategorias mCategoria = ManejadorCategorias.getinstance();
		Categoria cat = new Categoria(nombre);
		if (!mCategoria.existeCategoria(nombre)) {
			mCategoria.darAltaCategoria(cat);
		} else {
			throw new ExisteCategoriaException("Ya existe una categoria con el nombre " + nombre);
		}
		
	}
	
	public void cambiarEstado(logica.ActividadAT.Estado nuevoEstado, String act) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT activ = mActividad.obtenerActividad(act);
		activ.setEstado(nuevoEstado);
	}
	
	public String[] listarActividadesCategoria(String cat) throws NoExistenActividadesException {
		ManejadorActividadT mAT = ManejadorActividadT.getinstance();
		String [] acts = mAT.listarActividadesCategoria(cat);
		return acts;
	}

	@Override
	public void setURLActividad(String nomAct, String URL) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT activ = mActividad.obtenerActividad(nomAct);
		activ.setUrlImagen(URL);
	}

	@Override
	public String getURLActividad(String nomAct) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT activ = mActividad.obtenerActividad(nomAct);
		return activ.getUrlImagen();
	}

	@Override
	public void setURLSalida(String nomSal, String nomAct, String URL) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT activ = mActividad.obtenerActividad(nomAct);
		SalidasAT sal = activ.getSalidaNombre(nomSal);
		sal.setUrlImage(URL);
	}

	@Override
	public String getURLSalida(String nomSal, String nomAct) {
		ManejadorActividadT mActividad = ManejadorActividadT.getinstance();
		ActividadAT activ = mActividad.obtenerActividad(nomAct);
		SalidasAT sal = activ.getSalidaNombre(nomSal);
		return sal.getUrlImage();
	}
	
	
	public boolean existeCategoria(String nomCat) {
		ManejadorCategorias mCategoria = ManejadorCategorias.getinstance();
		return mCategoria.existeCategoria(nomCat);
	}
	
	public boolean existeSalida(String salida) {
		ManejadorActividadT mAct = ManejadorActividadT.getinstance();
		boolean existe = mAct.existeSalida(salida);
		return existe;
	}
}
