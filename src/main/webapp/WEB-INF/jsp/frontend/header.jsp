<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
	<title>Cinema System - test version</title>
	<spring:url value="/resources/libs/jquery-min.js" var="jquery_url" /><script src="${jquery_url}"></script>
	<spring:url value="/resources/js/sly.min.js" var="sly_url" /><script src="${sly_url}"></script>
	<spring:url value="/resources/js/Cinema.js" var="cinema_js_url" /><script src="${cinema_js_url}"></script>
	
	<spring:url value="/resources/css/fe-style.css" var="css_url" /><link rel="stylesheet" type="text/css" href="${css_url}" />
	<spring:url value="/resources/css/normalize.css" var="normalize_url" /><link rel="stylesheet" type="text/css" href="${normalize_url}" />
</head>
<body>

<div class="wrap">
	<h2>Co hrajeme?</h2>

	<div class="scrollbar">
		<div class="handle">
			<div class="mousearea"></div>
		</div>
	</div>

	<div class="frame effects" id="effects">
		<ul class="clearfix">
			<c:if  test="${!empty movies}">
				<c:forEach items="${movies}" var="movie">
					<li style="background: url('${movie.poster}');background-repeat: no-repeat;background-size: 100% 100%;">
						<section data-csfd="${$movie.idCsfd}" class="movie">
							<header>
								<span>
									<span class="name">${movie.name}</span>
									<br />
									<span class="price"><fmt:formatNumber value="${movie.price}" type="currency"/></span>
									<br />
									<spring:url value="/movie/${movie.id_movie}" var="cinema_js_url" />
									<span class="button detail"><a href="${cinema_js_url}">Detail filmu</a></span>
								</span>
							</header>
						</section>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>

	<div class="controls center">
		<button class="btn prev"><i class="icon-chevron-left"></i> Předchozí</button>
		<button class="btn next">Další <i class="icon-chevron-right"></i></button>
	</div>
</div>