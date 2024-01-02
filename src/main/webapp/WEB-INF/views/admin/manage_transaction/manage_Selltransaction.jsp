<!--
FileName: manage_Buytransaction.jsp
Description: 개인결제관리을 관리하기 위한 페이지 입니다.
Author: 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>

<head>
    <meta charset="UTF-8"/>
    <title>TIESHOE ADMIN</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/selectric.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/admin/admin.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/admin.png">
</head>

<body onload="sellPayOrder()">
<%@ include file="../inc/admin_header.jsp" %>
<div class="content-padder content-background">
    <div class="uk-section-xsmall uk-section-default header">
        <div class="uk-container uk-container-large">
            <h1 class="bold"><span class="ion-speedometer"></span>판매자 송금관리</h1>
        </div>
    </div>
    <div class="uk-section-small">
        <!-- 내용 -->
        <div class="uk-container uk-container-large">
            <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-1@l">
                <div>
                    <div class="uk-card uk-card-small uk-card-default">
                        <div class="uk-navbar-left">
                            <div class="uk-navbar-item">
                                <form id="search" class="searchTab" method="get">
                                    <!-- 설렉트 바 -->
                                    <select name="category" class="optionTag" id="user_category">
                                        <option selected value="0">전체</option>
                                        <option value="1">아이디</option>
                                        <option value="2">이름</option>
                                    </select>

                                    <!-- 서치 -->
                                    <div class="inline uk-search uk-search-default input_margin">
                                        <input id="searchKey" name="keyword" class="uk-search-input input_box"
                                               type="search" onkeydown="submitSearch(event)" placeholder="Search..."
                                               value="${param.keyword}">
                                        <a class="uk-form-icon uk-form-icon-flip icon_color" uk-icon="icon: search"
                                           onclick="submitSearchBt()"></a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-1@l">
                        <div>
                            <div class="uk-card uk-card-default">
                                <div class="uk-card-header bold">
                                    송금 정보 <br><br>
                                    <span class="small">* 송금 확인후 해당 탭을 클릭해 송금완료로 수정해주세요. <br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요</span>
                                </div>
                                <div class="uk-card-body">
                                    <table class="uk-table uk-table-striped  uk-table-responsive uk-table-hover">
                                        <thead>
                                        <tr>
                                            <th>주문번호</th>
                                            <th>거래확정일시</th>
                                            <th>이름 / 아이디</th>
                                            <th>가격 / 수수료</th>
                                            <th>송금은행 / 계좌번호</th>
                                            <th>송금여부</th>
                                        </tr>
                                        </thead>
                                        <tbody id="sellList">
                                        </tbody>
                                    </table>
                                    <!-- 페이징-->
                                    <ul class="uk-pagination uk-flex-center push" uk-margin id="pagination"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/x-handlebars-template" id="sellList_tmpl">
    {{#each info}}
    <tr class="thumbTable">
        <td>{{orderNumber}}</td>
        <td>{{reg_date}}</td>
        <td><a href="<%=request.getContextPath()%>/admin/manageUsers?category=1&keyword={{seller}}">
            {{buyer}}
            <hr class="table_hr">
            {{seller}}
        </a>
        </td>
        <td>₩{{comma orderPrice}}
            <hr class="table_hr">
            <mark>₩{{comma orderSize}}</mark>
        </td>
        <td>{{checkFraud1 productImg}}
            <hr class="table_hr">
            {{checkFraud2 productName}}
        </td>
        <td>
            <button class="checkButton" onclick="updateToDone({{id}})">송금확인</button>
        </td>
    </tr>
    {{/each}}
</script>

<!-- Jquery 3.4.1 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.transit/0.9.12/jquery.transit.min.js"></script>
<!-- UI-kit Script-->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<!-- Handlebar Script-->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.selectric.min.js"></script>
<!-- sweet alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- Plain Script-->
<script src="<%=request.getContextPath()%>/admin/manage_transaction/manage_transaction.js"></script>
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>

<script>
    function sellPayOrder() {
        // 파라미터 값을 가져옵니다.
        var options = "${param.category}";
        var search = "${param.keyword}";
        var page = "${param.page}"

        $("#pagination").empty();

        //만약에 옵션이 없다면 (즉, 아무 파라미터가 없을 경우에) 전체 검색을 실행합니다.
        if (options == "") {
            options = 0;
        }
        //페이지가 없을 경우 페이지를 1로 세팅합니다. (즉, 아무 파라미터가 없을 경우)
        if (page == "") {
            page = 1;
        }

        $('#user_category').prop('selectedIndex', options).selectric('refresh');

        //AJAX는 POST 방식으로 합니다. (리스트를 가져옵니다.)
        $.post("${pageContext.request.contextPath}/admin/SellPayOrder.do", {
            key: search,
            option: options,
            nowPage: page
        }, function (json) {
            var source1 = $("#sellList_tmpl").html();   // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1);  // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#sellList").html(result1); // 최종 결과물을 #list 요소에 추가한다.

            //페이징을 위한 구문입니다.

            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/manageSellTransaction?category=" + options + "&keyword=" + search + "&page=" + json.page.prevPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/manageSellTransaction?category=" + options + "&keyword=" + search + "&page=" + i + "\">" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/manageSellTransaction?category=" + options + "&keyword=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
            }
        });
    }

    // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
    Handlebars.registerHelper('comma', function (text) {
        str = String(text);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    });


    // 은행정보가 없을떄 에러를 내보냅니다.
    Handlebars.registerHelper('checkFraud1', function (text) {
        if (text == "null") {
            return "Error";
        }
    });

    // 은행정보가 없을떄 에러를 내보냅니다.
    Handlebars.registerHelper('checkFraud2', function (text) {
        if (text == "null") {
            return "확인 요망";
        }
    });

    function submitSearch(e) {
        if (e.keyCode === 13) {
            e.preventDefault();
            document.getElementById('search').submit();
        }
    }

    function submitSearchBt() {
        document.getElementById('search').submit();
    }

    function updateToDone(ID) {
        // 만약 판매 상품을 받았다면 바로 3으로 업데이트합니다.
        Swal.fire({
            title: '송금확인',
            text: "송금을 확실히 보내셨습니까?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.value) {
                $.post("${pageContext.request.contextPath}/admin/orderSellUpdate.do", {
                    ID: ID
                }, function (success) {
                    if (success == 1) {
                        Swal.fire(
                            '완료',
                            '송금을 완료했습니다.',
                            'success'
                        )
                        sellPayOrder();
                    }
                });
            }
        })
    }
</script>
</body>

</html>