<!--
FileName: myPage.html
Description: 개인정보관리.
Author: 임석현
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
    <title>마이페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/myPage.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/checkpass.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/AJAX_helper/ajax_helper.css">
</head>

<body>
<!-- Header -->

<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<div class="container_Change">
    <div>
        <h3 class="title_PW">비밀번호 재설정</h3>
        <p class="center">${sessionScope.user_ID}님,</p>
        <p class="center">비밀번호 재설정을 위해 이메일을 보내드렸습니다.</p>
        <p class="center">확인해 주시기 바랍니다.</p>
        <div class="uk-child-width-1-2@s uk-grid-match center push" uk-grid>
            <div>
                <div class="uk-card uk-card-default uk-card-hover uk-card-body" id="resendEmail">
                    <h3 class="uk-card-title">이메일 재전송</h3>
                    <p>이메일을 받지 못하셨나요?</p>
                    <p>이메일 받지 못하셨다면 여기를 클릭하세요.</p>
                </div>
            </div>
            <div>
                <c:choose>
                    <c:when test="${fn:contains(sessionScope.Email_carrier, 'naver')}">
                        <div class="uk-card uk-card-default uk-card-hover uk-card-body"
                             onclick="window.open('https://www.naver.com/', '_blank')">
                            <h3 class="uk-card-title">NAVER</h3>
                            <p>여기를 클릭해 이메일을 확인해주세요.</p>
                        </div>
                    </c:when>

                    <c:when test="${fn:contains(sessionScope.Email_carrier, 'gmail')}">
                        <div class="uk-card uk-card-default uk-card-hover uk-card-body"
                             onclick="window.open('https://www.google.com/', '_blank')">
                            <h3 class="uk-card-title">Google</h3>
                            <p>여기를 클릭해 이메일을 확인해주세요.</p>
                        </div>
                    </c:when>

                    <c:when test="${fn:contains(sessionScope.Email_carrier, 'daum')}">
                        <div class="uk-card uk-card-default uk-card-hover uk-card-body"
                             onclick="window.open('https://www.daum.net/', '_blank')">
                            <h3 class="uk-card-title">Daum</h3>
                            <p>여기를 클릭해 이메일을 확인해주세요.</p>
                        </div>
                    </c:when>

                    <c:when test="${fn:contains(sessionScope.Email_carrier, 'yahoo')}">
                        <div class="uk-card uk-card-default uk-card-hover uk-card-body"
                             onclick="window.open('https://www.yahoo.com/', '_blank')">
                            <h3 class="uk-card-title">Yahoo</h3>
                            <p>여기를 클릭해 이메일을 확인해주세요.</p>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="uk-card uk-card-default uk-card-hover uk-card-body">
                            <h3 class="uk-card-title"> 이메일을 확인해주세요</h3>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<!-- Footer -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.nice-select.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="<%=request.getContextPath()%>/assets/AJAX_helper/ajax_helper.js"></script>
<script>
    $(function () {
        /** 더 보기 버튼에 대한 이벤트 정의 */
        $('#resendEmail').click(function () {
            // Restful API에 post 방식 요청
            $.post("${pageContext.request.contextPath}/resend.do",{ID: "${sessionScope.ID}", Email: "${sessionScope.Email}", user_ID:"${sessionScope.user_ID}"}, function() {
                var Toast = Swal.fire({
                    icon: 'success',
                    title: '재전송',
                    text: '이메일 재전송을 성공하였습니다.',
                })
            });
        })
        <c:set var="name" value="홍길동" />

        <c:if test="${empty {sessionScope.ID}}">
        var Toast = Swal.fire({
            icon: 'success',
            title: '재전송',
            text: '이메일 재전송을 성공하였습니다.',
        });
        </c:if>

    });




</script>

</body>