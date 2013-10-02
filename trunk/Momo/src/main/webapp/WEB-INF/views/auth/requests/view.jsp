<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Time Change Request</h2>
	<span class="byline">by ${timeRequest.requester.name}</span>
</div>

<c:if test="${isRequester or isTeamManager}">
<table class="request">
	<tbody>
		<tr>
			<td style="padding-right: 15px;">Requested by:</td>
			<td style="min-width: 300px;">${timeRequest.requester.name}</td>
		</tr>
		<tr>
			<td style="padding-right: 15px;">Requested on:</td>
			<td style="min-width: 300px;">${timeRequest.requestedOn}</td>
		</tr>
		<tr>
			<td style="padding-right: 15px;">Shift requested to change:</td>
			<td>${timeRequest.shiftDay}<br> ${timeRequest.shiftStart} -
				${timeRequest.shiftEnd}
			</td>
		</tr>
		<tr>
			<td style="padding-right: 15px;">Requested to change time to:</td>
			<td>${timeRequest.newShiftStart} - ${timeRequest.newShiftEnd}</td>
		</tr>
		<tr>
			<td style="padding-right: 15px;">Requested to make up on:</td>
			<td>${timeRequest.makeupDay} <br> ${timeRequest.makeupShiftStart} -
				${timeRequest.makeupShiftEnd}
			</td>
		</tr>
		<tr>
			<td style="padding-right: 15px;">Message:</td>
			<td>${timeRequest.requestNotes}</td>
		</tr>
		<tr style="background-color: #D6DFEE;">
			<td>Status:</td>
			<td>${timeRequest.status}</td>
		</tr>
		<tr style="background-color: #EFF4FC;">
			<td>Reviewer:</td>
			<td>${timeRequest.reviewer.name}</td>
		</tr>
		<tr style="background-color: #EFF4FC;">
			<td>Reviewed on:</td>
			<td>${timeRequest.reviewedOn}</td>
		</tr>
			<tr style="background-color: #EFF4FC;">
			<td>Review notes:</td>
			<td>${timeRequest.reviewNotes}</td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${isTeamManager and isApprovable}">
<form:form method="post" modelAttribute="actionRequestBean"
	action="${pageContext.servletContext.contextPath}/auth/requests/decideRequest">
	<div>
	<form:hidden path="requestId" value="${timeRequest.id}"/>
	<form:textarea rows="5" cols="70"
					path="notes" />
	</div>
	<input style="float: left; margin-left: 10px;" name="submit_approval" type="submit"
				value="Approve Request" class="button_blue"/>
	<input name="submit_rejection" type="submit"
				value="Reject Request" class="button_red"/>
</form:form>

</c:if>