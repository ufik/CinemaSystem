<%@include file="header.jsp" %>

<c:choose>
      <c:when test="${hall.id_hall == 0}">
      	<c:set var="postUrl" value="admin/halls/add" />
      </c:when>
      <c:otherwise>
      	<c:set var="postUrl" value="admin/halls/udpate" />
      </c:otherwise>
</c:choose>

<form:form method="post" action="${postUrl}" commandName="hall">
<form:hidden path="id_hall" />
	<table>
		<tr>
			<td><form:label path="name"><spring:message code="label.hallname"/></form:label></td>
			<td><form:input path="name" /><form:errors path="name" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="rows"><spring:message code="label.hallrows"/></form:label></td>
			<td><form:input path="rows" /><form:errors path="rows" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="columns"><spring:message code="label.hallcolumns"/></form:label></td>
			<td><form:input path="columns" /><form:errors path="columns" cssClass="error" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-large btn-success" value="<spring:message code="label.addhall" />"/>
				<a href="admin/halls" class="btn btn-large btn-danger">Zrušit</a>
			</td>
		</tr>
	</table>	
</form:form>

<%@include file="footer.jsp" %>