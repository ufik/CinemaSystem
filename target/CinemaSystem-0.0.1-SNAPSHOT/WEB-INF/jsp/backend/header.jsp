<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="cs">
<head>
	<base href="http://localhost:8080/CinemaSystem/" />
	<meta charset="utf-8">
	<title>Administrace systému</title>

	<spring:url value="/resources/css/style.css" var="css_url" />
	<spring:url value="/resources/libs/jquery-min.js" var="jquery_url" />
	<spring:url value="/resources/js/main.js" var="mainjs_url" />
	
	<link rel="stylesheet" type="text/css" href="${css_url}" />
	
	<script src="${jquery_url}"></script>
	<script src="${mainjs_url}"></script>
</head>
<body>

<header>
	<div id="head-pannel">
		<a href="">Odhlásit</a>
	</div>
</header>

<aside class="leftMenu">
	<ul>
		<li>
			<a href="admin/movies"><span>Filmy</span></a>
		</li>
		<li>
			<a href="admin/program"><span>Program</span></a>
		</li>
		<li>
			<a href="admin/reservations"><span>Rezervace</span></a>
		</li>
		
	</ul>
</aside>
	
<div class="content">
<h1>${title}</h1>