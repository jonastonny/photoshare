<%@ taglib prefix="login" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<login:wrap title="Sign Up">
<h1>Sign Up</h1>

<c:if test="${msg != null}"><div class="alert alert-success">${msg}</div></c:if>
<c:if test="${error != null}"><div class="alert alert-warning">${error}</div></c:if>

<div class="form-group">
	<form method="POST" action="signup">
		
		<input type="text" id="username" class="form-control" placeholder="Enter username" name="username"><br>

		<input type="password" id="password" class="form-control" placeholder="Enter password" name="password"><br>
		
		<input type="password" id="confirm-password" class="form-control" placeholder="Confirm password" name="confirm-password"><br>
		
		<input type="submit" value="Sign Up" class="btn btn-success">
	
	</form>
</div>
</login:wrap>