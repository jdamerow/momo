<%@page import="edu.asu.momo.core.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<div class="title">
	<h2>Add new team</h2>
	<span class="byline">Enter team information</span>
</div>

<form:form method="post" modelAttribute="teamBackingBean"
	action="${pageContext.servletContext.contextPath}/auth/team/addTeam">

	<table class="form">
		<tr>
			<td width="100">Name:</td>
			<td><form:input type='text' path='name' value='' /></td>
			<td><form:errors path="name" cssClass="errors" /></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><form:input type='text' height="20" path='description'
					value='' /></td>
			<td><form:errors path="description" cssClass="errors" /></td>
		</tr>
		<tr>
			<td valign="top">Team Managers:</td>
			<td>
			<ul class="splitList">
				<form:checkboxes element="li" items="${users}"
					itemLabel="name" itemValue="username" path="managers" />
			</ul>
			</td>
			<td><form:errors path="managers" cssClass="errors" /></td>
		</tr>
		<tr>

			<td valign="top">Team Members:</td>
			<td><ul class="splitList">
					<form:checkboxes element="li" items="${users}" itemLabel="name"
						itemValue="username" path="members" />
				</ul></td>
			<td><form:errors path="members" cssClass="errors" /></td>
		</tr>
		<tr>
			<td colspan='4'><input name="submit" type="submit"
				value="Add team" /></td>
		</tr>
	</table>

</form:form>
