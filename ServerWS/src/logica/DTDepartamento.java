package logica;

public class DTDepartamento {
	private String nombre;
	private String descripcion;
	private String url;
	
	public DTDepartamento(String nombre, String descripcion, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getURL() {
		return url;
	}
}