<!--
FileName: user_manage.jsp
Description: 회원관리를 위한 페이지입니다.
Author: 임석현
-->

<%@ page import="com.ezen03.tieshoe.helper.PageData"%>
<%@ page import="sun.reflect.annotation.ExceptionProxy"%>
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
<title>TIESHOE ADMIN</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/common.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/nice-select.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/admin/admin.css">
<link rel="icon"
	href="<%=request.getContextPath()%>/assets/img/admin.png">
<style type="text/css">
#boardContextLabel {
	margin-top: 20px;
}

.boardSubmitBTNArea {
	width: 100%;
	margin-top: 40px !important;
}

.theadTh {
	text-align: center !important;
}

.tdPTitle {
	text-align: left;
}

.uk-pagination {
	width: 100%;
	height: 40px;
	text-align: center;
	font-size: 16px;
	margin-top: 40px;
	justify-content: center;
}
</style>
</head>

<body>
	<%@ include file="../inc/admin_header.jsp"%>
	<div class="content-padder content-background">
		<div class="uk-section-xsmall uk-section-default header">
			<div class="uk-container uk-container-large">
				<h1 class="bold">
					<span class="ion-speedometer"></span>공지사항
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
								공지사항 등록 <br> <br> <span class="small">* 공지사항
									제목을 작성한 후에 본문을 작성해주세요 <br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를
									닫아주세요
								</span>
							</div>
							<div class="uk-card-body">

								<!-- FORM 시작 action="${pageContext.request.contextPath}/admin/announcement_ok"-->
								<form id="addBoardInfo" class="uk-grid-small uk-form-stacked"
									method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/board/addBoard.do" uk-grid uk-margin>
									<!-- 공지사항 제목 -->
									<div class="uk-width-1-1">
										<label class="uk-form-label bold" for="announcement_title">공지사항
											제목</label>
										<div class="uk-form-controls">
											<input class="uk-input enterDetection" name="boardTitle"
												id="boardTitle" type="text" placeholder="ex) TIESHOE 이용방법">
										</div>
									</div>

									<!-- 공지 PNG 파일  내용 -->
									<div class="uk-width-1-1 uk-position-relative">
										<label id="boardContextLabel" class="uk-form-label bold"
											for="announcement_title">공지사항 내용</label>
										<div class="uk-form-controls">
											<input class="uk-input enterDetection" name="boardContext"
												id="boardContext" type="text" placeholder="ex) 텍스트 입력">
										</div>
									</div>

									<!-- 이미지 업로드 -->
									<div class="uk-width-1-1">
										<label class="uk-form-label bold" for="boardImgPath"
											style="margin-top: 20px;">공지 PNG업로드&nbsp;&nbsp;&nbsp;
											<span class="small">* 텍스트 박스를 클릭하여 업로드해주세요.(610KB 미만)</span>
										</label>
										<div uk-form-custom="target: true">
											<input type="file" name="boardImgPath" id="boardImgPath"> <input
												class="uk-input uk-form-width-large" type="text"
												placeholder="Select file" disabled>
										</div>
									</div>

									<div class="boardSubmitBTNArea">
										<!-- <button class="uk-button uk-button-secondary uk-align-right"
											type="submit">저장하기</button> -->
										<button class="uk-button uk-button-secondary uk-align-right"
											type="submit">저장하기</button><!-- onclick="addBoard();" -->
									</div>
								</form>

							</div>
						</div>
					</div>
				</div>
				<!-- <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-1@l">
                <div>
                    <div class="uk-card uk-card-default">
                        <div class="uk-card-header bold">
                            공지사항 본문 작성 <br><br>
                            <span class="small">* 최근 등록 공지사항 차례로 나열됩니다.<br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요</span>
                        </div>
                        <div class="uk-card-body">

                            메일 박스
                            <div id="editor">
                                박스 안은 나중에 결정되면 수정 할 예정입니다.
                            </div>
                            <button class="uk-button uk-button-secondary right">등록</button>
                        </div>
                    </div>
                </div>
            </div> -->

				<div uk-grid class="uk-child-width-1-1@s uk-child-width-1-1@l">
					<div>
						<div class="uk-card uk-card-default">
							<div class="uk-card-header bold">
								게시판 현황 <br> <br> <span class="small">* 공지사항 삭제
									또는 수정은 관리자 페이지에서만 가능합니다. <br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를
									닫아주세요
								</span>
							</div>
							<div class="uk-card-body">
								<table
									class="uk-table uk-table-striped  uk-table-responsive uk-table-hover">
									<thead>
										<tr>
											<th class="theadTh">번호</th>
											<th class="theadTh">제목</th>
											<th class="theadTh">작성일</th>
											<!-- <th class="theadTh">옵션</th> -->
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
														<td class="tdPTitle"><a href="${viewUrl}">${boardTitle}</a></td>
														<td class="tdP" align="center">${boardDate}</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
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
	<!-- ckeditor for Email-->
	<script
		src="https://cdn.ckeditor.com/ckeditor5/16.0.0/classic/ckeditor.js"></script>
	<!-- 드롭다운 -->
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery.nice-select.js"></script>
	<!-- Plain Script-->
	<script
		src="<%=request.getContextPath()%>/admin/announcement/announcement.js"></script>
	<script src="<%=request.getContextPath()%>/admin/admin.js"></script>	
	<!-- 공통속성 common -->
	<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
</body>

</html>