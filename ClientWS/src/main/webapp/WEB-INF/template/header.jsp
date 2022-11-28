<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.TurismoUy.model.EstadoSesion"%>
<%@page import="servidor.DataUsuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/indexVisitante.css">
<link rel="stylesheet" href="media/styles/indexTurista.css">
<link rel="stylesheet" href="media/styles/indexProveedor.css">
</head>
<header>
	<nav class="navegacion">
		<%
            	DataUsuario usr = (DataUsuario) session.getAttribute("usuario");
            	if(usr==null){ //no hay usuario ingresado%>
		<a class="titulo" href="home?direccionar=visitante"><img
			class="bandera" src="media/images/icon.png" alt="Bandera de Uruguay">Turismo
			UY</a>
		<div class="perfil">
			<a class="nomPerfil" href="home?direccionar=visitante"><img
				class="imgPerfil" src="media/images/visitante.jpg"
				alt="Usuario Visitante">Usuario Visitante</a>
		</div>
		<ul class="menu">
			<li><a href=usuario?parametro=altaUsuario>Alta
					de Usuario</a></li>
			<li><a href="#">Consultas</a>
				<ul class="Consultas">
					<li><a href="usuario?parametro=ConsultaUsuarios">Consulta
							de usuarios</a></li>
					<li><a href="actividades?parametro=consultaActividades">Consulta
							de actividades</a></li>
					<li><a href="salidas?direccionar=consultaSalidas">Consulta
							de salidas</a></li>
					<!-- <li><a href="#">Consulta de paquetes</a></li>  -->
				</ul></li>
			<li><a href="home?direccionar=sesion">Iniciar Sesión</a></li>
		</ul>
		<%}else{
            		String imagen=(String) session.getAttribute("imagen");
            		EstadoSesion hola=(EstadoSesion) request.getSession().getAttribute("estado_sesion");
            		if(hola.equals(EstadoSesion.LOGIN_PROVEEDOR)){%>
		<a class="titulo" href="home?direccionar=proveedor"><img
			class="bandera" src="media/images/icon.png" alt="Bandera de Uruguay">Turismo
			UY</a>
		<div class="perfil">
			<a class="nomPerfil"
				href="usuario?parametro=ConsultaUsuario&nickname=<%=usr.getNickname()%>"><img
				class="imgPerfil" id='base64image'
				src='data:image/png;base64,<%=imagen%>'><%=usr.getNombre()%> <%=usr.getApellido()%></a>
		</div>
		<ul class="menu">
			<li><a href="#">Altas</a>
				<ul class="Altas">
					<li><a
						href=actividades?parametro=altaActividad>Alta
							de activiad turistica</a></li>
					<li><a href=salidas?direccionar=altaSalida>Alta
							de salida turistica</a></li>
				</ul></li>
			<li><a href="#">Consultas</a>
				<ul class="Consultas">
					<li><a href="usuario?parametro=ConsultaUsuarios">Consulta
							de usuarios</a></li>
					<li><a href="actividades?parametro=consultaActividades">Consulta
							de actividades</a></li>
					<li><a href="salidas?direccionar=consultaSalidas">Consulta
							de salidas</a></li>
					<!-- <li><a href="#">Consulta de paquetes</a></li>  -->
				</ul></li>
			<li><a href="home?direccionar=cierre">Cerrar Sesión</a></li>
		</ul>
		<%}else{%>
		<a class="titulo" href="home?direccionar=turista"><img
			class="bandera" src="media/images/icon.png" alt="Bandera de Uruguay">Turismo
			UY</a>
		<div class="perfil">
			<a class="nomPerfil"
				href="usuario?parametro=ConsultaUsuario&nickname=<%=usr.getNickname()%>"><img
				class="imgPerfil" id='base64image'
				src='data:image/png;base64,<%=imagen%>'><%=usr.getNombre()%> <%=usr.getApellido()%></a>
		</div>
		<ul class="menu">
			<li><a href="#">Consultas</a>
				<ul class="Consultas">
					<li><a href="usuario?parametro=ConsultaUsuarios">Consulta
							de usuarios</a></li>
					<li><a href="actividades?parametro=consultaActividades">Consulta
							de actividades</a></li>
					<li><a href="salidas?direccionar=consultaSalidas">Consulta
							de salidas</a></li>
					<!-- <li><a href="#">Consulta de paquetes</a></li>  -->
				</ul></li>
			<li><a href="salidas?direccionar=inscripcionSalida">Inscripcion
					a salida</a></li>
					<li><a href="usuario?parametro=ActividadFavorita">Actividades Favoritas</a></li>
			<!-- <li><a href="#">Comprar paquete</a></li>-->
			<li><a href="home?direccionar=cierre">Cerrar Sesión</a></li>
		</ul>
		<%}      		
            	}%>
	</nav>
</header>
</html>

