<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>

<div>
<b>
Your session has expired! Please login to finish the action you were about to perform.
</b>
</div>
<p>
<form name='f' action="<c:url value='j_spring_security_check' />"
	method='post'>

	<table>
		<tr>
			<td>Username:</td>
			<td><input type='text' name='j_username' value=''></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type='password' name='j_password' /></td>
		</tr>
		<tr>
	</table>
	<p>
		<input name="submit" type="submit" value="Login" class="button" />
	</p>

	<form:errors path="" cssStyle="color: #ff0000;"/>

</form>