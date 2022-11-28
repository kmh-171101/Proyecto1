package logica;

import java.time.LocalDate;

public class DTUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private LocalDate fechaNacimiento;	
	private String password;
	
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
	
	public void modificarNacimiento(LocalDate fechaNac) {  //misma operacion que setFechaNacimiento()
		fechaNacimiento = fechaNac;
	}
	public String getPassword() {
		return password;
	}
	
	public DTUsuario(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String password) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		fechaNacimiento = fechaNac;
		this.password = password;
	}

	@Override
	public String toString() {
		return "DTUsuario [Nickname=" + nickname + ", Nombre=" + nombre + ", Apellido=" + apellido + ", Email=" + email
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
}
