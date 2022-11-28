package logica;

import java.time.LocalDate;

public class DTTurista extends DTUsuario {
	
	private String nacionalidad;
	
	public DTTurista(String nickname, String nombre, String apellido, String email, LocalDate fechaNac,
			String nacionalidad, String password) {
		super(nickname, nombre, apellido, email, fechaNac, password);
		this.nacionalidad = nacionalidad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}
}
