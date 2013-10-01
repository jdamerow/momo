<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Timesheets of your Teams</h2>
<ul class="style2">
	<c:forEach items="${teams}" var="team">
		<li><a href="${pageContext.servletContext.contextPath}/auth/timesheets/team/${team.id}">${team.name}</a>
	</c:forEach>
</ul>

<c:if test="${not empty teams}">
	<h2>Time Change Requests</h2>
	
	<ul>
		<li><a href="${pageContext.servletContext.contextPath}/auth/requests/list">List Requests</a>
	</ul>
</c:if>