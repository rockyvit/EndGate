<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<%-- INTERT CODE HERE --%>

<%-- INTERT CODE HERE --%>
<%-- 
<h2>This is the map:</h2>

<table>
  <tr>
  <thead>
    <th>1st</th>
    <th>2nd</th>
  </thead>
  </tr>
  
<c:forEach var="testclass" items="${testClassList}">
<tr>
      <td>ID: </td><td>${testclass.one}</td><td>${testclass.two}</td>
</tr>
</c:forEach>

</table>

<form:form modelAttribute="restaurant">
  <table>
    <tr>
      <th>
        Name: <form:errors path="name" cssClass="errors"/>
        <br/>
        <form:input path="name" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
    	<th>
        Type: <form:errors path="type" cssClass="errors"/>
        <br/>
        <form:select path="type" items="${typeList}"/>
      </th>
    </tr>
    <tr>
      <td>
        <p class="submit"><input type="submit" value="Add Restaurant"/></p>
      </td>
    </tr>
  </table>
</form:form>
--%>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
