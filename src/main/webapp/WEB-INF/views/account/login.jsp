<!--
filename: login.jsp
description: 로그인페이지 입니다. login.css login.js를 참조하고 있습니다.
author: 임채린
-->
<!--
#login_id : 로그인 아이디
#login_pw : 로그인 비밀번호
#login_button : 로그인 버튼
#login_checkbox: 아이디 저장 체크박스
#find_id_name: ID 찾기 이름
#find_id_mail: ID 찾기 이메일
#find_id_button: ID 찾기 버튼
#reset_pw_id: PW 분실 아이디
#reset_pw_mail: PW 분실 이메일
#reset_pw_button: PW 분실 버튼
-->
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
    <title>로그인 | TIE SHOE</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <!-- CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/index.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>

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
<!-- CONTAINER -->
<div class="login_container">
    <div class="login_screen">
        <!--로그인 input form 시작-->
        <form class="uk-form" id="login_form" method="post" action="<%=request.getContextPath()%>/login_ok">
            <fieldset class="uk-fieldset">
                <div class=loginLinkArea>
                    <ul uk-tab>
                        <li class="uk-active uk-align-center"><a href="login.jsp">로그인</a></li>
                    </ul>
                </div>
                <!--아이디 input-->
                <div class="uk-margin">
                    <div class="uk-inline">
                        <span class="uk-form-icon" uk-icon="icon: user"></span> <input
                            class="uk-input uk-width-large uk-text-small" id="login_id" name="userId"
                            value="${cookie.user_check.value}" type="text" placeholder="아이디">
                    </div>
                </div>
                <!-- 경고메시지 -->
                <label class="uk-alert-danger error" for="login_id" generated="true" uk-alert></label>
                <!--비밀번호 input-->
                <div class="uk-margin">
                    <div class="uk-inline">
                        <span class="uk-form-icon" uk-icon="icon: lock"></span> <input
                            class="uk-input uk-width-large uk-text-small" id="login_pw" name="userPw" type="password"
                            placeholder="비밀번호">
                    </div>
                </div>
                <!-- 경고메시지 -->
                <label class="uk-alert-danger error" for="login_pw" generated="true" uk-alert></label>
                <div class="uk-margin">
                    <div class="uk-inline">
                        <!--로그인 버튼-->
                        <button class="uk-button uk-button-secondary uk-width-large" id="login_button" type="submit">
                            로그인
                        </button>
                    </div>
                </div>
                <!--아이디 저장 체크박스-->
                <div class="uk-margin-medium-top uk-grid-small uk-child-width-auto uk-grid">
                    <label> <input class="uk-margin-small-right uk-checkbox" id="remember_userId" name="remember_userId"
                                   value="${checked}" type="checkbox" checked>아이디 저장
                    </label>
                </div>
                <!--아이디 찾기/비밀번호 분실/회원 가입-->
                <ul class="push_log uk-breadcrumb uk-link-text">
                    <li><a href="#modal-group-1" uk-toggle>아이디 찾기</a></li>
                    <li><a href="#modal-group-2" uk-toggle>비밀번호 분실</a></li>
                    <li><a href="join">회원 가입</a></li>
                </ul>
                <div class="socialLoginContainer">
                    <button onclick="MyWindow=window.open('${urlNaver}','MyWindow','width=400,height=650'); return false;"
                            class='btn-social-login' style='background:#1FC700'><i class="xi-2x xi-naver"></i></button>
                    <button onclick="MyWindow=window.open('${urlKakao}','MyWindow','width=400,height=650'); return false;"
                            class='btn-social-login' style='background:#FFEB00'><i
                            class="xi-2x xi-kakaotalk text-dark"></i></button>
                </div>

            </fieldset>
        </form>
        <!--로그인 input form 끝-->
    </div>
</div>
<!--아이디찾기/비밀번호 분실 modal 시작-->
<!--아이디찾기 modal 시작 -->
<div id="modal-group-1" uk-modal>
    <div class="uk-modal-dialog">
        <button class="uk-modal-close-default" type="button" uk-close></button>
        <div class="uk-modal-header">
            <!--탭 설정 -->
            <ul class="uk-child-width-expand" uk-margin uk-tab>
                <li class="uk-disabled"><a href="#modal-group-1">아이디 찾기</a></li>
                <li class="uk-active"><a href="#modal-group-2" uk-toggle>비밀번호 분실</a></li>
            </ul>
        </div>
        <div class="uk-modal-body">
            <form class="uk-margin-auto uk-width-large uk-align-center uk-position-center" method="post"
                  id="find_id_form" action="<%=request.getContextPath()%>/findID">
                <legend class="uk-heading-bullet uk-margin">이름과 이메일을 입력해주세요.</legend>
                <legend> 내 정보의 이메일 주소와 입력한 이메일 주소가 같아야 합니다.</legend>
                <!--이름 input-->
                <div class="uk-margin">
                    <div class="uk-inline">
                        <span class="uk-form-icon" uk-icon="icon: pencil"></span> <input
                            class="uk-input uk-width-large uk-text-small" id="find_id_name" name="userName" type="text"
                            placeholder="이름">
                    </div>
                </div>
                <!-- 경고메시지 -->
                <label class="uk-alert-danger error" for="find_id_name" generated="true" uk-alert></label>
                <!--이메일 input-->
                <div class="uk-margin">
                    <div class="uk-inline">
                        <span class="uk-form-icon" uk-icon="icon: mail"></span>
                        <input class="uk-input uk-width-large uk-text-small" id="find_id_email" name="userEmail"
                               type="text" placeholder="이메일">
                    </div>
                </div>
                <!-- 경고메시지 -->
                <label class="uk-alert-danger error" for="find_id_email" generated="true" uk-alert></label>
        </div>
        <div class="uk-modal-footer">
            <button class="uk-button uk-button-secondary uk-width-medium uk-align-center" id="find_id_button"
                    type="submit">다음 단계
            </button>
        </div>
        </form>
    </div>
</div>
<!--아이디찾기 modal 끝-->
<!--비밀번호 분실 modal -->
<div id="modal-group-2" uk-modal>
    <div class="uk-modal-dialog">
        <button class="uk-modal-close-default" type="button" uk-close></button>
        <div class="uk-modal-header">
            <ul class="uk-child-width-expand" uk-margin uk-tab>
                <li class="uk-active"><a href="#modal-group-1" uk-toggle>아이디 찾기</a></li>
                <li class="uk-disabled"><a href="#modal-group-2">비밀번호 분실</a></li>
            </ul>
        </div>
        <div class="uk-modal-body">
            <form class="uk-margin-auto uk-width-large uk-align-center uk-position-center" method="post"
                  id="reset_pw_form" action="<%=request.getContextPath()%>/reset.do">

                <legend class="uk-heading-bullet uk-margin">아이디와 이메일을 입력해주세요.</legend>
                <legend> 내 정보의 이메일 주소와 입력한 이메일 주소가 같아야 합니다.</legend>
                <!--아이디 input-->
                <div class="uk-margin">
                    <div class="uk-inline">
                        <span class="uk-form-icon" uk-icon="icon: user"></span>
                        <input class="uk-input uk-width-large uk-text-small" id="reset_pw_id" name="user_ID" type="text"
                               placeholder="아이디">
                    </div>
                </div>
                <!-- 경고메시지 -->
                <label class="uk-alert-danger error" for="reset_pw_id" generated="true" uk-alert></label>
                <!--이메일 input-->
                <div class="uk-margin">
                    <div class="uk-inline">
                        <span class="uk-form-icon" uk-icon="icon: mail"></span>
                        <input class="uk-input uk-width-large uk-text-small" id="reset_pw_mail" name="Email" type="text"
                               placeholder="이메일">
                    </div>
                </div>
                <!-- 경고메시지 -->
                <label class="uk-alert-danger error" for="reset_pw_mail" generated="true" uk-alert></label>
        </div>
        <div class="uk-modal-footer">
            <button class="uk-button uk-button-secondary uk-width-medium uk-align-center" id="reset_pw_button"
                    type="submit">다음 단계
            </button>
        </div>
        </form>
    </div>
</div>
<!--아이디찾기/비밀번호 분실 modal 끝-->
<!-- FOOTER -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<!-- 스크립트 구간-->
<script src="<%=request.getContextPath()%>/assets/js/login.js"></script>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>

<!-- 카카오 로그인  -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>


</body>

</html>