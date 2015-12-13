<%@page import="directory.model.Person"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<title>Admin Management</title>
</head>
<body>
	<form action="logout">
		<input type="submit" value="LOGOUT" class="button">
	</form>
	<table>
		<tr>
			<th colspan="6">Person</th>
			<th colspan="2" rowspan="2">Group
				<br/>
				<form action="addGroup">
					<input type="submit" value="new Group" class="button"/>
				</form>
			</th>
		</tr>
		<tr>
			<th>Id</th>
			<th>Family Name</th>
			<th>First Name</th>
			<th>Email</th>
			<th>WebSite</th>
			<th>BirthDate</th>
		</tr>
		
		<c:forEach items="${persons}" var="person">
			<tr>
				<!-- Person Definition -->
				<td><c:out value="${person.id}" /></td>
				<td><c:out value="${person.familyName}" /></td>
				<td><c:out value="${person.firstName}" /></td>
				<td><c:out value="${person.email}" /></td>
				<td><c:out value="${person.webSite}" /></td>
				<td><c:out value="${person.birthDate}" /></td>
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
					<form action="editPerson">
						<input type="hidden" name="idPerson" value="${person.id}">
						<input type="submit" value="Edit" class="button">
					</form>
					<form action="addToGroup">
						<input type="hidden" name="idPerson" value="${person.id}">
						<input type="submit" value="add Group" class="button">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>