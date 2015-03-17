<%@ taglib prefix="image" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<image:wrap title="Image">
<div class="row">
	<c:if test="${error != null}"><div class="alert alert-warning">${error}</div></c:if>
	<c:if test="${msg != null}"><div class="alert alert-warning">${msg}</div></c:if>
	
	<c:if test="${image != null}">
	<h1 style="text-align:center">${image.description}</h1>
		
			<img src="${image.url}" class="img-responsive center-block" alt="Responsive image"><br>
	</c:if>
</div>
<div class="row">
	<div class="col-md-8">
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
					<label>New comment</label>
					<textarea class="form-control" name="comment" rows="3" placeholder="Leave a comment..."></textarea>
					<input type="hidden" value="${param.id}" name="id"><br>
					
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</c:if>
	</div>
	
	<div class="col-md-4">
	<c:choose>
		<c:when test="${image.owner eq user.id }">
			<!-- Hvis brugeren er ejeren af billedet, så... -->
			<form method="POST" action="share">
				<div class="form-group">
					<input type="hidden" value="${param.id}" name="id">
					<label for="username">Share image with user:</label>
					<input class="form-control" type="text" name="username" placeholder="Username">
				</div>
					<button type="submit" class="btn btn-default">Share</button>
			</form>
		</c:when>
		<c:otherwise>
			<div class="text-center">You can only share you own images!</div>
		</c:otherwise>
	</c:choose>
	</div>
</div>
</image:wrap>