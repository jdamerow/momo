<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul>
	<sec:authorize access="isAuthenticated()">
		<li ${currentPage == "home" ? "class=\"current_page_item\"" : ""}><a
			href="${pageContext.servletContext.contextPath}/auth/welcome" accesskey="1" title="">Enter time</a></li>
		<li
			${currentPage == "timesheets" ? "class=\"current_page_item\"" : ""}><a
			href="${pageContext.servletContext.contextPath}/auth/timesheets/overview" accesskey="2" title="">Timesheets</a></li>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<li ${currentPage == "user" ? "class=\"current_page_item\"" : ""}><a
				href="${pageContext.servletContext.contextPath}/auth/user/manage">User
					Management</a>
		</sec:authorize>

		<li><a href="<c:url value='/j_spring_security_logout' />">Logout</a></li>
	</sec:authorize>
</ul>