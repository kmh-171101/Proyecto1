package servidor;
import logica.DTActividad;
import logica.DTProveedor;
import logica.DTSalidasAT;
import logica.DTTurista;
import logica.DTUsuario;
import logica.Fabrica;
import logica.IATYST;
import logica.IDepartamento;
import logica.IUsuario;
import excepciones.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;



@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {

	private Fabrica fabrica = Fabrica.getInstance();
    private Endpoint endpoint = null;
    private String urlPublicador="";
    private String urlImagenes="";
    //Constructor
    public Publicador(){}

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar(){
    	Properties properties= new Properties();
    	try {
			String finalmente=System.getProperty("user.home")+File.separator+".turismoUy"+File.separator+".properties";
    		File archivo=new File(finalmente);
    		properties.load(new FileInputStream(archivo));
			urlPublicador=properties.getProperty("urlPublicador");
			urlImagenes=properties.getProperty("urlImagenes");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        endpoint = Endpoint.publish(urlPublicador, this);
    }
    
    
    
    @WebMethod
    public String obtenerNombreActividad(String nombreSalida) {
    	IATYST IActSal = fabrica.getIATYST();
    	return IActSal.obtenerNombreActividad(nombreSalida);
    }
    
    
    @WebMethod
    public int obtenerCantInscriptos(String nickname, String nombreSalida) {
    	IUsuario IUsr= fabrica.getIUsuario();
    	return IUsr.obtenerCantInscriptos(nickname, nombreSalida);
    }
    
    
    
    @WebMethod
    public String obtenerUrlUsuario(String nickName) {
    	IUsuario IUsr= fabrica.getIUsuario();
    	return IUsr.getURL(nickName);
    }
    
    @WebMethod
    public String obtenerUrlActividad(String act) {
    	IATYST IAct = fabrica.getIATYST();
    	return IAct.getURLActividad(act);
    }
    
    @WebMethod
    public void setUrlUsuario(String nickname, String url) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	IUsr.setURL(nickname, url);
    }
    
    @WebMethod
    public DataGeneric listarUsuarios() throws NoExistenUsuariosException {
    	IUsuario IUsr=fabrica.getIUsuario();
    	String[] usrs;
		try {
			usrs = IUsr.listarUsuarios();
			return new DataGeneric("",usrs);
		} catch (NoExistenUsuariosException e) {
			throw new NoExistenUsuariosException ("");
		}
    }
    
    @WebMethod
    public DataUsuario obtenerDatosTurista(String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	DTTurista dtUsr=IUsr.obtenerDatosTurista(nickname);
    	LocalDate fecha=dtUsr.getFechaNacimiento();
    	DataUsuario enviar=new DataUsuario(dtUsr.getNickname(),dtUsr.getNombre(),dtUsr.getApellido(),dtUsr.getEmail(),fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth(),dtUsr.getPassword());
    	enviar.setNacionalidad(dtUsr.getNacionalidad());
    	return enviar;
    }
    
    @WebMethod
    public DataUsuario obtenerDatosProveedor(String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	DTProveedor dtUsr=IUsr.obtenerDatosProveedor(nickname);
    	LocalDate fecha=dtUsr.getFechaNacimiento();
    	DataUsuario enviar=new DataUsuario(dtUsr.getNickname(),dtUsr.getNombre(),dtUsr.getApellido(),dtUsr.getEmail(),fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth(),dtUsr.getPassword());
    	enviar.setDescripcion(dtUsr.getDescripcion());
    	enviar.setLink(dtUsr.getLink());
    	return enviar;
    }
    
    @WebMethod
    public DataUsuario obtenerDatosUsr(String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	DTUsuario dtUsr=IUsr.obtenerDatosUsuario(nickname);
    	LocalDate fecha=dtUsr.getFechaNacimiento();
    	DataUsuario enviar=new DataUsuario(dtUsr.getNickname(),dtUsr.getNombre(),dtUsr.getApellido(),dtUsr.getEmail(),fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth(),dtUsr.getPassword());
    	return enviar;
    }
    
    @WebMethod
    public DataUsuario obtenerDatosEmail(String email) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	DTUsuario dtUsr=IUsr.obtenerDatosUsuarioEmail(email);
    	LocalDate fecha=dtUsr.getFechaNacimiento();
    	DataUsuario enviar=new DataUsuario(dtUsr.getNickname(),dtUsr.getNombre(),dtUsr.getApellido(),dtUsr.getEmail(),fecha.getYear(),fecha.getDayOfMonth(),fecha.getMonthValue(),dtUsr.getPassword());
    	return enviar;
    }
    
    @WebMethod
    public DataGeneric listarActividadesTuristicasProveedor(String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	String [] activiaddes=IUsr.listarActividadesTuristicasProveedor(nickname);
    	return new DataGeneric(nickname,activiaddes);
    }
    
    @WebMethod
    public DataActividad obtenerDatosActividad(String nombreAt) {
    	IATYST IAt= fabrica.getIATYST();
    	DTActividad devo=IAt.obtenerDatosActividad(nombreAt);
    	LocalDate fecha=devo.getFechaAlta();
    	return new DataActividad(devo.getNombre(),devo.getDescripcion(), devo.getEnlace(), devo.getDuracion(), devo.getCostoPT(), devo.getCiudad(),fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth(), devo.getEstadoActual(), devo.getCantFavoritos(), devo.getCategoria());
    }
    
    @WebMethod
    public DataGenericArray listarActividadesTuristicasConfirmadasProveedor(String nickname) throws NoExistenActividadesException {
    	IUsuario IUsr=fabrica.getIUsuario();
    	try {
			DTActividad[] devo=IUsr.listarActividadesTuristicasConfirmadasProveedor(nickname);
			ArrayList<DataActividad> devolver= new ArrayList<DataActividad>();
			for(int i=0;i<devo.length;i++) {
				LocalDate fecha=devo[i].getFechaAlta();
				DataActividad agregar=new DataActividad(devo[i].getNombre(),devo[i].getDescripcion(), devo[i].getEnlace(), devo[i].getDuracion(), devo[i].getCostoPT(), devo[i].getCiudad(),fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth(), devo[i].getEstadoActual(), devo[i].getCantFavoritos(), devo[i].getCategoria());
				devolver.add(agregar);
			}
			return new DataGenericArray("",devolver,null);
    	} catch (NoExistenActividadesException e) {
			throw new NoExistenActividadesException("");
    	}
    }
    
    @WebMethod
    public boolean existeProveedor(String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	return IUsr.existeProveedor(nickname);
    }
    
    @WebMethod
    public boolean existeTurista(String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	return IUsr.existeTurista(nickname);
    }
    
    @WebMethod
    public boolean existeNickName(String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	return IUsr.existeNickname(nickname);
    }
    
    @WebMethod
    public boolean existeEmail(String email) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	return IUsr.existeEmail(email);
    }
    
    @WebMethod
    public byte[] getFile(@jakarta.jws.WebParam(name = "fileName") String name)
                    throws  IOException {
        byte[] byteArray = null;
        try {	
        		Path path1 = Paths.get(urlImagenes, name);
        		String path=path1.toString();
                File f = new File(path);
                FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
        } catch (IOException e) {
                throw e;
        }
        return byteArray;
    }
    
    @WebMethod
    public void guardarImagen(String url,byte []imagen) {
    	Path path1 = Paths.get(urlImagenes, url);
		String path=path1.toString();
    	String urlCompleta=path;
    	try {
			BufferedImage imag=ImageIO.read(new ByteArrayInputStream(imagen));
			ImageIO.write(imag, "jpg", new File(urlCompleta));
		} catch (IOException e) {
		}
    }
    
    @WebMethod
    public void darAltaUsuarioProveedor(String nickname,String nombre,String apellido, String email,int anio,int mes, int dia,String descrip,String link,String password){
    	IUsuario IUsr=fabrica.getIUsuario();
    	IUsr.darAltaProveedor(nickname, nombre, apellido, email, LocalDate.of(anio, mes, dia), descrip, link, password);
    }

    @WebMethod
    public void darAltaUsuarioTurista(String nickname,String nombre,String apellido, String email,int anio,int mes, int dia,String nacionalidad,String password){
    	IUsuario IUsr=fabrica.getIUsuario();
    	IUsr.darAltaTurista(nickname, nombre, apellido, email, LocalDate.of(anio, mes, dia), nacionalidad, password);
    }
    
    @WebMethod
    public boolean existeCategoria(String cat) {
    	IATYST IAct = fabrica.getIATYST();
    	boolean existe = IAct.existeCategoria(cat);
    	return existe;
    }
    
    @WebMethod
    public String getURLSalida(String nomSal, String nomAct) { //FALTA HACERLO BIEN
    	IATYST IAct = fabrica.getIATYST();
    	String urlSal = IAct.getURLSalida(nomSal, nomAct);
    	return urlSal;
    }
    
    @WebMethod
    public void setURLSalida(String nomSal, String nomAct, String URL) { //FALTA HACERLO BIEN
    	IATYST IAct = fabrica.getIATYST();
    	IAct.setURLSalida(nomSal, nomAct, URL);
    }
    
    @WebMethod
    public boolean existeSalida(String sal) {
    	IATYST IAct = fabrica.getIATYST();
    	boolean existe = IAct.existeSalida(sal);
    	return existe;
    }
    
    @WebMethod
    public boolean existeSalidaAct(String act,String sal) {
    	IATYST IAct = fabrica.getIATYST();
    	boolean existe = IAct.existeSalida(act, sal);
    	return existe;
    }
    
    @WebMethod
    public DataGenericArray listarDatosSalidasVigentes(String act) throws NoHaySalidasDisponiblesException{
    	IATYST IAct = fabrica.getIATYST();
    	ArrayList<DataSalida> nuevo=new ArrayList<DataSalida>();
    	try{
    		DTSalidasAT[] salsVig = IAct.listarDatosSalidasVigentes(act);
    		for (int i = 0; i < salsVig.length; i++) {
    			LocalDate fechaAlta=salsVig[i].getFechaAltaFech();
    			LocalDate fechaSalida=salsVig[i].getFechaFech();
    			LocalTime hora=salsVig[i].getHoraHora();
    			DataSalida agregar = new DataSalida(salsVig[i].getNombre(),salsVig[i].getCantMaxT(), fechaSalida.getYear(), fechaSalida.getMonthValue(), fechaSalida.getDayOfMonth(), fechaAlta.getYear(),fechaAlta.getMonthValue(),fechaAlta.getDayOfMonth(),hora.getHour(),hora.getMinute(),salsVig[i].getLugar(),salsVig[i].getCantTotalTuristas());
        		nuevo.add(agregar);
        	}
    	}catch(NoHaySalidasDisponiblesException e){
    		throw new NoHaySalidasDisponiblesException("hola");
    	}
    	return new DataGenericArray("",null,nuevo);
    }
    
    @WebMethod
    public DataSalida obtenerDatosSalida(String act, String sal) {
    	IATYST IAct = fabrica.getIATYST();
    	DTSalidasAT sal1 = IAct.obtenerDatosSalida(act, sal);
    	int cantT = sal1.getCantMaxT();
    	LocalDate fechaAlta = sal1.getFechaAltaFech();
    	LocalDate fechaSalida = sal1.getFechaFech();
    	LocalTime hora = sal1.getHoraHora();
    	String lugar = sal1.getLugar();
    	int cantTotalTur = sal1.getCantTotalTuristas();
    	DataSalida sal2 = new DataSalida(sal, cantT, fechaSalida.getYear(), fechaSalida.getMonthValue(), fechaSalida.getDayOfMonth(), fechaAlta.getYear(), fechaAlta.getMonthValue(), fechaAlta.getDayOfMonth(), hora.getHour(), hora.getMinute(), lugar, cantTotalTur);
    	return sal2;
    }
    
    @WebMethod
    public DataSalida obtenerDatosSalidaTur(String tur, String sal) {
    	IATYST IAct = fabrica.getIATYST();
    	DTSalidasAT sal1 = IAct.obtenerDatosSalidaTur(tur, sal);
    	int cantT = sal1.getCantMaxT();
    	LocalDate fechaAlta = sal1.getFechaAltaFech();
    	LocalDate fechaSalida = sal1.getFechaFech();
    	LocalTime hora = sal1.getHoraHora();
    	String lugar = sal1.getLugar();
    	int cantTotalTur = sal1.getCantTotalTuristas();
    	DataSalida sal2 = new DataSalida(sal, cantT, fechaSalida.getYear(), fechaSalida.getMonthValue(), fechaSalida.getDayOfMonth(), fechaAlta.getYear(), fechaAlta.getMonthValue(), fechaAlta.getDayOfMonth(), hora.getHour(), hora.getMinute(), lugar, cantTotalTur);
    	return sal2;
    }
    

    @WebMethod
    public DataGeneric listarActividadesDepartameto(String depto) throws NoExistenActividadesEnDepartamentoException {
    	IDepartamento IDep = fabrica.getIDepartamento();
    	String[] deptos;
		try {
			deptos = IDep.listarActividadesDepartamento(depto);
		} catch (NoExistenActividadesEnDepartamentoException e) {
			throw new NoExistenActividadesEnDepartamentoException("hola");
		}
    	return new DataGeneric("",deptos);
    }
    
    @WebMethod
    public DataGeneric listarActividadesCategoria(String cat) throws NoExistenActividadesException{
    	IATYST IAct = fabrica.getIATYST();
    	String[] cats;
    	try {
    		cats = IAct.listarActividadesCategoria(cat);	
    	} catch(NoExistenActividadesException e) {
    		throw new NoExistenActividadesException("hola");
    	}
    	return new DataGeneric("",cats);
    }
    

    
    @WebMethod
    public DataGeneric listarActividadesConfirmadas() throws NoExistenActividadesException{
    	IATYST IAct = fabrica.getIATYST();
    	String[] acts;
    	try {
    		acts = IAct.listarActividadesConfirmadas();	
    	} catch(NoExistenActividadesException e) {
    		throw new NoExistenActividadesException("hola");
    	}
    	return new DataGeneric("",acts);
    }
    
    @WebMethod
    public DataGeneric listarSalidasActividad(String nombre) throws NoExistenSalidasEnActividadException {
    	IATYST IAct = fabrica.getIATYST();
    	String[] sals;
		try {
			sals = IAct.listarSalidasActividad(nombre);
		} catch (NoExistenSalidasEnActividadException e) {
			throw new NoExistenSalidasEnActividadException("hola");
		}
    	return new DataGeneric("",sals);
    }
    

    
    @WebMethod
    public DataGeneric listarPaquetesActividad(String nombre) throws NoExistenPaquetesEnLaActividadException  {
    	IATYST IAct = fabrica.getIATYST();
    	String[] paqs;
		try {
			paqs = IAct.listarPaquetesActividad(nombre);
		} catch (NoExistenPaquetesEnLaActividadException e) {
			throw new NoExistenPaquetesEnLaActividadException("hola");
		}
    	return new DataGeneric("",paqs);
    }
    
    @WebMethod
    public void setURLActividad(String nomAct, String URL) {
    	IATYST IAct = fabrica.getIATYST();
    	IAct.setURLActividad(nomAct, URL);
    }
    
    @WebMethod
    public DataGeneric listarCategoriasActividad(String nombre) throws NoExistenCategoriasException  {
    	IATYST IAct = fabrica.getIATYST();
    	String[] cats;
		try {
			cats = IAct.listarCategoriasActividad(nombre);
		} catch (NoExistenCategoriasException e) {
			throw new NoExistenCategoriasException("hola");
		}
    	return new DataGeneric("",cats);
    }
    
    @WebMethod
    public String getURLActividad(String nomAct) {
    	IATYST IAct = fabrica.getIATYST();
    	String urlAct = IAct.getURLActividad(nomAct);
    	return urlAct;
    }
    
    @WebMethod
    public DataGeneric listarDepartamentos() throws NoExistenDepartamentosException {
    	IDepartamento IDep = fabrica.getIDepartamento();
    	String[] deptos;
		try {
			deptos = IDep.listarDepartamentos();
		} catch (NoExistenDepartamentosException e) {
			throw new NoExistenDepartamentosException("hola");
		}
    	return new DataGeneric("",deptos);
    }
    
    @WebMethod
    public DataGeneric listarCategorias() throws NoExistenCategoriasException  {
    	IATYST IAct = fabrica.getIATYST();
    	String[] cats;
		try {
			cats = IAct.listarCategorias();
		} catch (NoExistenCategoriasException e) {
			throw new NoExistenCategoriasException("hola");
		}
    	return new DataGeneric("",cats);
    }
    
    @WebMethod
    public DataGeneric listarSalidasTurista(String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	IATYST IAct = fabrica.getIATYST();
    	String[] salidas=IUsr.listarSalidasTurista(nickname);
    	ArrayList<String> array=new ArrayList<String>();
    	for(int i=0; i<salidas.length;i++) {
    		String nombreAT=IAct.obtenerNombreActividad(salidas[i]);
    		if(IAct.obtenerDatosActividad(nombreAT).getEstadoActual().equals(logica.ActividadAT.Estado.Confirmada)) {
    			array.add(salidas[i]);
    		}
    	}
    	Iterator iter=array.iterator();
    	String[] devoler=new String[array.size()];
    	int i=0;
    	while(iter.hasNext()) {
    		devoler[i]=(String) iter.next();
    		i++;
    	}
    	return new DataGeneric(nickname,devoler);
    }
    
    @WebMethod
    public void darAltaActividadTuristica(String nombreU, String departamento, String ciudad, String descripcion, String enlace, String proveedor, int duracion, int costo, int anio, int mes, int dia,String arregloCat)throws ExisteActividadException, NoExisteDepartamentoException, NoExisteProveedorException {
    	IATYST IAct = fabrica.getIATYST();
    	String cats=arregloCat;
		String [] comas=cats.split(",,,");
		String [] arregloCats=new String[comas.length-1];
		for(int i=1;i<comas.length;i++) {
			arregloCats[i-1]=comas[i];
		}
    		
		try {
			IAct.darAltaActividadTuristica(nombreU, departamento, ciudad, descripcion, enlace, proveedor, duracion, costo, LocalDate.of(anio,mes,dia), arregloCats);
		} catch (ExisteActividadException e) {
			throw new ExisteActividadException("");
		} catch (NoExisteDepartamentoException e) {
			throw new NoExisteDepartamentoException("");
		} catch (NoExisteProveedorException e) {
			throw new NoExisteProveedorException("");
		}
    }
    
    @WebMethod
    public void inscribirASalida(String tur, String datoact, String sal, int canttur, int anio, int mes, int dia) throws ExcedeCapacidadException, YaTieneInscripcionAEstaSalidaException {
    	IATYST IAct = fabrica.getIATYST();
    	try {
    		IAct.inscribirASalida(tur, datoact, sal, canttur, LocalDate.of(anio, mes, dia));
    	} catch (ExcedeCapacidadException e) {
    		throw new ExcedeCapacidadException("hola");
    	} catch (YaTieneInscripcionAEstaSalidaException e) {
    		throw new YaTieneInscripcionAEstaSalidaException("hola");
    	}
    }
    
    @WebMethod
    public void darAltaSalidacarga(String nombreAT, String nombreSal, int anioSalida, int mesSalida, int diaSalida, int hora, int minuto, int turisMax, String lugar, int anioAlta, int mesAlta, int diaAlta) {
    	IATYST IAct = fabrica.getIATYST();
    	IAct.darAltaSalidacarga(nombreAT, nombreSal, LocalDate.of(anioSalida, mesSalida, diaSalida), LocalTime.of(hora, minuto), turisMax, lugar, LocalDate.of(anioAlta, mesAlta, diaAlta));
    }
    
    

    @WebMethod
    public void agregarActividadFavorita(String act, String nickname) {
    	IUsuario IUsr = fabrica.getIUsuario();
    	IUsr.agregarActividadFavorita(act, nickname);
    }
    
    @WebMethod
    public void eliminarActividadFavorita(String act, String nickname) {
    	IUsuario IUsr = fabrica.getIUsuario();
    	IUsr.eliminarActividadFavorita(act, nickname);
    }
    
    @WebMethod
    public DataGeneric listaractividadesFavoritas(String nickname) throws UsuarioSinActividadesFavoritasException{
    	IUsuario IUsr = fabrica.getIUsuario();
    	String[] acts;
		try {
			acts = IUsr.listaractividadesFavoritas(nickname);
		} catch (UsuarioSinActividadesFavoritasException e) {
			throw new UsuarioSinActividadesFavoritasException("hola");
		}
    	return new DataGeneric("", acts);
    }
    
    @WebMethod
    public DataGeneric listarSeguidoresUsuario(String nickname) throws UsuarioSinSeguidoresException{
    	IUsuario IUsr = fabrica.getIUsuario();
    	String[] seguidores;
		try {
			seguidores = IUsr.listarSeguidoresUsuario(nickname);
		} catch (UsuarioSinSeguidoresException e) {
			throw new UsuarioSinSeguidoresException("hola");
		}
    	return new DataGeneric("", seguidores);
    }
	
    @WebMethod
	public DataGeneric listarSeguidosUsuario(String nickname) throws UsuarioSinSeguidosException{
    	IUsuario IUsr = fabrica.getIUsuario();
    	String[] seguidos;
		try {
			seguidos = IUsr.listarSeguidosUsuario(nickname);
		} catch (UsuarioSinSeguidosException e) {
			throw new UsuarioSinSeguidosException("hola");
		}
    	return new DataGeneric("",seguidos);
	}
	
    @WebMethod
	public void agregarSeguidorYSeguido(String usrQueSigue, String usrSeguido) {
    	IUsuario IUsr = fabrica.getIUsuario();
    	IUsr.agregarSeguidorYSeguido(usrQueSigue, usrSeguido);
	}
	
    @WebMethod
	public void eliminarSeguidor(String usrQueDeja, String usrDejado) {
    	IUsuario IUsr = fabrica.getIUsuario();
    	IUsr.eliminarSeguidor(usrQueDeja, usrDejado);
	}
    
    @WebMethod
    public boolean sePuedeFinalizarActividad(String act) {
    	IATYST IAct = fabrica.getIATYST();
    	try {
			DTSalidasAT[] salidas = IAct.listarDatosSalidasVigentes(act);
			return false;
		} catch (NoHaySalidasDisponiblesException e) {
			try {
				String[] paquetes = IAct.listarPaquetesActividad(act);
				return false;
			} catch (NoExistenPaquetesEnLaActividadException e1) {
				DTActividad activi=IAct.obtenerDatosActividad(act);
				if(activi.getEstadoActual().equals(logica.ActividadAT.Estado.Confirmada)) {
					return true;					
				}else {
					return false;
				}
			}
		}
    }
    
    @WebMethod
    public void cambiarEstado( String act) { //se le podria pasar directamente finalizada porque solo para eso se usa
    	IATYST IAct = fabrica.getIATYST();
    	DTActividad activi=IAct.obtenerDatosActividad(act);
    	if(activi.getEstadoActual().equals(logica.ActividadAT.Estado.Confirmada) && sePuedeFinalizarActividad(act)) {
    		IAct.cambiarEstado(logica.ActividadAT.Estado.Finalizada, act);    		
    	}
    }

    @WebMethod
    public void modificarApellidoUsuario(String apel, String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	IUsr.modificarApellidoUsuario(apel, nickname);
    }
    
    @WebMethod
    public void modificarNacimientoUsuario(int anio, int mes, int dia, String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	LocalDate fecha=LocalDate.of(anio, mes, dia);
    	IUsr.modificarNacimientoUsuario(fecha, nickname);
    }

    @WebMethod
    public void modificarNombreUsuario(String nombre, String nickname) {
    	IUsuario IUsr=fabrica.getIUsuario();
    	IUsr.modificarNombreUsuario(nombre, nickname);
    }
}


