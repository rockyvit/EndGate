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
});
</script>

<div class="url_m"><div class="url_r">
	<div class="hback" onclick="loadPrevious('r');"></div>
	<div class="url_l">History: /
<span onclick="loadRestaurants();" class="pointer">Restaurants</span> /
<span class="pointer">${restaurant.name}</span>
	</div></div></div>
<br/>

<table class="menu_group">
<c:forEach var="group" items="${groupsList}">
<tr>
 <td onclick="loadDishesFromGroup(${group.groupId});"><div class="elem"><div class="elem_content">${group.groupName}</div></div></td>
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
