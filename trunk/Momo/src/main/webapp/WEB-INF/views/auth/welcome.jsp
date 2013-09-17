<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Welcome to Momo!</h2>
	<span class="byline">Get back your stolen time</span>
</div>

<p>Welcome back! Start your workday by clocking in!</p>
<form:form method="post" modelAttribute="recording" action="${pageContext.servletContext.contextPath}/auth/signIn">
	<p>
	<form:select path="projectId" items="${projects}"  itemValue="id" itemLabel="name" />
	</p>
	<p>
	<input type="submit" name="submit" value="Clock in" class="button" />
	</p>
</form:form>

