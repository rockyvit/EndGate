<%@ include file="/WEB-INF/jsp/includes.jsp" %>

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
	<div class="div_current_orders">
		<div class="tl3"><div class="tr3"><div class="dl3"><div class="dr3">
		<div class="inner_no_padd">
			<span class="special">${rep.timeRemaining}</span> remaining (ordering date: <fmt:formatDate value="${rep.ts}" pattern="yyyy-MM-dd HH:mm"/>)<br/>
			Restaurant name: <span class="special">${rep.restName}</span><br/> 
			Orders total price: <span class="special"><fmt:formatNumber type='number' maxFractionDigits='2' minFractionDigits='2' value='${rep.totalCash}'/></span> PLN<br/>
			Total users in order: <span class="special">${rep.usersCount}</span><br/>
			<sec:authorize ifAnyGranted="ROLE_USER" ifNotGranted="ROLE_ADMIN">
				<table>
				<c:forEach var="det" items="${rep.details}">
					<tr><td colspan="3"><span class="special">${det.username}:</span></td></tr>
					<c:forEach var="elems" items="${det.orderElements}">
						<tr><td>
						<span onmouseover="loadDetailsFromDish(${elems.dish.dishId});" onmouseout="hideDishInfo();" class="dish_details">
							name: <span class="special">${elems.dish.details.name}</span>
						</span>
							</td><td>
							price: <span class="special">${elems.dish.price}</span> PLN
							</td><td>
								<a href="<c:url value="/users/orders.do?dishId=${elems.dish.dishId}&action=order"/>" class="a_current_orders">Order</a>
							<%--<c:if test="${det.username == username}">
								<a href="<c:url value="/users/orders.do?orderElemId=${oElem.orderElementId}&action=delete"/>" class="delete_elem">Delete</a>								
							</c:if>--%>
						</td></tr>
						</c:forEach>
				</c:forEach>
				</table>
			</sec:authorize>
		</div>
		</div></div></div></div>
	</div>
	</c:forEach>
</c:otherwise>
</c:choose>
</center>
<%-- <fmt:formatDate value="${rep.key}" pattern="yyyy-MM-dd HH:mm"/> --%>


<%-- 
ANONYMOUS: Remaining time (Time), Restaurant, Total cash
USER:      Remaining time (Time), Restaurant, Total cash, Users cnt, Order per user (username, order details[if user agreed])
ADMIN:     Remaining time (Time), Restaurant, Total cash, Users cnt, Order per user (username, order details[always])
--%>