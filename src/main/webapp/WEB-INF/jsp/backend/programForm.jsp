<%@include file="header.jsp" %>

<c:choose>
      <c:when test="${empty programForm.id_program}">
      	<c:set var="postUrl" value="admin/program/add" />
      </c:when>
      <c:otherwise>
      	<c:set var="postUrl" value="admin/program/udpate" />
      </c:otherwise>
</c:choose>

<form:form method="post" action="${postUrl}" modelAttribute="programForm">
<form:hidden path="id_program" />
	<table>
		<tr>
			<td><form:label path="date"><spring:message code="label.programdate"/></form:label></td>
			<td><form:input path="date" type="datetime" step="1800" /><form:errors path="date" cssClass="error" /></td>
		</tr>	
		<tr>
			<td><form:label path="id_movie"><spring:message code="label.programmovie"/></form:label></td>
			<td>
			<form:select path="id_movie">
				<form:options items="${movies}" itemValue="id_movie" itemLabel="name"/>
			</form:select><form:errors path="id_movie" cssClass="error" />
			</td> 
		</tr>
		<tr>
			<td><form:label path="id_hall"><spring:message code="label.programhall"/></form:label></td>
			<td>
				<form:select path="id_hall">
				<form:options items="${halls}" itemValue="id_hall" itemLabel="name"/>
			</form:select><form:errors path="id_hall" cssClass="error" />
			</td>
		</tr>		
		<tr>
			<td colspan="2">
				<input class="btn btn-large btn-success" type="submit" value="<spring:message code="label.addprogram" />"/>
				<a href="admin/program" class="btn btn-large btn-danger">Zrušit</a>
			</td>
		</tr>
	</table>	
</form:form>

<%@include file="footer.jsp" %>