<%@ tag %>
<%@ attribute name="title" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/favicon.ico" rel="shortcut icon" type="image/x-icon" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		<title>${title}</title>
	</head>
	<body>
	
	<c:import url="/views/nav.jsp" />
    
    <div class="container">
		<jsp:doBody/>
    </div><!-- /.container -->
	</body>
</html>