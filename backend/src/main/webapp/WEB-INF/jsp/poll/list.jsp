<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
	<jsp:include page="../menu/header.jsp" />
	<body>
		<jsp:include page="../menu/topMenu.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
		        <div class="span10">
		        	<div class="header">Polls</div>
		        	<c:forEach items="${polls}" var="poll">
		        		<div class="polllist">
			        		<div class="subject">
								<a href="<c:url value="/poll/view/${poll.id}"/>">${poll.subject}</a>
							</div>
							<div class="metainfo">
								<a class="icon-thumbs-up"></a> ${poll.voteCount} &nbsp;
								<a class="icon-eye-open"></a> ${poll.viewCount}
							</div>
							<div class="tags">
								<c:forEach items="${poll.tags}" var="tag">
									<a href="<c:url value="/tags/view?name=${tag}"/>">${tag}</a>
								</c:forEach>
							</div>
							<div class="userinfo">
								by <b>${poll.createdBy.firstName}</b>
								<i>(<fmt:formatDate value="${poll.createdDate}" pattern="dd-MM-yyyy" />)</i>
							</div>
	        			</div>
					</c:forEach>
					<div class="spacer"> &nbsp; </div>
					<div class="pagination pagination-centered">
						<ul>
							<li> <a href="<c:url value="/${pageInfo.firstPage}"/>"> &laquo; &nbsp; First </a> </li>
							<li> <a href="<c:url value="/${pageInfo.previousPage}"/>"> &lt; &nbsp; Previous </a> </li>
							<li> <a href="<c:url value="/${pageInfo.nextPage}"/>"> Next &nbsp; &gt; </a> </li>
							<li> <a href="<c:url value="/${pageInfo.lastPage}"/>"> Last &nbsp; &raquo; </a> </li>
						</ul>
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