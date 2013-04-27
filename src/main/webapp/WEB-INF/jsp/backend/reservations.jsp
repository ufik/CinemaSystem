<%@include file="header.jsp" %>

<a href="admin/reservations/form" class="btn btn-large">Přidat rezervaci</a><br /><br />

<table class="datagrid">
	<tr>
	   	<th>Zákazník</th>
	   	<th>Email zákazníka</th>
	   	<th>Datum a čas</th>
	   	<th class="controll">Úpravy</th>
    </tr>
    
  <c:forEach var="reservation" items="${reservations}">
    <tr>
      <td>${reservation.contact.firstname} ${reservation.contact.lastname}</td>
      <td>${reservation.contact.email}</td>
      <td>${reservation.date}</td>
      <td>
	      <a class="btn btn-mini btn-warning" href="admin/reservations/form/${reservation.id_reservation}">Upravit</a>
	      <a class="confirm btn btn-mini btn-danger" href="admin/reservations/delete/${reservation.id_reservation}">Smazat</a> 
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>