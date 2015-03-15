<%@ taglib prefix="image" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<image:wrap title="PhotoShare - Image">
	<h1>Image</h1>
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
</image:wrap>