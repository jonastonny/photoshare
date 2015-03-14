<%@ taglib prefix="image" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<image:wrap title="PhotoShare - Image">
	<h1>Image</h1>
	<!-- Hvis du er ejeren af billedet, så vis billedet. Lige nu viser jeg billeder som brugerid=1 må se -->
	<c:choose>
		<c:when test="<!-- ${imageOwner} -->">
			<img src="<!-- ${imageURL} -->">
		</c:when>
		<c:otherwise>Intet at vise her</c:otherwise>
	</c:choose>
	<br>
	Testbillede fra db:<br>
	<img src="${image.getURL()}">
	<p>${image.getDescription()}</p>
</image:wrap>