<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<a href="<c:url value="/restaurants.do"/>">Restaurants</a>|
			
<sec:authorize ifAnyGranted="ROLE_USER" ifNotGranted="ROLE_ADMIN">
	<a href="<c:url value="/users/orders.do"/>" title="My orders">My orders</a>|
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ADMIN">
	<a href="<c:url value="/admin/main.do"/>">Admin page</a>|
</sec:authorize>

<sec:authorize ifAnyGranted="ROLE_USER">
	<a href="<c:url value="/j_spring_security_logout"/>" onclick="loadFooter();">Logout (<sec:authentication property="principal.username" />)</a>|
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_USER">
	<a href="#" class="loginLink" id="loginFooterId">Login</a>|
	<a href="<c:url value="/secure/register.do"/>">Register</a>|
</sec:authorize>
	<a href="<c:url value="/welcome.do"/>">Main page</a>

