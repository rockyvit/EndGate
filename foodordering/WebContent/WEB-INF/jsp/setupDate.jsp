<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

 <h2 class="main_title">Setup date</h2>
  


<div class="main_center">
 <h1>NOT USED !!!!!!!!!</h1>
<h2>Enter time:</h2>

<form:form modelAttribute="dateTime">
  <table>
    <tr>
    	<td>
		    Time: (HH:mm)
    	</td>
    	<td>
    	 Hours: <form:errors path="hour" cssClass="errors"/>
        <br/>
        <form:select path="hour" items="${hlist}"/>
        
    	</td>
    	<td>
    	Minutes: <form:errors path="minute" cssClass="errors"/>
        <br/>
        <form:select path="minute" items="${mlist}"/>
    	</td>
    </tr>
    <tr>
      <td>
        <p class="submit"><input type="submit" value="Setup call time"/></p>
      </td><td></td><td></td>
    </tr>
  </table>
</form:form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
