<!--
FileName: user_manage.jsp
Description: 회원관리를 위한 페이지입니다.
Author: 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>

<head>
    <meta charset="UTF-8"/>
    <title>TIESHOE ADMIN</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/nice-select.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/admin/admin.css">
</head>

<body>
<%@ include file="../inc/admin_header.jsp" %>
<div class="content-padder content-background">
    <div class="uk-section-xsmall uk-section-default header">
        <div class="uk-container uk-container-large">
            <h1 class="bold"><span class="ion-speedometer"></span>메일발송테스트</h1>
        </div>
    </div>
    <div class="uk-section-small">
        <!-- 내용 -->
        <div class="uk-container uk-container-large">
            <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-1@l">
                <div>
                    <div class="uk-card uk-card-default">
                        <div class="uk-card-header bold">
                            메일발송 <br><br>
                            <span class="small">* 매일보내기 기능에 문제가 있을경우 테스트를 통해 검사해주세요.<br>* 테스트메일의 내용은 설정되어 있습니다.<br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요</span>
                        </div>
                        <div class="uk-card-body">
                            <!-- 메일 박스 -->
                            <!-- FORM 시작 -->
                            <form method="post" action="${pageContext.request.contextPath}/admin/sendMail/send.do"
                                  class="uk-grid-small uk-form-stacked" uk-grid uk-margin>
                                <!-- 상품명(한글) -->
                                <div class="uk-width-1-2">
                                    <label class="uk-form-label bold" for="email">E-mail주소</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="email" type="text"
                                               placeholder="ex)tieshoe@gmail.com"
                                               name="to">
                                    </div>
                                </div>
                                <div class="uk-width-1-1">
                                    <button class="uk-button uk-button-secondary right">테스트 메일전송</button>
                                </div>
                                <!-- 이메일 subject입니다. -->
                                <input  class="hidden" type="text" value="TieShoe C2C" name="subject">
                                <!-- 이메일 내용입니다. -->
                                <input type="text" class="hidden" value="TieShoe C2C에서 보내는 관리자 이메일 테스트 입니다." name="content">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Jquery 3.4.1 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.transit/0.9.12/jquery.transit.min.js"></script>
<!-- UI-kit Script-->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<!-- Handlebar Script-->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!-- ckeditor for Email-->
<script src="https://cdn.ckeditor.com/ckeditor5/16.0.0/classic/ckeditor.js"></script>
<!-- 드롭다운 -->
<script src="<%=request.getContextPath()%>/assets/js/jquery.nice-select.js"></script>
<!-- Auto Complete-->
<script src="<%=request.getContextPath()%>/assets/js/jquery.autocomplete.js"></script>
<!-- Plain Script-->
<script src="<%=request.getContextPath()%>/admin/send_mail/send_mail.js"></script>
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>
</body>

</html>