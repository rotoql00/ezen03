<%@ page import="com.ezen03.tieshoe.helper.PageData" %>
<%@ page import="sun.reflect.annotation.ExceptionProxy" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>이용안내</title>
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/userGuide.css">


</head>
<body>
<!-- Header -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<div id="container">
    <div id="totaltab">
        <ul class="uk-tab">
            <li><a href="<%=request.getContextPath()%>/board">공지사항</a></li>
            <li><a href="<%=request.getContextPath()%>/faq">FAQ</a></li>
            <li class="uk-active"><a href="<%=request.getContextPath()%>/userGuide">이용안내</a></li>
        </ul>
    </div>

    <div id="Guide">
        <img src="<%=request.getContextPath()%>/assets/img/contents.png">
    </div>
</div>


<!--Footer -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>

<!-- UIkit JS -->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/board.js"></script>
</body>
</html>
