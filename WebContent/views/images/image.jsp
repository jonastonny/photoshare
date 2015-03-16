<%@ taglib prefix="image" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<image:wrap title="PhotoShare - Image">
	<h1>Image</h1>
	<c:if test="${error != null}"><div class="alert alert-warning"><p class="error">${error}</p></div></c:if>
	
	<c:if test="${param.id != null}">
		<div class="container" align="center">
			<img src="image?id=${param.id}" class="img-responsive" alt="Responsive image">
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
</image:wrap>