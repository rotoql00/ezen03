<%@ page import="java.time.LocalDate" %>
<!--
FileName: index.html
Description: 모든 정보를 가지고 있는 Main 페이지 입니다.
Author: 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>

<c:set var="checkNew" value="<%=LocalDate.now().minusMonths(1)%>"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tie Shoe</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <!-- UIkit CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/index.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/AJAX_helper/ajax_helper.js"></script>
<!-- HEADER -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<!-- CONTAINER -->
<div class="container">
    <!-- autoplay를 true로 4초마다 set을 넘어가게 설정하였습니다.-->
    <div class="index_title">BEST BRAND</div>

    <div uk-slideshow="animation: fade; autoplay: true; autoplay-interval: 3000;">

        <div class="uk-position-relative uk-visible-toggle uk-light banner ul_brand" tabindex="-1">

            <ul class="uk-slideshow-items">
                <li>
                    <img class="banner" src="<%=request.getContextPath()%>/assets/img/banner/adidas_banner.png"
                         alt="아디다스">

                    <!-- HandleBar로 받아옵니다. (아디다스) -->
                    <ul class="uk-slider-items uk-child-width-1-2 uk-child-width-1-3@s uk-child-width-1-4@m "
                        id="adidas">
                    </ul>
                </li>
                <li>
                    <img class="banner" src="<%=request.getContextPath()%>/assets/img/banner/nike_banner.png" alt="나이키">

                    <!-- HandleBar로 받아옵니다. (나이키) -->
                    <ul class="uk-slider-items uk-child-width-1-2 uk-child-width-1-3@s uk-child-width-1-4@m"
                        id="nike">
                    </ul>
                </li>
                <li>
                    <img class="banner" src="<%=request.getContextPath()%>/assets/img/banner/jordan_banner.png"
                         alt="조던">

                    <!-- HandleBar로 받아옵니다. (조던) -->
                    <ul class="uk-slider-items uk-child-width-1-2 uk-child-width-1-3@s uk-child-width-1-4@m"
                        id="jordan">
                    </ul>
                </li>
                <li>
                    <img class="banner" src="<%=request.getContextPath()%>/assets/img/banner/converse_banner.png"
                         alt="컨버스">

                    <!-- HandleBar로 받아옵니다. (컨버스) -->
                    <ul class="uk-slider-items uk-child-width-1-2 uk-child-width-1-3@s uk-child-width-1-4@m"
                        id="converse">
                    </ul>
                </li>
            </ul>

            <a class="uk-position-top-right" href="#"
               uk-slideshow-item="previous"><img class="back" src="<%=request.getContextPath()%>/assets/img/button.jpg"
                                                 alt="뒤로가기"></a>
        </div>

        <ul class="uk-slideshow-nav uk-dotnav uk-flex-center uk-margin"></ul>

    </div>

    <hr class="index_divider">

    <!-- 신상품을 AJAX 해옵니다 -->
    <div class="index_title_pop">NEW ARRIVALS</div>
    <div id="newArrival" class="uk-text-center" uk-grid uk-height-match="target: > div > .uk-card">
    </div>

    <!-- MD's PICK -->
    <hr class="index_divider" id="indexMdPick">
    <div class="index_title_pop">MD's PICK</div>
    <div class="mdContainer">
        <ul uk-tab class="uk-flex-center">
            <li><a href="#">BEST 상품</a></li>
            <li><a href="#" onclick="popular()">인기상품</a></li>
            <c:choose>
                <c:when test="${empty sessionScope.UserId}">
                    <li><a href="#">추천상품</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="#"
                            <c:if test="${sessionScope.UserForm eq 'Y'}">
                                onclick="mdPick()"
                            </c:if>
                    >${sessionScope.UserId}님의 추천상품</a></li>
                </c:otherwise>
            </c:choose>
        </ul>

        <ul class="uk-switcher uk-margin">
            <!-- BEST 상품 -->
            <li>
                <div tabindex="-1" uk-slider="sets: true; autoplay: true; autoplay-interval: 3000">
                    <div class="uk-position-relative">
                        <div class="uk-slider-container uk-light">
                            <ul id="bestProduct"
                                class="uk-slider-items uk-child-width-1-3 uk-child-width-1-4@s uk-child-width-1-5@m resize">
                            </ul>
                        </div>
                        <div class="uk-hidden@s uk-light">
                            <a class="uk-position-center-left uk-position-small" href="#" uk-slidenav-previous
                               uk-slider-item="previous"></a>
                            <a class="uk-position-center-right uk-position-small" href="#" uk-slidenav-next
                               uk-slider-item="next"></a>
                        </div>
                        <div class="uk-visible@s">
                            <a class="uk-position-center-left-out uk-position-small" href="#" uk-slidenav-previous
                               uk-slider-item="previous"></a>
                            <a class="uk-position-center-right-out uk-position-small" href="#" uk-slidenav-next
                               uk-slider-item="next"></a>
                        </div>
                    </div>
                </div>
            </li>
            <!-- 인기 상품 탭 -->
            <li>
                <div tabindex="-1" uk-slider="sets: true; autoplay: true; autoplay-interval: 3000">
                    <div class="uk-position-relative">
                        <div class="uk-slider-container uk-light">
                            <ul id="popular"
                                class="uk-slider-items uk-child-width-1-3 uk-child-width-1-4@s uk-child-width-1-5@m resize">
                            </ul>
                        </div>
                        <div class="uk-hidden@s uk-light">
                            <a class="uk-position-center-left uk-position-small" href="#" uk-slidenav-previous
                               uk-slider-item="previous"></a>
                            <a class="uk-position-center-right uk-position-small" href="#" uk-slidenav-next
                               uk-slider-item="next"></a>
                        </div>
                        <div class="uk-visible@s">
                            <a class="uk-position-center-left-out uk-position-small" href="#" uk-slidenav-previous
                               uk-slider-item="previous"></a>
                            <a class="uk-position-center-right-out uk-position-small" href="#" uk-slidenav-next
                               uk-slider-item="next"></a>
                        </div>
                    </div>
                </div>
            </li>
            <!-- 추천 상품 (사용자 개별)-->
            <li>
                <c:if test="${empty sessionScope.UserId}">
                    <div class="loginReq">
                        <p class="cardTitle">로그인이 필요한 서비스입니다.</p>
                        <button class="uk-button uk-button-secondary uk-button-large uk-width-1-1"
                                onclick="window.location.href = '<%=request.getContextPath()%>/login';"> 여기를 클릭해
                            로그인해주세요.
                        </button>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.UserId}">
                    <c:if test="${sessionScope.UserForm eq 'N'}">
                        <div class="loginReq">
                            <p class="cardTitle">설문이 완료되지 않았습니다.</p>
                            <button class="uk-button uk-button-secondary uk-button-large uk-width-1-1"
                                    onclick="window.location.href = '<%=request.getContextPath()%>/survey';"> 여기를 클릭해
                                설문에 참여해주세요.
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.UserForm eq 'Y'}">
                        <div tabindex="-1" uk-slider="sets: true; autoplay: true; autoplay-interval: 3000">
                            <div class="uk-position-relative">
                                <div class="uk-slider-container uk-light">
                                    <ul id="mdPick"
                                        class="uk-slider-items uk-child-width-1-3 uk-child-width-1-4@s uk-child-width-1-5@m resize">
                                    </ul>
                                </div>
                                <div class="uk-hidden@s uk-light">
                                    <a class="uk-position-center-left uk-position-small" href="#" uk-slidenav-previous
                                       uk-slider-item="previous"></a>
                                    <a class="uk-position-center-right uk-position-small" href="#" uk-slidenav-next
                                       uk-slider-item="next"></a>
                                </div>
                                <div class="uk-visible@s">
                                    <a class="uk-position-center-left-out uk-position-small" href="#"
                                       uk-slidenav-previous
                                       uk-slider-item="previous"></a>
                                    <a class="uk-position-center-right-out uk-position-small" href="#" uk-slidenav-next
                                       uk-slider-item="next"></a>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:if>
            </li>
        </ul>
    </div>
</div>
<!-- 최근 본 상품-->
<div id="recentItem" class="recentCat">
    <div class="uk-card uk-card-hover recentOuterBox">
        <p class="recentTitle">최근본상품</p>
        <hr class="customHR">
        <div id="recentImage"></div>
        <script id="recentImage-tmpl" type="text/x-handlebars-template">
            <a href="<%=request.getContextPath()%>product?productNum={{first.id}}">
                <div id="first">
                    <img src="${context}{{first.product_thumb}}" alt="{{first.productNameEN}}"
                         onmouseover="show(1)" id="firstImage">

                    <div class="recent_container hidden" onmouseleave="hide(1)" id="firstCon">
                        <div class="recentContext">
                            <p class="boldName">{{txtLen first.productNameEN}}</p>
                            <p class="boldName recentContextPosition">최저판매가: {{comma first.productReleasePrice}}</p>
                        </div>
                        <div class="recentContext_img">
                            <img src="${context}{{first.product_thumb}}"
                                 alt="{{first.productNameEN}}">
                        </div>
                    </div>
                </div>
            </a>
            <a href="<%=request.getContextPath()%>product?productNum={{second.id}}">
                <div id="second">
                    <img src="${context}{{second.product_thumb}}" alt="{{second.productNameEN}}"
                         onmouseover="show(2)" id="secondImage">

                    <div class="recent_container hidden" onmouseleave="hide(2)" id="secondCon">
                        <div class="recentContext">
                            <p class="boldName">{{txtLen second.productNameEN}}</p>
                            <p class="boldName recentContextPosition">최저판매가: {{comma second.productReleasePrice}}</p>
                        </div>
                        <div class="recentContext_img">
                            <img src="${context}{{second.product_thumb}}"
                                 alt="{{second.productNameEN}}">
                        </div>
                    </div>

                </div>
            </a>

            <a href="<%=request.getContextPath()%>product?productNum={{third.id}}">
                <div id="third">
                    <img src="${context}{{third.product_thumb}}" alt="{{third.productNameEN}}"
                         onmouseover="show(3)" id="thirdImage">

                    <div class="recent_container hidden" onmouseleave="hide(3)" id="thirdCon">
                        <div class="recentContext">
                            <p class="boldName">{{txtLen third.productNameEN}}</p>
                            <p class="boldName recentContextPosition">최저판매가: {{comma third.productReleasePrice}}</p>
                        </div>
                        <div class="recentContext_img">
                            <img src="${context}{{third.product_thumb}}"
                                 alt="{{third.productNameEN}}">
                        </div>
                    </div>
                </div>
            </a>
            </div>
        </script>
    </div>
</div>
<!-- FOOTER -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<!-- 스크립트 구간-->
<script src="<%=request.getContextPath()%>/assets/js/index.js"></script>

<!-- --------------------------------------HANDLEBAR TEMPLATE ----------------------------------------------- -->

<script id="adidas-tmpl" type="text/x-handlebars-template">
    {{#each adidas}}
    <li class="brandCard">
        <a href="<%=request.getContextPath()%>/product?productNum={{id}}">
            <img src="${context}{{index_thumb}}" alt="{{productNameEN}}">
            <div class="index_product_name">{{productNameEN}}</div>
            <div class="index_lowprice_name">최저판매가</div>
            <div class="index_lowprice-value">₩{{comma productReleasePrice}}</div>
        </a>
    </li>
    {{/each}}
</script>

<script id="nike-tmpl" type="text/x-handlebars-template">
    {{#each nike}}
    <li class="brandCard">
        <a href="<%=request.getContextPath()%>/product?productNum={{id}}">
            <img src="${context}{{index_thumb}}" alt="{{productNameEN}}">
            <div class="index_product_name">{{productNameEN}}</div>
            <div class="index_lowprice_name">최저판매가</div>
            <div class="index_lowprice-value">₩{{comma productReleasePrice}}</div>
        </a>
    </li>
    {{/each}}
</script>

<script id="jordan-tmpl" type="text/x-handlebars-template">
    {{#each jordan}}
    <li class="brandCard">
        <a href="<%=request.getContextPath()%>/product?productNum={{id}}">
            <img src="${context}{{index_thumb}}" alt="{{productNameEN}}">
            <div class="index_product_name">{{productNameEN}}</div>
            <div class="index_lowprice_name">최저판매가</div>
            <div class="index_lowprice-value">₩{{comma productReleasePrice}}</div>
        </a>
    </li>
    {{/each}}
</script>

<script id="converse-tmpl" type="text/x-handlebars-template">
    {{#each converse}}
    <li class="brandCard">
        <a href="<%=request.getContextPath()%>/product?productNum={{id}}">
            <img src="${context}{{index_thumb}}" alt="{{productNameEN}}">
            <div class="index_product_name">{{productNameEN}}</div>
            <div class="index_lowprice_name">최저판매가</div>
            <div class="index_lowprice-value">₩{{comma productReleasePrice}}</div>
        </a>
    </li>
    {{/each}}
</script>

<!-- 신상품 HANDLE BAR-->
<script id="newArrival-tmpl" type="text/x-handlebars-template">
    {{#each newArrival}}
    <div class="uk-width-1-4@m">
        <div class="uk-card uk-card-hover card-body">
            <div>
                <div class="badgeContainer">
                    <span class="uk-badge" {{badge productCode}}>BEST</span>
                    <span class="uk-badge" {{new reg_date}}>NEW</span>
                </div>
                <a href="<%=request.getContextPath()%>/product?productNum={{id}}">
                    <img src="${context}{{index_thumb}}" alt="{{productNameEN}}">
                    <div class="index_product_name">{{productNameEN}}</div>
                    <div class="index_lowprice_name">최저판매가</div>
                    <div class="index_lowprice-value">₩{{comma productReleasePrice}}</div>
                </a>
            </div>
        </div>
    </div>
    {{/each}}
</script>

<script id="bestProduct-tmpl" type="text/x-handlebars-template">
    {{#each bestProduct}}
    <li>
        <div class="uk-card uk-card-hover card-body">
            <div>
                <div class="badgeContainer">
                    <span class="uk-badge" {{badge productCode}}>BEST</span>
                    <span class="uk-badge" {{new reg_date}}>NEW</span>
                </div>
                <a href="<%=request.getContextPath()%>/product?productNum={{id}}">
                    <img src="${context}{{index_thumb}}" alt="{{productNameEN}}">
                    <div class="index_product_name">{{productNameEN}}</div>
                    <div class="index_lowprice_name">최저판매가</div>
                    <div class="index_lowprice-value">₩{{comma productReleasePrice}}</div>
                </a>
            </div>
        </div>
    </li>
    {{/each}}
</script>

<script id="popular-tmpl" type="text/x-handlebars-template">
    {{#each popular}}
    <li>
        <div class="uk-card uk-card-hover card-body">
            <div>
                <div class="badgeContainer">
                    <span class="uk-badge" {{badge productCode}}>BEST</span>
                    <span class="uk-badge" {{new reg_date}}>NEW</span>
                </div>
                <a href="<%=request.getContextPath()%>/product?productNum={{id}}">
                    <img src="${context}{{index_thumb}}" alt="{{productNameEN}}">
                    <div class="index_product_name">{{productNameEN}}</div>
                    <div class="index_lowprice_name">최저판매가</div>
                    <div class="index_lowprice-value">₩{{comma productReleasePrice}}</div>
                </a>
            </div>
        </div>
    </li>
    {{/each}}
</script>

<script id="mdPick-tmpl" type="text/x-handlebars-template">
    {{#each mdPick}}
    <li>
        <div class="uk-card uk-card-hover card-body">
            <div>
                <div class="badgeContainer">
                    <span class="uk-badge" {{badge productCode}}>BEST</span>
                    <span class="uk-badge" {{new reg_date}}>NEW</span>
                </div>
                <a href="<%=request.getContextPath()%>/product?productNum={{ID}}">
                    <img src="${context}{{index_thumb}}" alt="{{productNameEN}}">
                    <div class="index_product_name">{{productNameEN}}</div>
                    <div class="index_lowprice_name">최저판매가</div>
                    <div class="index_lowprice-value">₩{{comma productReleasePrice}}</div>
                </a>
            </div>
        </div>
    </li>
    {{/each}}
</script>
<script>
    $.post("${pageContext.request.contextPath}/recentProduct.do", function (json) {
        var source = $("#recentImage-tmpl").html();   // 템플릿 코드 가져오기
        var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
        var result = template(json); // 템플릿 컴파일 결과물에 json 전달
        $("#recentImage").html(result); // 최종 결과물을 #list 요소에 추가한다.
        // 로그인이 되어 있지 않다면 최근 상품을 숨깁니다
        console.log(json)
        if (json.first == 1) {
            document.getElementById("recentItem").setAttribute("class", "hidden")
        }
    })

    function show(number) {

        console.log(number)
        switch (number) {
            case 1:
                number = "first";
                break;
            case 2:
                number = "second";
                break;
            case 3:
                number = "third";
                break;
        }
        document.getElementById(number + "Con").classList.remove("hidden");
        document.getElementById(number + "Image").classList.add("class", "hidden"); //이미지를 숨김
    }


    function hide(number) {

        switch (number) {
            case 1:
                number = "first";
                break;
            case 2:
                number = "second";
                break;
            case 3:
                number = "third";
                break;
        }
        document.getElementById(number + "Con").classList.add("class", "hidden");
        document.getElementById(number + "Image").classList.remove("hidden")
    }

    //AJAX중복을 막습니다.
    var popularController = 0;
    var mdPickController = 0;

    //JSON
    $.post("${pageContext.request.contextPath}/getBrand.do", function (json) {
        var source1 = $("#adidas-tmpl").html();   // 템플릿 코드 가져오기
        var source2 = $("#nike-tmpl").html();   // 템플릿 코드 가져오기
        var source3 = $("#jordan-tmpl").html();   // 템플릿 코드 가져오기
        var source4 = $("#converse-tmpl").html();   // 템플릿 코드 가져오기
        var source5 = $("#newArrival-tmpl").html();   // 템플릿 코드 가져오기
        var source6 = $("#bestProduct-tmpl").html();   // 템플릿 코드 가져오기
        var template1 = Handlebars.compile(source1);  // 템플릿 코드 컴파일
        var template2 = Handlebars.compile(source2);  // 템플릿 코드 컴파일
        var template3 = Handlebars.compile(source3);  // 템플릿 코드 컴파일
        var template4 = Handlebars.compile(source4);  // 템플릿 코드 컴파일
        var template5 = Handlebars.compile(source5);  // 템플릿 코드 컴파일
        var template6 = Handlebars.compile(source6);  // 템플릿 코드 컴파일
        var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
        var result2 = template2(json); // 템플릿 컴파일 결과물에 json 전달
        var result3 = template3(json); // 템플릿 컴파일 결과물에 json 전달
        var result4 = template4(json); // 템플릿 컴파일 결과물에 json 전달
        var result5 = template5(json); // 템플릿 컴파일 결과물에 json 전달
        var result6 = template6(json); // 템플릿 컴파일 결과물에 json 전달
        $("#adidas").html(result1); // 최종 결과물을 #list 요소에 추가한다.
        $("#converse").html(result4); // 최종 결과물을 #list 요소에 추가한다.
        $("#nike").html(result2); // 최종 결과물을 #list 요소에 추가한다.
        $("#jordan").html(result3); // 최종 결과물을 #list 요소에 추가한다.
        $("#newArrival").html(result5); // 최종 결과물을 #list 요소에 추가한다.
        $("#bestProduct").html(result6); // 최종 결과물을 #list 요소에 추가한다.
    })
    // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
    Handlebars.registerHelper('comma', function (text) {
        str = String(text);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    });

    Handlebars.registerHelper('txtLen', function (text) {
        if (text == null) {
            return null;
        }
        if (text.length < 25) {
            return text;
        }
        str = text.substr(0, 30) + "...";
        return str;
    });

    // BEST(인기상품에 BADGE를 달아줍니다. (Handlebar only)
    Handlebars.registerHelper('badge', function (text) {

        if (text === undefined || text <= 0) {
            //배지 숨기기 (기본값)
            return "hidden";
        }
        return "";
    });

    //신상품일 경우 NEW BADGE를 달아줍니다. (Handlebar only)
    Handlebars.registerHelper('new', function (text) {
        var today = new Date();
        var release = text;
        var releaseArray = release.split('-');
        var releaseDate = new Date(releaseArray[0], releaseArray[1] - 1, releaseArray[2]);
        var byMonth = 24 * 60 * 60 * 1000; // 일단위
        var dif = today - releaseDate;


        //현재 날자로부터 n일 안에 업데이트 했으면 NEW BADEG를 줍니다.
        if (parseInt(dif / byMonth) >= 15) {
            return "hidden";
        }
        return "";
    });


    function popular() {
        //한 페이지에서 AJAX여러번 하는것을 막습니다.
        if (popularController === 1) {
            return;
        }

        //페이지 컨트롤러에 1을 주어 다음번 AJAX를 막습니다.
        popularController = 1;
        $.post("${pageContext.request.contextPath}/getPopular.do", function (json) {
            var source1 = $("#popular-tmpl").html();   // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1);  // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#popular").html(result1); // 최종 결과물을 #list 요소에 추가한다.
        })

        // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        });

        // BEST(인기상품에 BADGE를 달아줍니다. (Handlebar only)
        Handlebars.registerHelper('badge', function (text) {
            if (text === undefined || text <= 0) {
                //배지 숨기기 (기본값)
                return "hidden";
            }
            return "";
        });

        //신상품일 경우 NEW BADGE를 달아줍니다. (Handlebar only)
        Handlebars.registerHelper('new', function (text) {
            var today = new Date();
            var release = text;
            var releaseArray = release.split('-');
            var releaseDate = new Date(releaseArray[0], releaseArray[1] - 1, releaseArray[2]);
            var byMonth = 24 * 60 * 60 * 1000; // 일단위
            var dif = today - releaseDate;

            //현재 날자로부터 n일 안에 업데이트 했으면 NEW BADEG를 줍니다.
            if (parseInt(dif / byMonth) >= 15) {
                return "hidden";
            }
            return "";
        })
    }

    ///사용자 설문을 통한 mdPick
    function mdPick() {
        //한 페이지에서 AJAX여러번 하는것을 막습니다.
        if (mdPickController === 1) {
            return;
        }

        //페이지 컨트롤러에 1을 주어 다음번 AJAX를 막습니다.
        mdPickController = 1;
        $.post("${pageContext.request.contextPath}/getMdPick.do", {
            ID: "${sessionScope.loginID}"
        }, function (json) {
            var source1 = $("#mdPick-tmpl").html();   // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1);  // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#mdPick").html(result1); // 최종 결과물을 #list 요소에 추가한다.
        })

        // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        });

        // BEST(인기상품에 BADGE를 달아줍니다. (Handlebar only)
        Handlebars.registerHelper('badge', function (text) {
            if (text === undefined || text <= 0) {
                //배지 숨기기 (기본값)
                return "hidden";
            }
            return "";
        });

        //신상품일 경우 NEW BADGE를 달아줍니다. (Handlebar only)
        Handlebars.registerHelper('new', function (text) {
            var today = new Date();
            var release = text;
            var releaseArray = release.split('-');
            var releaseDate = new Date(releaseArray[0], releaseArray[1] - 1, releaseArray[2]);
            var byMonth = 24 * 60 * 60 * 1000; // 일단위
            var dif = today - releaseDate;

            //현재 날자로부터 n일 안에 업데이트 했으면 NEW BADEG를 줍니다.
            if (parseInt(dif / byMonth) >= 15) {
                return "hidden";
            }
            return "";
        });
    }
</script>
</body>
</html>