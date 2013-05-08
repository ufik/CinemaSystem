<%@include file="header.jsp" %>

<table class="datagrid">
	<tr>
	   	<th>Zákazník</th>
	   	<th>Email zákazníka</th>
	   	<th>Datum a čas</th>
	   	<th class="controll">Úpravy</th>
    </tr>
    
  <c:forEach var="reservation" items="${reservations}">
    <tr>
      <td>${reservation.firstname} ${reservation.lastname}</td>
      <td>${reservation.email}</td>
      <td><fmt:formatDate value="${reservation.date}" pattern="dd.MM.yyyy HH:mm:ss" /></td>
      <td>
	      <a class="btn btn-mini btn-warning" href="admin/reservations/form/${reservation.id_reservation}">Detail</a>
	      <a class="btn btn-mini btn-danger confirm" href="admin/reservations/delete/${reservation.id_reservation}">Smazat</a>
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>