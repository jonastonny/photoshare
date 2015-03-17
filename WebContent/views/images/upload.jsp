<%@ taglib prefix="imageUpload" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<imageUpload:wrap title="Upload Image">
<div class="row">
	<h1>Upload Image</h1>
	<c:if test="${error != null}"><div class="alert alert-warning"><p class="msg">${error}</p></div></c:if>
	
	<form action="upload" method="post" enctype="multipart/form-data">
	    <h2>Select image to upload:</h2>
	    
	    <input type="file" name="image" id="image"><br>
	    
	    <input type="text" name="description" placeholder="Add a description"><br><br>
	    
	    <input type="submit" value="Upload" class="btn btn-success">
	</form>
</div>

</imageUpload:wrap>
