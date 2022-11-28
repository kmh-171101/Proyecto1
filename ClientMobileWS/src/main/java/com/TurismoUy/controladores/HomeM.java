package com.TurismoUy.controladores;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TurismoUy.model.EstadoSesion;

import servidor.DataUsuario;
import servidor.IOException_Exception;

@WebServlet ("/homeM")
public class HomeM extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public HomeM() {
		super();
	}
	
	
	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		if(req.getParameter("direccionar")==null) {
			req.getRequestDispatcher("/WEB-INF/home/iniciarSesionM.jsp").forward(req, res);
		}else {
			switch(req.getParameter("direccionar")) {
			case("turista"):
				req.getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(req, res);
				break;
			case("sesion"):
				req.getRequestDispatcher("/WEB-INF/home/inicioSesionM.jsp").forward(req, res);
				break;
			case("cierre"):
				req.getSession().invalidate();
				req.getSession().setAttribute("imagen", null);
				req.getSession().setAttribute("usuario", null);
				req.getRequestDispatcher("/WEB-INF/home/inicioSesionM.jsp").forward(req, res);
				break;
			}
		}
	}
	
	public void processRequest2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("email");
		String pass = req.getParameter("password");
		
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		
		DataUsuario usuario = null;
    	if (id == "" || pass == "") {
			req.getRequestDispatcher("/WEB-INF/home/inicioSesionM.jsp").forward(req, res);
    	}else if((!port.existeNickName(id)) && (!port.existeEmail(id))) {
			req.getRequestDispatcher("/WEB-INF/home/inicioSesionErroneo.jsp").forward(req, res);
		} else {
			if(port.existeNickName(id)) {
				usuario = port.obtenerDatosUsr(id);
			} else {
				usuario = port.obtenerDatosEmail(id);
				id = usuario.getNickname();
			}
			if(!usuario.getPassword().equals(pass) || port.existeProveedor(usuario.getNickname())) {
				req.getRequestDispatcher("/WEB-INF/home/inicioSesionErroneo.jsp").forward(req, res);
			} else {
				req.getSession().setAttribute("usuario", usuario);
				
				String url=port.obtenerUrlUsuario(id);
				byte[] photo;
				try {
					photo = port.getFile(url);
					String bphoto = Base64.getEncoder().encodeToString(photo);
					req.getSession().setAttribute("imagen", bphoto);
				}catch (IOException_Exception e) {
					e.printStackTrace();
				}
					req.getRequestDispatcher("/WEB-INF/home/indexVisitante.jsp").forward(req, res);
				
			}
		}
			
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest2(request, response);
	}
}