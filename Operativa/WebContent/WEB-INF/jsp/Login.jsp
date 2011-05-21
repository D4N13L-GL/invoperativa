<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Login - Trabajo Final Investigación Operativa</title>

<style>
body {
font: 12px verdana, arial, helvetica, sans-serif;
background-color:#FFFFFF;
} 

table.wwFormTable {
font: 12px verdana, arial, helvetica, sans-serif;
border-width: 1px;
border-color: black;
border-style: solid;
color: black;
background-color: #C8D7DF;
width: 30%;
margin-left:35%;
margin-right:35%;
margin-top:15%;
} 

table.wwFormTable th {
}

table.wwFormTable tr td {
background-color: #C8D7DF;
margin: 5px;
padding: 5px;
}

.tdLabel {
/*
border-width: 1px;
border-color: #afa;
border-style: solid;
*/
font-weight: bold;
align: top;
} 

.label {
} 

.errorMessage {
color: red;
font-size: 12px;
} 

#headerDiv {
border-style: solid;
border-width: 1px 1px 0px;
border-color: black;
padding: 5px;
background-color: blue;
/* height: 22px; */
height: 1.8em;
/* margin-bottom: 12px; */
}

#buttonBar {
border-width: 0px 1px 1px;
border-style: solid;
border-color: black;
color: white;
margin-bottom: 12px;
background-color: black;
height: 1.6em;
padding: 5px;
}

#appName {
color: white;
font-size: 1.8em;
}

#pageTitle {
font-size: 1.4em;
color: red;
clear: none;
}

#appName, #pageTitle {
float: right;
}

#menuContainer {
float: left;
}

#brandingContainer {
float: right:
text-align: right;
}
</style>

</head>
<body>

<s:form action="login.action" method="POST" validate="true">
<tr>
<td colspan="2">
Login
</td>

</tr>

  <tr>
   <td colspan="2">
         <s:actionerror />
   </td>
  </tr>

<s:textfield name="username" label="Login name"/>
<s:password name="password" label="Password"/>
<s:submit value="Login" align="center"/>

</s:form>

</body>

</html>