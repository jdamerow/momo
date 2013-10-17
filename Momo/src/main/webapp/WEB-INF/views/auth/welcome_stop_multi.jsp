<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">
	<h2>Welcome to Momo!</h2>
	<span class="byline">Get back your lost time</span>
</div>

<p>You have clocked in more than once without clocking out. The following entries exist in Momo:</p>

<ul>
<c:forEach items="${currentEntries}" var="item">
	<li>You clocked in on ${item.date} at ${item.startDate}. <a href="${pageContext.servletContext.contextPath}/auth/welcome/${item.id}">Clock out</a></li>	
</c:forEach>
</ul>