<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificaci�n de F�brica</title>
</head>
<body>
	<s:form action="updateFabrica.action">
		<s:hidden name="id" />
		<s:textfield name="nombre" label="Nombre de la F�brica" value="%{fabrica.nombre}" />
		<s:textfield name="localizacion" label="Direcci�n" value="%{fabrica.localizacion}" size="70"/>
		<s:textfield name="latitud" label="Latitud" value="%{fabrica.latitud}" />
		<s:textfield name="longitud" label="Longitud" value="%{fabrica.longitud}"/>
		<s:textfield name="produccion" label="Producci�n" value="%{fabrica.produccion}" />
		<s:submit />
	
	</s:form>
</body>
</html>