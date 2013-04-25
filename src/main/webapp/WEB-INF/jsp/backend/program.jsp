<%@include file="header.jsp" %>

<a href="admin/program/form">Přidat položku programu</a>

<table class="datagrid">
	<tr>
	   	<th>Film</th>
	   	<th>Sál</th>
	   	<th>Datum a čas</th>
	   	<th>Úpravy</th>
    </tr>
    
  <c:forEach var="program" items="${program}">
    <tr>
      <td>${program.movie.name}</td>
      <td>${program.hall.name}</td>
      <td>${program.date}</td>
      <td>
	      <a href="admin/program/form/${program.id_program}">Upravit</a>
	      <a class="confirm" href="admin/program/delete/${program.id_program}">Smazat</a> 
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>