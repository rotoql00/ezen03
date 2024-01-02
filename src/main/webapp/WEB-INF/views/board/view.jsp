<%@ page import="com.ezen03.tieshoe.helper.PageData"%>
<%@ page import="sun.reflect.annotation.ExceptionProxy"%>
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
<title>공지</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/board.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/common.css">
<style type="text/css">
#container {
	padding-top: 30px;
	min-height: 1000px;
}

#list {
	width: 90%;
	margin: auto;
	padding-bottom: 20px;
}

.boardTitle {
	margin: 0;
	padding: 12px 22px;
	color: #545861;
	font-weight: bold;
	font-size: 22px;
	line-height: 30px;
	text-align: left;
}

#TitleSize {
	font-size: 30px;
	font-weight: bold;
}

.boardDate {
	clear: both;
	float: left;
	width: 100%;
	border-top: 1px solid #f1f1f1;
	border-bottom: 1px solid #f1f1f1;
	background-color: #f9f9f9;
	font-size: 13px;
}

.boardDateKor {
	float: left;
	font-weight: bold;
	color: #545861;
	text-align: right;
}

.boardDateValue {
	float: left;
	padding-left: 20px;
	color: #545861;
}

.boardViewArea {
	margin: auto;
	width: 70%;
}

#test-notice {
	width: 70%;
	margin-top: 80px;
}

#viewList {
	margin: 50px;
}

.boardDate-attr {
	float: right !important;
	display: inline-block;
	zoom: 1;
	float: left;
	margin: 0;
	padding: 12px 35px 12px 22px;
}
</style>

</head>
<body>
	<!-- Header -->
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<!-- board 게시판 코드 -->
	<div id="container">
		<div id="list">
			<div id="totaltab">
				<ul class="uk-tab">
					<li class="uk-active"><a
						href="<%=request.getContextPath()%>/board">공지사항</a></li>
					<li><a href="<%=request.getContextPath()%>/faq">FAQ</a></li>
					<li><a href="<%=request.getContextPath()%>/userGuide">이용안내</a></li>
				</ul>
			</div>

			<!-- 공지 상세 보기 -->
			<div class="boardViewArea">
				<div class="boardTitle">
					<h1 id="TitleSize">${output.boardTitle}</h1>
				</div>

				<div class="boardDate">
					<div class="boardDate-attr">
						<div class="boardDateKor">작성일</div>
						<div class="boardDateValue">${output.boardDate}</div>
					</div>
				</div>

				<div class="boardContent">
					<div>${output.boardContext}</div>
				</div>

				<!-- 상품 이미지 -->
				<div id="boardImage">
					<img src="${context}${output.boardImgPath}">
				</div>


				<%-- <img class="uk-align-center" id="test-notice"
					data-src="<%=request.getContextPath()%>/assets/img/board/test-notice.png"
					uk-img> --%>

				<img class="uk-align-center" id="test-notice"
					data-src="<%=request.getContextPath()%>/assets/img/board/${output.boardContext}"
					uk-img>



				<div>

					<!-- 일반 유저 목록보기 -->
					<button class="uk-button uk-button-primary" id="viewList"
						onclick="window.location.href = '${pageContext.request.contextPath}/board';">목록보기</button>

					<!-- 관리자 확인 글쓰기-->
					<button class="uk-button uk-button-primary"
						onclick="window.location.href = '${pageContext.request.contextPath}/board/add';"
						style="display: none;">공지 작성</button>
					<button class="uk-button uk-button-primary"
						onclick="window.location.href = '${pageContext.request.contextPath}/board/edit?ID=${output.ID}';"
						style="display: none;">공지 수정</button>
					<button class="uk-button uk-button-primary"
						onclick="window.location.href = '${pageContext.request.contextPath}/board/delete_ok?ID=${output.ID}';"
						style="display: none;">공지 삭제</button>
					<c:if test='${sessionScope.UserAdmin eq \'Y\'}'>
						<script>
							$(".uk-button").show();
						</script>
					</c:if>
				</div>
			</div>

		</div>

	</div>

	<!--Footer -->
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<!-- UIkit JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/board.js"></script>
</body>
</html>
