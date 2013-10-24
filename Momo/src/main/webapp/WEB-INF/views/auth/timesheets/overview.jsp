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
			<th width="160">Date</th>
			<th></th>
			<th width="110">Clocked in</th>
			<th width="110">Clocked out</th>
			<th width="90">Duration</th>
			<th></th>
			<th width="90">Lunch break</th>
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
				<td>${entry.breakTime}</td>
				<td>${entry.project.name}</td>
				<td>Clocking in notes:<br> ${entry.clockingInNotes} <br><br> Clocking out notes: <br>${entry.notes}</td>
			</tr>

		</c:forEach>
	</tbody>
</table>

<p style="clear: both; margin-top: 40px; font-weight: bold;">
	Total of all work times (minus lunch breaks):
	<c:out value="${total}"></c:out>
	.
</p>

<h3 style="margin-top: 100px;">Approved time change requests</h3>
<p>
<table id="requestTable">
	<thead>
		<tr>
			<th></th>
			<th>Requested on</th>
			<th></th>
			<th width="220">Day of shift requested to change</th>
			<th></th>
			<th width="220">Requested to change time to</th>
			<th width="220">Requested to make up on</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${approvedRequests}" var="entry">
			<tr>
				<td><a title="View request" href="${pageContext.servletContext.contextPath}/auth/requests/view/${entry.id}"><img alt="Show details" src="${pageContext.servletContext.contextPath}/resources/images/icons/clipboard24.png"></a></td>
				<td>${entry.requestedOn}</td>
				<td>${entry.requestedOnMS}</td>
				<td>${entry.shiftDay} <br> ${entry.shiftStart} - ${entry.shiftEnd}</td>
				<td>${entry.shiftDayMS}</td>
				<td>${entry.newShiftStart} - ${entry.newShiftEnd}</td>
				<td>${entry.makeupDay}<br>${entry.makeupShiftStart} - ${entry.makeupShiftEnd}</td>
				<td>${entry.shiftDayMS}</td>
			</tr>

		</c:forEach>
	</tbody>
</table>

<h3 style="margin-top: 100px;">Rejected time change requests</h3>
<p>
<table id="rejectedTable">
	<thead>
		<tr>
			<th></th>
			<th>Requested on</th>
			<th></th>
			<th width="220">Day of shift requested to change</th>
			<th></th>
			<th width="220">Requested to change time to</th>
			<th width="220">Requested to make up on</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${deniedRequests}" var="entry">
			<tr>
				<td><a title="View request" href="${pageContext.servletContext.contextPath}/auth/requests/view/${entry.id}"><img alt="Show details" src="${pageContext.servletContext.contextPath}/resources/images/icons/clipboard24.png"></a></td>
				<td>${entry.requestedOn}</td>
				<td>${entry.requestedOnMS}</td>
				<td>${entry.shiftDay} <br> ${entry.shiftStart} - ${entry.shiftEnd}</td>
				<td>${entry.shiftDayMS}</td>
				<td>${entry.newShiftStart} - ${entry.newShiftEnd}</td>
				<td>${entry.makeupDay}<br>${entry.makeupShiftStart} - ${entry.makeupShiftEnd}</td>
				<td>${entry.shiftDayMS}</td>
			</tr>

		</c:forEach>
	</tbody>
</table>