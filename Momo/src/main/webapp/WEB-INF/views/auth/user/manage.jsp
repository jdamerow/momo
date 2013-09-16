<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">
	<h2>Manage users!</h2>
	<span class="byline">Add, remove, and edit users</span>
</div>

<table id="userTable">
<thead>
	<tr><th>Username</th><th>Name</th><th>Email</th><th>Roles</th></tr>
</thead>
<tbody>
	<c:forEach items="${users}" var="user" varStatus="status">
		<tr>
			<td>${user.username}</td>
			<td>${user.name}</td>
			<td>${user.email}</td>
			<td>
				<c:forEach items="${user.roles}" var="role">
				» ${role.name}
				</c:forEach>
			</td>
		</tr>

	</c:forEach>
</tbody>
</table>