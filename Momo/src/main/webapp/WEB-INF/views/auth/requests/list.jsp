<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Request list</h2>
	<span class="byline">These are all open time change requests</span>
</div>


<table id="requestTable">
	<thead>
		<tr>
			<th></th>
			<th style="min-width: 120px">Requested by</th>
			<th>Requested on</th>
			<th></th>
			<th width="220">Day of shift requested to change</th>
			<th></th>
			<th width="220">Requested to change time to</th>
			<th width="220">Requested to make up on</th>
			<th></th>
			<th>Notes</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requests}" var="entry">
			<tr>
				<td><a title="View request" href="${pageContext.servletContext.contextPath}/auth/requests/view/${entry.id}"><img alt="Edit Team" src="${pageContext.servletContext.contextPath}/resources/images/icons/edit24.png"></a></td>
				<td>${entry.requester.name}</td>
				<td>${entry.requestedOn}</td>
				<td>${entry.requestedOnMS}</td>
				<td>${entry.shiftDay} <br> ${entry.shiftStart} - ${entry.shiftEnd}</td>
				<td>${entry.shiftDayMS}</td>
				<td>${entry.newShiftStart} - ${entry.newShiftEnd}</td>
				<td>${entry.makeupDay}<br>${entry.makeupShiftStart} - ${entry.makeupShiftEnd}</td>
				<td>${entry.shiftDayMS}</td>
				<td>${entry.requestNotes}</td>
			</tr>

		</c:forEach>
	</tbody>
</table>
