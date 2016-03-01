<%@page import="directory.model.Person"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<title>Admin Login</title>
</head>
<body>
	<form action="admin/login">
		<label for="id">Identification : </label><input type="text" name="id" id="id" />
		<br />
		<label for="password">Password : </label><input type="password" name="password" id="password" />
		<br />
		<input type="submit" value="Log In" class="button"/>
	</form>
</body>
</html>