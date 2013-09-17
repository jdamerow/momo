<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">
	<h2>Manage users!</h2>
	<span class="byline">Add, remove, and edit users</span>
</div>

<table id="userTable">
<thead>
	<tr><th></th><th>Username</th><th>Name</th><th>Email</th><th>Roles</th></tr>
</thead>
<tbody>
	<c:forEach items="${users}" var="user" varStatus="status">
		<tr>
			<td><a title="Delete User" href="${pageContext.servletContext.contextPath}/auth/user/delete/${user.username}"><img alt="Delete User" src="${pageContext.servletContext.contextPath}/resources/images/icons/trash24.png"></a></td>
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