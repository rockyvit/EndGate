<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">User's main page</h2>

<div class="main_center">
<center>

You are: ${mname}


</center>
</div>


<sec:authorize ifAnyGranted="ROLE_ADMIN">
<br/><br/>
	Go to admin page: <a href="<c:url value="/admin/main.do"/>">Admin page</a>
</sec:authorize>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
