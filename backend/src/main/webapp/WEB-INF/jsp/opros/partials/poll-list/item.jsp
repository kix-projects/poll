<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="vp-poll-list-item">
    <div class="vp-header">
        <div class="vp-like">
            <div class="vp-like-counter">9999</div>
            <div class="vp-like-buttons">
                <a href="#" class="vp-like-up"></a>
                <a href="#" class="vp-like-dn"></a>
            </div>
            <div class="vp-like-icon"></div>
        </div>
        <div class="vp-author">
            <div class="vp-photo">
                <div></div>
            </div>
            <strong>${poll.createdBy.firstName}</strong>
            <br>
            <span>продвинутый новичек</span>
        </div>
        <div class="vp-time-n-share">
            <span><fmt:formatDate value="${poll.createdDate}" pattern="HH:mm dd-MM-yyyy" /></span>
            <a href="#" id="vp-share-more">Еще</a>
        </div>
    </div>
    <div class="vp-question">
        <h3>${poll.subject}</h3>
        ${poll.content}        
        <c:if test="${not empty poll.image}">
        <img border="0" src="${poll.image}"/>
        </c:if>
    </div>
    <ul class="vp-answers">
    	<c:forEach items="${poll.candidates}" var="candidate">
        <li><a href="#"><span></span>${candidate.content }</a></li>
        </c:forEach>
    </ul>
     <div class="vp-revote-n-comment">
        <a href="./comment" class="vp-comment-link">
            <strong></strong>
            <span>3125</span>
            комментировать
        </a>
    </div>
</div>