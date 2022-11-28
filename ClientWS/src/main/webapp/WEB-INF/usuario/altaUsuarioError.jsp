<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/altaUsuario.css">
<title>Turismo UY</title>
</head>
<body onload='javascript:hideTable()'>
	<header>
		<nav class="navegacion">
			<a class="titulo" href="/tarea_2_dinamica"><img class="bandera"
				src="media/images/icon.png" alt="Bandera de Uruguay">Turismo
				UY</a>
			<div class="perfil">
				<a class="nomPerfil" href="#"><img class="imgPerfil"
					src="media/images/visitante.jpg" alt="Usuario Visitante">Usuario
					Visitante</a>
			</div>
			<ul class="menu">
				<li><a href="/tarea_2_dinamica">Iniciar Sesión</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<div id="container">
			<h1 id="uno">&bull; Alta De Usuario &bull;</h1>
			<%if(request.getAttribute("mostrar")!=null){%>
			<p class="MensajeError"><%=(String) request.getAttribute("mostrar")%></p>
			<%}else{%>
			<p class="MensajeError">El nickname o el email ya existen, elije
				otro por favor.</p>
			<%}%>
			<div class="underline"></div>
			<form action="usuario?parametro=altaUsr" method="POST"
				enctype="multipart/form-data" id="altaUsuario">
				<div class="derecha">
					<label for="nickname"></label>
					<%if(request.getAttribute("MalNick")!=null && !((String)request.getAttribute("MalNick")).equals("si")){%>
					<input type="text" placeholder="Nickname" name="Nickname"
						id="nickname_input" required
						value="<%= request.getAttribute("Nick")%>">
					<%}else{%>
					<input type="text" placeholder="Nickname" name="Nickname"
						id="nickname_input" required>
					<%} %>
				</div>
				<div class="izquierda">
					<label for="email"></label>
					<%if(request.getAttribute("MalEmail")!=null && !((String)request.getAttribute("MalEmail")).equals("si")){
                    %>
					<input type="text" placeholder="Email" name="email"
						id="email_input" required
						value="<%= request.getAttribute("ema")%>">
					<%}else{%>
					<input type="text" placeholder="Email" name="email"
						id="email_input" required>
					<%} %>
				</div>
				<div class="izquierda">
					<label for="apellido"></label> <input type="text"
						placeholder="Apellido" name="apellido" id="apellido_input"
						required value="<%= request.getAttribute("apell") %>">
				</div>
				<div class="derecha">
					<label for="Nombre"></label> <input type="text"
						placeholder="Nombre" name="Nombre" id="nombre_input" required
						value="<%= request.getAttribute("Nomb") %>">
				</div>
				<div class="derecha">
					<label for="contraseña1"></label> <input type="password"
						placeholder="Contraseña" name="contrasea1" id="contrasea1"
						required>
				</div>
				<div class="izquierda">
					<label for="contraseña2"></label> <input type="password"
						placeholder="Confirme contraseña" name="contraseña2"
						id="contraseña2" required>
				</div>
				<div class="derecha">
					<label for="fecha"></label> <input type="date"
						placeholder="Fecha de nacimiento" name="fecha" id="fecha" required
						pattern="\d{4}-\d{2}-\d{2}">
				</div>
				<div class="izquierda">
					<label for="fecha"></label> <input type="file" placeholder="Imagen"
						name="imagen" id="imagen">
				</div>
				<div class="subject">
					<label for="tipo"></label> <select name="tipo" id="tipo" required
						onChange="javascript:selectProvTur()">
						<option disabled selected>Seleccione tipo de usuario</option>
						<option value="Proveedor">Proovedor</option>
						<option value="Turista">Turista</option>
					</select>
				</div>
				<div class="derecha">
					<label for="DescripcionGeneral"></label> <input type="text"
						placeholder="Descripcion" name="Descripcion" id="des">
				</div>
				<div class="izquierda">
					<label for="urlPagina"></label> <input type="text"
						placeholder="Url de su pagina" name="Url" id="url">
				</div>
				<div class="subject">
					<label for="nacionalidad"></label> <input type="text"
						placeholder="Nacionalidad" name="Nacionalidad" id="nac">
				</div>
				<div class="botones">
					<div class="submit">
						<a href="/tarea_2_dinamica" class="cancelar"><input
							type="button" value="Cancelar" id="form_button" /></a>
					</div>
					<div class="submit">
						<input type="submit" value="Enviar" id="form_button" />
					</div>
				</div>
			</form>
		</div>
	</main>
	<script>
    	function selectProvTur(){
    		if(document.getElementById('tipo').value=="Proveedor"){
    			document.getElementById('nac').style.visibility = "hidden";
    			document.getElementById('nac').value="";
    			document.getElementById('des').style.visibility = "visible";
    			document.getElementById('url').style.visibility = "visible";
    		}else{
    			document.getElementById('des').style.visibility = "hidden";
    			document.getElementById('des').value="";
        		document.getElementById('url').style.visibility = "hidden";
        		document.getElementById('url').value="";
        		document.getElementById('nac').style.visibility = "visible";
    		}	
    	}
		function hideTable(){
			document.getElementById('des').style.visibility = "hidden";
			document.getElementById('nac').style.visibility = "hidden";
			document.getElementById('url').style.visibility = "hidden";
		}
		
		document.addEventListener("DOMContentLoaded", function() {
			  document.getElementById("altaUsuario").addEventListener('submit', validarFormulario); 
		});

		function validarFormulario(evento) {
		  evento.preventDefault();
		  const c1 = document.getElementById('contrasea1').value;
		  const c2 = document.getElementById('contraseña2').value;
		  const fecha= document.getElementById('fecha').value;
			
		  const desc=document.getElementById("des").value;
		  const nac=document.getElementById("nac").value;
		  const url=document.getElementById("url").value;
		  
 		  const anio=fecha.split('-')[0];
 		  const mes =fecha.split('-')[1];
 		  const dia =fecha.split('-')[2];
 		  
 		  const anioactual= new Date().getFullYear();
 		  const mesactual=new Date().getMonth()+1;
 		  const diaactual=new Date().getDate();
		  if(c1.localeCompare(c2)!=0) {
			  alert('Las contraseñas no coinciden.');
			  return;
		  }else if((anio>anioactual)||(anio==anioactual && mes>mesactual)||(anio==anioactual && mes==mesactual && dia>diaactual)) {
			  alert("La fecha debe ser anterior a la actual.");
			  return;
		  }else if(document.getElementById('tipo').value=="Proveedor" && (desc==null || desc.length == 0 || /^\s+$/.test(desc))){
			  alert("Tiene que rellenar el campo Descripcion.");
			  return;
		  }else if(document.getElementById('tipo').value=="Turista" && (nac==null || nac.length == 0 || /^\s+$/.test(nac))){
			  alert("Tiene que rellenar el campo Nacionalidad.");
			  return;
		  }else{			  
			  this.submit();
		  }
		}
    </script>
</body>
</html>