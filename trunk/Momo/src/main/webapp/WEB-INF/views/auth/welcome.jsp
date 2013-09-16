<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">
	<h2>Welcome to Momo!</h2>
	<span class="byline">Get back your stolen time</span>
</div>

<p>Welcome back! Start your workday by signing in!</p>
<a href="${pageContext.servletContext.contextPath}/auth/signIn" class="button">Sign in</a>

