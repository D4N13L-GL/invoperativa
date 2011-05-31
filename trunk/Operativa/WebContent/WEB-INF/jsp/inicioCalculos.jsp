<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/estilos.jsp" />
<jsp:include page="/WEB-INF/jsp/purecssmenu.jsp" />
<jsp:include page="/WEB-INF/jsp/checkLogin.jsp" />


<script>
	function updateFabricasMap(elem){
		id = "fabMap['"+ elem.id +"'].elegida";
		document.getElementById(id).value = elem.checked ? 'true' : 'false';
	}

	function updateDestinosMap(elem){
		id = "destMap['"+ elem.id +"'].elegida";
		document.getElementById(id).value = elem.checked ? 'true' : 'false';	
	}
	function marcarDesmarcarDestinos(valor){
		checks = document.getElementById('destinosCheck').getElementsByTagName('input');
		for(var i = 0; i < checks.length; i++){
			checks[i].checked = valor;
			checks[i].onchange();
		}
	}
	function marcarDesmarcarFabricas(valor){
		checks = document.getElementById('fabricasCheck').getElementsByTagName('input');
		for(var i = 0; i < checks.length; i++){
			checks[i].checked = valor;
			checks[i].onchange();
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio de Cálculos</title>
</head>
<body style="font-family: verdana,arial,sans-serif;font-size:15px;background: #F2F2F2;">
<br/><br/>
<ul>
	<li><h5>Elija las fábricas y los puntos de destino que desea tener en cuenta para la distribución de mercadería.</h5></li>
	<li><h5>Luego presione el botón "Siguiente" para comenzar con los cálculos.</h5></li>
</ul>
<br/>
<s:form id="formInicioCalculos" theme="simple" action="nextStepAction">
<s:actionerror />
<table cellspacing="5" border="0" width="100%" style="background: #F2F2F2;">
				   			<tr>
					            <td class="tdHeader">
					                <div style="float: left;">
					                	Fábricas
					                </div>
					            </td>
					            <td class="tdHeader">
				                    <div style="float: left;">
				                        Puntos de Destino
				                    </div>
					            </td>
					        </tr>
					        <tr>
					        	<td width="50%" style="vertical-align:top">
					        		<div align="center" style="font-family: arial;">
					        			Marcar/Desmarcar Todas<input type="checkbox" onchange="marcarDesmarcarFabricas(this.checked)" id="MD1">
					           		</div>
					           		<br/>
					           		<div id="fabricasCheck">
					        		<table cellpadding="5px" border="1" class="imagetable">
					        			<tr>
											<th>Fábrica</th>
											<th>Localización</th>
											<th>Producción</th>
											<th>Elegir</th>
										</tr>
					        			<s:iterator value="fabricas">
					        				<tr>
    											<td>
    												<s:property value="nombre"/>
    											</td>
    											<td>
    												<s:property value="localizacion"/>
    											</td>
    											<td>
    												<s:property value="unidades"/>
    											</td>
    											<td>
    												<input type="checkbox" name="elegida" onchange="updateFabricasMap(this)" id="<s:property value="id" />"/>
    											</td>
    										</tr>
										</s:iterator>
									</table>
								</div>
					        	</td>
					        	<td width="50%" style="vertical-align:top">
					        		<div align="center" style="font-family: arial;">
					        			Marcar/Desmarcar Todas<input type="checkbox" onchange="marcarDesmarcarDestinos(this.checked)" id="MD2">
					           		</div>
					           		<br/>
					           		<div id="destinosCheck">
					        		<table cellpadding="5px" border="1" class="imagetable">
					        			<tr>
											<th>Punto de Destino</th>
											<th>Localización</th>
											<th>Demanda</th>
											<th>Elegir</th>
										</tr>
					        			<s:iterator value="destinos" >
					        				<tr>
    											<td>
    												<s:property value="nombre"/>
    											</td>
    											<td>
    												<s:property value="localizacion"/>
    											</td>
    											<td>
    												<s:property value="unidades"/>
    											</td>
    											<td>
    												<input type="checkbox" name="elegida" id="<s:property value="id" />" onchange="updateDestinosMap(this)"/>
    											</td>
    										</tr>
    									
										</s:iterator>
									</table>
								</div>
					        	</td>
					        </tr>
					        
</table>
<br/>
<div align="center" >
	<s:submit value="Siguiente" id="siguienteBtn" method="nextStep"/>
</div>


<div id="fabMap">
			<s:iterator value="fabricas">
    			<s:hidden name="fabMap['%{id}'].elegida" id="fabMap['%{id}'].elegida"/>
			</s:iterator>
</div>
<div id="destMap">
			<s:iterator value="destinos">
    			<s:hidden name="destMap['%{id}'].elegida" id="destMap['%{id}'].elegida"/>
			</s:iterator>
</div>
</s:form>
</body>
</html>