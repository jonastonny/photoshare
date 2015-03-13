<%@ taglib prefix="login" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<login:wrap title="PhotoShare - Login">
<h1>Login</h1>

<c:if test="${msg != null}"><div class="alert alert-warning">${msg}</div></c:if>
<c:if test="${error != null}"><div class="alert alert-success">${error}</div></c:if>

<div class="form-group">
	<form method="POST" action="login">
		<label for="email">Email:</label>
		<input type="email" id="email" class="form-control" placeholder="Enter email" name="email"><br>
		<label for="password">Password:</label>
		<input type="password" id="password" class="form-control" placeholder="Enter password" name="password"><br>
		<input type="submit" value="Login" class="btn btn-default"> or <a href="createuser">Create New User</a>
	</form>
</div>
</login:wrap>