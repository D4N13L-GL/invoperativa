<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ABM de Fábricas</title>
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
        }
        
		var actualMarker;
		var lastMarker;
        function codeAddress() {
          var direccion = document.getElementById("direccion").value;
          if (geocoder) {
            geocoder.geocode( { 'address': direccion}, function(results, status) {
              if (status == google.maps.GeocoderStatus.OK) {
                if (results.size > 1)
                    alert('Debe ser más específico en la ubicación. Múltiples localizaciones posibles');
                else{
                	map.setCenter(results[0].geometry.location);
					lastMarker = actualMarker;
					
					actualMarker = new google.maps.Marker({
                	    					map: map, 
                    						position: results[0].geometry.location,
                    						icon: 'http://google-maps-icons.googlecode.com/files/factory.png'  
                						});
					alert("alskdjasklj");			
					lastMarker.setMap(null);

					//alert("ASKDLJ");
					//document.getElementById('latitud').value = results[0].geometry.location.lat();
					//alert("ASKDLJ 213");
					//document.getElementById('longitud').value = results[0].geometry.location.lng();
					//alert("ASKDLJ ghdfhdfjh");
					//document.getElementById('localizacion').value = results[0].address_components.long_name;
                }
				
              } else {
                alert("No se pudo localizar");
              }
            });
          }
        }
              

</script>


<body onload="initialize()">
	
	<s:form action="abmFabricas.action" method="post">
		
		Dirección: <input id="direccion" type="textbox" size="60" >
    	<input type="button" value="Localizar" onclick="codeAddress()">

		<div id="map_canvas" style="width: 500px; height: 500px;"></div>
		
		<s:hidden name="localizacion" />
		<s:hidden name="latitud" />
		<s:hidden name="longitud" />
		<s:textfield name="nombre" key="label.nombreFabrica" size="20"/>
		<s:textfield name="produccion" key="label.produccion" size="20"/>
		<s:submit method="crearFabrica" key="label.guardar" />
	
	</s:form>
</body>
</html>