
package servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataSalida complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataSalida">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantMaxT" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="diaSalida" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="mesSalida" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="anioSalida" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="diaAlta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="mesAlta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="anioAlta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="hora" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="minuto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="lugar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantTotalTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSalida", propOrder = {
    "nombre",
    "cantMaxT",
    "diaSalida",
    "mesSalida",
    "anioSalida",
    "diaAlta",
    "mesAlta",
    "anioAlta",
    "hora",
    "minuto",
    "lugar",
    "cantTotalTuristas"
})
public class DataSalida {

    protected String nombre;
    protected int cantMaxT;
    protected int diaSalida;
    protected int mesSalida;
    protected int anioSalida;
    protected int diaAlta;
    protected int mesAlta;
    protected int anioAlta;
    protected int hora;
    protected int minuto;
    protected String lugar;
    protected int cantTotalTuristas;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad cantMaxT.
     * 
     */
    public int getCantMaxT() {
        return cantMaxT;
    }

    /**
     * Define el valor de la propiedad cantMaxT.
     * 
     */
    public void setCantMaxT(int value) {
        this.cantMaxT = value;
    }

    /**
     * Obtiene el valor de la propiedad diaSalida.
     * 
     */
    public int getDiaSalida() {
        return diaSalida;
    }

    /**
     * Define el valor de la propiedad diaSalida.
     * 
     */
    public void setDiaSalida(int value) {
        this.diaSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad mesSalida.
     * 
     */
    public int getMesSalida() {
        return mesSalida;
    }

    /**
     * Define el valor de la propiedad mesSalida.
     * 
     */
    public void setMesSalida(int value) {
        this.mesSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad anioSalida.
     * 
     */
    public int getAnioSalida() {
        return anioSalida;
    }

    /**
     * Define el valor de la propiedad anioSalida.
     * 
     */
    public void setAnioSalida(int value) {
        this.anioSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad diaAlta.
     * 
     */
    public int getDiaAlta() {
        return diaAlta;
    }

    /**
     * Define el valor de la propiedad diaAlta.
     * 
     */
    public void setDiaAlta(int value) {
        this.diaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad mesAlta.
     * 
     */
    public int getMesAlta() {
        return mesAlta;
    }

    /**
     * Define el valor de la propiedad mesAlta.
     * 
     */
    public void setMesAlta(int value) {
        this.mesAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad anioAlta.
     * 
     */
    public int getAnioAlta() {
        return anioAlta;
    }

    /**
     * Define el valor de la propiedad anioAlta.
     * 
     */
    public void setAnioAlta(int value) {
        this.anioAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad hora.
     * 
     */
    public int getHora() {
        return hora;
    }

    /**
     * Define el valor de la propiedad hora.
     * 
     */
    public void setHora(int value) {
        this.hora = value;
    }

    /**
     * Obtiene el valor de la propiedad minuto.
     * 
     */
    public int getMinuto() {
        return minuto;
    }

    /**
     * Define el valor de la propiedad minuto.
     * 
     */
    public void setMinuto(int value) {
        this.minuto = value;
    }

    /**
     * Obtiene el valor de la propiedad lugar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Define el valor de la propiedad lugar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugar(String value) {
        this.lugar = value;
    }

    /**
     * Obtiene el valor de la propiedad cantTotalTuristas.
     * 
     */
    public int getCantTotalTuristas() {
        return cantTotalTuristas;
    }

    /**
     * Define el valor de la propiedad cantTotalTuristas.
     * 
     */
    public void setCantTotalTuristas(int value) {
        this.cantTotalTuristas = value;
    }

}
