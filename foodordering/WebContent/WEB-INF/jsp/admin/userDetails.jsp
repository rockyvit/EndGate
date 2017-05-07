<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">Users manager.</h2>

<div class="main_center">
<%-- INTERT CODE HERE --%>
<h3>User ${user.username} details</h3>
<%-- INTERT CODE HERE --%>


<center>
<form:form modelAttribute="user">
  <table class="centering">
    <tr><td class="right">
        Enabled: 
        </td><td align="left">
        <form:select path="enabled">
            <form:option value="true"/>
            <form:option value="false"/>
        </form:select>
        <br/><form:errors path="enabled" cssClass="errors"/>
    </td></tr>
    
    <tr><td class="right">
        Password: 
        </td><td align="left">
        <form:password path="password" size="30" maxlength="50"/>
        <br/><form:errors path="password" cssClass="errors"/>
    </td></tr>
    
    <tr><td class="right">
        Repeat password:
        </td><td align="left">
        <form:password path="password2" size="30" maxlength="50"/>
        <br/><form:errors path="password2" cssClass="errors"/>
    </td></tr>
    
    <tr><td class="right">
        First name: 
        </td><td align="left">
        <form:input path="firstName" size="30" maxlength="50"/>
        <br/><form:errors path="firstName" cssClass="errors"/>
    </td></tr>
    
    <tr><td class="right">
        Last name: 
        </td><td align="left">
        <form:input path="lastName" size="30" maxlength="50"/>
        <br/><form:errors path="lastName" cssClass="errors"/>
    </td></tr>
    
    <tr><td class="right">
        E-mail: 
        </td><td align="left">
        <form:input path="email" size="30" maxlength="50"/>
        <br/><form:errors path="email" cssClass="errors"/>
    </td></tr>
    
    <tr><td class="right">
        Room number: 
        </td><td align="left">
        <form:input path="roomNumber" size="10" maxlength="6" id="roomNumber" onkeyup="validateInt('roomNumber');"/>
        <br/><form:errors path="roomNumber" cssClass="errors"/>
    </td></tr>
    
    <tr>
    <td></td>
      <td>
        <p class="submit"><input type="submit" value="Update"/></p>
      </td>
    </tr>
  </table>
</form:form>

</center>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
