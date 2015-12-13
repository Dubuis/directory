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
	<form>
		<input type="hidden" name="modified" value=true />
		<input type="hidden" name="id" value="${sessionScope.logged.id}" /	>
		<table>
			<tr>
				<td>Current password :</td>
				<td>
					<input name="currentPassword"/>
				</td>
			</tr>
			<tr>
				<td>New password :</td>
				<td>
					<input name="newPassword"/>
				</td>
			</tr>
			<tr>
				<td>Confirm :</td>
				<td>
					<input name="confirmPassword"/>
				</td>
			</tr>
		</table>
		<input type="submit" value="Save" class="button"/>
	</form>
</body>
</html>