<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Request list of ${user.name}</h2>
	<span class="byline">See your time change requests below</span>
</div>


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
			<th>Notes</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requests}" var="entry">
			<tr>
				<td><a title="View request" href="${pageContext.servletContext.contextPath}/auth/requests/view/${entry.id}"><img alt="See details" src="${pageContext.servletContext.contextPath}/resources/images/icons/clipboard24.png"></a></td>
				<td>${entry.requestedOn}</td>
				<td>${entry.requestedOnMS}</td>
				<td>${entry.shiftDay} <br> ${entry.shiftStart} - ${entry.shiftEnd}</td>
				<td>${entry.shiftDayMS}</td>
				<td>${entry.newShiftStart} - ${entry.newShiftEnd}</td>
				<td>${entry.makeupDay}<br>${entry.makeupShiftStart} - ${entry.makeupShiftEnd}</td>
				<td>${entry.shiftDayMS}</td>
				<td>${entry.requestNotes}</td>
				<td>${entry.status}</td>
			</tr>

		</c:forEach>
	</tbody>
</table>
