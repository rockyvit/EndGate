<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">Restaurant manager</h2>
  

<div class="main_center">

<c:choose>
	<c:when test="${restaurant.restId eq 0}">
		<h2>Add restaurant:</h2>
	</c:when>
	<c:otherwise>
		<h2>Edit ${restaurant.name} restaurant:</h2>
	</c:otherwise>
</c:choose>


<center>
<form:form modelAttribute="restaurant">
  <table>
    <tr>
      <td class="right">
        Name: 
        </td><td align="left">
        <form:input path="name" size="30" maxlength="80"/>
        <br/><form:errors path="name" cssClass="errors"/>
      </td>
    </tr>
    <tr>
      <td class="right">
        Description: 
        </td><td align="left">
        <form:textarea path="description" cols="30" rows="10"/>
        <br/><form:errors path="description" cssClass="errors"/>
      </td>
    </tr>
    <tr>
    	<td class="right">
        Type: 
        </td><td align="left">
        <form:select path="restType" items="${typeList}"/>
        <br/><form:errors path="restType" cssClass="errors"/>
      </td>
    </tr>
    <tr>
    <td></td>
      <td>
		<c:choose>
			<c:when test="${restaurant.restId eq 0}">
				<p class="submit"><input type="submit" value="Add Restaurant"/></p>
			</c:when>
			<c:otherwise>
				<p class="submit"><input type="submit" value="Update Restaurant"/></p>
			</c:otherwise>
		</c:choose>
      </td>
    </tr>
  </table>
</form:form>
</center>

<a href="<c:url value="/admin/crudRestaurantType.do"/>">Add restaurant type</a>

</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
