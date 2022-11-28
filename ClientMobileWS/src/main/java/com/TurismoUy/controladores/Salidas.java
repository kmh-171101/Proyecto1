package com.TurismoUy.controladores;

import java.io.File;
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
import servidor.DataGenericArray;
import servidor.DataSalida;
import servidor.DataUsuario;
import servidor.ExcedeCapacidadException_Exception;
import servidor.IOException_Exception;
import servidor.NoExisteDepartamentoException_Exception;
import servidor.NoExisteProveedorException_Exception;
import servidor.NoExistenActividadesEnDepartamentoException;
import servidor.NoExistenActividadesEnDepartamentoException_Exception;
import servidor.NoExistenActividadesException;
import servidor.NoExistenActividadesException_Exception;
import servidor.NoExistenCategoriasException;
import servidor.NoExistenCategoriasException_Exception;
import servidor.NoExistenDepartamentosException;
import servidor.NoExistenDepartamentosException_Exception;
import servidor.NoExistenPaquetesEnLaActividadException;
import servidor.NoExistenPaquetesEnLaActividadException_Exception;
import servidor.NoExistenSalidasEnActividadException;
import servidor.NoExistenSalidasEnActividadException_Exception;
import servidor.NoHaySalidasDisponiblesException;
import servidor.NoHaySalidasDisponiblesException_Exception;
import servidor.YaTieneInscripcionAEstaSalidaException;
import servidor.YaTieneInscripcionAEstaSalidaException_Exception;

@WebServlet ("/salidas")

@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Salidas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Salidas() {
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
	
	
	private String[] convertirDataGenericArreglo(DataGeneric aux) {
		List<String> aux2 = aux.getArr();
		Iterator<String> iterador = aux2.iterator();
    	String [] resultado = new String[aux2.size()];
    	int i=0;
    	while(iterador.hasNext()) {
    		resultado[i]=iterador.next();
    		i++;
    	}
		return resultado;
	}
	
	private DataSalida[] convertirDataGenericArraySalidas(DataGenericArray aux) {
	 	List<DataSalida> aux2 = aux.getSalidas();
		Iterator<DataSalida> iterador = aux2.iterator();
    	DataSalida [] resultado = new DataSalida[aux2.size()];
    	int i=0;
    	while(iterador.hasNext()) {
    		resultado[i]=iterador.next();
    		i++;
    	}
		return resultado;
	}
	
	/*private DataActividad[] convertirDataGenericArrayActividad(DataGenericArray aux) {
	 	List<DataActividad> aux2 = aux.getArr();
		Iterator<DataActividad> iterador = aux2.iterator();
		DataActividad [] resultado = new DataActividad[aux2.size()];
		int i=0;
		while(iterador.hasNext()) {
			resultado[i]=iterador.next();
			i++;
		}
		return resultado;
	}*/
	
	
	public void casoConsultaSalidas(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, NoExistenDepartamentosException_Exception {

		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		
		String[] deps = convertirDataGenericArreglo(port.listarDepartamentos());
		        
		String[] cats;
		try {
			cats = convertirDataGenericArreglo(port.listarCategorias());
		} catch (NoExistenCategoriasException_Exception e1) {
			cats = null;
		}
		
		req.setAttribute("departamentos", deps);
		req.setAttribute("categorias", cats);
				
		if(req.getParameter("DepCat") != null) {  //Caso de uso consultaSalidas, cambió el select de Departamentos/Actividades
			
			String dato1 = req.getParameter("DepCat");
			dato1 = dato1.replace("-", " ");
			
			if(port.existeCategoria(dato1)) {
				try {
					req.setAttribute("actividades", convertirDataGenericArreglo(port.listarActividadesCategoria(dato1)));
				} catch (NoExistenActividadesException_Exception e) {
					req.setAttribute("noTieneActividadesCategoria", true);
					req.setAttribute("actividades", null);
				}
			} else {
				try {
					req.setAttribute("actividades", convertirDataGenericArreglo(port.listarActividadesDepartameto(dato1)));
				} catch (NoExistenActividadesEnDepartamentoException_Exception e) {
					req.setAttribute("noTieneActividadesDepartamento", true);
					req.setAttribute("actividades", null);
				}
			}
			
			req.setAttribute("dato1", dato1);
			getServletContext().getRequestDispatcher("/WEB-INF/salidas/consultaSalidas.jsp").forward(req, res);
			
		} else {      //Caso de uso consultaSalidas, cambió el select de Actividades
			
			String dato2 = req.getParameter("Acti");
			dato2 = dato2.replace("-", " ");
			
			String elegido = req.getParameter("elegido");
			elegido = elegido.replace("/", "");
			elegido = elegido.replace("-", " ");
			
			if(port.existeCategoria(elegido)) {
				try {
					req.setAttribute("actividades", convertirDataGenericArreglo(port.listarActividadesCategoria(elegido)));
				} catch (NoExistenActividadesException_Exception e) {
					req.setAttribute("noTieneActividadesCategoria", true);
					req.setAttribute("actividades", null);
				}
			} else {
				try {
					req.setAttribute("actividades", convertirDataGenericArreglo(port.listarActividadesDepartameto(elegido)));
				} catch (NoExistenActividadesEnDepartamentoException_Exception e) {
					req.setAttribute("noTieneActividadesDepartamento", true);
					req.setAttribute("actividades", null);
				}
			}
			
			
			req.setAttribute("dato1", elegido);
			req.setAttribute("actividadElegida", dato2);
				
			String[] salidasAct;
			try {
				salidasAct = convertirDataGenericArreglo(port.listarSalidasActividad(dato2));
			} catch (NoExistenSalidasEnActividadException_Exception e) {
				req.setAttribute("noTieneSalidas", true);
				salidasAct = null;
			}
			DataSalida[] arrUsr=new DataSalida[salidasAct.length];
        	String [] urls=new String[salidasAct.length];
        	for(int j=0;j<salidasAct.length;j++) {
        		arrUsr[j]=port.obtenerDatosSalida(dato2,salidasAct[j]);
        		byte[] photo;
				try {
					photo = port.getFile(port.getURLSalida(salidasAct[j],dato2));
					urls[j]=Base64.getEncoder().encodeToString(photo);
				} catch (IOException_Exception e) {
				}
        	}
        	req.setAttribute("urls", urls);
			req.setAttribute("salidas", salidasAct);
			req.getRequestDispatcher("/WEB-INF/salidas/consultaSalidas.jsp").forward(req, res);
		}


	}
	
	
	
	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, NoExistenCategoriasException_Exception, NoExistenDepartamentosException_Exception, NoExistenActividadesException_Exception, NoExistenActividadesEnDepartamentoException_Exception, NoExistenSalidasEnActividadException_Exception, NoHaySalidasDisponiblesException_Exception, NoExistenPaquetesEnLaActividadException_Exception /*ExcedeCapacidadException_Exception, YaTieneInscripcionAEstaSalidaException_Exception*/ {
		
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		
		if(req.getParameter("direccionar") == null) {  //Precisa trabajar dentro de un caso de uso, no redireccionarlo a otro

			if((req.getParameter("DepCat") != null) || (req.getParameter("Acti") != null)) {  //Caso de uso consultaSalidas, cambió el select de Departamentos/Actividades
				casoConsultaSalidas(req, res);		
			}
			
		} else {
			
			
			switch(req.getParameter("direccionar")) {
			case("consultaSalidas"):
				String[] deps = convertirDataGenericArreglo(port.listarDepartamentos());
				String[] cats = convertirDataGenericArreglo(port.listarCategorias());
				req.setAttribute("departamentos", deps);
				req.setAttribute("categorias", cats);
				if ((deps.length == 0) && (cats.length == 0)){
					getServletContext().getRequestDispatcher("/WEB-INF/home/indexProveedor.jsp").forward(req, res);
				}else {			
					getServletContext().getRequestDispatcher("/WEB-INF/salidas/consultaSalidas.jsp").forward(req, res);
				}
				break;
			case("ConsultaSalida"):
				String actividad = req.getParameter("actividadElegida");
				String salida = req.getParameter("salida");
				int costo = port.obtenerDatosActividad(actividad).getCostoPT();
				req.setAttribute("costo", costo);
				DataSalida dataSalida = port.obtenerDatosSalida(actividad, salida);       //O_O   !!!!!!!!!!!!!!!
				req.setAttribute("datosSalida", dataSalida);
				byte[] photo=null;
				try {
					photo = port.getFile(port.getURLSalida(salida, actividad));
				} catch (IOException_Exception e1) {
					e1.printStackTrace();
				}
				String urls=Base64.getEncoder().encodeToString(photo);
				req.setAttribute("urlImagen",urls );
				req.getRequestDispatcher("/WEB-INF/salidas/consultaSalida.jsp").forward(req, res);
				break;

			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExistenCategoriasException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExistenDepartamentosException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExistenActividadesException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("noTieneActividadesCategoria", true);
			request.getRequestDispatcher("/WEB-INF/salidas/consultaSalidas.jsp").forward(request, response);
		} catch (NoExistenActividadesEnDepartamentoException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("noTieneActividadesDepartamento", true);
			request.getRequestDispatcher("/WEB-INF/salidas/consultaSalidas.jsp").forward(request, response);
		} catch (NoExistenSalidasEnActividadException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("noTieneSalidas", true);
			request.getRequestDispatcher("/WEB-INF/salidas/consultaSalidas.jsp").forward(request, response);
		} catch (NoHaySalidasDisponiblesException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExistenPaquetesEnLaActividadException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | NoExistenCategoriasException_Exception | NoExistenDepartamentosException_Exception | NoExistenActividadesException_Exception | NoExistenActividadesEnDepartamentoException_Exception | NoExistenSalidasEnActividadException_Exception e) {
			e.printStackTrace();
		} catch (NoHaySalidasDisponiblesException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExistenPaquetesEnLaActividadException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
