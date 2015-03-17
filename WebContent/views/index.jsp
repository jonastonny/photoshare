<%@ taglib prefix="index" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<index:wrap title="PhotoShare">

<h1>Photoshare 2000</h1>

<c:if test="${msg != null}"><div class="alert alert-success">${msg}</div></c:if>
<c:if test="${error != null}"><div class="alert alert-warning">${error}</div></c:if>

	<c:forEach var="images" items="${images}">
		<p><img src="${images.url}"></p>
	</c:forEach>


</index:wrap>