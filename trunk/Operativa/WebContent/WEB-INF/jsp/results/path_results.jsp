<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
   src="http://maps.google.com/maps/api/js?sensor=false">
</script>

<style type="text/css">
body,td,th {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	color: #000;
}
body {
	background-color:#FFF;
}

#topbar {
	background:#E8ECF9;
	border:thin;
	border-bottom-style:inset;
	cursor:default;
	width:auto;
	height:30px;
	text-shadow:#000;
}

#right_container {
	float:right;
	width:550px;
	height:550px;	
}

#left_container {
	position:absolute;
	width:auto;
	height:auto;
}
</style>
</head>
<body>
	<s:form action="" method="post">
		<div id="header"></div>
		<div id="page">
  			<div id="topbar"> </div>
				<div id="left_container">
    				<div id="left_top_container">
        			</div>
        			<div id="left_bottom_container">
        			</div>
    			</div>
    
    			<div id="right_container">
    				<div id="right_top_container">
        				<div id="map_canvas">
                    	</div>
        			</div>
        			<div id="right_bottom_container"> 
       	  				<div id="results_table"></div>
        			</div>    	
    			</div>
	
		</div>
	</s:form>  

</body>
</html>