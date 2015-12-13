<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<title>Edit or Create Person</title>
</head>
<%@ include file="/WEB-INF/jsp/include/top.jsp"%>
<body>
	<form:form method="POST" commandName="person">
	<input type="hidden" value=true name="modified">
		<table>
			<tr>
				<td>Id :</td>
				<td><form:input path="id" readonly="true"/></td>
				<td><form:errors path="id" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Family Name :</td>
				<td><form:input path="familyName"/></td>
				<td><form:errors path="familyName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>First Name :</td>
				<td><form:input path="firstName"/></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><form:input path="email"/></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>WebSite :</td>
				<td><form:input path="webSite"/></td>
				<td><form:errors path="webSite" cssClass="error" /></td>
			</tr>
			<tr>
				<td>BirthDate :</td>
				<td><form:input path="birthDate"/></td>
				<td><form:errors path="birthDate" cssClass="error" /></td>
			</tr>
			<!--  <tr>
				<td>Password :</td>
				<td><form:password path="password"/></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr> -->
		</table>
		<input type="submit" value="Save Informations" class="button"/>
	</form:form>
	<!-- 
	<form>
		<input type="hidden" name="idPerson" value="${person.id}" />
		<table>
			<tr>
				<td>Group(s) :</td>
				<td>
					<select multiple name="groups">
						<option value="-1" selected />
						<c:forEach items="${groups}" var="group">
							<c:set var="bool">false</c:set>
							<c:forEach items="${group.list}" var="p">
								<c:if test="${p.id == person.id}">
									<c:set var="bool">true</c:set>
								</c:if>
							</c:forEach>
							<c:choose>
								 <c:when test="${bool}">
								 	<option value="${group.id}" selected><c:out value="${group.name}"></c:out></option>
								 </c:when>
								<c:otherwise>
									<option value="${group.id}"><c:out value="${group.name}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Update Group and Back" />
				</td>
			</tr>
		</table>
	</form>
	-->
</body>
</html>