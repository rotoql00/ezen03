<!--
FileName: product.html
Description: 상품상세 페이지 입니다.
Author: 임석현
-->

<!--
#product_name: 제품명
#product_name_ko: 한국어 제품명
#product_condition: 상품 상태
#product_check: 검수 상태
#product_name_fake: 제품명 (상단 1070px 미만)
#product_name_ko_fake: 한국어 제품명 (상단 1070px 미만)
#product_condition_fake: 상품 상태 (상단 1070px 미만)
#product_check_fake: 검수 상태 (상단 1070px 미만)
#product_buy: 상품 입찰버튼
#product_sell: 상품 판매버튼
#product_style: 상품 스타일 코드
#product_color: 상품 색상
#product_release_price: 발매가
#product_release_date: 발매일
#product_maxPrice: 최고 판매가
#product_minPrice: 최저 판매가
** 옵션별 가격 표에대한 ID **
#product_price_high_1: 최고 입찰가 (첫번째)
#product_price_high_2: 최고 입찰가 (두번째)
#product_price_high_3: 최고 입찰가 (세번째)
#product_size_1: 최고입찰가의 발사이즈 (첫번째)
#product_size_2: 최고입찰가의 발사이즈 (두번째)
#product_size_3: 최고입찰가의 발사이즈 (세번째)
#product_price_recent_1: 최근거래가 (첫번째)
#product_price_recent_2: 최근거래가 (두번째)
#product_price_recent_3: 최근거래가 (세번째)
#product_price_low_1: 최저판매가 (첫번쨰)
#product_price_low_1: 최저판매가 (두번쨰)
#product_price_low_1: 최저판매가 세번쨰)
#product_currentBuy: 입찰가 현황 버튼
#product_currentSell: 판매가 현황 버튼
#product_currentTranscation: 거래 가격 버튼
#buy_highPrice: buy popup의 최고입찰가
#buy_selectOption: buy popup의 옵션선택(최근 거래가)
#buy_lowPrice : buy popup의 최저판매가
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <title>Title</title>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/review.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/product.css">
    <link rel="stylesheet"
          href="https://naver.github.io/billboard.js/release/latest/dist/billboard.min.css">
    <link rel="stylesheet"
          href="https://naver.github.io/billboard.js/release/latest/dist/theme/insight.min.css">
    <link rel="icon"
          href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/selectric.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/review.css">
</head>

<body>
<div id="mask"></div>
<!-- Header -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>


<!-- container -->
<div id="container">

    <!-- 1070px 이하 -->
    <div class="product_title_fake">
        <h2 class='product_name_fake'>${productOutput.productNameEN}</h2>
        <h3 class='product_name_ko_fake'>${productOutput.productNameKR}</h3>
        <div class="product_condition_fake_form">
            <span class='product_condition_fake'>상태 new</span> <span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
            <span class='product_check_fake'>인증 100% 검수</span>
        </div>
    </div>

    <div id="left_container">
        <!-- 상품 이미지 -->
        <div id="product_image">
            <img
                    src="${context}${productOutput.productImgPath}"
                    alt="<%=request.getContextPath()%>${productOutput.productBrandEN}">
        </div>
    </div>
    <!-- 상품명 (영어, 한국어)-->
    <div class="right_container">

        <!-- 일반 -->
        <div id="product_title">

            <h2 class="product_name">${productOutput.productNameEN}</h2>
            <h3 class="product_name_ko">${productOutput.productNameKR}</h3>
            <span class="product_condition">상태 new</span> <span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
            <span class="product_check">인증 100% 검수</span>
        </div>
        <!-- Buy & Sell 버튼-->
        <div class="sell_buy_button">
            <span><button type="button" class="product_button"
                          id="product_buy"
                    <c:choose>
                        <c:when test="${empty sessionScope.loginID}">
                            onclick="login()"
                        </c:when>
                        <c:when test="${sessionScope.UserPenalty eq 'Y'}">
                            onclick="penalty()"
                        </c:when>
                        <c:otherwise>
                            onclick="buyButton()"
                        </c:otherwise>
                    </c:choose>>Buy</button></span>
            <span><button type="button" class="product_button"
                          id="product_sell"
                    <c:choose>
                        <c:when test="${empty sessionScope.loginID}">
                            onclick="login()"
                        </c:when>
                        <c:when test="${sessionScope.UserPenalty eq 'Y'}">
                            onclick="penalty()"
                        </c:when>
                        <c:otherwise>
                            onclick="sellButton()"
                        </c:otherwise>
                    </c:choose>>Sell</button></span>
        </div>

        <div class="product_buy">
            <div class="buyPopup_Wrapper Popup_Wrapper">
                <div class="buyPopup">
                    <div class="popupline_white"></div>
                    <div class="buyPopup_close close_white"></div>
                    <div class="buy_PopupContent">
                        <h1 class="buy_contentBuy">BUY</h1>
                        <table id="buy_price_table" class="buy_table">
                            <thead>
                            <tr>
                                <th>최고입찰가</th>
                                <th>옵션선택(최근 거래가)</th>
                                <th>최저 판매가</th>
                            </tr>
                            </thead>
                            <form action="<%=request.getContextPath()%>/buy.jsp"
                                  METHOD="post">
                                <tbody id="BuyPopupInfo">
                                </tbody>
                            </form>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="product_sell">
            <div class="sellPopup_Wrapper Popup_Wrapper">
                <div class="sellPopup">
                    <div class="popupline_white"></div>
                    <div class="sellPopup_close close_white"></div>
                    <div class="sell_PopupContent">
                        <h1 class="sell_contentSell">SELL</h1>
                        <table class="sell_table" id="sell_price_table">
                            <thead>
                            <tr>
                                <th>최고입찰가</th>
                                <th>옵션선택(최근 거래가)</th>
                                <th>최저 판매가</th>
                            </tr>
                            </thead>
                            <tbody id="SellPopupInfo">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- 이용방법 -->
        <div class="howToUse clear">
            <p>
                가격을 등록하세요. 안전거래가 시작됩니다. &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                <button class="userGuidebutton">이용안내</button>
                <br> "거래가 성사되지 않으면 결제되지 않습니다."
            </p>

            <!--이용방법 팝업-->
            <div class="userGuidePopup_Wrapper Popup_Wrapper">
                <div class="userGuidePopup Popup">
                    <div class="popupline"></div>
                    <div class="userGuidePopup_close close"></div>
                    <div class="userGuidePopupContent">
                        <h1>이용 안내</h1>
                        <img src="${context}/assets/img/contents.png"
                             alt="이용안내">
                    </div>
                </div>
            </div>
        </div>
        <!-- EOF 이용방법 끝-->

        <div class="product_allprice">
            <span class="option_2" style="color:black">옵션별 가격</span> <span>
               <button type="button" class="current_status buy_button"
                       id="product_currentBuy" onclick="connector(2)">입찰가 현황</button>
            </span> <span>
               <button type="button" class="current_status sell_button"
                       id="product_currentSell" onclick="connector(1)">판매가 현황</button>
            </span> <span>
               <button type="button" class="current_status deal_button"
                       id="product_currentTranscrion" onclick="connector(3)">거래가격</button>
            </span>
        </div>

        <!-- 판매가에 대한 팝업-->
        <%@ include file="/WEB-INF/views/inc/popupCurrent.jsp" %>

        <!-- 최고 입찰가 옵션 최저판매가를 출력 (JAVASCRIPT와 연동되면 수정해야합니다)-->
        <table>
            <tr class="element_table">
                <th>최고 입찰가</th>
                <th>옵션 (최근 거래가)</th>
                <th>최저 판매가</th>
            </tr>
            <tr class="element_table">
                <!-- 수정할 수 있기때문에 class와 ID 같이 사용하였습니다-->
                <td class="blue" id="product_price_high_1">￦ <c:choose>
                    <c:when test="${empty buyHighPrice260.buyHighest260}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${buyHighPrice260.buyHighest260}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>
                </td>
                <td><span id="product_size_1">260</span>&nbsp&nbsp <span
                        id="product_price_recent_1">(￦ <c:choose>
                    <c:when test="${empty recent260.orderPrice}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${recent260.orderPrice}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>)
               </span></td>
                <td><span id="product_price_low_1">￦ <c:choose>
                    <c:when test="${empty sellLowPrice260.sellLowest260}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${sellLowPrice260.sellLowest260}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>
               </span></td>
            </tr>
            <tr class="element_table">
                <td class="blue" id="product_price_high_2">￦ <c:choose>
                    <c:when test="${empty buyHighPrice265.buyHighest265}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${buyHighPrice265.buyHighest265}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>
                </td>
                <td><span id="product_size_2">265</span>&nbsp&nbsp <span
                        id="product_price_recent_2">(￦ <c:choose>
                    <c:when test="${empty recent265.orderPrice}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${recent265.orderPrice}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>)
               </span></td>
                <td><span id="product_price_low_2">￦ <c:choose>
                    <c:when test="${empty sellLowPrice265.sellLowest265}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${sellLowPrice265.sellLowest265}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>
               </span></td>
            </tr>
            <tr class="element_table">
                <td class="blue" id="product_price_high_3">￦ <c:choose>
                    <c:when test="${empty buyHighPrice270.buyHighest270}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${buyHighPrice270.buyHighest270}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>
                </td>
                <td><span id="product_size_3">270</span>&nbsp&nbsp <span
                        id="product_price_recent_3">(￦ <c:choose>
                    <c:when test="${empty recent270.orderPrice}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${recent270.orderPrice}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>)
               </span></td>
                <td><span id="product_price_low_3">￦ <c:choose>
                    <c:when test="${empty sellLowPrice270.sellLowest270}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${sellLowPrice270.sellLowest270}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>
               </span></td>
            </tr>
            <tr class="element_table">
                <th colspan="3">
                    <button type="button" class="product_more" onclick="connector(2)">더보기+</button>
                </th>
            </tr>
        </table>

        <!-- 상품 정보-->
        <div class="product_detail">
            <p style="color:black">상품정보</p>
            <hr class="hr_solid">
            <div id="product_info">

                <table>
                    <tr>
                        <th class="bold">스타일</th>
                        <th class="product_style">${productOutput.productCode}</th>
                    </tr>
                    <tr>
                        <th class="bold">색상</th>
                        <th class="product_color">${productOutput.productColor}</th>
                    </tr>
                    <tr>
                        <th class="bold">발매가</th>
                        <th class="product_release_price">${productOutput.productUnit}&nbsp;
                            <span id="productUnit"> <fmt:formatNumber
                                    value="${productOutput.productReleasePrice}" pattern="#,###"/>
                     </span>
                        </th>
                    </tr>
                    <tr>
                        <th class="bold">발매일</th>
                        <th class="product_release_date">${productOutput.productReleaseDate}</th>
                    </tr>
                </table>
            </div>
            <!-- 그래프가 들어갈 자리입니다. (미정)-->
            <div class="recent_transaction">
                <div class="product_detail">
                    <p style="color:black">최근 판매 내역</p>
                    <hr class="hr_solid">
                    <div id="overlayText" class="overlay_text hidden">최근 판매가 한건 이상 존재하지 않습니다</div>
                    <div id="overlay" class="overlay hidden">
                    </div>
                    <!-- 그래프가 들어갈 자리를 공백처리하였습니다-->
                    <div id="xAxisTickAutorotate_2" class="rel"></div>
                </div>
                <!-- 1년간 판매 이력-->
                <div class="product_detail">
                    <p style="color:black">1년간 판매 이력</p>

                    <hr class="hr_solid">
                    <div class="product_price">
                        <p class="max">최고 판매가</p>
                        <span id="product_maxPrice">￦ <c:choose>
                            <c:when test="${empty highestPrice.orderPrice}">
                                0
                            </c:when>
                            <c:otherwise>
                                <fmt:formatNumber value="${lowestPrice.orderPrice}"
                                                  pattern="#,###"/>
                            </c:otherwise>
                        </c:choose>
                     </span>
                    </div>
                    <hr class="hr_fade">
                    <div class="product_price">
                        <p class="min">최저 판매가</p>
                        <span id="product_minPrice">￦ <c:choose>
                            <c:when test="${empty lowestPrice.orderPrice}">
                                0
                            </c:when>
                            <c:otherwise>
                                <fmt:formatNumber value="${highestPrice.orderPrice}"
                                                  pattern="#,###"/>
                            </c:otherwise>
                        </c:choose>
                     </span>
                    </div>
                    <hr class="hr_fade">
                </div>
            </div>
        </div>
    </div>
    <!-- 리뷰 영역 시작 -->
    <div class="bottom_container" id="reviewContainer">
        <p style="font-weight: bold; color:black">상품평</p>
        <hr class="hr_solid limit">
        <div>
            <p style="font-weight: bold; font-size: 17px; color:black">${productOutput.productNameKR}의 상품평</p>
        </div>
        <!-- useid -->

        <c:choose>
            <c:when test="${empty sessionScope.loginID}">
                <input type="hidden" id="user_ID" value="0"/>
            </c:when>
            <c:when test="${not empty sessionScope.loginID}">
                <input type="hidden" id="user_ID" value="${sessionScope.loginID}"/>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${empty sessionScope.loginID}">
                <input type="hidden" id="user_ID" value="0"/>
            </c:when>
            <c:when test="${not empty sessionScope.loginID}">
                <input type="hidden" id="user_ID" value="${sessionScope.loginID}"/>
            </c:when>
        </c:choose>

        <!-- 리뷰 작성 시작 -->
        <div class="u_cbox_write_box" id="reviewWriter" style="width: 80%; /* display: none; */ ">
            <form class="reviewForm">
                <fieldset>
                    <div class="u_cbox_write">
                        <div class="u_cbox_write_inner">
                            <!-- 별레이팅 시작 -->
                            <div class="reviewTopBox">
                                <div style="float: left; color:black; font-weight: bold; padding-top:3px">나의 평점</div>
                                <div class="ratingFieldset">
                                    <fieldset class="rating" id="FieldsetRating">
                                        <!-- 5점 -->
                                        <input type="radio" id="star5" name="rating" value="5" checked="checked"/>
                                        <label class="full" for="star5" title="Awesome - 5 stars"></label>
                                        <!-- 4.5점 -->
                                        <!-- <input type="radio" id="star4half" name="rating" value="4.5"/>
                                        <label class="half" for="star4half" title="Pretty good - 4.5 stars"></label> -->
                                        <!-- 4점 -->
                                        <input type="radio" id="star4" name="rating" value="4"/>
                                        <label class="full" for="star4" title="Pretty good - 4 stars"></label>
                                        <!-- 3.5점 -->
                                       <!--  <input type="radio" id="star3half" name="rating" value="3.5"/>
                                        <label class="half" for="star3half" title="Meh - 3.5 stars"></label> -->
                                        <!-- 3점 -->
                                        <input type="radio" id="star3" name="rating" value="3"/>
                                        <label class="full" for="star3" title="Meh - 3 stars"></label>
                                        <!-- 2.5점 -->
                                        <!-- <input type="radio" id="star2half" name="rating" value="2.5"/>
                                        <label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label> -->
                                        <!-- 2점 -->
                                        <input type="radio" id="star2" name="rating" value="2"/>
                                        <label class="full" for="star2" title="Kinda bad - 2 stars"></label>
                                        <!-- 1.5점 -->
                                       <!--  <input type="radio" id="star1half" name="rating" value="1.5"/>
                                        <label class="half" for="star1half" title="Meh - 1.5 stars"></label> -->
                                        <!-- 1점 -->
                                        <input type="radio" id="star1" name="rating" value="1"/>
                                        <label class="full" for="star1" title="Sucks big time - 1 star"></label>
                                        <!-- 0.5점 -->
                                        <!-- <input type="radio" id="starhalf" name="rating" value="0.5"/>
                                        <label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label> -->
                                    </fieldset>
                                </div>
                            </div>
                            <!-- 별레이팅 끝 -->
                            <!--u_cbox_profile_area-->
                            <div class="u_cbox_write_area review_push">
                                <div class="u_cbox_inbox">
                              <textarea id="cbox_module__write_textarea" class="u_cbox_text"
                                        rows="3" cols="30"
                                        style="width: 100%; min-height: 100px; resize: none; font-size: 17px; "
                                        maxlength="200"
                                        placeholder="저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은 이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다. 건전한 토론문화와 양질의 댓글 문화를 위해, 타인에게 불쾌감을 주는 욕설 또는 특정 계층/민족, 종교 등을 비하하는 단어들은 표시가 제한됩니다."></textarea>
                                </div>
                            </div>
                            <!--u_cbox_write_area-->
                            <div class="u_cbox_write_count">
                           <span id="reviewCounter"
                                 style="float: left; margin-right: 30px;">0/200</span>
                            </div>
                            <div class="uk-position-relative" style="height: 40px; margin-top: 10px;">
                                <button id="addMyReviewBTN" type="button"
                                        class="uk-button uk-button-secondary uk-position-right ">등록
                                </button>
                            </div>
                            <!--u_cbox_upload-->
                        </div>
                        <!--u_cbox_write_inner-->
                    </div>
                    <!--u_cbox_write-->
                </fieldset>
            </form>
        </div>
        <!-- 리뷰 작성 끝 -->
        <div id="reviewList">
            <input type="hidden" id="productID" value="${productOutput.ID}">
            <table id="reviewTable" class="boardTable uk-table uk-table-hover uk-table-divider" border="1">
                <thead>
                <tr class="boardTR">
                    <th class="thP" width="100" align="center" style="text-align: center;">평점</th>
                    <th class="thP" width="200" align="center" style="text-align: center;">리뷰</th>
                    <th class="thP" width="70" align="center" style="text-align: center;">작성자</th>
                    <th class="thP" width="100" align="center" style="text-align: center;">작성일</th>
                </tr>
                </thead>
                <tbody class="text-center">
                <c:choose>
                    <%-- 조회결과가 없는 경우 --%>
                    <c:when test="${reviewOPT == null || fn:length(reviewOPT) == 0}">
                        <tr>
                            <td colspan="4" align="center">작성된 리뷰가 없습니다.</td>
                        </tr>
                    </c:when>
                    <%-- 조회결과가 있는  경우 --%>
                    <c:otherwise>
                        <%-- 조회 결과에 따른 반복 처리 --%>
                        <c:forEach var="item" items="${reviewOPT}" varStatus="status">
                            <%-- 출력을 위해 준비한 제목과 위치 --%>
                            <c:set var="reviewContext" value="${item.reviewContext}"/>
                            <c:set var="reg_date" value="${item.reg_date}"/>

                            <%-- 상세페이지로 이동하기 위한 URL --%>
                            <c:url value="/board/view" var="viewUrl">
                                <c:param name="ID" value="${item.reviewNum}"/>
                            </c:url>
                            <tr id="frontTr" class="boardList uk-accordion='multiple: true'">

                                <td class="tdP" align="center" value="${item.reviewRating}">${item.reviewRating}</td>
                            	                            	
                                <td class="tdP" align="center" value="${item.reviewRating}">${item.reviewRating}
                                	<c:if test="${item.reviewRating == 1}">
										<p>★☆☆☆☆</p>
									</c:if>

									<c:if test="${item.reviewRating == 2}">
										<p>★★☆☆☆</p>
									</c:if>

									<c:if test="${item.reviewRating == 3}">
										<p>★★★☆☆</p>
									</c:if>

									<c:if test="${item.reviewRating == 4}">
										<p>★★★★☆</p>
									</c:if>

									<c:if test="${item.reviewRating == 5}">
										<p>★★★★★</p>
									</c:if>
                                </td>
                                <td class="tdPContext" align="center" style="cursor: pointer;">
                                    <span>${reviewContext}</span></td>
                                <td class="tdP" align="center">${item.userName}</td>
                                <td class="tdP" align="center">${reg_date}</td>
                            </tr>
                            <tr id="bottomTr" class="FullContext">

                                <td colspan="4">
                                    <p>${reviewContext}</p>
                                    <button class="uk-button uk-button-primary deleteReviewBTN" value="${item.reviewNum}" style="display: none; margin-top: 20px; float: right;">
                                	    리뷰 삭제</button>
                                    <c:if test="${sessionScope.loginID eq item.user_ID}">
										<script>
											$(".deleteReviewBTN").show();
										</script>
									</c:if>
									<c:if test="${sessionScope.UserAdmin eq 'Y'}">
										<script>
											$(".deleteReviewBTN").show();
										</script>
									</c:if>
                                </td>

                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>

            <!-- 페이지 번호 구현 시작 -->
            <ul class="uk-pagination add_uk-pagination" uk-margin>
                <%-- 이전 그룹에 대한 링크 --%>
                <c:choose>
                    <%-- 이전 그룹으로 이동 가능하다면? --%>
                    <c:when test="${pageData.prevPage > 0}">
                        <%-- 이동할 URL 생성 --%>
                        <c:url value="/product?productNum=${productOutput.ID}"
                               var="prevPageUrl">
                            <c:param name="page" value="${pageData.prevPage}"/>
                            <c:param name="keyword" value="${keyword}"/>
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
                    <c:url value="/product?productNum=${productOutput.ID}" var="pageUrl">
                        <c:param name="page" value="${i}"/>
                        <c:param name="keyword" value="${keyword}"/>
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
                        <c:url value="/product?productNum=${productOutput.ID}"
                               var="nextPageUrl">
                            <c:param name="page" value="${pageData.nextPage}"/>
                            <c:param name="keyword" value="${keyword}"/>
                        </c:url>
                        <a href="${nextPageUrl}"><span uk-pagination-next></span></a>
                    </c:when>
                    <c:otherwise>
                        <span uk-pagination-next></span>
                    </c:otherwise>
                </c:choose>
            </ul>
            <!-- 페이지 번호 구현  끝-->
        </div><!-- <div id="reviewList"> -->
    </div><!-- bottom_container -->
    <!-- 리뷰 영역 끝 -->
</div>

<!-- 넘기기위한 FORM-->
<form id="push" action="<%=request.getContextPath()%>/buy"
      method="post">
    <div class="hidden">
        <input id="sizePush" type="text" name="size"> <input
            id="productNumPush" type="text" name="productNum"
            value="${productOutput.ID}">
    </div>
</form>

<!-- 넘기기위한 FORM-->
<form id="push2" action="<%=request.getContextPath()%>/sell"
      method="post">
    <div class="hidden">
        <input id="sizePush2" type="text" name="size"> <input
            id="productNumPush2" type="text" name="productNum"
            value="${productOutput.ID}">
    </div>
</form>

<script id="BuyPopupInfo-tmpl" type="text/x-handlebars-template">
    {{#each complex}}
    <tr id="buyPopupSubmit" onclick="buyPopupSubmit({{size}})">
        <td id="buy_highPrice">₩ {{comma buyHighPrice}}</td>
        <td id="buy_selectOption">{{size}}(₩ {{comma buyRecentPrice}})</td>
        <td id="buy_lowPrice">₩ {{comma buyLowPrice}}</td>
    </tr>
    {{/each}}
</script>

<script id="SellPopupInfo-tmpl" type="text/x-handlebars-template">
    {{#each complex}}
    <tr id="sellPopupSubmit" onclick="sellPopupSubmit({{size}})">
        <td id="sell_highPrice">₩ {{comma buyHighPrice}}</td>
        <td id="sell_selectOption">{{size}}(₩ {{comma buyRecentPrice}})</td>
        <td id="sell_lowPrice">₩ {{comma buyLowPrice}}</td>
    </tr>
    {{/each}}
</script>

<!-- HandleBar-->
<!--Footer -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
        src="<%=request.getContextPath()%>/assets/js/jquery.selectric.min.js"></script>
<script src="https://d3js.org/d3.v5.min.js"></script>
<script
        src="https://naver.github.io/billboard.js/release/latest/dist/billboard.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
<script
        src="<%=request.getContextPath()%>/assets/AJAX_helper/ajax_helper.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="<%=request.getContextPath()%>/assets/js/product.js"></script>

<script>
    var onoff = 0;
    var sizeControlVal = 0;

    //AJAX를 하기위해 1 2 3으로 나누어 호출합니다.
    function connector(option) {
        //페이징이 겹치지 않게 하기 위한 page 변수 입니다.
        $('#sizeOption').prop('selectedIndex', 0).selectric('refresh');
        if (option === 1) {
            sizeControlVal = 1;
            //판매가 가격
            sellPopup(1);
        } else if (option === 2) {
            sizeControlVal = 2;
            //입찰가 가격
            buyPopup(1);
        } else {
            sizeControlVal = 3;
            orderPopup(1);
        }
    }

    function sizeControl() {
        if (sizeControlVal == 1) {
            sellPopup(1);
        } else if (sizeControlVal == 2) {
            buyPopup(1);
        } else {
            orderPopup(1);
        }
    }

    function sellPopup(page) {
        var value = ($('#sizeOption').val());
        // Restful API에 GET 방식 요청'
        $("#paginationSell").empty();
        $.post("${pageContext.request.contextPath}/sellPopupCurrent.do", {
            productNum: ${productOutput.ID},
            page: page,
            size: value
        }, function (json) {
            var source = $("#sellPopup-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            $("#sellPopup").html(result); // 최종 결과물을 #list 요소에 추가한다.
            //페이징을 위한 구문입니다.
            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#paginationSell").append("<li style='cursor: pointer' onclick='sellPopup(" + json.page.prevPage + ")'><span uk-pagination-previous></span></a></li>")
            }
            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#paginationSell").append("<li class=\"uk-active\" style='cursor: pointer'><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#paginationSell").append("<li style='cursor: pointer' onclick='sellPopup(" + i + ")'>" + i + "</a></li>")
                }
            }
            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#paginationSell").append("<li style='cursor: pointer' onclick='sellPopup(" + json.page.nextPage + ")'><span uk-pagination-next></span></a></li>")
            }
        });
        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        })
    }

    function buyPopup(page) {
        var value = ($('#sizeOption').val());
        $("#paginationBuy").empty();
        // Restful API에 Post 방식 요청
        $.post("${pageContext.request.contextPath}/buyPopupCurrent.do", {
            productNum: ${productOutput.ID},
            page: page,
            size: value
        }, function (json) {
            var source = $("#buyPopup-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            $("#buyPopup").html(result); // 최종 결과물을 #list 요소에 추가한다.
            //페이징을 위한 구문입니다.
            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#paginationBuy").append("<li style='cursor: pointer' onclick='buyPopup(" + json.page.prevPage + ")'><span uk-pagination-previous></span></a></li>")
            }
            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#paginationBuy").append("<li class=\"uk-active\" style='cursor: pointer'><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#paginationBuy").append("<li style='cursor: pointer' onclick='buyPopup(" + i + ")'>" + i + "</a></li>")
                }
            }
            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#paginationBuy").append("<li style='cursor: pointer' onclick='buyPopup(" + json.page.nextPage + ")'><span uk-pagination-next></span></a></li>")
            }
        });
        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        })
    }

    function orderPopup(page) {
        var value = ($('#sizeOption').val());
        $("#paginationOrder").empty();
        $.post("${pageContext.request.contextPath}/orderPopupCurrent.do", {
            productNum: ${productOutput.ID},
            page: page,
            size: value
        }, function (json) {
            var source = $("#orderPopup-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            $("#orderPopup").html(result); // 최종 결과물을 #list 요소에 추가한다.
            //페이징을 위한 구문입니다.
            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#paginationOrder").append("<li style='cursor: pointer' onclick='orderPopup(" + json.page.prevPage + ")'><span uk-pagination-previous></span></a></li>")
            }
            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#paginationOrder").append("<li class=\"uk-active\" style='cursor: pointer'><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#paginationOrder").append("<li style='cursor: pointer' onclick='orderPopup(" + i + ")'>" + i + "</a></li>")
                }
            }
            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#paginationOrder").append("<li style='cursor: pointer' onclick='orderPopup(" + json.page.nextPage + ")'><span uk-pagination-next></span></a></li>")
            }
        });
        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        })
    }

    function buyButtonDB() {
        $.post("${pageContext.request.contextPath}/buyButton.do", {
            ID: ${productOutput.ID}
        }, function (json) {
            var source = $("#BuyPopupInfo-tmpl").html();   // 템플릿 코드 가져오기
            var source1 = $("#SellPopupInfo-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var template1 = Handlebars.compile(source1);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#BuyPopupInfo").html(result); // 최종 결과물을 #list 요소에 추가한다.
            $("#SellPopupInfo").html(result1); // 최종 결과물을 #list 요소에 추가한다.
        });
        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        })
    }

    function buyPopupSubmit(size) {
        document.getElementById("sizePush").setAttribute("value", size);
        document.getElementById("push").submit();
    }

    function sellPopupSubmit(size) {
        document.getElementById("sizePush2").setAttribute("value", size);
        document.getElementById("push2").submit();
    }

    $.post("${pageContext.request.contextPath}/productGraph.do", {
        productNum: ${productOutput.ID}
    }, function (json) {
        console.log(json)
        var xtickArray = [];
        var priceArray = [];
        if (json.chartData.length <= 1) {
            console.log("최근거래가 데이터가 존재하지 않습니다")
            // 데이터가 없거나 하나이면 overlay를 배치합니다
            document.getElementById("overlay").classList.remove("hidden");
            document.getElementById("overlayText").classList.remove("hidden");
            priceArray = ["29.39", "29.7", "29.37", "28.87", "28.62", "27.72", "27.61", "27.82", "27.48", "26.78", "26.62", "26.64", "26.29", "26.01", "25.84", "25.07", "24.85", "24.01", "23.83", "22.8", "23", "22.64", "22.77", "22.64"]
            xtickArray = ["2018-01-01", "2018-02-01", "2018-03-01", "2018-04-01", "2018-05-01", "2018-06-01", "2018-07-01", "2018-08-01", "2018-09-01", "2018-10-01", "2018-11-01", "2018-12-01", "2019-01-01", "2019-02-01", "2019-03-01", "2019-04-01", "2019-05-01", "2019-06-01", "2019-07-01", "2019-08-01", "2019-09-01", "2019-10-01", "2019-11-01", "2019-12-01"]
        } else {
            for (var i = 0; i < json.chartData.length; i++) {
                var data = json.chartData[i];
                var xtick = data.reg_date;
                var price = data.orderPrice;
                xtickArray.push(xtick);
                priceArray.push(price);
            }
        }
        var chart2 = bb.generate({
            data: {
                json: {
                    가격: priceArray,
                    x: xtickArray
                },
                x: "x",
                type: "area",
                xFormat: "%Y-%m-%d"
            },
            size: {
                height: 350,
            },
            axis: {
                x: {
                    tick: {
                        fit: true,
                        multiline: false,
                        culling: false,
                        rotate: 90,
                        format: "%m-%d"
                    },
                    type: "timeseries"
                }
            },
            bindto: "#xAxisTickAutorotate_2"
        });
    })

    // 금칙어 배열
    banWordArr = ["fuck", "쓰발", "씨발", "시발", "시펄", "니미", "조또", "개", "sex", "섹스", "자지", "보지", "쥬지", "뷰지", "좆", "admin"];

    /** review 등록 버튼 ajax 중복방지 */
    var overlapFlag = false;
    $("#addMyReviewBTN").click(function () {
        if ($("#user_ID").val() == "0") {
            var Toast = Swal.fire({
                icon: 'error',
                title: '로그아웃상태',
                text: '리뷰를 작성하기 위해서는 로그인을 해주세요.',
            })
        } else {
            // 금칙어 필터링
            var check = $("#cbox_module__write_textarea").val();
            let j = 0;
            for (; j < banWordArr.length; j++) {
                if (check.indexOf(banWordArr[j]) > -1) {
                    var Toast = Swal.fire({
                        icon: 'error',
                        title: '금칙어 입력',
                        text: banWordArr[j] + ' 은(는) 금칙어 입니다.',
                    })
                    $("#cbox_module__write_textarea").select();
                    return false;
                }
            }

            // 연속 입력 방지
            if (overlapFlag == true) {
                var Toast = Swal.fire({
                    icon: 'warning',
                    title: '연속 입력 방지',
                    text: '잠시만 기다려 주세요.',
                })
                //settime 5초가 걸려 있는 상태이므로 다시 되돌린다.
                overlapFlag = false;
                return;
            }

            overlapFlag = true;

            // 댓글 공백 방지
            var checkContext = $("#cbox_module__write_textarea").val();
            if (checkContext == "") {
                var Toast = Swal.fire({
                    icon: 'error',
                    title: '공백금지',
                    text: '리뷰 내용을 작성해주세요.',
                })
                return false;
            }

            var query = {
                reviewRating: $("input[name = rating]:checked").val(),
                reviewContext: $("#cbox_module__write_textarea").val(),
                user_ID: $("#user_ID").val(), //${sessionScope.loginID}, 비로그인시 script에러로 인해 대체 
                product_ID: ${productOutput.ID}
            };

	            $.ajax({
	                url: "${pageContext.request.contextPath}/review/add",
	                type: "post",
	                data: query,
	                success: function (data) {
	                    console.log(data)
	                    if (data.checkReview > 0) {
	                        var Toast = Swal.fire({
	                            icon: 'error',
	                            title: '리뷰 중복',
	                            text: '이미 해당 제품에 리뷰를 작성하셨습니다.',
	                        })
	                        return false;
	                    } else {
	                        var Toast = Swal.fire({
	                            icon: 'success',
	                            title: '리뷰등록완료',
	                            text: '리뷰를 작성해 주셔서 감사합니다.',
	                        })
	                        setTimeout(function () {
	                            overlapFlag = false;
	                        }, 5000);
	                        //$("#reviewList").load(" #reviewList");
	                        location.reload();
	                        $("#cbox_module__write_textarea").val("");
	                        //$( "#reviewContainer" ).load(" #reviewContainer");
	                        //$("#reviewWriter").hide();
	                        //$("#reviewCompletion").show();
	                    }// end else
	                } // end success
	            }); // end ajax
        } // end else (로그인 상태 기능)
    }); // end review 등록

    /** 리뷰 아코디언 */
    $(function () {
        var article = (".boardTable .show");
        $("#reviewTable #frontTr td").click(function () {
            var myArticle = $(this).parents().next("tr");
            if ($(myArticle).hasClass("FullContext")) {
                $(article).removeClass('show').addClass('FullContext')
                $(myArticle).removeClass('FullContext').addClass('show');
            } else {
                $(myArticle).addClass('FullContext').removeClass('show');
            }
        });// end click func
    });// end toggle func


    /** review 글씨 카운팅 */
    $('#cbox_module__write_textarea').keyup(function (e) {
        var content = $(this).val();
        $(this).height(((content.split('\n').length + 1) * 1.5) + 'em');
        $('#reviewCounter').html(content.length + '/200');
        /** 텍스트 제한 */
        if (content.length > 200) {
            $(this).val($(this).val().substring(0, 200));
        }
    });
    $('#reviewCounter').keyup();


   $(".deleteReviewBTN").click(function(){
	   var deleteNum = $(this).val();
	   console.log(deleteNum);
	   var query = {
			   reviewNum: $(this).val()
	   };

	   $.ajax({
		   url: "${pageContext.request.contextPath}/review/delete",
		   type: "DELETE",
           data: query,
           success: function (data) {
        	   console.log(data)
        	   if(data.rt=="OK") {
        		   var Toast = Swal.fire({
                       icon: 'success',
                       title: '리뷰 삭제 완료',
                       text: '작성한 리뷰가 삭제 되었습니다.',
                   })
                   location.reload();
        	   }
           }
	   })//end ajax
   });


</script>

</body>

</html>