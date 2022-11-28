<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.TurismoUy.model.EstadoSesion"%>
<%@ page import="servidor.DataUsuario"%>
<%@page import="servidor.DataActividad"%>
<%@page import="servidor.DataSalida"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="media/styles/style.css">
<link rel="stylesheet" href="media/styles/indexVisitante.css">
<link rel="stylesheet" href="media/styles/indexProovedor.css">
<link rel="stylesheet" href="media/styles/consultaUsuarios.css">
<link rel="stylesheet" href="media/styles/consultaUsuario.css">
<title>Turismo UY</title>
</head>
<%String usrs=(String )request.getAttribute("strsr");
if(usrs.equals("si")){%>
<body onload="javascript:ocultar()">
<%}else{%>
<body onload="javascript:ocultar2()">
<%} %>

	<jsp:include page="/WEB-INF/template/header.jsp" />
	<%
    
    DataUsuario usrSesion = (DataUsuario) session.getAttribute("usuario");
    String nickSesion="";
    if(usrSesion!=null){
    	nickSesion=usrSesion.getNickname();
    }else{
    	nickSesion="";
    }
    
    String nickName1=(String )request.getAttribute("nickname");
    DataUsuario datosUsr=(DataUsuario)request.getAttribute("datosUsr");
    String url=(String)request.getAttribute("urlimagen");
    boolean [] pueFinalizar=(boolean[]) request.getAttribute("actsFin");
    String[] seguidores=(String[])request.getAttribute("seguidores");
	String[] seguidos=(String[])request.getAttribute("seguidos");
	boolean[] boolseg = (boolean[])request.getAttribute("boolSeg");
	boolean bol = (boolean)request.getAttribute("bol");
    
    if(usrs.equals("si")){%>
	<h1 class="titCS">&bull; Usuario &bull;</h1>
	<div class="ConsultaUsuario">
		<table class="tablaDatos">
			<tr>
				<td><img id='base64image'src='data:image/png;base64,<%=url%>'  class="imagenUsr"></td>
				<td>
					<table class="tablaDatos2">
						<tr>
							<td class="nombreGrande" NOWRAP><%=datosUsr.getNombre()%> <%=datosUsr.getApellido() %></td>
						</tr>
						<tr>
							<td NOWRAP><%=datosUsr.getEmail() %></td>
						</tr>
						<%if(!nickSesion.equals("") && !usrSesion.getNickname().equals(datosUsr.getNickname())) {%>
							<tr>
								<%if (!bol) {%>
									<td NOWRAP><a href="usuario?parametro=seguir&seguido=<%=datosUsr.getNickname()%>">Seguir</a></td>
								<% } else{%>
									<td NOWRAP><a href="usuario?parametro=dejarseguir&seguido=<%=datosUsr.getNickname()%>">Dejar de Seguir</a></td>
								<%} %>
							</tr>
						<%} %>
					</table>
				</td>
			</tr>
		</table>
	</div>

	<!-- Aqui va las pestañas -->
	<div class="ConsultaUsuario">
		<div id="tabbed_box_1" class="tabbed_box">
			<div class="tabbed_area">
				<%DataActividad[] actividades=(DataActividad[])request.getAttribute("actividades"); 
				DataSalida[] salidas=(DataSalida[])request.getAttribute("salAct");
				String[] costos=(String[])request.getAttribute("costosSal");
				String[] seguidores1=(String[])request.getAttribute("seguidores");
				String[] seguidos1=(String[])request.getAttribute("seguidos");%>
				<div class="tabbed_area">

					<script src="scripts/functions.js" type="text/javascript"></script>
					<ul class="tabs">
						<li><a onclick='javascript:tabSwitch("tab_11","content_11")'
							id="tab_11" class="active">Datos</a></li>
						<li><a onclick='javascript:tabSwitch("tab_22","content_22")'
							id="tab_22">Actividades</a></li>
						<li><a onclick='javascript:tabSwitch("tab_33","content_33")'
							id="tab_33">Salidas</a></li>
						<li><a onclick='javascript:tabSwitch("tab_44","content_44")'
							id="tab_44">Seguidores</a></li>
						<li><a onclick='javascript:tabSwitch("tab_55","content_55")'
							id="tab_55">Seguidos</a></li>
					</ul>

					<div id="content_11" class="content">
						<div id="content_1" class="content">
							<div>
								<form name="modificarDatos"
									action="usuario?parametro=modificarDatos&nick=<%=nickName1%>"
									method="POST">
									<table class="tablaDatos">
										<tr>
											<td NOWRAP class="ponerGris"><b>Nickname :</b></td>
											<td NOWRAP class="ponerGris"><%=nickName1 %></td>
										</tr>
										<tr>
											<td NOWRAP class="ponerGris"><b>Email :</b></td>
											<td NOWRAP class="ponerGris"><%=datosUsr.getEmail() %></td>
										</tr>
										<tr>
											<td NOWRAP class="ponerNegritaGrande"><b>Nombre :</b></td>
											<td>
												<table class="tablaDatos3">
													<tr>
														<td NOWRAP class="ponerNegritaGrande"><input id="nombre" type="text" name="nombre" class="ponerNegritaGrande" value="<%=datosUsr.getNombre() %>"></td>
														<%if(datosUsr.getNickname().equals(nickSesion)){%>
															<td><input id="ConfirmarNombre2" type="button" value="Editar" onclick='javascript:hacerEditableNombre()'></td>
															<td><input id="ConfirmarNombre" type="button" onclick='document.modificarDatos.submit()' value="Ok"></td>
														<%}%>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td NOWRAP class="ponerNegritaGrande"><b>Apellido :</b></td>
											<td>
												<table class="tablaDatos3">
													<tr>
														<td NOWRAP class="ponerNegritaGrande"><input id="apellido" type="text" name="apellido" class="ponerNegritaGrande" value="<%=datosUsr.getApellido() %>"></td>
														<%if(datosUsr.getNickname().equals(nickSesion)){%>
															<td><input id="ConfirmarApellido2" type="button" value="Editar" onclick='javascript:hacerEditableApellido()'></td>
															<td><input id="ConfirmarApellido" type="button" onclick='document.modificarDatos.submit()' value="Ok"></td>
														<%}%>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td NOWRAP class="ponerNegritaGrande"><b>Fecha de Nacimiento :</b></td>
											<td>
												<table class="tablaDatos3">
													<tr>
														<%String fecha3=datosUsr.getDia()+"-"+datosUsr.getMes()+"-"+datosUsr.getAnio() ; %>
														<td NOWRAP class="ponerNegritaGrande"><input id="fechaNaci" type="text" name="naci" class="ponerNegritaGrande" value="<%=fecha3%>"></td>
														<%if(datosUsr.getNickname().equals(nickSesion)){%>
															<td><input id="ConfirmarFecha2" type="button" value="Editar" onclick='javascript:hacerEditableFecha()'></td>
															<td><input id="ConfirmarFecha" type="button" onclick='document.modificarDatos.submit()' value="Ok"></td>
														<%}%>
													</tr>
												</table>
											</td>
										</tr>
										<%DataUsuario provDt=(DataUsuario)request.getAttribute("datProv"); %>
										<tr>
											<td NOWRAP class="ponerGris"><b>Descripcion :</b></td>
											<td NOWRAP class="ponerGris"><%=provDt.getDescripcion() %></td>
										</tr>
										<%if(!(provDt.getLink()==null || provDt.getLink().equals(""))) {%>
										<tr>
											<td NOWRAP class="ponerGris"><b>Link de su pagina :</b></td>
											<td NOWRAP class="ponerGris"><a href="<%=provDt.getLink() %>"  target="_blank"><%=provDt.getLink() %></a></td>
										</tr>
										<%} %>
									</table>
								</form>
							</div>
						</div>
					</div>
					<%if(actividades.length>0){%>
					<div id="content_22" class="content">
						<div>
							<%
					        	if(!datosUsr.getNickname().equals(nickSesion)) {
					        	%>
							<table class="tablaDatos2">
								<tr>
									<td NOWRAP><b>Nombre de Actividad</b></td>
									<td NOWRAP><b>Duracion</b></td>
									<td NOWRAP><b>Costo</b></td>
									<td NOWRAP><b>Ciudad</b></td>
									<td NOWRAP><b>Fecha del alta</b></td>
								</tr>
								<tr align="center">
									<td COLSPAN=5> <hr size="5px" color="" /></td>
								</tr>
								
								<%for(int i=0;i<actividades.length;i++){%>
								<tr>
									<td NOWRAP><a
										href="usuario?parametro=ConsultaActividad&actividad=<%=actividades[i].getNombre().replace(" ","---") %>"><%=actividades[i].getNombre() %></a></td>
									<td NOWRAP><%=actividades[i].getDuracion() %> hs</td>
									<td NOWRAP><%=actividades[i].getCostoPT() %></td>
									<td NOWRAP><%=actividades[i].getCiudad() %></td>
									<%String fecha=actividades[i].getDia()+"/"+actividades[i].getMes()+"/"+actividades[i].getAnio() ; %>
									<td NOWRAP><%=fecha%></td>
								</tr>
								<tr align="center">
									<td COLSPAN=5> <hr size="5px" color="" /></td>
								</tr>
								<%} %>
							</table>
							<%
					        	} else {
                        	  	%>
							<table class="tablaDatos2">
							<%boolean[] actsFin = (boolean[])request.getAttribute("actsFin"); %>
								<tr>
									<td NOWRAP><b>Nombre de Actividad</b></td>
									<td NOWRAP><b>Estado</b></td>
									<td NOWRAP><b>Duracion</b></td>
									<td NOWRAP><b>Costo</b></td>
									<td NOWRAP><b>Ciudad</b></td>
									<td NOWRAP><b>Fecha del alta</b></td>
									<td NOWRAP><b>Finalizar Actividad</b></td>
								</tr>
								<tr align="center">
									<td COLSPAN=7> <hr size="5px" color="" /></td>
								</tr>
								<%for(int i=0;i<actividades.length;i++){%>
								<tr>
									<td NOWRAP><a href="usuario?parametro=ConsultaActividad&actividad=<%=actividades[i].getNombre().replace(" ","---") %>"><%=actividades[i].getNombre() %></a></td>
									<td NOWRAP><%=actividades[i].getEstadoActual() %></td>
									<td NOWRAP><%=actividades[i].getDuracion() %> hs</td>
									<td NOWRAP><%=actividades[i].getCostoPT() %></td>
									<td NOWRAP><%=actividades[i].getCiudad() %></td>
									<%String fecha=actividades[i].getDia()+"-"+actividades[i].getMes()+"-"+actividades[i].getAnio() ; %>
									<td NOWRAP><%=fecha%></td>
									<%if(pueFinalizar[i]==true){ %>
										<td NOWRAP><a href="usuario?parametro=akshadka&optjui=<%=actividades[i].getNombre().replace(" ","---") %>">Finalizar :<%=actividades[i].getNombre() %></a></td>
									<%} else{%>
										<td NOWRAP>-</td>
									<%} %>
									</tr>
									<tr align="center">
										<td COLSPAN=7> <hr size="5px" color="" /></td>
									</tr>
								<%} %>
							</table>
							<%
					        	}
                        	  	%>
						</div>
					</div>
					<%}else{%>
						<div id="content_22" class="content">
							<p>NO A DADO DE ALTA NINGUNA ACTIVIDAD EL USUARIO</p>
						</div>
					<%}
					if(salidas.length>0){%>
					<div id="content_33" class="content">
						<div>
							<table class="tablaDatos">
								<tr>
									<td NOWRAP><b>Nombre</b></td>
									
									<td NOWRAP><b>Fecha y hora</b></td>
									<td NOWRAP><b>Lugar</b></td>
									<%if(datosUsr.getNickname().equals(nickSesion)){ %>
										<td NOWRAP><b>Cant. Inscriptos</b></td>
										<td NOWRAP><b>Costo por Turista</b></td>
									<%} %>
									<td NOWRAP><b>Cant. Maxima de Turistas</b></td>
								</tr>
								<tr align="center">
								<%if(datosUsr.getNickname().equals(nickSesion)){ %>
									<td COLSPAN=6> <hr size="5px" color="" /></td>
								<%}else{ %>
									<td COLSPAN=4> <hr size="5px" color="" /></td>
								<%} %>
								</tr>
								<%for(int i=0;i<salidas.length;i++){%>
								<%int cantTur=salidas[i].getCantTotalTuristas();%>
								<tr>
									<td NOWRAP><a
										href="usuario?parametro=ConsultaSalida&salida=<%=salidas[i].getNombre().replace(" ", "---") %>&tur=<%=nickName1.replace(" ", "---") %>"><%=salidas[i].getNombre() %></a>
									</td>
									
									<%String fecha45=salidas[i].getDiaSalida()+"/"+salidas[i].getMesSalida()+"/"+salidas[i].getAnioSalida(); %>
									<td NOWRAP><%=fecha45%> - <%=salidas[i].getHora()+":"+salidas[i].getMinuto()%></td>
									<td NOWRAP><%=salidas[i].getLugar()%></td>
									<%if(datosUsr.getNickname().equals(nickSesion)){ %>
										<td NOWRAP><%=salidas[i].getCantTotalTuristas() %></td>
										<td NOWRAP><%=costos[i]%></td>
									<%} %>
									<td NOWRAP><%=salidas[i].getCantMaxT()%></td>
								</tr>
								<tr align="center">
								<%if(datosUsr.getNickname().equals(nickSesion)){ %>
									<td COLSPAN=6> <hr size="5px" color="" /></td>
								<%}else{ %>
									<td COLSPAN=4> <hr size="5px" color="" /></td>
								<%} %>
								</tr>
								<%} %>
							</table>
						</div>
					</div>
					<%}else{%>
					<div id="content_33" class="content">
						<p>NO HAY SALIDAS ASOCIADAS A LAS ACTIVIDADES</p>
					</div>
					<%}
					if(seguidores1!=null && seguidores1.length>0){%>
					<div id="content_44" class="content">
						<div>
							<table class="tablaDatos">
							<tr>
								<td>USUARIOS</td>
								<%if(nickName1.equals(nickSesion)){ %>
									<td>Opcion</td>
								<%} %>
							</tr>
							<%boolean[] boolseg1 = (boolean[])request.getAttribute("boolSeg"); %>
							<%for(int i=0;i<seguidores1.length;i++){%>
								<tr>
									<td NOWRAP><a href="usuario?parametro=ConsultaUsuario&nickname=<%=seguidores1[i]%>"><%=seguidores1[i]%></a></td>
									<%if(nickName1.equals(nickSesion)){ %>
										<%if (boolseg1[i]) {%>
											<td NOWRAP><a href="usuario?parametro=seguir&seguido=<%=seguidores1[i]%>">Seguir</a></td>
										<% } else{%>
											<td NOWRAP><a href="usuario?parametro=dejarseguir&seguido=<%=seguidores1[i]%>">Dejar de Seguir</a></td>
										<%}
									}%>	
								</tr>
							<%} %>
							</table>
						</div>
					</div>
					<%}else{%>
					<div id="content_44" class="content">
						<p>NO TIENE SEGUIDORES ESTE USUARIO</p>
					</div>
					<%}
					if(seguidos1!=null && seguidos1.length>0){%>
					<div id="content_55" class="content">
						<div>
							<table class="tablaDatos">
							<tr>
								<td>USUARIOS</td>
								<td>Opcion</td>
							</tr>
								<%for(int i=0;i<seguidos1.length;i++){%>
								<tr>
									<td NOWRAP><a href="usuario?parametro=ConsultaUsuario&nickname=<%=seguidos1[i]%>"><%=seguidos1[i]%></a></td>
									<%if(nickName1.equals(nickSesion)){ %>
										<td NOWRAP><a href="usuario?parametro=dejarseguir&seguido=<%=seguidos1[i]%>">Dejar de Seguir</a></td>
									<%} %>
								</tr>
								<%} %>
							</table>
						</div>
					</div>
					<%}else{%>
					<div id="content_55" class="content">
						<p>NO TIENE SEGUIDOS ESTE USUARIO</p>
					</div>
					<%} %>
					
				</div>
			</div>
		</div>

	</div>
	<%}else if( usrs.equals("no")){%>
	<h1 class="titCS">&bull; Usuario &bull;</h1>
	<div class="ConsultaUsuario">
		<table class="tablaDatos">
			<tr>
				<td><img id='base64image'src='data:image/png;base64,<%=url%>'  class="imagenUsr"></td>
				<td>
					<table class="tablaDatos2">
						<tr>
							<td class="nombreGrande" NOWRAP><%=datosUsr.getNombre()%> <%=datosUsr.getApellido() %></td>
						</tr>
						<tr>
							<td NOWRAP><%=datosUsr.getEmail() %></td>
						</tr>
						<%if(!nickSesion.equals("") && !usrSesion.getNickname().equals(datosUsr.getNickname())) {%>
							<tr>
								<%if (!bol) {%>
									<td NOWRAP><a href="usuario?parametro=seguir&seguido=<%=datosUsr.getNickname()%>">Seguir</a></td>
								<% } else{%>
									<td NOWRAP><a href="usuario?parametro=dejarseguir&seguido=<%=datosUsr.getNickname()%>">Dejar de Seguir</a></td>
								<%} %>
							</tr>
						<%} %>
					</table>
				</td>
			</tr>
		</table>
	</div>

	<!-- Aqui va las pestañas -->
	<%DataSalida[] salidas= (DataSalida[])request.getAttribute("salidasTur");
	    String[]costos=(String[])request.getAttribute("costos");
	    String[] seguidores2=(String[])request.getAttribute("seguidores");
		String[] seguidos2=(String[])request.getAttribute("seguidos");%>

	<div class="ConsultaUsuario">
		<div class="tabbed_area">

			<div class="tabbed_area">

				<script src="scripts/functions.js" type="text/javascript"></script>
				<ul class="tabs">
					<li><a onclick='javascript:tabSwitch1("tab_11","content_11")'
						id="tab_11" class="active">Datos</a></li>
					<li><a onclick='javascript:tabSwitch1("tab_22","content_22")'
						id="tab_22">Salidas</a></li>
					<li><a onclick='javascript:tabSwitch1("tab_33","content_33")'
						id="tab_33">Seguidores</a></li>
					<li><a onclick='javascript:tabSwitch1("tab_44","content_44")'
						id="tab_44">Seguidos</a></li>
				</ul>

				<div id="content_11" class="content">
					<div id="content_1" class="content">
						<div>
							<form name="modificarDatos1"
								action="usuario?parametro=modificarDatos&nick=<%=nickName1%>"
								method="POST">
								<table class="tablaDatos">
									<tr>
										<td NOWRAP class="ponerGris"><b>Nickname :</b></td>
										<td NOWRAP class="ponerGris"><%=nickName1 %></td>
									</tr>
									<tr>
										<td NOWRAP class="ponerGris"><b>Email :</b></td>
										<td NOWRAP class="ponerGris"><%=datosUsr.getEmail() %></td>
									</tr>
									<tr>
										<td NOWRAP class="ponerNegritaGrande"><b>Nombre :</b></td>
										<td>
											<table class="tablaDatos3">
												<tr>
													<td NOWRAP class="ponerNegritaGrande"><input id="nombre" type="text" name="nombre" class="ponerNegritaGrande" value="<%=datosUsr.getNombre() %>"></td>
													<%if(datosUsr.getNickname().equals(nickSesion)){%>
														<td><input id="ConfirmarNombre2" type="button" value="Editar" onclick='javascript:hacerEditableNombre()'></td>
														<td><input id="ConfirmarNombre" type="button" onclick='document.modificarDatos1.submit()' value="Ok"></td>
													<%}%>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td NOWRAP class="ponerNegritaGrande"><b>Apellido :</b></td>
										<td>
											<table class="tablaDatos3">
												<tr>
													<td NOWRAP class="ponerNegritaGrande"><input id="apellido" type="text" name="apellido" class="ponerNegritaGrande" value="<%=datosUsr.getApellido() %>"></td>
													<%if(datosUsr.getNickname().equals(nickSesion)){%>
														<td><input id="ConfirmarApellido2" type="button" value="Editar" onclick='javascript:hacerEditableApellido()'></td>
														<td><input id="ConfirmarApellido" type="button" onclick='document.modificarDatos1.submit()' value="Ok"></td>
													<%}%>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td NOWRAP class="ponerNegritaGrande"><b>Fecha de
												Nacimiento :</b></td>
										<td>
											<table class="tablaDatos3">
												<tr>
													<%String fecha2=datosUsr.getDia()+"-"+datosUsr.getMes()+"-"+datosUsr.getAnio(); %>
													<td NOWRAP class="ponerNegritaGrande"><input id="fechaNaci" type="text" name="naci" class="ponerNegritaGrande" value="<%=fecha2%>"></td>
													<%if(datosUsr.getNickname().equals(nickSesion)){%>
														<td><input id="ConfirmarFecha2" type="button" value="Editar" onclick='javascript:hacerEditableFecha()'></td>
														<td><input id="ConfirmarFecha" type="button" onclick='document.modificarDatos1.submit()' value="Ok"></td>
													<%}%>
												</tr>
											</table>
										</td>
									</tr>
									<%DataUsuario turDt=(DataUsuario)request.getAttribute("datTur"); %>
									<tr>
										<td NOWRAP class="ponerGris"><b>Nacionalidad :</b></td>
										<td NOWRAP class="ponerGris"><%=turDt.getNacionalidad() %></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
				<%if(salidas.length>0){%>
				<div id="content_22" class="content">
					<div>
						<table class="tablaDatos" style="border-left: medium;">
							<tr>
								<td NOWRAP><b>Nombre</b></td>
								<td NOWRAP><b>Cant. Maxima de Turistas</b></td>
								<td NOWRAP><b>Fecha y hora</b></td>
								<td NOWRAP><b>Lugar</b></td>
								<td NOWRAP><b>Tipo Inscripcion</b></td>
								<%if(datosUsr.getNickname().equals(nickSesion)){ %>
									<td NOWRAP><b>Cant. Inscriptos</b></td>
									<td NOWRAP><b>Costo</b></td>
								<%} %>
							</tr>
							<tr align="center">
								<%if(datosUsr.getNickname().equals(nickSesion)){ %>
									<td COLSPAN=7> <hr size="5px" color="" /></td>
								<%}else{ %>
									<td COLSPAN=5> <hr size="5px" color="" /></td>
								<%} %>
							</tr>
							<%for(int i=0;i<salidas.length;i++){%>
							<%int cantTur=salidas[i].getCantTotalTuristas();%>
							<tr>
								<td NOWRAP><%=salidas[i].getNombre() %></td>
								<td NOWRAP><%=salidas[i].getCantMaxT()%></td>
								<%String fecha3=salidas[i].getDiaSalida()+"/"+salidas[i].getMesSalida()+"/"+salidas[i].getAnioSalida(); %>
								<td NOWRAP><%=fecha3%> - <%=salidas[i].getHora()+":"+salidas[i].getMinuto()%></td>
								<td NOWRAP><%=salidas[i].getLugar()%></td>
								<td NOWRAP>General</td>
								<%if(datosUsr.getNickname().equals(nickSesion)){ %>
									<td NOWRAP><%=salidas[i].getCantTotalTuristas() %></td>
									<td NOWRAP><%=costos[i]%></td>
								<%} %>
							</tr>
							<%if(datosUsr.getNickname().equals(nickSesion)){ %>
									<tr>
										<td></td>
										<td NOWRAP><b>Datos detallados salida:</b></td>
										<td NOWRAP><a href="usuario?parametro=ConsultaSalida&salida=<%=salidas[i].getNombre().replace(" ", "---") %>&tur=<%=nickName1.replace(" ", "---") %>">Ir</a> </td>
										<td NOWRAP><b>Comprobante de la salida:</b></td>
										<td NOWRAP><a href="usuario?parametro=Comprobante&nickname=lachiqui&salida=<%=salidas[i].getNombre().replace(" ", "---")%>"  target="_blank">Ver</a></td>
									</tr>
							<%}%>
							<tr align="center">
								<%if(datosUsr.getNickname().equals(nickSesion)){ %>
									<td COLSPAN=7> <hr size="5px" color="" /></td>
								<%}else{ %>
									<td COLSPAN=5> <hr size="5px" color="" /></td>
								<%} %>
							</tr>
							<%} %>
						</table>
					</div>
				</div>
				<%}else{%>
					<div id="content_22" class="content">
					<p>NO HAY SALIDAS PARA ESTE USUARIO</p>
					</div>
				<%}
				if(seguidores2!=null && seguidores2.length>0){%>
				<div id="content_33" class="content">
					<div>
						<table class="tablaDatos">
						<tr>
							<td>USUARIOS</td>
							<%if(nickName1.equals(nickSesion)){ %>
								<td>Opcion</td>
							<%} %>
						</tr>
						<%boolean[] boolseg1 = (boolean[])request.getAttribute("boolSeg"); %>
						<%for(int i=0;i<seguidores2.length;i++){%>
							<tr>
								<td NOWRAP><a href="usuario?parametro=ConsultaUsuario&nickname=<%=seguidores2[i]%>"><%=seguidores2[i]%></a></td>
								<%if(nickName1.equals(nickSesion)){ %>
										<%if (boolseg1[i]) {%>
											<td NOWRAP><a href="usuario?parametro=seguir&seguido=<%=seguidores2[i]%>">Seguir</a></td>
										<% } else{%>
											<td NOWRAP><a href="usuario?parametro=dejarseguir&seguido=<%=seguidores2[i]%>">Dejar de Seguir</a></td>
										<%}
									}%>	
							</tr>
						<%} %>
						</table>
					</div>
				</div>
				<%}else{%>
				<div id="content_33" class="content">
				<p>NO TIENE SEGUIDORES ESTE USUARIO</p>
				</div>
				<%}
				if(seguidos2!=null && seguidos2.length>0){%>
				<div id="content_44" class="content">
					<div>
						<table class="tablaDatos">
						<tr>
							<td>USUARIOS</td>
							<%if(nickName1.equals(nickSesion)){ %>
								<td>Opcion</td>
							<%} %>
						</tr>
							<%for(int i=0;i<seguidos2.length;i++){%>
							<tr>
								<td NOWRAP><a href="usuario?parametro=ConsultaUsuario&nickname=<%=seguidos2[i]%>"><%=seguidos2[i]%></a></td>
								<%if(nickName1.equals(nickSesion)){ %>
									<td NOWRAP><a href="usuario?parametro=dejarseguir&seguido=<%=seguidos2[i]%>">Dejar de Seguir</a></td>
								<%} %>
							</tr>
							<%} %>
						</table>
					</div>
				</div>
				<%}else{%>
				<div id="content_44" class="content">
				<p>NO SIGUE A NADIE ESTE USUARIO</p>
				</div>
				<%} %>
			</div>
		</div>
	</div>
	<%}%>



	<script>
    
	    function tabSwitch(new_tab, new_content) {
	    	
	    	document.getElementById('content_11').style.display = 'none';
	    	document.getElementById('content_22').style.display = 'none';
	    	document.getElementById('content_33').style.display = 'none';
	    	document.getElementById('content_44').style.display = 'none';
	    	document.getElementById('content_55').style.display = 'none';
	    	document.getElementById(new_content).style.display = 'block';	
	    	
	
	    	document.getElementById('tab_11').className = '';
	    	document.getElementById('tab_22').className = '';
	    	document.getElementById('tab_33').className = '';
	    	document.getElementById('tab_44').className = '';
	    	document.getElementById('tab_55').className = '';
	    	document.getElementById(new_tab).className = 'active';		
	
	    }
    
		function tabSwitch1(new_tab, new_content) {
	    	
	    	document.getElementById('content_11').style.display = 'none';
	    	document.getElementById('content_22').style.display = 'none';
	    	document.getElementById('content_33').style.display = 'none';
	    	document.getElementById('content_44').style.display = 'none';
	    			
	    	document.getElementById(new_content).style.display = 'block';	
	    	
	
	    	document.getElementById('tab_11').className = '';
	    	document.getElementById('tab_22').className = '';
	    	document.getElementById('tab_33').className = '';
	    	document.getElementById('tab_44').className = '';
	    			
	    	document.getElementById(new_tab).className = 'active';		
	
	    }
	    
	    
		function ocultar2(){
			document.getElementById('content_22').style.display = 'none';
        	document.getElementById('content_33').style.display = 'none';
        	document.getElementById('content_44').style.display = 'none';
        	document.getElementById('tab_22').className = '';
        	document.getElementById('tab_33').className = '';
        	document.getElementById('tab_44').className = '';
        	
        	document.getElementById('apellido').disabled = true;
        	document.getElementById('nombre').disabled = true;
        	document.getElementById('fechaNaci').disabled = true;
        	
            document.getElementById('ConfirmarApellido').style.visibility = "hidden";
            document.getElementById('ConfirmarNombre').style.visibility = "hidden";
            document.getElementById('ConfirmarFecha').style.visibility = "hidden";
        }
    
        function ocultar(){
        	document.getElementById('content_22').style.display = 'none';
        	document.getElementById('content_33').style.display = 'none';
        	document.getElementById('content_44').style.display = 'none';
        	document.getElementById('content_55').style.display = 'none';
        	document.getElementById('tab_22').className = '';
        	document.getElementById('tab_33').className = '';
        	document.getElementById('tab_44').className = '';
        	document.getElementById('tab_55').className = '';
        	
        	document.getElementById('apellido').disabled = true;
        	document.getElementById('nombre').disabled = true;
        	document.getElementById('fechaNaci').disabled = true;
        	
            document.getElementById('ConfirmarApellido').style.visibility = "hidden";
            document.getElementById('ConfirmarNombre').style.visibility = "hidden";
            document.getElementById('ConfirmarFecha').style.visibility = "hidden";
        }
        function hacerEditableApellido(){
            document.getElementById('ConfirmarApellido').style.visibility = "visible";
            document.getElementById('apellido').disabled = false;
        }
        function hacerEditableNombre(){
            document.getElementById('ConfirmarNombre').style.visibility = "visible";
            document.getElementById('nombre').disabled = false;
        }
        function hacerEditableFecha(){
            document.getElementById('ConfirmarFecha').style.visibility = "visible";
            document.getElementById('fechaNaci').disabled = false;
        }

        function hacerNoEditable(){
            document.getElementById('ConfirmarApellido').style.visibility = "hidden";
            document.getElementById('ConfirmarNombre').style.visibility = "hidden";
            document.getElementById('ConfirmarFecha').style.visibility = "hidden";

            document.getElementById('apellido').contentEditable="false";
            document.getElementById('nombre').contentEditable="false";
            document.getElementById('fechaNaci').contentEditable="false";
            
        }
        
        function hacerEditableApellido(){
            document.getElementById('ConfirmarApellido').style.visibility = "visible";
            document.getElementById('apellido').disabled = false;
        }
    </script>
</body>
</html>
