<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2 class="main_title">Registration</h2>
  
  <div class="main_center">
<center>
<form:form modelAttribute="user">
  <table>
    <tr><td class="right">
        Your Name: 
        </td><td align="left">
        <form:input path="username" size="30" maxlength="50"/>
        <br/><form:errors path="username" cssClass="errors"/>
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
        Mobile No: 
        </td><td align="left">
        <form:input path="mobileno" size="10" maxlength="10"/>
        <br/><form:errors path="mobileno" cssClass="errors"/>
    </td></tr>
    
    <tr><td class="right">
        E-mail: 
        </td><td align="left">
        <form:input path="email" size="30" maxlength="50"/>
        <br/><form:errors path="email" cssClass="errors"/>
    </td></tr>
    
    
    <tr>
    <td></td>
      <td>
        <p class="submit"><input type="submit" value="Register"/></p>
      </td>
    </tr>
  </table>
</form:form>
</center>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
