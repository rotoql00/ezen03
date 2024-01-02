<!--
@filename : registerSuccess.jsp
@description : 회원가입 환영,설문조사 이동 jsp 페이지 입니다..
@author : 임석현
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>

<head>
    <meta charset="UTF-8"/>
    <title>TIESHOE</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <!-- UIkit CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/registerSuccess.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="icon"
          href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
</head>

<body>
<!-- HEADER -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<div class="container">
    <div class="reg_box_container">
        <p class="logo customLogo">TIESHOE</p>
        <p class="topContainer"><span class="userID">${sessionScope.UserName}</span>님, <span style="color: red">회원가입</span>이 완료되었습니다.</p>
        <p class="topContainer">TIESHOE의 다양한 서비스를 마음껏 경험해보세요</p>

        <div class="reg_box_survey">
            <p class="bottom_container">설문에 참여하면 <span style="color:red;">쿠폰</span>이 발급됩니다</p>
            <p class="bottom_container">여기를 클릭해서 설문에 참여해주세요</p>
        </div>

    </div>
    <div class="uk-flex uk-flex-center bottom_tab">
        <button class="uk-button uk-button-secondary uk-button-large">홈으로</button>
    </div>

</div>
<!-- FOOTER -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/validate.js"></script>
<!-- 공통속성 common -->
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
</body>

</html>