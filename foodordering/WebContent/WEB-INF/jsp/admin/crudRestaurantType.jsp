<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">Restaurant type manager.</h2>
  

<div class="main_center">

<h3>Add restaurant type:</h3>


<center>
<form:form modelAttribute="restaurantType">
  <table>
    <tr>
      <td class="right">
        Type Name: 
        </td><td align="left">
        <form:input path="typeName" size="30" maxlength="80"/>
        <br/><form:errors path="typeName" cssClass="errors"/>
      </td>
    </tr>
    <tr><td></td>
      <td>
        <p class="submit"><input type="submit" value="Add Type"/></p>
      </td>
    </tr>
  </table>
</form:form>
</center>

</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
