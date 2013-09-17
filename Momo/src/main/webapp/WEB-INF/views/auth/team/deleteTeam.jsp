<%@page import="edu.asu.momo.core.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<div class="title">
	<h2>Delete team</h2>
	<span class="byline">Do you really want to delete this team?</span>
</div>

<form:form method="post" modelAttribute="team"
	action="${pageContext.servletContext.contextPath}/auth/team/executeDelete">
	<form:input path="id" type="hidden" value="${id}" />
	<table class="boppel">
		<tr>
			<td width="150">Name:</td>
			<td>${team.name}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>${team.description}</td>
		</tr>
		<tr>
			<td valign="top">Team Managers:</td>
			<td>
			<ul class="splitList">
				<c:forEach items="${team.managers}" var="man">
					<li>${man.name}</li>
				</c:forEach>
			</ul>
			</td>
		</tr>
		<tr>

			<td valign="top">Team Members:</td>
			<td><ul class="splitList">
					<c:forEach items="${team.members}" var="man">
						<li>${man.name}</li>
					</c:forEach>
				</ul></td>
		</tr>
		<tr>
			<td colspan='4'><input name="submit" type="submit"
				value="Yes, delete team" class="button"/></td>
		</tr>
	</table>

</form:form>
