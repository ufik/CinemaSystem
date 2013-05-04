<%@include file="header.jsp" %>

<a href="admin/program/form" class="btn btn-large">Přidat položku programu</a><br /><br />

<table class="datagrid">
	<tr>
	   	<th>Film</th>
	   	<th>Sál</th>
	   	<th>Datum a čas</th>
	   	<th  class="controll">Úpravy</th>
    </tr>
    
  <c:forEach var="program" items="${program}">
    <tr <c:if test="${program.old}">class="old"</c:if>>
      <td><a class="btn btn-mini btn-warning" title="Upravit film?" href="admin/movies/form/${program.movie.id_movie}">${program.movie.name}</a></td>
      <td><a class="btn btn-mini btn-warning" title="Upravit sál ${program.hall.name}?" href="admin/halls/form/${program.hall.id_hall}">${program.hall.name}<a/></td>
      <td><fmt:formatDate value="${program.date}" pattern="dd.MM.yyyy HH:mm" /></td>
      <td>
	      <a class="btn btn-mini btn-warning" href="admin/program/form/${program.id_program}">Upravit</a>
	      <a class="confirm btn btn-mini btn-danger" href="admin/program/delete/${program.id_program}">Smazat</a> 
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>