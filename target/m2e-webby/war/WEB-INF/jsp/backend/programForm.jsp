<%@include file="header.jsp" %>

<c:choose>
      <c:when test="${empty program.date}">
      	<c:set var="postUrl" value="admin/program/add" />
      </c:when>
      <c:otherwise>
      	<c:set var="postUrl" value="admin/program/udpate" />
      </c:otherwise>
</c:choose>

<form:form method="post" action="${postUrl}" commandName="program">
	<table>
		<tr>
			<td><form:label path="date"><spring:message code="label.programdate"/></form:label></td>
			<td><form:input path="date" /><form:errors path="date" cssClass="error" /></td>
		</tr>	
		<tr>
			<td><form:label path="movie"><spring:message code="label.programmovie"/></form:label></td>
			<td>
			<spring:bind path="movie">
			<form:select path="movie">
				<form:options items="${movies}" itemValue="name" itemLabel="name"/>
			</form:select><form:errors path="movie" cssClass="error" />
			</spring:bind>
			<!--<form:input  path="movie" list="movies" /><form:errors path="movie" cssClass="error" />
				<datalist id="movies">
				   <c:forEach var="m" items="${movies}">
				   	 <option value="${m.name}">${m.name}</option>
				   </c:forEach>
				 </datalist>-->
			</td> 
		</tr>
		<tr>
			<td><form:label path="hall"><spring:message code="label.programhall"/></form:label></td>
			<td>
				<form:select path="hall">
				<form:options items="${halls}" itemValue="id_hall" itemLabel="name"/>
			</form:select><form:errors path="hall" cssClass="error" />
				<!--<form:input path="hall" list="halls" />
				<datalist id="halls">
				   <c:forEach var="h" items="${halls}">
				   	 <option value="${h.name}">${h.name}</option>
				   </c:forEach>
				 </datalist>-->
			</td>
		</tr>		
		<tr>
			<td colspan="2">
				<input type="submit" value="<spring:message code="label.addprogram" />"/>
			</td>
		</tr>
	</table>	
</form:form>

<%@include file="footer.jsp" %>