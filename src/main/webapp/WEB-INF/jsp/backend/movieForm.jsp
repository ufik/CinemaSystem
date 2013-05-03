<%@include file="header.jsp" %>

<c:choose>
      <c:when test="${movie.id_movie == 0}">
      	<c:set var="postUrl" value="admin/movies/add" />
      </c:when>
      <c:otherwise>
      	<c:set var="postUrl" value="admin/movies/udpate" />
      </c:otherwise>
</c:choose>

<div class="flash info">
	<spring:message code="movieinfo"/>
</div>

<form:form method="post" action="${postUrl}" commandName="movie">
<form:hidden path="id_movie" />
	<table>
		<tr>
			<td><form:label path="csfdId"><spring:message code="label.moviecsfdId"/></form:label></td>
			<td><form:input path="csfdId" /><form:errors path="csfdId" cssClass="error" /></td>
		</tr>
		<tr>
			<td><form:label path="price"><spring:message code="label.movieprice"/></form:label></td>
			<td><form:input path="price" /><form:errors path="price" cssClass="error" /></td>
		</tr>
		<tr>
			<td><form:label path="name"><spring:message code="label.moviename"/></form:label></td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td><form:label path="actors"><spring:message code="label.movieactors"/></form:label></td>
			<td><form:input path="actors" /></td>
		</tr>
		<tr>
			<td><form:label path="countries"><spring:message code="label.moviecountries"/></form:label></td>
			<td><form:input path="countries" /></td>
		</tr>
		<tr>
			<td><form:label path="description"><spring:message code="label.moviedescription"/></form:label></td>
			<td style="width: 90%"><form:textarea style="width: 100%; height: 200px;" path="description" /></td>
		</tr>
		<tr>
			<td><form:label path="genres"><spring:message code="label.moviegenres"/></form:label></td>
			<td><form:input path="genres" /></td>
		</tr>
		<tr>
			<td><form:label path="length"><spring:message code="label.movielength"/></form:label></td>
			<td><form:input path="length" /></td>
		</tr>
		<tr>
			<td><form:label path="poster"><spring:message code="label.movieposter"/></form:label></td>
			<td><form:input path="poster" /></td>
		</tr>
		<tr>
			<td><form:label path="rating"><spring:message code="label.movierating"/></form:label></td>
			<td><form:input path="rating" /></td>
		</tr>
		<tr>
			<td><form:label path="year"><spring:message code="label.movieyear"/></form:label></td>
			<td><form:input path="year" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-large btn-success" value="<spring:message code="label.addmovie" />"/>
				<a href="admin/movies" class="btn btn-large btn-danger">Zrušit</a>
			</td>
		</tr>
	</table>	
</form:form>

<%@include file="footer.jsp" %>