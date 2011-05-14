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
	width: 50%;
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
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestión de Fábricas</title>
</head>

<script type="text/javascript"
   src="http://maps.google.com/maps/api/js?libraries=geometry&sensor=false">
</script>

<script type="text/javascript">

        var map;
        var geocoder;
        
        function initialize() {
          geocoder = new google.maps.Geocoder();
          var latlng = new google.maps.LatLng(-38.00,-57.33);
          var myOptions = {
            zoom: 8,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
          };
          map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);


          google.maps.event.addListener(map, 'click', function(event) {
        	  codeLatLng(event.latLng);
          });
                  
        }

        function placeMarker(location) {
            var newMark = new google.maps.Marker({
                position: location,
                map: map,
                icon: 'http://google-maps-icons.googlecode.com/files/factory.png'  
            });
            map.setCenter(location);
        }

        function codeAddress() {
		  var markers = new Array();
          var direccion = document.getElementById("saveFabrica_localizacion").value;
          if (geocoder) {
            geocoder.geocode( { 'address': direccion}, function(results, status) {
              if (status == google.maps.GeocoderStatus.OK) {
                if (results.size > 1)
                    alert('Debe ser más específico en la ubicación. Múltiples localizaciones posibles');
                else{
                	map.setCenter(results[0].geometry.location);
					markers.push(new google.maps.Marker({
                	    					map: map, 
                    						position: results[0].geometry.location,
                    						icon: 'http://google-maps-icons.googlecode.com/files/factory.png'  
                        		}));

					componentes = results[0].address_components;
					direccion = "";
					for(var i = 0; i < componentes.length; i++){
						direccion+=componentes[i].short_name;
						if (i != componentes.length - 1)
							direccion+=", ";
					}
					latitud = results[0].geometry.location.lat();
					longitud = results[0].geometry.location.lng();
					
					document.getElementById('saveFabrica_latitud').value = (latitud+'');
					document.getElementById('saveFabrica_longitud').value = (longitud+'');
					document.getElementById('saveFabrica_localizacion').value = direccion;

				}
				
              } else {
                alert("No se pudo localizar");
              }
            });
          }
        }
        var infowindow = new google.maps.InfoWindow();

        function codeLatLng(latlng) {
 
            if (geocoder) {
              geocoder.geocode({'latLng': latlng}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                  if (results[1]) {
                    map.setZoom(11);
                    marker = new google.maps.Marker({
                        position: latlng, 
                        map: map
                    }); 

                    componentes = results[0].address_components;
					direccion = "";
					for(var i = 0; i < componentes.length; i++){
						direccion+=componentes[i].short_name;
						if (i != componentes.length - 1)
							direccion+=", ";
					}

					latitud = results[0].geometry.location.lat();
					longitud = results[0].geometry.location.lng();
					
					document.getElementById('saveFabrica_latitud').value = (latitud+'').replace('.',',');
					document.getElementById('saveFabrica_longitud').value = (longitud+'').replace('.', ',');
					document.getElementById('saveFabrica_localizacion').value = direccion;
					
                  }
                } else {
                  alert("Geocoder failed due to: " + status);
                }
              });
            }
          }
        var directionsService = new google.maps.DirectionsService();

		function getDistance(origen, destino, id){
		     var request = {
			    origin:origen, 
			    destination:destino,
			    travelMode: google.maps.DirectionsTravelMode.DRIVING
			  };
			  directionsService.route(request, function(result, status) {
			    if (status == google.maps.DirectionsStatus.OK) {
				    var totalDist = 0;
				    var legs = result.routes[0].legs;
			      	for (var i = 0; i < legs.length; i++)
				      	totalDist += (legs[i].distance.value/1000);
			      	resultado = document.getElementById(id);
			      	resultado.value = totalDist + '';
			    }
			    else{
				    alert("error"); 
				    alert(status);
			    }
			  });
					 
		}
        
        function calculateDistance(){
            if (document.getElementById('saveFabrica_localizacion').value == null ||
            	document.getElementById('saveFabrica_localizacion').value == "")
            	alert("Debe espeficicar una localización para la fábrica primero");
            else{
				div = document.getElementById('destinos');
				destinos = div.getElementsByTagName('input');
	
				for (var i = 0; i < destinos.length; i++){
					origen = destinos[i].value;
					getDistance(origen, document.getElementById('saveFabrica_localizacion').value, "costosMap['"+ destinos[i].id +"'].costo");
				}
	
				alert("Se calcularon las distancias con éxito");	
				document.getElementById("distanciasBtn").disabled=true;
				document.getElementById("guardarBtn").disabled=false;
            }
        }

        var fabricasMarkers = new Array();
        function showHideFabricas(){
        	if (document.getElementById("mostrarFabCheck").checked)
        		showFabricas();
			else
				hideFabricas();
        }

        
        function showFabricas(){
        	div = document.getElementById('fabricas');
    		fabricas = div.getElementsByTagName('input');
    		for ( var i = 0; i < fabricas.length; i++) {
    			latLng = fabricas[i].value;
    			latLng = latLng.split("|");
    			lat = parseFloat(latLng[0]);
    			lng = parseFloat(latLng[1]);
    			position = new google.maps.LatLng(lat,lng);
    			fabricasMarkers.push(new google.maps.Marker({
    				map: map, 
    				position: position,
    				icon: 'http://google-maps-icons.googlecode.com/files/factory.png'  
    			}));
        	}
        }

        function hideFabricas(){
            for ( var i = 0; i < fabricasMarkers.length; i++) {
				fabricasMarkers[i].setMap(null);
			}
        }
</script>

<body onload="initialize()" style="font-family: verdana,arial,sans-serif;
								   font-size:15px;">

	<br />
    <div id="abm" style="position:absolute; 
    					 top:150;
                         left:0;
                         width: 300px; 
                         height: 500px;
                         margin-left: 30px;
                         margin-top: 50px">
                        
		<s:form action="saveFabrica">
		<s:hidden name="id" />
		<s:textfield name="nombre" label="Nombre"/>
		<s:textfield name="latitud" label="Latitud" />
		<s:textfield name="longitud" label="Longitud" />
		<s:textfield name="unidades" label="Producción" />
		<table>
			<tr>
				<td><s:textfield name="localizacion" label="Dirección" size="70" /></td>
				<td><input type="button" value="Localizar" onclick="codeAddress()"></td>
			</tr>
		</table>
		
		<div align="left">
			<input value="Calcular Distancias" type="button" id="distanciasBtn" onclick="calculateDistance()"/>
			<s:submit value="Guardar" id="guardarBtn" disabled="true"/>
			<input type="checkbox" value="Mostrar Fábricas" id="mostrarFabCheck" onclick="showHideFabricas()"/> Mostrar Fábricas <br>
		</div>
		<div id="costos">
			<s:iterator value="costos">
    			<s:hidden name="costosMap['%{destino.id}'].costo" value="%{costo}" id="costosMap['%{destino.id}'].costo"/>
			</s:iterator>
		</div>
		</s:form>
    </div>
	
    <div id="map_canvas" style="width: 500px; 
                                height: 500px;
                                right:0;
                                position:absolute;
                                margin-right:60px"></div>

<br />
	<div style="position:relative; 
	            top:270px; 
	            left:0px;
	            margin-left: 20px;">
	
	<table cellpadding="5px" border="1" class="imagetable">
		<tr>
			<th>Nombre</th>
			<th>Localizaci&oacute;n</th>
			<th>Latitud</th>
			<th>Longitud</th>
			<th>Producci&oacute;n</th>
			<th></th>
			<th></th>
		</tr>
		<s:iterator value="fabricaList" status="fabricastatus">
			<tr>
				<td><s:property value="nombre" /></td>
				<td><s:property value="localizacion" /></td>
				<td><s:property value="latitud" /></td>
				<td><s:property value="longitud" /></td>
				<td><s:property value="unidades" /></td>
				<td><s:url id="editURL" action="editFabrica">
					<s:param name="id" value="%{id}"></s:param>
				</s:url> <s:a href="%{editURL}">Editar</s:a></td>
				<td><s:url id="deleteURL" action="deleteFabrica">
					<s:param name="id" value="%{id}"></s:param>
				</s:url> <s:a href="%{deleteURL}">Eliminar</s:a></td>
			</tr>
		</s:iterator>
	</table>
	</div>
	
	<div id="destinos">
		<s:iterator value="costos">
			<s:hidden name="%{destino.id}" value="%{destino.localizacion}" />
		</s:iterator>
	</div>
	<div id="fabricas">
		<s:iterator value="fabricaList">
			<s:hidden name="%{nombre}" value="%{latitud}|%{longitud}" />
		</s:iterator>
	</div>
	
</body>
</html>