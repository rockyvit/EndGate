<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2 class="main_title">Restaurant Types</h2>
  

<div class="main_center">
${errorCode}

<table>
  <thead><tr><td>ID</td><td>Type</td><td></td></tr></thead>
  <tbody>	
  <c:set var="count" scope="page" value="1"/>
	<c:forEach var="restType" items="${restaurantsTypesList}">
	<tr>
      <td>
	      <c:choose>
		      <c:when test="${addedType eq restType.typeName}">
		      	<b>${count}</b>
		      </c:when>
		      <c:otherwise>
		      	${count}
		      </c:otherwise>
	      </c:choose>
      </td><td>
      	<c:choose>
	      <c:when test="${addedType eq restType.typeName}">
	      <b>${restType.typeName}</b>
	      </c:when>
	      <c:otherwise>
	      ${restType.typeName}
	      </c:otherwise>
	      </c:choose>
      </td>
      <td>
      	<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<a href="<c:url value="/admin/crudRestaurantType.do?restaurantTypeName=${restType.typeName}&action=delete"/>">Delete</a>
		</sec:authorize>
      </td>
     </tr>
     <c:set var="count" scope="page" value="${count + 1}"/>
	</c:forEach>
 
</tbody>

</table>
<sec:authorize ifAnyGranted="ROLE_ADMIN">
<a href="<c:url value="/admin/crudRestaurantType.do"/>">Add restaurant type</a><br/>
<a href="<c:url value="/admin/crudRestaurant.do"/>">Add restaurant</a>
</sec:authorize>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
