this.myLatitude = 0;
this.myLongitude = 0;

function myCurrentLocation() {
  var output = document.getElementById("output-pre-maps");
  var outputLatitude = document.getElementById("latitude");
  var outputLongitude = document.getElementById("longitude");
  var outputAltitude = document.getElementById("altitude");
  var outputAccuracy = document.getElementById("accuracy");
  var outputLocation = document.getElementById("locationname");


  if (!navigator.geolocation){
    output.innerHTML = "<p>Geolocation is not supported by your browser</p>";
    return;
  }

  function success(position) {
    var latitude  = position.coords.latitude;
    var longitude = position.coords.longitude;

    outputLatitude.innerHTML = latitude.toFixed(5);
    outputLongitude.innerHTML = longitude.toFixed(5);
    outputAltitude.innerHTML = position.coords.altitude;
    outputAccuracy.innerHTML = position.coords.accuracy.toFixed(2);

    window.mylat = latitude;
    output.innerHTML = "";

    //output.innerHTML = '<p>Latitude is ' + latitude + '° <br>Longitude is ' + longitude + '°</p>';


        /**     GOOGLE MAPS THING
         *
         */

            var mapOptions = {
              center: new google.maps.LatLng(latitude, longitude),
              zoom: 13
            };
            var map = new google.maps.Map(document.getElementById("maps-location"),
                mapOptions);

      // ADDING THE MARKER ON THE CENTER
      var myLatlng = new google.maps.LatLng(latitude,longitude);
      // To add the marker to the map, use the 'map' property


      var myPosition = new google.maps.Marker({
        position: myLatlng,
        map: map,
        animation: google.maps.Animation.DROP,
        title:"YOU ARE HERE!!!"
      });

      // GEOCODER IS USED TO GET THE LOCATION NAME FROM AN COORDINATES
        var geocoder;
        var marker;
        var infowindow = new google.maps.InfoWindow({maxWidth:350});

        var accuracyStatus;
        if(position.coords.accuracy<100){
           accuracyStatus = "<strong style=\"color:green;\"><span class=\"glyphicon glyphicon-ok\"></span>Accuracy : "+position.coords.accuracy.toFixed(2)+" m (Good)</strong>";
        }
        else{
           accuracyStatus = "<strong style=\"color:red;\"><span class=\"glyphicon glyphicon-warning-sign\"></span>Accuracy : "+position.coords.accuracy.toFixed(2)+" m (Poor)</strong>";
        }

        geocoder = new google.maps.Geocoder();
        geocoder.geocode({'latLng': myLatlng}, function(results, status) {
         if (status == google.maps.GeocoderStatus.OK) {
           if (results[1]) {
             map.setZoom(15);
             marker = new google.maps.Marker({
                 position: myLatlng,
                 map: map
             }); //end marker


             infowindow.setContent("<strong>You Are Here</strong><br />"+results[1].formatted_address+" <br/> Lat : " + latitude.toFixed(5) + " |  Long : " + longitude.toFixed(5) + "<br/>" + accuracyStatus+"<br/>"+' <strong><a href="https://www.facebook.com/sharer/sharer.php?u=http://mycurrentlocation.net/share-my-current-location.php?param='+latitude+'|'+longitude+'|'+results[1].formatted_address+'  ">Share My Location on Facebook</a></strong> &nbsp; | &nbsp;<strong><a href="https://grocerystorefinder.net" target="_blank">Grocery Store Near Me</strong></a> <br/>');
             infowindow.open(map, marker);
             outputLocation.innerHTML = results[1].formatted_address;

           }
         } else {
           alert("Couldn't determine your location name due to: " + status);
         } //end else

       }); //end geocoder

       if(position.coords.accuracy > 1000){
          outputAccuracy.style.color = "red";
          outputAccuracy.style.fontWeight = "900";
       }
       else{
          outputAccuracy.style.color = "green";
          outputAccuracy.style.fontWeight = "900";
          //alert("This is our best estimates of your location...");
          navigator.geolocation.clearWatch(watchid);
          return;
       }

  };

  function error() {
    output.innerHTML = "Sorry, we are Unable to retrieve your location yet. <br />If you use smartphone, please Turn on your GPS and or wait 1-2 minutes.";

    //var myip = document.getElementById("ipNumber").innerHTML;

    }


  var geo_options = {
    enableHighAccuracy: true,
    maximumAge        : 30000,
    timeout           : 27000
    };

  output.innerHTML = '<p>Please wait, we are detecting your location...</p>';

  //navigator.geolocation.getCurrentPosition(success, error, geo_options);
  var watchid = navigator.geolocation.watchPosition(success, error, geo_options);
  //alert("wpid : " + wpid);
}


function putMeOnMapIPAddress(mylatitude, mylongitude){

  var output = document.getElementById("output-pre-maps");
  var outputLatitude = document.getElementById("latitude");
  var outputLongitude = document.getElementById("longitude");
  var outputLocation = document.getElementById("locationname");


    var latitude  = mylatitude;
    var longitude = mylongitude;

    outputLatitude.innerHTML = latitude;
    outputLongitude.innerHTML = longitude;


    output.innerHTML = "";


        /**     GOOGLE MAPS THING
         *
         */

            var mapOptions = {
              center: new google.maps.LatLng(latitude, longitude),
              zoom: 13
            };
            var map = new google.maps.Map(document.getElementById("maps-location"),
                mapOptions);

      // ADDING THE MARKER ON THE CENTER
      var myLatlng = new google.maps.LatLng(latitude,longitude);
      // To add the marker to the map, use the 'map' property


      var myPosition = new google.maps.Marker({
        position: myLatlng,
        map: map,
        animation: google.maps.Animation.DROP,
        title:"YOU ARE HERE!!!"
      });

      // GEOCODER IS USED TO GET THE LOCATION NAME FROM AN COORDINATES
        var geocoder;
        var marker;
        var infowindow = new google.maps.InfoWindow({maxWidth:350});


        geocoder = new google.maps.Geocoder();
        geocoder.geocode({'latLng': myLatlng}, function(results, status) {
         if (status == google.maps.GeocoderStatus.OK) {
           if (results[4]) {
             map.setZoom(11);
             marker = new google.maps.Marker({
                 position: myLatlng,
                 map: map
             }); //end marker


             infowindow.setContent("<strong>You Are Here</strong><br />"+results[4].formatted_address+" <br/> Lat : " + latitude + " |  Long : " + longitude + "<br/>" +' <a href="https://www.facebook.com/sharer/sharer.php?u=http://mycurrentlocation.net/share-my-current-location.php?param='+latitude+'|'+longitude+'|'+results[1].formatted_address+'  ">Share My Location on Facebook</a>');
             infowindow.open(map, marker);
             outputLocation.innerHTML = results[4].formatted_address;

           }
         } else {
           alert("Couldn't determine your location name due to: " + status);
         } //end else

       }); //end geocoder

}


function putMeOnMap(mylatitude, mylongitude){

  var output = document.getElementById("output-pre-maps");
  var outputLocation = document.getElementById("locationname");


    var latitude  = mylatitude;
    var longitude = mylongitude;


    //alert("horee");



    output.innerHTML = "";


        /**     GOOGLE MAPS THING
         *
         */

            var mapOptions = {
              center: new google.maps.LatLng(latitude, longitude),
              zoom: 15
            };
            var map = new google.maps.Map(document.getElementById("maps-location"),
                mapOptions);



      // ADDING THE MARKER ON THE CENTER
      var myLatlng = new google.maps.LatLng(latitude,longitude);
      // To add the marker to the map, use the 'map' property




      var myPosition = new google.maps.Marker({
        position: myLatlng,
        map: map,
        animation: google.maps.Animation.DROP,
        title:"YOU ARE HERE!!!"
      });





      // GEOCODER IS USED TO GET THE LOCATION NAME FROM AN COORDINATES
        var geocoder;
        var marker;
        var infowindow = new google.maps.InfoWindow({maxWidth:350});

        //alert(mylatitude);
        geocoder = new google.maps.Geocoder();

        geocoder.geocode({'latLng': myLatlng}, function(results, status) {

         if (status == google.maps.GeocoderStatus.OK) {
           if (results[3]) {
             map.setZoom(15);
             marker = new google.maps.Marker({
                 position: myLatlng,
                 map: map
             }); //end marker



             infowindow.setContent("<strong>You Are Here</strong><br />"+results[3].formatted_address+" <br/> Lat : " + latitude + " |  Long : " + longitude + "<br/>" +' <a href="https://www.facebook.com/sharer/sharer.php?u=http://mycurrentlocation.net/share-my-current-location.php?param='+latitude+'|'+longitude+'|'+results[3].formatted_address+'  ">Share My Location on Facebook</a>');
             infowindow.open(map, marker);
             outputLocation.innerHTML = results[3].formatted_address;



           }
         } else {
           alert("Couldn't determine your location name due to: " + status);
         } //end else

       }); //end geocoder

}


function putMeOnMapDraggable(mylatitude, mylongitude){

  var output = document.getElementById("output-pre-maps");
  var outputLocation = document.getElementById("locationname");


    var latitude  = mylatitude;
    var longitude = mylongitude;


    //alert("horee");



    output.innerHTML = "";


            var mapOptions = {
              center: new google.maps.LatLng(latitude, longitude),
              zoom: 15
            };
            var map = new google.maps.Map(document.getElementById("maps-location"),
                mapOptions);


      // ADDING THE MARKER ON THE CENTER
      var myLatlng = new google.maps.LatLng(latitude,longitude);
      // To add the marker to the map, use the 'map' property



      // GEOCODER IS USED TO GET THE LOCATION NAME FROM AN COORDINATES
        var geocoder;
        var marker;
        var infowindow = new google.maps.InfoWindow({maxWidth:350});

        map.setZoom(2);
        marker = new google.maps.Marker({
          position: myLatlng,
          map: map,
          animation: google.maps.Animation.DROP,
          draggable : true,
          title : "Are You Here?"
        }); //end marker





        infowindow.setContent("<strong>Drag Me to Your Position</strong><br /> Zoom / unzoom using +/- button on the left <br/>See the coordinates on the right");
        infowindow.open(map, marker);

        google.maps.event.addListener(marker, 'dragend', function(evt){
            document.getElementById('thelatitude').value = evt.latLng.lat().toFixed(7);
            document.getElementById('thelongitude').value = evt.latLng.lng().toFixed(7);
        });

        google.maps.event.addListener(marker, 'dragstart', function(evt){
            document.getElementById('thelatitude').value = "Still dragging";
            document.getElementById('thelongitude').value = "Still dragging";
        });



}
