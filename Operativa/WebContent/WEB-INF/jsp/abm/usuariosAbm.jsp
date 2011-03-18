<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>    

<jsp:include page="/WEB-INF/jsp/menu.jsp" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestión de usuarios</title>
</head>
<body>
	
	<br />
	<br />

	<s:form action="abmUsuarios.action" method="post">
		<s:textfield name="username" key="label.username" size="20" />
		<s:password name="password" key="label.password" size="20" />
		<s:submit method="crearUsuario" key="label.guardar" />
	</s:form>
	
</body>
</html>