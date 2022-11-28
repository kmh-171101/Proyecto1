package logica;

import java.time.LocalDate;

public class CompraPaquete {
	private LocalDate fechaCompra;
	private int cantTuristas;
	private int costoTotal;
	private LocalDate vencimiento;
	
	public CompraPaquete(LocalDate fechaComp, int cantTur, int costoTot, LocalDate venci) {
		this.fechaCompra = fechaComp;
		this.cantTuristas = cantTur;
		this.costoTotal = costoTot;
		this.vencimiento = venci;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public int getCantTuristas() {
		return cantTuristas;
	}

	public int getCostoTotal() {
		return costoTotal;
	}

	public LocalDate getVencimiento() {
		return vencimiento;
	}
	
}
