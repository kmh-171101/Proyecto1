package logica;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import excepciones.NoExistenActividadesException;
import excepciones.NoExistenProveedoresException;
import excepciones.NoExistenUsuariosException;
import excepciones.UsuarioSinActividadesFavoritasException;
import excepciones.UsuarioSinSeguidoresException;
import excepciones.UsuarioSinSeguidosException;

public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
    }
	@Override
	public String[] listarProveedores() throws NoExistenProveedoresException {
		ManejadorUsuarios mDep = ManejadorUsuarios.getinstance();
		Map<String, Proveedor> deps = mDep.getProveedores();
		if (!deps.isEmpty()) {
            Collection<Proveedor> depas = deps.values();
            Object[] departamentos = depas.toArray();
            Proveedor[] depComp = new Proveedor[departamentos.length];
            String[] devolver = new String[departamentos.length];
            for (int i = 0; i < departamentos.length; i++) {
                depComp[i] = (Proveedor) departamentos[i];
                devolver[i] = depComp[i].getNickname();
            }
	        return devolver;
        } else {
        	throw new NoExistenProveedoresException("No existen Proveedores registrados para realizar el alta");
        }
	}
	

	public int obtenerCantInscriptos(String nickname, String nombreSalida) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Turista turista = mUsr.obtenerTurista(nickname);
		return turista.cantTuristasInscripcion(nombreSalida);
	}

	
	public DTActividad[] listarActividadesTuristicasConfirmadasProveedor(String nickname) throws NoExistenActividadesException {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Proveedor prov = (Proveedor) mUsr.obtenerUsuario(nickname);
		DTActividad[] acts = prov.listarActividadesTuristicasConfirmadas();
		return acts;
	}
	
	public String[] listarActividadesTuristicasProveedorkevin(String string) {
		ManejadorUsuarios mDep = ManejadorUsuarios.getinstance();
		Proveedor prov = (Proveedor) mDep.obtenerUsuario(string);
		List<DTActividad> list = prov.listarActividadesTuristicas();
		DTActividad[] targetArray = list.toArray(new DTActividad[0]);
		String[] devolver = new String[targetArray.length];
		for (int i = 0; i < targetArray.length; i++) {
			devolver[i] = targetArray[i].getNombre();
		}
		return devolver;
	}
	
	@Override
	public String[] listarTuristas() throws NoExistenUsuariosException {
		ManejadorUsuarios mDep = ManejadorUsuarios.getinstance();
		Map<String, Turista> deps = mDep.getTuristas();
		if (!deps.isEmpty()) {
            Collection<Turista> turis = deps.values();
            Object[] turistas = turis.toArray();
            Turista[] depComp = new Turista[turistas.length];
            String[] devolver = new String[turistas.length];
            for (int i = 0; i < turistas.length; i++) {
                depComp[i] = (Turista) turistas[i];
                devolver[i] = depComp[i].getNickname();
            }
	        return devolver;
        } else {
        	throw new NoExistenUsuariosException("No existen Turistas registrados para realizar la inscripcion");
        }
	}
	
	public String[] listarUsuarios() throws NoExistenUsuariosException {
		ManejadorUsuarios mDep = ManejadorUsuarios.getinstance();
		Map<String, Usuario> deps = mDep.getUsuarios();
		if (!deps.isEmpty()) {
            Collection<Usuario> depas = deps.values();
            Object[] departamentos = depas.toArray();
            Usuario[] depComp = new Usuario[departamentos.length];
            String[] devolver = new String[departamentos.length];
            for (int i = 0; i < departamentos.length; i++) {
                depComp[i] = (Usuario) departamentos[i];
                devolver[i] = depComp[i].getNickname();
            }
	        return devolver;
        } else {
        	throw new NoExistenUsuariosException("No existen usuarios registrados para modificar");
        }		
	}
	
	public DTUsuario obtenerDatosUsuario(String nickname) {
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuario(nickname);
		return usr.getDatosUsuario();
	}
	
	public DTUsuario obtenerDatosUsuarioEmail(String email) {
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuarioEmail(email);
		return usr.getDatosUsuario();
	}
	
	public void modificarNombreUsuario(String nombre, String nickname) {
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuario(nickname);
		usr.modificarNombre(nombre);
	}
	public void modificarApellidoUsuario(String apellido, String nickname) {
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuario(nickname);
		usr.modificarApellido(apellido);
	}
	public void modificarNacimientoUsuario(LocalDate fecha, String nickname) {
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuario(nickname);
		usr.modificarNacimiento(fecha);
	}
	
	public boolean existeNickname(String nickname) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		return mUsr.existeNickname(nickname);
	}
	
	public boolean existeEmail(String email) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		return mUsr.existeEmail(email);
	}
	
	public void darAltaProveedor(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String descripcion, String link, String password) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Proveedor prov = new Proveedor(nickname, nombre, apellido, email, fechaNac, descripcion, link, password);
		mUsr.darAltaProveedor(prov);
	}
	
	public void darAltaTurista(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String nacionalidad, String password) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Turista tur = new Turista(nickname, nombre, apellido, email, fechaNac, nacionalidad, password);
		mUsr.darAltaTurista(tur);
	}
	
	public DTTurista obtenerDatosTurista(String nickname) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		return mUsr.obtenerDatosTurista(nickname);
	}
	
	public DTProveedor obtenerDatosProveedor(String nickname) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		return mUsr.obtenerDatosProveedor(nickname);
	}
	
	public boolean existeTurista(String nickname) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		return mUsr.existeTurista(nickname);
	}
	
	public boolean existeProveedor(String nickname) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		return mUsr.existeProveedor(nickname);
	}
	
	public String[] listarSalidasTurista(String nickname) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Turista tur = mUsr.obtenerTurista(nickname);
		return tur.listarSalidasTurista();
	}
	
	public String[] listarActividadesTuristicasProveedor(String nickname) {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Proveedor prov = mUsr.obtenerProveedor(nickname);
		return prov.listarNombresActividadesTuristicas();
	}
	
	public void setURL(String nickname, String URL) {
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuario(nickname);
		usr.setUrlImagen(URL);
	}

	public String getURL(String nickname) {
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuario(nickname);
		return usr.getUrlImagen();
	}
	
	public String[] listaractividadesFavoritas(String nickname) throws UsuarioSinActividadesFavoritasException{
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuario(nickname);
		return usr.listaractividadesFavoritas();
	}
	
	public void agregarActividadFavorita(String act, String nickname) {
		ManejadorActividadT mAT= ManejadorActividadT.getinstance();
		ActividadAT activi = mAT.obtenerActividad(act);
		activi.agregarFavorito();
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuario(nickname);
		usr.agregarActFav(activi);
	}
	
	public void eliminarActividadFavorita(String act, String nickname) {
		ManejadorActividadT mAT= ManejadorActividadT.getinstance();
		ActividadAT activi = mAT.obtenerActividad(act);
		activi.eliminarFavorito();
		ManejadorUsuarios mUsuarios = ManejadorUsuarios.getinstance();
		Usuario usr = mUsuarios.obtenerUsuario(nickname);
		usr.eliminarActFav(activi);
	}
	
	public String[] listarSeguidoresUsuario(String nickname) throws UsuarioSinSeguidoresException {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Usuario usr = mUsr.obtenerUsuario(nickname);
		return usr.listarSeguidores();
	}
	
	public String[] listarSeguidosUsuario(String nickname) throws UsuarioSinSeguidosException {
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Usuario usr = mUsr.obtenerUsuario(nickname);
		return usr.listarSeguidos();
	}
	
	public void agregarSeguidorYSeguido(String usrQueSigue, String usrSeguido) { // si 1 sigue a 2, agregar 1 a la lista de seguidores de 2 y 2 a la lista de seguidos de 1
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Usuario usr1 = mUsr.obtenerUsuario(usrQueSigue);
		Usuario usr2 = mUsr.obtenerUsuario(usrSeguido);
		usr2.agregarSeguidor(usr1);
		usr1.agregarSeguido(usr2);
	}
	
	public void eliminarSeguidor(String usrQueDeja, String usrDejado) { // si 1 deja de seguir a 2, eliminar 1 de los seguidores de 2 y eliminar 2 de los seguidos de 1
		ManejadorUsuarios mUsr = ManejadorUsuarios.getinstance();
		Usuario usr1 = mUsr.obtenerUsuario(usrQueDeja);
		Usuario usr2 = mUsr.obtenerUsuario(usrDejado);
		usr2.eliminarSeguidor(usr1);
		usr1.eliminarSeguido(usr2);
	}
}
