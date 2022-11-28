package logica;

import java.time.LocalDate;

public class Inscripcion {
	private LocalDate fechaRealizada;
	private int costoTotal;
	private int cantTuristas;
	private Turista turista;
	private SalidasAT salidaAT;
	
	public Inscripcion(LocalDate now, int integrantes, int costoTotal2, Turista tur, SalidasAT sal) {
		fechaRealizada = now;
		costoTotal = costoTotal2;
		cantTuristas = integrantes;
		turista = tur;
		salidaAT = sal;
	}
	public LocalDate getFechaRealizada() {
		return fechaRealizada;
	}
	public void setFechaRealizada(LocalDate fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}
	public int getCostoTotal() {
		return costoTotal;
	}
	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}
	public int getCantTuristas() {
		return cantTuristas;
	}
	public void setCantTuristas(int cantTuristas) {
		this.cantTuristas = cantTuristas;
	}
	public Turista getTurista() {
		return turista;
	}
	public void setTurista(Turista turista) {
		this.turista = turista;
	}
	public SalidasAT getSalidaAT() {
		return salidaAT;
	}
	public void setSalidaAT(SalidasAT salidaAT) {
		this.salidaAT = salidaAT;
	}
	public String obtenerNombreSalida() {
		return salidaAT.getNombre();
	}
	public DTSalidasAT obtenerDatosSalida() {
		
		DTSalidasAT DTSal = new DTSalidasAT(salidaAT.getNombre(), salidaAT.getCantMax(), salidaAT.getFechaAlta(), 
				salidaAT.getFecha(), salidaAT.getHora(), salidaAT.getLugar(), salidaAT.getCantTotalTuristas());
		return DTSal;
	}
	
}
