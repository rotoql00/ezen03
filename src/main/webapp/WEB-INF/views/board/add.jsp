<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Hello JSP</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/board.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/common.css">
</head>
<body>
	<!-- Header -->
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>

	<h1>공지 작성하기</h1>
	<form method="post"
		action="${pageContext.request.contextPath}/board/add_ok">
		<div>
			<label for="boardTitle">제목: </label> <input type="text"
				name="boardTitle" id="boardTitle" placeholder="노출할 공지 타이틀"/>
		</div>
		<div>
			<label for="boardContext">내용: </label> <input type="text"
				name="boardContext" id="boardContext" placeholder="파일이름.확장자"/>
		</div>
		<hr />
		<button type="submit">저장하기</button>
		<button type="reset">다시작성</button>
	</form>
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

