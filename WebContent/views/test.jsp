<%@ taglib prefix="index" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<index:wrap title="PhotoShare">

<h1>Photoshare 2000</h1>

<c:if test="${msg != null}"><div class="alert alert-success">${msg}</div></c:if>
<c:if test="${error != null}"><div class="alert alert-warning">${error}</div></c:if>


<h3>Hej med jer...</h3>
<img src="http://localhost:8080/photoshare/image?id=1">


	<c:if test="${comments != null}">
		<table class="table">
			<tr><th>User</th><th>Comment</th></tr>
			<c:forEach var="comments" items="${comments}">
				<tr>
					<td>
					<p>${comments.userId}</p>
					</td>
					<td>
					<p>${comments.body}</p>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<div>
		<form method="POST" action="comment">
			<textarea class="form-control" name="comment" rows="3" placeholder="Leave a comment..."></textarea>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
	
	



</index:wrap>