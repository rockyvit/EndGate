<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
<a href="<c:url value="/admin/crudRestaurant.do"/>">Add restaurant</a> | 
<a href="<c:url value="/admin/crudDishDef.do?action=add"/>">Add Dish Definition</a>
</sec:authorize>
<%
	int elems = 0;
%>
<table class="menu_restaurant">
<c:forEach var="restaurant" items="${restaurantsList}">
<%
if ((elems % 2)==0)
 {
	 %>
	 <tr>
	 <%
 } 
	 %>

 <td onclick="loadGroupsFromRestaurant(${restaurant.restId});"><div class="elem"><div class="elem_content">${restaurant.name}
 <sec:authorize ifAnyGranted="ROLE_ADMIN">
 		<br/>
		<a href="<c:url value="/admin/crudGroup.do?restaurantName=${restaurant.name}&action=add"/>">Add group</a>|
		<a href="<c:url value="/admin/crudRestaurant.do?restaurantName=${restaurant.name}&action=edit"/>">Edit</a>|
		<a href="<c:url value="/admin/crudRestaurant.do?restaurantName=${restaurant.name}&action=delete"/>">Delete</a>
 </sec:authorize>
 
 </div></div></td>
 <% 
 if ((elems % 2)==1)
 {
	 %>
	 </tr>
	 <%
 } 
	 %>

<% elems = elems +1; %>
</c:forEach>
<% if ((elems != 0) && ((elems % 2) != 0))
	{
		%>
		<td></td></tr>
		<%	
	}
%>
</table>


