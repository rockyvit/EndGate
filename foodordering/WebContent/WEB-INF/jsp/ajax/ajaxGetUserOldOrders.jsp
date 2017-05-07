<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%--
<script type="text/javascript">
//$(document).ready(function() {
	$('.dish_details').mouseover(function(e) {
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

 <center>
 <c:choose>
 <c:when test="${report_size == 0}">
 	No orders.
 </c:when>
 <c:otherwise>
 	<c:forEach var="rep" items="${report}">
	<div class="div_curent_orders">
		<div class="tl3"><div class="tr3"><div class="dl3"><div class="dr3">
		<div class="inner">
			<span class="special">${rep.timeRemaining}</span> remaining (ordering date: <fmt:formatDate value="${rep.ts}" pattern="yyyy-MM-dd HH:mm"/>)<br/>
			Restaurant name: <span class="special">${rep.restName}</span><br/> 
			Orders total price: <span class="special"><fmt:formatNumber type='number' maxFractionDigits='2' minFractionDigits='2' value='${rep.totalCash}'/></span> PLN<br/>
			Total users in order: <span class="special">${rep.usersCount}</span><br/>
			<sec:authorize ifAnyGranted="ROLE_USER" ifNotGranted="ROLE_ADMIN">
				<c:forEach var="det" items="${rep.details}">
					<span class="special">${det.username}:</span><br/>
					<c:forEach var="elems" items="${det.orderElements}">
						<span onmouseover="loadDetailsFromDish(${elems.dish.dishId});" onmouseout="hideDishInfo();" class="dish_details">
							name: <span class="special">${elems.dish.details.name}</span>
						</span>
							price: <span class="special">${elems.dish.price}</span> PLN
							<%--<c:if test="${det.username != username}">-->
								<a href="<c:url value="/users/orders.do?dishId=${elems.dish.dishId}&action=order"/>" class="a_curent_orders">Order</a>
							<%--</c:if>-->
							<br/>
						</c:forEach>
						<br/>
				</c:forEach>
			</sec:authorize>
		</div>
		</div></div></div></div>
	</div>
	</c:forEach>
</c:otherwise>
</c:choose>
</center>


 --%>
<%-- <fmt:formatDate value="${rep.key}" pattern="yyyy-MM-dd HH:mm"/> --%>


<%-- 
ANONYMOUS: Remaining time (Time), Restaurant, Total cash
USER:      Remaining time (Time), Restaurant, Total cash, Users cnt, Order per user (username, order details[if user agreed])
ADMIN:     Remaining time (Time), Restaurant, Total cash, Users cnt, Order per user (username, order details[always])
--%>



<%--


<c:forEach var="order" items="${orders}">
<div class="div_order"> 
	<div class="div_details">
		<div class="tl1"><div class="tr1"><div class="dl1"><div class="dr1">
		<span class="title">Order details:</span>
		<p>
		Ordering date: <span class="special"><fmt:formatDate value="${order.orderingDate}" pattern="yyyy-MM-dd HH:mm"/></span><br/>
		To pay: <span class="special">${order.cashToPay}</span> PLN<br/>
		Restaurant: <span class="special">${order.restaurant.name}</span><br/>
		Creation date: <fmt:formatDate value="${order.creationDate}" pattern="yyyy-MM-dd HH:mm"/><br/>
		<c:if test="${errorVal==order.status}">
		<br/>
		<div class="order_errors">Cannot send email!<br/>This order cannot be realized.</div>
		</c:if>
	
		
		</p>
		<a href="<c:url value="/users/orders.do?orderId=${order.orderId}&action=delete"/>" class="delete">Delete order</a>
		</div></div></div></div>
	</div>
	
	<div class="div_elements_main">
		<div class="tl1"><div class="tr1"><div class="dl1"><div class="dr1">
		<span class="title2">Order elements</span>
	
		<c:forEach var="oElem" items="${order.orderElements}">
			<div class="div_elements_elem">
			<p>
			Name: <span class="special">${oElem.dish.details.name}</span><br/>
			Price: <span class="special">${oElem.dish.price}</span> PLN<br/>
			Description: <span class="special">${oElem.dish.details.description}</span><br/><br/>
			Additional info:<br/> <span class="special">
				${oElem.dish.specyficInfo}<br/>
				</span>
			My remarks: <br/> <span class="special">
				${oElem.additionalInfo}</span>
			</p>
			<a href="<c:url value="/users/orders.do?orderElemId=${oElem.orderElementId}&action=delete"/>" class="delete_elem">Delete</a>
			</div>
		</c:forEach>
	
	
		</div></div></div></div>
	</div>
</div>
</c:forEach>
--%>

<c:forEach var="oldOrder" items="${oldOrdersList}">


<div class="div_old_order"> 
	<span class="orders_time_remaining">
	<span class="special"><fmt:formatDate value="${oldOrder.orderingDate}" pattern="yyyy-MM-dd HH:mm"/></span></span>
	<span class="orders_restaurant_name">
	<span class="special">${oldOrder.restaurantName}</span></span>
	<div class="div_elements_main">
		<div class="tl3"><div class="tr3"><div class="dl3"><div class="dr3">
		<div class="inner_padd">
		<div class="dish_details"> <!-- default cursor --> 

			<table>
			<c:forEach var="ooElem" items="${oldOrder.horderElements}">
				<tr>
					<td>Name: <span class="special">${ooElem.dishName}</span></td>
					<td>Price: <span class="special">${ooElem.price}</span> PLN</td>
				</tr>
			</c:forEach>
				<tr>
					<td></td>
					<td style="border-top:1px solid black;">Total: <span class="special">${oldOrder.cashTotal}</span> PLN</td>
					<td></td>
				</tr>
			</table>
		</div></div>
		</div></div></div></div>
	</div>
	<span class="orders_dates">
	creation date: <fmt:formatDate value="${oldOrder.creationDate}" pattern="yyyy-MM-dd HH:mm"/></span>
</div>

</c:forEach>




