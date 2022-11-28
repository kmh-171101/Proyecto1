<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection"%>
<%@page import="servidor.DataUsuario"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/indexProovedor.css">
<link rel="stylesheet" href="media/styles/indexVisitante.css">
<link rel="stylesheet" href="media/styles/altaActividadTuristica.css">
<title>Turismo UY</title>
</head>
<body>
	<%
     	DataUsuario usr = (DataUsuario) session.getAttribute("usuario");
     %>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<main>
		<div id="container">
			<h1>&bull; Alta actividad Turistica &bull;</h1>
			<div class="underline"></div>
			<form action="actividades?parametro=altaAct" method="POST"
				enctype="multipart/form-data" id="altaActividad">
				<div class="derecha">
					<label for="name"></label> <input type="text" placeholder="Nombre"
						name="nom" id="name" required>
				</div>
				<div class="izquierda">
					<label for="ciudad"></label> <input type="text"
						placeholder="Ciudad" name="ciudad" id="ciudad" required>
				</div>
				<div class="izquierda">
					<label for="costo"></label> <input type="text" placeholder="Costo"
						name="costo" id="costo" required>
				</div>
				<div class="derecha">
					<label for="duracion"></label> <input type="text"
						placeholder="Duracion" name="duracion" id="duracion" required>
				</div>

				<div class="subjet">
					<% 
            String [] dep=(String [])request.getAttribute("departamentos");%>
					<label for="departamento"></label> <select
						placeholder="Departamento" name="departamento" id="departamento"
						required>
						<optgroup label="Seleccione un Departamento">
							<option disabled hidden selected>Departamento</option>
							<%	
					for(int i = 0; i < dep.length; i++){		
    					%>
							<option><%= dep[i] %></option>
							<% } 
				%>
						</optgroup>
					</select>
				</div>
				<div class="subjet">
					<label for="fecha"></label> <input type="file" placeholder="Imagen"
						name="imagen" id="imagen">
				</div>
				<section class="contenedorCategorias underlines">
					<h2 class="titleCategorias">Seleccione una o mas categorías</h2>
					<div class="categorias">
						<ul>
							<%
		        String[] categorias = (String[])request.getAttribute("categorias");
		          for(int i = 0; i < categorias.length; i++){		
					%>
							<li><input type="checkbox" id="<%= categorias[i] %>"
								name="categorias" class="categorias" id="categorias"
								value="<%= categorias[i] %>"> <label
								for="<%= categorias[i] %>"><%= categorias[i] %></label></li>
							<% } 
 				%>
						</ul>
					</div>
				</section>
				<div class="subjet" hidden>
					<input hidden type="text" placeholder="cater" name="cater"
						id="cater">
				</div>
				<div class="subjet">
					<label for="DescripcionGeneral"></label> <input type="text"
						placeholder="Descripcion" name="Descripcion" id="des" required>

				</div>
				<div  class="subjet">
                	<label for="URLEnlace"></label>
                	<input type="text" placeholder="enlace" name="enlace" id="enl">

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
			  document.getElementById("altaActividad").addEventListener('submit', validarFormulario); 
		});
		function validarFormulario(evento) {
			evento.preventDefault();
			const costo=document.getElementById("costo").value;
			const duracion=document.getElementById("duracion").value;
			const dep=document.getElementById("departamento").value;
			
			if(document.getElementById("enl").value.length == 0){
				document.getElementById("enl").value = null;
			}
			
			var correctoEnlace1 = document.getElementById("enl").value.includes("embed");
			var correctoEnlace2 = document.getElementById("enl").value.includes("youtube");
			
			var elementos=document.querySelectorAll(".categorias");
			var catsEnv="";
			
			elementos.forEach((e)=>{
				if(e.checked== true){
					catsEnv=catsEnv+",,,"+e.value;
				}
			});
			var cater = document.getElementById("cater");
			cater.value=catsEnv;
			if(isNaN(costo)){
				alert("Costo debe ser un número, ingreselo nuevamente por favor.");				
				document.getElementById("costo").value="";
				return;
			}else if(costo<=0){
				alert("Costo debe ser un numero mayor a 0.");				
				document.getElementById("costo").value="";
				return;
			}else if(isNaN(duracion)){
				alert("Duración debe ser un número, ingreselo nuevamente por favor.");				
				document.getElementById("duracion").value="";
				return;
			}else if(duracion<=0){
				alert("Duracion debe ser un numero mayor a 0.");				
				document.getElementById("duracion").value="";
				return;
			}else if(dep==null || dep=="Departamento" || dep.length == 0 || /^\s+$/.test(dep)){
				alert("Debe seleccionar un departamento.");
				return;
			}else if(catsEnv==""){
				alert("Debe seleccionar al menos una categoria.");
				return;
			}else if((correctoEnlace1 == false) || (correctoEnlace2 == false)){
				alert("El enlace debe ser embebido válido de Youtube o vacío.");
				return;
			}else{		
				this.submit();				
			}
		}
    </script>
</body>
</html>