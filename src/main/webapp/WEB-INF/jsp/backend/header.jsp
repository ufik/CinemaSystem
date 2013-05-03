<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="cs">
<head>
	<base href="http://localhost:8080/CinemaSystem/" />
	<meta charset="utf-8">
	<title>Administrace systému</title>

	<spring:url value="/resources/css/style.css" var="css_url" />
	<spring:url value="/resources/css/datagrid.css" var="datagridCss_url" />
	<spring:url value="/resources/css/datetime-polyfill.css" var="datetime_url" />
	<spring:url value="/resources/libs/jquery-ui/themes/base/jquery-ui.css" var="jquery_ui_css_url" />
	<spring:url value="/resources/libs/jquery-min.js" var="jquery_url" />
	<spring:url value="/resources/libs/jquery-ui/ui/minified/jquery-ui.min.js" var="jquery_ui_url" />
	<spring:url value="/resources/js/datetime-polyfill.js" var="datetime_polyfill_url" />
	<spring:url value="/resources/libs/modernizr.js" var="modernizr_url" />
	<spring:url value="/resources/js/main.js" var="mainjs_url" />
	<spring:url value="/resources/js/Cinema.js" var="cinema_url" />
	
	<link rel="stylesheet" type="text/css" href="${css_url}" />
	<link rel="stylesheet" type="text/css" href="${datagridCss_url}" />
	<link rel="stylesheet" type="text/css" href="${datetime_url}" />
	<link rel="stylesheet" type="text/css" href="${jquery_ui_css_url}" />
	
	<script src="${jquery_url}"></script>
	<script src="${modernizr_url}"></script>
	<script src="${mainjs_url}"></script>
	<script src="${jquery_ui_url}"></script>
	<script src="${datetime_polyfill_url}"></script>
	<script src="${cinema_url}"></script>
</head>
<body>

<header>
	<div id="head-pannel">
		<time id="timestamp"></time>
		<a class="btn btn-primary" href="">Nastavení účtu</a>
		<a class="btn btn-danger confirm" href="">Odhlásit se</a>
	</div>
</header>

<aside class="leftMenu">

	<ul>
		<li>
			<a href="admin/movies" <c:if test="${id == 'movies'}">class="selected"</c:if>><span>Filmy</span></a>
		</li>
		<li>
			<a href="admin/halls" <c:if test="${id == 'halls'}">class="selected"</c:if>><span>Sály</span></a>
		</li>
		<li>
			<a href="admin/program" <c:if test="${id == 'program'}">class="selected"</c:if>><span>Program</span></a>
		</li>
		<li>
			<a href="admin/reservations" <c:if test="${id == 'reservations'}">class="selected"</c:if>><span>Rezervace</span></a>
		</li>
		<li>
			<a href="admin/contacts" <c:if test="${id == 'contacts'}">class="selected"</c:if>><span>Zákazníci</span></a>
		</li>
	</ul>
</aside>
	
<div class="content">
<h1>${title}</h1>

<div class="status">
	<c:if test="${SUCCESS_MESSAGE != null}">
	  <div class="flash ok">${SUCCESS_MESSAGE}</div>
	</c:if>
	<c:if test="${ERROR_MESSAGE != null}">
	  <div class="flash error">${ERROR_MESSAGE}</div>
	</c:if>
	<c:if test="${INFO_MESSAGE != null}">
	  <div class="flash info">${INFO_MESSAGE}</div>
	</c:if>
</div>