<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta name="description" content="Online CSS Menu Maker. Create No-Javascript, 100% CSS Dropdown Menu in seconds! Horizontal css menu & Vertical css menu." />
</head>
<body bgcolor="#ffffff">



<!-- Start PureCSSMenu.com STYLE -->
<style>
#pcm{display:none;}
ul.pureCssMenu ul{display:none}
ul.pureCssMenu li:hover>ul{display:block}
ul.pureCssMenu ul{position: absolute;left:-1px;top:98%;}
ul.pureCssMenu ul ul{position: absolute;left:98%;top:-2px;}
ul.pureCssMenu,ul.pureCssMenu ul {
	margin:0px;
	list-style:none;
	padding:0px 2px 2px 0px;
	background-color:#333333;
	background-repeat:repeat;
	border-color:#cccccc #111111 #111111 #cccccc;
	border-width:1px;
	border-style:solid;
}
ul.pureCssMenu table {border-collapse:collapse}ul.pureCssMenu {
	display:block;
	zoom:1;
	float: left;
}
ul.pureCssMenu ul{
	width:120px;
}
ul.pureCssMenu li{
	display:block;
	margin:2px 0px 0px 2px;
	font-size:0px;
}
ul.pureCssMenu a:active, ul.pureCssMenu a:focus {
outline-style:none;
}
ul.pureCssMenu a, ul.pureCssMenu li.dis a:hover, ul.pureCssMenu li.sep a:hover {
	display:block;
	vertical-align:middle;
	background-color:#333333;
	border-width:1px;
	border-color:#333333;
	border-style:solid;
	text-align:left;
	text-decoration:none;
	padding:2px 5px 2px 10px;
	_padding-left:0;
	font:normal 12px Trebuchet MS,Tahoma;
	color: #cccccc;
	text-decoration:none;
	cursor:default;
}
ul.pureCssMenu span{
	overflow:hidden;
}
ul.pureCssMenu li {
	float:left;
}
ul.pureCssMenu ul li {
	float:none;
}
ul.pureCssMenu ul a {
	text-align:left;
	white-space:nowrap;
}
ul.pureCssMenu li.sep{
	text-align:left;
	padding:0px;
	line-height:0;
	height:100%;
}
ul.pureCssMenu li.sep span{
	float:none;	padding-right:0;
	width:3px;
	height:100%;
	display:inline-block;
	background-color:#cccccc #111111 #111111 #cccccc;	background-image:none;}
ul.pureCssMenu ul li.sep span{
	width:100%;
	height:3px;
}
ul.pureCssMenu li:hover{
	position:relative;
}
ul.pureCssMenu li:hover>a{
	background-color:#377D9F;
	border-color:#377D9F;
	border-style:solid;
	font:normal 12px Trebuchet MS, Tahoma;
	color: #FFFFFF;
	text-decoration:none;
}
ul.pureCssMenu li a:hover{
	position:relative;
	background-color:#377D9F;
	border-color:#377D9F;
	border-style:solid;
	font:normal 12px Trebuchet MS, Tahoma;
	color: #FFFFFF;
	text-decoration:none;
}
ul.pureCssMenu li.dis a {
	color: #666 !important;
}
ul.pureCssMenu img {border: none;float:left;_float:none;margin-right:2px;width:16px;
height:16px;
}
ul.pureCssMenu ul img {width:16px;
height:16px;
}
ul.pureCssMenu img.over{display:none}
ul.pureCssMenu li.dis a:hover img.over{display:none !important}
ul.pureCssMenu li.dis a:hover img.def {display:inline !important}
ul.pureCssMenu li:hover > a img.def  {display:none}
ul.pureCssMenu li:hover > a img.over {display:inline}
ul.pureCssMenu a:hover img.over,ul.pureCssMenu a:hover ul img.def,ul.pureCssMenu a:hover a:hover img.over{display:inline}
ul.pureCssMenu a:hover img.def,ul.pureCssMenu a:hover ul img.over,ul.pureCssMenu a:hover a:hover img.def{display:none}
ul.pureCssMenu a:hover ul{display:block}
ul.pureCssMenu span{
	display:block;
	background-image:url(./images/arr_white.gif);
	background-position:right center;
	background-repeat: no-repeat;
   padding-right:12px;}
ul.pureCssMenu li:hover>a>span{	background-image:url(./images/arrv_white.gif);
}
ul.pureCssMenu a:hover span{	_background-image:url(./images/arrv_white.gif)}
ul.pureCssMenu ul span,ul.pureCssMenu a:hover table span{background-image:url(./images/arr_white.gif)}
</style>
<!-- End PureCSSMenu.com STYLE -->



<!-- Start PureCSSMenu.com MENU -->
<ul class="pureCssMenu pureCssMenum">
	<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="goHome.action" />">Home</a></li>
	<li class="pureCssMenui"><a class="pureCssMenui" href="#"><span>Configuraci&oacute;n</span><![if gt IE 6]></a><![endif]><!--[if lte IE 6]><table><tr><td><![endif]-->
	<ul class="pureCssMenum">
		<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="abmUsuariosWelcome.action" />">Usuarios</a></li>
		<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="abmFabricasWelcome.action" />">F&aacute;bricas</a></li>
		<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="abmPuntosDestinoWelcome.action" />">Destinos</a></li>
	</ul>
	<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
	<li class="pureCssMenui"><a class="pureCssMenui" href="#"><span>C&aacute;lculos</span><![if gt IE 6]></a><![endif]><!--[if lte IE 6]><table><tr><td><![endif]-->
	<ul class="pureCssMenum">
		<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="calculateTransport.action" />">Transporte</a></li>
		<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="viajanteAction.action" />">Viajante</a></li>
	</ul>
	<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
	<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="logout.action" />">Logout</a></li>
</ul>

<!-- End PureCSSMenu.com MENU -->


<!-- (c) 2009, PureCSSMenu.com -->
</body>
</html> 