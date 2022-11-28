<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servidor.DataActividad"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/consultaAct.css">
<link rel="stylesheet" href="media/styles/indexTurista.css">
<link rel="stylesheet" href="media/styles/indexVisitante.css">
<link rel="stylesheet" href="media/styles/indexProveedor.css">
<title>Turismo UY</title>
</head>
</head>
<body>

	<jsp:include page="/WEB-INF/template/header.jsp" />

	<%
    DataActividad actividad = (DataActividad) request.getAttribute("datosActividad");
	int cantF = (int) request.getAttribute("cantFav");
    String imgURL = (String) request.getAttribute("imgURL");
    boolean fav = (boolean) request.getAttribute("esFav");    
    boolean tur = (boolean) request.getAttribute("tur");
    %>

	<main>
		<h1 class="tituloCAT">&bull; Detalles de la Actividad Turistca
			&bull;</h1>
		<div class="ConsultaActividad">
			<img id='base64image'src='data:image/png;base64,<%=imgURL%>' class="imagenActividad">
			<p>
			<table class="Detalles">
				<tr>
					<td NOWRAP>Nombre de la Actividad Turistica:</td>
					<td NOWRAP><%=actividad.getNombre()%></td>
				</tr>
				<tr>
					<td NOWRAP>Duracion:</td>
					<td NOWRAP><%=actividad.getDuracion()%></td>
				</tr>
				<tr>
					<td NOWRAP>Costo:</td>
					<td NOWRAP><%=actividad.getCostoPT()%></td>
				</tr>
				<tr>
					<td NOWRAP>Ciudad a realizarse:</td>
					<td NOWRAP><%=actividad.getCiudad()%></td>
				</tr>
				<tr>
					<td NOWRAP>Fecha de alta de la Actividad:</td>
					<td NOWRAP><%=actividad.getAnio()%>/<%=actividad.getMes()%>/<%=actividad.getDia()%></td>
				</tr>
				<tr>
					<td NOWRAP>Cantidad de Favoritos:</td>
					<td NOWRAP><%=cantF%></td>
				</tr>
				<%
				if(tur){
					if(fav){ %>
				<tr>
					<td NOWRAP><a href="actividades?parametro=nofav&actividad=<%=actividad.getNombre().replace(" ","---")%>">Desmarcar como Favorita</a></td>
				<% } else{%>
					<td NOWRAP><a href="actividades?parametro=fav&actividad=<%=actividad.getNombre().replace(" ","---")%>">Marcar como Favorita</a></td>
				</tr>
					<%} 
				}%>
			</table>
			</p>
		</div>
		
		<%if(actividad.getEnlace() != null) {%>
            <div class="Lst">
            	<table>
            		<tr>
            			<td NOWRAP>Video asociado a la actividad:</td>
            		</tr>
            		<tr>
            			<td><iframe width="560" height="315" src=<%=actividad.getEnlace()%>></iframe></td>
            		</tr>
            	</table>
            </div>
            
        <%} %>
		
		<div class="Lst">
			<label>Categorias</label>
			<%
            	if(request.getAttribute("categorias") != null) {%>
			<table class="listaCats">
				<%
            		String actividadElegida = (String) request.getAttribute("actividadElegida");
   					String [] categorias = (String[]) request.getAttribute("categorias");
            		for (int k = 0; k < categorias.length; k++) {%>
				<tr>
					<td>
						<p><%=categorias[k]%></p>
					</td>
				</tr>
				<%}%>
			</table>

			<%
                } 
                %>
		</div>
		<div class=Lst>
			<%
            if (request.getAttribute("paquetes") != null | request.getAttribute("salidas") != null) {
            %>
			<table class="listas">
				<tr>
					<th>Seleccione uno para ver sus detalles</th>
				</tr>
				<tr>
					<th>Paquetes</th>
				</tr>
				<%
            		if(request.getAttribute("paquetes") != null) {
            		String actividadElegida = (String) request.getAttribute("actividadElegida");
   						String [] paquetes = (String[]) request.getAttribute("paquetes");
 
                    	for (int k = 0; k < paquetes.length; k++) {
                    %>
				<tr>
					<td NOWRAP><a href="#"><%=paquetes[k]%></a></td>
				</tr>
				<%
                    	}
                    } else {
                    %>
				<tr>
					<td NOWRAP>La actividad no cuenta con paquetes asociados</td>
				</tr>
				<%
                    }
                    %>
				<tr>
					<th>Salidas</th>
				</tr>
				<%
                    
            		if(request.getAttribute("salidas") != null) {
            		String actividadElegida = (String) request.getAttribute("actividadElegida");
   						String [] salidas = (String[]) request.getAttribute("salidas");
           			
                    	for (int k = 0; k < salidas.length; k++) {
                    %>
				<tr>
					<td NOWRAP><a
						href="salidas?direccionar=ConsultaSalida&actividadElegida=<%=actividad.getNombre()%>&salida=<%=salidas[k]%>"><%=salidas[k]%></a></td>
				</tr>
				<%
                    	}
                    } else {
                    %>
				<tr>
					<td NOWRAP>La actividad no cuenta con salidas asociadas</td>
				</tr>
			</table>
			<%
                    }
                } else {
                %>
			<p>No existen Salidas o paquetes asociados a la actividad</p>
			<%
                }
                %>
		</div>

	</main>

</body>
</html>
