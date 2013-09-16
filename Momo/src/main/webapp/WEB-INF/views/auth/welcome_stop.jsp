<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">
	<h2>Welcome to Momo!</h2>
	<span class="byline">Get back your stolen time</span>
</div>

<p>You logged in ${entry.startDate}. Are you leaving and want to log
	out?</p>
<a href="${pageContext.servletContext.contextPath}/auth/signOut" class="button">Log out</a>
