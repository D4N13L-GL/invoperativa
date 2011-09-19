<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Login - Trabajo Final Investigación Operativa</title>
<jsp:include page="/WEB-INF/jsp/estilos.jsp" />

</head>
<body>

	<s:form action="login.action" method="POST" validate="true">
		<center>
			<h2>Trabajo Final Investigación Operativa</h2>
		</center>
		<center>
			<img alt="UNICEN"
				src="http://3.bp.blogspot.com/_1CabVGTatqE/Ssvli-J-VVI/AAAAAAAAAD8/WdGlWmPfDeE/s200/unicen+logo.jpg">
		</center>
		<tr>
			<td colspan="2">
				<div align="left">Introduzca sus datos para ingresar</div>
			</td>

		</tr>

		<tr>
			<td colspan="2"><s:actionerror />
			</td>
		</tr>
		<tr>
			<td colspan="2"><s:actionmessage />
			</td>
		</tr>

		<s:textfield name="username" label="Nombre de Usuario" />
		<s:password name="password" label="Password" />
		<div align="right" style="margin-right: 5%">
			<s:submit value="Ingresar" />
		</div>
		<div align="right" style="margin-right: 5%">
			<s:submit method="register" value="Registrarse" />
		</div>
	</s:form>

</body>

</html>