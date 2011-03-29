<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link rel="stylesheet" href="/css/estilos.css" 
      type="text/css">
      
<title>Login</title>
</head>

<body>
<h4>Ingrese los datos de logueo:</h4>
<s:actionerror />
<s:form action="login.action" method="post">
	<s:textfield name="username" key="label.username" size="20" /></p>
	<s:password name="password" key="label.password" size="20" cssClass="p" />
	<s:submit method="execute" key="label.login" align="center" />
</s:form>

<s:form action="login.action" method="post">
	<s:submit method="register" key="Registrarse" align="center" />
</s:form>



</body>
</html>
