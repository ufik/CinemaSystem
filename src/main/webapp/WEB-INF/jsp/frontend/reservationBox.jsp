<div id="reserve">
		
<spring:url value="/reserve/save" var="save_url" />
<form:form method="post" action="${save_url}" commandName="reservationForm" modelAttribute="reservation" id="reserveForm">
	<form:input path="firstname" placeholder="Jméno" />
	<form:input path="lastname" placeholder="Přijmení" />
	<form:input path="email" placeholder="Email" />
	<form:input path="telephone" placeholder="Telefon" />
	<input type="submit" value="Uložit údaje" />
	<input type="hidden" name="programItems" value="0" />
</form:form>
		
	<script>
		$(function(){
			<c:forEach items="${reservation.reservationItems}" var="item">
				cinema.addProgramItem(null, ${item.row}, ${item.column}, ${item.program.id_program});
			</c:forEach>
		});
	</script>
			
</div>