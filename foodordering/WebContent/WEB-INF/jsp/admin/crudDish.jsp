<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">Dishes manager</h2>
  

<div class="main_center">

<c:choose>
	<c:when test="${dish.dishId eq 0}">
		<h2>Add Dish to ${dish.group.groupName} in ${dish.group.restaurant.name}:</h2>
	</c:when>
	<c:otherwise>
		<h2>Edit Dish in ${dish.group.groupName} in ${dish.group.restaurant.name}:</h2>
	</c:otherwise>
</c:choose>


<center>
<form:form modelAttribute="dish">
  <table>
    <tr>
       <td class="right">
        Name: 
        </td><td align="left">
        <form:select path="details" items="${dishDefList}"/>
      </td>
    </tr>
    <tr>
      <td class="right">
        Price: 
        </td><td align="left">
        <form:input path="price" size="30" maxlength="10"/>
        <br/><form:errors path="price" cssClass="errors"/>
      </td>
    </tr>
    <tr>
      <td class="right">
        Specyfic info: 
        </td><td align="left">
        <form:textarea path="specyficInfo" cols="30" rows="10"/>
        <br/><form:errors path="specyficInfo" cssClass="errors"/>
      </td>
    </tr>
    
    <tr><td></td>
      <td>
		<c:choose>
			<c:when test="${dish.dishId eq 0}">
				<p class="submit"><input type="submit" value="Add Dish"/></p>
			</c:when>
			<c:otherwise>
				<p class="submit"><input type="submit" value="Update Dish"/></p>
			</c:otherwise>
		</c:choose>
      </td>
    </tr>
  </table>
</form:form>
</center>

<a href="<c:url value="/admin/crudDishDef.do?action=add"/>">Add dish def</a>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
