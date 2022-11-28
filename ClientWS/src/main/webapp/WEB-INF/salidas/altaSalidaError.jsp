<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection"%>
<%@ page import="java.util.List"%>
<%@ page import="servidor.DataUsuario"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/indexProovedor.css">
<link rel="stylesheet" href="media/styles/indexVisitante.css">
<link rel="stylesheet" href="media/styles/altaSalida.css">
<title>Turismo UY</title>
</head>
<body>
	<%
     	DataUsuario usr = (DataUsuario) session.getAttribute("usuario");
     %>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<main>
		<div id="container">
			<h1>&bull; Alta de Salida Turistica &bull;</h1>
			<%if(request.getAttribute("error")!=null) {%>
			<div class="subject">
				<h4 class="MensajeError" id="error">
					&bull;
					<%=request.getAttribute("error") %>&bull;
				</h4>
			</div>
			<%} %>
			<div class="underline"></div>

			<div class="subject">
				<% 
		            String [] dep=(String [])request.getAttribute("departamentos");%>
				<label for="departamento"></label>
				<form name="form1" action="salidas?direccionar=altaSalida">
					<select placeholder="Seleccione un Departamento"
						name="name_departamento" id="departamento"
						onchange="document.form1.submit()">
						<%String depSelect=(String) request.getAttribute("name_departamento"); 
				            if(depSelect==null){%>
						<option disabled hidden selected>Departamento</option>
						<%if(dep!=null && dep.length>0){ 
							    	for(int i=0;i<dep.length;i++){%>
						<option><%=dep[i] %></option>
						<%}
						        }			            	
				            }else{
						        if(dep!=null && dep.length>0){ 
							    	for(int i=0;i<dep.length;i++){
							    		if(!depSelect.equals(dep[i])){%>
						<option><%=dep[i] %></option>
						<%}else{%>
						<option selected><%=dep[i] %></option>
						<%}
						        	}
						        }	
				            }%>
					</select>
				</form>
			</div>

			<form name="altaSalida" action="salidas?direccionar=altaSalida2"
				method="POST" enctype="multipart/form-data" id="altaSalidas">
				<div class="subject">
					<label for="actividad"></label>
					<%String[] actividades=(String[])request.getAttribute("actividades"); %>
					<select placeholder="Actividad Turistica" name="actividadSelect"
						id="actividad_input" required>
						<%String actSelect=(String) request.getAttribute("actSelect"); 
			            if(actSelect==null){%>
						<option disabled hidden selected>Actividad Turistica</option>
						<%if(actividades!=null && actividades.length>0){ 
						    	for(int i=0;i<actividades.length;i++){        %>
						<option><%=actividades[i] %></option>
						<%}
					        }			            	
			            }else{
					        if(actividades!=null && actividades.length>0){ 
						    	for(int i=0;i<actividades.length;i++){
						    		if(!actSelect.equals(actividades[i])){%>
						<option><%=actividades[i] %></option>
						<%}else{%>
						<option selected><%=actividades[i] %></option>
						<%}
					        	}
					        }	
			            }%>
					</select>
				</div>
				<div class="derecha">
					<label for="nombre"></label> <input type="text"
						placeholder="Nombre" name="nombre" id="nombre_input" required>
				</div>
				<div class="izquierda">
					<label for="cantidad"></label> <input type="text"
						placeholder="Cantidad Maxima de Turistas" name="cantidad"
						id="cantidad_input" required>
				</div>
				<div class="derecha">
					<label for="fecha"></label>
					<h2 class="titleCategorias">Seleccione fecha de la salida</h2>
					<input type="date" placeholder="Fecha de Salida" name="fechaAlta"
						id="fecha_input" required>
				</div>
				<div class="izquierda">
					<label for="hora"></label>
					<h2 class="titleCategorias">Seleccione hora de la salida</h2>
					<input type="time" placeholder="Hora de Salida" name="hora"
						id="hora_input" required>
				</div>
				<div class="subject">
					<label for="salida"></label> <input type="text"
						placeholder="Lugar de Salida" name="salida" id="salida_input"
						required>
				</div>
				<div class="imagen">
					<label for="fecha"></label> <input type="file" placeholder="Imagen"
						name="imagen" id="imagen">
				</div>
				<div class="botones">
					<div class="submit">
						<a href="home?direccionar=proveedor" class="cancelar"><input
							type="button" value="Cancelar" id="form_button" /></a>
					</div>
					<div class="submit">
						<input type="submit" value="Enviar" id="form_button" />
					</div>
				</div>
			</form>
		</div>
	</main>
	<script type="text/javascript">
	var selected = new Array();
    
	document.addEventListener("DOMContentLoaded", function() {
		  document.getElementById("altaSalOF").addEventListener('submit', validarFormulario); 
	});
	function validarFormulario(evento) {
		evento.preventDefault();
		const cant=document.getElementById("cantidad_input").value;
		const dep=document.getElementById("departamento").value;
		const act=document.getElementById("actividad_input").value;
		if(isNaN(cant)){
			alert("La cantidad debe ser un número, ingreselo nuevamente por favor.");				
			document.getElementById("cantidad_input").value="";
			return;
		}else if(dep==null || dep=="Departamento" || dep.length == 0 || /^\s+$/.test(dep)){
			alert("Debe seleccionar un departamento.");
			return;
		}else if(act==null || act=="Actividad Turistica" || dep.length == 0 || /^\s+$/.test(dep)){
			alert("Debes seleccionar una actividad.");
			return;
		}else if(cant<=0){
			alert("La cantidad debe ser mayor a cero.");				
			document.getElementById("cantidad_input").value="";
			return;
		}else{	
			this.submit();	
		}
	}
    </script>
</body>
</html>