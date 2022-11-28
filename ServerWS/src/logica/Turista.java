package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Turista extends Usuario {
	
	private String nacionalidad;
	//private Map<String, DTActividadSalidas> actividadesSalidas = new HashMap<String, DTActividadSalidas>();
	//private ArrayList<CompraPaquete> comprasPaquete = new ArrayList<CompraPaquete>();
	private List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	
	public Turista(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String password) {
		super(nickname, nombre, apellido, email, fechaNac, password);
	}

	public Turista(String nickname, String nombre, String apellido, String email, LocalDate fechaNac,
			String nacionalidad, String password) {
		super(nickname, nombre, apellido, email, fechaNac, password);
		this.nacionalidad = nacionalidad;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String[] listarSalidasTurista() {
        String[] devolver = new String[inscripciones.size()];
        for (int i = 0; i < inscripciones.size(); i++) {
            devolver[i] = inscripciones.get(i).obtenerNombreSalida();
        }
        return devolver;
	}
	
	
	public boolean estaInscripto(String nombreSalida) {
		if (inscripciones.isEmpty()) {
			return false;
		}
		Inscripcion[] insc = inscripciones.toArray(new Inscripcion[0]);
		boolean esta = false;
		int largo = 0;
		while (!esta && largo < insc.length) {
			esta = (insc[largo].getSalidaAT().getNombre().equals(nombreSalida));
			largo++;
		}
		return esta;
	}
	//...

	public void agregarInscripcion(Inscripcion nueva) {
		inscripciones.add(nueva);
	}

	public DTSalidasAT obtenerDatosSalida(String nombre) {
		boolean encontrada = false;
		int indice = 0;
		while (!inscripciones.get(indice).obtenerNombreSalida().equals(nombre)) {
			indice++;
		}
		return inscripciones.get(indice).obtenerDatosSalida();
	}
	
	public int cantTuristasInscripcion(String nombreSalida) {   //El Turista debe o no tener ninguna inscripcion o una seguro de la salida que se da
		if (inscripciones.isEmpty()) {
			return 0;
		}
		Inscripcion[] insc = inscripciones.toArray(new Inscripcion[0]);
		boolean encontrada = false;
		int largo = 0;
		while (!encontrada) {
			encontrada = (insc[largo].getSalidaAT().getNombre().equals(nombreSalida));
			largo++;
		}
		largo = largo - 1;
		return insc[largo].getCantTuristas();
	}
}
