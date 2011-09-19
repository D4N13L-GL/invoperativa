<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Login - Trabajo Final Investigación Operativa</title>
<jsp:include page="/WEB-INF/jsp/estilos.jsp" />

</head>
<body >

<s:form action="abmUsuarios.action" method="POST" validate="true">
<center>
	<img alt="UNICEN" src="http://3.bp.blogspot.com/_1CabVGTatqE/Ssvli-J-VVI/AAAAAAAAAD8/WdGlWmPfDeE/s200/unicen+logo.jpg"	>
</center>
<tr>
<td colspan="2">
<div align="left">Introduzca los datos del nuevo usuario</div>
</td>

</tr>

  <tr>
   <td colspan="2">
         <s:actionerror />
   </td>
  </tr>

<s:textfield name="username" label="Nombre de Usuario"/>
<s:password name="password" label="Password"/>
<div align="right" style="margin-right: 5%">
<s:submit value="Crear Usuario"/>
</div>
</s:form>

</body>

</html>