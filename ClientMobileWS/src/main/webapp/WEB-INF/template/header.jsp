<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="servidor.DataUsuario"%>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="media/css/normalize.css">
        <link rel="stylesheet" href="media/css/index.css">
    </head>
    <header>
  		<%
  			DataUsuario usr=(DataUsuario) session.getAttribute("usuario");
  		%>
        <nav class="nav-bar">
            <ul>
                <div class="toggle-menu" id="toggle-menu">
                    <img class="icon" src="media/img/menu.png" alt="menu">
                </div>
                <li><a href="homeM?direccionar=turista"><%= usr.getNombre() %> <%= usr.getApellido() %> </a></li>
                <li><a href="homeM?direccionar=turista"><img class="bandera" src="media/img/icon.png" alt="bandera"></a></li>
            </ul>
        </nav>
        <div class="main-nav">
            <ul class="main-menu" id="main-menu">
                <li class="main-menu_item"><a class="main-menu_link" href="actividades?parametro=consultaActividades">Ver Actividades Turisticas</a></li>
                <li class="main-menu_item"><a class="main-menu_link" href="salidas?direccionar=consultaSalidas">Ver Salidas Turisticas</a></li>
                <li class="main-menu_item"><a class="main-menu_link" href="homeM?direccionar=cierre">Cerrar Sesion</a></li>
            </ul>
        </div>
    </header>
    <script src="media/js/script.js"></script>
</html>

