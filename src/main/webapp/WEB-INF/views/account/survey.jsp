<!--
@filename : survey.jsp
@description : 추가 설문 조사를 위한 jsp 입니다.
@author : 최성준
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>설문조사 페이지</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <!-- join.css -->
    <!-- <link rel="stylesheet"
        href="assets/css/join.css"> -->
    <!-- UIkit CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/index.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/common.css">
    <!-- Your platform (jquery) scripts. -->
    <link href="https://surveyjs.azureedge.net/1.1.29/survey.css"
          type="text/css" rel="stylesheet"/>
    <!-- survey.css -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/survey.css">
    <link rel="icon"
          href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
</head>

<body>
<!-- HEADER -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<!-- 로그인 상태 확인 -->
<c:if test="${empty sessionScope.loginID}">
    <script>
        alert("회원님은 현재 로그아웃 상태입니다.");
        location.href = "<%=request.getContextPath()%>/login";
    </script>
</c:if>
<c:if test="${sessionScope.UserForm eq 'Y'}">
    <script>
        alert("설문에 이미 참여 하셨습니다.");
        location.href = "<%=request.getContextPath()%>/";
    </script>
</c:if>

<!-- SURVEY PAGE -->
<!-- 설문조사 전체 영역 -->
<div class="container">
    <!-- 설문조사 영역-->
    <div class="surveyBox">
        <!-- 설문조사 입력 영역 -->
        <div id="surveyContainer"></div>
    </div>
</div>
<!-- FOOTER -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>

<!-- 공통속성 common -->
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
        src="https://surveyjs.azureedge.net/1.1.29/survey.jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/survey.js"></script>

</body>
</html>