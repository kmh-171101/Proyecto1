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
	
	
	private void processRequestAltaSalida(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, NoExistenActividadesException_Exception, NoExistenDepartamentosException_Exception, NoExistenCategoriasException_Exception {
		
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		
		String depaelegido = (String) req.getParameter("name_departamento");
		
		String actielegida = (String) req.getParameter("actividadSelect");
		 
		String [] deps = convertirDataGenericArreglo(port.listarDepartamentos());
		
		
		if(depaelegido == null || depaelegido.equals("Departamento")) {
			req.setAttribute("departamentos", deps);
			if (deps == null || deps.length == 0){
				getServletContext().getRequestDispatcher("/WEB-INF/home/indexProveedor.jsp").forward(req, res);
			}else {
				getServletContext().getRequestDispatcher("/WEB-INF/salidas/altaSalida.jsp").forward(req, res);
			}
		}else if(actielegida==null || actielegida.equals("Actividad Turistica")){
			req.setAttribute("name_departamento", depaelegido);
			req.setAttribute("departamentos", deps);
			try {
				String [] actividades = convertirDataGenericArreglo(port.listarActividadesDepartameto(depaelegido));
				req.setAttribute("actividades",actividades);
			} catch (NoExistenActividadesEnDepartamentoException_Exception e) {
				String[] actividades=null;
				req.setAttribute("actividades",actividades);
			}
			getServletContext().getRequestDispatcher("/WEB-INF/salidas/altaSalida.jsp").forward(req, res);
		}else {
			req.setAttribute("departamentos", deps);
			req.setAttribute("name_departamento", depaelegido);
			req.setAttribute("actSelect", actielegida);
			getServletContext().getRequestDispatcher("/WEB-INF/salidas/altaSalida.jsp").forward(req, res);
		}
	}	
	public void casoInscripcionSalida(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
		if(req.getParameter("Depas") != null) { //caso uso inscripcionsalida
			String[] deps;
			try {
				
				deps = convertirDataGenericArreglo(port.listarDepartamentos());
		    	
			} catch (NoExistenDepartamentosException_Exception e1) {
				deps=null;
			}
			String[] cats;
			try {
				
				cats = convertirDataGenericArreglo(port.listarCategorias());
		    	
			} catch (NoExistenCategoriasException_Exception e1) {
				cats = null;
			}
			req.setAttribute("departamentos", deps);
			req.setAttribute("categorias", cats);
			
			String depObtenido = req.getParameter("Depas");
			depObtenido = depObtenido.replace("---", " ").replace("/", "");
			
			req.setAttribute("depObtenido", depObtenido);
			if(port.existeCategoria(depObtenido)) {
				String[] actividadesCat;
				try {
					
					actividadesCat = convertirDataGenericArreglo(port.listarActividadesCategoria(depObtenido));
			    	
					req.setAttribute("actividades", actividadesCat);
				} catch (NoExistenActividadesException_Exception e) {
					actividadesCat=null;
					req.setAttribute("actividades", actividadesCat);
				}
			}else {
				String[] actividadesDeps;
				try {
					
					actividadesDeps = convertirDataGenericArreglo(port.listarActividadesDepartameto(depObtenido));
					
					req.setAttribute("actividades", actividadesDeps);
				} catch (NoExistenActividadesEnDepartamentoException_Exception e) {
					req.setAttribute("noActs", "hola");
					e.printStackTrace();
				}
			}
			getServletContext().getRequestDispatcher("/WEB-INF/salidas/inscripcionSalida.jsp").forward(req, res);
		}else {
			String[] deps;
			try {
				
				deps = convertirDataGenericArreglo(port.listarDepartamentos());
				
			} catch (NoExistenDepartamentosException_Exception e1) {
				deps = null;
			}
			String[] cats;
			try {
				
				cats = convertirDataGenericArreglo(port.listarCategorias());
				
			} catch (NoExistenCategoriasException_Exception e1) {
				cats=null;
			}
			req.setAttribute("departamentos", deps);
			req.setAttribute("categorias", cats);
			
			String datoact = req.getParameter("ActiAT");
			datoact = datoact.replace("---", " ").replace("/", "");
			
			String elegido = req.getParameter("selec");
			elegido = elegido.replace("/", "");
			
			if(port.existeCategoria(elegido)) {
				String[] actividadesCat;
				try {
					
					actividadesCat = convertirDataGenericArreglo(port.listarActividadesCategoria(elegido));
					
					req.setAttribute("actividades", actividadesCat);
				} catch (NoExistenActividadesException_Exception e) {
					actividadesCat=null;
					req.setAttribute("actividades", actividadesCat);
				}
				
			}else {
				String[] actividadesDeps;
				try {
					
					actividadesDeps = convertirDataGenericArreglo(port.listarActividadesDepartameto(elegido));
					
					req.setAttribute("actividades", actividadesDeps);
				} catch (NoExistenActividadesEnDepartamentoException_Exception e) {
					req.setAttribute("noActs", "hola");
					e.printStackTrace();
				}
			}
			
			
			req.setAttribute("depObtenido", elegido);
			req.setAttribute("actSelect", datoact);
			int costo=port.obtenerDatosActividad(datoact).getCostoPT();
			req.setAttribute("costo", costo);
			
			
			/*req.setAttribute("actividades", actividadesDep);*/
				
			String[] salidasActi;
			try {

				salidasActi = convertirDataGenericArreglo(port.listarSalidasActividad(datoact));
				
				req.setAttribute("salidas", salidasActi);
			} catch (NoExistenSalidasEnActividadException_Exception e) {
				req.setAttribute("noSals", "hola");
			}
			try {
				DataSalida[] dtSal;
				
				dtSal = convertirDataGenericArraySalidas(port.listarDatosSalidasVigentes(datoact));
				
				req.setAttribute("datosSalidas", dtSal);
				
			} catch (NoHaySalidasDisponiblesException_Exception e) {
				req.setAttribute("datosSalidas", null);
			}
			try {
				
				String [] paqs = convertirDataGenericArreglo(port.listarPaquetesActividad(datoact));
				
				req.setAttribute("paqAct", paqs);				
			} catch (NoExistenPaquetesEnLaActividadException_Exception e) {
				req.setAttribute("paqAct", null);
			}
			
			req.getRequestDispatcher("/WEB-INF/salidas/inscripcionSalida.jsp").forward(req, res);
		}
	}
	
	
	private void casoInscribir(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		
		DataUsuario usr = (DataUsuario) req.getSession().getAttribute("usuario");
		String tur = usr.getNickname();
		String datoact = req.getParameter("actiSelect").replace("/", "");
		datoact = datoact.replace("---", " ");
		String sal = req.getParameter("Salidas");
		int canttur = Integer.parseInt(req.getParameter("turistas"));
		LocalDate fecha = LocalDate.now();
		int anio = fecha.getYear();
		int mes = fecha.getMonthValue();
		int dia = fecha.getDayOfMonth();
		try {
			port.inscribirASalida(tur, datoact, sal, canttur, anio, mes, dia);
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexTurista.jsp").forward(req, res);
		}catch(ExcedeCapacidadException_Exception e1) {
			String[] deps;
			try {
				deps = convertirDataGenericArreglo(port.listarDepartamentos());
			} catch (NoExistenDepartamentosException_Exception e11) {
				deps=null;
			}
			String[] cats;
			try {
				cats = convertirDataGenericArreglo(port.listarCategorias());
			} catch (NoExistenCategoriasException_Exception e11) {
				cats=null;
			}
			req.setAttribute("departamentos", deps);
			req.setAttribute("categorias", cats);
			getServletContext().getRequestDispatcher("/WEB-INF/salidas/inscripcionSalidaError.jsp").forward(req, res);
		}catch(YaTieneInscripcionAEstaSalidaException_Exception e) {
			String[] deps;
			try {
				deps = convertirDataGenericArreglo(port.listarDepartamentos());
			} catch (NoExistenDepartamentosException_Exception e12) {
				deps=null;
			}
			String[] cats;
			try {
				cats = convertirDataGenericArreglo(port.listarCategorias());
			} catch (NoExistenCategoriasException_Exception e13) {
				cats=null;
			}
			req.setAttribute("departamentos", deps);
			req.setAttribute("categorias", cats);
			getServletContext().getRequestDispatcher("/WEB-INF/salidas/inscripcionSalidaError.jsp").forward(req, res);
		}
	}
	
	
	
	private void processRequestAltaSalida2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, NoExisteDepartamentoException_Exception, NoExisteProveedorException_Exception, NoExistenDepartamentosException_Exception, NoExistenCategoriasException_Exception {
		
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		
		//fecha es fecha de alta, fecha alta es la fecha cuando se realiza la salida
		//f es fecha alta f2 es fecha de la salida
		
		String nombre = req.getParameter("nombre");
		int cantTuristas = Integer.parseInt(req.getParameter("cantidad"));
		
		LocalDate f =LocalDate.now();
		int anio = f.getYear();
		int mes = f.getMonthValue();
		int dia = f.getDayOfMonth();
		
		String fecha2 = req.getParameter("fechaAlta");
		int anio2 = Integer.parseInt(fecha2.split("-")[0]);
		int mes2 = Integer.parseInt(fecha2.split("-")[1]);
		int dia2 = Integer.parseInt(fecha2.split("-")[2]);
		LocalDate f2 =LocalDate.of(anio2, mes2, dia2);
		
		String horas = req.getParameter("hora");
		int hora = Integer.parseInt(horas.split(":")[0]);
		int min = Integer.parseInt(horas.split(":")[1]);
		String lugar = req.getParameter("salida");
		String dep = req.getParameter("departamento");
		String nomActividad = req.getParameter("actividadSelect");
		
		boolean existeSal = port.existeSalida(nombre);
		
		if(existeSal==false && !f.isAfter(f2)) {
			Part filePart = req.getPart("imagen");
    		String fileName="salidas.jpg";
    		port.darAltaSalidacarga(nomActividad, nombre, anio2, mes2, dia2, hora, min, cantTuristas, lugar, anio, mes, dia);
			if(filePart!=null && filePart.getContentType().startsWith("image")) {
				fileName=getPreFileName();
				InputStream inputS = filePart.getInputStream();
    			byte[] imagen=inputS.readAllBytes();			
    			
    			port.guardarImagen(fileName+".jpg", imagen);
    			port.setURLSalida(nombre,nomActividad, fileName+".jpg");
			}else {
				port.setURLSalida(nombre,nomActividad,"vacioAct.webp");
			}
			getServletContext().getRequestDispatcher("/WEB-INF/home/indexProveedor.jsp").forward(req, res);
		}else {
			String mal;
			if(existeSal) {
				mal="Ya existe el nombre de la salida, seleccione otro por favor.";
			}else {
				mal="La fecha de la salida es anterior a la actual, ingrese otra por favor.";
			}
			String[] deps = convertirDataGenericArreglo(port.listarDepartamentos());
			req.setAttribute("departamentos", deps);
			req.setAttribute("name_departamento", dep);
			req.setAttribute("actSelect", nomActividad);
			req.setAttribute("error", mal);
			getServletContext().getRequestDispatcher("/WEB-INF/salidas/altaSalida.jsp").forward(req, res);
		}
	}


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
			if((req.getParameter("Depas") != null) || (req.getParameter("ActiAT") != null)) {
				casoInscripcionSalida(req, res);
			}
			if(req.getParameter("name_departamento")!=null) {
				processRequestAltaSalida(req,res);
			}
			if(req.getParameter("actividadSelect")!=null) {
				try {
					processRequestAltaSalida2(req, res);
				} catch (ServletException | IOException | NoExisteDepartamentoException_Exception | NoExisteProveedorException_Exception
						| NoExistenDepartamentosException_Exception | NoExistenCategoriasException_Exception e) {
					e.printStackTrace();
				}
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
				req.getRequestDispatcher("/WEB-INF/salidas/ConsultaSalida.jsp").forward(req, res);
				break;
			case("altaSalida"):
				processRequestAltaSalida(req, res);
				break;
			case("altaSalida2"):
				try {
					processRequestAltaSalida2(req, res);
				} catch (ServletException | IOException | NoExisteDepartamentoException_Exception | NoExisteProveedorException_Exception
						| NoExistenDepartamentosException_Exception | NoExistenCategoriasException_Exception e) {
					e.printStackTrace();
				}
				break;
			case("inscripcionSalida"):
				String[] depas = convertirDataGenericArreglo(port.listarDepartamentos());
				String[] categs = convertirDataGenericArreglo(port.listarCategorias());
				req.setAttribute("departamentos", depas);
				req.setAttribute("categorias", categs);		
				getServletContext().getRequestDispatcher("/WEB-INF/salidas/inscripcionSalida.jsp").forward(req, res);
				break;
			case("inscribir"):
				casoInscribir(req, res);
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
