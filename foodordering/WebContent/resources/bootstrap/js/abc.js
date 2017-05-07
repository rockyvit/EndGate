var showMap = $('#show-map');

function initialize() {
    var mapOptions = {
	    center: { lat: 0, lng: 0 },
	    zoom: 8
	};
	var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
}

$(document).ready(function(){
    $('#show-map').on('click',initialize)
});