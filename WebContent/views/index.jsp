<%@ taglib prefix="index" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<index:wrap title="PhotoShare">

<div class="row">
	<c:if test="${msg != null}"><div class="alert alert-success">${msg}</div></c:if>
	<c:if test="${error != null}"><div class="alert alert-danger">${error}</div></c:if>
	
	<h3>My Pictures</h3>
	<c:choose>
	<c:when test="${images.size() ne 0}">
		<c:forEach var="image" items="${images}">
			<div class="col-md-4"><a href="view?id=${image.id}"><img src="image?id=${image.id}" class="img-responsive center-block" title="${image.description}" alt="${image.description}"></a></div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		You have no pictures
	</c:otherwise>
	</c:choose>
</div>
<div class="row">
	<h3>Pictures shared with me</h3>
	<c:choose>
	<c:when test="${imagesSharedWithMe.size() ne 0}">
	<c:forEach var="image" items="${imagesSharedWithMe}">
		<div class="col-md-4"><a href="view?id=${image.id}"><img src="image?id=${image.id}" class="img-responsive center-block" title="${image.description}" alt="${image.description}"></a></div>
	</c:forEach>
	</c:when>
	<c:otherwise>
		No pictures have been shared with you.
	</c:otherwise>
	</c:choose>
</div>

</index:wrap>