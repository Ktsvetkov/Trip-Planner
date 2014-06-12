<%@ page import="edu.gatech.cs2340.trip.model.Account" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
            <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

        <script>
          var geocoder;
          var map;
          var pointsSelectable = 4;
          var pointsSelected = 0;
          var currentLocation;

          function initialize() {

            if(navigator.geolocation) {
              navigator.geolocation.getCurrentPosition(function(position) {
                var pos = new google.maps.LatLng(position.coords.latitude,
                                                 position.coords.longitude);

                map.setCenter(pos);
                document.getElementById('currentLocation').innerHTML += "(" + position.coords.latitude
                       + ", " + position.coords.longitude + ")";

              }, function() {
                handleNoGeolocation(true);
              });
            } else {
              // Browser doesn't support Geolocation
              handleNoGeolocation(false);
            }


            geocoder = new google.maps.Geocoder();
            var latlng = currentLocation;
            var mapOptions = {
              zoom: 8,
              center: latlng
            }

            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);


            google.maps.event.addListener(map, 'click', function(event) {
                 var clickLocation =
                    new google.maps.LatLng(event.latLng.lat(), event.latLng.lng());

                if (pointsSelected < pointsSelectable) {

                  if (pointsSelected == 3) {

                    pointsSelected++;
                    var marker4 = new google.maps.Marker({
                        position: clickLocation,
                        map: map,
                        title: 'pinFour'
                    });

                    document.getElementById('pinFour').innerHTML += clickLocation;

                  }



                  if (pointsSelected == 2) {

                    pointsSelected++;
                    var marker3 = new google.maps.Marker({
                        position: clickLocation,
                        map: map,
                        title: 'pinThree'
                    });

                    document.getElementById('pinThree').innerHTML += clickLocation;

                  }



                  if (pointsSelected == 1) {

                    pointsSelected++;
                    var marker2 = new google.maps.Marker({
                        position: clickLocation,
                        map: map,
                        title: 'pinTwo'
                    });

                    document.getElementById('pinTwo').innerHTML += clickLocation;

                  }

                  if (pointsSelected == 0) {

                    pointsSelected++;
                    var marker1 = new google.maps.Marker({
                        position: clickLocation,
                        map: map,
                        title: 'pinOne'
                    });

                    document.getElementById('pinOne').innerHTML += clickLocation;

                  }



                }
            });

          }

          function codeAddress() {
            var address = document.getElementById('address').value;
            geocoder.geocode( { 'address': address}, function(results, status) {
              if (status == google.maps.GeocoderStatus.OK) {
                map.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location
                });
                 document.getElementById('currentLocation').innerHTML = 'Current location: ' + results[0].geometry.location
              } else {
                alert('Geocode was not successful for the following reason: ' + status);
              }
            });
          }

          function clearCoordinates() {
            delete marker1;
            delete marker2;
            delete marker3;
            delete marker4;
            pointsSelected = 0;
            document.getElementById('currentLocation').innerHTML = 'Current location: '
            document.getElementById('pinOne').innerHTML = 'Pin One location: ';
            document.getElementById('pinTwo').innerHTML = 'Pin Two location: ';
            document.getElementById('pinThree').innerHTML = 'Pin Three location: ';
            document.getElementById('pinFour').innerHTML = 'Pin Four location: ';
            initialize();
          }

          google.maps.event.addDomListener(window, 'load', initialize);

        </script>
</head>

<body>

    <!-- Start tool bar-->

    <div id="nav">
        <ul>
            <% if(request.getSession().getAttribute("account") == null) { %>
            <li style="background-color: #fff"><a href="#"><font color="#c00">Home</a></font></li>
            <li><a href="/trip/login.jsp">Login</a></li>
            <li><a href="/trip/register.jsp">Register</a></li>
            <% } else {%>
            <li style="background-color: #fff"><a href="#"><font color="#c00">Home</a></font></li>
            <li><a href="/trip/update.jsp">Update Info</a></li>
            <li><a href="/trip/logout">Logout</a></li>
            <% } %>
            <li>
                <form method="get" action="#" >
                    <input id="address" type="text" maxlength="120" placeholder="Search Location">
                    <input type="button" value="Find" onclick="codeAddress()">
                </form></li>
        </ul>
    </div>

    <!-- End tool bar-->

    <div id="userInfo">

        <% if(request.getSession().getAttribute("account") != null) { %>
        <%      Account userAccount = (Account) request.getSession().getAttribute("account");%>
        <%      out.println("Welcome " + userAccount.getName() + "!");%>
        <a href="/trip/logout">logout<a>
        <% } %>
    </div>

    <div id="map-canvas"></div>

</body>
</html>