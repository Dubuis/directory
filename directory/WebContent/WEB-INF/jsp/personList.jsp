<%@page import="directory.model.Person"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<title>List of Persons</title>
</head>
<%@ include file="/WEB-INF/jsp/include/top.jsp"%>
<body>
	<table>
		<tr>
			<c:if test="${sessionScope.logged.id != 0}">
				<th colspan="6">Person</th>
			</c:if>
			<c:if test="${sessionScope.logged.id == 0}">
				<th colspan="3">Person</th>
			</c:if>
			<th rowspan="2" colspan="2">Group</th>
		</tr>
		<tr>
			<th>Id</th>
			<th>Family Name</th>
			<th>First Name</th>
			<c:if test="${sessionScope.logged.id != 0}">
				<th>Email</th>
				<th>WebSite</th>
				<th>BirthDate</th>
			</c:if>
		</tr>
		
		<c:forEach items="${persons}" var="person">
			<tr>
				<!-- Person Definition -->
				<td><c:out value="${person.id}" /></td>
				<td><c:out value="${person.familyName}" /></td>
				<td><c:out value="${person.firstName}" /></td>
				<c:if test="${sessionScope.logged.id != 0}">
					<td><c:out value="${person.email}" /></td>
					<td><c:out value="${person.webSite}" /></td>
					<td><c:out value="${person.birthDate}" /></td>
				</c:if>
				<!-- Group Definition -->
				<td>
				<ul style="list-style-type:none;padding:0;margin:0;">
					<c:forEach items="${groups}" var="group">
						<c:forEach items="${group.list}" var="p">
							<c:if test="${p.id == person.id}"><li><c:out value="${group.name}" /></li></c:if>
						</c:forEach>
					</c:forEach>
				</ul>
				</td>
				<!-- Actions -->
				<td>
					<c:if test="${sessionScope.logged.id == person.id}">
						<form action="editPerson">
							<input type="hidden" name="idPerson" value="${person.id}">
							<input type="submit" value="Edit" class="button">
						</form>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>