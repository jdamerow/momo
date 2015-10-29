<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="title">
	<h2>Welcome to Momo!</h2>
	<span class="byline">Get back your lost time</span>
</div>

<h2 class="subtitle">Clock out!</h2>

<p>You clocked in ${entry.startDate}. Are you leaving and want to clock
	out?</p>
<div id="countdown"></div>
<form:form modelAttribute="signOutBackingBean" action="${pageContext.servletContext.contextPath}/auth/signOut">
	<p>
	What did you do today?<br>
	<form:textarea path="notes" cols="50" rows="15" />
	</p>
	<p>
	Did you take a lunch break?
	<form:radiobuttons path="breakTime" items="${breakTimes}" itemValue="time" itemLabel="label" cssClass="radioOptions" />
	</p>
	<input type="submit" value="Clock out" name="submit" class="button" />
</form:form>

<script type="text/javascript">
$(function(){
	
	$('#countdown').countup();
	
});
</script>