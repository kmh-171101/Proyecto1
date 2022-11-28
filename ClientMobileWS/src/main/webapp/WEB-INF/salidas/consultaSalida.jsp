<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="servidor.DataSalida"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="media/css/normalize.css">
    <link rel="stylesheet" href="media/css/index.css">
    <link rel="stylesheet" href="media/css/ConsultaActividad.css">
    <title>Turismo UY</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	 <%
    DataSalida salida = (DataSalida) request.getAttribute("datosSalida");
    String aux = (String) request.getAttribute("urlImagen");
    %>
    <main class="todo">
        <div class="contenedor">
            <h2 class="nombre-actividad">&bull; <%=salida.getNombre()%> &bull;</h2>
            <p class="imagen"><img class="imagen" id='base64image'src='data:image/png;base64,<%=aux%>' alt="foto-salida"></p>
            <p class="parrafo">Fecha:<%=salida.getDiaSalida()%>/<%=salida.getMesSalida()%>/<%=salida.getAnioSalida()%></p>
            <p class="parrafo">Hora:<%=salida.getHora()%></p>
            <p class="parrafo">Turistas Maximos:<%=salida.getCantMaxT()%></p>
            <p class="parrafo">Lugar:<%=salida.getLugar()%></p>
            <p class="parrafo">Fecha alta:<%=salida.getDiaAlta()%>/<%=salida.getMesAlta()%>/<%=salida.getAnioAlta()%></p>
            <p class="parrafo">Costo por turista:<%=request.getAttribute("costo")%></p>
        </div>
    </main>
    <script src="media/js/script.js"></script>
</body>
</html>