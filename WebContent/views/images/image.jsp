<%@ taglib prefix="image" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<image:wrap title="Image">
	<h1>Image</h1>
<<<<<<< HEAD
	<div class="container" align="center">
		<img src="${image.getURL()}">
		<p>${image.getDescription()}</p>
		<p>Comments HERE BITCH</p>
	</div>
	<table>
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
=======
	<c:if test="${error != null}"><div class="alert alert-warning"><p class="error">${error}</p></div></c:if>
	
	<c:if test="${image != null}">
		<div class="container" align="center">
			<img src="${image.url}" class="img-responsive" alt="Responsive image">
		<p>${image.description}</p>
		</div>
	</c:if>
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
	
	<c:if test="${user != null}">
		<div>
			<form method="POST" action="createcomment">
				<textarea class="form-control" name="comment" rows="3" placeholder="Leave a comment..."></textarea>
				<input type="hidden" value="${param.id}" name="id">
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
	</c:if>
	
>>>>>>> 8b7d2343ce27649813e981605b5b7a35d6d60196
</image:wrap>