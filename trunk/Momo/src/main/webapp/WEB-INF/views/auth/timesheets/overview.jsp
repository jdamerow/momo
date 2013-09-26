<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Timesheet overview</h2>
	<span class="byline">See your work times</span>
</div>

<form:form method="post" modelAttribute="timePeriod"
	action="${pageContext.servletContext.contextPath}/auth/timesheets/refreshTimesheet">
	<p>
		Show times from
		<form:input type="text" path="startDay" id="startdatepicker" />
		to
		<form:input type="text" path="endDay" id="enddatepicker" />
		<input type="submit" name="submit" value="Refresh timesheet"><br>
		<form:errors path="startDay" cssClass="errors" />
		<form:errors path="endDay" cssClass="errors" />
	</p>
</form:form>

<table id="userTable">
	<thead>
		<tr>
			<th width="190">Date</th>
			<th></th>
			<th width="110">Clocked in</th>
			<th width="110">Clocked out</th>
			<th width="90">Duration</th>
			<th></th>
			<th>Project</th>
			<th>Notes</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${entries}" var="entry">
			<tr>
				<td>${entry.date}</td>
				<td>${entry.dateAsMSec}</td>
				<td>${entry.startDate}</td>
				<td>${entry.endDate}</td>
				<td>${entry.timeInHM}</td>
				<td>${entry.time}</td>
				<td>${entry.project.name}</td>
				<td>${entry.notes}</td>
			</tr>

		</c:forEach>
	</tbody>
</table>

<p style="clear: both; margin-top: 40px; font-weight: bold;">
	Total of all work times:
	<c:out value="${total}"></c:out>
	.
</p>