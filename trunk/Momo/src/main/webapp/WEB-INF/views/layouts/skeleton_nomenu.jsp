<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Uprightness 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20130920

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><tiles:insertAttribute name="title" /></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800"
	rel="stylesheet" />
<link
	href="${pageContext.servletContext.contextPath}/resources/default.css"
	rel="stylesheet" type="text/css" media="all" />
<link
	href="${pageContext.servletContext.contextPath}/resources/fonts.css"
	rel="stylesheet" type="text/css" media="all" />
<link
	href="${pageContext.servletContext.contextPath}/resources/js/css/jquery.dataTables.css"
	rel="stylesheet" type="text/css" media="all" />
<link
	href="${pageContext.servletContext.contextPath}/resources/js/css/jquery.dataTables_themeroller.css"
	rel="stylesheet" type="text/css" media="all" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

<!-- <script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery.js"></script>-->
<script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery.easyListSplitter.js"></script>

<tiles:insertAttribute name="header" />
</head>
<body>
<tiles:importAttribute name="currentPage" scope="request" />
	<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
				<h1>
					<a href="#">Momo</a>
				</h1>
				<h2>Timesheet Management Tool</h2>
			</div>
			<div id="menu">
				<tiles:insertAttribute name="topmenu" />
			</div>
		</div>
	</div>
	<div id="featured">&nbsp;</div>
	<div id="wrapper">
		<div id="page" class="container">
			<div id="content_nomenu">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>
	<div id="copyright" class="container">
		<p>
			Copyright (c) 2013 <a href="http://devo-evo.lab.asu.edu/diging/">Digital Innovation Group</a>. All rights reserved. | Design by <a
				href="http://www.freecsstemplates.org/" rel="nofollow">FreeCSSTemplates.org</a> | Icons by <a href="http://iconza.com/">Iconza</a>.
		</p>
	</div>

</body>
</html>
