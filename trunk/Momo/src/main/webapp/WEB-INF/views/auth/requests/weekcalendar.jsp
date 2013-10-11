<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<style type='text/css'>

	body {
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		margin: 0;
	}
	
	h1 {
		margin: 0;
		padding: 0.5em;
	}
	
	p.description {
		font-size: 0.8em;
		padding: 1em;
		position: absolute;
		top: 3.2em;
		margin-right: 400px;
	}
	
	#message {
		font-size: 0.7em;
		position: absolute;
		top: 1em; 
		right: 1em;
		width: 350px;
		display: none;
		padding: 1em;
		background: #ffc;
		border: 1px solid #dda;
	}
	
</style>
<link rel='stylesheet' type='text/css' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/smoothness/jquery-ui.css' />
<link rel='stylesheet' type='text/css' href='${pageContext.servletContext.contextPath}/resources/js/calendar/jquery.weekcalendar.css' />

<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js'></script>
<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js'></script>
<script type='text/javascript' src='${pageContext.servletContext.contextPath}/resources/js/calendar/jquery.weekcalendar.js'></script>
<script type='text/javascript'>

 
	$(document).ready(function() {

		$('#calendar').weekCalendar({
			timeslotsPerHour: 2,
			allowCalEventOverlap: true,
			readonly: true,
			height: function($calendar){
				return $(window).height() - $("h1").outerHeight();
			},
			eventRender : function(calEvent, $event) {
				if(calEvent.end.getTime() < new Date().getTime()) {
					//$event.css("backgroundColor", "#ddd");
					$event.css("color", "#333");
					/*$event.find(".wc-time").css({"backgroundColor": "#999", "border":"1px solid #888"});*/
					
				}
			},
			eventClick : function(calEvent, $event) {
				displayMessage("<strong>" + calEvent.title + "</strong><br>Start: " + calEvent.start + "<br/>End: " + calEvent.end);
			},
			noEvents : function() {
				displayMessage("There are no events for this week");
			},
			overlapEventsSeparate : true,
			data: function(start, end, callback) {
					  $.getJSON("${pageContext.servletContext.contextPath}/auth/requests/calendarevents.json", {
					     start: start.getTime(),
					     end: end.getTime()
					   },  function(result) {
					     callback(result);
					   });
				}
		});

		function displayMessage(message) {
			$("#message").html(message).fadeIn();
		}

		$("<div id=\"message\" class=\"ui-corner-all\"></div>").prependTo($("body"));
		
	});

</script>
</head>
<body>
	<h1>Approved Time Changes</h1>
	<p class="description">This calendar shows all approved time changes. Work times that were changes are displayed in grey. Regular shifts that got extended or shortened are displayed in yellow. Times to make up for hours are displayed in blue.</p>
	<div id='calendar'></div>
	
</body>
</html>
