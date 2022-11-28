<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servidor.DataUsuario"%>
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
<link rel="stylesheet" href="media/styles/consultaUsuarios.css">
<title>Turismo UY</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<h1 class="uno">&bull; Consulta de Usuario &bull;</h1>
	<div class="divi">
		<%
        	DataUsuario usr = (DataUsuario) session.getAttribute("usuario");
            DataUsuario [] usrs=(DataUsuario [])request.getAttribute("strsr");
            String [] urls=(String[])request.getAttribute("urls");
            if(usrs!=null){%>
		<table class="tablaUsr">
			<caption NOWRAP class="titTabla">Seleccione un Usuario</caption>
			<tr>
				<td NOWRAP>Nombre y Apellido</td>
				<td NOWRAP>Nickname</td>
				<td NOWRAP>Imagen del Usuario</td>
			</tr>
			<%for(int i=0;i<usrs.length;i++){
	            	%>
			<tr>
				<td NOWRAP><a
					href="usuario?parametro=ConsultaUsuario&nickname=<%=usrs[i].getNickname()%>"><%=usrs[i].getNombre()%>
						<%=usrs[i].getApellido()%></a></td>
				<td><%=usrs[i].getNickname()%></td>
				<td><img id='base64image'src='data:image/png;base64,<%=urls[i]%>' class="imagenUsuario"></td>
			</tr>
			<%}%>
		</table>
		<%}else{%>
		<h3 class="mio2">NO HAY USUARIOS REGISTRADOS EN EL SISTEMA</h3>
		<%}%>
	</div>
</body>
</html>
</html>
