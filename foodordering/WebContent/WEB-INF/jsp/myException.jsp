<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<%
Exception ex = (Exception) request.getAttribute("exception");
%>


<h2 class="main_title">Oops</h2>
  <hr id="line"/>

<div class="main_center">
<h3><%= ex.getMessage() %></h3>

Go back to <a href="<c:url value="/welcome.do"/>">home</a> page.
</div>

<%--
ex.printStackTrace(new java.io.PrintWriter(out));
--%>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>
