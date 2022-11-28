<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/indexTurista.css">
<link rel="stylesheet" href="media/styles/indexVisitante.css">
<link rel="stylesheet" href="media/styles/indexProveedor.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@700&display=swap"
	rel="stylesheet">
<title>Turismo UY</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />

	<section class="contenedorFoto">
		<div class="contenidoFoto">
			<h2>Acerca de nosotros</h2>
			<p>Empresa uruguaya destinada a brindar facilidad a la hora de
				buscar servicios turísticos dando la posibilidad a proveedores de
				ofrecer sus paquetes</p>
		</div>
		<svg class="wave" xmlns="http://www.w3.org/2000/svg"
			viewBox="0 0 1440 320">
				<path fill="#F2F3EB" fill-opacity="1"
				d="M0,160L48,176C96,192,192,224,288,218.7C384,213,480,171,576,165.3C672,160,768,192,864,218.7C960,245,1056,267,1152,245.3C1248,224,1344,160,1392,128L1440,96L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path>
          	</svg>
	</section>
	<section class="lugares">
		<h2>Lugares</h2>
		<div class="fotos">

			<div class="card">
				<figure>
					<img src="media/images/colonia.jpg" alt="Colonia">
				</figure>
				<h2>Colonia del sacramento</h2>
				<h3 class="departamento">Colonia</h3>
			</div>

			<div class="card">
				<figure>
					<img src="media/images/maldonado.jpg" alt="maldonado">
				</figure>
				<h2>Punta del Este</h2>
				<h3 class="departamento">Maldonado</h3>
			</div>

			<div class="card">
				<figure>
					<img src="media/images/rocha.webp" alt="rocha">
				</figure>
				<h2>Rocha</h2>
				<h3 class="departamento">Rocha</h3>
			</div>

		</div>
	</section>
</body>
</html>