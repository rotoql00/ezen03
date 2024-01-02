<!--
@filename : registerSuccess.jsp
@description : 회원가입 환영,설문조사 이동 jsp 페이지 입니다..
@author : 임석현
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8" />
<title>TIESHOE</title>
<meta name="viewport" content="width=device-width, user-scalable=no">
<!-- UIkit CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/registerSuccess.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/common.css">
<link rel="icon"
	href="<%=request.getContextPath()%>/assets/img/tieshoe.png">

<style type="text/css">
.wrap-loading { /*화면 전체를 어둡게 합니다.*/
	position: fixed;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	background: rgba(0, 0, 0, 0.2); /*not in ie */
	filter: progid:DXImageTransform.Microsoft.Gradient(startColorstr='#20000000',
		endColorstr='#20000000'); /* ie */
}

.wrap-loading div { /*로딩 이미지*/
	position: fixed;
	top: 50%;
	left: 50%;
	margin-left: -210px;
	margin-top: -210px;
}

.display-none { /*감추기*/
	display: none;
}

.infoLoading {
	color: white;
	font-size: 50px;
	font-weight: 200;
}
</style>
</head>

<body onload="sendEmail();">
	<!-- HEADER -->
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>

	<!-- 로그인 상태 확인 -->
	<c:if test="${empty sessionScope.welcomeID}">
		<script>
			alert("로그인을 해주세요.");
			location.href = "${pageContext.request.contextPath}/login"
		</script>
	</c:if>

	<!--  로딩 이미지 -->
	<div class="wrap-loading display-none">
		<div>
			<img src="<%=request.getContextPath()%>/assets/img/loadingJordan.gif" />
			<h1 class="infoLoading">인증 메일 전송중...</h1>
		</div>
	</div>


	<div class="container">
		<div class="reg_box_container">
			<p class="logo customLogo">TIESHOE</p>
			<p class="topContainer">
				<span class="userID">${output.userName}</span>님, <span
					style="color: red">회원가입</span>이 완료되었습니다.
			</p>
			<p class="topContainer">TIESHOE의 다양한 서비스를 마음껏 경험해보세요</p>

			<div class="reg_box_survey">
				<p class="bottom_container">
					설문에 참여하면 <span style="color: red;">쿠폰</span>이 발급됩니다
				</p>
				<p class="bottom_container">
					<a class="surveyLink" href="<%=request.getContextPath()%>/survey">
						여기를 클릭해서 설문에 참여해주세요</a>
				</p>
			</div>

			<form method="post" id="sendEmailAuth">
				<!-- action="${pageContext.request.contextPath}/Welcome?ID=${output.ID}" -->
				<div>
					<input type="hidden" id="MailOverlap" value="0"> <input
						type="hidden" id="ID" value="${output.ID}"> <input
						type="hidden" id="userId" value="${output.userId}"> <input
						type="hidden" id="userEmail" value="${output.userEmail}">
					<input type="hidden" id="userName" value="${output.userName}">
					<!-- <button id="emailReSend" type="button" onclick="sendEmail();">인증메일
						재발송</button> -->
				</div>
			</form>

		</div>
		<div class="uk-flex uk-flex-center bottom_tab">
			<button class="uk-button uk-button-secondary uk-button-large"><a href="<%=request.getContextPath()%>"> 홈으로</a></button>
		</div>

	</div>
	<!-- FOOTER -->
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/validate.js"></script>
	<!-- 공통속성 common -->
	<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
	<!-- sweetalert2 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script type="text/javascript">
		function sendEmail() {
			var MailOverlap = $("#MailOverlap").val();
			/** 이메일 전송 */
			var ID = $("#ID").val();
			var userId = $("#userId").val();
			var userEmail = $("#userEmail").val();
			var userName = $("#userName").val();

			var query = {
				"ID" : ID,
				"userId" : userId,
				"userEmail" : userEmail,
				"userName" : userName
			};

			if (MailOverlap == "0") {
				$.ajax({
					url : "${pageContext.request.contextPath}/emailAuth",
					type : "POST",
					data : query,
					success : function(data) {
						var Toast = Swal.fire({
							icon : 'success',
							title : '이메일 전송완료',
							text : '회원정보 이메일로 인증메일을 발송했습니다.',
						})// end fire
						$("#MailOverlap").val("1");
					},

					beforeSend : function() {
						//(이미지 보여주기 처리)
						$('.wrap-loading').removeClass('display-none');
					},

					complete : function() {
						//(이미지 감추기 처리)
						$('.wrap-loading').addClass('display-none');
					},

				/**
				error : function(e) {
					//	조회 실패일 때 처리
					var Toast = Swal.fire({
						icon : 'error',
						title : '이메일 전송 실패',
						text : '인증메일 전송에 실패 했습니다. 재 전송 버튼을 눌러주세요.',
					})
				}
				 */

				});//ajax
			} else {
				if ((event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82))
						|| (event.keyCode == 116)) {
					event.keyCode = 0;
					event.cancelBubble = true;
					event.returnValue = false;
				}

			}
			document.onkeydown = doNotReload;
		}
		// $("#sendEmailAuth").click(function() {

		function doNotReload() {
			if ((event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82))
					|| (event.keyCode == 116)) {
				event.keyCode = 0;
				event.cancelBubble = true;
				event.returnValue = false;
			}
		}
		document.onkeydown = doNotReload;
	</script>
</body>

</html>