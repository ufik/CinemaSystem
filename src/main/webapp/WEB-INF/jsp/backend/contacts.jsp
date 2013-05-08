<%@include file="header.jsp" %>

<a href="admin/contacts/form" class="btn btn-large">Přidat kontakt</a><br /><br />

<table class="datagrid">
	<tr>
	   	<th>Jméno</th>
	   	<th>Email / telefon</th>
	   	<th class="controll">Úpravy</th>
    </tr>
   
  <c:forEach var="contact" items="${contacts}">
    <tr>
      <td><a class="btn btn-mini btn-warning" title="Upravit kontakt ${contact.firstname} ${contact.lastname}?" href="admin/contacts/form/${contact.id}">${contact.firstname} ${contact.lastname}</a></td>
      <td>${contact.email} ${contact.telephone}</td>
      <td>
	      <a class="btn btn-mini btn-warning" title="Upravit kontakt ${contact.firstname} ${contact.lastname}?" href="admin/contacts/form/${contact.id}">Upravit</a>
	      <a class="btn btn-mini btn-danger confirm" title="Smazat kontakt ${contact.firstname} ${contact.lastname}?" href="admin/contacts/delete/${contact.id}">Smazat</a> 
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>