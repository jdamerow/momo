<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Team Timesheet Overview</h2>
	<span class="byline">These are the timesheets of your team
		${team.name} </span>
</div>

<form:form method="post" modelAttribute="timeSheetSelection"
	action="${pageContext.servletContext.contextPath}/auth/timesheets/team/refreshTimesheet">
	<form:input type="hidden" path="teamId" value="${team.id}" />

	<table class="formData">
		<tr>
			<td colspan="3">Show times from <form:input type="text"
					path="startDay" id="startdatepicker" /> to <form:input type="text"
					path="endDay" id="enddatepicker" />
			</td>
		</tr>


		<tr>
			<td>Show timesheets of:</td>
			<td><c:if test="${not empty managers}">

					<div class="cellTitle">Team managers</div>

					<ul class="splitList">
						<form:checkboxes element="li" items="${managers}" itemLabel="name"
							itemValue="username" path="managers" />
					</ul>

				</c:if></td>
			<td><c:if test="${not empty members}">
					<div class="cellTitle">Team members</div>
					<ul class="splitList">
						<form:checkboxes element="li" items="${members}" itemLabel="name"
							itemValue="username" path="members" />
					</ul>

				</c:if></td>
		</tr>
	</table>
	<input type="submit" name="submit" value="Refresh timesheet"
		class="button" style="margin-bottom: 30px">
</form:form>

<table id="userTable">
	<thead>
		<tr>
			<th style="min-width: 120px">Name</th>
			<th width="180">Date</th>
			<th></th>
			<th width="110">Clocked in</th>
			<th width="110">Clocked out</th>
			<th width="90">Duration</th>
			<th></th>
			<th width="120">Lunch break</th>
			<th>Project</th>
			<th>Notes</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${entries}" var="entry">
			<tr>
				<td>${entry.user.name}</td>
				<td>${entry.date}</td>
				<td>${entry.dateAsMSec}</td>
				<td>${entry.startDate}</td>
				<td>${entry.endDate}</td>
				<td>${entry.timeInHM}</td>
				<td>${entry.time}</td>
				<td>${entry.breakTime}</td>
				<td>${entry.project.name}</td>
				<td>${entry.notes}</td>
			</tr>

		</c:forEach>
	</tbody>
</table>

<p style="clear: both; margin-top: 40px; font-weight: bold;">
	Total of all work times (minus lunch breaks):
	<c:out value="${total}"></c:out>
	.
</p>
