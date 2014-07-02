<%@ page import="edu.gatech.cs2340.trip.model.accounts.Account" %>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <title>Map</title>
        <style type="text/css">

        </style>
        <link rel="stylesheet" type="text/css" href="stylesheets/home.css">

        <!-- Google Maps and Places API -->
 <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3&key=AIzaSyBPYvn0hWrHIz-OEKXLcmQfGzqMK_Tk0wU&sensor=false"></script>
        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
            var mapCenter;
            var map;
            var itinerary;
            var detailMarker = new google.maps.Marker({
                                                 map: null,
                                                 position: null
                                             });
            var infoWindow = new google.maps.InfoWindow({
                             content: ""
                         });
            var geocoder = new google.maps.Geocoder();
            initialize();
            $("#geocode").click(geocode);
            $("#placesearch").click(findPlaces);
            function map_initialize() {
                var googleMapOptions =
                {
                    center: mapCenter,
                    zoom: 12,
                    maxZoom: 18,
                    minZoom: 8,
                    zoomControlOption: {
                    style: google.maps.ZoomControlStyle.SMALL
                    },
                    scaleControl: true,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                map = new google.maps.Map(document.getElementById("map"), googleMapOptions);

                var marker = new google.maps.Marker({
                                            map: map,
                                            position: itinerary.centralLocation
                                        });
            }

            function initialize() {
                 $.post("places",
                 {
                     action: "itinerary"
                 },
                 function(data, status) {
                    itinerary = data;
                    if($.isEmptyObject(itinerary.centralLocation)) {
                        itinerary.centralLocation = {lat:33.7489, lng:-84.388};
                        updateCentralLocation(itinerary.centralLocation);
                    }
                    mapCenter = data.centralLocation;
                    map_initialize();
                 }, "json");
            }

            function updateCentralLocation(centralLocation) {
                $.post("places",
                {
                    action: "centralLocation",
                    location: JSON.stringify(centralLocation)
                },
                function(data, status) {
                },
                "text");
            }

            function geocode()
            {
                var address = $('#address').val();
                geocoder.geocode( { 'address': address}, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK)
                    {
                        itinerary.centralLocation = {lat:results[0].geometry.location.lat(),
                                                     lng:results[0].geometry.location.lng()};
                        updateCentralLocation(itinerary.centralLocation);
                        map.setCenter(itinerary.centralLocation);
                        var marker = new google.maps.Marker({
                            map: map,
                            position: itinerary.centralLocation
                        });
                    }
                    else
                    {
                        alert("Geocode was not successful for the following reason: " + status);
                    }
                });
            }

            function findPlaces()
            {
                $("#searchResults").off("click", "#result", placeDetails);
                var squery = $("#placequery").val();
                var type = $('input[name="search_type"]:checked').val();
                var mode = $('input[name="placemode"]:checked').val();
                var srating = $('select[name="min_rating"]').val();
                var price = $('select[name="price_range"]').val();
                $.post("places",
                {
                    action: "places",
                    latitude: itinerary.centralLocation.lat,
                    longitude: itinerary.centralLocation.lng,
                    query: squery,
                    search_type: type,
                    maxprice: price,
                    rating: srating,
                    radius: mode
                },
                function(data, status) {
                    $("#searchResults").html("");
                    for(i = 0; i<data.results.length; i++) {
                        var img;
                        if(data.results[i].photos != undefined) {
                            img = "<img src='https://maps.googleapis.com/maps/api/place/photo?photoreference="+
                               data.results[i].photos[0].photo_reference + "&key=AIzaSyBPYvn0hWrHIz-OEKXLcmQfGzqMK_Tk0wU&maxheight=50"+
                               "&maxwidth=50' width=50 height=50>";
                        } else {
                            img = "<img src='" + data.results[i].icon +"' width=50 height=50'>";
                        }
                        $("#searchResults").append(
                        "<div id='result' key='"+ data.results[i].place_id + "'>" + img +
                        data.results[i].name+" "+ data.results[i].vicinity+ "</div>");
                    }
                    $("#searchResults").on("click", "#result", placeDetails);
                }, "json");
            }

            function placeDetails() {
                key = $(this).attr("key");
                $.post("places",
                {
                    action: "details",
                    placeId: key
                },
                function(data, status) {
                    detailMarker.setMap(null);
                    infoWindow.close();
                    detailMarker = new  google.maps.Marker({
                                    map: map,
                                    position: data.result.geometry.location
                                });
                    var contentString = data.result.name + " " +
                                 data.result.formatted_address + " " +
                                 data.result.formatted_phone_number + " ";
                    infoWindow = new google.maps.InfoWindow({
                                    content: contentString
                                });
                    infoWindow.open(map,detailMarker);
                    google.maps.event.addListener(infoWindow,'closeclick',function(){
                       detailMarker.setMap(null); //removes the marker
                       map.panTo(itinerary.centralLocation);
                    });

                    map.panTo(data.result.geometry.location);

                }, "json");

            }

        });
        </script>
    </head>

    <body>

        <!-- side panel div container -->
        <div id="left_col">

            <% if(request.getSession().getAttribute("account") == null) { %>
                <a href="/trip/login"> Login </a>
            <% } else {
                Account userAccount = (Account) request.getSession().getAttribute("account");
                out.println("You are logged in as: " + userAccount.getName());
                out.println("</br><a href=\"/trip/update\">Update<a>");
                out.println("</br><a href=\"/trip/logout\">Logout<a>");
            } %>
            <br>
            <a href="/trip/main.jsp">Home</a>

            <h2>Choose a Central Location</h2>
            <!-- search box -->
                <div style="border:1px solid #ccc; background:#e5e5e5; padding:10px;">
                    <input type="text" id="address">
                    <input type="button" value="Find" id="geocode">
                </div>

                <!-- display directions -->
                <div id="directionsPanel"></div>
        </div>

        <!-- map div container -->
        <div id="map_canvas" style="position: fixed"><div id="map" style="height:100%"></div></div>

        <!-- right div container -->
        <div id="right_col">
            Search: <input type="text" id="placequery">
            <input type="submit" value="Submit" id="placesearch"><br>
            <input type="radio" name="search_type" value="food">Food<br>
            <input type="radio" name="search_type" value="attractions">Attractions<br>
            Max Price<br>
            <select name="price_range">
            <option value="1">$</option>
            <option value="2">$$</option>
            <option value="3">$$$</option>
            <option value="4">$$$$</option>
            </select>
            Minimum Rating<br>
            <select name="min_rating">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            </select>
            <input name="placemode" type="radio" value="50000" checked>Drive
            <input name="placemode" type="radio" value="5000">Walk
            <input name="placemode" type="radio" value="20000">Bicycle


            <div id="searchResults">

            </div>
        </div>


    </body>
</html>