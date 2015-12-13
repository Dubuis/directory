<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<title>Registration</title>
</head>

<body>
	<form:form commandName="person">
		<table class="registration">
			<tr>
				<td>Id :</td>
				<td><form:input path="id" /></td>
				<td><form:errors path="id" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Family Name :</td>
				<td><form:input path="familyName" /></td>
				<td><form:errors path="familyName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>First Name :</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>WebSite :</td>
				<td><form:input path="webSite" /></td>
				<td><form:errors path="webSite" cssClass="error" /></td>
			</tr>
			<tr>
				<td>BirthDate :</td>
				<td><form:input path="birthDate" /></td>
				<td><form:errors path="birthDate" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><form:input path="password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit"  class="button"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>