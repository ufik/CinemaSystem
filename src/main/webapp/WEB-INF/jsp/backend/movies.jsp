<%@include file="header.jsp" %>

<a href="admin/movies/form" class="btn btn-large">Přidat film</a>
<a href="#" id="opener" class="btn btn-large">Přidat film pomocí csfd</a>
<br /><br />

<div id="dialog">
	<input style="height: 40px;" type="search" id="searchCsfd" placeholder="<spring:message code="label.moviesearch"/>" />
	   <form:form method="post" action="admin/movies/add" commandName="movie">
		   <table>
			<tr>
				<th>	   
					<form:label path="csfdId"><spring:message code="label.moviename"/>:</form:label>
				</th>
				<td>
					<form:hidden id="csfdId" path="csfdId" /><span id="movieTitle"></span>
				</td>
			</tr>
			<tr>
				<th>	
					<form:label path="price"><spring:message code="label.movieprice"/>:</form:label>
					</th>
				<td>
					<form:input style="width: 60px;" id="price" path="price" />
				</td>
			</tr>
		   	<tr>
				<th colspan="2" style="text-align: left;">
		   			<input type="submit" class="btn btn-large btn-success" value="<spring:message code="label.addmovie" />"/>
		   		</th>
		   </tr>
		   </table>
	   </form:form>
</div>

<table class="datagrid">
	<tr>
	   	<th>Název</th>
	   	<th>Cena</th>
	   	<th>Hodnocení</th>
	   	<th>Délka</th>
	   	<th>Rok výroby</th>
	   	<th class="controll">Úpravy</th>
    </tr>
   
  <c:forEach var="movie" items="${movies}">
    <tr>
      <td><a class="btn btn-mini btn-warning" title="Upravit film?" href="admin/movies/form/${movie.id_movie}">${movie.name}</a></td>
      <td>${movie.price}</td>
      <td>${movie.rating}%</td>
      <td>${movie.length}</td>
      <td>${movie.year}</td>
      <td>
	      <a class="btn btn-mini btn-warning" title="Upravit film?" href="admin/movies/form/${movie.id_movie}">Upravit</a>
	      <a class="btn btn-mini btn-danger confirm" title="Smazat film?" href="admin/movies/delete/${movie.id_movie}">Smazat</a> 
	  </td>
    </tr>
  </c:forEach>
</table>

<%@include file="footer.jsp" %>