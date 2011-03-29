<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificación de Usuarios</title>
</head>
<body>
	<s:form action="updateUsuario.action">
		<s:hidden name="id" />
		<s:textfield name="username" label="Usuario" value="%{usuario.username}" />
		<s:textfield name="password" label="Password" value="%{usuario.password}"/>
		<s:submit />
	
	</s:form>
</body>
</html>