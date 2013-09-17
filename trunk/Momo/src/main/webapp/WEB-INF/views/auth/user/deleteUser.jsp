<%@page import="edu.asu.momo.core.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>

<div class="title">
	<h2>Delete user?</h2>
	<span class="byline">Do you really want to delete this user?</span>
</div>

<table>
		<tr>
			<td width="100">Name:</td><td> ${user.name}</td>
		</tr>
		<tr>
			<td>Username:</td><td> ${user.username}</td>
		</tr>
		<tr>
			<td>Email:</td><td> ${user.email}</td>
		</tr>
		<tr>
			<td valign="top">Roles: </td>
			<td>
			<c:forEach items="${user.roles}" var="role">
				» ${role.name}<br>
				</c:forEach>
			</td>
		</tr>
	</table>
<p>
<a href="${pageContext.servletContext.contextPath}/auth/user/executeDelete/${user.username}" class="button">Yes, delete user</a>
</p>