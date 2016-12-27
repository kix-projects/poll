<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
	<jsp:include page="../menu/header.jsp" />

	<body>
		<jsp:include page="../menu/topMenu.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span10">
					<div class="poll">
						<div class="header">
							${poll.subject}
						</div>
						<div class="metainfo">
							<a class="icon-pencil"></a> ${fn:length(poll.candidates)}
							<a class="icon-thumbs-up"></a> ${poll.voteCount}
							<a class="icon-eye-open"></a> ${poll.viewCount}
						</div>
						<div class="content">${poll.content}</div>
						
						<div class="candidates">
							<form:form method="post" modelAttribute="poll" action="/poll/poll/candidate/vote">							
							  <form:radiobuttons element="li" path="candidates" items="${poll.candidates}"/>  
        					 <input class="btn btn-primary" name="commit" type="submit" value="Post Comment">
							</form:form>
						</div>	
						
						<div class="actions">
							<a data-toggle="collapse" data-target="#qcomment" class="icon-comment"></a>
							<a class="icon-thumbs-up" href="<c:url value="/poll/vote?pollId=${poll.id}"/>"></a>
							<div id="qcomment" class="collapse">
								<form method="post" action="<c:url value="/poll/comment/add"/>">
								  <fieldset>
								    <label><b>Your Comment</b></label>
								    <input name="pollId" type="hidden" value="${poll.id}">
								    <textarea class="text required span12" id="content" name="content" rows="5" ></textarea>
								    <input class="btn btn-primary" name="commit" type="submit" value="Vote">
								  </fieldset>
								</form> 
							</div>
						</div>
						<div class="userinfo">
							by <b>${poll.createdBy.firstName}</b>
							<i>(<fmt:formatDate value="${poll.createdDate}" pattern="dd-MM-yyyy" />)</i>
						</div>				
						
						<c:forEach items="${poll.comments}" var="comment">
							<div class="comment">
								${comment.content} - <i>by ${comment.createdBy.firstName}</i> 
								<i>(<fmt:formatDate value="${comment.createdDate}" pattern="dd-MM-yyyy" />)</i>
							</div>
						</c:forEach>					
					</div>
					
				</div><!--/span-->
				<jsp:include page="../menu/rightMenu.jsp" />
			</div><!--/row-->
			<hr>
			<jsp:include page="../menu/footer.jsp" />
		</div><!--/.fluid-container-->
		<jsp:include page="../menu/includeScripts.jsp" />
	</body>
</html>