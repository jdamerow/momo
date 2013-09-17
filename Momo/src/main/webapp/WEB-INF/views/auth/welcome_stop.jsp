<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">
	<h2>Welcome to Momo!</h2>
	<span class="byline">Get back your stolen time</span>
</div>

<p>You clocked in ${entry.startDate}. Are you leaving and want to clock
	out?</p>
<a href="${pageContext.servletContext.contextPath}/auth/signOut" class="button">Clock out</a>
