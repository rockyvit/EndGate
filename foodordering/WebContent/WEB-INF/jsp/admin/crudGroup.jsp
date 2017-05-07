<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">Groups manager</h2>
  

<div class="main_center">

<c:choose>
	<c:when test="${group.groupId eq 0}">
		<h2>Add group to ${group.restaurant.name}:</h2>
	</c:when>
	<c:otherwise>
		<h2>Edit ${group.groupName} group:</h2>
	</c:otherwise>
</c:choose>


<center>
<form:form modelAttribute="group">
  <table>
    <tr>
      <td class="right">
        Name: 
        </td><td align="left">
        <form:input path="groupName" size="30" maxlength="50"/>
        <br/><form:errors path="groupName" cssClass="errors"/>
      </td>
    </tr>
    <tr>
      <td></td><td>
		<c:choose>
			<c:when test="${group.groupId eq 0}">
				<p class="submit"><input type="submit" value="Add Group"/></p>
			</c:when>
			<c:otherwise>
				<p class="submit"><input type="submit" value="Update Group"/></p>
			</c:otherwise>
		</c:choose>
      </td>
    </tr>
  </table>
</form:form>
</center>

</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
