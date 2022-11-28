<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servidor.DataUsuario"%>
<%@page import="servidor.DataActividad"%>
<%@ page import='com.TurismoUy.model.EstadoSesion'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/indexVisitante.css">
<link rel="stylesheet" href="media/styles/indexProovedor.css">
<link rel="stylesheet" href="media/styles/actividadFavorita.css">
<title>Turismo UY</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<h1 class="uno">&bull; Actividades &bull;</h1>
	<div class="divi">
		<%
        	DataActividad act = (DataActividad) request.getAttribute("actividad");
            DataActividad [] acts=(DataActividad [])request.getAttribute("strsr");
            String [] urls=(String[])request.getAttribute("urls");
            boolean[] boolfav = (boolean[])request.getAttribute("favs2");
            if(acts!=null){%>
		<table class="tablaUsr">
			<caption NOWRAP class="titTabla">Seleccione una Actividad</caption>
			<tr>
				<td NOWRAP>Imagen de la Actividad</td>
				<td NOWRAP>Nombre </td>
				<td NOWRAP>Descripcion</td>
				<td NOWRAP>Favoritas</td>
			</tr>
			<%for(int i = 0; i < acts.length; i++){
	            	%>
			<tr>
				<td><img id='base64image'src='data:image/png;base64,<%=urls[i]%>' class="imagenUsuario"></td>
				<td NOWRAP><a href="usuario?parametro=ConsultaActividad&actividad=<%=acts[i].getNombre().replace(" ","---") %>"><%=acts[i].getNombre() %></a></td>
				<td><%=acts[i].getDescripcion()%></td>
				<%if (!boolfav[i]) {%>
				<td NOWRAP><a href="usuario?parametro=fav&favss=<%=acts[i].getNombre().replace(" ","---")%>">Marcar como Favorita</a></td>
				<% } else{%>
				<td NOWRAP><a href="usuario?parametro=nofav&favss=<%=acts[i].getNombre().replace(" ","---")%>">Desmarcar como Favorita</a></td>
				<%} %>
			</tr>
			<%}%>
		</table>
		<%}else{%>
		<h3 class="mio2">NO HAY ACTIVIDADES REGISTRADAS EN EL SISTEMA</h3>
		<%}%>
	</div>
</body>
</html>
</html>