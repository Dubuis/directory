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
	<form:form commandName="group">
		<input type="hidden" name="modified" value=true />
		<table>
			<tr>
				<td>ID : </td>
				<td><form:input path="id"/></td>
				<td><form:errors path="id"/></td>
			</tr>
			<tr>
				<td>Name : </td>
				<td><form:input path="name"/></td>
				<td><form:errors path="name"/></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Save"/></td>
			<tr>
		</table>
	</form:form>
</body>
</html>