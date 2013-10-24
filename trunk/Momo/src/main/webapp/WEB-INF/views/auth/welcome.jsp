<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Welcome to Momo!</h2>
	<span class="byline">Get back your lost time</span>
</div>

<h2 class="subtitle">Clock in!</h2>

<p>Welcome back! Start your workday by clocking in!</p>
<form:form method="post" modelAttribute="recording" action="${pageContext.servletContext.contextPath}/auth/signIn">
	<p>
	Projects: <form:select path="projectId" items="${projects}"  itemValue="id" itemLabel="name" />
	</p>
	<p>
	What do you plan on working on today?<br>
	<form:textarea path="notes" cols="50" rows="15" />
	</p>
	<p>
	<input type="submit" name="submit" value="Clock in" class="button" />
	</p>
</form:form>

