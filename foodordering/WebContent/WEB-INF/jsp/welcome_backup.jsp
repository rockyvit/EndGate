<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<script type="text/javascript">
var t;
var refreshing = 1;
function loadCurrentOrders()
{	
	$('#orders_loading_img').show();
	$.get('/foodordering/showCurrentOrders.do', function(data) {
		$('#current_orders').html(data);
		$('#orders_loading_img').hide();
		if (refreshing == 1)
			t=setTimeout("loadCurrentOrders()",30000);
	});
	
}

function stopRefreshing()
{
	clearTimeout(t);
	refreshing = 0;
}

function startRefreshing()
{
	if (!refreshing)
	{
		refreshing = 1;
		loadCurrentOrders();
	}
}

function restartRefreshing()
{
	stopRefreshing();
	startRefreshing();
}

function resfreshCurrentView()
{
	$('#main_loading_img').ajaxError(function() {
		$(this).hide();
	}).show();

	restartRefreshing();
	
	$.get('/foodordering/ajaxGetMainMessage.do', function(data) {
		$('#main_loading_img').hide();
		$('#main_message').html(data);
		connectLogin();
	});
}
function fixTooltipWidth()
{
	var di = $('#dish_info');
	if (di.width() > 300) {
		di.css('width','300px');
	}
}

var detailsIndex = [];
var details = [];

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
	//alert('p1');
	if (found != -1) { /* Use data from cache */
		$('#dish_info').html(details[found]);
		fixTooltipWidth();
	} else { /* Send ajax request to server */
		$.post('/foodordering/ajaxGetDetails.do', {dishId: dishId }, function(data) {
			addDetailsToCache(dishId, data);
			$('#dish_info').html(data);
			fixTooltipWidth();
		});
	}
}
function hideDishInfo()
{
	$('#dish_info').hide();
}
$(document).ready(function() {
	$('.main_center').ajaxError(function(e, xhr, settings, exception) {
	//   if (settings.url == '/foodordering/showCurrentOrders.do') {
	// 	
	//   }
		$('#current_orders').html("<font color='red'>Cannot connect! Retrying...</font>");
		$('#orders_loading_img').hide();
		if (refreshing == 1)
			t=setTimeout("loadCurrentOrders()",30000);
	});
	
	loadCurrentOrders();
	resfreshCurrentView();
	
});
</script>

<c:remove scope="session" var="SPRING_SECURITY_SAVED_REQUEST_KEY"/>


<h2 class="main_title">Main Page</h2>



<div class="main_center">
	<div class="main_content">
		<center>
			 <div class="center_image">
			 	<img src='<c:url value="/images/ajax-loader-3.gif"/>' id="main_loading_img" />
			 </div>
		</center>
		<div id="main_message"></div>
		Current orders: 
		<center>
			 <div class="center_image">
			 	<img src='<c:url value="/images/ajax-loader-3.gif"/>' id="orders_loading_img" />
			 </div>
		</center>
		<div id="current_orders"></div>
	</div>
	<div style="position:absolute;top:0px;left:0px;display:none;" id="dish_info"><img src='<c:url value="/images/ajax-loader.gif"/>'/></div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
