<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2 class="main_title">Select ordering time</h2>

<div class="main_center">
<center>
<br/><br/>

<table class="time_selection">
<tr height=240>
<c:if test="${datesSize != 0}">
	<td class="select_left">
		<div id="attach_date" style="text-align:center;">
		<br/>
		<select id="attach_list" class="select_mod">
		<c:forEach var="att_date" items="${dates}">
	       	<option value="<fmt:formatDate value="${att_date}" pattern="yyyy-MM-dd HH:mm"/>"><fmt:formatDate value="${att_date}" pattern="yyyy-MM-dd HH:mm"/></option>
	    </c:forEach>		
		</select><br/><br/>
		<p class="date_select_substring">Attach to<br/>existing order</p>
		</div>
	</td>

	<td class="middle" width="40"></td>
	
</c:if>
	<td  class="select_right">
		<div id="create_date" style="text-align:center;">
		<br/>
		<input id="change_today" class="input_mod" type="text" value="Today" readonly="readonly" size="10"><input id="datepicker" class="input_mod" type="text" size="10"> <input id="hrs" class="input_mod" type="text" value="12" size="2" readonly="readonly" style="position:relative;">:<input id="mins" class="input_mod" type="text" value="30" size="2" readonly="readonly">
		<br/><br/>
		<p class="date_select_substring">Create new<br/>ordering time</p>
		</div>
	</td>
</tr>
<tr height=128>
<c:if test="${datesSize != 0}">
	<td colspan="3" class="accept_button_cell">
</c:if>
<c:if test="${datesSize == 0}">
	<td class="accept_button_cell">
</c:if>
<span id="accept_button">Make an order</span>
<span id="please_wait">Please wait...</span>
</td></tr>
</table>

<div id="selection"></div>

<form:form modelAttribute="dateDishId" id="dateDishId">
 	<form:input id="res" path="odate" readonly="true"/>
</form:form>

</center>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
