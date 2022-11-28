package servidor;


import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataGeneric {
	
	private String nombre;
	private ArrayList<String> arr=new ArrayList<String>();
	
	public DataGeneric() {
	}
	
	public DataGeneric(String nombre, String[] arreglo) {
		this.nombre=nombre;
		for(int i=0;i<arreglo.length;i++) {
			this.arr.add(arreglo[i]);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getArr() {
		return arr;
	}

	public void setArr(ArrayList<String> arr) {
		this.arr = arr;
	}

}
