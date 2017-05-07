<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
//$(document).ready(function() {
	$('.dish_details_name').mouseover(function(e) {
		//alert('A1');
			$('#dish_info').css('left',e.pageX - $('#food').offset().left + 20).css('top',e.pageY - $('#food').offset().top + 30).show();
	}).mousemove(function(e) {
			$('#dish_info').css('left',e.pageX - $('#food').offset().left + 20).css('top',e.pageY - $('#food').offset().top + 30);
	}).mouseout(function() {
	//	alert('A2');
		var di = $('#dish_info');
		di.hide();
		di.html('<img src="/foodordering/images/ajax-loader.gif">');
		di.css('width','auto');
	});
//}
</script>


<c:forEach var="order" items="${orders}">

<div class="div_order"> 
	<span class="orders_time_remaining">
		<span class="special">
		<c:choose>
		<c:when test="${errorVal==order.status}">
			<fmt:formatDate value="${order.orderingDate}" pattern="yyyy-MM-dd HH:mm"/>
		</c:when>
		<c:otherwise>
			${order.stringTimeRemaining}
		</c:otherwise>
		</c:choose>
		</span>
	</span>
	<span class="orders_restaurant_name">
		<span class="special">${order.restaurant.name}</span>
	</span>
	<div class="div_elements_main">
		<div class="tl3"><div class="tr3"><div class="dl3"><div class="dr3">
		<div class="inner_padd">
		<div class="dish_details"> <!-- default cursor --> 

			<table>
			<c:forEach var="oElem" items="${order.orderElements}">
				<tr>
					<td>
					<span onmouseover="loadDetailsFromDish(${oElem.dish.dishId});" onmouseout="hideDishInfo();" class="dish_details_name">
							Name: <span class="special">${oElem.dish.details.name}</span>
					</span>
					</td>
					<td>Price: <span class="special">${oElem.dish.price}</span> PLN</td>
					<td><a href="<c:url value="/users/orders.do?orderElemId=${oElem.orderElementId}&action=delete"/>" class="delete_elem">delete</a></td>
				</tr>
			</c:forEach>
				<tr>
					<td></td>
					<td style="border-top:1px solid black;">Total: <span class="special">${order.cashToPay}</span> PLN</td>
					<td></td>
				</tr>
			</table>
		</div></div>
		</div></div></div></div>
	</div>
	<span class="orders_dates">
		ordering date: <fmt:formatDate value="${order.orderingDate}" pattern="yyyy-MM-dd HH:mm"/>
		creation date: <fmt:formatDate value="${order.creationDate}" pattern="yyyy-MM-dd HH:mm"/>
		<c:if test="${errorVal==order.status}">
		<br/>
		<span class="order_errors">Cannot send email! This order cannot be realized.</span>
		</c:if>
	</span>
	<span class="orders_delete_order">
	<a href="<c:url value="/users/orders.do?orderId=${order.orderId}&action=delete"/>" class="delete_order">Delete order</a>
	</span>
</div>

</c:forEach>
<%--
<h3 id="old_orders" class="pointer">Old orders: (click to view)</h3>
<div id="old_orders_content">
<c:forEach var="oldOrder" items="${oldOrdersList}">
<div class="div_old_orders">
	<div class="tl2"><div class="tr2"><div class="dl2"><div class="dr2">
	
	<div class="inner">
	
		Ordering date: <span class="special"><fmt:formatDate value="${oldOrder.orderingDate}" pattern="yyyy-MM-dd HH:mm"/></span>
		Restaurant: <span class="special">${oldOrder.restaurantName}</span>
		Total: <span class="special">${oldOrder.cashTotal}</span> PLN
		Creation date: <fmt:formatDate value="${oldOrder.creationDate}" pattern="yyyy-MM-dd HH:mm"/><br/>
		<!--                T   R   B   L --!>
		<div style="padding:5px 0px 5px 50px;">
		<c:forEach var="ooElem" items="${oldOrder.horderElements}">
			Dish: <span class="special">${ooElem.dishName}</span>
			Paid: <span class="special">${ooElem.price}</span> PLN <br/>
		</c:forEach>
		
		</div>
	</div>
	</div></div></div></div>
</div>
</c:forEach>
</div>
 --%>




