package logica;

import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;


public class ManejadorUsuarios {
	private Map<String, Usuario> usuarios;
	private Map<String, Proveedor> proveedores;
	private Map<String, Turista> turistas;
	private static ManejadorUsuarios instancia = null;
	
	private ManejadorUsuarios() {
        usuarios = new HashMap<String, Usuario>();
        proveedores = new HashMap<String, Proveedor>();
        turistas = new HashMap<String, Turista>();
    }

    public static ManejadorUsuarios getinstance() {
        if (instancia == null) {
            instancia = new ManejadorUsuarios();
        }
        return instancia;
    }
    
    public boolean existeProveedor(String nickName) {
		return ((Proveedor) proveedores.get(nickName)) != null;
	}
    
    public Map<String, Proveedor> getProveedores() {
    	return proveedores;
    }
    public Map<String, Usuario> getUsuarios() {
    	return usuarios;
    }

	public Usuario obtenerUsuario(String usr) {
		return ((Usuario) usuarios.get(usr));
	}
	
	public Usuario obtenerUsuarioEmail(String email) {
		boolean encontrado = false;
		Usuario usr = null;
		Iterator<Map.Entry<String, Usuario>> iterador = usuarios.entrySet().iterator();  //REVISAR
		while ((!encontrado) && (iterador.hasNext())) {
			Map.Entry<String, Usuario> pair = iterador.next();
		    String email2 = pair.getValue().getEmail().toString();
			if (email2.equals(email)) {
		    	encontrado = true;
		    	usr = pair.getValue();
		    }
		}
		return usr;
	}

	public Map<String, Turista> getTuristas() {
		return turistas;
	}

	public boolean existeNickname(String nickname) {
		return usuarios.containsKey(nickname);
	}
	
	public boolean existeEmail(String email) {
		boolean encontrado = false;
		Iterator<Map.Entry<String, Usuario>> iterador = usuarios.entrySet().iterator();  //REVISAR
		while ((!encontrado) && (iterador.hasNext())) {
			Map.Entry<String, Usuario> pair = iterador.next();
		    String email2 = pair.getValue().getEmail().toString();
			if (email2.equals(email)) {
		    	encontrado = true;
		    }
		}
		return encontrado;
	}

	public void darAltaProveedor(Proveedor prov) {
		proveedores.put(prov.getNickname(), prov);
		usuarios.put(prov.getNickname(), (Usuario) prov);
	}

	public void darAltaTurista(Turista tur) {
		turistas.put(tur.getNickname(), tur);
		usuarios.put(tur.getNickname(), (Usuario) tur);
	}

	public DTTurista obtenerDatosTurista(String nickname) {
		Turista tur = turistas.get(nickname);
		DTTurista dtTur = new DTTurista(tur.getNickname(), tur.getNombre(), tur.getApellido(), tur.getEmail(), tur.getFechaNacimiento(), tur.getNacionalidad(), tur.getPassword());
		return dtTur;
	}

	public DTProveedor obtenerDatosProveedor(String nickname) {
		Proveedor prov = proveedores.get(nickname);
		DTProveedor dtProv = new DTProveedor(prov.getNickname(), prov.getNombre(), prov.getApellido(), prov.getEmail(), prov.getFechaNacimiento(), prov.getDescripcion(), prov.getLinkWeb(), prov.getPassword());
		return dtProv;
	}

	public Turista obtenerTurista(String nickname) {
		return turistas.get(nickname);
	}
	
	public Proveedor obtenerProveedor(String nickname) {
		return proveedores.get(nickname);
	}

	public boolean existeTurista(String nickname) {
		return ((Turista) turistas.get(nickname)) != null;
	}
    
}
