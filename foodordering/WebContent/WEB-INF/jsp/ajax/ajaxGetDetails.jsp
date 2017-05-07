<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<style type="text/css">
.det_main_table { font-size: 12px; }
.det_rest_name { font-size:14px;font-weight:bold;text-align:center; }
.det_group_name { font-size:11px;border-bottom:1px solid #472900;text-align:center; }
</style>

<table class="det_main_table">
<tr><td class="det_rest_name">${dish.group.restaurant.name}</td></tr>
<tr><td class="det_group_name">${dish.group.groupName}</td></tr>
<c:choose>
<c:when test="${emp == 0}">
	<tr><td style="text-align:left;">${dish.details.description}</td></tr>
	<tr><td style="text-align:left;">${dish.specyficInfo}</td></tr>
</c:when>
<c:otherwise>
	<tr><td style="text-align:left;">No additional info.</td></tr>
</c:otherwise>
</c:choose>
</table>
