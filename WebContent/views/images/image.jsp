<%@ taglib prefix="image" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<image:wrap title="PhotoShare - Image">
	<h1>Image</h1>
	<!-- Hvis du er ejeren af billedet, så vis billedet -->
	<c:choose>
		<c:when test="<!-- ${imageOwner} -->">
			<!-- Indsæt billede fra parametre herunder -->
			<img src="<!-- ${imageURL} -->">
		</c:when>
		<c:otherwise>Intet at vise her</c:otherwise>
	</c:choose>
</image:wrap>