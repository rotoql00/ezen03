<%@ page import="java.time.LocalDate" %>
<!--
FileName: buy.jsp
Description: 입찰하는 페이지입니다.
Author: 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>

<c:set var="seven" value="<%=LocalDate.now().plusDays(7)%>"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Buy</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/buy.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/popupCurrent.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/selectric.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
    <!-- 배송정보 수정을 위한 modal의 css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/myPage_change_addr.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/doneBuy.css">
</head>
<!--
#buySize = 옵션 (사이즈)
#buyHighbid = 최고 입찰가
#buyLowsell = 최저 판매가
-->
<body>
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<div id="container">
    <div class="product_title-fake">
        <h2 id=product_name_fake>${product.productNameEN}</h2>
        <h3 id=product_name_ko_fake>${product.productNameKR}</h3>
    </div>
    <div class="left_container">
        <div class="product_title">
            <h2 id=product_name>${product.productNameEN}</h2>
            <h3 id=product_name_ko>${product.productNameKR}</h3>
        </div>
        <!-- 상품 이미지 -->
        <img src="${context}${product.productImgPath}" alt="${product.productNameKR}">
    </div>

    <!-- 오른쪽 부분-->
    <div class="right_container">
        <p class="buy_title">
            <c:choose>
                <c:when test="${checkSell.sellProcess eq 'N'}">
                    판매 접수완료
                </c:when>
                <c:otherwise>
                    판매 시작
                </c:otherwise>
            </c:choose>
        </p>

        <!-- 선택사항 (옵션, 최고입찰가 등등...-->
        <div class="option">
            <div class="option_adjustment">
                <!-- 사이즈는 파라미터로 받아옵니다.-->
                <div class="buy_left">
                    옵션 &nbsp <span id="buySize" class="option_font_size">${checkSell.sellSize}</span>
                </div>
            </div>
        </div>

        <hr class="buy_hr_bold addPadding">

        <div class="buy_shipping_price_container">
            <div class="buy_shipping_price_text">판매 가격</div>
            <div class="buy_shipping_price">₩
                <fmt:formatNumber value="${checkSell.sellPrice}" pattern="#,###"/>
            </div>
        </div>

        <hr class="buy_hr_fade">
        <!-- 배송비에 대한 HTML-->

        <div class="buy_shipping_price_container">
            <div class="buy_shipping_price_text">수수료</div>
            <div class="buy_shipping_price" id="buy_cons">₩
                <fmt:formatNumber value="${checkSell.sellFee}" pattern="#,###"/>
            </div>
        </div>

        <hr class="buy_hr_fade">

        <div class="buy_shipping_price_container">
            <div class="buy_shipping_totalprice_text">합계</div>
            <div class="buy_shipping_totalprice" style="color: red!important;" id="buy_totalPrice">₩
                <fmt:formatNumber value="${checkSell.sellPrice + checkSell.sellFee}" pattern="#,###"/></div>
        </div>

        <hr class="buy_hr_bold">

        <!-- 입찰 마감일-->
        <c:if test="${checkSell.sellProcess eq 'N'}">
            <div class="buy_bidDate">
                <div class="buy_shipping_price_text">판매 마감일</div>
                <div class="buy_shipping_price">
                    <fmt:formatNumber value="${checkSell.sellEnd}" pattern="#,###"/> 일 <span class="smallSize">(<c:out
                        value="${seven}"/>)</span>
                </div>
            </div>
        </c:if>

        <hr class="buy_hr_fade">

        <div class="buy_address_container" id="address_container">
            <input id="userIDNum" type="hidden" name="ID"
                   value="${sessionScope.loginID}">
            <div class="buy_address">배송</div>
            <div class="buy_address_info" id="userAddrInfo">서울특별시 서초구. 1303-37<br>서초W타워 13층</div>
        </div>
        <hr class="buy_hr_fade">

        <div class="buy_submit_container">
            <div class="buy_submit">
                <button id="confirmButton" type="button" onclick="myPage()"
                        class="buy_submit_button">마이페이지
                </button>
            </div>
        </div>

    </div>
</div>


<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<!-- jquery 를 가져옵니다.-->
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- validate plugin CDN -->
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
<!-- 드롭다운을 위해 가져옵니다.-->
<script src="<%=request.getContextPath()%>/assets/js/jquery.selectric.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    function myPage() {
        window.location.href = "<%=request.getContextPath()%>/myPage/sell"
    }

    history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
    };

</script>
</body>
</html>