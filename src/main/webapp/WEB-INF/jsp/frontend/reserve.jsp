<%@include file="header.jsp" %>

<h1>${program.movie.name}</h1>
<h2>
	<fmt:formatDate value="${program.date}" pattern="dd.MM.yyyy hh:mm" />
</h2>
<input type="hidden" id="programId" value="${program.id_program}" />

<span>${program.hall.name}</span>
<div id="hall"></div>

<script>

	$(function(){
		cinema.generateHall(${program.hall.rows}, ${program.hall.columns});	
		cinema.setReserved({data: [
			               			<c:forEach items="${reservations}" var="item">
			               				{row: ${item.row}, column: ${item.column}},
			               			</c:forEach>
			               		]
			               		});
	});

</script>

<%@include file="reservationBox.jsp" %>
<%@include file="footer.jsp" %>