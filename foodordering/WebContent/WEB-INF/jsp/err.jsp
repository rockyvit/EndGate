<%@ include file="/WEB-INF/jsp/includes.jsp" %>
  <c:if test="${not empty param.login_error}">
	<font color="blue" size="-1">
	  Your login attempt <br />
	  was not successful, try again.<br/><br/>
	  Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
	</font>
</c:if> 

<c:if test="${empty param.login_error}">
	<font color="green" size="+3">
	<c:redirect url="/restaurants.do"/>
	  <b>Login successful.</b>
	  
	</font>
</c:if>

<div id="login_error" style="display:none;">${param.login_error}</div>
