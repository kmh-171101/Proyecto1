<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="servidor.DataUsuario"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="media/css/normalize.css">
    <link rel="stylesheet" href="media/css/index.css">
    <title>Turismo UY</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
    <main>
      	<%
  			DataUsuario usr=(DataUsuario) session.getAttribute("usuario");
  			
  		%>
        <h1>Bienvenid@ <%= usr.getNombre() %> ;)</h1>
    </main>
    <script src="media/js/script.js"></script>
</body>
</html>