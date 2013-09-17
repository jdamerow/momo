<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<div class="title">
	<h2>Projects</h2>
	<span class="byline">These are the projects you work on.</span>
</div>


<c:forEach items="${projectMap}" var="project">

<h3>${project.key.name}</h3>
<c:if test="${project.key.isTeamManager}">
<a class="smallLink" href="${pageContext.servletContext.contextPath}/auth/projects/addProject">+ Add new project</a>
</c:if>

<ul class="boppel">
<c:if test="${not empty project.value}">
	<c:forEach items="${project.value}" var="p">
		<li>${p.name}<br>${p.description}</li>
	</c:forEach>
</c:if>
</ul>
<c:if test="${empty project.value}">
No projects for this team.
</c:if>


</c:forEach>