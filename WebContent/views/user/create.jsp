<%@ taglib prefix="login" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<login:wrap title="Sign Up">
<h1>Sign Up</h1>

<c:if test="${msg != null}"><div class="alert alert-success">${msg}</div></c:if>
<c:if test="${error != null}"><div class="alert alert-warning">${error}</div></c:if>

<div class="form-group">
	<form method="POST" action="login">
		
		<label for="username">Email:</label>
		<input type="text" id="username" class="form-control" placeholder="Enter username" name="username"><br>
		
		<label for="password">Password:</label>
		<input type="password" id="password" class="form-control" placeholder="Enter password" name="password"><br>
		
		<input type="submit" value="Login" class="btn btn-default"> or <a href="createuser">Create New User</a>
	
	</form>
</div>
</login:wrap>