<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html" import="java.util.*"%>
<html>
    <head>
	</head>
    <body>
      <s:if test="#session['logged-in'] != 'true'">
	      <jsp:forward page="/WEB-INF/jsp/Login.jsp" />  
	  </s:if>
    </body>
</html>