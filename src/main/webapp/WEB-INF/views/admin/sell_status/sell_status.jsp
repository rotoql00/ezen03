<!--
FileName: buy_status.jsp
Description: 상품입찰현황을 모니터링을 하기 위한 관리자페이지 입니다.
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

<body onload="getsell()">
<%@ include file="../inc/admin_header.jsp" %>
<div class="content-padder content-background">
    <div class="uk-section-xsmall uk-section-default header">
        <div class="uk-container uk-container-large">
            <h1 class="bold"><span class="ion-speedometer"></span>상품판매현황</h1>
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
                                <div class="uk-card-header bold">판매정보 <br><br>
                                    <span class="small">* 상품 상세정보를 보시려면 클릭해주세요 <br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요<br>* 검수확인과 송금확인은 현재페이지에서 업데이트 할 수 없습니다.
                                        <br>* 정상적으로 거래가 끝났다면 거래완료를 시켜주세요.<br>* <mark>상품대기중</mark> -> <mark>상품인수</mark> -> 검수중 -> <mark>검수완료</mark> -> 송금대기중 -> <mark>송금완료</mark> -> 거래완료
                                        <br>* 위 <mark>하이라이트</mark>된 항목만 제어 가능합니다.</span>
                                </div>
                                <div class="uk-card-body">
                                    <table class="uk-table uk-table-striped uk-table-responsive uk-table-hover">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th>이름 / 아이디</th>
                                            <th>상품코드 / 사이즈</th>
                                            <th>판매 시작일 / 종료일</th>
                                            <th>판매마감일 / 판매가격</th>
                                            <th>상태</th>
                                            <th>상태 업데이트</th>
                                        </tr>
                                        </thead>
                                        <tbody id="sellList">
                                        </tbody>
                                    </table>
                                    <!-- 페이징-->
                                    <ul class="uk-pagination uk-flex-center push" uk-margin id="pagination">
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- HandleBar -->
<script type="text/x-handlebars-template" id="sellList_tmpl">
    {{#each info}}
    <tr>
        <td><a href="<%=request.getContextPath()%>/admin/productList?category=1&keyword={{productCode}}"><img
                class="shoeImg" src="${context}{{product_thumb}}" alt="이미지"> </a>
        </td>
        <td><a href="<%=request.getContextPath()%>/admin/manageUsers?category=1&keyword={{rangeEnd}}">
            {{rangeStart}}
            <hr class="table_hr">
            {{rangeEnd}}
        </a>
        </td>
        <td>{{productCode}}
            <hr class="table_hr">
            {{sellSize}}
        </td>
        <td>{{check edit_date}}
            <hr class="table_hr">
            {{reg_date}}
        </td>
        <td>₩{{comma sellPrice}}
            <hr class="table_hr">
            {{sellEnd}}일
        </td>
        <td>{{sellStatusToText orderStatus}}</td>
        <td>
            <button class="{{checkStatusButton orderStatus}}"
                    onclick="updateToDone({{orderNum}}, {{orderStatus}}, {{sellLowest270}})" {{hidden
                    orderStatus}}>
                {{rename orderStatus}}
            </button>
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
<!-- sweet alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- Plain Script-->
<script src="<%=request.getContextPath()%>/assets/js/jquery.selectric.min.js"></script>
<script src="<%=request.getContextPath()%><%=request.getContextPath()%>/admin/sell_status/sell_status.js"></script>
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>

<script>
    function getsell() {
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
        $.post("${pageContext.request.contextPath}/admin/sellList.do", {
            key: search,
            option: options,
            nowPage: page
        }, function (json) {
            console.log(json);
            var source1 = $("#sellList_tmpl").html();   // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1);  // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#sellList").html(result1); // 최종 결과물을 #list 요소에 추가한다.

            //페이징을 위한 구문입니다.
            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/sellStatus?category=" + options + "&keyword=" + search + "&page=" + json.page.prevPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/sellStatus?category=" + options + "&keyword=" + search + "&page=" + i + "\">" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/sellStatus?category=" + options + "&keyword=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
            }
        });
    }

    // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
    Handlebars.registerHelper('comma', function (text) {
        str = String(text);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    });

    // 상태에 대한 document 처리입니다.(Handlebar only)
    Handlebars.registerHelper('check', function (text) {
        if (text == "9999-12-31") {
            return "거래중";
        }
    });

    Handlebars.registerHelper('checkStatusButton', function (text) {
        if (text == 7) {
            return "done";
        } else if (text == 3 || text == null || text == 5 || text == 2) {
            return "uncheckButton"
        } else {
            return "checkButton";
        }
    });

    Handlebars.registerHelper('hidden', function (text) {

        // 범위안지 않으면 버튼을 disable 시킵니다.
        if (text == 7 || text == 3 || text == null || text == 5 || text == 2) {
            return "disabled";
        }
    });

    // 상태에 대한 document 처리입니다.(Handlebar only)
    Handlebars.registerHelper('sellStatusToText', function (text) {

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

            case 99:
                return "거래파기"

            default:
                return "판매중";
        }
    });

    Handlebars.registerHelper('rename', function (text) {
        if (text == 7) {
            return "거래완료";
        } else if (text == 3 || text == null || text == 6) {
            return "대기중"
        } else if (text == 2) {
            return "입찰 대기중"
        } else {
            return "다음단계";
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

    function updateToDone(ID, orderStatus, buy) {
        if (buy == 2) {
            Swal.fire({
                title: '상품 확인',
                text: "입찰자의 입금이 끝났으므로, 검수중으로 업데이트 됩니다.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '확인',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.value) {
                    $.post("${pageContext.request.contextPath}/admin/orderUpdateBoth.do", {
                        ID: ID
                    }, function (success) {
                        if (success == 1) {
                            Swal.fire(
                                '완료',
                                '입찰자와 판매자의 상태를 업데이트 하였습니다.',
                                'success'
                            )
                            getsell();
                        }
                    });
                }
            })
        } else {
            //다음으로 넘기기위해 1을 더합니다.
            var next = orderStatus + 1;

            //AJAX는 POST 방식으로 합니다. (리스트를 가져옵니다.
            $.post("${pageContext.request.contextPath}/admin/updateOrderStatusSell.do", {
                ID: ID,
                status: next
            }, function (success) {
                if (success == 1) {
                    var Toast = Swal.mixin({
                        toast: true,
                        position: 'bottom-end',
                        showConfirmButton: false,
                        timer: 3000,
                        timerProgressBar: true,
                        onOpen: (toast) => {
                            toast.addEventListener('mouseenter', Swal.stopTimer)
                            toast.addEventListener('mouseleave', Swal.resumeTimer)
                        }
                    })
                    Toast.fire({
                        icon: 'success',
                        title: '상품을 확인하였습니다.'
                    })
                    getsell();
                }
            });
        }
    }
</script>
</body>

</html>