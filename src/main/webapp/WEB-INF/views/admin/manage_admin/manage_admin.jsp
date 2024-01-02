<!--
FileName: add_product.jsp
Description: 상품등록을 위한 관리자 페이지 입니다.
Author: 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8" />
<title>TIESHOE ADMIN</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/common.css">
<link rel="stylesheet" href=<%=request.getContextPath()%>"/assets/css/nice-select.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/plugin/air-datepicker/datepicker.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/selectric.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/admin/admin.css">
<link rel="icon"
	href="<%=request.getContextPath()%>/assets/img/admin.png">
</head>

<body>
	<%@ include file="../inc/admin_header.jsp"%>
	<div class="content-padder content-background">
		<div class="uk-section-xsmall uk-section-default header">
			<div class="uk-container uk-container-large">
				<h1 class="bold">
					<span class="ion-speedometer"></span>관리자 등록
				</h1>
			</div>
		</div>
		<div class="uk-section-small">

			<!-- 내용 -->
			<div class="uk-container uk-container-large">
				<div uk-grid class="uk-child-width-1-1@s uk-child-width-1-1@l">
					<div>
						<div class="uk-card uk-card-default">
							<div class="uk-card-header bold">
								관리자 등록 <br>
								<br> <span class="small"><br>* 특정 픽셀로 내려가면 깨질수
									있으니 사이드 바를 닫아주세요<br>* 최고권한자만 관리자 등록이 가능합니다.</span>
							</div>
							<div class="uk-card-body">

								<!-- FORM 시작 -->
								<form method="post" class="uk-grid-small uk-form-stacked"
									id="signUp_Form"
									action="${pageContext.request.contextPath}/adminConfirm" uk-grid
									uk-margin>


									<!-- 관리자 이름 -->
									<div class="uk-width-1-2">
										<label class="uk-form-label bold" for="userName">이름</label>
										<div class="uk-form-controls">
											<input class="uk-input enterDetection" id="userName"
												name="userName" type="text" placeholder="ex) 홍길동"
												onkeyup="validate()" name="productNameKR">
										</div>
									</div>


									<!-- 관리자 아이디 -->
									<div class="uk-width-1-2">
										<label class="uk-form-label bold" for="userId">아이디</label>
										<div class="uk-form-controls">
											<input class="uk-input enterDetection" id="userId"
												name="userId" type="text" placeholder="ex) abcd1234">
										</div>
									</div>


									<!-- 관리자 이메일 -->
									<div class="uk-width-1-2">
										<label class="uk-form-label bold" for="userEmail">이메일</label>
										<div class="uk-form-controls">
											<input class="uk-input enterDetection" id="userEmail"
												name="userEmail" type="text"
												placeholder="ex) abcd123@gmail.com">
										</div>
									</div>



									<!-- 관리자 전화번호 -->
									<div class="uk-width-1-4@s">
										<label class="uk-form-label bold" for="userPhonenum">전화번호</label>
										<div class="uk-form-controls">
											<input class="uk-input"
												id="userPhonenum" name="userPhonenum" type="text" maxlength="13"
												placeholder="ex) 010-1234-5678" onkeyup="inputHyphen(this);">
										</div>
									</div>



									<!-- 관리자 비밀번호 -->
									<div class="uk-width-1-2">
										<label class="uk-form-label bold" for="userPw">비밀번호</label>
										<div class="uk-form-controls">
											<input class="uk-input enterDetection" id="userPw" type="password"
												name="userPw" minlength="6" maxlength="20"  placeholder="ex) ****">
										</div>
									</div>



									<!-- 관리자 권한 -->
									<div class="uk-width-1-2">
										<label class="uk-form-label bold" for="userLevel">권한</label>
										<select name="userLevel" id="userLevel">
											<option selected>선택</option>
											<option value="1">최고권한</option>
											<option value="2">물류</option>
											<option value="3">경리</option>
											<option value="4">CS담당</option>
										</select>
									</div>

									<!-- submit -->
									<div class="uk-width-1-1">
										<div class="uk-flex uk-flex-right">
											<button type="submit" id="addProduct"
												class="uk-button uk-button-secondary">등록</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Jquery 3.4.1 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.transit/0.9.12/jquery.transit.min.js"></script>
	<!-- UI-kit Script-->
	<script
		src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
	<!-- Handlebar Script-->
	<script
		src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<!-- air-datepicker -->
	<script
		src="<%=request.getContextPath()%>/assets/plugin/air-datepicker/datepicker.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery.selectric.min.js"></script>
	<!-- sweet alert-->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<!-- Plain Script-->
	<script
		src="<%=request.getContextPath()%>/admin/add_product/add_product.js"></script>
	<script src="<%=request.getContextPath()%>/admin/admin.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery.nice-select.js"></script>
	<script src="<%=request.getContextPath()%>/admin/manage_admin/manage_admin.js"></script>
</body>

</html>