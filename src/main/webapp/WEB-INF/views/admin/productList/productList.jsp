<!--
FileName: add_product.jsp
Description: 상품등록을 위한 관리자 페이지 입니다.
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

<body onload="getProduct()">
<%@ include file="../inc/admin_header.jsp" %>
<div class="content-padder content-background">
    <div class="uk-section-xsmall uk-section-default header">
        <div class="uk-container uk-container-large">
            <h1 class="bold"><span class="ion-speedometer"></span>상품등록</h1>
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
                                        <option value="1">상품코드</option>
                                        <option value="2">상품명</option>
                                        <option value="3">브랜드</option>
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
                                <div class="uk-card-header bold">상품정보 <br><br>
                                    <span class="small">* 상품 상세정보를 보시려면 클릭해주세요 <br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요</span>
                                </div>
                                <div class="uk-card-body">
                                    <table class="uk-table uk-table-striped uk-table-responsive uk-table-hover">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th>상품명</th>
                                            <th>상품코드(브랜드) / 색상</th>
                                            <th>발매일 / 등록일</th>
                                            <th>발매가</th>
                                            <th>삭제</th>
                                        </tr>
                                        </thead>
                                        <tbody id="productList">
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

<script type="text/x-handlebars-template" id="productList_tmpl">
    {{#each info}}
    <tr class="thumbTable">
        <td><img class="shoeImg" src="${context}{{product_thumb}}" alt="{{productNameEN}}"></td>
        <td>{{productNameKR}}
            <hr class="table_hr">
            {{productNameEN}}
        </td>
        <td>{{productCode}}({{productBrandEN}})
            <hr class="table_hr">
            {{productColor}}
        </td>
        <td>{{date productReleaseDate}}
            <hr class="table_hr">
            {{date reg_date}}
        </td>
        <td>{{productUnit}}{{comma productReleasePrice}}</td>
        <td>
            <button class="{{checkStatusButton productDrop}}" onclick="updateToDone({{id}}, '{{productDrop}}')">
                {{checkDrop productDrop}}
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
<script src="<%=request.getContextPath()%>/admin/productList/productList.js"></script>
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>

<script>
    function getProduct() {
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
        $.post("${pageContext.request.contextPath}/admin/productList.do", {
            key: search,
            option: options,
            nowPage: page
        }, function (json) {
            var source1 = $("#productList_tmpl").html();   // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1);  // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#productList").html(result1); // 최종 결과물을 #list 요소에 추가한다.

            //페이징을 위한 구문입니다.

            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/productList?category=" + options + "&keyword=" + search + "&page=" + json.page.prevPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/productList?category=" + options + "&keyword=" + search + "&page=" + i + "\">" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/productList?category=" + options + "&keyword=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
            }
        });
    }

    // 가격정보에 콤마를 찍어줍니다. (Handlebar only)
    Handlebars.registerHelper('comma', function (text) {
        str = String(text);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    });

    // 상태에 대한 document 처리입니다.(Handlebar only)
    Handlebars.registerHelper('date', function (text) {
        var str = text.substring(0, 10);
        return str;
    });

    Handlebars.registerHelper('checkStatusButton', function (text) {
        console.log(text);
        //둘다 검수중일때 버튼이 생성됩니다.
        if (text == "N") {
            return "checkButton";
        } else {
            return "uncheckButton";
        }
    });

    Handlebars.registerHelper('checkDrop', function (text) {
        if (text == "N") {
            return "삭제";
        } else {
            return "복구";
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

    function updateToDone(ID, drop) {
        console.log(drop);

        if (drop == "N") {
            drop = "Y";
            Swal.fire({
                title: '삭제',
                text: "상품을 삭제하시겠습니까?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '삭제',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.value) {
                    $.post("${pageContext.request.contextPath}/admin/dropProduct.do", {
                        ID: ID,
                        drop: drop
                    }, function (success) {
                        if (success == 1) {
                            Swal.fire(
                                '삭제완료',
                                '상품을 삭제하였습니다.',
                                'success'
                                )
                            getProduct();
                        }
                    })
                }
            })
        } else {
            drop = "N";
            Swal.fire({
                title: '복구',
                text: "상품을 복구하시겠습니까?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '복구',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.value) {
                    $.post("${pageContext.request.contextPath}/admin/dropProduct.do", {
                        ID: ID,
                        drop: drop
                    }, function (success) {
                        if (success == 1) {
                            Swal.fire(
                                '복구완료',
                                '상품을 복구하였습니다.',
                                'success'
                                )
                            getProduct();
                        }
                    })
                }
            })
        }
    }
</script>

</body>

</html>