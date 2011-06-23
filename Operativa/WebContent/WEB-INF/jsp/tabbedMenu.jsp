<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>Centered Menu</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css" media="screen,print">
html,body {
		margin:0;
		padding:0;
		color:#000;
		background:#fff;
	}
	body {
		font:76%/135% "Lucida Grande", "Lucida Sans Unicode", Arial, Helvetica, sans-serif;
		padding:0 0 10px 0;
		min-width:760px;
	}
	code {
		font-family:Monaco, Courier, "Courier New", monospace;
	}
	h1 {
		font-size:1.5em;
	}
	#labfooter {
		text-align:center;
		clear:both;
	}
	#wrap {
		width:760px;
		margin:0 auto;
	}
	#main {
		clear:both;
		margin:20px 80px;
	}
	.adwrap,
	.adwrap iframe {
		width:300px;
		height:250px;
	}
	.adwrap {
		float:left;
		margin:0 1em 0 0;
	}
	.adwrap-wide,
	.adwrap-wide iframe {
		width:468px;
		height:60px;
	}
	.adwrap-wide {
		margin:0 auto;
	}
	/* CSS for the tabs starts here */
	#header {
		height:140px;
		background:url(http://www.uploadimage.com.ar/images/04138758473408524518.png) 0 0 no-repeat;
	}
	#nav {
		float:left;
		width:760px;
		margin-top:-19px;
		font-size:0.94em;
		line-height:1em;
	}
	#nav ul {
		margin:0;
		padding:0 0 0 28px;
		list-style:none;
	}
	#nav li {
		float:left;
		margin:0 0 0 -1px;
		padding:0 0 0 6px;
		background:url(http://www.456bereastreet.com/lab/inverted_tabs/nav1.gif) 0 100% no-repeat;
	}
	#nav li:hover {
		background-position:-6px 100%;
	}
	#nav a {
		float:left;
		display:block;
		color:#473215;
		font-weight:bold;
		font-family:Georgia,Times,"Times New Roman",serif;
		background:url(http://www.456bereastreet.com/lab/inverted_tabs/nav2.gif) 100% 100% no-repeat;
		padding:5px 14px 12px 8px;
		text-decoration:none;
	}
	#nav li:hover a {
		background-image:url(http://www.456bereastreet.com/lab/inverted_tabs/nav1.gif);
	}
	/* Hide from IE5-Mac \*/
	#nav a {float:none;}
	/* End IE5-Mac hack */
	#nav a:hover {
		color:#000;
		text-decoration:underline;
	}
	#home #nav #t1,
	#services #nav #t2,
	#projects #nav #t3,
	#about #nav #t4,
	#contact #nav #t5 {
		background-position:-12px 100%;
		padding-left:26px;
		margin:0 2px 0 1px;
	}
	
	#nav #t1,
	#home #nav #t1 {
		margin-left:0;
	}
	#nav #t5,
	#contact #nav #t5 {
		margin-right:0;
	}
	</style>


</head>

<body id="services">
<div id="wrap">
<div id="header"></div>
<div id="nav">
	<ul>
		<li id="t1"><a href="<s:url action="goHome.action" />"><span>Home</span></a></li>
		<li id="t6"><a href="<s:url action="abmFabricasWelcome.action" />"><span>Fábricas</span></a></li>
		<li id="t3"><a href="<s:url action="abmPuntosDestinoWelcome.action" />"><span>Destinos</span></a></li>
		<li id="t4"><a href="<s:url action="inicioCalculosAction.action" />"><span>Iniciar Cálculos</span></a></li>
		<li id="t5"><a href="<s:url action="logout.action" />"><span>Logout</span></a></li>

	</ul>
</div>
</div>
</body>
</html>