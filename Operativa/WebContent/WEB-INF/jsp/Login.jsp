<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Gestión de Usuarios</title>
<style type="text/css">

   @import "./style.css";

</style></head> 
  
    
<body>
<div class="container">
  <div class="header">  </div>
  <div class="content">
<h4>Ingrese los datos de logueo:</h4>
<s:actionerror />
<s:form action="login.action" method="post">
	<s:textfield name="username" key="label.username" size="20" />
	<s:password name="password" key="label.password" size="20" cssClass="p" />
	<s:submit method="execute" key="label.login" align="center" />
	<s:submit id="buttonRegister" method="register" type="input" value="Registrarse" cssClass="buttonRegister" />
	
</s:form>

</div>
 
  <div class="footer">
    <p>&nbsp;</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
</body>
</html>
