package logica;
import java.time.LocalDate;

import excepciones.NoExistenActividadesException;
import excepciones.NoExistenProveedoresException;
import excepciones.NoExistenUsuariosException;
import excepciones.UsuarioSinActividadesFavoritasException;
import excepciones.UsuarioSinSeguidoresException;
import excepciones.UsuarioSinSeguidosException;

public interface IUsuario {
	public abstract String[] listarProveedores() throws NoExistenProveedoresException;

	public abstract String[] listarUsuarios() throws NoExistenUsuariosException;
	
	public abstract String[] listarSalidasTurista(String nickname);
	
	public abstract DTUsuario obtenerDatosUsuario(String nickname);
	
	public abstract DTUsuario obtenerDatosUsuarioEmail(String email);
	
	public abstract void modificarNombreUsuario(String nombre, String nickname);

	public abstract void modificarApellidoUsuario(String apellido, String nickname);

	public abstract void modificarNacimientoUsuario(LocalDate fecha, String nickname);

	public abstract String[] listarTuristas() throws NoExistenUsuariosException;

	public abstract boolean existeNickname(String nickname);
	
	public abstract boolean existeEmail(String email);
	
	public abstract void darAltaProveedor(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String descripcion, String link, String password);
	
	public abstract void darAltaTurista(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String nacionalidad, String password);
	
	public abstract DTTurista obtenerDatosTurista(String nickname);
	
	public abstract DTProveedor obtenerDatosProveedor(String nickname);

	public abstract boolean existeProveedor(String nickname);
	
	public abstract String[] listarActividadesTuristicasProveedor(String nickname);
	
	public abstract DTActividad[] listarActividadesTuristicasConfirmadasProveedor(String nickname) throws NoExistenActividadesException;

	public abstract String[] listarActividadesTuristicasProveedorkevin(String string);
	
	public abstract void setURL(String nickname, String URL);
	
	public abstract String getURL(String nickname);
	
	public abstract void agregarActividadFavorita(String act, String nickname);
	
	public abstract void eliminarActividadFavorita(String act, String nickname);

	public abstract String[] listarSeguidoresUsuario(String nickname) throws UsuarioSinSeguidoresException;
	
	public abstract String[] listarSeguidosUsuario(String nickname) throws UsuarioSinSeguidosException;
	
	public abstract void agregarSeguidorYSeguido(String usrQueSigue, String usrSeguido);
	
	public abstract void eliminarSeguidor(String usrQueDeja, String usrDejado);
	
	public abstract String[] listaractividadesFavoritas(String nickname) throws UsuarioSinActividadesFavoritasException;
	
	public abstract boolean existeTurista(String nickname);
	
	public abstract int obtenerCantInscriptos(String nickname, String nombreSalida);
}
