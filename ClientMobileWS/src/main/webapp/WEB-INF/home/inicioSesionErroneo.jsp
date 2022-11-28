<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="media/css/inicioSesion.css">
    <link rel="stylesheet" href="media/css/normalize.css">
    <title>Turismo UY</title>
</head>
<body>
  </div>
    <div class="login-box">
      <h1>Iniciar Sesión</h1>
      <form name="login" method="post" action="homeM" id="iniSes">
      	<p class="MensajeError">Usuario no existente o contraseña incorrecta, por favor inténtelo de nuevo.</p>
        <label for="email">Email o Nickname</label>
        <input type="text" name="email" placeholder="Ingrese su email" id="mail">
        <label for="password">Contraseña</label>
        <input type="password" name="password" placeholder="Ingrese su contraseña" id="pass">
         <input type="submit" value="Enviar" id="form_button" onclick="submit()"/>
      </form>
    </div>
    <script type="text/javascript">
    	//var selected = new Array();
    
		document.addEventListener("DOMContentLoaded", function() {
			  document.getElementById("iniSes").addEventListener('submit', validarFormulario); 
		});
		function validarFormulario(evento) {
			evento.preventDefault();
			const mail=document.getElementById("mail").value;
			const pass=document.getElementById("pass").value;

			if(mail == ""){
				alert("Debe completar el campo de email");				
				document.getElementById("mail").value="";
				return;
			}else if(pass == ""){
				alert("Debe ingresar la contraseña");				
				document.getElementById("pass").value="";
				return;
			}else{		
				this.submit();				
			}
		}
    </script>
  </body>
</html>