<!--
FileName: admin_header.jsp
Description: 관리자 페이지의 상단 헤더입니다.
Author: 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div uk-sticky class="uk-navbar-container tm-navbar-container uk-active">
    <div class="uk-container uk-container-expand">
        <nav uk-navbar>
            <div class="uk-navbar-left">
                <a id="sidebar_toggle" class="uk-navbar-toggle" uk-navbar-toggle-icon></a>
                <a href="<%=request.getContextPath()%>/admin" class="uk-navbar-item uk-logo">
                    TIE SHOE Admin
                </a>
            </div>
            <!-- NAVBAR 오른쪽 상당 메뉴-->
            <div class="uk-navbar-right uk-light">
                <!-- 로그아웃 -->
                <a href="<%=request.getContextPath()%>/logout" uk-icon="sign-out"></a>
                <!-- 홈으로 -->
                <a href="<%=request.getContextPath()%>/" uk-icon="home"></a>
            </div>
        </nav>
    </div>
</div>
<!-- END OF NAV -->

<!-- Starting SideNav-->
<div id="sidebar" class="tm-sidebar-left uk-background-default">
    <ul class="uk-nav uk-nav-default">
        <li class="uk-active bold "><a href="<%=request.getContextPath()%>/admin">Admin Home</a></li>
        <li class="uk-active adminStatus main">
            <c:choose>
                <c:when test="${sessionScope.userLevel == 4}">
                    CS 관리자
                </c:when>
                <c:when test="${sessionScope.userLevel == 3}">
                    경리 관리자
                </c:when>
                <c:when test="${sessionScope.userLevel == 2}">
                    물류 관리자
                </c:when>
                <c:when test="${sessionScope.userLevel == 1}">
                    최고 관리자
                </c:when>
                <c:otherwise>
                    누구시죠...?
                </c:otherwise>
            </c:choose>
        </li>

        <li class="uk-parent uk-active bold">
            <a href="#">회원관리</a>
            <ul class="uk-nav-sub" uk-nav="collapsible: false">
                <li><a href="<%=request.getContextPath()%>/admin/manageUsers">회원관리</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/sendMailTest">메일발송테스트</a></li>
            </ul>
        </li>
        <!-- Level 4 (CS관리자)-->
        <c:if test="${sessionScope.userLevel == 4 || sessionScope.userLevel == 1}">
            <li class="uk-parent uk-active bold">
                <a href="#">게시판관리</a>
                <ul class="uk-nav-sub" uk-nav="collapsible: false">
                    <li><a href="<%=request.getContextPath()%>/admin/announcement">공지사항</a></li>
                </ul>
            </li>
        </c:if>
        <li class="uk-parent uk-active bold">
            <a href="#">상품관리</a>
            <ul class="uk-nav-sub" uk-nav="collapsible: false">
                <li><a href="<%=request.getContextPath()%>/admin/productList">상품목록 </a></li>
                <!-- Level 2 (물류)-->
                <c:if test="${sessionScope.userLevel == 2 || sessionScope.userLevel == 1}">
                    <li><a href="<%=request.getContextPath()%>/admin/addProduct">상품등록 </a></li>
                </c:if>
            </ul>
        </li>
        <li class="uk-parent uk-active bold">
            <a href="#">주문관리</a>
            <ul class="uk-nav-sub" uk-nav="collapsible: false">
                <c:if test="${sessionScope.userLevel == 1}">
                    <li><a href="<%=request.getContextPath()%>/admin/superUser">거래진행 (최고 권한)</a></li>
                </c:if>
                <!-- Level 2 (물류)-->
                <c:if test="${sessionScope.userLevel == 2 || sessionScope.userLevel == 1}">
                    <li><a href="<%=request.getContextPath()%>/admin/manageOrder">주문내역 (검수확인)</a></li>
                </c:if>
                <!-- Level 3 (경리)-->
                <c:if test="${sessionScope.userLevel == 1 || sessionScope.userLevel == 3}">
                    <li><a href="<%=request.getContextPath()%>/admin/manageBuyTransaction">입찰자 입금관리 </a></li>
                    <li><a href="<%=request.getContextPath()%>/admin/manageSellTransaction">판매자 송금관리 </a></li>
                    <li><a href="<%=request.getContextPath()%>/admin/sellStatus">상품판매현황</a></li>
                    <li><a href="<%=request.getContextPath()%>/admin/buyStatus">상품입찰현황</a></li>
                </c:if>
                <!-- Level 4 (CS관리자)-->
                <c:if test="${sessionScope.userLevel == 4 || sessionScope.userLevel == 1}">
                    <li><a href="#">상품후기관리</a></li>
                </c:if>

            </ul>
        </li>
        <!-- Level 4 (CS관리자)-->
        <c:if test="${sessionScope.userLevel == 4 || sessionScope.userLevel == 1}">
            <li class="uk-parent uk-active bold">
                <a href="#">키워드관리</a>
                <ul class="uk-nav-sub" uk-nav="collapsible: false">
                    <li><a href="<%=request.getContextPath()%>/admin/popProduct">상품노출순위</a></li>
                    <li><a href="<%=request.getContextPath()%>/admin/popSearch">검색어노출순위</a></li>
                </ul>
            </li>
        </c:if>
        <c:if test="${sessionScope.userLevel == 1}">
            <li class="uk-parent uk-active bold">
                <a href="#">관리자</a>
                <ul class="uk-nav-sub" uk-nav="collapsible: false">
                    <li><a href="<%=request.getContextPath()%>/admin/adminList">관리자 목록</a></li>
                    <li><a href="<%=request.getContextPath()%>/admin/control">관리자 등록</a></li>
                </ul>
            </li>
        </c:if>
    </ul>
</div>