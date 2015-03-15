<%@ taglib prefix="image" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<image:wrap title="PhotoShare - Image">
	<h1>Image</h1>
	<div class="container" align="center">
		<img src="${image.getURL()}">
		<p>${image.getDescription()}</p>
	</div>
</image:wrap>