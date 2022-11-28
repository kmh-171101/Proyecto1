<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/consultaATs.css">
<title>Turismo UY</title>
</head>
<body>

	<jsp:include page="/WEB-INF/template/header.jsp" />

	<main>
		<h1 class="tituloCAT">&bull; Consulta De Actividad Turistca
			&bull;</h1>

		<div class="ConsultaAct">
			<label for="consultaAct">Seleccione un Departamento o una
				Categoria</label>
			<table class="tablaDepCat">
				<tr>
					<td>
						<%
                        	String [] dep=(String [])request.getAttribute("departamentos");
							String[] categorias = (String[])request.getAttribute("categorias");
							String aux;
							%>
						<form name="form1" action="actividades" method="get">
							<select name="DepCat" id="elegir"
								onchange="document.form1.submit()">
								<%
                        			if(request.getAttribute("dato1") != null) {
                            		%>
								<option value=<%=request.getAttribute("dato1")%> selected
									disabled hidden><%=request.getAttribute("dato1")%></option>

								<%
                            		} else {
                            		%>
								<option value="ninguno"></option>
								<% } %>
								<optgroup label="Departamentos">
									<%
										for(int i = 0; i < dep.length; i++){
											aux = dep[i].replace(" ", "---");
		    					%>
									<option value=<%=aux%>><%= dep[i] %></option>
									<% } %>
								</optgroup>
								<optgroup label="Categorias">
									<%
		    					   for(int i = 0; i < categorias.length; i++){
		    						   aux = categorias[i].replace(" ", "---");
		    					%>
									<option value=<%=aux%>><%= categorias[i] %></option>
									<% }%>
								</optgroup>
							</select>
						</form>
					</td>
				</tr>
			</table>
		</div>
		<div class="Acts">
			<label for="Acts">Seleccione una Actividad</label>
			<table class="listasAT">
				<%
            	if(request.getAttribute("actividades") != null) {
            		//String actividadElegida = (String) request.getAttribute("actividadElegida");
            		String [] actis = (String []) request.getAttribute("actividades");
            		for (int k = 0; k < actis.length; k++) {
            	%>

				<tr>
					<td><a
						href="actividades?parametro=ConsultaActividad&actividad=<%=actis[k]%>"><%=actis[k]%></a>
					</td>
				</tr>

				<% 
                	}
                } else if (request.getParameter("direccionar") == null) { 
                %>
				<tr>
					<td>
						<p>
							No existen Actividades registradas</a>
					</td>
				</tr>
				<%
                }
            	%>
			</table>
		</div>
	</main>
</body>
</html>