<%@include file="header.jsp" %>

<c:choose>
      <c:when test="${reservationForm.id_reservation == 0}">
      	<c:set var="postUrl" value="admin/reservations/add" />
      </c:when>
      <c:otherwise>
      	<c:set var="postUrl" value="admin/reservations/udpate" />
      </c:otherwise>
</c:choose>

<form:form method="post" action="${postUrl}" modelAttribute="reservationForm">
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
				<input type="search" id="searchProgram" list="programList" autocomplete="on" />
				<datalist id="programList">
					<c:forEach items="${program}" var="p">
						<option value="${p.movie.name} ${p.hall.name} ${p.date} #${p.id_program}" />
					</c:forEach>
				</datalist>
				
				<form:errors path="programItems" cssClass="error" />
			</td>
		</tr>		
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-large btn-success" value="<spring:message code="label.addreservation" />"/>
				<a href="admin/reservations" class="btn btn-large btn-danger">Zrušit</a>
			</td>
		</tr>
	</table>	
</form:form>

<%@include file="footer.jsp" %>