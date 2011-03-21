<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestión de Fábricas</title>
</head>

<script type="text/javascript"
   src="http://maps.google.com/maps/api/js?sensor=false">
</script>

<script type="text/javascript">

        var map;
        var geocoder;
        var directions = new google.maps.DirectionsService();
        
        function initialize() {
          geocoder = new google.maps.Geocoder();
          var renderer = new google.maps.DirectionsRenderer();
          var latlng = new google.maps.LatLng(-38.00,-57.33);
          var myOptions = {
            zoom: 8,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
          };
          map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

		  renderer.setMap(map);
		  renderer.setPanel(document.getElementById("directions"));	  
          
          var request = {
        	      origin: "La Rioja 3506, Mar del Plata",
        	      destination: "Rawson 777, Mar del Plata",
        	      travelMode: google.maps.DirectionsTravelMode.DRIVING
          };

          directions.route(request, function(result, status) {
        	    if (status == google.maps.DirectionsStatus.OK) {
        	      renderer.setDirections(result);
        	    }
          });       
                   
        }

		var directions;
        function codeAddress() {
		  var markers = new Array();
          var direccion = document.getElementById("direccion").value;
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

					document.getElementById('latitud').value = results[0].geometry.location.lat();
					document.getElementById('longitud').value = results[0].geometry.location.lng();
					document.getElementById('localizacion').value = results[0].address_components.long_name;

				}				
              } else {
                alert("No se pudo localizar");
              }
            });
          }
        }
        
              

</script>


<body onload="initialize()" style="
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	color: #000000;">
	<s:form action="abmFabricas.action" method="post">
		<div id="front_image" style="background: url(images/blue_background.jpg);
									 background-image: url(images/blue_background.jpg);
									 font-size: 22px;">
			Aqui puedes agregar fabricas al sistema brindando su direccion
		</div>	
		Dirección: <input type="text" id="direccion" size="60" >
    	<input type="button" value="Localizar" onclick="codeAddress()">
		<s:hidden id="localizacion" name="localizacion" />
		<s:hidden id="latitud" name="latitud" />
		<s:hidden id="longitud" name="longitud" />
		<s:textfield name="nombre" key="label.nombreFabrica" size="20"/>
		<s:textfield name="produccion" key="label.produccion" size="20"/>
		<s:submit method="crearFabrica" key="label.guardar" />
		<div id="map_container" style="background-color: #000000;
									   position: absolute;
									   margin-right: 10px;	
									   width: 530px; 
									   height: 530px;
									   top: 100;
									   right: 0 ">		
		<div id="map_canvas" style="position: relative;
									top: 15px;
									left: 15px;    
									width: 500px; 
									height: 500px;"></div>
		</div>	
		<p></p>
		Direcciones:
		<div id="directions" style= "background-image: url(images/blue_background);
									background-repeat: no-repeat;
      								background-position: left top;
									position: relative; 
									top: 100; 
									left: 0; 
									width: 600px; 
									height: 450px;"></div>

	</s:form>
</body>
</html>