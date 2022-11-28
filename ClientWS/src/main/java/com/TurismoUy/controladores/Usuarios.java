package com.TurismoUy.controladores;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.crypto.Data;

import com.TurismoUy.model.EstadoSesion;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.Image;
import com.itextpdf.layout.element.Text;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
//For adding content into PDF document
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import servidor.UsuarioSinActividadesFavoritasException;
import servidor.UsuarioSinActividadesFavoritasException_Exception;
import servidor.UsuarioSinSeguidoresException;
import servidor.UsuarioSinSeguidoresException_Exception;
import servidor.UsuarioSinSeguidosException_Exception;
import servidor.DataActividad;
import servidor.DataActividadArray;
import servidor.DataGeneric;
import servidor.DataGenericArray;
import servidor.DataSalida;
import servidor.DataUsuario;
import servidor.IOException_Exception;
import servidor.NoExistenActividadesException;
import servidor.NoExistenActividadesException_Exception;
import servidor.NoExistenCategoriasException_Exception;
import servidor.NoExistenPaquetesEnLaActividadException_Exception;
import servidor.NoExistenSalidasEnActividadException_Exception;
import servidor.NoExistenUsuariosException;
import servidor.NoExistenUsuariosException_Exception;


@WebServlet ("/usuario")

@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Usuarios() {
		super();
	}
	private void processRequestAltaUsuario1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		getServletContext().getRequestDispatcher("/WEB-INF/usuario/altaUsuario.jsp").forward(req, res);
	}
	
	private String getPreFileName(){
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate date = LocalDate.now();
                 // Formatear cadena de fecha
        stringBuilder.append(date.format(DateTimeFormatter.BASIC_ISO_DATE));
        LocalTime time = LocalTime.now().withNano(0);
                 // Formatear cadena de tiempo
        stringBuilder.append(time.format(DateTimeFormatter.ofPattern("HHmmss")));
        return stringBuilder.toString();
    }

		
	
	private void processRequestAltaUsuario(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
        String nickname=req.getParameter("Nickname");
        String apel = req.getParameter("apellido");
        String nombre = req.getParameter("Nombre");
        String contrasenia = req.getParameter("contrasea1");
        String email = req.getParameter("email");
        String desc = req.getParameter("Descripcion");
        String urlPag=req.getParameter("Url");
        String nacional=req.getParameter("Nacionalidad");
        String fecha = req.getParameter("fecha");
        int anio = Integer.parseInt(fecha.split("-")[0]);
        int mes = Integer.parseInt(fecha.split("-")[1]);
        int dia = Integer.parseInt(fecha.split("-")[2]);
        LocalDate  f = LocalDate.of(anio,mes,dia);
        String malNick="";
        String malEmail="";
        if(port.existeNickName(nickname)) {
        	malNick="si";
        }
        if(port.existeEmail(email)) {
        	malEmail="si";
        }
        if(malNick.equals("si") && malEmail.equals("si")) {
        	req.setAttribute("mostrar", "El nickname y el email ya existen, elije otros por favor.");
        }else if(malNick.equals("si")) {
        	req.setAttribute("mostrar", "El nickname ya existe, elija otro por favor");
        }else if(malEmail.equals("si")) {
        	req.setAttribute("mostrar","El email ya existe, elija otro por favor");
        }
        
        //seccion imagen y cosas raras
        
        
        if(malNick.equals("si")||malEmail.equals("si")) {
        	req.setAttribute("MalNick",malNick);
        	req.setAttribute("MalEmail",malEmail);

        	req.setAttribute("Nick",nickname);
            req.setAttribute("apell",apel);
            req.setAttribute("Nomb",nombre);
            req.setAttribute("ema",email);
        	getServletContext().getRequestDispatcher("/WEB-INF/usuario/altaUsuarioError.jsp").forward(req, res);
        }else {
        	if(desc!=null && !desc.equals("")) {
        		//es proveedor
        		
        		port.darAltaUsuarioProveedor(nickname, nombre, apel, email, anio,mes,dia, desc, urlPag, contrasenia);
        		DataUsuario usr=port.obtenerDatosUsr(nickname);
        		
        		Part filePart = req.getPart("imagen");
        		String fileName="visitante.jpg";
        		if(filePart!=null && filePart.getContentType().startsWith("image")) {
        			fileName=getPreFileName();
        			
        			InputStream inputS = filePart.getInputStream();
        			byte[] imagen=inputS.readAllBytes();			
        			
        			port.guardarImagen(fileName+".jpg", imagen);
        			port.setUrlUsuario(nickname, fileName+".jpg" );
    
					String bphoto = Base64.getEncoder().encodeToString(imagen);
					
					req.getSession().setAttribute("imagen", bphoto);
        			req.getSession().setAttribute("usuario", usr);
            		req.getSession().setAttribute("estado_sesion", EstadoSesion.LOGIN_PROVEEDOR);
    				req.getRequestDispatcher("/WEB-INF/home/indexProveedor.jsp").forward(req, res);
        		}else {
        			port.setUrlUsuario(nickname, "visitante.jpg");
        			byte[] photo;
    				try {
    					photo = port.getFile("visitante.jpg");
    					String bphoto = Base64.getEncoder().encodeToString(photo);
    					req.getSession().setAttribute("imagen", bphoto);
    				}catch (IOException_Exception e) {
    					e.printStackTrace();
    				}
    				
        			req.getSession().setAttribute("usuario", usr);
        			req.getSession().setAttribute("estado_sesion", EstadoSesion.LOGIN_PROVEEDOR);
        			req.getRequestDispatcher("/WEB-INF/home/indexProveedor.jsp").forward(req, res);
        		}

        	}else if(nacional!=null && !nacional.equals("")){
        		//es turista
        		
        		port.darAltaUsuarioTurista(nickname, nombre, apel, email, anio,mes,dia,nacional, contrasenia);
        		Part filePart = req.getPart("imagen");
        		String fileName="visitante.jpg";
        		DataUsuario usr=port.obtenerDatosUsr(nickname);
        		
        		if(filePart!=null && filePart.getContentType().startsWith("image")) {
        			fileName=getPreFileName();
        			
        			InputStream inputS = filePart.getInputStream();
        			byte[] imagen=inputS.readAllBytes();			
        			
        			port.guardarImagen(fileName+".jpg", imagen);
        			port.setUrlUsuario(nickname, fileName+".jpg" );
    
					String bphoto = Base64.getEncoder().encodeToString(imagen);
					
					req.getSession().setAttribute("imagen", bphoto);
        			req.getSession().setAttribute("usuario", usr);
        			req.getSession().setAttribute("estado_sesion", EstadoSesion.LOGIN_TURISTA);
    				req.getRequestDispatcher("/WEB-INF/home/indexTurista.jsp").forward(req, res);
        		}else {
        			
        			port.setUrlUsuario(nickname, "visitante.jpg");
        			byte[] photo;
    				try {
    					photo = port.getFile("visitante.jpg");
    					String bphoto = Base64.getEncoder().encodeToString(photo);
    					req.getSession().setAttribute("imagen", bphoto);
    				}catch (IOException_Exception e) {
    					e.printStackTrace();
    				}
    				
        			req.getSession().setAttribute("usuario", usr);
        			req.getSession().setAttribute("estado_sesion", EstadoSesion.LOGIN_TURISTA);
    				req.getRequestDispatcher("/WEB-INF/home/indexTurista.jsp").forward(req, res);
        		}
        	}else {
        		//quien sabe que es
        		getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(req, res);        	        		
        	}
        }
	}
	
	private void processRequestActividadFavorita(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{ //SOLO SE PUEDE HACER SI EL USUARIO ES UN TURISTA
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();  
        DataUsuario usr = (DataUsuario) req.getSession().getAttribute("usuario");
        try {
        	DataGeneric acts = port.listarActividadesConfirmadas();
        	List<String> acts2 = acts.getArr();
        	Iterator<String> iterador = acts2.iterator();
        	String [] acts3 = new String[acts2.size()];
        	int i=0;
        	while(iterador.hasNext()) {
        		acts3[i]=iterador.next();
        		i++;
        	}
        	
        	DataActividad[] arrAct = new DataActividad[acts3.length];
        	String [] urls = new String[acts3.length];
        	for(int j = 0; j < acts3.length; j++) {
        		arrAct[j]=port.obtenerDatosActividad(acts3[j]);
        		byte[] photo;
				try {
					photo = port.getFile(port.obtenerUrlActividad(acts3[j]));
					urls[j]=Base64.getEncoder().encodeToString(photo);
				} catch (IOException_Exception e) {
				}
        	}
        	
        	DataGeneric favs;
    		String [] favoritas = null;
    		try {
    			favs = port.listaractividadesFavoritas(usr.getNickname());
    			List<String> favs2 = favs.getArr();
    			Iterator<String> iter1 = favs2.iterator();
    			favoritas = new String[favs2.size()];
    			int k = 0;
    			while(iter1.hasNext()) {
    				favoritas[k] = iter1.next();
    				k++;
    			}
    			req.setAttribute("favoritas", favoritas);
    		} catch (UsuarioSinActividadesFavoritasException_Exception e) {
    		}
        	
        	boolean[] favs2 = new boolean[acts3.length]; 
    		for(int n = 0; n < acts3.length; n++) { //true si no lo sigue, false si ya lo sigue
    			int s = 0;
    			while((s < favoritas.length) && (!favoritas[s].equals(acts3[n]))) { // capaz habria que poner equals
    				s++;
    			}
    			if(s < favoritas.length)
    				favs2[n] = true;
    			else favs2[n] = false;
    		}
    		req.setAttribute("favs2", favs2);
        	
        	req.setAttribute("urls", urls);
			req.setAttribute("strsr", arrAct);
			getServletContext().getRequestDispatcher("/WEB-INF/usuario/ActividadFavorita.jsp").forward(req, res);
        }catch(NoExistenActividadesException_Exception e) {
			DataActividad [] acts3 = null;
			req.setAttribute("strsr", acts3);
			getServletContext().getRequestDispatcher("/WEB-INF/usuario/ActividadFavorita.jsp").forward(req, res);
        }
	}
	
	private void processRequestMarcarDesmarcarActFav(HttpServletRequest req, HttpServletResponse res, String act, boolean bol) throws ServletException, IOException{
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        DataUsuario usr = (DataUsuario) req.getSession().getAttribute("usuario");
        
        if(bol) { // bol = true = marcar fav
        	port.agregarActividadFavorita(act, usr.getNickname());
        }else { // bol = false = desmarcar fav
        	port.eliminarActividadFavorita(act, usr.getNickname());
        }
        
        processRequestActividadFavorita(req,res);
	}
	
	private void processRequestConsultaUsuarios(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();    
        try {
        	DataGeneric complet=port.listarUsuarios();
        	List<String> Usrs=complet.getArr();
        	Iterator<String> iterador=Usrs.iterator();
        	String [] usrs=new String[Usrs.size()];
        	int i=0;
        	while(iterador.hasNext()) {
        		usrs[i]=iterador.next();
        		i++;
        	}
        	DataUsuario[] arrUsr=new DataUsuario[usrs.length];
        	String [] urls=new String[usrs.length];
        	for(int j=0;j<usrs.length;j++) {
        		arrUsr[j]=port.obtenerDatosUsr(usrs[j]);
        		byte[] photo;
				try {
					photo = port.getFile(port.obtenerUrlUsuario(usrs[j]));
					urls[j]=Base64.getEncoder().encodeToString(photo);
				} catch (IOException_Exception e) {
				}
        	}
        	req.setAttribute("urls", urls);
			req.setAttribute("strsr", arrUsr);
			getServletContext().getRequestDispatcher("/WEB-INF/usuario/consultaUsuarios.jsp").forward(req, res);
		} catch (NoExistenUsuariosException_Exception e) {
			DataUsuario [] usrs=null;
			req.setAttribute("strsr", usrs);
			getServletContext().getRequestDispatcher("/WEB-INF/usuario/consultaUsuarios.jsp").forward(req, res);
		}
	}
	
	private void processRequestConsultaUsuario(HttpServletRequest req, HttpServletResponse res, String nickname) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        String si="si";
        req.setAttribute("nickname",nickname);
        DataUsuario datosUsr=port.obtenerDatosUsr(nickname);
        req.setAttribute("datosUsr", datosUsr);
        DataUsuario usr = (DataUsuario) req.getSession().getAttribute("usuario");        	
        
        byte[] photo=null;
		try {
			photo = port.getFile(port.obtenerUrlUsuario(nickname));
		} catch (IOException_Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String urls=Base64.getEncoder().encodeToString(photo);
        req.setAttribute("urlimagen", urls);
        
        DataGeneric segs;
		String []seguidores = null;
		try {
			segs = port.listarSeguidoresUsuario(nickname);
			List<String> segs2 = segs.getArr();
			Iterator<String> iter1 = segs2.iterator();
			seguidores = new String[segs2.size()];
			int i = 0;
			while(iter1.hasNext()) {
				seguidores[i] = iter1.next();
				i++;
			}
			req.setAttribute("seguidores", seguidores);
		} catch (UsuarioSinSeguidoresException_Exception e) {
			seguidores = new String[0];
		}
		DataGeneric segs3;
		String []seguidos=null;
		try {
			segs3 = port.listarSeguidosUsuario(nickname);
			List<String> segs4 = segs3.getArr();
			Iterator<String> iter2 = segs4.iterator();
			seguidos = new String[segs4.size()];
			int j = 0;
			while(iter2.hasNext()) {
				seguidos[j] = iter2.next();
				j++;
			}
			req.setAttribute("seguidos", seguidos);
		} catch (UsuarioSinSeguidosException_Exception e) {
			seguidos = new String[0];
		}
		
		
			boolean [] seguirDejarSeguir = new boolean[seguidores.length]; // true seguir, false dejar de seguir
			int s = 0;
			for(int i = 0; i < seguidores.length; i++) {
				if(seguidos!=null) {
					s=0;
					while((s < seguidos.length) && (!seguidos[s].equals(seguidores[i]))) { // capaz habria que poner equals
						s++;
					}
					if(s >= seguidos.length || seguidos==null)
						seguirDejarSeguir[i] = true;
					else seguirDejarSeguir[i] = false;
				}else {
					seguirDejarSeguir[i] = false;
				}
			}
			req.setAttribute("boolSeg", seguirDejarSeguir);
		
		if(usr!=null) {
			int c = 0;
			while((c < seguidores.length) && (!seguidores[c].equals(usr.getNickname()))) {
				c++;
			}
			boolean bol;
			if(c >= seguidores.length) {
				bol = false;
			}else{
				bol = true;
			}
			req.setAttribute("bol", bol);
		}else {
			boolean bol = true;//por poner algo
			req.setAttribute("bol", bol);
		}
        if(port.existeProveedor(nickname)) {
        	DataUsuario datProv=port.obtenerDatosProveedor(nickname);
        	req.setAttribute("datProv",datProv);
        	if(req.getSession().getAttribute("usuario") != null && usr.getNickname().equals(nickname)){
        		DataGeneric actividae=port.listarActividadesTuristicasProveedor(nickname);
            	String[] actividades1=new String[actividae.getArr().size()];
            	List<String> parallenar=actividae.getArr();
            	Iterator<String> iterador=parallenar.iterator();
            	int l=0;
            	while(iterador.hasNext()) {
            		actividades1[l]=iterador.next();
            		l++;
            	}
        		DataActividad[] devolver= new DataActividad[actividades1.length];
        		for(int i=0; i<actividades1.length;i++) {
        			devolver[i]= port.obtenerDatosActividad(actividades1[i]);
        		}
        		
        		boolean [] actsFinal = new boolean[actividades1.length];
        		for(int i=0; i<actividades1.length;i++) {
        			actsFinal[i] = port.sePuedeFinalizarActividad(actividades1[i]);
        		}
        		req.setAttribute("actsFin",actsFinal);

        		List<DataSalida> salidas1=new ArrayList<DataSalida>();
            	Map<String, String>costos= new HashMap<String, String>();
            	for(int i=0;i<devolver.length;i++) {
            		try {
    					DataGeneric geneSal=port.listarSalidasActividad(devolver[i].getNombre());
    					List<String> salAux=geneSal.getArr();
    					Iterator<String> iterStart=salAux.iterator();
    					while(iterStart.hasNext()) {
    						String nomSal=iterStart.next();
    						DataSalida auxSal2=port.obtenerDatosSalida(devolver[i].getNombre(), nomSal);
    						salidas1.add(auxSal2);
    						costos.put(nomSal,devolver[i].getCostoPT()+"");
    					}
    				} catch (NoExistenSalidasEnActividadException_Exception e) {
    				}
            	}
            	DataSalida[] vacio=new DataSalida[salidas1.size()];
            	String []costosSal=new String[salidas1.size()];
            	Iterator<DataSalida> iterSali=salidas1.iterator();
            	int q=0;
            	while(iterSali.hasNext()) {
            		vacio[q]=iterSali.next();
            		costosSal[q]=costos.get(vacio[q].getNombre());
            		q++;
            	}
            	
            	req.setAttribute("salAct", vacio);
            	req.setAttribute("costosSal", costosSal);
        		
        		req.setAttribute("actividades",devolver);
        		req.setAttribute("strsr", si);
        		req.setAttribute("mismoprov", si);
        	} else {
           		List<DataActividad> actividad;
           		DataActividad[] actividades1;
				try {
					DataGenericArray global=port.listarActividadesTuristicasConfirmadasProveedor(nickname);
					actividad = global.getActividades();
					actividades1=new DataActividad[actividad.size()];
					int y=0;
					Iterator<DataActividad> iterator4=actividad.iterator();
					while(iterator4.hasNext()) {
						actividades1[y]=iterator4.next();
						y++;
					}
					
					List<DataSalida> salidas1=new ArrayList<DataSalida>();
	            	Map<String, String>costos= new HashMap<String, String>();
	            	for(int i=0;i<actividades1.length;i++) {
	            		try {
	    					DataGeneric geneSal=port.listarSalidasActividad(actividades1[i].getNombre());
	    					List<String> salAux=geneSal.getArr();
	    					Iterator<String> iterStart=salAux.iterator();
	    					while(iterStart.hasNext()) {
	    						String nomSal=iterStart.next();
	    						DataSalida auxSal2=port.obtenerDatosSalida(actividades1[i].getNombre(), nomSal);
	    						salidas1.add(auxSal2);
	    						costos.put(nomSal,actividades1[i].getCostoPT()+"");
	    					}
	    				} catch (NoExistenSalidasEnActividadException_Exception e) {
	    				}
	            	}
	            	DataSalida[] vacio=new DataSalida[salidas1.size()];
	            	String []costosSal=new String[salidas1.size()];
	            	Iterator<DataSalida> iterSali=salidas1.iterator();
	            	int q=0;
	            	while(iterSali.hasNext()) {
	            		vacio[q]=iterSali.next();
	            		costosSal[q]=costos.get(vacio[q].getNombre());
	            		q++;
	            	}
	            	
	            	req.setAttribute("salAct", vacio);
	            	req.setAttribute("costosSal", costosSal);
					
				} catch (NoExistenActividadesException_Exception e) {
					actividades1=null;
				}
        		req.setAttribute("actividades",actividades1);
        		req.setAttribute("strsr", si);        	
        	}        
        	getServletContext().getRequestDispatcher("/WEB-INF/usuario/ConsultaUsuario.jsp").forward(req, res);
		}else {		
			DataUsuario datTur =port.obtenerDatosTurista(nickname);
			req.setAttribute("datTur",datTur);
			DataGeneric entero=port.listarSalidasTurista(nickname); 
			List<String> Usrs=entero.getArr();
        	Iterator<String> iterador=Usrs.iterator();
        	String [] salidas=new String[Usrs.size()];
        	int o=0;
        	while(iterador.hasNext()) {
        		salidas[o]=iterador.next();
        		o++;
        	}
			
			DataSalida[] devolver= new DataSalida[salidas.length];
			String[] costos=new String[salidas.length];
        	for(int i=0; i<salidas.length;i++) {
        		devolver[i]= port.obtenerDatosSalidaTur(nickname, salidas[i]);
        		try {
        			DataGeneric paraCat=port.listarCategorias();
        			List<String> auxCat=paraCat.getArr();
        			Iterator<String> iterator2=auxCat.iterator();
        			String [] cats=new String[auxCat.size()];
        			int l=0;
        			while(iterator2.hasNext()) {
        				cats[l]=iterator2.next();
        				l++;
        			}
        			for(int j=0;j<cats.length;j++) {
        				try {
        					DataGeneric entero1=port.listarActividadesCategoria(cats[j]);
			        		List<String> actAux=entero1.getArr();
			            	Iterator<String> iterador1=actAux.iterator();
			            	String [] actividades=new String[actAux.size()];
			            	int p=0;
			            	while(iterador1.hasNext()) {
			            		actividades[p]=iterador1.next();
			            		p++;
			            	}
        					for(int k=0;k<actividades.length;k++) {
        						if(port.existeSalidaAct(actividades[k], salidas[i])) {
        							int valorTotal=devolver[i].getCantTotalTuristas()*port.obtenerDatosActividad(actividades[k]).getCostoPT();
        							costos[i]=valorTotal+"";
        							break;
        						}
        					}
        					if(costos[i]!=null && costos[i]!="") {
        						break;
        					}
        					
        				} catch (NoExistenActividadesException_Exception e) {
        				}
        			}
        		} catch (NoExistenCategoriasException_Exception e) {
        		}
        	}
        	
			req.setAttribute("costos", costos);
        	
        	req.setAttribute("salidasTur",devolver);
			
			si="no";
			req.setAttribute("strsr", si);
			getServletContext().getRequestDispatcher("/WEB-INF/usuario/ConsultaUsuario.jsp").forward(req, res);
		}
	}
	
	private void processRequestModificarDatosActividad(HttpServletRequest req, HttpServletResponse res, String act) throws ServletException, IOException{
		DataUsuario usr = (DataUsuario) req.getSession().getAttribute("usuario");
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        port.cambiarEstado(act);
        processRequestConsultaUsuario(req, res, usr.getNickname());
	}
	
	public void processRequestSeguirYDejarDeSeguir(HttpServletRequest req, HttpServletResponse res, String seguir, boolean bol) throws ServletException, IOException{
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        DataUsuario usr = (DataUsuario) req.getSession().getAttribute("usuario");
        
        if(bol) { // bol = true = seguir
        	port.agregarSeguidorYSeguido(usr.getNickname(), seguir);
        }else { // bol = false = dejar de seguir
        	port.eliminarSeguidor(usr.getNickname(), seguir);
        }
        
        processRequestConsultaUsuario(req,res,usr.getNickname());
	}

	
	private void processRequestModificarDatos(HttpServletRequest req, HttpServletResponse res, String nickname) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
        byte[] photo=null;
		try {
			photo = port.getFile(port.obtenerUrlUsuario(nickname));
		} catch (IOException_Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String urls=Base64.getEncoder().encodeToString(photo);
        req.setAttribute("urlimagen", urls);
        
        String nac= req.getParameter("naci");
        String nom= req.getParameter("nombre");
        String apel = req.getParameter("apellido");
        if(apel!=null && apel!="") {
        	port.modificarApellidoUsuario(apel, nickname);        	
        }
        if(nom!=null && nom!="") {
        	port.modificarNombreUsuario(nom, nickname);   
        }
        if(nac!=null && nac!="") {
        	String[] comp = nac.split("-");
        	if(comp.length==3) {
        		try {
        			int dia = Integer.parseInt(comp[0]);
        			int mes = Integer.parseInt(comp[1]);
        			int anio = Integer.parseInt(comp[2]);
        			if(mes>0 && mes<13) {
        				LocalDate fecha=LocalDate.of(anio, mes, dia);
        				if(fecha.isBefore(LocalDate.now())) {
        					port.modificarNacimientoUsuario(anio,mes,dia, nickname);        				
        				}
        			}
        		}catch(NumberFormatException | DateTimeException e){        			
        		}
        	}
        }
        processRequestConsultaUsuario(req, res, nickname);
	}

	private void processRequestConsultaSalida(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
		String salida = req.getParameter("salida").replace("---", " ");
		String actividad="";
		
		DataGeneric cats;
		try {
			cats = port.listarCategorias();
			List<String> listCats=cats.getArr();
			Iterator<String> iteratorCat=listCats.iterator();
			while(iteratorCat.hasNext()) {
				DataGeneric actividades;
				String nomCatUs=iteratorCat.next();
				try {
					actividades = port.listarActividadesCategoria(nomCatUs);
					List<String> listAct=actividades.getArr();
					Iterator<String> iteratorAct=listAct.iterator();
					
					while(iteratorAct.hasNext()) {
						String nomact=iteratorAct.next();
						if(port.existeSalidaAct(nomact, salida)) {
							actividad=nomact;
						}
					}
					if(!actividad.equals("")) {
						break;
					}
				} catch (NoExistenActividadesException_Exception e) {
				}
			}
		} catch (NoExistenCategoriasException_Exception e2) {
		}
		
		int costo = port.obtenerDatosActividad(actividad).getCostoPT();
		req.setAttribute("costo", costo);
		DataSalida dataSalida = port.obtenerDatosSalida(actividad, salida);
		req.setAttribute("datosSalida", dataSalida);
		byte[] photo=null;
		try {
			photo = port.getFile(port.getURLSalida(salida, actividad));
		} catch (IOException_Exception e1) {
			e1.printStackTrace();
		}
		String urls=Base64.getEncoder().encodeToString(photo);
		req.setAttribute("urlImagen",urls );
		
		req.getRequestDispatcher("/WEB-INF/salidas/ConsultaSalida.jsp").forward(req, res);
		
	}
	
	
	

	
	private void processRequestConsultaActividad(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        DataUsuario usr = (DataUsuario) req.getSession().getAttribute("usuario");        	
        
		String actividad = req.getParameter("actividad").replace("---", " ");
		DataActividad DTAct = port.obtenerDatosActividad(actividad);
		int cantFav = DTAct.getCantFavoritos();
		req.setAttribute("cantFav", cantFav);
		req.setAttribute("datosActividad", DTAct);
		boolean tur = false;
		
		if((usr != null) && (port.existeTurista(usr.getNickname()))) {
			tur = true;
			DataGeneric favs;
			String [] favoritas = null;
			try {
				favs = port.listaractividadesFavoritas(usr.getNickname());
				List<String> favs2 = favs.getArr();
				Iterator<String> iter1 = favs2.iterator();
				favoritas = new String[favs2.size()];
				int k = 0;
				while(iter1.hasNext()) {
					favoritas[k] = iter1.next();
					k++;
				}
				req.setAttribute("favoritas", favoritas);
			} catch (UsuarioSinActividadesFavoritasException_Exception e) {
				
			}
			int j = 0;
			while((j < favoritas.length) && (!favoritas[j].equals(actividad))) { //capaz poner equals
				j++;
			}
			boolean esFav; //falso es que no esta en sus favs
			if(j >= favoritas.length) {
				esFav = false;
			}else esFav = true;
			req.setAttribute("esFav", esFav);
		}else {
			req.setAttribute("esFav", false);
		}
		
		req.setAttribute("tur", tur);
		DataGeneric categorias=null;
		try {
			categorias = port.listarCategoriasActividad(DTAct.getNombre());
			List<String> lista=categorias.getArr();
			Iterator<String> iterador=lista.iterator();
			int i=0;
			String[] catAct=new String[lista.size()];
			while(iterador.hasNext()) {
				catAct[i]=iterador.next();
				i++;
			}
			
			req.setAttribute("categorias", catAct);
		} catch (NoExistenCategoriasException_Exception e) {
		}
		
		DataGeneric salidasAct=null;
		try {
			salidasAct = port.listarSalidasActividad(DTAct.getNombre());
			List<String> lista=salidasAct.getArr();
			Iterator<String> iterador=lista.iterator();
			int i=0;
			String[] devSal=new String[lista.size()];
			while(iterador.hasNext()) {
				devSal[i]=iterador.next();
				i++;
			}
			
			req.setAttribute("salidas", devSal);
		} catch (NoExistenSalidasEnActividadException_Exception e) {
		}			
		
		DataGeneric paquetesAct=null;
		try {
			paquetesAct = port.listarPaquetesActividad(DTAct.getNombre());
			List<String> lista=paquetesAct.getArr();
			Iterator<String> iterador=lista.iterator();
			int i=0;
			String[] paqAct=new String[lista.size()];
			while(iterador.hasNext()) {
				paqAct[i]=iterador.next();
				i++;
			}
			
			req.setAttribute("paquetes", paqAct);			
		} catch (NoExistenPaquetesEnLaActividadException_Exception e) {
		}
		
		byte[] photo=null;
		try {
			photo = port.getFile(port.getURLActividad(DTAct.getNombre()));
		} catch (IOException_Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String urls=Base64.getEncoder().encodeToString(photo);

		req.setAttribute("imgURL", urls);
		req.getRequestDispatcher("/WEB-INF/actividades/ConsultaActividad.jsp").forward(req, res);
		
	}
	
	
	
	
	private void processRequestComprobante(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
        String nickname = req.getParameter("nickname");
		String salida = req.getParameter("salida").replace("---", " ");
		
		DataSalida datosSalida = port.obtenerDatosSalidaTur(nickname, salida);
		int inscriptos  = port.obtenerCantInscriptos(nickname, salida);
		String nombreActividad = port.obtenerNombreActividad(salida);
		
		String anioSalida = Integer.toString(datosSalida.getAnioSalida());
		
		String mesSalida = Integer.toString(datosSalida.getMesSalida());
		if (datosSalida.getMesSalida() < 10) {
			mesSalida = "0" + mesSalida;
		}
		
		String diaSalida = Integer.toString(datosSalida.getDiaSalida());
		if (datosSalida.getDiaSalida() < 10) {
			diaSalida = "0" + diaSalida;
		}
		
		String horaSalida = Integer.toString(datosSalida.getHora());
		if (datosSalida.getHora() < 10) {
			horaSalida = "0" + horaSalida;
		}
		
		String minutosSalida = Integer.toString(datosSalida.getMinuto());
		if (datosSalida.getMinuto() < 10) {
			minutosSalida = "0" + minutosSalida;
		}

		
        DataUsuario datos = port.obtenerDatosUsr(nickname);
        
        String nombre = datos.getNombre();
		
        OutputStream out=res.getOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            
            
            String BASE_FONT_BOLD = "Trebuchet MS B";
            Font titlefontSmall = FontFactory.getFont(BASE_FONT_BOLD, 14, Font.UNDERLINE);
            
            Paragraph paragraph0 = new Paragraph("Comprobante de Inscripción", titlefontSmall);
            paragraph0.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragraph05 = new Paragraph("______________________________________________________________________________");
            Paragraph espacio = new Paragraph("\n");

            Paragraph paragraph1 = new Paragraph("• " + "Nombre del Turista Inscripto:");
            Paragraph paragraph2 = new Paragraph("  " + nombre);
            Paragraph paragraph3 = new Paragraph("\n");
            Paragraph paragraph4 = new Paragraph("• " + "Nombre de la Actividad:");
            Paragraph paragraph5 = new Paragraph("  " + nombreActividad);
            Paragraph paragraph6 = new Paragraph("\n");
            Paragraph paragraph7 = new Paragraph("• " + "Nombre de la Salida:");
            Paragraph paragraph8 = new Paragraph("  " + salida);
            Paragraph paragraph9 = new Paragraph("\n");
            Paragraph paragraph10 = new Paragraph("• " + "Fecha y hora de la Salida:");
            Paragraph paragraph11 = new Paragraph("  " + diaSalida + "/" + mesSalida + "/" + anioSalida + " - " + horaSalida + ":" + minutosSalida);
            Paragraph paragraph12 = new Paragraph("\n");
            Paragraph paragraph13 = new Paragraph("• " + "Cantidad de Turistas inscriptos:");
            Paragraph paragraph14 = new Paragraph("  " + inscriptos);
            Paragraph paragraph15 = new Paragraph("\n");
            
            document.add(paragraph0);
            document.add(paragraph05);
            document.add(espacio);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(paragraph5);
            document.add(paragraph6);
            document.add(paragraph7);
            document.add(paragraph8);
            document.add(paragraph9);
            document.add(paragraph10);
            document.add(paragraph11);
            document.add(paragraph12);
            document.add(paragraph13);
            document.add(paragraph14);
            document.add(paragraph15);
            
            
            String url = "https://s03.s3c.es/imag/_v0/770x420/d/d/5/ivan-disney.jpg";
            Image image = Image.getInstance(url);    
            image.scaleAbsolute(363, 230);
            document.add(image); 
            
            
            document.close();
        }
            catch (DocumentException exc){
            throw new IOException(exc.getMessage());
        }
        finally {            
            out.close();
        }		
	}
	
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		
		String parametro = request.getParameter("parametro");
		String nickname = request.getParameter("nickname");
		if(parametro.equals("altaUsuario")) {
			processRequestAltaUsuario1(request, response);
		}else if(parametro.equals("ConsultaUsuarios")) {
			processRequestConsultaUsuarios(request,response);
		}else if(parametro.equals("ConsultaUsuario")&&port.existeNickName(nickname)){
			try {
				processRequestConsultaUsuario(request,response,nickname);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(parametro.equals("ConsultaSalida")){
			processRequestConsultaSalida(request, response);
		}else if(parametro.equals("ConsultaActividad")){
			processRequestConsultaActividad(request, response);
		}else if(parametro.equals("ActividadFavorita")) {
			processRequestActividadFavorita(request, response);
		}else if(parametro.equals("akshadka")) {
			String act = request.getParameter("optjui");
			processRequestModificarDatosActividad(request, response, act.replace("---", " "));
		}else if(parametro.equals("seguir") || parametro.equals("dejarseguir")){
			String seguir = request.getParameter("seguido");
			boolean bol = false;
			if (parametro.equals("seguir")) {
				bol = true;
			}
			processRequestSeguirYDejarDeSeguir(request, response, seguir, bol);
		}else if(parametro.equals("fav") || parametro.equals("nofav")) {
			String act = request.getParameter("favss").replace("---", " ");
			boolean bol = false;
			if (parametro.equals("fav")) {
				bol = true;
			}
			processRequestMarcarDesmarcarActFav(request, response, act, bol);
		}else if(parametro.equals("Comprobante")){
			processRequestComprobante(request, response);
		}else {
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametro = request.getParameter("parametro");
		if (parametro == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);
		}else if(parametro.equals("altaUsr")){
			processRequestAltaUsuario(request, response);
		}else if(parametro.equals("modificarDatos")){
			String nick=request.getParameter("nick");
			processRequestModificarDatos(request, response,nick);
		}else if(parametro.equals("modificarDatosActividad")) {
			String act = request.getParameter("actividad");
			processRequestModificarDatosActividad(request, response, act);
		}else{
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);
		}
	}
}

