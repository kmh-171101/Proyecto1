<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servidor.DataSalida"%>
<%@page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/consultaSalida.css">
<title>Turismo UY</title>
</head>
</head>
<body>

	<jsp:include page="/WEB-INF/template/header.jsp" />

	<%
    DataSalida salida = (DataSalida) request.getAttribute("datosSalida");
    String aux = (String) request.getAttribute("urlImagen");
    %>

	<h1 class="titST">&bull; Detalles de Salida Turistica &bull;</h1>
	<div class="ConsultaSalida">
		<img id='base64image'src='data:image/png;base64,<%=aux%>' class="imagenSalida">
		<table class="Detalles">
			<tr>
				<td NOWRAP>Nombre Salida Turistica:</td>
				<td NOWRAP><%=salida.getNombre()%></td>
			</tr>
			<tr>
				<td NOWRAP>Fecha-Hora a realizarse:</td>
				<td NOWRAP><%=LocalDate.of(salida.getAnioSalida(), salida.getMesSalida(), salida.getDiaSalida())%> - <%if(salida.getHora() < 10){%>0<%}%><%=salida.getHora()%>:<%if(salida.getMinuto() < 10){%>0<%}%><%=salida.getMinuto()%></td>
			</tr>
			<tr>
				<td NOWRAP>Cantidad maxima de turistas:</td>
				<td NOWRAP><%=salida.getCantMaxT()%></td>
			</tr>
			<tr>
				<td NOWRAP>Lugar de la Salida Turistica:</td>
				<td NOWRAP><%=salida.getLugar()%></td>
			</tr>
			<tr>
				<td NOWRAP>Fecha de alta de la Salida:</td>
				<td NOWRAP><%=LocalDate.of(salida.getAnioAlta(), salida.getMesAlta(), salida.getDiaAlta())%></td>
			</tr>
			<tr>
				<td NOWRAP>Costo por turista:</td>
				<td NOWRAP><%=request.getAttribute("costo")%></td>
			</tr>
		</table>
	</div>

	<main></main>

</body>
</html>
