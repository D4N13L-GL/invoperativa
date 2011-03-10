<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=yes" />
        <style type="text/css">
            html { height: 100% }
            body { height: 100%; margin: 0px; padding: 0px }
            #map_canvas { height: 100% }
        </style>
    <script type="text/javascript"
        src="http://maps.google.com/maps/api/js?sensor=false">
    </script>
    <script type="text/javascript">

        var map;
        function initialize() {
            var myLatlng = new google.maps.LatLng(-38.00,-57.33);
            var myOptions = {
            zoom: 4,
            center: myLatlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
            }
            map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

            //google.maps.event.addListener(map, 'zoom_changed', function() {
              //  setTimeout(moveToDarwin, 1000);
            //});

            google.maps.event.addListener(map, 'click', function(event) {
                placeMarker(event.latLng);
            });

            var marker = new google.maps.Marker({
                position: myLatlng,
                map: map,
                title:"Hello World!"
            });
            google.maps.event.addListener(marker, 'click', function() {
                map.setZoom(8);
            });
        }

        function placeMarker(location) {
            var newMark = new google.maps.Marker({
                position: location,
                map: map,
                title: "Punto "
            });
            map.setCenter(location);
        }

        function moveToDarwin() {
            var darwin = new google.maps.LatLng(-12.461334, 130.841904);
            map.setCenter(darwin);
        }

</script>
    </head>
    
    <body onload="initialize()">
        <p>Hello! This is the first test with google maps for +1.</p>
        <div id="map_canvas" style="width:100%; height:100%"></div>
    </body>
</html>