<!--
FileName: detailSearch.jsp
Description: 상품 상세검색 사이드바를 포함하고 있는 임시 페이지입니다. index가 임시로 포함되어 있습니다. 전체 상품 디스플레이 페이지와 병합 예정
Author: 임채린, 임석현(수정)
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
    <title>Tie Shoe</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/index.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/detailSearch.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/moreBtn.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
          crossorigin="anonymous">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
</head>

<body onload="search()">
<!-- HEADER -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<!-- CONTAINER -->
<div class="container">

    <!-- 검색확인-->
    <div class="searchResultContainer">

        <div id="showResult" class="searchResult">상품정보를 가져오는중...</div>
        <div class="searchResultSub">원하는 상품을 찾기 어렵다면 '스마트 서치'를 이용해보세요</div>
    </div>

    <!-- 스마트 서치 -->
    <form id="searchForm" class="option_container" method="get" action="<%=request.getContextPath()%>/detailSearch_ok">
        <div class="eachOptionBrand">
            <p class="optionTitle">BRAND</p>
            <ul class="uk-nav-sub ks-cboxtags optionBrand">
                <li><input class="search" type="checkbox" id="nike" name="brand" value="nike">
                    <label for="nike">Nike</label><br></li>
                <li><input class="search" type="checkbox" id="adidas" name="brand" value="adidas">
                    <label for="adidas">ADIDAS</label><br></li>
                <li><input class="search" type="checkbox" id="jordan" name="brand" value="jordan">
                    <label for="jordan">JORDAN</label><br></li>
                <li><input class="search" type="checkbox" id="vans" name="brand" value="vans">
                    <label for="vans">VANS</label><br></li>
                <li><input class="search" type="checkbox" id="converse" name="brand" value="converse">
                    <label for="converse">CONVERSE</label><br></li>
            </ul>
        </div>
        <div class="eachOption">
            <p class="optionTitle">SIZE</p>
            <ul class="uk-nav-sub ks-cboxtags optionAll">
                <%
                    for (int i = 220; i <= 300; i += 5) {
                        out.write("<li><input class=\"search\"type=\"checkbox\" id=\"" + i + "\"value=\"" + i + "\"name=\"size\"><label for=\"" + i + "\">" + i + "</label> </li>");
                    }
                %>
            </ul>
        </div>
        <div class="eachOption">
            <p class="optionTitle">가격</p>
            <ul class="uk-nav-sub ks-cboxtags optionPrice">
                <li><input class="search" type="radio" id="under100k" value=100000
                           name="price"><label for="under100k"> ~ 100,000</label>
                </li>
                <li><input class="search" type="radio" id="100kto200k" value=200000
                           name="price"><label for="100kto200k"> 100,000 ~ 200,000</label>
                </li>
                <li><input class="search" type="radio" id="200kto300k" value=300000
                           name="price"><label for="200kto300k"> 200,000 ~ 300,000</label>
                </li>
                <li><input class="search" type="radio" id="300kto400k" value=400000
                           name="price"><label for="300kto400k"> 300,000 ~ 400,000</label>
                </li>
                <li><input class="search" type="radio" id="400kto500k" value=500000
                           name="price"><label for="400kto500k"> 400,000 ~ 500,000</label>
                </li>
                <li><input class="search" type="radio" id="over500k" value=999999
                           name="price"><label for="over500k"> 500,000 ~ </label>
                </li>
                <div class="inputTitle">직접입력
                    <li><input class="search uk-search-input input_box uk-text-right" type="search" id="minPrice"
                               name="minPrice">원 ~<input class="search uk-search-input input_box uk-text-right"
                                                         type="search" id="maxPrice" name="maxPrice">원
                    </li>
                </div>
            </ul>

            <div class="pushSelect"></div>
            <p class="optionTitle">발매년도</p>
            <ul class="uk-nav-sub ks-cboxtags optionAll">
                <%
                    for (int j = 2015; j <= 2020; j++) {
                        out.write("<li><input class=\"search\"type=\"checkbox\" id=\"" + j + "\" value=\"" + j + "\"name=\"year\"><label for=\"" + j + "\">" + j + "</label> </li>");
                    }
                %>
            </ul>
        </div>
        <div>&nbsp;</div>
        <div>&nbsp;</div>
        <div class="uk-position-relative uk-position-bottom-center">
            <button class="uk-button uk-button-default uk-width-small uk-width-auto" id="reset_button" type="reset"
                    onclick="window.location.reload()">초기화
            </button>
            <button class="uk-button uk-button-secondary uk-width-small uk-width-auto" id="detailSearch_button"
                    type="submit" onclick="replace(this);">검색
            </button>
        </div>
    </form>

    <!-- 상품을 AJAX 해옵니다 -->
    <div class="searchProductResult">
        <p id="howMany"></p>
        <hr class="solidProduct">
        <div id="searchAll" class="uk-text-center" uk-grid uk-height-match="target: > div > .uk-card">
        </div>
        <ul class="uk-pagination uk-flex-center push" uk-margin id="pagination">
        </ul>


    </div>

</div>

<!-- FOOTER -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<!-- 스크립트 구간-->
<script src="<%=request.getContextPath()%>/assets/js/index.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/detailSearch.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/moreBtn.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.form.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- 체크박스버튼 스크립트 -->
<script src="https://cdn.rawgit.com/InventPartners/Checkbox2Button/master/js/checkbox2button.min.js"></script>


<!--  SEARCH HANDLE BAR-->
<script id="search-tmpl" type="text/x-handlebars-template">

    {{#each product}}
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

<!--  SEARCH HANDLE BAR-->
<script id="searchNone-tmpl" type="text/x-handlebars-template">
    <div>
        <div class="uk-text-center" uk-grid uk-height-match="target: > div > .uk-card">
            {{#each popular}}
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
        </div>
    </div>
</script>

<script>

    function search() {
        var page = "${param.page}";
        var search = "${param.search}";

        //페이지가 없을 경우 페이지를 1로 세팅합니다. (즉, 아무 파라미터가 없을 경우)
        if (page == "") {
            page = 1;
        }

        $.post("${pageContext.request.contextPath}/search.do", {
            search: "${param.search}",
            page: page
        }, function (json) {

            // 검색결과가 없다면 인기상품을 보여줍니다.
            if (json.count === 0 || json.count === null) {
                console.log("asd");
                $.post("${pageContext.request.contextPath}/getPopular.do", function (json) {
                    $("#showResult").html("<span class=\"getSearch\">${param.search}</span>에 대한 검색결과가 없습니다."); // 최종 결과물을 #list 요소에 추가한다.
                    var source1 = $("#searchNone-tmpl").html(); // 템플릿 코드 가져오기
                    var template1 = Handlebars.compile(source1); // 템플릿 코드 컴파일
                    var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
                    $("#searchAll").html(result1); // 최종 결과물을 #list 요소에 추가한다.
                    $("#howMany").html("TIE SHOE의 인기상품"); // 최종 결과물을 #list 요소에 추가한다.
                });
            } else {
                $("#showResult").html("<span class=\"getSearch\">${param.search}</span>에 대한 " + json.count + "건의 검색결과가 있습니다."); // 최종 결과물을 #list 요소에 추가한다.
                $("#howMany").html("총 <span class=\"countSearch\">" + json.count + "</span>개의 상품이 있습니다."); // 최종 결과물을 #list 요소에 추가한다.
            }

            var source1 = $("#search-tmpl").html(); // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1); // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#searchAll").html(result1); // 최종 결과물을 #list 요소에 추가한다.


            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/detailSearch?search=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/detailSearch?search=" + search + "&page=" + i + "\">" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/detailSearch?search=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
            }

        });
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

    $(function () {
        $("#searchForm").ajaxForm(function (json) {
            var source1 = $("#search-tmpl").html(); // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1); // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#searchAll").html(result1); // 최종 결과물을 #list 요소에 추가한다.

            // 검색결과가 없다면 인기상품을 보여줍니다.
            if (json.count === 0 || json.count === null) {
                $.post("${pageContext.request.contextPath}/getPopular.do", function (json) {
                    $("#showResult").html("검색결과가 없습니다."); // 최종 결과물을 #list 요소에 추가한다.
                    var source1 = $("#searchNone-tmpl").html(); // 템플릿 코드 가져오기
                    var template1 = Handlebars.compile(source1); // 템플릿 코드 컴파일
                    var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
                    $("#searchAll").html(result1); // 최종 결과물을 #list 요소에 추가한다.
                    $("#howMany").html("TIE SHOE의 인기상품"); // 최종 결과물을 #list 요소에 추가한다.
                });
            } else {
                $("#showResult").html("<span class=\"getSearch\">" + json.count + "</span>건의 검색결과가 있습니다."); // 최종 결과물을 #list 요소에 추가한다.
                $("#howMany").html("총 <span class=\"countSearch\">" + json.count + "</span>개의 상품이 있습니다."); // 최종 결과물을 #list 요소에 추가한다.
            }
        });
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

    });

</script>
</body>
</html>