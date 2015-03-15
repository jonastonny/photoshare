<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-fixed-top">
     <div class="container">
       <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
           <span class="sr-only">Toggle navigation</span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="${pageContext.request.contextPath}">PhotoShare</a>
         
       </div>
       <div id="navbar" class="collapse navbar-collapse">
         <ul class="nav navbar-nav">
         <c:choose>
         	<c:when test="${user eq null}"> <!-- NOT LOGGED IN -->
           		<li><a href="login">Login</a></li>
		   		<li><a href="signup">Sign Up</a></li>
         	</c:when>
         	<c:otherwise>
         		<li><a href="logout">Logout</a></li> <!-- LOGGED IN -->
         	</c:otherwise>
         </c:choose>
         </ul>
       </div><!--/.nav-collapse -->
     </div>
</nav>