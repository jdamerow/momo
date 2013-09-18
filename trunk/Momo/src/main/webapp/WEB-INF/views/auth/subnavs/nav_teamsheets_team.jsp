<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Your Teams</h2>
<ul class="style2">
	<c:forEach items="${teams}" var="team">
		<li><a href="${pageContext.servletContext.contextPath}/auth/timesheets/team/${team.id}">${team.name}</a>
	</c:forEach>
</ul>