
package servidor;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servidor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExcedeCapacidadException_QNAME = new QName("http://servidor/", "ExcedeCapacidadException");
    private final static QName _ExisteActividadException_QNAME = new QName("http://servidor/", "ExisteActividadException");
    private final static QName _IOException_QNAME = new QName("http://servidor/", "IOException");
    private final static QName _NoExisteDepartamentoException_QNAME = new QName("http://servidor/", "NoExisteDepartamentoException");
    private final static QName _NoExisteProveedorException_QNAME = new QName("http://servidor/", "NoExisteProveedorException");
    private final static QName _NoExistenActividadesEnDepartamentoException_QNAME = new QName("http://servidor/", "NoExistenActividadesEnDepartamentoException");
    private final static QName _NoExistenActividadesException_QNAME = new QName("http://servidor/", "NoExistenActividadesException");
    private final static QName _NoExistenCategoriasException_QNAME = new QName("http://servidor/", "NoExistenCategoriasException");
    private final static QName _NoExistenDepartamentosException_QNAME = new QName("http://servidor/", "NoExistenDepartamentosException");
    private final static QName _NoExistenPaquetesEnLaActividadException_QNAME = new QName("http://servidor/", "NoExistenPaquetesEnLaActividadException");
    private final static QName _NoExistenSalidasEnActividadException_QNAME = new QName("http://servidor/", "NoExistenSalidasEnActividadException");
    private final static QName _NoExistenUsuariosException_QNAME = new QName("http://servidor/", "NoExistenUsuariosException");
    private final static QName _NoHaySalidasDisponiblesException_QNAME = new QName("http://servidor/", "NoHaySalidasDisponiblesException");
    private final static QName _YaTieneInscripcionAEstaSalidaException_QNAME = new QName("http://servidor/", "YaTieneInscripcionAEstaSalidaException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExcedeCapacidadException }
     * 
     * @return
     *     the new instance of {@link ExcedeCapacidadException }
     */
    public ExcedeCapacidadException createExcedeCapacidadException() {
        return new ExcedeCapacidadException();
    }

    /**
     * Create an instance of {@link ExisteActividadException }
     * 
     * @return
     *     the new instance of {@link ExisteActividadException }
     */
    public ExisteActividadException createExisteActividadException() {
        return new ExisteActividadException();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     * @return
     *     the new instance of {@link IOException }
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link NoExisteDepartamentoException }
     * 
     * @return
     *     the new instance of {@link NoExisteDepartamentoException }
     */
    public NoExisteDepartamentoException createNoExisteDepartamentoException() {
        return new NoExisteDepartamentoException();
    }

    /**
     * Create an instance of {@link NoExisteProveedorException }
     * 
     * @return
     *     the new instance of {@link NoExisteProveedorException }
     */
    public NoExisteProveedorException createNoExisteProveedorException() {
        return new NoExisteProveedorException();
    }

    /**
     * Create an instance of {@link NoExistenActividadesEnDepartamentoException }
     * 
     * @return
     *     the new instance of {@link NoExistenActividadesEnDepartamentoException }
     */
    public NoExistenActividadesEnDepartamentoException createNoExistenActividadesEnDepartamentoException() {
        return new NoExistenActividadesEnDepartamentoException();
    }

    /**
     * Create an instance of {@link NoExistenActividadesException }
     * 
     * @return
     *     the new instance of {@link NoExistenActividadesException }
     */
    public NoExistenActividadesException createNoExistenActividadesException() {
        return new NoExistenActividadesException();
    }

    /**
     * Create an instance of {@link NoExistenCategoriasException }
     * 
     * @return
     *     the new instance of {@link NoExistenCategoriasException }
     */
    public NoExistenCategoriasException createNoExistenCategoriasException() {
        return new NoExistenCategoriasException();
    }

    /**
     * Create an instance of {@link NoExistenDepartamentosException }
     * 
     * @return
     *     the new instance of {@link NoExistenDepartamentosException }
     */
    public NoExistenDepartamentosException createNoExistenDepartamentosException() {
        return new NoExistenDepartamentosException();
    }

    /**
     * Create an instance of {@link NoExistenPaquetesEnLaActividadException }
     * 
     * @return
     *     the new instance of {@link NoExistenPaquetesEnLaActividadException }
     */
    public NoExistenPaquetesEnLaActividadException createNoExistenPaquetesEnLaActividadException() {
        return new NoExistenPaquetesEnLaActividadException();
    }

    /**
     * Create an instance of {@link NoExistenSalidasEnActividadException }
     * 
     * @return
     *     the new instance of {@link NoExistenSalidasEnActividadException }
     */
    public NoExistenSalidasEnActividadException createNoExistenSalidasEnActividadException() {
        return new NoExistenSalidasEnActividadException();
    }

    /**
     * Create an instance of {@link NoExistenUsuariosException }
     * 
     * @return
     *     the new instance of {@link NoExistenUsuariosException }
     */
    public NoExistenUsuariosException createNoExistenUsuariosException() {
        return new NoExistenUsuariosException();
    }

    /**
     * Create an instance of {@link NoHaySalidasDisponiblesException }
     * 
     * @return
     *     the new instance of {@link NoHaySalidasDisponiblesException }
     */
    public NoHaySalidasDisponiblesException createNoHaySalidasDisponiblesException() {
        return new NoHaySalidasDisponiblesException();
    }

    /**
     * Create an instance of {@link YaTieneInscripcionAEstaSalidaException }
     * 
     * @return
     *     the new instance of {@link YaTieneInscripcionAEstaSalidaException }
     */
    public YaTieneInscripcionAEstaSalidaException createYaTieneInscripcionAEstaSalidaException() {
        return new YaTieneInscripcionAEstaSalidaException();
    }

    /**
     * Create an instance of {@link DataUsuario }
     * 
     * @return
     *     the new instance of {@link DataUsuario }
     */
    public DataUsuario createDataUsuario() {
        return new DataUsuario();
    }

    /**
     * Create an instance of {@link DataSalida }
     * 
     * @return
     *     the new instance of {@link DataSalida }
     */
    public DataSalida createDataSalida() {
        return new DataSalida();
    }

    /**
     * Create an instance of {@link DataActividad }
     * 
     * @return
     *     the new instance of {@link DataActividad }
     */
    public DataActividad createDataActividad() {
        return new DataActividad();
    }

    /**
     * Create an instance of {@link DataGeneric }
     * 
     * @return
     *     the new instance of {@link DataGeneric }
     */
    public DataGeneric createDataGeneric() {
        return new DataGeneric();
    }

    /**
     * Create an instance of {@link DataGenericArray }
     * 
     * @return
     *     the new instance of {@link DataGenericArray }
     */
    public DataGenericArray createDataGenericArray() {
        return new DataGenericArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExcedeCapacidadException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExcedeCapacidadException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "ExcedeCapacidadException")
    public JAXBElement<ExcedeCapacidadException> createExcedeCapacidadException(ExcedeCapacidadException value) {
        return new JAXBElement<>(_ExcedeCapacidadException_QNAME, ExcedeCapacidadException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExisteActividadException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExisteActividadException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "ExisteActividadException")
    public JAXBElement<ExisteActividadException> createExisteActividadException(ExisteActividadException value) {
        return new JAXBElement<>(_ExisteActividadException_QNAME, ExisteActividadException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExisteDepartamentoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExisteDepartamentoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoExisteDepartamentoException")
    public JAXBElement<NoExisteDepartamentoException> createNoExisteDepartamentoException(NoExisteDepartamentoException value) {
        return new JAXBElement<>(_NoExisteDepartamentoException_QNAME, NoExisteDepartamentoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExisteProveedorException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExisteProveedorException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoExisteProveedorException")
    public JAXBElement<NoExisteProveedorException> createNoExisteProveedorException(NoExisteProveedorException value) {
        return new JAXBElement<>(_NoExisteProveedorException_QNAME, NoExisteProveedorException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExistenActividadesEnDepartamentoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExistenActividadesEnDepartamentoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoExistenActividadesEnDepartamentoException")
    public JAXBElement<NoExistenActividadesEnDepartamentoException> createNoExistenActividadesEnDepartamentoException(NoExistenActividadesEnDepartamentoException value) {
        return new JAXBElement<>(_NoExistenActividadesEnDepartamentoException_QNAME, NoExistenActividadesEnDepartamentoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExistenActividadesException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExistenActividadesException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoExistenActividadesException")
    public JAXBElement<NoExistenActividadesException> createNoExistenActividadesException(NoExistenActividadesException value) {
        return new JAXBElement<>(_NoExistenActividadesException_QNAME, NoExistenActividadesException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExistenCategoriasException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExistenCategoriasException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoExistenCategoriasException")
    public JAXBElement<NoExistenCategoriasException> createNoExistenCategoriasException(NoExistenCategoriasException value) {
        return new JAXBElement<>(_NoExistenCategoriasException_QNAME, NoExistenCategoriasException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExistenDepartamentosException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExistenDepartamentosException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoExistenDepartamentosException")
    public JAXBElement<NoExistenDepartamentosException> createNoExistenDepartamentosException(NoExistenDepartamentosException value) {
        return new JAXBElement<>(_NoExistenDepartamentosException_QNAME, NoExistenDepartamentosException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExistenPaquetesEnLaActividadException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExistenPaquetesEnLaActividadException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoExistenPaquetesEnLaActividadException")
    public JAXBElement<NoExistenPaquetesEnLaActividadException> createNoExistenPaquetesEnLaActividadException(NoExistenPaquetesEnLaActividadException value) {
        return new JAXBElement<>(_NoExistenPaquetesEnLaActividadException_QNAME, NoExistenPaquetesEnLaActividadException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExistenSalidasEnActividadException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExistenSalidasEnActividadException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoExistenSalidasEnActividadException")
    public JAXBElement<NoExistenSalidasEnActividadException> createNoExistenSalidasEnActividadException(NoExistenSalidasEnActividadException value) {
        return new JAXBElement<>(_NoExistenSalidasEnActividadException_QNAME, NoExistenSalidasEnActividadException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExistenUsuariosException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExistenUsuariosException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoExistenUsuariosException")
    public JAXBElement<NoExistenUsuariosException> createNoExistenUsuariosException(NoExistenUsuariosException value) {
        return new JAXBElement<>(_NoExistenUsuariosException_QNAME, NoExistenUsuariosException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoHaySalidasDisponiblesException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoHaySalidasDisponiblesException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoHaySalidasDisponiblesException")
    public JAXBElement<NoHaySalidasDisponiblesException> createNoHaySalidasDisponiblesException(NoHaySalidasDisponiblesException value) {
        return new JAXBElement<>(_NoHaySalidasDisponiblesException_QNAME, NoHaySalidasDisponiblesException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YaTieneInscripcionAEstaSalidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link YaTieneInscripcionAEstaSalidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "YaTieneInscripcionAEstaSalidaException")
    public JAXBElement<YaTieneInscripcionAEstaSalidaException> createYaTieneInscripcionAEstaSalidaException(YaTieneInscripcionAEstaSalidaException value) {
        return new JAXBElement<>(_YaTieneInscripcionAEstaSalidaException_QNAME, YaTieneInscripcionAEstaSalidaException.class, null, value);
    }

}
