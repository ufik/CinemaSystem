<%@include file="header.jsp" %>

<a href="admin/movies/form" class="btn btn-large">Přidat film</a><br /><br />

<table class="datagrid">
	<tr>
	   	<th>Název</th>
	   	<th>Cena</th>
	   	<th class="controll">Úpravy</th>
    </tr>
   
  <c:forEach var="movie" items="${movies}">
    <tr>
      <td><a class="btn btn-mini btn-warning" title="Upravit film ${movie.name}?" href="admin/movies/form/${movie.id_movie}">${movie.name}</a></td>
      <td>${movie.price}</td>
      <td>
	      <a class="btn btn-mini btn-warning" title="Upravit film ${movie.name}?" href="admin/movies/form/${movie.id_movie}">Upravit</a>
	      <a class="btn btn-mini btn-danger confirm" title="Smazat film ${movie.name}?" href="admin/movies/delete/${movie.id_movie}">Smazat</a> 
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>