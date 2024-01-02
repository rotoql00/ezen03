<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원가입 페이지</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <!-- join.css -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/join.css">
    <!-- datePicker.css -->
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assets/plugin/air-datepicker/datepicker.min.css">
    <!-- UIkit CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/index.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/naverRegister.css">
    <link rel="icon"
          href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
</head>
<!--
body에서 사용하고 있는 id정리

form id
#signUp_Form : 회원 강비 전체 양식
#User_ID : 회원 아이디
#User_PW : 회원 비밀번호
#User_PWCheck : 회원 비밀번호 재확인
#User_Name : 회원 이름
#User_PhoneNum : 회원 연락처
#User_BirthDate : 회원 생일
#User_Email : 회원 이메일
#zip_code : 회원주소 우편번호
#addr_1 : 회원주소 도로명 주소
#addr_2 : 회원주소 상세주소

-->
<body>
<!-- HEADER -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<!-- Cookie가 비어있지 않을 때 checked 속성을 줌 -->
<c:if test="${not empty cookie.user_check}">
    <c:set value="checked" var="checked"/>
</c:if>
<!-- 로그인 상태 확인 -->
<c:if test="${not empty sessionScope.loginID}">
    <script>
        alert("회원님은 현재 로그인 상태입니다.");
        window.history.back();
    </script>
</c:if>

<!-- JOIN FORM -->
<div class="container">
    <div class="signUp_screen">
        <form method="post" class="uk-form" id="signUp_Form"
              action="${pageContext.request.contextPath}/joinNaverConfirm">
            <fieldset class="uk-fieldset">
                <div class="joinLinkArea">
                    <ul uk-tab>
                        <li class="uk-active uk-align-center"><a href="naverRegister.jsp">간편 회원가입(네이버)</a></li>
                    </ul>
                </div>

                <!-- 핸드폰번호 입력 -->
                <div class="uk-margin uk-position-relative">
                    <div class="uk-inline Phonenum">
                        <span class="uk-form-icon" uk-icon="icon:receiver"></span> <input
                            class="uk-input Phonenum" id="userPhonenum" name="userPhonenum"
                            type="text" maxlength="13" placeholder="핸드폰번호 "
                            onchange="reInputPhonenum();" onkeyup="inputHyphen(this);">
                    </div>
                    <input type="hidden" id="checkPhonenumBtn" value="N">
                    <button class="uk-button uk-button-secondary uk-position-right "
                            type="button" id="checkPhonenum">중복확인
                    </button>
                </div>
                <label class="uk-alert-danger error" for="userPhonenum"
                       generated="true" uk-alert></label>

                <!-- 생년월일 입력 -->
                <div class="uk-margin">
                    <div class="uk-inline">
                        <span class="uk-form-icon" uk-icon="icon:calendar"></span> <input
                            class="uk-input uk-form-width-large datepicker-here enterDetection"
                            id="userBirthDate" name="userBirthDate" type="text"
                            placeholder="생년월일 " readonly>
                    </div>
                </div>
                <label class="uk-alert-danger error" for="userBirthDate"
                       generated="true" uk-alert></label>

                <!-- 회원가입 버튼 -->
                <button id="signUpBtn"
                        class="uk-button uk-button-secondary uk-width-large signUp"
                        type="submit">회원가입
                </button>

            </fieldset>
        </form>
    </div>
</div>

<!-- FOOTER -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- daum 주소찾기 CDN -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- validate plugin CDN-->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/validate.js"></script>
<!-- 공통속성 common -->
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
<!-- air-datepicker -->
<script src="<%=request.getContextPath()%>/assets/plugin/air-datepicker/datepicker.min.js"></script>
<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- join js -->
<script src="<%=request.getContextPath()%>/assets/js/join.js"></script>
</body>
</html>