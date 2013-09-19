<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Welcome to Momo!</h2>
	<span class="byline">Get back your stolen time</span>
</div>

<p>You clocked in ${entry.startDate}. Are you leaving and want to clock
	out?</p>
<form:form modelAttribute="signOutBackingBean" action="${pageContext.servletContext.contextPath}/auth/signOut">
	<p>
	Notes:<br>
	<form:textarea path="notes" cols="40" rows="10" />
	</p>
	<input type="submit" value="Clock out" name="submit" class="button" />
</form:form>