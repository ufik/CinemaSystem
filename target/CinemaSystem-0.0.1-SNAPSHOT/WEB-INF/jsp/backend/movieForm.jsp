<%@include file="header.jsp" %>

<c:choose>
      <c:when test="${movie.id_movie == 0}">
      	<c:set var="postUrl" value="admin/movies/add" />
      </c:when>
      <c:otherwise>
      	<c:set var="postUrl" value="admin/movies/udpate" />
      </c:otherwise>
</c:choose>

<form:form method="post" action="${postUrl}" commandName="movie">
<form:hidden path="id_movie" />
	<table>
		<tr>
			<td><form:label path="name"><spring:message code="label.moviename"/></form:label></td>
			<td><form:input path="name" /><form:errors path="name" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="length"><spring:message code="label.movielength"/></form:label></td>
			<td><form:input path="length" /><form:errors path="length" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="price"><spring:message code="label.movieprice"/></form:label></td>
			<td><form:input path="price" /></td>
		</tr>
		<tr>
			<td><form:label path="description"><spring:message code="label.moviedescription"/></form:label></td>
			<td><form:textarea path="description" /></td>
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