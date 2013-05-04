<%@include file="header.jsp" %>

<h1>${program.movie.name}</h1>
<h2>
	<fmt:formatDate value="${program.date}" pattern="dd.MM.yyyy hh:mm" />
</h2>
<input type="hidden" id="programId" value="${program.id_program}" />
<div id="reserve">
		
		<spring:url value="/reserve/save" var="save_url" />
		<form:form method="post" action="${save_url}" commandName="reservationForm" modelAttribute="reservation" id="reserveForm">
			<form:input path="firstname" />
			<form:input path="lastname" />
			<form:input path="email" />
			<form:input path="telephone" />
			<input type="submit" value="Provést rezervaci" />
		</form:form>
				
			<script>
				$(function(){
					<c:forEach items="${reservation.reservationItems}" var="item">
						cinema.addProgramItem(null, ${item.row}, ${item.column});
					</c:forEach>
				});
			</script>
			
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