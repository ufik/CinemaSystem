<%@include file="header.jsp" %>
<h1>Rekapitulace rezervace</h1>

<h2>Osobní údaje</h2>
${error}
<spring:url value="/reserve/save" var="save_url" />
<form:form method="post" action="${save_url}" commandName="reservationForm" modelAttribute="reservation" id="reserveForm">
<table>
<tr>
	<th>Jméno:</th>
	<td><form:input path="firstname" /><form:errors path="firstname" cssClass="error" /></td>
<tr>
	<th>Přijmení:</th>
	<td><form:input path="lastname" /><form:errors path="lastname" cssClass="error" /></td>
<tr>
	<th>Email:</th>
	<td><form:input path="email" /><form:errors path="email" cssClass="error" /></td>
<tr>
	<th>Telefon:</th>
	<td><form:input path="telephone" /><form:errors path="telephone" cssClass="error" /></td>
</tr>
</table>


<h2>Položky rezervace</h2>
<input type="hidden" name="programItems" value="0" />
<table>
	<tr>
		<th>Název filmu</th>
		<th>Sál a místo</th>
		<th>Datum a čas</th>
		<th>Cena</th>
		<th>Smazat</th>
	</tr>
	<c:if  test="${!empty reservation.reservationItems}">
		<c:forEach items="${reservation.reservationItems}" var="item">
			<tr>
				<input type="hidden" name="programItems" value="${item.row},${item.column},${item.program.id_program}" />
				<td>${item.program.movie.name}</td>
				<td>${item.program.hall.name} řada ${item.row} sedadlo ${item.column}.</td>
				<td>${item.program.date}</td>
				<td>${item.program.movie.price},- Kč</td>
				<td><a href="#" class="reservationDelete">&times;</a>
			</tr>
		</c:forEach>
	</c:if>
	<tfoot>
		<tr style="border-top: 1px solid black;">
			<th>Celková částka:</th>
			<th colspan="3" style="text-align: right;">${reservation.totalPrice},- Kč</th>
		</tr>
	</tfoot>
</table>
<input type="submit" value="Uložit údaje" />
</form:form>

<spring:url value="/reserve/finish" var="save_url" />
<form:form method="post" action="${save_url}">
	<input type="submit" value="Dokončit rezervaci" />
</form:form>

<%@include file="footer.jsp" %>