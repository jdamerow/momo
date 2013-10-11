<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Quick Links</h2>
<ul class="style2">
	<!-- <li><a href="#">Your Profile</a></li>
	<li><a href="#">Your last timesheet</a></li> -->
	<li><a
		href="${pageContext.servletContext.contextPath}/auth/profile/changePassword">Change
			password</a></li>
	<li><a
		href="${pageContext.servletContext.contextPath}/auth/projects/overview">Projects</a>
	</li>
</ul>
<h2 style="margin-top: 40px;">Time Change Requests</h2>
<ul class="style2">
	<li><a
		href="${pageContext.servletContext.contextPath}/auth/requests/timeChange">Request
			a time change</a></li>
	<li><a target="_blank" href="${pageContext.servletContext.contextPath}/auth/requests/showCalendar">Show Calendar</a>
	<li><a
		href="${pageContext.servletContext.contextPath}/auth/requests/mylist">See
			your time change requests</a></li>
			
	<c:if test="${not empty managedTeams}">
		<li><a href="${pageContext.servletContext.contextPath}/auth/requests/list">Pending Requests</a>
		<li><a href="${pageContext.servletContext.contextPath}/auth/requests/accepted">Approved Requests</a>
		<li><a href="${pageContext.servletContext.contextPath}/auth/requests/rejected">Rejected Requests</a>
	</c:if>
</ul>