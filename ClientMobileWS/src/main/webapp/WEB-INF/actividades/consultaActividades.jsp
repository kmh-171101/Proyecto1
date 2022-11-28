<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <section class="lugares">
        <h2>Actividades Turisticas</h2>
            <div class="content-select">
                        	<%
                        	String [] dep=(String [])request.getAttribute("departamentos");
							String[] categorias = (String[])request.getAttribute("categorias");
				            String [] urls=(String[])request.getAttribute("urls");
							String aux;
							%>
                            <form name="form1" action="actividades" method="get">
	                            <select name="DepCat" id="elegir" onchange="document.form1.submit()">
                                	<%
                        			if(request.getAttribute("dato1") != null) {
                            		%>
                            		<option value=<%=request.getAttribute("dato1")%> selected disabled hidden><%=request.getAttribute("dato1")%></option>
                            	
                            		<%
                            		} else {
                            		%>
	                                <option value="ninguno"></option>
	                                <% } %>
	                                <optgroup label="Departamentos"> <%
										for(int i = 0; i < dep.length; i++){
											aux = dep[i].replace(" ", "---");
		    					%>
					    			           <option value=<%=aux%>><%= dep[i] %></option>	
					    				    <% } %>
	                                </optgroup>
	                                <optgroup label="Categorias">        
								<%
		    					   for(int i = 0; i < categorias.length; i++){
		    						   aux = categorias[i].replace(" ", "---");
		    					%>
		    			           <option value=<%=aux%>><%= categorias[i] %></option>	
		    				    <% }%>
	                                </optgroup>
	                            </select>
                        </form>
            </div>           
        </div>
                <%
            	if(request.getAttribute("actividades") != null) {
            		//String actividadElegida = (String) request.getAttribute("actividadElegida");
            		String [] actis = (String[]) request.getAttribute("actividades");
            		for (int k = 0; k < actis.length; k++) {
            	%>
        <div class="fotos">
            <div class="card"><a href="actividades?parametro=ConsultaActividad&actividad=<%=actis[k]%>">
                <figure>
                    <img class="foto" id='base64image' src='data:image/png;base64,<%=urls[k]%>' alt="foto">
                </figure>
                    <h2><%=actis[k]%></h2>
                </a>
            </div>
			  	<%
               			}
            		}
               	%>
               

        </div>
    </section>
    <script src="media/js/script.js"></script>
</body>
</html>