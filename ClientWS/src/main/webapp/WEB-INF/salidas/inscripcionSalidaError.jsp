<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servidor.DataSalida"%>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/inscripcionSalida.css">
<title>Turismo UY</title>
</head>
<body onload="javascript:hideTable()">
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<main>
		<h1 class="titCS">Inscripcion a Salida Turistica</h1>
		<%if(request.getAttribute("mostrar")!=null){%>
		<p class="MensajeError"><%=(String) request.getAttribute("mostrar")%></p>
		<%}else{%>
		<p class="MensajeError">Excedio la capacidad o ya esta inscripto,
			intentelo nuevamente por favor.</p>
		<%}%>
		<div class="ConsultaSal">
			<label for="consultaSal">Seleccione un Departamento o una
				Categoria</label>
			<% String [] dep=(String[])request.getAttribute("departamentos"); %>
			<% String[] categorias = (String[])request.getAttribute("categorias"); 
            String aux;%>
			<table class="tablaDepCat">
				<tr>
					<td>
						<form name="form1" action="salidas" method="get">
							<select name="Depas" id="tipo" onChange="document.form1.submit()">
								<%
                        	if(request.getAttribute("depObtenido") != null) {
                            %>

								<option value=<%=request.getAttribute("depObtenido")%> selected
									disabled hidden><%=request.getAttribute("depObtenido")%></option>

								<% } else { %>

								<option value="ninguno" selected disabled hidden></option>

								<% } %>
								<optgroup label="Departamentos">
									<% for(int i = 0; i < dep.length; i++){ %>
									<%aux = dep[i].replace(" ", "---"); %>
									<option value=<%=aux%>><%=dep[i]%></option>
									<% } %>
								</optgroup>
								<optgroup label="Categorias">
									<% for(int i = 0; i < categorias.length; i++){	
                           	 	aux = categorias[i].replace(" ", "---");%>
									<option value=<%=aux%>><%=categorias[i]%></option>
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
					<td NOWRAP>
						<form name="form2" action="salidas" method="get">
							<select name="ActiAT" id="actividades"
								onchange="document.form2.submit()">
								<% if (request.getAttribute("noActs")== null){
	                        if(request.getAttribute("actSelect") != null) {
	                        %>

								<option value=<%=request.getAttribute("actSelect")%> selected
									disabled hidden><%=request.getAttribute("actSelect")%></option>

								<% } else { %>

								<option value="ninguno" selected disabled hidden></option>

								<%
                        	
	                        }
                    		if(request.getAttribute("actividades") != null) {
                    			String [] actividades = (String []) request.getAttribute("actividades");
                    			for (int j = 0; j < actividades.length; j++) {
                    				aux = actividades[j].replace(" ", "---");
                    		%>
								<option value=<%=aux%>><%=actividades[j]%></option>
								<% 
                    			}
                    		} 
                    		%>
							</select>
							<%if(request.getAttribute("depObtenido") != null) {
                     		   String lol=(String) request.getAttribute("depObtenido"); 
                     		   %>

							<input type="hidden" id="DepoCatElegido" name="selec"
								value=<%=lol.replace(" ", "---")%> />

							<% } 
                        	}else{%>
							<div class="erroracts">
								<p NOWRAP>No hay actividades registradas para este
									depatamento.</p>
							</div>
							<%}%>

						</form>
					</td>
				</tr>
			</table>
		</div>
		<div class="SalidasCons">
			<label for="ActividadesCons">Salidas disponibles:</label>
			<% DataSalida [] dtsals = (DataSalida []) request.getAttribute("datosSalidas"); %>
			<% if(dtsals != null) { 
				String Fecha;
			%>
			<table class="tablaSal">
				<tr>
					<td NOWRAP>Nombre:</td>
					<td NOWRAP>Fecha:</td>
					<td NOWRAP>Hora:</td>
					<td NOWRAP>Lugar:</td>
				</tr>
				<%for(int i = 0; i < dtsals.length; i++){ %>
				<tr>
					<%Fecha = (LocalDate.of(dtsals[i].getAnioSalida(), dtsals[i].getMesSalida(), dtsals[i].getDiaSalida())).format(DateTimeFormatter.ofPattern("dd LLLL yyyy"));%>
					<td NOWRAP><b><%= dtsals[i].getNombre()%></b></td>
					<td NOWRAP><%= Fecha%></td>
					<td NOWRAP><%= dtsals[i].getHora()%></td>
					<td NOWRAP><%= dtsals[i].getLugar()%></td>
				</tr>
				<% } %>
			</table>
			<% }%>
		</div>
		<div class="login">
			<form action="salidas?direccionar=inscribir" method="POST"
				id="subiendo">
				<table class="tabla">
					<tr>
						<td>Seleccione una salida</td>
						<td><select name="Salidas" id="tiposal"
							onChange="document.form3.submit()">

								<% if(request.getAttribute("datosSalidas") != null) {%>
								<% 
            		for (int k = 0; k < dtsals.length; k++) { %>
								<option><%=dtsals[k].getNombre() %></option>
								<% } %>
								<% } %>
						</select> <%if(request.getAttribute("actSelect") != null) { 
	      	   		String nomActK=(String)request.getAttribute("actSelect");
	      	   %> <input type="hidden" id="actelegida" name="actiSelect"
							value=<%=nomActK.replace(" ", "---")%> /> <% } %></td>
					</tr>
					<tr>
						<td>Cantidad de turistas a inscribir:</td>
						<td>Forma de pago:</td>
					</tr>
					<tr>
						<td><input type="number" id="turistas" name="turistas"
							placeholder="0" step="0" min="0" max="1000" /></td>
						<td><input type="radio" onClick='javascript:hideTable()'
							name="tipo">General</td>
						<!-- <td><input type="radio" onClick='javascript:showTable()' name="tipo">Por paquete</td> -->
					</tr>
					<tr>
						<td id="disponibles">Paquetes disponibles</td>
						<td>
							<% if(request.getAttribute("paqAct") != null) {
    			String [] paquetes = (String []) request.getAttribute("paqAct"); %>
							<table class="selecPaq" id="table">
								<% for (int j = 0; j < paquetes.length; j++) { %>
								<tr>
									<td><input type="radio" name="paquete"><%=paquetes[j] %></td>
								</tr>
								<% } %>
							</table> <% } %>
						</td>
					</tr>
					<tr>
						<td><a href="home?direccionar=turista"><input
								id="button2" type="button" name="cancelar" value="Cancelar" /></a></td>

						<td><input type="submit" value="Inscribirse" id="button" /></td>
					</tr>
				</table>
			</form>
		</div>
	</main>


	<script>
	function showTable(){
		document.getElementById('disponibles').style.visibility = "visible";
		document.getElementById('table').style.visibility = "visible";
	}
	function hideTable(){
		document.getElementById('disponibles').style.visibility = "hidden";
		document.getElementById('table').style.visibility = "hidden";
	}
	
	document.addEventListener("DOMContentLoaded", function() {
		  document.getElementById("subiendo").addEventListener('submit', validarFormulario); 
	});
	
	function validarFormulario(evento) {
		evento.preventDefault();  
		const actaSec=document.getElementById('actelegida');
		const numSec= document.getElementById("turistas");
		if(actaSec==null || actaSec.value==""){
			alert("Para continuar debe seleccionar una actividad");
			return;
		}else if(numSec.value==0){
			alert("Debe ingresar al menos un turista para la inscripcion.");
			return;
		}
		this.submit();
	}
	
</script>

</body>
</html>