<%@include file="header.jsp" %>

<h2>Osobní údaje</h2>
<table>
<tr>
	<th>Jméno:</th>
	<td>${reservation.firstname}</td>
<tr>
	<th>Přijmení:</th>
	<td>${reservation.lastname}</td>
<tr>
	<th>Email:</th>
	<td>${reservation.email}</td>
<tr>
	<th>Telefon:</th>
	<td>${reservation.telephone}</td>
</table>

<h2>Položky rezervace</h2>

<table>
	<tr>
		<th>Název filmu</th>
		<th>Sál a místo</th>
		<th>Datum a čas</th>
		<th>Cena</th>
	</tr>
	<c:if  test="${!empty reservation.reservationItems}">
		<c:forEach items="${reservation.reservationItems}" var="item">
			<tr>
				<td>${item.program.movie.name}</td>
				<td>${item.program.hall.name} řada ${item.row} sedadlo ${item.column}.</td>
				<td><fmt:formatDate value="${item.program.date}" pattern="dd.MM.yyyy HH:mm" /></td>
				<td>${item.program.movie.price},- Kč</td>
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


<%@include file="footer.jsp" %>