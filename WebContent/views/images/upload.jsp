<%@ taglib prefix="imageUpload" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<imageUpload:wrap title="Upload Image">
<h1>Upload Image</h1>

<c:if test="${error != null}"><div class="alert alert-warning"><p class="msg">${error}</p></div></c:if>

<form action="upload" method="post" enctype="multipart/form-data">
    Select image to upload:<br>
    <input type="file" name="image" id="image">
    <input type="text" name="description" placeholder="Add a description"><br>
    <input type="submit" value="Upload Image" name="upload">
</form>

</imageUpload:wrap>
