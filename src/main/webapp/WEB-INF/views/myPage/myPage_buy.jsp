<%@ page import="java.time.LocalDate" %>
<!--
FileName: myPage_buy.html
Description: 개인 입찰정보 관리.
Author: 임석현
-->

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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/myPage_buy.css">
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
            <li class="uk-active"><a href="#">구매/입찰</a></li>
            <li><a href="<%=request.getContextPath()%>/myPage/sell">판매</a></li>
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
            <div class="myPage_card_title">MY<br><span class="myPage_card_title_sell">구매/입찰</span></div>
            <div class="card_middle">
                <div class="user_title">
                    <div class="user_title_info">배송완료</div>
                    <div>사용가능<br>배송쿠폰</div>
                </div>
                <div class="user_title_result">
                    <div class="user_level_info" id="shipping_success"></div>
                    <div class="user_fee_info" id="coupon"></div>
                </div>
            </div>
            <div class="myPage_level">
                <div class="level_circle_container">
                    <div class="level_circle" id="level_1">R</div>
                    <div class="level_circle" id="level_2">R</div>
                    <div class="level_circle" id="level_3">R</div>
                    <div class="level_circle" id="level_4">R</div>
                    <div class="level_circle" id="level_5">R</div>
                </div>
                <div class="myPage_level_desc">
                    <ul>
                        <li><span uk-icon="chevron-right"></span>구매실적 5회 달성 시 무료배송 혜택이 제공됩니다.</li>
                        <li><span uk-icon="chevron-right"></span>구매실적 5회가 달성되면 1회부터 다시 계산됩니다.</li>
                        <li><span uk-icon="chevron-right"></span>사용하신 배송쿠폰은 사용가능 배송쿠폰 수량에<br>
                            &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp포함되지 않습니다.
                        </li>
                        <li>
                            <button type="button" class="myPage_pen_button" href="#myPage_sell_penalty" uk-toggle>
                                구매 혜택안내
                            </button>
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
                <h2 class="uk-modal-title">구매 혜택 안내</h2>
            </div>

            <div class="uk-modal-body uk-modal-adjust" uk-overflow-auto>

                <p class="myPage_penalty_info">TieShoe 편리한 거래를 위해 무료배송 쿠폰을 제공해 드립니다.</p>

                <p class="myPage_penalty_bold"><span class="popup_icon" uk-icon="nut"></span>쿠폰 안내</p>

                <p class="myPage_penalty_title">- 구매실적이 5회 달성 시 무료배송 쿠폰이 제공됩니다.</p>
                <p class="myPage_penalty_title">- 쿠폰은 자동으로 발급됩니다.</p>
                <p class="myPage_penalty_title">- 구매실적이 5회가 달성되면 1회부터 다시 계산됩니다</p>
                <p class="myPage_penalty_explain">예) 5회, 10회, 15회 등으로 발급</p>
                <p class="myPage_penalty_title">- 사용하신 배송쿠폰은 사용가능 배송쿠폰 수량에 포함되지 않습니다</p>
                <p class="myPage_penalty_title">- 입찰가 등록 시 배송쿠폰을 선택하여 사용할 수 있습니다.</p>
                <p class="myPage_penalty_title">- 입찰가 등록 시 배송쿠폰을 적용한 경우, 거래가 성사되지 않으면 쿠폰은 사용되지 않습니다.</p>
                <p class="myPage_penalty_title">- 단, 거래성사 된 후 환불하는 경우 쿠폰은 재발급 되지 않습니다.</p>
            </div>

            <div class="uk-modal-footer uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">확인</button>
            </div>
        </div>
    </div>

    <div class="push">
        <h3 class="uk-card-title">구매/입찰 내역</h3>
        <div class="uk-panel search">
            <div class=" uk-align-left@xl">
                <button class="uk-button uk-button-default uk-button-secondary" type="button" onclick="getList()">입찰내역
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
    <table id="result" class="uk-table uk-table-middle uk-table-divider">
    </table>
    <ul class="uk-pagination uk-flex-center push" uk-margin id="pagination">
    </ul>
</div>
<!-- End of Container -->

<!--입찰 내역의 Handlebars-->
<script id="result-tmpl" type="text/x-handlebars-template">
    <thead>
    <tr>
        <th class="uk-width-small">종목명</th>
        <th class="uk-width-medium"></th>
        <th class="uk-width-small">스타일 코드</th>
        <th class="uk-width-small">입찰일</th>
        <th class="uk-width-small">입찰가</th>
        <th class="uk-width-small">입찰 마감일</th>
        <th class="uk-width-small">입찰현황</th>
        <th class="uk-width-small">기능</th>
    </tr>
    </thead>
    <tbody>
    {{#each buyList}}
    <tr>
        <td id="image{{ID}}"><a href="<%=request.getContextPath()%>/product?productNum={{productNum}}"><img
                class="shoeImg"
                src="${context}{{product_thumb}}"
                alt="{{productNameEN}}"></a>
        </td>
        <td id="productName{{ID}}">{{productNameEN}} <br><br>옵션: {{buySize}}</td>
        <td id="productCode{{ID}}">{{productCode}}</td>
        <td id="reg_date{{ID}}">{{reg_date}}</td>
        <td id="buyPrice{{ID}}">₩{{comma buyPrice}}</td>
        <td>{{addDate reg_date buyEnd}}</td>
        <td>입찰중</td>
        <td>
            <button class="uk-button uk-button-secondary" type="button"
                    onclick=" push({{buyEnd}}, {{productNum}}, {{buySize}}, {{id}}, {{buyPrice}}, {{buyFee}})">수정
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
        <th class="uk-width-small">입찰가</th>
        <th class="uk-width-small">거래현황</th>
        <th class="uk-width-small">철회</th>

    </tr>
    </thead>
    <tbody>
    {{#each buyList}}
    <tr>
        <td><a href="<%=request.getContextPath()%>/product?productNum={{productNum}}"><img class="shoeImg"
                                                                                           src="${context}{{product_thumb}}"
                                                                                           alt="{{productNameEN}}"></a>
        </td>
        <td>{{productNameEN}} <br><br>옵션: {{buySize}}</td>
        <td>{{productCode}}</td>
        <td>{{orderReg_date}}</td>
        <td>{{editDate edit_date}}</td>
        <td>₩{{comma buyPrice}}</td>
        <td><span uk-tooltip="title: {{toolTip orderStatus}}; pos: bottom">{{statusToText orderStatus}}</span></td>
        <td>
			<button class="uk-button uk-button-secondary {{paycomplete orderStatus}}" type="button"
                    onclick="Payment('{{productNameEN}}')">입금
            </button>
			<div class="gap"></div>
            <button class="uk-button uk-button-danger" type="button"
                    onclick="orderDelete({{orderNum}})" {{disable orderStatus}}>철회
            </button>
        </td>

    </tr>
    {{/each}}
    </tbody>
</script>

<!-- 넘기기위한 FORM-->
<form id="push" action="<%=request.getContextPath()%>/buy" method="post">
    <div class="hidden">
        <input id="bidDate" type="text" name="bidDate">
        <input id="productNumPush" type="text" name="productNum">
        <input id="size" type="text" name="size">
        <input id="buyId" type="text" name="buyEdit_ID">
        <input id="price" type="text" name="price">
        <input id="fee" type="text" name="fee">
    </div>
</form>


<!-- Footer -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>

<!-- 스크립트 구간-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- UIkit -->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="<%=request.getContextPath()%>/assets/AJAX_helper/ajax_helper.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugin/air-datepicker/datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/myPage_buy.js"></script>
<!-- 아임포트 결제  -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script>
//결제 시스템 설정


function Payment(productNameEN) {
	var IMP = window.IMP; 
	IMP.init('imp68852797'); 
	
	console.log(productNameEN)
	
	IMP.request_pay({
	    pg : 'inicis', // version 1.1.0부터 지원.
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : productNameEN,
	    amount : 100,
	    buyer_email : '${sessionScope.UserEmail}',
	    buyer_name : '${sessionScope.UserName}',
	    buyer_tel : '010-1234-5678',
	    buyer_addr : '서울특별시 강남구 삼성동',
	    buyer_postcode : '123-456',
	    m_redirect_url : 'https://www.yourdomain.com/payments/complete'
	}, function(rsp) {
	    if ( rsp.success ) {
	    	/* 
	        var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num; 
	        */
	    	var Toast = Swal.fire({
                icon: 'success',
                title: '결제 완료',
                text: '입찰 하신 상품의 결제가 완료 되었습니다.',
            })
            location.reload();
	    } else {
	        /* var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg; */
	        var Toast = Swal.fire({
                icon: 'error',
                title: '결제 실패',
                text: '에러내용 : ' + + rsp.error_msg,
            })
	    }
	    alert(msg);
	});
}

    //3달전
    var today = new Date();
    today.setMonth(today.getMonth() - 3);
    var on_off = 0;

    $('#start').datepicker().data('datepicker').selectDate(today);
    $('#end').datepicker().data('datepicker').selectDate(new Date());

    //기본값
    var startCheck = document.getElementById("start").value;
    var endCheck = document.getElementById("end").value;
    var page = 1;
    var once = 1;

    //동일한 날짜 검색에 대한 페이지 설정
    // 환경 조정
    function search() {
        page = 1;
        if (on_off === 1) {
            getList();
        } else {
            getProcessList();
        }
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
        on_off = 1;

        if (startCheck != document.getElementById("start").value || endCheck != document.getElementById("end").value) {
            page = 1;
            console.log("reset");
            startCheck = document.getElementById("start").value;
            endCheck = document.getElementById("end").value;
        }
        // Restful API에 GET 방식 요청
        $.post("${pageContext.request.contextPath}/getBuyInfo.do", {
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
                var shipping = json.userInfo.countShipping;
                var coupon = json.userInfo.countCoupon;
                // 배송완료 수
                $("#shipping_success").append(shipping);
                //사용가능한 쿠폰
                $("#coupon").append(coupon);
                // 한번 이상 못돌게 막는 변수
                once++;
            }
            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/buy?category=" + category + "&page=" + json.page.prevPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/buy?category=" + category + "&page=" + i + "\">" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/sell?category=" + category + "&keyword=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
            }

            //total Page를 가져옵니다.
            // 동그라미 색칠
            for (let level_color = 1; level_color <= parseInt(shipping); level_color++) {
                $("#level_" + level_color).removeClass("level_circle");
                $("#level_" + level_color).addClass("level_circle_active_Buy");
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
            console.log(add);
            var date = new Date(split[0], split[1] - 1, split[2]);
            date.setDate(date.getDate() + parseInt(add));
            return date.toLocaleDateString('zh-Hans-CN');
        });

        Handlebars.registerHelper('setUndifined', function (text) {
            if (text == undefined) {
                return 0;
            }
        });
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

        on_off = 0;

        if (startCheck != document.getElementById("start").value || endCheck != document.getElementById("end").value) {
            page = 1;
            startCheck = document.getElementById("start").value;
            endCheck = document.getElementById("end").value;
        }
        // Restful API에 GET 방식 요청
        $.post("${pageContext.request.contextPath}/getBuyProcessInfo.do", {
            ID: "${sessionScope.loginID}",
            Page: page,
            start: document.getElementById("start").value,
            end: document.getElementById("end").value
        }, function (json) {
            var source = $("#resultProcess-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            $("#result").html(result); // 최종 결과물을 #list 요소에 추가한다.

            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/buy?category=" + category + "&page=" + json.page.prevPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/buy?category=" + category + "&page=" + i + "\">" + i + "</a></li>")
                }
            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/myPage/sell?category=" + category + "&keyword=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
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
                    return "입금대기중";

                case 2:
                    return "입금완료";

                case 3:
                    return "검수중";

                case 4:
                    return "검수완료";

                case 5:
                    return "배송중";

                case 6:
                    return "배송완료";

                case 7:
                    return "거래완료";

                case 8:
                    return "거래파기";

                default:
                    return "입찰중";
            }
        });

        Handlebars.registerHelper('toolTip', function (text) {
            var cases = parseInt(text);

            switch (cases) {
                case 1:
                    return "TIESHOE에서 <br>입금을 확인하지 않았습니다<br> 3일 이내로 입금해주세요.";

                case 2:
                    return "TIESHOE에서 <br>입금을 확인했습니다.";

                case 3:
                    return "TIESHOE에서 상품을 검수중입니다.";

                case 4:
                    return "TIESHOE에서 상품검수를 완료했습니다.";

                case 5:
                    return "고객님께 배송중입니다.";

                case 6:
                    return "배송이 완료되었습니다";

                case 7:
                    return "모든 거래가 완료 되었습니다";

                case 8:
                    return "거래가 중간에 파기되었습니다.";
            }

        });

        Handlebars.registerHelper('editDate', function (text) {
            if (text == "9999-12-31") {
                return "거래중"
            }
        });

        Handlebars.registerHelper('disable', function (text) {
            if (text == 8) {
                return "disabled";
            }
        });
        
        Handlebars.registerHelper('paycomplete', function (text) {
            if (text != 1) {
                return "none";
            }
        });
    }

    function push(bidDate, productNum, size, id, price, fee) {
        document.getElementById("bidDate").setAttribute("value", bidDate);
        document.getElementById("size").setAttribute("value", size);
        document.getElementById("productNumPush").setAttribute("value", productNum);
        document.getElementById("buyId").setAttribute("value", id);
        document.getElementById("price").setAttribute("value", price);
        document.getElementById("fee").setAttribute("value", fee);
        document.getElementById("push").submit();
    }

    function deleteItem(id) {
        Swal.fire({
            title: '입찰 삭제',
            text: "사용된 쿠폰이 있다면 쿠폰을 재사용 할 수 없습니다",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.value) {
                $.post("${pageContext.request.contextPath}/deleteBuy.do", {
                    ID: id
                }, function (success) {
                    if (success == 1) {
                        Swal.fire(
                            '삭제완료',
                            '해당 입찰건이 삭제되었습니다.',
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
            text: "판매자 배송비용이 부과되고 15일 패널티가 부여되며 \n 차후에 불이익이 있을수 있습니다. \n패널티기간에는 구매 판매가 제한됩니다. \n 확인하시고 철회 하시기 바랍니다",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.value) {
                $.post("${pageContext.request.contextPath}/deleteBuyerOrder.do", {
                    orderNum: orderNum
                }, function (success) {
                    if (success == 1) {
                        Swal.fire(
                            '철회완료',
                            '15일 패널티가 부여되었으며 판매자 배송비용이 부과될 예정입니다.',
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

