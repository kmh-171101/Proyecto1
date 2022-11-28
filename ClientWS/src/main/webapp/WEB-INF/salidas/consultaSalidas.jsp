<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/indexProovedor.css">
<link rel="stylesheet" href="media/styles/consultaSalidas.css">
<link rel="stylesheet" href="media/styles/indexTurista.css">
<title>Turismo UY</title>
</head>
<body>

	<jsp:include page="/WEB-INF/template/header.jsp" />

	<%
	String [] departamentos2 = (String[]) request.getAttribute("departamentos");
	String [] categorias2 = (String[]) request.getAttribute("categorias");
	String aux;
    %>

	<main>
		<h1 class="titCS">&bull; Consulta de Salidas &bull;</h1>

		<%if(request.getAttribute("noTieneActividadesDepartamento") != null) {
        %>

		<p class=MensajeError>El departamento seleccionado no tiene
			actividades asociadas, por favor elija uno diferente, seleccione otro
			caso de uso o vuelva al menú principal.</p>

		<% } %>

		<%if(request.getAttribute("noTieneActividadesCategoria") != null) {
        %>

		<p class=MensajeError>La categoria seleccionada no tiene
			actividades asociadas, por favor elija uno diferente, seleccione otro
			caso de uso o vuelva al menú principal.</p>

		<% } %>

		<%if(request.getAttribute("noTieneSalidas") != null) {
        %>

		<p class=MensajeError>La actividad seleccionada no tiene salidas
			asociadas, por favor elija una diferente, seleccione otro caso de uso
			o vuelva al menú principal.</p>

		<% } %>

		<div class="ConsultaSal">
			<label for="consultaSal">Seleccione un Departamento o una
				Categoria</label>
			<table class="tablaDepCat">
				<tr>
					<td>
						<form name="form1" action="salidas" method="get">
							<select name="DepCat" id="elegir"
								onchange="document.form1.submit()">

								<%
                        	if(request.getAttribute("dato1") != null) {
                            %>

								<option value=<%=request.getAttribute("dato1")%> selected
									disabled hidden><%=request.getAttribute("dato1")%></option>

								<% } else { %>

								<option value="ninguno" selected disabled hidden></option>

								<% } %>

								<optgroup label="Departamentos">

									<%for (int i = 0; i < departamentos2.length; i++) {
                            		aux = departamentos2[i].replace(" ", "-");
                            	%>

									<option value=<%=aux%>><%=departamentos2[i]%></option>

									<% } %>


								</optgroup>
								<optgroup label="Categorias">

									<%for (int j = 0; j < categorias2.length; j++) {
                            		aux = categorias2[j].replace(" ", "-");
                            	%>

									<option value=<%=aux%>><%=categorias2[j]%></option>

									<% } %>

								</optgroup>
							</select>
						</form>
					</td>
				</tr>
			</table>
		</div>
		<div class="ActividadesCons">
			<label for="ActividadesCons">Seleccione una Actividad</label>
			<table class="tablaAct">
				<tr>
					<td>
						<form name="form2" action="salidas" method="get">
							<select name="Acti" id="actividades"
								onchange="document.form2.submit()">

								<%
	                        if(request.getAttribute("actividadElegida") != null) {
	                        %>

								<option value="ninguno" selected disabled hidden><%=request.getAttribute("actividadElegida")%></option>

								<% } else { %>

								<option value="ninguno" selected disabled hidden></option>

								<%
                        	
	                        }
                        	
                        	if(request.getAttribute("actividades") != null) {
                        		String [] actividades = (String []) request.getAttribute("actividades");
                        		for (int j = 0; j < actividades.length; j++) {
                        			aux = actividades[j].replace(" ", "-");
                        	%>
								<option value=<%=aux%>><%=actividades[j]%></option>
								<% 
                        		}
                        	} 
                        	%>
							</select>


							<%if(request.getAttribute("dato1") != null) {
                        	aux = (String) request.getAttribute("dato1");
                        	aux = aux.replace(" ", "-");
                        %>

							<input type="hidden" id="DepCatElegido" name="elegido"
								value=<%=aux%> />

							<% } %>

						</form>
					</td>
				</tr>
			</table>
		</div>
		<div class="SalidasCons">
			<label for="ActividadesCons">Seleccione una Salida</label>
			<table class="tablaSal">
				<%
            	if(request.getAttribute("salidas") != null) {
            		String actividadElegida = (String) request.getAttribute("actividadElegida");
            		String [] salidas = (String []) request.getAttribute("salidas");
            		for (int k = 0; k < salidas.length; k++) {
            	%>

				<tr>
					<td><a
						href="salidas?direccionar=ConsultaSalida&actividadElegida=<%=request.getAttribute("actividadElegida")%>&salida=<%=salidas[k]%>"><%=salidas[k]%></a>
					</td>
				</tr>

				<%
                	}
                } 
                %>
			</table>
		</div>
	</main>
</body>

</html>