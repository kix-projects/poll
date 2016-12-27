<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <meta charset="utf-8" />
        <!--[if lt IE 9]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
        <title>New Poll - VUPOINT.kz</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href='<c:url value="/css/style.css"/>' rel="stylesheet">
    </head>

    <body>

    <div class="vp-wrapper">

        <div class="vp-middle">

            <div class="vp-container">
                <div class="vp-content">
                    <a href="./list">VUPOINT.kz</a>
                    <main class="vp-main-place">
                        <jsp:include page="./partials/newpoll.jsp"/>
                    </main>
                </div><!-- .content -->
            </div><!-- .container-->

            <aside class="vp-right-sidebar">
                <div class="vp-enter-n-profile">
                    <a href="./enter" class="vp-button vp-button-enter">Войти</a>
                    <div class="vp-sidebar-place" >
                    <jsp:include page="./partials/right-sidebar/search.jsp" />
                        <jsp:include page="./partials/right-sidebar/button-newpoll.jsp" />
                        <jsp:include page="./partials/right-sidebar/comment-of-day.jsp" />
                        <jsp:include page="./partials/right-sidebar/categories.jsp" />
                    </div>
                </div>
            </aside><!-- .right-sidebar -->

        </div><!-- .middle-->

    </div><!-- .wrapper -->

    <footer class="vp-footer">
        <div class="vp-counter"></div>
    </footer><!-- .footer -->
   <jsp:include page="../menu/includeScripts.jsp" />
    </body>
</html>