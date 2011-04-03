<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
   src="http://maps.google.com/maps/api/js?sensor=false">
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
          var direccion = document.getElementById("savePuntoDestino_localizacion").value;
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
					
					document.getElementById('savePuntoDestino_latitud').value = (latitud+'');
					document.getElementById('savePuntoDestino_longitud').value = (longitud+'');
					document.getElementById('savePuntoDestino_localizacion').value = direccion;

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
					
					document.getElementById('savePuntoDestino_latitud').value = (latitud+'').replace('.',',');
					document.getElementById('savePuntoDestino_longitud').value = (longitud+'').replace('.', ',');
					document.getElementById('savePuntoDestino_localizacion').value = direccion;
					
                  }
                } else {
                  alert("Geocoder failed due to: " + status);
                }
              });
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
                        
		<s:form action="savePuntoDestino.action" theme="xhtml">
		<s:hidden name="id" />
		<s:textfield name="nombre" label="Nombre"/>
		<s:textfield name="latitud" label="Latitud" />
		<s:textfield name="longitud" label="Longitud" />
		<s:textfield name="demanda" label="Demanda" />
		<table>
			<tr>
				<td><s:textfield name="localizacion" label="Dirección" size="70" /></td>
				<td><input type="button" value="Localizar" onclick="codeAddress()"></td>
			</tr>
		</table>
		<s:submit value="Guardar" />
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
			<th>Demanda</th>
			<th></th>
			<th></th>
		</tr>
		<s:iterator value="puntoDestinoList" status="puntoDestinostatus">
			<tr>
				<td><s:property value="nombre" /></td>
				<td><s:property value="localizacion" /></td>
				<td><s:property value="latitud" /></td>
				<td><s:property value="longitud" /></td>
				<td><s:property value="demanda" /></td>
				<td><s:url id="editURL" action="editPuntoDestino">
					<s:param name="id" value="%{id}"></s:param>
				</s:url> <s:a href="%{editURL}">Editar</s:a></td>
				<td><s:url id="deleteURL" action="deletePuntoDestino">
					<s:param name="id" value="%{id}"></s:param>
				</s:url> <s:a href="%{deleteURL}">Eliminar</s:a></td>
			</tr>
		</s:iterator>
	</table>
	</div>

</body>
</html>