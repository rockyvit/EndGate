<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">Import manager</h2>
  

<div class="main_center">

<h3>Import Menu</h3>

<center>
<form:form modelAttribute="restMap">
  <table>
    <tr>
      <td class="right">
        Name: 
        </td><td align="left">
        <form:input path="restaurantName" size="30" maxlength="50"/>
        <br/><form:errors path="restaurantName" cssClass="errors"/>
      </td>
    </tr>
    
     <tr>
      <td class="right">
        Restaurant:
        </td><td align="left">
        <form:select path="selected" items="${restMap.opt}"/>
        <br/><form:errors path="selected" cssClass="errors"/>
      </td>
    </tr>
    <tr>
      <td></td><td>
		<p class="submit"><input type="submit" value="Import"/></p>
      </td>
    </tr>
  </table>
</form:form>
</center>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
