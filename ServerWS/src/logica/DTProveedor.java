package logica;

import java.time.LocalDate;

public class DTProveedor extends DTUsuario {
	
	private String descripcion;
	private String linkWeb;
	
	public DTProveedor(String nickname, String nombre, String apellido, String email, LocalDate fechaNac,
			String descripcion, String link, String password) {
		super(nickname, nombre, apellido, email, fechaNac, password);
		this.descripcion = descripcion;
		linkWeb = link;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getLink() {
		return linkWeb;
	}
}
