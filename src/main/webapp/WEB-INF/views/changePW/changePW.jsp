<!--
FileName: myPage.html
Description: 개인정보관리.
Author: 임석현
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
<title>마이페이지</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/common.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/myPage.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/changePW.css">
<link rel="icon"
	href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
	<style type="text/css">
	.error {
	color: red;
	}
	</style>
</head>

<body>
	<!-- Header -->
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<div class="container_PW" onload="callInfo()">
		<p class="title_PW">비밀번호 변경</p>
		<input type="hidden" id="ID" value="${sessionScope.loginID}">
		<input type="hidden" id="ID" value="${passID}">
		<input type="hidden" id="ID" value="${ID}">
		<input type="hidden" id="ID" value="${loginID}">
		
		<form method="post" class="uk-form" id="changePWForm" action="${pageContext.request.contextPath}/changPW.do">
			<div
				class="uk-margin-auto uk-margin-auto-vertical uk-width-1-2@s uk-card uk-card-default uk-card-body">
				<div class="content">
					<h6 class="uk-card-title space_title">새로운 비밀번호</h6>
					<input class="uk-input uk-width-1-1" id="User_PW" name="userPw"
						type="password" placeholder="비밀번호를 입력하세요">
				</div>
				<div class="space_content">
					<h6 class="uk-card-title space_title">비밀번호 확인</h6>
					<input class="uk-input uk-width-1-1" id="User_PWCheck"
						name="userPwCheck" type="password" placeholder="비밀번호를 다시 입력해주세요.">
				</div>

				<div class="uk-width-1-1 button_pw">
					<div class="uk-flex uk-flex-right">
						<button type="submit" id="CPW"
							class="uk-button uk-button-secondary">변경</button>
					</div>
				</div>
			</div>
		</form>

	</div>
	<!-- Footer -->
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery.nice-select.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
	<!-- validate plugin CDN-->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/validate.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/join.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/changePW.js"></script>
</body>