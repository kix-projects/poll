<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
  <jsp:include page="../menu/header.jsp" />

  <body>
  	<div class="container">
		<c:if test="${not empty message}">
			<div class="span6 offset2 alert">${message}</div>
		</c:if>
  		<div class="span6 offset2 well">
	  		<form class="form-horizontal" action="login" method="post">
			  <legend>Sign In</legend>
			  <div class="control-group">
			    <label class="control-label" for="username">Username</label>
			    <div class="controls">
			      <input type="text" id="username" name="username" placeholder="User Name">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="password">Password</label>
			    <div class="controls">
			      <input type="password" id="password" name="password" placeholder="Password">
			    </div>
			  </div>
			  <div class="control-group">
			    <div class="controls">
			      <button type="submit" class="btn btn-primary">Sign in</button>
			      <button type="submit" class="btn btn-danger">Reset</button>
			    </div>
			  </div>
			  <div class="control-group">
			    <div class="controls">
			      Not a User? <a href="<c:url value="/users/add"/>">Register Here</a>
			    </div>
			  </div>
			</form>
  		</div>
  	</div>
	
	<!-- Placed at the end of the document so the pages load faster -->
    <jsp:include page="../menu/includeScripts.jsp" />
</body>
</html>