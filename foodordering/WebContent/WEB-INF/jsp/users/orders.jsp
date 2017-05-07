<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<script type="text/javascript">
var t_cur;
//, t_old;
var refreshing_cur = 1;
//var refreshing_old = 1;
function loadCurrentOrders()
{	
	$('#current_loading_img').show();
	$.post('/foodordering/ajaxGetUserCurrentOrders.do', function(data) {
		$('#user_current_orders').html(data);
		$('#current_loading_img').hide();
		if (refreshing_cur == 1)
			t_cur=setTimeout("loadCurrentOrders()",30000);
	});
}
function loadOldOrders()
{	
	$('#old_loading_img').show();
	$.post('/foodordering/ajaxGetUserOldOrders.do', function(data) {
		$('#user_old_orders').html(data);
		$('#old_loading_img').hide();
		//if (refreshing_old == 1)
		//	t_old=setTimeout("loadOldOrders()",120000);
	});
}

function stopRefreshing()
{
	clearTimeout(t_cur);
	//clearTimeout(t_old);
	refreshing_cur = 0;
	//refreshing_old = 0;
}

function startRefreshing()
{
	if (!refreshing_cur)
	{
		refreshing_cur = 1;
		loadCurrentOrders();
	}
	//if (!refreshing_old)
	//{
	//	refreshing_old = 1;
	//	loadOldOrders();
	//}
}

function restartRefreshing()
{
	stopRefreshing();
	startRefreshing();
}

function resfreshCurrentView()
{
	$('#current_loading_img').ajaxError(function() {
		$(this).hide();
	}).show();
//	$('#old_loading_img').ajaxError(function() {
//		$(this).hide();
//	}).show();

	restartRefreshing();
	
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
		$('#user_current_orders').html("<font color='red'>Cannot connect! Retrying...</font>");
		$('#user_old_orders').html("<font color='red'>Cannot connect! Please retry later...</font>");
		$('#current_loading_img').hide();
		$('#old_loading_img').hide();
		if (refreshing_cur == 1)
			t_cur=setTimeout("loadCurrentOrders()",30000);
	//	restartRefreshing();
	});
	
	resfreshCurrentView();
	
});
</script>

<h2 class="main_title">Orders</h2>

<div class="main_center">
<center>

<sec:authorize ifAllGranted="ROLE_ADMIN">
	<c:redirect url="/admin/main.do"/>
</sec:authorize>

<div class="main_center">
	<div class="main_content">
		<h3>New orders:</h3>
		<center>
			 <div class="center_image">
			 	<img src='<c:url value="/images/ajax-loader-3.gif"/>' id="current_loading_img" />
			 </div>
		</center>
		<div id="user_current_orders"></div>
		
		<h3>Old orders:</h3>
		<center>
			 <input type="button" value="Load orders history" onclick="loadOldOrders();" />
			 <div class="center_image">
			 	<img src='<c:url value="/images/ajax-loader-3.gif"/>' id="old_loading_img" style="display:none"/>
			 </div>
		</center>
		<div id="user_old_orders"></div>

	</div>
	<div style="position:absolute;top:0px;left:0px;display:none;" id="dish_info"><img src='<c:url value="/images/ajax-loader.gif"/>'/></div>
</div>

</center>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
