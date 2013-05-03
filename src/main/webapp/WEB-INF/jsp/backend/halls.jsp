<%@include file="header.jsp" %>

<a href="admin/halls/form" class="btn btn-large">Přidat sál</a><br /><br />

<table class="datagrid">
	<tr>
	   	<th>Název</th>
	   	<th>Počet sedadel</th>
	   	<th class="controll">Úpravy</th>
    </tr>
   
  <c:forEach var="hall" items="${halls}">
    <tr>
      <td><a class="btn btn-mini btn-warning" title="Upravit sál ${hall.name}?" href="admin/halls/form/${hall.id_hall}">${hall.name}</a></td>
      <td>${hall.capacity}</td>
      <td>
	      <a class="btn btn-mini btn-warning" title="Upravit sál ${hall.name}?" href="admin/halls/form/${hall.id_hall}">Upravit</a>
	      <a class="btn btn-mini btn-danger confirm" title="Smazat sál ${hall.name}?" href="admin/halls/delete/${hall.id_hall}">Smazat</a> 
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>