<header>
	<img src="/WEB-INF/img/Logo.png" />
	<c:choose>
	<c:when test="${sessionScope.logged.id != 0}">
		<%@ include file="/WEB-INF/jsp/include/logged.jsp"%>
	</c:when>
	<c:when test="${sessionScope.logged.id == 0}">
		<%@ include file="/WEB-INF/jsp/include/connection.jsp"%>
	</c:when>
	</c:choose>
</header>