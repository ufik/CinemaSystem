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
      <td><a class="btn btn-mini btn-warning" title="Upravit kontakt ${contact.firstname} ${contact.surname}?" href="admin/contacts/form/${contact.id_contact}">${contact.firstname} ${contact.surname}</a></td>
      <td>${contact.email} ${contact.telephon}</td>
      <td>
	      <a class="btn btn-mini btn-warning" title="Upravit kontakt ${contact.firstname} ${contact.surname}?" href="admin/contacts/form/${contact.id_contact}">Upravit</a>
	      <a class="btn btn-mini btn-danger confirm" title="Smazat kontakt ${contact.firstname} ${contact.surname}?" href="admin/contacts/delete/${contact.id_contact}">Smazat</a> 
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>