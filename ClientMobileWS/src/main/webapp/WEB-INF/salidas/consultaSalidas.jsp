<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="servidor.DataSalida"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="media/css/normalize.css">
    <link rel="stylesheet" href="media/css/index.css">
    <link rel="stylesheet" href="media/css/ConsultaActividades.css">
    <title>Document</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<%
	String [] departamentos2 = (String[]) request.getAttribute("departamentos");
	String [] categorias2 = (String[]) request.getAttribute("categorias");
	String [] urls=(String[])request.getAttribute("urls");
	String aux;
    %>
	
    <section class="lugares">
        <h2>Salidas Turisticas</h2>
            <div class="content-select">
<form name="form1" action="salidas" method="get">
                        <select name="DepCat" id="elegir" onchange="document.form1.submit()">
                        
                        	<%
                        	if(request.getAttribute("dato1") != null) {
                            %>
                            	
                            <option value=<%=request.getAttribute("dato1")%> selected disabled hidden><%=request.getAttribute("dato1")%></option>
                            	
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
            </div>
            <div class="content-select">
					<form name="form2" action="salidas" method="get">
                        <select name="Acti" id="actividades" onchange="document.form2.submit()">
                        
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

                        <input type="hidden" id="DepCatElegido" name="elegido" value=<%=aux%>/>

                        <% } %>
                        
                    </form>
            </div>                

				<%
            	if(request.getAttribute("salidas") != null) {
            		String actividadElegida = (String) request.getAttribute("actividadElegida");
            		String [] salidas = (String []) request.getAttribute("salidas");
            		for (int k = 0; k < salidas.length; k++) {
            	%>

		        <div class="fotos">
		
		            <div class="card"><a href="salidas?direccionar=ConsultaSalida&actividadElegida=<%=request.getAttribute("actividadElegida")%>&salida=<%=salidas[k]%>">
		                <figure>
		                    <img class="foto" id='base64image' src='data:image/png;base64,<%=urls[k]%>' alt="foto">
		                </figure>
		                    <h2><%=salidas[k]%></h2>
		                </a>
		            </div>
		
		
		        </div>

				<%
                	}
                } 
                %>

    </section>
    <script src="/js/script.js"></script>
</body>
</html>