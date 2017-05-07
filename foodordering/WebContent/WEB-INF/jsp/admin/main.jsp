<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">Administration panel.</h2>
  

<div class="main_center">

You are: ${mname}
<br/><br/>
<a href="<c:url value="/admin/userManager.do"/>">Users Manager</a>
<br/>
<a href="<c:url value="/restaurants.do"/>">Food Manager</a>
<br/>
<a href="<c:url value="/admin/fetcher.do"/>">Fetch Manager</a>

</div>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>
