<%@page import="edu.asu.momo.core.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<div class="title">
	<h2>Change your password</h2>
	<span class="byline">Enter your new password here</span>
</div>

<form:form method="post" modelAttribute="pw"
	action="${pageContext.servletContext.contextPath}/auth/profile/executeChange">

	<form:errors cssClass="errors" />
	<table class="form">
		<tr>
			<td width="200">New Password:</td>
			<td><form:input type='password' path='password' value='' /></td>
			<td><form:errors path="password" cssClass="errors" /></td>
		</tr>
		<tr>
			<td>Retype password:</td>
			<td><form:input type='password' path='control' value='' /></td>
			<td><form:errors path="control" cssClass="errors" /></td>
		</tr>
	</table>
	
	<input type="submit" name="submit" value="Change Password" class="button" />
</form:form>