<%@include file="header.jsp" %>

<a href="admin/movies/form">Přidat film</a>

<table class="datagrid">
	<tr>
	   	<th>Název</th>
	   	<th>Cena</th>
	   	<th>Úpravy</th>
    </tr>
  <c:forEach var="movie" items="${movies}">
    <tr>
      <td>${movie.name}</td>
      <td>${movie.price}</td>
      <td>
	      <a href="admin/movies/form/${movie.id_movie}">Upravit</a>
	      <a class="confirm" href="admin/movies/delete/${movie.id_movie}">Smazat</a> 
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>