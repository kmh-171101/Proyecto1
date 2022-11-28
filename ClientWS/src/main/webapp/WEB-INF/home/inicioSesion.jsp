<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="media/styles/inicioSesion.css">
<link rel="stylesheet" type="text/css" href="media/styles/style.css">
<title>Turismo.uy</title>
</head>
<body>
	<header>
		<nav class="navegacion">
			<a class="titulo" href="home?direccionar=visitante"><img
				class="bandera" src="media/images/icon.png" alt="Bandera de Uruguay">Turismo
				UY</a>
		</nav>
	</header>

	<br>

	<main>
		<body>
			<div id="container">
				<h1>&bull; Iniciar Sesión &bull;</h1>
				<div class="underline"></div class="datos">
				<form name="login" method="post" action="home">
					<div class="email">
						<label for="email"></label> <input type="text"
							placeholder="Email o Nickname" name="email" id="email_input"
							required>
					</div>
					<div class="contraseña">
						<label for="contraseña"></label> <input type="password"
							placeholder="Contraseña" name="password" id="contraseña_input"
							required>
					</div>
					<div class="registro">
						<p>¿No tienes una cuenta?</p>
						<a href="usuario?parametro=altaUsuario">Registrate aquí</a>
					</div>
					<div class="botones">
						<div class="submit">
							<a href="home?direccionar=visitante" class="cancelar"><input
								type="button" value="Cancelar" id="form_button" /></a>
						</div>
						<div class="submit">
							<input type="submit" value="Enviar" id="form_button"
								onclick="submit()" />
						</div>
					</div>
				</form>
			</div>
	</main>


</body>
</html>