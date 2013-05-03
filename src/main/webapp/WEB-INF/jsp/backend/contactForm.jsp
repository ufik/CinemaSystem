<%@include file="header.jsp" %>

<c:choose>
      <c:when test="${contact.id == 0}">
      	<c:set var="postUrl" value="admin/contacts/add" />
      </c:when>
      <c:otherwise>
      	<c:set var="postUrl" value="admin/contacts/udpate" />
      </c:otherwise>
</c:choose>

<form:form method="post" action="${postUrl}" commandName="contact">
<form:hidden path="id" />
	<table>
		<tr>
			<td><form:label path="firstname"><spring:message code="label.contactfirstname"/></form:label></td>
			<td><form:input path="firstname" /><form:errors firstname="firstname" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="lastname"><spring:message code="label.contactlastname"/></form:label></td>
			<td><form:input path="lastname" /><form:errors path="lastname" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="email"><spring:message code="label.contactemail"/></form:label></td>
			<td><form:input path="email" /><form:errors path="email" cssClass="error" /></td>
		</tr>
		<tr>
			<td><form:label path="telephone"><spring:message code="label.contacttelephone"/></form:label></td>
			<td><form:input path="telephone" /><form:errors path="telephone" cssClass="error" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-large btn-success" value="<spring:message code="label.addcontact" />"/>
				<a href="admin/contacts" class="btn btn-large btn-danger">Zrušit</a>
			</td>
		</tr>
	</table>	
</form:form>

<%@include file="footer.jsp" %>