package servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataSalida {
	private String nombre;
	private int cantMaxT;
	private int diaSalida;
    private int mesSalida;
    private int anioSalida;
    private int diaAlta;
    private int mesAlta;
    private int anioAlta;
    private int hora;
	private int minuto;
	private String lugar;
	private int cantTotalTuristas;
	
	public DataSalida() {}
	
	public DataSalida(String nombre, int cantmaxt, int anioSalida, int mesSalida, int diaSalida, int anioAlta, int mesAlta, int diaAlta, int hora, int minuto, String lugar, int canttotalturistas) {
		this.setNombre(nombre);
		this.setCantMaxT(cantmaxt);
		this.setDiaSalida(diaSalida);
		this.setMesSalida(mesSalida);
		this.setAnioSalida(anioSalida);
		this.setDiaAlta(diaAlta);
		this.setMesAlta(mesAlta);
		this.setAnioAlta(anioAlta);
		this.setHora(hora);
		this.setMinuto(minuto);
		this.setLugar(lugar);
		this.setCantTotalTuristas(canttotalturistas);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantMaxT() {
		return cantMaxT;
	}

	public void setCantMaxT(int cantMaxT) {
		this.cantMaxT = cantMaxT;
	}

	public int getDiaSalida() {
        return diaSalida;
    }

    public void setDiaSalida(int dia) {
        this.diaSalida = dia;
    }

    public int getMesSalida() {
        return mesSalida;
    }

    public void setMesSalida(int mes) {
        this.mesSalida = mes;
    }

    public int getAnioSalida() {
        return anioSalida;
    }

    public void setAnioSalida(int anio) {
        this.anioSalida = anio;
    }

    public int getDiaAlta() {
        return diaAlta;
    }

    public void setDiaAlta(int dia) {
        this.diaAlta = dia;
    }

    public int getMesAlta() {
        return mesAlta;
    }

    public void setMesAlta(int mes) {
        this.mesAlta = mes;
    }

    public int getAnioAlta() {
        return anioAlta;
    }

    public void setAnioAlta(int anio) {
        this.anioAlta = anio;
    }

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public int getCantTotalTuristas() {
		return cantTotalTuristas;
	}

	public void setCantTotalTuristas(int cantTotalTuristas) {
		this.cantTotalTuristas = cantTotalTuristas;
	}
}

