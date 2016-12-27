<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="vp-poll-list-header">
    <a href="#" class="active">Популярное</a>
    <a href="#">Новое</a>
    <a href="#">Обсуждаемое</a>
    <strong></strong>
</div>
   
<div class="vp-poll-list">
<c:forEach items="${polls}" var="poll">
		<c:set var="poll" value="${poll}" scope="request"></c:set>	
		<jsp:include page="./poll-list/item.jsp"/>	
</c:forEach>
</div>

<div>
 <jsp:include page="./loader.jsp"/>
</div>