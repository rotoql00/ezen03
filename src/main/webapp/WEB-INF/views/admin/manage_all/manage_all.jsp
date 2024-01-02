<!--
FileName: manage_all.jsp
Description: 모든 주문내역을 통제
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

<body onload="getOrder()">
<%@ include file="../inc/admin_header.jsp" %>
<div class="content-padder content-background">
    <div class="uk-section-xsmall uk-section-default header">
        <div class="uk-container uk-container-large">
            <h1 class="bold"><span class="ion-speedometer"></span>거래진행(최고 권한)</h1>
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
                                        <option value="1">주문번호</option>
                                        <option value="2">판매자</option>
                                        <option value="3">구매자</option>
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
                                    주문정보 <br><br>
                                    <span class="small">* 상세정보를 보시려면 클릭해주세요 <br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요 <br>* 검수확인이 필요한 주문건이 가장 상위로 올라옵니다.<br>* 최고 관리자에게만 권한이 주어집니다.<br>* 수정 또는 변경할 때 <span
                                            class="red">주의</span>바랍니다.<br>* 철회는 시간이 10초이상 소요될 수 있습니다</span>
                                </div>
                                <div class="uk-card-body">
                                    <table class="uk-table uk-table-striped  uk-table-responsive uk-table-hover">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th>주문상품</th>
                                            <th>주문번호</th>
                                            <th>구매자 (현황)</th>
                                            <th>판매자 (현황)</th>
                                            <th>판매가격</th>
                                            <th>거래일시</th>
                                        </tr>
                                        </thead>
                                        <tbody id="userList">
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

<script type="text/x-handlebars-template" id="userList_tmpl">
    {{#each info}}
    <tr class="thumbTable">
        <td><a href="${pageContext.request.contextPath}/admin/productList?category=1&keyword={{productName}}"><img
                class="thumbImg" src="${context}{{productImg}}" alt="{{productName}}"></a></td>
        <td><a href="${pageContext.request.contextPath}/admin/productList?category=1&keyword={{productName}}">{{productName}}<br><span
                class="orderSize">사이즈: {{orderSize}}</span></a></td>
        <td>{{orderNumber}}</td>
        <td>
            <a href="${pageContext.request.contextPath}/admin/manageUsers?category=1&keyword={{buyer}}">{{buyer}}<br><span
                    class="orderSize">{{buyStatusToText buyOrderStatus}}</span></a></td>
        <td>
            <a href="${pageContext.request.contextPath}/admin/manageUsers?category=1&keyword={{seller}}">{{seller}}<br><span
                    class="orderSize">{{sellStatusToText sellOrderStatus}}</span></a></td>
        <td>₩{{comma orderPrice}}</td>
        <td>{{reg_date}}</td>

    </tr>
    <tr>
        <td colspan="7">
            <button class="uk-button uk-button-secondary uk-button-small"
                    onclick="updateBuy({{id}}, {{buyOrderStatus}}, 0)"><span uk-icon="chevron-up">Buy</span>
            </button>
            <button class="uk-button uk-button-secondary uk-button-small"
                    onclick="updateBuy({{id}}, {{buyOrderStatus}}, 1)"><span uk-icon="chevron-down">Buy</span></button>

            <button class="uk-button uk-button-secondary uk-button-small"
                    onclick="updateSell({{id}}, {{sellOrderStatus}}, 0)"><span uk-icon="chevron-up">Sell</span></button>
            <button class="uk-button uk-button-secondary uk-button-small"
                    onclick="updateSell({{id}}, {{sellOrderStatus}}, 1)"><span uk-icon="chevron-down">Sell</span>
            </button>
            <button class="uk-button uk-button-secondary uk-button-small"
                    onclick="complete({{id}}, {{buyOrderStatus}}, {{sellOrderStatus}})">완료
            </button>
            <button class="uk-button uk-button-danger uk-button-small"
                    onclick="deleteAll({{id}}, {{buyOrderStatus}}, {{sellOrderStatus}})">강제철회
            </button>
        </td>
    </tr>
    {{/each}}
</script>
s

<!-- Jquery 3.4.1 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.transit/0.9.12/jquery.transit.min.js"></script>
<!-- UI-kit Script-->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<!-- Handlebar Script-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!-- Plain Script-->
<script src="<%=request.getContextPath()%>/assets/js/jquery.selectric.min.js"></script>
<script src="<%=request.getContextPath()%>/admin/manage_order/manage_order.js"></script>
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>

<script>
    function getOrder() {
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
        $.post("${pageContext.request.contextPath}/admin/order.do", {
            key: search,
            option: options,
            nowPage: page
        }, function (json) {
            var source1 = $("#userList_tmpl").html();   // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1);  // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#userList").html(result1); // 최종 결과물을 #list 요소에 추가한다.

            console.log(json)
            //페이징을 위한 구문입니다.

            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/manageOrder?category=" + options + "&keyword=" + search + "&page=" + json.page.prevPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/manageOrder?category=" + options + "&keyword=" + search + "&page=" + i + "\">" + i + "</a></li>")
                }
            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/manageOrder?category=" + options + "&keyword=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
            }
        });
    }

    // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
    Handlebars.registerHelper('comma', function (text) {
        str = String(text);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    });

    // 상태에 대한 document 처리입니다.(Handlebar only)
    Handlebars.registerHelper('buyStatusToText', function (text) {

        var cases = parseInt(text);

        switch (cases) {
            case 1:
                return "입금대기";

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
                return "철회";

            case 99:
                return "거래파기"
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

            case 8:
                return "철회";

            case 99:
                return "거래파기"

            default:
                return "판매중";
        }
    });

    // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
    Handlebars.registerHelper('comma', function (text) {
        str = String(text);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    });

    Handlebars.registerHelper('checkDisable', function (sell, buy) {

        if (!(sell == 3 && buy == 3)) {
            return "disabled"
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
        //AJAX는 POST 방식으로 합니다. (리스트를 가져옵니다.
        $.post("${pageContext.request.contextPath}/admin/orderUpdate.do", {
            ID: ID
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
                    title: '검수를 확인했습니다..'
                })
                getOrder();
            }
        });
    }

    function updateSell(id, status, np) {
        // 앞으로 업데이트인지 뒤로 업데이트인지 확인합니다
        if (np == 0) {
            status++;
        } else if (np == 1) {
            status--;
        }

        // 범위 밖으로 업데이트를 시도하려 한다면 에러메세지 출력
        if (status > 7 || 1 > status) {
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
                icon: 'error',
                title: '판매자 현황을 확인해주세요 (OUT OF RANGE)'
            })
            return false;
        }

        //업데이트 시작
        //AJAX는 POST 방식으로 합니다. (리스트를 가져옵니다.
        $.post("${pageContext.request.contextPath}/admin/updateOrderStatusSell.do", {
            ID: id,
            status: status
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
                    title: '판매자 현황을 업데이트를 완료하였습니다.'
                })
                getOrder();
            }
        });
    }

    function updateBuy(id, status, np) {
        // 앞으로 업데이트인지 뒤로 업데이트인지 확인합니다
        if (np == 0) {
            status++;
        } else if (np == 1) {
            status--;
        }

        // 범위 밖으로 업데이트를 시도하려 한다면 에러메세지 출력
        if (status > 7 || 1 > status) {
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
                icon: 'error',
                title: '구매자 현황을 확인해주세요 (OUT OF RANGE)'
            })
            return false;
        }

        //업데이트 시작
        //AJAX는 POST 방식으로 합니다. (리스트를 가져옵니다.
        $.post("${pageContext.request.contextPath}/admin/updateOrderStatus.do", {
            ID: id,
            status: status
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
                    title: '구매자 현황을 업데이트를 완료하였습니다.'
                })
                getOrder();
            }
        });

    }

    function updateSellBuy() {
    }

    function complete(id, sstatus, bstatus) {
        // 범위 밖으로 업데이트를 시도하려 한다면 에러메세지 출력
        if (sstatus == 7 && bstatus == 7) {
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
                icon: 'error',
                title: '이미 주문완료 상태입니다.'
            })
            return false;
        }

        //업데이트 시작
        //AJAX는 POST 방식으로 합니다. (리스트를 가져옵니다.
        $.post("${pageContext.request.contextPath}/admin/completeOrderStatus.do", {
            ID: id,
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
                    title: '주문완료 상태로 전환되었습니다'
                })
                getOrder();
            }
        });

    }

    function complete(id, sstatus, bstatus) {
        // 범위 밖으로 업데이트를 시도하려 한다면 에러메세지 출력
        if (sstatus == 7 && bstatus == 7) {
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
                icon: 'error',
                title: '철회되었거나 주문완료 상태입니다.'
            })
            return false;
        }

        //업데이트 시작
        //AJAX는 POST 방식으로 합니다. (리스트를 가져옵니다.
        $.post("${pageContext.request.contextPath}/admin/completeOrderStatus.do", {
            ID: id,
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
                    title: '주문완료 상태로 전환되었습니다'
                })
                getOrder();
            }
        });

    }

    function deleteAll(id, sstatus, bstatus) {
        // 범위 밖으로 업데이트를 시도하려 한다면 에러메세지 출력
        if (sstatus == 8 && bstatus == 8) {
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
                icon: 'error',
                title: '이미 철회상태입니다.'
            })
            return false;
        }

        //업데이트 시작
        //AJAX는 POST 방식으로 합니다. (리스트를 가져옵니다.
        $.post("${pageContext.request.contextPath}/deleteAdminOrder.do", {
            orderNum: id,
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
                    title: '주문 철회가 완료되었으며 이메일 전송을 완료하였습니다'
                })
                getOrder();
            }
        });

    }
</script>
</body>
</html>