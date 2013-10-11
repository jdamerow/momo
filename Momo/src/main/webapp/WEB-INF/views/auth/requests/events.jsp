<%@page import="java.util.Random"%>
<%@page import="java.util.UUID"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

[
<c:forEach items="${requests}" var="req">
	<c:if test="${not empty req.oldStartDate}">
	{"id":1,"title":"${req.user.name}", "start": ${req.oldStartDate.time}, "end": ${req.oldEndDate.time}},
	</c:if>
	<c:if test="${not empty req.newStartDate}">
	{"id":2,"title":"${req.user.name}", "start": ${req.newStartDate.time}, "end": ${req.newEndDate.time}},
	</c:if>
	<c:if test="${not empty req.makeupStartDate}">
	{"id":3,"title":"${req.user.name}", "start": ${req.makeupStartDate.time}, "end": ${req.makeupEndDate.time}},
	</c:if>
</c:forEach>	
]