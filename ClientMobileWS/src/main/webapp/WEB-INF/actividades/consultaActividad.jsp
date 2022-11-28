<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="servidor.DataActividad"%>
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
    DataActividad actividad = (DataActividad) request.getAttribute("datosActividad");
    String imgURL = (String) request.getAttribute("imgURL");
    %>
    <main class="todo">
        <div class="contenedor">
            <h2 class="nombre-actividad">&bull; <%=actividad.getNombre()%> &bull;</h2>
            <p class="imagen"><img class="imagen" id='base64image'src='data:image/png;base64,<%=imgURL%>'  alt="foto-salida"></p>
            <p class="parrafo">Duracion:<%=actividad.getDuracion()%></p>
            <p class="parrafo">Costo:$<%=actividad.getCostoPT()%></p>
            <p class="parrafo">Lugar:<%=actividad.getCiudad()%></p>
            <%if (request.getAttribute("paquetes") != null | request.getAttribute("salidas") != null) {
           	%>
					<h2 class="salidas">&bull; Paquetes &bull;</h2>
                    <%
            		if(request.getAttribute("paquetes") != null) {
            		//String actividadElegida = (String) request.getAttribute("actividadElegida");
   						String [] paquetes = (String[]) request.getAttribute("paquetes");
 
                    	for (int k = 0; k < paquetes.length; k++) {
                    %>
                     <a class="nom-salida" href="#"><%=paquetes[k]%></a></td>
                  
                    <%
                    	}
                    } else {
                    %>
                   
                       <p class="parrafo">La actividad no cuenta con paquetes asociados</p>

                   
                    <%
                    }
                    %>	
                       <h2 class="salidas">&bull; Salidas &bull;</h2>
                    <%
                    
            		if(request.getAttribute("salidas") != null) {
            		//String actividadElegida = (String) request.getAttribute("actividadElegida");
   						String [] salidas = (String[]) request.getAttribute("salidas");
           			
                    	for (int k = 0; k < salidas.length; k++) {
                    %>
        
                    <a class="nom-salida" href="salidas?direccionar=ConsultaSalida&actividadElegida=<%=actividad.getNombre()%>&salida=<%=salidas[k]%>"><%=salidas[k]%></a></td>
                   
                    <%
                    	}
                    } else {
                    %>

                        <p class="parrafo">La actividad no cuenta con salidas asociadas</p>

                <%
                    }
                } else {
                %>
                <p class="parrafo">No existen actividades o paquetes asociados a la actividad</p>
                <%
                }
                %>
        </div>
    </main>
    <script src="media/js/script.js"></script>
</body>
</html>