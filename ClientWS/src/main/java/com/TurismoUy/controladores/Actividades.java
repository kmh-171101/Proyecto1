package com.TurismoUy.controladores;


import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import servidor.DataActividad;
import servidor.DataGeneric;
import servidor.DataUsuario;
import servidor.ExisteActividadException_Exception;
import servidor.IOException_Exception;
import servidor.NoExisteDepartamentoException_Exception;
import servidor.NoExisteProveedorException_Exception;
import servidor.NoExistenActividadesEnDepartamentoException_Exception;
import servidor.NoExistenActividadesException_Exception;
import servidor.NoExistenCategoriasException_Exception;
import servidor.NoExistenDepartamentosException_Exception;
import servidor.NoExistenPaquetesEnLaActividadException_Exception;
import servidor.NoExistenSalidasEnActividadException_Exception;
import servidor.UsuarioSinActividadesFavoritasException;
import servidor.UsuarioSinActividadesFavoritasException_Exception;


@WebServlet ("/actividades")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Actividades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Actividades() {
		super();
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
	
	private void processRequestAltaActividad(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
		DataGeneric deps;
		String []dep=null;
		try {
			deps = port.listarDepartamentos();
			List<String> depsSub=deps.getArr();
			Iterator<String> iter1=depsSub.iterator();
			dep=new String[depsSub.size()];
			int i=0;
			while(iter1.hasNext()) {
				dep[i]=iter1.next();
				i++;
			}
			req.setAttribute("departamentos", dep);
		} catch (NoExistenDepartamentosException_Exception e) {
		}
		
		DataGeneric cats;
		String []cat=null;
		try {
			cats = port.listarCategorias();
			int i=0;
			List<String> catSub=cats.getArr();
			Iterator<String> iter2=catSub.iterator();
			cat=new String[catSub.size()];
			while(iter2.hasNext()) {
				cat[i]=iter2.next();
				i++;
			}
			req.setAttribute("categorias", cat);
		} catch (NoExistenCategoriasException_Exception e) {
		}
		
		
		if (dep == null || dep.length == 0){
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexProveedor.jsp").forward(req, res);
		}else {			
			getServletContext().getRequestDispatcher("/WEB-INF/actividades/altaActividadAT.jsp").forward(req, res);
		}
	}
	
	private void processRequestAltaActividad2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();

        DataUsuario usr=(DataUsuario)req.getSession().getAttribute("usuario");
        
		String cats=req.getParameter("cater");
		
		String nombre = req.getParameter("nom");
		String depto = req.getParameter("departamento");
		String ciudad = req.getParameter("ciudad");
		String desc = req.getParameter("Descripcion");
		String enl = req.getParameter("enlace");
		String prov = usr.getNickname();
		int dur = Integer.parseInt(req.getParameter("duracion"));
		int costo = Integer.parseInt(req.getParameter("costo"));
		try {
			Part filePart = req.getPart("imagen");
    		String fileName="visitante.jpg";
    		LocalDate fecha=LocalDate.now();
    		
			port.darAltaActividadTuristica(nombre, depto, ciudad, desc, enl, prov, dur, costo, fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth(),cats);
			if(filePart!=null && filePart.getContentType().startsWith("image")) {
				fileName=getPreFileName();
				
				InputStream inputS = filePart.getInputStream();
    			byte[] imagen=inputS.readAllBytes();			
    			
    			port.guardarImagen(fileName+".jpg", imagen);
    			port.setURLActividad(nombre, fileName+".jpg");
			}else {
				port.setURLActividad(nombre,"vacioAct.webp");
			}
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexProveedor.jsp").forward(req, res);
		} catch (ExisteActividadException_Exception | NoExisteDepartamentoException_Exception | NoExisteProveedorException_Exception e) {
			DataGeneric deps;
			String []dep=null;
			try {
				deps = port.listarDepartamentos();
				List<String> depsSub=deps.getArr();
				Iterator<String> iter1=depsSub.iterator();
				dep=new String[depsSub.size()];
				int i=0;
				while(iter1.hasNext()) {
					dep[i]=iter1.next();
					i++;
				}
				req.setAttribute("departamentos", dep);
			} catch (NoExistenDepartamentosException_Exception e3) {
			}
			
			DataGeneric cats2;
			String []cat=null;
			try {
				cats2 = port.listarCategorias();
				int i=0;
				List<String> catSub=cats2.getArr();
				Iterator<String> iter2=catSub.iterator();
				cat=new String[catSub.size()];
				while(iter2.hasNext()) {
					cat[i]=iter2.next();
					i++;
				}
				req.setAttribute("categorias", cat);
			} catch (NoExistenCategoriasException_Exception e2) {
			}
			getServletContext().getRequestDispatcher("/WEB-INF/actividades/altaActividadATError.jsp").forward(req, res);
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
		
        String actividad = act;
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
	
	private void processRequestConsultaActividad(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        DataUsuario usr = (DataUsuario) req.getSession().getAttribute("usuario");        	
        
		String actividad = req.getParameter("actividad");
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
	
	
	private void processRequestConsultaActividades(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
//		String[] acts = IAct.listarActividadesConfirmadas();
//		req.setAttribute("actividades", acts);
		DataGeneric actsDep=null;
		if (req.getParameter("parametro") != null) {
			DataGeneric deps;
			String []dep=null;
			try {
				deps = port.listarDepartamentos();
				List<String> depsSub=deps.getArr();
				Iterator<String> iter1=depsSub.iterator();
				dep=new String[depsSub.size()];
				int i=0;
				while(iter1.hasNext()) {
					dep[i]=iter1.next();
					i++;
				}
				req.setAttribute("departamentos", dep);
			} catch (NoExistenDepartamentosException_Exception e) {
			}
			DataGeneric cats;
			String []cat=null;
			try {
				cats = port.listarCategorias();
				int i=0;
				List<String> catSub=cats.getArr();
				Iterator<String> iter2=catSub.iterator();
				cat=new String[catSub.size()];
				while(iter2.hasNext()) {
					cat[i]=iter2.next();
					i++;
				}
				req.setAttribute("categorias", cat);
			} catch (NoExistenCategoriasException_Exception e) {
			}
			getServletContext().getRequestDispatcher("/WEB-INF/actividades/consultaATs.jsp").forward(req, res);			
		} else {
			DataGeneric deps;
			String []dep=null;
			try {
				deps = port.listarDepartamentos();
				List<String> depsSub=deps.getArr();
				Iterator<String> iter1=depsSub.iterator();
				dep=new String[depsSub.size()];
				int i=0;
				while(iter1.hasNext()) {
					dep[i]=iter1.next();
					i++;
				}
				req.setAttribute("departamentos", dep);
			} catch (NoExistenDepartamentosException_Exception e) {
			}
			DataGeneric cats;
			String []cat=null;
			try {
				cats = port.listarCategorias();
				int i=0;
				List<String> catSub=cats.getArr();
				Iterator<String> iter2=catSub.iterator();
				cat=new String[catSub.size()];
				while(iter2.hasNext()) {
					cat[i]=iter2.next();
					i++;
				}
				req.setAttribute("categorias", cat);
			} catch (NoExistenCategoriasException_Exception e) {
			}
			
			String dc = req.getParameter("DepCat");
			dc = dc.replace("---", " ");
			req.setAttribute("dato1", dc);
			if(port.existeCategoria(dc)) {
				DataGeneric actGen;
				try {
					actGen = port.listarActividadesCategoria(dc);
					String[] actEnv=new String[actGen.getArr().size()];
					int i=0;
					List<String> listAcy=actGen.getArr();
					Iterator<String> iter2=listAcy.iterator();
					while(iter2.hasNext()) {
						actEnv[i]=iter2.next();
						i++;
					}
					req.setAttribute("actividades", actEnv);
				} catch (NoExistenActividadesException_Exception e) {
				}
			} else {
				try {
					actsDep = port.listarActividadesDepartameto(dc);
					String[] actEnv=new String[actsDep.getArr().size()];
					int i=0;
					List<String> listAcy=actsDep.getArr();
					Iterator<String> iter2=listAcy.iterator();
					while(iter2.hasNext()) {
						actEnv[i]=iter2.next();
						i++;
					}
					req.setAttribute("actividades", actEnv);
				} catch (NoExistenActividadesEnDepartamentoException_Exception e) {
				}
			}		
			getServletContext().getRequestDispatcher("/WEB-INF/actividades/consultaATs.jsp").forward(req, res);
		}
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametro = request.getParameter("parametro");
		if(request.getParameter("parametro") != null && parametro.equals("altaActividad")) {
			String[] acts = null;
			request.setAttribute("actividades", acts);
			processRequestAltaActividad(request, response);
		}else if(request.getParameter("parametro") != null && parametro.equals("ConsultaActividad")) {
			try {
				processRequestConsultaActividad(request, response);
			} catch (ServletException | IOException e) {
				getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);
			}
		}else if(request.getParameter("parametro") != null && parametro.equals("consultaActividades")) {
			try {
				processRequestConsultaActividades(request, response);
				
			} catch (ServletException | IOException e) {
				getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);
			}
		}else if(parametro == null) {
			if(request.getParameter("DepCat") != null) {
				try {
					processRequestConsultaActividades(request, response);
				} catch (ServletException | IOException e) {
					getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);
				}
			}
		}else if(parametro.equals("fav") || parametro.equals("nofav")) {
			String act = request.getParameter("actividad").replace("---", " ");
			boolean bol = false;
			if (parametro.equals("fav")) {
				bol = true;
			}
			processRequestMarcarDesmarcarActFav(request, response, act, bol);
		}else{	
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametro = request.getParameter("parametro");
		if (parametro == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexProveedor.jsp").forward(request, response);
		}else if(parametro.equals("altaAct")){
			try {
				processRequestAltaActividad2(request, response);
			} catch (ServletException | IOException e) {
				getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);
			}
		}else{
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexProveedor.jsp").forward(request, response);
		}
	}
}
