package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import excepciones.NoExistenActividadesEnElPaqueteException;

public class PaqueteAT {
	private String nombre;
	private String descripcion;
	private LocalDate fechaAlta;
	private int descuento;
	private int periodoValidez;
	private int costoPorPersona;
	private String urlImagen = "";
	private Map<String, ActividadAT> actividades = new HashMap<String, ActividadAT>();
	private List<CompraPaquete> comprasPaquetes = new ArrayList<CompraPaquete>();
	
	public PaqueteAT(String nombre, String descripcion, int descuento, int periodo, LocalDate fecha) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = descuento;
		this.periodoValidez = periodo;
		this.fechaAlta = fecha;
	}
	
	public DTPaqueteAT getDatosPaquete() {
		DTPaqueteAT dtPaquete = new DTPaqueteAT(this.nombre, this.descripcion, this.periodoValidez, this.descuento, this.costoPorPersona, this.fechaAlta);
		return dtPaquete;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public int getCostoPorPersona() {
		return costoPorPersona;
	}
	public void setCostoPorPersona(int costoPorPersona) {
		this.costoPorPersona = costoPorPersona;
	}

	public int getPeridoValidez() {
		return periodoValidez;
	}

	public void setPeridoValidez(int peridoValidez) {
		this.periodoValidez = peridoValidez;
	}
	
	public String [] listarActividades() throws NoExistenActividadesEnElPaqueteException {
		if (!actividades.isEmpty()) {
			Collection<ActividadAT> acts = actividades.values();
			Object[] actividades = acts.toArray();
			String[] result = new String[actividades.length];
			for (int i = 0; i < actividades.length; i++) {
				ActividadAT act = (ActividadAT) actividades[i];
				result[i] = act.getNombre();
			}
			return result;				
		} else {
        	throw new NoExistenActividadesEnElPaqueteException("No existen actividades registradas para el paquete");
        }
	}
	
	public String[] listarCategoriasPaquete(PaqueteAT paq) {
		String[] acts;
		try {
			acts = paq.listarActividades();
			ManejadorActividadT MAct= ManejadorActividadT.getinstance();
			ArrayList<String> cats = new ArrayList<String>();
			for(int i = 0; i < acts.length; i++) {
				DTActividad acti = MAct.obtenerDatosActividad(acts[i]);
				String [] catsAux=acti.getCategoria();
				for(int j = 0; j < catsAux.length; j++) {
					if(!cats.equals(catsAux[j])) {
						cats.add(catsAux[j]);
					}
				}
			}
			String[] devolver = new String[cats.size()];
			Iterator<String> ite = cats.iterator();
			int indice=0;
			while(ite.hasNext()) {
				devolver[indice]=ite.next();
				indice++;
			}
			return devolver;
		} catch (NoExistenActividadesEnElPaqueteException e) {
			return null;
		}
	}
	
	public void agregarActividad(ActividadAT act) {
		actividades.put(act.getNombre(), act);
	}

	public boolean fueComprado() {
		return !comprasPaquetes.isEmpty();
	}

	public String getURLImagen() {
		return urlImagen;
	}

	public void setURLImagen(String uRLImagen) {
		urlImagen = uRLImagen;
	}
}

