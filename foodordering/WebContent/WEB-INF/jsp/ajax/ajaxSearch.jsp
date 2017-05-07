<%@ include file="/WEB-INF/jsp/includes.jsp" %>


<script type="text/javascript">
$(document).ready(function(){
	$('.elem').mouseover(function() {
		$(this).css('background-image','url("/foodordering/images/group_back_s.jpg")');
	}).mouseout(function() {
		$(this).css('background-image','url("/foodordering/images/group_back.jpg")');
	});

	$('.hback').mouseover(function() {
		$('.url_l').css('background-image','url("/foodordering/images/url_l_b.jpg")');
	}).mouseout(function() {
		$('.url_l').css('background-image','url("/foodordering/images/url_l.jpg")');
	});
	
	$('.menu_dish tr').mouseover(function() {
		$(this).css('background-image','url("/foodordering/images/dish_back_s.jpg")');
	}).mouseout(function() {
		$(this).css('background-image','url("/foodordering/images/dish_back.jpg")');
	});
	
	$('.menu_dish tr td.name').mouseover(function(e) {
			$('#dish_info').css('left',e.pageX - $('#food').offset().left + 20).css('top',e.pageY - $('#food').offset().top + 30).show();
	}).mousemove(function(e) {
			$('#dish_info').css('left',e.pageX - $('#food').offset().left + 20).css('top',e.pageY - $('#food').offset().top + 30);
	}).mouseout(function() {
		var di = $('#dish_info');
		di.hide();
		di.html('<img src="/foodordering/images/ajax-loader.gif">');
		di.css('width','auto');
	});
});

</script>
<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
	<script type="text/javascript">
	$(document).ready(function() {
		$('.menu_dish').css('width','524px');
	});
	</script>
</sec:authorize>



<c:choose>
<c:when test="${found.groupsSize == 0 && found.dishesSize == 0}">
	No results found.
</c:when>
<c:otherwise>
	<c:if test="${found.groupsSize != 0}">

	<h3>Groups found:</h3>	
	<table class="menu_group">
		<c:forEach var="group" items="${found.groups}">
		<tr>
		 <td onclick="loadDishesFromGroup(${group.groupId});"><div class="elem"><div class="elem_content">${group.groupName} (${group.restaurant.name})</div></div></td>
		 <sec:authorize ifAnyGranted="ROLE_ADMIN">
		 <td width="300">
				<a href="<c:url value="/admin/crudDish.do?groupId=${group.groupId}&action=add"/>">Add dish</a>|
				<a href="<c:url value="/admin/crudGroup.do?groupId=${group.groupId}&action=edit"/>">Edit</a>|
				<a href="<c:url value="/admin/crudGroup.do?groupId=${group.groupId}&action=delete"/>">Delete</a>
		 </td>
		 </sec:authorize>
		 </tr>
		 </c:forEach>
	</table>
	<br/>
	</c:if>
	
	<c:if test="${found.dishesSize != 0}">
		<h3>Dishes found:</h3>
		<table class="menu_dish">
		<c:forEach var="dish" items="${found.dishes}">
		<tr>
			<td onmouseover="loadDetailsFromDish(${dish.dishId});" onmouseout="hideDishInfo();" class="name">${dish.details.name}</td><td class="price">${dish.price}</td>
			
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
				<td class="extra"></td>
				<td class="action">
					<a href="<c:url value="/admin/crudDish.do?dishId=${dish.dishId}&action=edit"/>">Edit</a>|
					<a href="<c:url value="/admin/crudDish.do?dishId=${dish.dishId}&action=delete"/>">Delete</a>
				</td>
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="ROLE_USER" ifNotGranted="ROLE_ADMIN">
				<td class="extra"></td>
				<td class="action">
					<a href="<c:url value="/users/orders.do?dishId=${dish.dishId}&action=order"/>">Order</a>
					<%-- <form action="<c:url value="/users/orders.do?dishId=${dish.dishId}&action=order"/>" method="post"><input type="submit" value="Order" class="order_button"/></form> --%>
				</td>
			</sec:authorize>
			
			
		</tr>
		</c:forEach>
		</table>
			
	</c:if>
</c:otherwise>
</c:choose>