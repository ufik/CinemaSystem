<%@include file="header.jsp" %>

<c:choose>
      <c:when test="${reservation.id_reservation == 0}">
      	<c:set var="postUrl" value="admin/reservations/add" />
      </c:when>
      <c:otherwise>
      	<c:set var="postUrl" value="admin/reservations/udpate" />
      </c:otherwise>
</c:choose>

<form:form method="post" action="${postUrl}" commandName="reservation">
	<table>
		<tr>
			<td><form:label path="firstname"><spring:message code="label.reservationsfirstname"/></form:label></td>
			<td><form:input path="firstname" /><form:errors path="firstname" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="lastname"><spring:message code="label.reservationslastname"/></form:label></td>
			<td><form:input path="lastname" /><form:errors path="lastname" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="email"><spring:message code="label.reservationsemail"/></form:label></td>
			<td><form:input type="email" path="email" /><form:errors path="email" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="telephone"><spring:message code="label.reservationstelephone"/></form:label></td>
			<td><form:input path="telephone" /><form:errors path="telephone" cssClass="error" /></td> 
		</tr>
		<tr>
			<td><form:label path="programItems"><spring:message code="label.reservationsprogramitems"/></form:label></td>
			<td>
				<form:select path="programItems">
				<form:options items="${program}" itemValue="movie.name" itemLabel="movie.name"/>
			</form:select><form:errors path="programItems" cssClass="error" />
			</td>
		</tr>		
		<tr>
			<td colspan="2">
				<input type="submit" value="<spring:message code="label.addreservation" />"/>
			</td>
		</tr>
	</table>	
</form:form>

<%@include file="footer.jsp" %>