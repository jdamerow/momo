<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">
	<h2>Manage Teams</h2>
	<span class="byline">Add, remove, and edit teams</span>
</div>

<table id="userTable" class="boppelList">
<thead>
	<tr><th>Team</th><th>Team managers</th><th>Team members</th></tr>
</thead>
<tbody>
	<c:forEach items="${teams}" var="team" >
		<tr>
			<td>${team.name}</td>
			<td>
			<ul class="splitList">
				<c:forEach items="${team.managers}" var="manager">
				<li> ${manager.name}</li>
				</c:forEach>
			</ul>
			</td>
			<td>
			<ul class="splitList">
				<c:forEach items="${team.members}" var="member">
				<li> ${member.name}</li>
				</c:forEach>
			</ul>
			</td>
		</tr>

	</c:forEach>
</tbody>
</table>