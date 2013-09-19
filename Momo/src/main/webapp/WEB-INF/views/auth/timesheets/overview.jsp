<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Timesheet overview</h2>
	<span class="byline">See your work times</span>
</div>

<form:form method="post" modelAttribute="timePeriod" action="${pageContext.servletContext.contextPath}/auth/timesheets/refreshTimesheet">
	<p>Show times from <form:input type="text" path="startDay" id="startdatepicker" /> to <form:input type="text" path="endDay" id="enddatepicker" /> <input type="submit" name="submit" value="Refresh timesheet"><br>
	<form:errors path="startDay" cssClass="errors" />
	<form:errors path="endDay" cssClass="errors" />
	</p>
</form:form>

<table id="userTable">
<thead>
	<tr><th>Date</th><th>Signed in</th><th>Signed out</th><th>Duration</th><th>Project</th><th>Notes</th></tr>
</thead>
<tbody>
	<c:forEach items="${entries}" var="entry">
		<tr>
			<td>${entry.date}</td>
			<td>${entry.startDate}</td>
			<td>${entry.endDate}</td>
			<td>${entry.time}</td>
			<td>${entry.project.name}</td>
			<td>${entry.notes}</td>
		</tr>

	</c:forEach>
</tbody>
</table>