<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificación de Punto de Destino</title>
</head>
<body>
	<s:form action="updatePuntoDestino.action">
		<s:hidden name="id" />
		<s:textfield name="nombre" label="Nombre"/>
		<s:textfield name="latitud" label="Latitud" />
		<s:textfield name="longitud" label="Longitud" />
		<s:textfield name="demanda" label="Demanda" />
		<table>
			<tr>
				<td><s:textfield name="localizacion" label="Dirección" size="70" /></td>
				<td><input type="button" value="Localizar" onclick="codeAddress()"></td>
			</tr>
		</table>
		<s:submit value="Guardar" />
	
	</s:form>
</body>
</html>