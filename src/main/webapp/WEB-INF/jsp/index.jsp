<%@include file="frontend/header.jsp" %>

<h2>Dnešní program</h2>

	<c:forEach items="${programToday}" var="program">
		<div class="programBlock">
			<fmt:formatDate value="${program.date}" pattern="HH:mm" /><br />
			<span>${program.movie.name}</span><br />
			<a href="reserve/${program.id_program}">Rezervovat místa</a>
		</div>
	</c:forEach>
	
<h2>Zítra</h2>
	
	<c:forEach items="${programTommorow}" var="program">
		<div class="programBlock">
			<fmt:formatDate value="${program.date}" pattern="HH:mm" /><br />
			<span>${program.movie.name}</span><br />
			<a href="reserve/${program.id_program}">Rezervovat místa</a>
		</div>
	</c:forEach>
	
<h2>Pozítří</h2>

	<c:forEach items="${program2Days}" var="program">
		<div class="programBlock">
			<fmt:formatDate value="${program.date}" pattern="HH:mm" /><br />
			<span>${program.movie.name}</span><br />
			<a href="reserve/${program.id_program}">Rezervovat místa</a>
		</div>
	</c:forEach>

<%@include file="frontend/footer.jsp" %>