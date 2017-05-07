<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2 class="main_title">Dish definitions manager</h2>
  

<div class="main_center">

<c:choose>
	<c:when test="${dishDef.dishDefId eq 0}">
		<h2>Add dish definition:</h2>
	</c:when>
	<c:otherwise>
		<h2>Edit ${dishDef.name} dish definition:</h2>
	</c:otherwise>
</c:choose>


<center>
<form:form modelAttribute="dishDef">
  <table>
    <tr>
      <td class="right">
        Name: 
        </td><td align="left">
        <form:input path="name" size="30" maxlength="50"/>
        <br/><form:errors path="name" cssClass="errors"/>
      </td>
    </tr>
    <tr>
      <td class="right">
        Description: 
        </td><td align="left">
        <form:textarea path="description" cols="30" rows="15"/>
        <br/><form:errors path="description" cssClass="errors"/>
      </td>
    </tr>
    
    <tr><td></td>
      <td>
		<c:choose>
			<c:when test="${dishDef.dishDefId eq 0}">
				<p class="submit"><input type="submit" value="Add Dish Definition"/></p>
			</c:when>
			<c:otherwise>
				<p class="submit"><input type="submit" value="Update Dish Definition"/></p>
			</c:otherwise>
		</c:choose>
      </td>
    </tr>
  </table>
</form:form>

</center>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
