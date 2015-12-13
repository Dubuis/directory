<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
	<title>Group for person</title>
</head>
<%@ include file="/WEB-INF/jsp/include/top.jsp"%>
<body>
	<form>
		<input type="hidden" name="idPerson" value="${person.id}" />
		<table>
			<tr>
				<td>Group(s) :</td>
				<td>
					<select multiple name="groups">
						<c:forEach items="${groupList}" var="g">
							<c:choose>
								<c:when test="${fn:contains(g.list, person)}">
									<option value="${g.id}" selected><c:out value="${g.name}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${g.id}"><c:out value="${g.name}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Update" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>