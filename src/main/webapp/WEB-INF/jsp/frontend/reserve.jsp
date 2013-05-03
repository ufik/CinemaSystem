<%@include file="header.jsp" %>

<h1>${program.movie.name}</h1>
<h2>
	<fmt:formatDate value="${program.date}" pattern="dd.MM.yyyy hh:mm" />
</h2>

<div id="reserve">
	
	<spring:url value="/reserve/save" var="save_url" />
	<form:form method="post" action="${save_url}" modelAttribute="reservationForm" id="reserveForm">
		
		<input type="submit" value="Rezervovat" />
	</form:form>
		
		<c:forEach items="${reserveForm.programItems}" var="item">
			${item}
		</c:forEach>
		
</div>

<span>${program.hall.name}</span>
<div id="hall"></div>

<script>
	$(function(){
		cinema.generateHall(${program.hall.rows}, ${program.hall.columns});	
		//cinema.setProgram();
	});
</script>

<%@include file="footer.jsp" %>