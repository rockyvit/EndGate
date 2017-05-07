<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">Users manager.</h2>
  

<center>
<table class="pretty">
  <tr>
  <thead>
    <th>Username</th>
    <th>Enabled</th>
    <th>Email</th>
    <th>First name</th>
    <th>Last name</th>
    <th>Room number</th>
    <th></th>
  </thead>
  </tr>
  
<c:forEach var="user" items="${users}">
<tr>
      <td>${user.username}</td>
      <td>${user.enabled}</td>
      <td>${user.email}</td>
      <td>${user.firstName}</td>
      <td>${user.lastName}</td>
      <td>${user.roomNumber}</td>
      <td>
      	<a href="<c:url value="/admin/userManager.do?username=${user.username}"/>">EDIT</a>
      	/
      	<a href="<c:url value="/admin/userManager.do?username=${user.username}&action=remove"/>">REMOVE</a></td>
</tr>
</c:forEach>

</table>
</center>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
