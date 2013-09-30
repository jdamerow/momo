<%@page import="edu.asu.momo.core.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<div class="title">
	<h2>Request time change</h2>
	<span class="byline">What do you request to change?</span>
</div>

<form:form method="post" modelAttribute="timeChangeRequestBean"
	action="${pageContext.servletContext.contextPath}/auth/requests/requestChangeTime">

	<table class="form">
		<tr>
			<td width="300">What is the shift you'd like to change?</td>
			<td width="100">Day: <form:input type="text" path="shiftDay"
					id="shift_day_picker" /></td>
			<td><form:errors path="shiftDay" cssClass="errors" /></td>
			<td width="100">Start time: <form:input type="text"
					path="shiftStart" id="shift_start" /></td>
			<td><form:errors path="shiftStart" cssClass="errors" /></td>
			<td width="100">End time: <form:input type="text"
					path="shiftEnd" id="shift_end" /></td>
			<td><form:errors path="shiftEnd" cssClass="errors" /></td>
		</tr>
		<tr>
			<td width="300">Do you still want to work at that day?</td>
			<td width="100"><form:radiobutton path="workThatDay" value="0" id="no" />No
				<form:radiobutton path="workThatDay" value="1" id="yes" />Yes</td>
			<td><form:errors path="workThatDay" cssClass="errors" /></td>
			<td width="100">New start time: <form:input type="text"
					path="newShiftStart" id="new_shift_start" /></td>
			<td><form:errors path="newShiftStart" cssClass="errors" /></td>
			<td width="100">New end time: <form:input type="text"
					path="newShiftEnd" id="new_shift_end" /></td>
			<td><form:errors path="newShiftEnd" cssClass="errors" /></td>
		</tr>

		<tr>
			<td colspan='3'><input name="submit" type="submit"
				value="Request Change" /></td>
		</tr>
	</table>

</form:form>
