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
	
	private void processRequestConsultaActividad(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
		String actividad = req.getParameter("actividad");
		DataActividad DTAct = port.obtenerDatosActividad(actividad);
		req.setAttribute("datosActividad", DTAct);
		
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
		req.getRequestDispatcher("/WEB-INF/actividades/consultaActividad.jsp").forward(req, res);
		
		
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
			getServletContext().getRequestDispatcher("/WEB-INF/actividades/consultaActividades.jsp").forward(req, res);			
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
					DataActividad[] arrUsr=new DataActividad[actEnv.length];
		        	String [] urls=new String[actEnv.length];
		        	for(int j=0;j<actEnv.length;j++) {
		        		arrUsr[j]=port.obtenerDatosActividad(actEnv[j]);
		        		byte[] photo;
						try {
							photo = port.getFile(port.getURLActividad(actEnv[j]));
							urls[j]=Base64.getEncoder().encodeToString(photo);
						} catch (IOException_Exception e) {
						}
		        	}
		        	req.setAttribute("urls", urls);
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
					DataActividad[] arrUsr=new DataActividad[actEnv.length];
		        	String [] urls=new String[actEnv.length];
		        	for(int j=0;j<actEnv.length;j++) {
		        		arrUsr[j]=port.obtenerDatosActividad(actEnv[j]);
		        		byte[] photo;
						try {
							photo = port.getFile(port.getURLActividad(actEnv[j]));
							urls[j]=Base64.getEncoder().encodeToString(photo);
						} catch (IOException_Exception e) {
						}
		        	}
		        	req.setAttribute("urls", urls);
					req.setAttribute("actividades", actEnv);
				} catch (NoExistenActividadesEnDepartamentoException_Exception e) {
				}
			}		
			getServletContext().getRequestDispatcher("/WEB-INF/actividades/consultaActividades.jsp").forward(req, res);
		}
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametro = request.getParameter("parametro");
		if(request.getParameter("parametro") != null && parametro.equals("ConsultaActividad")) {
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
		}else {	
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(request, response);
	}
}
