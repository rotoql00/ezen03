<!--
FileName: user_manage.jsp
Description: 회원관리를 위한 페이지입니다.
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/admin/admin.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/selectric.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/admin.png">
</head>

<body onload="getUser()">
<%@ include file="../inc/admin_header.jsp" %>
<div class="content-padder content-background">
    <div class="uk-section-xsmall uk-section-default header">
        <div class="uk-container uk-container-large">
            <h1 class="bold"><span class="ion-speedometer"></span>회원관리</h1>
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
                                <form id="search" class="searchTab">
                                    <select name="category" class="optionTag" id="user_category">'
                                        <option selected value="0">전체</option>
                                        <option value="1">아이디</option>
                                        <option value="2">이름</option>
                                        <option value="3">전화번호</option>
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
                                    회원정보 <br><br>
                                    <span class="small">* 원하시는 회원을 찾아 클릭하여 수정해주세요<br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요</span>
                                </div>
                                <div class="uk-card-body">
                                    <table class="uk-table uk-table-striped  uk-table-responsive uk-table-hover">
                                        <thead>
                                        <tr>
                                            <th>성명 / 아이디
                                            </th>
                                            <th>이메일 / 휴대폰 번호
                                            </th>
                                            <th>가입일 / 인증여부
                                            </th>
                                            <th>거래수수료 / 배송쿠폰
                                            </th>
                                            <th>상태 / 탈퇴일
                                            </th>
                                            <th>등급</th>
                                            <th>배송정보</th>
                                            <th>탈퇴</th>
                                        </tr>
                                        </thead>
                                        <!-- HandleBar 추가되는 부분-->
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

<!-- HandleBar -->
<script type="text/x-handlebars-template" id="userList_tmpl">
    {{#each info}}
    <tr>
        <td>{{userName}}
            <hr class="table_hr">
            {{userId}}
        </td>
        <td>{{userEmail}}
            <hr class="table_hr">
            {{userPhonenum}}
        </td>
        <td>{{reg_date}}
            <hr class="table_hr">
            {{emailCheck}}
        </td>
        <td>{{fee userLevel}}%
            <hr class="table_hr">
            {{couponCount}}개
        </td>
        <td>{{status userdropTF user_penalty}}
            <hr class="table_hr">
            {{exit edit_date}}
        </td>
        <td>{{userLevel}}</td>
        <td>{{userAddress1}}<br>{{userAddress2}}</td>
        <td>
            <button class="{{checkStatusButton userdropTF}}" {{checkDisable userdropTF}} onclick="updateToDone({{id}})">
                {{checkDrop userdropTF}}
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
<!-- Plain Script-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.selectric.min.js"></script>
<script src="<%=request.getContextPath()%>/admin/manage_users/user_manage.js"></script>
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>

<script>
    /*
    0 = 전체
    1 = 아이디
    2 = 이름
    3 = 전화번호
    */
    function getUser() {
        // 파라미터 값을 가져옵니다.
        var options = "${param.category}";
        var search = "${param.keyword}";
        var page = "${param.page}"

        //만약에 옵션이 없다면 (즉, 아무 파라미터가 없을 경우에) 전체 검색을 실행합니다.
        if (options == "") {
            options = 0;
        }
        //페이지가 없을 경우 페이지를 1로 세팅합니다. (즉, 아무 파라미터가 없을 경우)
        if (page == "") {
            page = 1;
        }

        //selectric에서 선택사항을 가져옵니다.
        $('#user_category').prop('selectedIndex', options).selectric('refresh');

        //AJAX는 POST 방식으로 합니다. (리스트를 가져옵니다.)
        $.post("${pageContext.request.contextPath}/admin/user.do", {
            key: search,
            option: options,
            nowPage: page
        }, function (json) {
            var source1 = $("#userList_tmpl").html();   // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1);  // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#userList").html(result1); // 최종 결과물을 #list 요소에 추가한다.

            //페이징을 위한 구문입니다.

            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/manageUsers?category=" + options + "&keyword=" + search + "&page=" + json.page.prevPage + "\"><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#pagination").append("<li class=\"uk-active\"><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/manageUsers?category=" + options + "&keyword=" + search + "&page=" + i + "\">" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#pagination").append("<li><a href=\"${pageContext.request.contextPath}/admin/manageUsers?category=" + options + "&keyword=" + search + "&page=" + json.page.nextPage + "\"><span uk-pagination-next></span></a></li>")
            }
        })
        Handlebars.registerHelper('fee', function (text) {
            //레벨에서 8을 더하면 수수료가 나옵니다
            return text + 8;
        })

        Handlebars.registerHelper('exit', function (text) {
            if (text == "9999-12-31") {
                return "정상";
            } else {
                return text;
            }
        })

        Handlebars.registerHelper('status', function (text1, text2) {
            if (text1 == "Y") {
                return "탈퇴";
            } else if (text2 == "Y") {
                return "패널티";
            } else {
                return "정상";
            }
        })

        Handlebars.registerHelper('checkStatusButton', function (text) {

            //둘다 검수중일때 버튼이 생성됩니다.
            if (text == "N") {
                return "checkButton";
            } else {
                return "uncheckButton"
            }
        });

        Handlebars.registerHelper('checkDisable', function (text) {

            if (text == "Y") {
                return "disabled"
            }
        });

        Handlebars.registerHelper('checkDrop', function (text) {
            if (text == "N") {
                return "탈퇴";
            } else {
                return "탈퇴완료"
            }
        });
    }

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
        Swal.fire({
            title: '탈퇴',
            text: "회원을 강제 탈퇴시키시겠습니까?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '탈퇴',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.value) {
                $.post("${pageContext.request.contextPath}/admin/dropUser.do", {
                    ID: ID
                }, function (success) {
                    if (success == 1) {
                        Swal.fire(
                            '탈퇴완료',
                            '회원을 강제 탈퇴하였습니다.',
                            'success'
                            )
                        getUser();
                    }
                });
            }
        })
    }
</script>
</body>

</html>