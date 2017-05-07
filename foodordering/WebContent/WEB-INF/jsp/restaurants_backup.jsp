<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<head>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB_Y6OnOzSbWVU31Nw6AI5ypoIBc5N6GOo&callback=initMap" async defer></script>

	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    
    <!-- <script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script> -->
    



    <style>
  #map {
  
    height: 300px;
width:300px;
  }
</style>
</head>
<h2 class="main_title">Restaurants</h2>
  
<script type="text/javascript">
<!--
var search_text = undefined;

var restaurants = undefined;

var groupsIndex = [];
var groups = [];

var dishesIndex = [];
var dishes = [];

var detailsIndex = [];
var details = [];

var currentSite = 'r'; /*r,g,d,s*/
var currentDetails = 0; /* rest id, group id */

function clearRestCache()
{
	restaurants = undefined;
}

function clearGroupsCache()
{
	groupsIndex = [];
	groups = [];
}

function clearDishesCache()
{
	dishesIndex = [];
	dishes = [];
}

function clearDetailsCache()
{
	detailsIndex = [];
	details = [];
}

function clearCache()
{
	clearRestCache();
	clearGroupsCache();
	clearDishesCache();
	clearDetailsCache();
}

function enableInterface() {
	//$('#back').fadeOut("fast");
	$('#ajax_content').css('height','auto');
}

function showAjaxWaiting()
{
	ac = $('#ajax_content');
	height = ac.height();
	ac.html('<img src="/foodordering/images/ajax-loader-3.gif" />').css('height',height);
		
}

/* Groups retrieval */
function addRestaurantsToCache(data)
{
	restaurants = data;
}
function loadRestaurants() {
	var cnt = 0;
	currentSite = 'r';
	$('.main_title').text('Restaurants');
	if (restaurants != undefined) { /* Use data from cache */
		$('#ajax_content').html(restaurants);
	} else { /* Send ajax request to server */
//		$('#back').fadeIn('fast', function() {
			showAjaxWaiting();
			$.post('/foodordering/ajaxGetRestaurants.do', function(data) {
				addRestaurantsToCache(data);
				$('#ajax_content').html(data);
				enableInterface();
			});
	//	});
	}
}

/* Groups retrieval */
function addGroupsToCache(restId, data)
{
	groupsIndex[groupsIndex.length] = restId;
	groups[groups.length] = data;
}
function fixGroupRowHeight()
{
	$('.menu_group tr').each(function() {
		var font_size = 15;
		while ($(this).height() > 31) {
			font_size--;
			$(this).find('.elem_content').css('font-size',font_size.toString()+'px');
			if (font_size < 5)
				return;
		}
	});
}
function loadGroupsFromRestaurant(restId) {
	var cnt = 0;
	var found = -1;
	currentSite = 'g';
	currentDetails = restId;
	$('.main_title').text('Groups');
	for(cnt=0; cnt < groupsIndex.length; cnt++) {
		if (groupsIndex[cnt] == restId) {
			found = cnt;
			break;
		}
	}
	if (found != -1) { /* Use data from cache */
		$('#ajax_content').html(groups[found]);
	} else { /* Send ajax request to server */
		//$('#back').fadeIn('fast', function() {
			showAjaxWaiting();
			$.post('/foodordering/ajaxGetGroups.do', {restId: restId }, function(data) {
				addGroupsToCache(restId, data);
				$('#ajax_content').html(data);
				enableInterface();
			});
		//});
	}
}


/* Dishes retrieval */
function addDishesToCache(groupId, data)
{
	dishesIndex[dishesIndex.length] = groupId;
	dishes[dishes.length] = data;
}
function fixDishRowHeight()
{
	$('.menu_dish tr').each(function() {
		var font_size = 15;
		while ($(this).height() > 29) {
			font_size--;
			$(this).find('.name').css('font-size',font_size.toString()+'px');
			if (font_size < 5)
				return;
		}
	});
}

function loadDishesFromGroup(groupId) {
	var cnt = 0;
	var found = -1;
	currentSite = 'd';
	currentDetails = groupId;
	$('.main_title').text('Dishes');
	for(cnt=0; cnt < dishesIndex.length; cnt++) {
		if (dishesIndex[cnt] == groupId) {
			found = cnt;
			break;
		}
	}
	if (found != -1) { /* Use data from cache */
		$('#ajax_content').html(dishes[found]);
		fixDishRowHeight();
	} else { /* Send ajax request to server */
		//$('#back').fadeIn('fast', function() {
			showAjaxWaiting();
			$.post('/foodordering/ajaxGetDishes.do', {groupId: groupId }, function(data) {
				addDishesToCache(groupId, data);
				$('#ajax_content').html(data);
				fixDishRowHeight();
				enableInterface();
			});
		//});
	}
}


/* Detils retrieval */
function addDetailsToCache(dishId, data)
{
	detailsIndex[detailsIndex.length] = dishId;
	details[details.length] = data;
}

function fixTooltipWidth()
{
	var di = $('#dish_info');
	if (di.width() > 300) {
		di.css('width','300px');
	}
}

function loadDetailsFromDish(dishId) {
	var cnt = 0;
	var found = -1;
	for(cnt=0; cnt < detailsIndex.length; cnt++) {
		if (detailsIndex[cnt] == dishId) {
			found = cnt;
			break;
		}
	}
	if (found != -1) { /* Use data from cache */
		$('#dish_info').html(details[found]);
		fixTooltipWidth();
	} else { /* Send ajax request to server */
		$.post('/foodordering/ajaxGetDetails.do', {dishId: dishId }, function(data) {
			addDetailsToCache(dishId, data);
			$('#dish_info').html(data)
			fixTooltipWidth();
		});
	}
}
function hideDishInfo()
{
	$('#dish_info').hide();
}

function ajaxSearch(value) {
	$('.main_title').text('Search results');
	currentSite = 's';
	search_text = value;
	showAjaxWaiting();
	$.post('/foodordering/search.do', {searching: value }, function(data) {
		$('#ajax_content').html(data);
		fixGroupRowHeight();
		fixDishRowHeight();
		enableInterface();
	});
}

function resfreshCurrentView()
{
	clearDishesCache(); /* The difference between logged in and not logged is only in dishes tab */
	switch (currentSite)
	{
		case 'r':
			search_text = undefined;
			loadRestaurants();
			break;
		case 'g':
			loadGroupsFromRestaurant(currentDetails);
			break;
		case 'd':
			loadDishesFromGroup(currentDetails);
			break;
		case 's':
			ajaxSearch(search_text);
			break;
		default:
			break;
	}
}

function loadPrevious(mode, param) {
	if (search_text != undefined) {
		currentSite = 's';
		resfreshCurrentView();
	} else {
		switch(mode) {
			case 'r':
				loadRestaurants();
				break;
			case 'g':
				loadGroupsFromRestaurant(param);
				break;
			default: 
				loadRestaurants();
				break;
		} 	
	}
}

$(document).ready(function(){
	/* Error handler */
	$('.log').ajaxError(function(e, xhr, settings, exception) {
	//   if (settings.url == '/foodordering/ajaxGetGroups.do') {
	// 	
	//   }
		//alert('error');
		enableInterface();
	});
	
	loadRestaurants();
	$('#search_button').click(function() {	ajaxSearch($('#search_text').val()); });
	$('#search_text').keydown(function(e) {if(e.keyCode == 13){ajaxSearch($('#search_text').val());}});
	$('#show_rests').click(function() { currentSite = 'r'; resfreshCurrentView(); });

	$('#search_text').focus();
});

//-->
</script>



<script>
 function initMap() {
   var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 14,
      center: {lat: 12.845379, lng: 77.681105},
      mapTypeId: 'terrain'
    });




    // Define the LatLng coordinates for the polygon's path.
    var triangleCoords = [
		{lat:12.836257, lng:77.679388},
		{lat:12.836759, lng:77.680375},
		{lat:12.836843, lng:77.681534},
		{lat:12.836718, lng:77.682650},
		{lat:12.837554, lng:77.683465},
		{lat:12.838308, lng:77.684066},
		{lat:12.838977, lng:77.684495},
		{lat:12.842701, lng:77.687242},
		{lat:12.847304, lng:77.690632},
		{lat:12.851864, lng:77.682349},
		{lat:12.851237, lng:77.681963},
		{lat:12.851069, lng:77.681233},
		{lat:12.851655, lng:77.680718},
		{lat:12.852408, lng:77.680161},
		{lat:12.853203, lng:77.678401},
		{lat:12.851613, lng:77.674968},
		{lat:12.850734, lng:77.674882},
		{lat:12.850316, lng:77.674281},
		{lat:12.849563, lng:77.673337},
		{lat:12.847429, lng:77.671105},
		{lat:12.841697, lng:77.675483},
		{lat:12.838475, lng:77.678144},
		{lat:12.836090, lng:77.679345}
    ];




    // Construct the polygon.
    var bermudaTriangle = new google.maps.Polygon({
      paths: triangleCoords,
      strokeColor: '#FF0000',
      strokeOpacity: 0.8,
      strokeWeight: 2,
      fillColor: '#FF0000',
      fillOpacity: 0.35,
     // userMarker:true
    });
    bermudaTriangle.setMap(map);
  }




    var im = 'http://www.robotwoods.com/dev/misc/bluecircle.png'; // use it to change icon
    function locate(){
        navigator.geolocation.getCurrentPosition(initialize,fail);
    }

    function initialize(position) {
        var myLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
        var mapOptions = {
          zoom: 16,
          center: myLatLng,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        var map = new google.maps.Map(document.getElementById('map'),mapOptions);				  
        var userMarker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            icon: im
        });
      }
	 

     function fail(){
         alert('navigator.geolocation failed, may not be supported');
     }
    </script>





<div class="main_center">
<center>

<input type="text" id="search_text" name="searching" />	<input type="button" value="Search" id="search_button"/> <input type="button" value="Show restaurants" id="show_rests"/>  
 <br/><br/>
<div id="ajax_content"></div>
</center>
<div style="position:absolute;top:0px;left:0px;display:none;" id="dish_info"><img src='<c:url value="/images/ajax-loader.gif"/>'/></div>
</div>

<div id="map" ></div>
<div id="map_location" onload="locate()"></div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
