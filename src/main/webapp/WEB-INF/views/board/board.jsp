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
	
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/nice-select.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/admin/admin.css">
<link rel="icon"
	href="<%=request.getContextPath()%>/assets/img/admin.png">
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

			<!-- 조회 결과 목록 -->
			<div class="uk-card-body">
			<table class="boardTable" border="1">
				<thead>
					<tr class="boardTR">
						<th class="thP" width="200" align="center">번호</th>
						<th class="thP" width="300" align="center">제목</th>
						<th class="thP" width="200" align="center">날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<%-- 조회결과가 없는 경우 --%>
						<c:when test="${output == null || fn:length(output) == 0}">
							<tr>
								<td colspan="3" align="center">조회결과가 없습니다.</td>
							</tr>
						</c:when>
						<%-- 조회결과가 있는  경우 --%>
						<c:otherwise>
							<%-- 조회 결과에 따른 반복 처리 --%>
							<c:forEach var="item" items="${output}" varStatus="status">
								<%-- 출력을 위해 준비한 제목과 위치 --%>
								<c:set var="boardTitle" value="${item.boardTitle}" />
								<c:set var="boardDate" value="${item.boardDate}" />

								<%-- 검색어가 있다면? --%>
								<c:if test="${keyword != ''}">
									<%-- 검색어에 <mark> 태그를 적용하여 형광팬 효과 준비 --%>
									<c:set var="mark" value="<mark>${keyword}</mark>" />
									<%-- 출력을 위해 준비한 제목과 위치에서 검색어와 일치하는 단어를 형광팬 효과로 변경 --%>
									<c:set var="boardTitle"
										value="${fn:replace(boardTitle, keyword, mark)}" />
									<c:set var="boardDate"
										value="${fn:replace(boardDate, keyword, mark)}" />
								</c:if>

								<%-- 상세페이지로 이동하기 위한 URL --%>
								<c:url value="/board/view" var="viewUrl">
									<c:param name="ID" value="${item.ID}" />
								</c:url>

								<tr class="boardList">
									<td class="tdP" align="center">${item.ID}</td>
									<td class="tdPTitle" align="center"><a href="${viewUrl}">${boardTitle}</a></td>
									<td class="tdP" align="center">${boardDate}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			</div>

			<!-- 페이지 번호 구현 -->
			<ul class="uk-pagination" uk-margin>
				<%-- 이전 그룹에 대한 링크 --%>
				<c:choose>
					<%-- 이전 그룹으로 이동 가능하다면? --%>
					<c:when test="${pageData.prevPage > 0}">
						<%-- 이동할 URL 생성 --%>
						<c:url value="/board" var="prevPageUrl">
							<c:param name="page" value="${pageData.prevPage}" />
							<c:param name="keyword" value="${keyword}" />
						</c:url>
						<a href="${prevPageUrl}"><span uk-pagination-previous></span></a>
					</c:when>
					<c:otherwise>
						<span uk-pagination-previous></span>
					</c:otherwise>
				</c:choose>

				<%-- 페이지 번호 (시작 페이지 부터 끝 페이지까지 반복) --%>
				<c:forEach var="i" begin="${pageData.startPage}"
					end="${pageData.endPage}" varStatus="status">
					<%-- 이동할 URL 생성 --%>
					<c:url value="/board" var="pageUrl">
						<c:param name="page" value="${i}" />
						<c:param name="keyword" value="${keyword}" />
					</c:url>

					<%-- 페이지 번호 출력 --%>
					<c:choose>
						<%-- 현재 머물고 있는 페이지 번호를 출력할 경우 링크 적용 안함 --%>
						<c:when test="${pageData.nowPage == i}">
							<li class="uk-active"><span>${i}</span></li>
						</c:when>
						<%-- 나머지 페이지의 경우 링크 적용함 --%>
						<c:otherwise>
							<li><a href="${pageUrl}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<%-- 다음 그룹에 대한 링크 --%>
				<c:choose>
					<%-- 다음 그룹으로 이동 가능하다면? --%>
					<c:when test="${pageData.nextPage > 0}">
						<%-- 이동할 URL 생성 --%>
						<c:url value="/board" var="nextPageUrl">
							<c:param name="page" value="${pageData.nextPage}" />
							<c:param name="keyword" value="${keyword}" />
						</c:url>
						<a href="${nextPageUrl}"><span uk-pagination-next></span></a>
					</c:when>
					<c:otherwise>
						<span uk-pagination-next></span>
					</c:otherwise>
				</c:choose>
			</ul>
			<!-- 관리자 확인 글쓰기-->
			<div>
				<button class="uk-button uk-button-primary"
					onclick="window.location.href = '${pageContext.request.contextPath}/admin/announcement';"
					id="adminWrite" style="display: none;">공지 작성하기</button>
				<c:if test="${sessionScope.UserAdmin eq 'Y'}">
					<script>
						$("#adminWrite").show();
					</script>
				</c:if>
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
