<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/purecssmenu.jsp" />
<jsp:include page="/WEB-INF/jsp/checkLogin.jsp" />
<jsp:include page="/WEB-INF/jsp/estilos.jsp" />

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestión de Puntos de Destino</title>
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

          latitud = document.getElementById('updatePuntoDestino_latitud').value;
		  longitud = document.getElementById('updatePuntoDestino_longitud').value;
          document.getElementById('updatePuntoDestino_latitud').value = (latitud).replace('.',',').substring(0,6);
		  document.getElementById('updatePuntoDestino_longitud').value = (longitud).replace('.', ',').substring(0,6);    
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
          var direccion = document.getElementById("updatePuntoDestino_localizacion").value;
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
					
					document.getElementById('updatePuntoDestino_latitud').value = (latitud+'');
					document.getElementById('updatePuntoDestino_longitud').value = (longitud+'');
					document.getElementById('updatePuntoDestino_localizacion').value = direccion;

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
					
					document.getElementById('updatePuntoDestino_latitud').value = (latitud+'').replace('.',',').substring(0,6);
					document.getElementById('updatePuntoDestino_longitud').value = (longitud+'').replace('.', ',').substring(0,6);
					document.getElementById('updatePuntoDestino_localizacion').value = direccion;
					
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
            if (document.getElementById('updatePuntoDestino_localizacion').value == null ||
            	document.getElementById('updatePuntoDestino_localizacion').value == "")
            	alert("Debe espeficicar una localización para el punto de destino primero");
            else{
				div = document.getElementById('destinos');
				destinos = div.getElementsByTagName('input');
	
				for (var i = 0; i < destinos.length; i++){
					origen = destinos[i].value;
					getDistance(origen, document.getElementById('updatePuntoDestino_localizacion').value, "costosMap['"+ destinos[i].id +"'].costo");
				}
	
				document.body.style.cursor='wait';
				setTimeout("alert('Se calcularon las distancias con éxito'); document.body.style.cursor='default';", 3000);	
				document.getElementById("distanciasBtn").disabled=true;
				setTimeout("document.getElementById('guardarBtn').disabled=false", 3000);
            }
        }

        var destinosMarkers = new Array();
        function showHideFabricas(){
        	if (document.getElementById("mostrarFabCheck").checked)
        		showDestinos();
			else
				hideDestinos();
        }

        
        function showDestinos(){
        	div = document.getElementById('destinosLista');
    		destinos = div.getElementsByTagName('input');
    		for ( var i = 0; i < destinos.length; i++) {
    			latLng = destinos[i].value;
    			latLng = latLng.split("|");
    			lat = parseFloat(latLng[0]);
    			lng = parseFloat(latLng[1]);
    			position = new google.maps.LatLng(lat,lng);
    			destinosMarkers.push(new google.maps.Marker({
    				map: map, 
    				position: position,
    				icon: 'http://google-maps-icons.googlecode.com/files/factory.png'  
    			}));
        	}
        }

        function hideDestinos(){
            for ( var i = 0; i < destinosMarkers.length; i++) {
				destinosMarkers[i].setMap(null);
			}
        }

</script>

<body onload="initialize()" style="font-family: verdana,arial,sans-serif;
								   font-size:15px;background: #F2F2F2;">
<s:form action="updatePuntoDestino" theme="simple">
<s:hidden name="id" />
<br/>
<table cellpadding="5" cellspacing="5" width="100%" style="width: 100%; border:0px; margin-top: 15px;background: #D8D8D8">
					        <tr>
					            <td class="tdHeader">
					                <div style="float: left;">
					                	<b>Edición de Punto de Destino</b>
					                </div>
					            </td>
					        </tr>
				        </table>
				   		<table cellpadding="5" cellspacing="5" border="0" width="100%" style="background: #D8D8D8">
				   			<tr>
					            <td class="tdHeader">
					                <div style="float: left;">
					                	Datos del Punto de Destino
					                </div>
					            </td>
					            <td class="tdHeader" >
				                    <div style="float: left;">
				                        Ubicación del Punto de Destino
				                    </div>
					            </td>
					        </tr>
				   		    <tr>
				   		    	<td width="63%" valign="top" style="border: 1px solid #808D91">
				   		    		<table cellpadding="5" border="0" width="100%">
				   		    			<tr>
				   		    				<td style="text-align: right; font-weight: bold; width: 36%;">
		                           				<span class="text" >Nombre</span>
		                        			</td>
					                        <td style="padding-left: 5px;" width="64%" class="registro">
					                            <s:textfield name="nombre"/>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td style="text-align: right; font-weight: bold;">
					                           <span class="text" > Latitud</span>
					                        </td>
					                        <td style="padding-left: 5px;" class="registro">
					                        	<s:textfield name="latitud"/>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td style="text-align: right; font-weight: bold;">
					                           <span class="text" >Longitud</span>
					                        </td>
					                        <td style="padding-left: 5px;" class="registro">
					                        	<s:textfield name="longitud"/>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td style="text-align: right; font-weight: bold;" >
					                           	<span class="text" >Localización</span>
					                        </td>
					                        <td class="registro">
					                        	<s:textfield name="localizacion" size="50"/>
					                        	<input type="button" value="Localizar" onclick="codeAddress()">
					                        </td>
					                    </tr>
					                    <tr>
					                        <td style="text-align: right; font-weight: bold;">
					                           	<span class="text" >Demanda</span>
					                        </td>
					                        <td style="padding-left: 5px;" class="registro">
					                        	<s:textfield name="unidades" />
					                        </td>
					                    </tr>
					                </table>
					                <br/>
					                
					                <table cellpadding="0" cellspacing="0" border="0" width="100%">
						            	<tr>
					                        <td style="text-align: right; font-weight: bold;">
					                            <input value="Calcular Distancias" type="button" id="distanciasBtn" onclick="calculateDistance()"/>
					                        </td>
					                        <td style="padding-left: 5px;" class="registro">
					                        	<s:submit value="Guardar" id="guardarBtn" disabled="true"/>
					                        </td>
					                    </tr>
					                </table>
									<br/>														
					                
					         	</td>
					         	
					         	<td width="37%" valign="top" style="border: 1px solid #808D91">
		                        	<table class="imagen" cellpadding="0" cellspacing="0" border="0" width="100%">
		                        		<tr>
		                        			<td height="300">
		                        				<div id="map_canvas" style="width: 500px; 
                                						height: 500px;
                                						right:0
                                						padding-top: 5px;"></div>
		                        			</td>
		                        		</tr>
		                        	</table>
		                        </td>
		                     </tr>
		            	</table>
<div id="costos">
			<s:iterator value="costos">
    			<s:hidden name="costosMap['%{destino.id}'].costo" value="%{costo}" id="costosMap['%{destino.id}'].costo"/>
			</s:iterator>
</div>		            	
</s:form>
	
	<div id="destinos">
		<s:iterator value="costos">
			<s:hidden name="%{destino.id}" value="%{destino.localizacion}" />
		</s:iterator>
	</div>
	<div id="destinosLista">
		<s:iterator value="puntoDestinoList">
			<s:hidden name="%{nombre}" value="%{latitud}|%{longitud}" />
		</s:iterator>
	</div>
</body>
</html>