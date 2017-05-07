<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	<span class="text_welcome">Welcome 
	<sec:authorize ifAnyGranted="ROLE_USER">
		<sec:authentication property="principal.username" />
	</sec:authorize></span><br/><br/>
	<span class="text_normal">
		<sec:authorize ifNotGranted="ROLE_USER">
			Please 
			<a href="<c:url value="/secure/register.do"/>">Register</a> or
			<a href="#" class="loginLink">Login</a> to have access to ordering features.<br />
			You can still browse our restaurants: 
			<a href="<c:url value="/restaurants.do"/>">Browse</a><br/><br />
		</sec:authorize>
	</span>