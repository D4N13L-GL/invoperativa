 
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<style type="text/css"> 
    
    .buttonRegister {
    
	color:red;
	font: bold small 'trebuchet ms',helvetica,sans-serif; 
	} 
</style>
<title>Login</title>

</head>


<body>
<h4>Ingrese los datos de logueo:</h4>
<s:actionerror />
<s:form action="login.action" method="post">
	<s:textfield name="username" key="label.username" size="20" /></p>
	<s:password name="password" key="label.password" size="20" cssClass="p" />
	<s:submit method="execute" key="label.login" align="center" />
	<s:submit id="buttonRegister" method="register" type="input" value="Registrarse" cssClass="buttonRegister" />
	
</s:form>


</body>
</html>
