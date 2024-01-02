<!--
FileName: myPage_sell.html
Description: 개인 판매정보 관리.
Author: 임석현
-->
<%@ page import="java.time.LocalDate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>

<c:set var="three" value="<%=LocalDate.now().minusMonths(3)%>"/>
<c:set var="six" value="<%=LocalDate.now().minusMonths(6)%>"/>
<c:set var="nine" value="<%=LocalDate.now().minusMonths(9)%>"/>
<c:set var="year" value="<%=LocalDate.now().minusMonths(12)%>"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/myPage-sell.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/AJAX_helper/ajax_helper.css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assets/plugin/air-datepicker/datepicker.min.css">
</head>

<body onload="getList()">
<!-- Header -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<!-- Container -->
<div class="container">
    <div class="myPage_title">MY</div>

    <div class="myPage_subnav">
        <ul class="uk-subnav uk-subnav-divider mypage_center">
            <li><a href="<%=request.getContextPath()%>/myPage/buy">구매/입찰</a></li>
            <li class="uk-active"><a href="#">판매</a></li>
            <li><a href="<%=request.getContextPath()%>/myPage">개인정보관리</a></li>
        </ul>
    </div>

    <div class="card_container uk-card uk-card-default uk-card-hover">
        <div class="myPage_username_background">
            <div class="myPage_username_text">
                <span class="icon_nut" uk-icon="nut"></span>&nbsp<span id="userName">${sessionScope.UserName}</span>
                &nbsp님
            </div>
        </div>
        <div class="card_context_container">
            <div class="myPage_card_title">MY<br><span class="myPage_card_title_sell">판매</span></div>

            <div class="card_middle">
                <div class="user_title">
                    <div class="user_title_info">판매자 등급</div>
                    <div>판매자 수수료<br>(스니커즈)</div>
                </div>
                <div class="user_title_result">
                    <div class="user_level_info" id="user_level">0</div>
                    <div class="user_fee_info"><span id="user_fee">0</span>%</div>
                </div>
                <div class="user_button">
                    <button type="button" class="myPage_level_button" href="#myPage_sell_level" uk-toggle>판매자 등급 안내
                    </button>
                    <button type="button" class="myPage_pen_button" href="#myPage_sell_penalty" uk-toggle>판매자 패널티 안내
                    </button>
                </div>
            </div>
            <div class="myPage_level">
                <div class="level_circle_container">
                    <div class="level_circle" id="level_1">1</div>
                    <div class="level_circle" id="level_2">2</div>
                    <div class="level_circle" id="level_3">3</div>
                    <div class="level_circle" id="level_4">4</div>
                    <div class="level_circle" id="level_5">5</div>
                </div>
                <div class="myPage_level_desc">
                    <ul>
                        <li><span uk-icon="chevron-right"></span>판매자 등급은 거래횟수에 따라 산정됩니다.</li>
                        <li><span uk-icon="chevron-right"></span>판매자 수수료는 판매자 등급별로 적용됩니다.</li>
                        <li><span uk-icon="chevron-right"></span>판매자 수수료는 카테고리별로 적용기준이 다릅니다.</li>
                        <li><span uk-icon="chevron-right"></span>거래성사 후 발송지연, 미발송 및 불량상품, 가품 등을 <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp발송하는
                            경우 패널티가 적용됩니다.
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div id="myPage_sell_penalty" uk-modal>
        <div class="uk-modal-dialog">

            <button class="uk-modal-close-default" type="button" uk-close></button>

            <div class="uk-modal-header">
                <h2 class="uk-modal-title">판매자 패널티 안내</h2>
            </div>

            <div class="uk-modal-body uk-modal-adjust" uk-overflow-auto>

                <p class="myPage_penalty_info">TieShoe의 안전한 거래를 위해 아래 규정을 위반할 경우 패널티가 부과됩니다.</p>

                <p class="myPage_penalty_bold"><span class="popup_icon" uk-icon="nut"></span>배송관련 패널티 사유</p>

                <p class="myPage_penalty_title">- 미배송</p>
                <p class="myPage_penalty_explain">거래 성사후 정해진 기간내에 배송하지 않는 경우</p>
                <p class="myPage_penalty_title">- 지연배송</p>
                <p class="myPage_penalty_explain">거래 성사 후 정해진 기간내에 송장번호를 입력하지 않는 경우</p>

                <p class="myPage_penalty_bold"><span class="popup_icon" uk-icon="nut"></span>상품관련 패널티 사유</p>

                <p class="myPage_penalty_title">- 상품 상태 상이</p>
                <p class="myPage_penalty_explain">거래 성사후 발송 한 상품의 상태가 상이한 경우</p>
                <p class="myPage_penalty_explain">예) 박스불량, 구성품 부족, 본상품 상태 불량 등</p>

                <p class="myPage_penalty_title">- 가짜 상품 거래</p>
                <p class="myPage_penalty_explain">거래 성사 후 가품을 발송한 경우</p>

                <p class="myPage_penalty_bold"><span class="popup_icon" uk-icon="nut"></span>패널티 처리</p>

                <p class="myPage_penalty_title">- 배송 및 상품관련 사유로 패널티처리 되는 경우 회원정지 처리되어 활동이 제약될 수 있습니다.</p>
                <p class="myPage_penalty_title">- 배송 및 상품관련 사유로 패널티 처리되는 경우 패널티 비용이 부과될 수 있습니다.</p>
            </div>

            <div class="uk-modal-footer uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">확인</button>
            </div>

        </div>
    </div>

    <!-- 판매자 등급 안내 모달-->
    <div id="myPage_sell_level" uk-modal>
        <div class="uk-modal-dialog">

            <button class="uk-modal-close-default" type="button" uk-close></button>

            <div class="uk-modal-header">
                <h2 class="uk-modal-title">판매자 등급 안내</h2>
            </div>

            <div class="uk-modal-body uk-modal-adjust" uk-overflow-auto>

                <p class="myPage_penalty_info">TIESHOE의 편리한 거래를 위해 등급별로 혜택을 드립니다.</p>

                <p class="myPage_penalty_bold"><span class="popup_icon" uk-icon="nut"></span>판매자 등급 안내</p>

                <p class="myPage_penalty_title">- 판매자 등급은 1~5등급까지 있습니다.</p>
                <p class="myPage_penalty_title">- 판매자 등급 설정 시 TIESHOE 내 모든 판매건수를 합산하여 설정합니다.</p>

                <p class="myPage_penalty_bold"><span class="popup_icon" uk-icon="nut"></span>판매자 등급 기준</p>

                <p class="myPage_penalty_title">- 1등급 : 2등급에서 50선 판매한 회원</p>
                <p class="myPage_penalty_title">- 2등급 : 3등급에서 40건 판매한 회원</p>
                <p class="myPage_penalty_title">- 3등급 : 4등급에서 7건 판매한 회원</p>
                <p class="myPage_penalty_title">- 4등급 : 5등급에서 3건 판매한 회원</p>
                <p class="myPage_penalty_title">- 5등급 : 회원 가입 후 시작하는 회원</p>

                <p class="myPage_penalty_bold"><span class="popup_icon" uk-icon="nut"></span>판매자 등급별 수수료</p>

                <p class="myPage_penalty_title"> 판매자 등급별 수수료는 카테고리별로 각각 적용됩니다.</p>
                <p class="myPage_penalty_title">- 1등급 : 9%</p>
                <p class="myPage_penalty_title">- 2등급 : 10%</p>
                <p class="myPage_penalty_title">- 3등급 : 11%</p>
                <p class="myPage_penalty_title">- 4등급 : 12%</p>
                <p class="myPage_penalty_title">- 5등급 : 13%</p>

            </div>

            <div class="uk-modal-footer uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">확인</button>
            </div>

        </div>
    </div>

    <div class="push">
        <h3 class="uk-card-title">판매 내역</h3>
        <div class="uk-panel search">
            <div class=" uk-align-left@xl">
                <button class="uk-button uk-button-default uk-button-secondary" type="button" onclick="getList()">판매내역
                </button>
                <button class="uk-button uk-button-default uk-button-secondary" type="button"
                        onclick="getProcessList()">거래내역
                </button>
            </div>
            <!-- 조회년도-->
            <span class="uk-align-right@xl">
            <button class="uk-button uk-button-default uk-button-secondary" type="button"
                    onclick="month('<c:out value="${three}"/>')">3개월
            </button>
            <button class="uk-button uk-button-default uk-button-secondary" type="button"
                    onclick="month('<c:out value="${six}"/>')">6개월
            </button>
            <button class="uk-button uk-button-default uk-button-secondary" type="button"
                    onclick="month('<c:out value="${nine}"/>')">9개월
            </button>
            <button class="uk-button uk-button-default uk-button-secondary" type="button"
                    onclick="month('<c:out value="${year}"/>')">1년
            </button>
            <span class="yearInput">
                <input id="start" name="start" class="uk-input uk-form-width-medium datepicker-here"
                       type="text" placeholder="시작">
                <span> ~ </span>
                <input id="end" name="end" value="<c:out value="${today}"/>"
                       class="uk-input uk-form-width-medium datepicker-here" type="text" placeholder="끝">
            <button class="uk-button uk-button-default uk-button-secondary" type="button" onclick="search()">
                조회
            </button>
            </span>
            </span>
        </div>
    </div>
    <table id="result" class="uk-table uk-table-middle uk-table-divider uk-table uk-table-justify">
    </table>
    <ul class="uk-pagination uk-flex-center push" uk-margin id="pagination">
    </ul>
</div>
<!-- End of Container -->

<div id="edit" class="uk-modal-container" uk-modal>
    <div class="uk-modal-dialog uk-modal-body">
        <button class="uk-modal-close-default" type="button" uk-close></button>
        <h2 class="uk-modal-title">판매가격 수정</h2>
    </div>
</div>


<script id="result-tmpl" type="text/x-handlebars-template">
    <thead>
    <tr>
        <th class="uk-width-small">종목명</th>
        <th class="uk-width-medium"></th>
        <th class="uk-width-small">스타일 코드</th>
        <th class="uk-width-small">판매일</th>
        <th class="uk-width-small">판매가</th>
        <th class="uk-width-small">판매 마감일</th>
        <th class="uk-width-small">판매현황</th>
        <th class="uk-width-small">기능</th>
    </tr>
    </thead>
    <tbody>
    {{#each sellList}}
    <tr>
        <td id="img{{ID}}"><a href="<%=request.getContextPath()%>/product?productNum={{productNum}}"><img
                class="shoeImg"
                src="${context}{{product_thumb}}"
                alt="{{productNameEN}}"></a>
        </td>
        <td id="productName{{ID}}">{{productNameEN}} <br><br>옵션: {{sellSize}}</td>
        <td id="productCode{{ID}}">{{productCode}}</td>
        <td id="reg_date{{ID}}">{{reg_date}}</td>
        <td id="buyPrice{{ID}}">₩{{comma sellPrice}}</td>
        <td>{{addDate reg_date sellEnd}}</td>
        <td>판매중</td>
        <td>
            <button class="uk-button uk-button-secondary" type="button"
                    onclick="push({{sellEnd}}, {{productNum}}, {{sellSize}}, {{id}}, {{sellPrice}}, {{sellFee}})">수정
            </button>
            <div class="gap"></div>
            <button class="uk-button uk-button-danger" type="button" onclick="deleteItem({{id}})">삭제</button>
        </td>
    </tr>
    {{/each}}
    </tbody>
</script>

<!--거래내역의 Handlebars-->
<script id="resultProcess-tmpl" type="text/x-handlebars-template">
    <thead>
    <tr>
        <th class="uk-width-small">종목명</th>
        <th class="uk-width-medium"></th>
        <th class="uk-width-small">스타일 코드</th>
        <th class="uk-width-small">거래시작일</th>
        <th class="uk-width-small">거래종료일</th>
        <th class="uk-width-small">판매가</th>
        <th class="uk-width-small">거래현황</th>
        <th class="uk-width-small">철회</th>
    </tr>
    </thead>
    <tbody>
    {{#each sellList}}
    <tr>
        <td id="img"><a href="<%=request.getContextPath()%>/product?productNum={{productNum}}"><img
                class="shoeImg"
                src="${context}{{product_thumb}}"
                alt="{{productNameEN}}"></a>
        </td>
        <td>{{productNameEN}} <br><br>옵션: {{sellSize}}</td>
        <td>{{productCode}}</td>
        <td>{{reg_date}}</td>
        <td>{{editDate edit_date}}</td>
        <td>₩{{comma sellPrice}}</td>
        <td><span uk-tooltip="title: {{toolTip orderStatus}}; pos: bottom">{{statusToText orderStatus}}</span></td>
        <td>
            <button class="uk-button uk-button-danger" type="button"
                    onclick="orderDelete({{orderNum}})" {{disable orderStatus}}>철회
            </button>
        </td>
    </tr>
    {{/each}}
    </tbody>
</script>
<!-- Footer -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>

<!-- 넘기기위한 FORM-->
<form id="push" action="<%=request.getContextPath()%>/sell" method="post">
    <div class="hidden">
        <input id="bidDate" type="text" name="bidDate">
        <input id="productNumPush" type="text" name="productNum">
        <input id="size" type="text" name="size">
        <input id="sellId" type="text" name="sellEdit_ID">
        <input id="price" type="text" name="price">
        <input id="fee" type="text" name="fee">
    </div>
</form>


<!-- 스크립트 구간-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- UIkit -->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="${context}/assets/AJAX_helper/ajax_helper.js"></script>
<script src="${context}/assets/plugin/air-datepicker/datepicker.min.js"></script>
<script src="${context}/assets/js/myPage_sell.js"></script>


<script>

    //3달전
    var today = new Date();
    today.setMonth(today.getMonth() - 3);

    $('#start').datepicker().data('datepicker').selectDate(today);
    $('#end').datepicker().data('datepicker').selectDate(new Date());

    //기본값
    var startCheck = document.getElementById("start").value;
    var endCheck = document.getElementById("end").value;
    var page = 1;
    var once = 1;

    //동일한 날짜 검색에 대한 페이지 설정
    function search() {
        page = 1;
        getList();
    }

    function getList() {
        var page = "${param.page}"
        var category = "${param.category}"
        if (category == 2) {
            page = 1
            category = 1
        }

        $("#pagination").empty();

        //페이지가 없을 경우 페이지를 1로 세팅합니다. (즉, 아무 파라미터가 없을 경우)
        if (page == "") {
            page = 1;
        }

        if (startCheck != document.getElementById("start").value || endCheck != document.getElementById("end").value) {
            page = page;
            startCheck = document.getElementById("start").value;
            endCheck = document.getElementById("end").value;
        }

        // Restful API에 Post 방식 요청
        $.post("${pageContext.request.contextPath}/getSellInfo.do", {
            ID: "${sessionScope.loginID}",
            Page: page,
            start: document.getElementById("start").value,
            end: document.getElementById("end").value
        }, function (json) {
            var source = $("#result-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            $("#result").html(result); // 최종 결과물을 #list 요소에 추가한다.

            //한번 이상 append 못하게 금지
            if (once === 1) {
                var shipping = json.userInfo;
                //사용자의 레벨
                $("#level").append(shipping);
                // 한번 이상 못돌게 막는 변수
                once++;
            }

            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/sell?category=" + category + "&page=" + json.page.prevPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/sell?category=" + category + "&page=" + i + "\">" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/sell?category=" + category + "&keyword=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
            }


            //total Page를 가져옵니다.
            // 동그라미 색칠
            let user_level = parseInt(json.userInfo.userLevel); //회원 등급을 받아오는 변수
            let level_con = "level_" + user_level;

            $("#user_level").html(user_level);
            $("#" + level_con).removeClass("level_circle");
            $("#" + level_con).addClass("level_circle_active");

            switch (json.userInfo.userLevel) {
                case 5:
                    $("#user_fee").html(13);
                    break;

                case 4:
                    $("#user_fee").html(12);
                    break;

                case 3:
                    $("user_fee").html(11);
                    break;

                case 2:
                    $("user_fee").html(10);
                    break;

                case 1:
                    $("user_fee").html(9);
                    break;
            }

        });

        // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        });

        // 날자를 더해줍니다 (Handlebar only)
        Handlebars.registerHelper('addDate', function (text, add) {
            var split = text.split('/');
            var date = new Date(split[0], split[1] - 1, split[2]);
            date.setDate(date.getDate() + parseInt(add));
            return date.toLocaleDateString('zh-Hans-CN');
        });

        // 상태에 대한 document 처리입니다.(Handlebar only)
        Handlebars.registerHelper('statusToText', function (text) {

            var cases = parseInt(text);

            switch (cases) {
                case 1:
                    return "상품배송 대기중";

                case 2:
                    return "상품인수";

                case 3:
                    return "검수중";

                case 4:
                    return "검수완료";

                case 5:
                    return "송금 대기중";

                case 6:
                    return "송금완료";

                case 7:
                    return "거래완료";

                case 8:
                    return "거래파기"

                default:
                    return "판매중";
            }
        });

        Handlebars.registerHelper('toolTip', function (text) {
            var cases = parseInt(text);

            switch (cases) {
                case 1:
                    return "TIESHOE에서 <br>판매자님의 상품을 기다리고 있습니다.";

                case 2:
                    return "TIESHOE에서 <br>판매자님의 상품을 받았습니다.";

                case 3:
                    return "TIESHOE에서 <br>상품을 검수중입니다.";

                case 4:
                    return "TIESHOE에서 <br>상품검수를 완료했습니다.";

                case 5:
                    return "송금을 위해 <br>준비중입니다.";

                case 6:
                    return "송금이 완료되었습니다.";

                case 7:
                    return "모든 거래가 완료 되었습니다";

                default:
                    return "현재가격의 입찰자가 없습니다.";
            }

        });

        // 날자를 더해줍니다 (Handlebar only)
        Handlebars.registerHelper('buttonControl', function (text) {
            if (text == undefined) {
                return "수정";
            } else if (parseInt(text) === 7) {
                return "완료";
            } else {
                return "진행중";
            }
        });

        Handlebars.registerHelper('disable', function (text) {
            if (text == 8) {
                return "disabled";
            }
        });

        Handlebars.registerHelper('editDate', function (text) {
            if (text == "9999-12-31") {
                return "거래중"
            }
        });

        function edit(sellNum) {
            UIkit.modal(edit).show();
        }
    }

    function getProcessList() {
        var page = "${param.page}"
        var category = "${param.category}"
        if (category == 1) {
            page = 1
            category = 2
        }

        $("#pagination").empty();

        //페이지가 없을 경우 페이지를 1로 세팅합니다. (즉, 아무 파라미터가 없을 경우)
        if (page == "") {
            page = 1;
        }

        if (startCheck != document.getElementById("start").value || endCheck != document.getElementById("end").value) {
            page = 1;
            startCheck = document.getElementById("start").value;
            endCheck = document.getElementById("end").value;
        }

        // Restful API에 Post 방식 요청
        $.post("${pageContext.request.contextPath}/getSellProcessInfo.do", {
            ID: "${sessionScope.loginID}",
            Page: page,
            start: document.getElementById("start").value,
            end: document.getElementById("end").value
        }, function (json) {
            var source = $("#resultProcess-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            $("#result").html(result); // 최종 결과물을 #list 요소에 추가한다.

            console.log(json)
            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/sell?category=" + category + "&page=" + json.page.prevPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/sell?category=" + category + "&page=" + i + "\">" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/sell?category=" + category + "&keyword=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
            }


            Handlebars.registerHelper('editDate', function (text) {
                if (text == "9999-12-31") {
                    return "거래중"
                }
            });


            // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
            Handlebars.registerHelper('comma', function (text) {
                str = String(text);
                return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
            });

            // 상태에 대한 document 처리입니다.(Handlebar only)
            Handlebars.registerHelper('statusToText', function (text) {

                var cases = parseInt(text);

                switch (cases) {
                    case 1:
                        return "상품배송 대기중";

                    case 2:
                        return "상품인수";

                    case 3:
                        return "검수중";

                    case 4:
                        return "검수완료";

                    case 5:
                        return "송금 대기중";

                    case 6:
                        return "송금완료";

                    case 7:
                        return "거래완료";

                    default:
                        return "판매중";
                }
            });

            Handlebars.registerHelper('toolTip', function (text) {
                var cases = parseInt(text);

                switch (cases) {
                    case 1:
                        return "TIESHOE에서 <br>판매자님의 상품을 기다리고 있습니다.";

                    case 2:
                        return "TIESHOE에서 <br>판매자님의 상품을 받았습니다.";

                    case 3:
                        return "TIESHOE에서 <br>상품을 검수중입니다.";

                    case 4:
                        return "TIESHOE에서 <br>상품검수를 완료했습니다.";

                    case 5:
                        return "송금을 위해 <br>준비중입니다.";

                    case 6:
                        return "송금이 완료되었습니다.";

                    case 7:
                        return "모든 거래가 완료 되었습니다";

                    default:
                        return "현재가격의 입찰자가 없습니다.";
                }

            })
        });
    }

    function push(bidDate, productNum, size, id, price, fee) {
        document.getElementById("bidDate").setAttribute("value", bidDate);
        document.getElementById("size").setAttribute("value", size);
        document.getElementById("productNumPush").setAttribute("value", productNum);
        document.getElementById("sellId").setAttribute("value", id);
        document.getElementById("price").setAttribute("value", price);
        document.getElementById("fee").setAttribute("value", fee);
        document.getElementById("push").submit();
    }

    function deleteItem(id) {
        Swal.fire({
            title: '판매 삭제',
            text: "판매 내역을 삭제합니다",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.value) {
                $.post("${pageContext.request.contextPath}/deleteSell.do", {
                    ID: id
                }, function (success) {
                    if (success == 1) {
                        Swal.fire(
                            '삭제완료',
                            '해당 판매건이 삭제되었습니다.',
                            'success'
                        )
                        getList();
                    }
                });
            }
        })
    }

    function orderDelete(orderNum) {
        Swal.fire({
            title: '철회',
            text: "배송비용이 부과되고 15일 패널티가 부여되며 \n 차후에 불이익이 있을수 있습니다. \n패널티기간에는 구매 판매가 제한됩니다. \n 확인하시고 철회 하시기 바랍니다",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.value) {
                $.post("${pageContext.request.contextPath}/deleteSellerOrder.do", {
                    orderNum: orderNum
                }, function (success) {
                    if (success == 1) {
                        Swal.fire(
                            '철회완료',
                            '15일 패널티가 부여되었으며 배송비용이 부과될 예정입니다.',
                            'success'
                        )
                        getProcessList()
                    }
                });
            }
        })
    }

</script>
</body>
</html>