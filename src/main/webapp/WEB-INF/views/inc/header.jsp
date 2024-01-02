<!--
FileName: header.html
Description: 헤더 (navigation bar)에 대한 HTML입니다.
Author: 임석현
Additional Info: INCLUDE ONLY
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Common CSS-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/search.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
<link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.ico"/>
<!-- Jquery 3.4.1 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- UIkit -->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<!-- Handle Bar-->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!-- common JS -->
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.vticker-min.js"></script>


<!-- UI KIT를 사용합니다-->

<!-- 최상단 메뉴 -->
<div class="uk-clearfix header_width">
    <ul class="uk-float-left top_header_left">
        <li onclick="addFavorite()"><a>즐겨찾기</a></li>
    </ul>
    <ul class="uk-float-right top_header_right">
        <c:if test="${empty sessionScope.UserName}">
            <li><a href="<%=request.getContextPath()%>/login">로그인</a></li>
            <li><a href="<%=request.getContextPath()%>/join">회원가입</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.UserName}">
            <li><a href="<%=request.getContextPath()%>/myPage"><span
                    class="userInfo_session">${sessionScope.UserName}</span></a>님
            </li>
            <li><a href="<%=request.getContextPath()%>/logout">로그아웃</a></li>
        </c:if>


        <li><a href="${pageContext.request.contextPath}/faq">고객센터</a></li>
    </ul>
</div>
<!-- 나누는 hr-->
<hr class="top_divider">

<!-- 중간 서치 메뉴-->
<div uk-sticky="sel-target: .uk-navbar-container; cls-active: uk-navbar-sticky; bottom: #transparent-sticky-navbar"
     class="onTop">
    <nav class="uk-navbar-container nav_container navbarContainer" uk-navbar>
        <div class="header_width" uk-navbar>
            <!-- 왼쪽에 배치합니다-->
            <div class="uk-navbar-left">
                <a class="uk-navbar-item" href="<%=request.getContextPath()%>/"> <!-- 로고 -->
                    <div class="logo">
                        TIE SHOE
                    </div>
                </a>
            </div>

            <!-- 가운데 배치-->
            <div class="uk-navbar-center">
                <div class="uk-navbar-item">
                    <!-- 검색 바-->

                    <form id="search" action="${pageContext.request.contextPath}/detailSearch">
                        <div class="uk-inline">
                            <a class="uk-form-icon uk-form-icon-flip icon_color" uk-icon="icon: search; ratio: 1.2"
                               onclick="summitSearch()"></a>
                            <label class="search_label" for="searchTitle"></label>
                            <input id="searchTitle" name="search"
                                   class="uk-input uk-form-width-large searchStyle"
                                   type="text"
                                   placeholder="검색" onkeydown="submitEnter(event)" value="${param.search}">
                            <input type="submit" hidden>
                        </div>

                    </form>

                </div>
            </div>
            <!-- 오른쪽에 배치합니다-->
            <div class="uk-navbar-right">
                <div class="uk-navbar-item">
                    <c:if test="${empty sessionScope.UserName}">
                        <a href="<%=request.getContextPath()%>/login"
                           class="uk-icon-link uk-margin-small-right icon_color"
                           uk-icon="icon: user; ratio: 1.5"></a>
                        <span>&nbsp;&nbsp;&nbsp;</span>
                        <a href="<%=request.getContextPath()%>/login"
                           class="uk-icon-link uk-margin-small-right icon_color"
                           uk-icon="icon: cart; ratio: 1.5"></a>
                    </c:if>
                    <c:if test="${not empty sessionScope.UserName && sessionScope.UserAdmin eq 'N'}">
                        <a href="<%=request.getContextPath()%>/myPage"
                           class="uk-icon-link uk-margin-small-right icon_color"
                           uk-icon="icon: user; ratio: 1.5"></a>
                        <span>&nbsp;&nbsp;&nbsp;</span>
                        <a href="#" class="uk-icon-link uk-margin-small-right icon_color"
                           uk-icon="icon: cart; ratio: 1.5"
                           uk-toggle="target: #toggle-animation-multiple; animation:  uk-animation-fade, uk-animation-fade uk-animation-reverse"></a>
                    </c:if>
                    <c:if test="${sessionScope.UserAdmin eq 'Y'}">
                        <a href="<%=request.getContextPath()%>/myPage"
                           class="uk-icon-link uk-margin-small-right icon_color"
                           uk-icon="icon: user; ratio: 1.5"></a>
                        <span>&nbsp;&nbsp;&nbsp;</span>
                        <a href="<%=request.getContextPath()%>/admin"
                           class="uk-icon-link uk-margin-small-right icon_color"
                           uk-icon="icon: unlock; ratio: 1.5"></a>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="controllers">
            <div id="toggle-animation-multiple" hidden>
                <div class="buySellButtonSession">
                    <span class="headerBuySell" onclick="location.href = '<%=request.getContextPath()%>/myPage/buy';">BUY</span>
                    <span class="headerBuySellDiv">&nbsp;|&nbsp;</span>
                    <span class="headerBuySell" onclick="location.href = '<%=request.getContextPath()%>/myPage/sell';">SELL</span>
                </div>
            </div>
        </div>

    </nav>
    <hr class="top_divider">
</div>
<div class="uk-clearfix bottomHeader">
    <div class="header_width">
        <ul class="uk-float-left top_header_left white">
            <li><a href="<%=request.getContextPath()%>/detailSearch?search=아디다스">ADIDAS</a></li>
            <li><a href="<%=request.getContextPath()%>/detailSearch?search=나이키">NIKE</a></li>
            <li><a href="<%=request.getContextPath()%>/detailSearch?search=조던">JORDAN</a></li>
        </ul>

        <ul id="header_rank" class="uk-float-right ranking white">
            <!-- 실시간 검색어 카드-->
            <div id="rankChart" class="uk-card uk-card-default rankCardContainer uk-hidden">
                <div class="rankHeader">
                    <p>인기 검색어</p>
                    <div class="headerClose" onclick="controlChart(2)"></div>
                </div>
                <div class="chart">

                    <ul>
                        <!-- 실시간 검색어-->
                        <!-- 짧은 검색어는 에러가 나기에 추가적으로 공백을 추가하였습니다 (삭제하지말아주세요!!!) 110px-->
                        <li onclick="linkTo(1)"><span class="rankNum">1</span>&nbsp;&nbsp;<span
                                class="rankhref rankFirst">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <br>

                        <li onclick="linkTo(2)"><span class="rankNum">2</span>&nbsp;&nbsp;<span
                                class="rankhref rankSecond">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <br>
                        <li onclick="linkTo(3)"><span class="rankNum">3</span>&nbsp;&nbsp;<span
                                class="rankhref rankThird">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <br>
                        <li onclick="linkTo(4)"><span class="rankNum">4</span>&nbsp;&nbsp;<span
                                class="rankhref rankFourth">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <br>
                        <li onclick="linkTo(5)"><span class="rankNum">5</span>&nbsp;&nbsp;<span
                                class="rankhref rankFifth">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <br>
                        <li onclick="linkTo(6)"><span class="rankNum">6</span>&nbsp;&nbsp;<span
                                class="rankhref rankSixth">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <br>
                        <li onclick="linkTo(7)"><span class="rankNum">7</span>&nbsp;&nbsp;<span
                                class="rankhref rankSeventh">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                    </ul>
                </div>
            </div>

            <li class="scrolling" onclick="controlChart(1)">
                <div class="headerRank">
                    <ul class="rankDeg">
                        <!-- 실시간 검색어-->
                        <!-- 실시간 검색어-->
                        <li><span class="rankNum">1</span>&nbsp;&nbsp;<span class="rankreg rankFirst">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <li><span class="rankNum">2</span>&nbsp;&nbsp;<span
                                class="rankreg rankSecond">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <li><span class="rankNum">3</span>&nbsp;&nbsp;<span class="rankreg rankThird">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <li><span class="rankNum">4</span>&nbsp;&nbsp;<span
                                class="rankreg rankFourth">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <li><span class="rankNum">5</span>&nbsp;&nbsp;<span class="rankreg rankFifth">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <li><span class="rankNum">6</span>&nbsp;&nbsp;<span class="rankreg rankSixth">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                        <li><span class="rankNum">7</span>&nbsp;&nbsp;<span
                                class="rankreg rankSeventh">Error</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </li>
                    </ul>
                </div>
            </li>
            <span onclick="controlChart(1)" class="downArr" uk-icon="chevron-down"></span>
        </ul>
    </div>
</div>


<!-- Header/Search JS -->
<script src="<%=request.getContextPath()%>/assets/js/header.js"></script>
<!-- navigation bar 끝-->

<!-- ToTop 스크롤 -->
<div id="scroll-top">
    <a href="#top" class="uk-icon-button toTop" uk-icon="icon: chevron-up; ratio: 1.5" uk-scroll></a>
</div>
<script>
    var one;
    var two;
    var three;
    var four;
    var five;
    var six;
    var seven;
    $.post("${pageContext.request.contextPath}/rank.do", function (json) {
        console.log(json);
        one = json.rank.popularFirst;
        two = json.rank.popularSecond;
        three = json.rank.popularThird;
        four = json.rank.popularFourth;
        five = json.rank.popularFifth;
        six = json.rank.popularSixth;
        seven = json.rank.popularSeventh;
        $(".rankFirst").html(one); // 최종 결과물을 #list 요소에 추가한다.
        $(".rankSecond").html(two); // 최종 결과물을 #list 요소에 추가한다.
        $(".rankThird").html(three); // 최종 결과물을 #list 요소에 추가한다.
        $(".rankFourth").html(four); // 최종 결과물을 #list 요소에 추가한다.
        $(".rankFifth").html(five); // 최종 결과물을 #list 요소에 추가한다.
        $(".rankSixth").html(six); // 최종 결과물을 #list 요소에 추가한다.
        $(".rankSeventh").html(seven); // 최종 결과물을 #list 요소에 추가한다.
    })

    function linkTo(num) {
        switch (num) {
            case 1:
                location.href = "<%=request.getContextPath()%>/detailSearch?search=" + one;
                break;

            case 2:
                location.href = "<%=request.getContextPath()%>/detailSearch?search=" + two;
                break;

            case 3:
                location.href = "<%=request.getContextPath()%>/detailSearch?search=" + three;
                break;

            case 4:
                location.href = "<%=request.getContextPath()%>/detailSearch?search=" + four;
                break;

            case 5:
                location.href = "<%=request.getContextPath()%>/detailSearch?search=" + five;
                break;

            case 6:
                location.href = "<%=request.getContextPath()%>/detailSearch?search=" + six;
                break;

            case 7:
                location.href = "<%=request.getContextPath()%>/detailSearch?search=" + seven;
        }
    }

    function controlChart(num) {
        var element = document.getElementById("rankChart");
        if (num == 1) {
            element.classList.remove("uk-hidden");
        } else {
            element.classList.add("uk-hidden");
        }
    }
</script>