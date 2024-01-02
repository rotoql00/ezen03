<!--
filename: find_id.jsp
description: 아이디 찾기 결과 페이지 입니다. 추가로 css 적용 예정
author: 임채린
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>아이디 찾기 | TIE SHOE</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <!-- CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/index.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/findID.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/inc/AJAX_helper/ajax_helper.css" />
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <!-- CONTAINER -->
    <div class="uk-section uk-section-muted uk-preserve-color">
        <div class="uk-container">
            <div class="uk-panel uk-margin-medium uk-text-bold uk-text-center">
                <span uk-icon="icon: file-edit; ratio:2"></span><uk-h1>아이디 찾기</uk-h1>
            </div>
         <!-- 아이디 찾기 결과 -->
            <div class="uk-card uk-card-default uk-card-body uk-width-1-2@m uk-align-center">
            <div class="uk-card-header">
            <div class="uk-grid-small uk-flex-middle" uk-grid>
            <div class="uk-width-auto">
             <div class="uk-width-expand">
                <p class="uk-text-bold">${output.userName}/${output.userEmail}으로 찾은 아이디입니다.</p>
                    	<p>개인정보 보호를 위해 아이디는 8자리로, 일부는 *로 표기됩니다.</p>
            </div>
        </div>
    </div>
    </div>
    <div class="uk-card-body uk-text-center">
                <p>${output.userId}</p>
    </div>
    </div>
        <!-- 로그인 페이지로 이동하기 버튼 -->
        <div class="uk-margin-medium-top uk-margin-large-bottom" id="find_id_login">
                <a class="uk-button uk-button-secondary uk-width-medium uk-align-center" id="login_button" href="login">로그인 하러가기</a>
        </div>
    </div>
    </div>
    <!-- FOOTER -->
    <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
    <!-- 스크립트 구간-->
    <script src="<%=request.getContextPath()%>/assets/js/login.js"></script>
    <!-- validate 플러그인 -->
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
    <!-- ajax-helper -->
    <script src="<%=request.getContextPath()%>/inc/AJAX_helper/ajax_helper.js"></script>
    <!-- ajaxForm-->
    <script src="<%=request.getContextPath()%>/assets/jquery.form.min.js"></script>
    <!-- 카카오 로그인  -->
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <!-- 공통속성 common -->
    <script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
    <!-- UIkit icon -->
    <script src="uikit/dist/js/uikit-icons.min.js"></script>
    <!-- sweetalert2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</body>

</html>