<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/purecssmenu.jsp" />

<html>
<head>

<style type="text/css">
table.imagetable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
	width: 100%;
}
table.imagetable th {
	background:#b5cfd2;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
}
table.imagetable td {
	background:#dcddc0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
}

.text {
    font-family: Arial,Helvetica,sans-serif;
    font-size: 12px;
    padding-left: 2px;
    padding-right: 2px;
    text-align: right;
    vertical-align: middle;
}

.tableTitle {
	style="background-color: #C8D7DF; 
	border: 1px solid #808D91; 
	font-family: arial; 
	padding: 3px; 
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orden de Entrega</title>
</head>

<script type="text/javascript"
   src="http://maps.google.com/maps/api/js?sensor=false&language=ES">
</script>

<script type="text/javascript">

        var map;
        var geocoder;
        var directionsDisplay;
        var directionsService;
        
        function initialize() {
          geocoder = new google.maps.Geocoder();
          var latlng = new google.maps.LatLng(-38.00,-57.33);
          var myOptions = {
            zoom: 8,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
          };
          map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
          directionsDisplay = new google.maps.DirectionsRenderer();
          directionsDisplay.setMap(map);
          directionsDisplay.setPanel(document.getElementById('divIndicaciones'));
		  directionsService = new google.maps.DirectionsService();
       	}

       function calcRoute() {
    	   var waypts = [];
    	   var destinos = document.getElementById("destinosLista").getElementsByTagName('input');
    	   var start = destinos[0].value;
    	   var end = destinos[0].value;
    	   for (var i = 1; i < destinos.length; i++) {
    	       waypts.push({
    	           location:destinos[i].value,
    	           stopover:true
    	       }); 
    	   }

    	   var request = {
    	       origin: start, 
    	       destination: end,
    	       waypoints: waypts,
    	       optimizeWaypoints: true,
    	       travelMode: google.maps.DirectionsTravelMode.DRIVING
    	   };
    	   
    	   directionsService.route(request, function(response, status) {
    	     if (status == google.maps.DirectionsStatus.OK) {
    	       directionsDisplay.setDirections(response);
    	       var route = response.routes[0];
    	       var summaryPanel = document.getElementById("divIndicaciones");
    	       summaryPanel.innerHTML = "";
    	       // For each route, display summary information.
    	       for (var i = 0; i < route.legs.length; i++) {
    	         var routeSegment = i+1;
    	         summaryPanel.innerHTML += "<b>Route Segment: " + routeSegment + "</b><br />";
    	         summaryPanel.innerHTML += route.legs[i].start_geocode.formatted_address + " to ";
    	         summaryPanel.innerHTML += route.legs[i].end_geocode.formatted_address + "<br />";
    	         summaryPanel.innerHTML += route.legs[i].distance.text + "<br /><br />";
    	       }
    	     }
    	   });
    	 }

</script>

<body onload="initialize()" style="font-family: verdana,arial,sans-serif;
								   font-size:15px;background: #F2F2F2;">

<s:hidden name="id" />
<br/>
<table cellpadding="5" cellspacing="5" width="100%" style="width: 100%; border:0px; margin-top: 15px;background: #D8D8D8">
					        <tr>
					            <td style="background-color: #C8D7DF;border: 1px solid #808D91;font-family: arial;padding: 3px;">
					                <input type="button" onclick="calcRoute()" value="Calcular Recorrido"/>
					                <div style="float: left;">
					                	<b>Recorrido de Entrega</b>
					                </div>
					            </td>
					        </tr>
				        </table>
				   		<table cellpadding="5" cellspacing="5" border="0" width="100%" style="background: #D8D8D8">
				   			<tr>
					            <td style="background-color: #C8D7DF;border: 1px solid #808D91;font-family: arial;padding: 3px;">
					                <div style="float: left;">
					                	Instrucciones de Direcci�n
					                </div>
					            </td>
					            <td style="background-color: #C8D7DF;border: 1px solid #808D91;font-family: arial;padding: 3px;" >
				                    <div style="float: left;">
				                        Recorrido en el Mapa
				                    </div>
					            </td>
					        </tr>
				   		    <tr>
				   		    	<td width="50%" valign="top" style="border: 1px solid #808D91">
				   		    		<div id="divIndicaciones" style="height: 650px; overflow: scroll; font-size: 10px; font-family: serif">
				   		    		
				   		    		</div>					                
					         	</td>
					         	
					         	<td width="50%" valign="top" style="border: 1px solid #808D91">
		                        	<table class="imagen" cellpadding="0" cellspacing="0" border="0" width="100%">
		                        		<tr>
		                        			<td height="300">
		                        				<div id="map_canvas" style="width: 600px; 
                                						height: 600px;
                                						right:0
                                						padding-top: 5px;"></div>
		                        			</td>
		                        		</tr>
		                        	</table>
		                        </td>
		                     </tr>
		            	</table>

	<div id="destinosLista">
		<s:iterator value="recorrido">
			<s:hidden name="%{nombre}" value="%{localizacion}" />
		</s:iterator>
	</div>
</body>
</html>