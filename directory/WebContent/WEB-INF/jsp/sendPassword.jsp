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
	<p>Enter your Id or your email to receive for a password.</p>
	<form>
		<input type="hidden" name="modified" value=true />
		<label for="mail">Mail : </label>
		<input type="text" name="mail" />
		<br />
		<label for="id">ID : </label>
		<input type="text" name="id" />
		<br />
		<input type="submit" value="Send Password" class="button"/>
	</form>
</body>
</html>