<div class="logged">
	<p><c:out value="${logged.id}"/>
	<br /><c:out value="${logged.firstName}"></c:out>
	<br /><c:out value="${logged.familyName}"></c:out>
	</p>
	<form action="logout">
		<input type="submit" value="Logout" class="button"/>
	</form>
	<form action="editPassword">
		<input type="hidden" value="${logged.id}" name="id" />
		<input type="submit" value="Change Password" class="button"/>
	</form>
</div>