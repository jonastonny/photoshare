<%@ taglib prefix="image" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<image:wrap title="PhotoShare - Image">
	<h1>Image</h1>
	
	<c:choose>
		<c:when test="<!-- INDSÆT TRUE FALSE SOMETHING -->">
			<!-- Indsæt billede fra parametre -->
		</c:when>
		<c:otherwise>Intet at vise her</c:otherwise>
	</c:choose>
</image:wrap>