<%@page import="edu.asu.momo.core.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<div class="title">
	<h2>Request time change</h2>
	<span class="byline">What shift do you request to change?</span>
</div>

<form:form method="post" modelAttribute="timeChangeRequestBean"
	action="${pageContext.servletContext.contextPath}/auth/requests/requestTimeChange">

	<table class="form">
		<tr>
			<td width="300">What is the shift you'd like to change?</td>
			<td width="200">Day: <br><form:input type="text" path="shiftDay"
					id="shift_day_picker" /></td>
			<td width="200">Start time: <br><form:input type="text"
					path="shiftStart" id="shift_start" /></td>
			<td width="200">End time: <br><form:input type="text"
					path="shiftEnd" id="shift_end" /></td>
		</tr>
		<tr>
			<td></td>
			<td><form:errors path="shiftDay" cssClass="errors" /></td>
			<td><form:errors path="shiftStart" cssClass="errors" /></td>
			<td><form:errors path="shiftEnd" cssClass="errors" /></td>
		</tr>
		<tr>
			<td>Do you still want to work at that day?</td>
			<td></td>
			<td>New start time: <br><form:input type="text"
					path="newShiftStart" id="new_shift_start" /></td>
			<td>New end time: <br><form:input type="text"
					path="newShiftEnd" id="new_shift_end" /></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td><form:errors path="newShiftStart" cssClass="errors" /></td>
			<td><form:errors path="newShiftEnd" cssClass="errors" /></td>
		</tr>
		
		<tr>
			<td>Do you want to make up for the hours on another day?</td>
			<td>Day: <br><form:input type="text" path="makeupDay"
					id="makeup_shift_day_picker" /></td>
			<td>Start time: <br><form:input type="text"
					path="makeupShiftStart" id="makeup_shift_start" /></td>
			<td>End time: <br><form:input type="text"
					path="makeupShiftEnd" id="makeup_shift_end" /></td>
		</tr>
		<tr>
			<td></td>
			<td><form:errors path="makeupDay" cssClass="errors" /></td>
			<td><form:errors path="makeupShiftStart" cssClass="errors" /></td>
			<td><form:errors path="makeupShiftEnd" cssClass="errors" /></td>
		</tr>
		<tr>
			<td>Why would you like to change your shift?</td>
			<td colspan="3">
			<form:textarea rows="7" cols="70"
					path="requestNotes" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="3">
			<form:errors path="requestNotes" cssClass="errors" />
			</td>
		</tr>

	</table>
	
	<input name="submit" type="submit"
				value="Request Change" class="button"/>

</form:form>
