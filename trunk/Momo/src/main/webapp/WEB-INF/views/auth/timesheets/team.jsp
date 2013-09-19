<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Team Timesheet Overview</h2>
	<span class="byline">These are the timesheets of your team ${team.name} </span>
</div>

<form:form method="post" modelAttribute="timeSheetSelection"
	action="${pageContext.servletContext.contextPath}/auth/timesheets/team/refreshTimesheet">
	<form:input type="hidden" path="teamId" value="${team.id}" />
	
	<table class="formData">
	<tr>
	<td colspan="3">
		Show times from
		<form:input type="text" path="startDay" id="startdatepicker" />
		to
		<form:input type="text" path="endDay" id="enddatepicker" />
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
		class="button" style="margin-bottom: 30px" >
</form:form>

<table id="userTable">
<thead>
	<tr><th style="min-width: 80px">Name</th><th>Date</th><th></th><th>Signed in</th><th>Signed out</th><th width="100">Duration</th><th>Project</th><th>Notes</th></tr>
</thead>
<tbody>
	<c:forEach items="${entries}" var="entry">
		<tr>
			<td>${entry.user.name}</td>
			<td>${entry.date}</td>
			<td>${entry.dateAsMSec}</td>
			<td>${entry.startDate}</td>
			<td>${entry.endDate}</td>
			<td>${entry.time}</td>
			<td>${entry.project.name}</td>
			<td>${entry.notes}</td>
		</tr>

	</c:forEach>
</tbody>
</table>

