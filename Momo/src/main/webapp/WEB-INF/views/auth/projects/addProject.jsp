<%@page import="edu.asu.momo.core.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>

<div class="title">
	<h2>Add new project</h2>
	<span class="byline">Enter project information</span>
</div>

<form:form method="post" modelAttribute="project" action="${pageContext.servletContext.contextPath}/auth/projects/executeAdd">

	<table class="form">
		<tr>
			<td width="100">Project name:</td>
			<td><form:input type='text' path='name' value=''/></td>
			<td><form:errors path="name" cssClass="errors" /></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><form:input type='text' path='description' value='' /></td>
			<td><form:errors path="description" cssClass="errors" /></td>
		</tr>
		<tr>
			<td>Team:</td>
			<td><form:select path="team" items="${teams}"  itemValue="id" itemLabel="name" /></td>
			<td><form:errors path="team" cssClass="errors" /></td>
		</tr>
		<tr>
			<td colspan='3'><input name="submit" type="submit"
				value="Create project" /></td>
		</tr>
	</table>

</form:form>
