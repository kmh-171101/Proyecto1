package logica;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import excepciones.UsuarioSinActividadesFavoritasException;
import excepciones.UsuarioSinSeguidoresException;
import excepciones.UsuarioSinSeguidosException;

public class Usuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private LocalDate fechaNacimiento;	
	private String password;
	private String urlImagen = "";
	private Map<String, ActividadAT> actividadesFav = new HashMap<String, ActividadAT>();
	private Map<String, Usuario> seguidos = new HashMap<String, Usuario>();
	private Map<String, Usuario> seguidores = new HashMap<String, Usuario>();
	
	
	public Usuario(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String password) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		fechaNacimiento = fechaNac;
		setPassword(password);
	}
	
	public String getNickname() {  
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void modificarNombre(String nombre) {   //misma operacion que setNombre()
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void modificarApellido(String apellido) {   //misma operacion que setApellido()
		this.apellido = apellido;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void modificarNacimiento(LocalDate fecha) {  //misma operacion que setFechaNacimiento()
		fechaNacimiento = fecha;
	}

	public DTUsuario getDatosUsuario() {
		return (new DTUsuario(nickname, nombre, apellido, email, fechaNacimiento, password));		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	
	public void agregarActFav(ActividadAT act) {
		actividadesFav.put(act.getNombre(), act);
	}
	
	public String[] listaractividadesFavoritas() throws UsuarioSinActividadesFavoritasException {
		if (!actividadesFav.isEmpty()) {
			Collection<ActividadAT> actsFav = actividadesFav.values();
			Object[] acts = actsFav.toArray();
			String[] result = new String[acts.length];
			for (int i = 0; i < acts.length; i++) {
				ActividadAT atf = (ActividadAT) acts[i];
				result[i] = atf.getNombre();
			}
			return result;				
		} else {
        	throw new UsuarioSinActividadesFavoritasException("El usuario seleccionado no tiene actividades favoritas");
        }
	}
	
	public void eliminarActFav(ActividadAT act) {
		actividadesFav.remove(act.getNombre());
	}
	
	public void agregarSeguidor(Usuario usr) {
		seguidores.put(usr.getNickname(), usr);
	}
	
	public String[] listarSeguidores() throws UsuarioSinSeguidoresException {
		if (!seguidores.isEmpty()) {
			Collection<Usuario> usr = seguidores.values();
			Object[] seg = usr.toArray();
			String[] result = new String[seg.length];
			for (int i = 0; i < seg.length; i++) {
				Usuario user = (Usuario) seg[i];
				result[i] = user.getNickname();
			}
			return result;				
		} else {
        	throw new UsuarioSinSeguidoresException("El usuario seleccionado no tiene seguidores");
        }
	}
	
	public void agregarSeguido(Usuario usr) {
		seguidos.put(usr.getNickname(), usr);
	}
	
	public void eliminarSeguidor(Usuario usr) {
		seguidores.remove(usr.getNickname());
	}
	public void eliminarSeguido(Usuario usr) {
		seguidos.remove(usr.getNickname());
	}
	
	public String[] listarSeguidos() throws UsuarioSinSeguidosException {
		if (!seguidos.isEmpty()) {
			Collection<Usuario> usr = seguidos.values();
			Object[] seg = usr.toArray();
			String[] result = new String[seg.length];
			for (int i = 0; i < seg.length; i++) {
				Usuario user = (Usuario) seg[i];
				result[i] = user.getNickname();
			}
			return result;				
		} else {
        	throw new UsuarioSinSeguidosException("El usuario seleccionado no tiene seguidos");
        }
	}
}