<!--
FileName: control_popular.jsp
Description: 인기상품을 설정하기위한 페이지 입니다.
Author: 임석현, 임채린
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>

<head>
    <meta charset="UTF-8"/>
    <title>TIESHOE ADMIN</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/nice-select.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/admin/admin.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/admin.png">
</head>

<body>
<%@ include file="../inc/admin_header.jsp" %>
<div class="content-padder content-background">
    <div class="uk-section-xsmall uk-section-default header">
        <div class="uk-container uk-container-large">
            <h1 class="bold"><span class="ion-speedometer"></span>상품노출순위</h1>
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
                                <div class="uk-search uk-search-default input_margin">
                                    <!-- 서치 -->
                                    <form id="search" method="get"
                                          action="<%=request.getContextPath()%>/admin/popProductList">
                                        <div class="inline uk-search uk-search-default input_margin">
                                            <span uk-search-icon class="input_margin"></span>
                                            <input id="searchKeyword" name="search" class="uk-search-input input_box"
                                                   type="search" placeholder="상품코드 또는 상품명" value="${param.search}">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-2@l">
                <div>
                    <div class="uk-card uk-card-default">
                        <div class="uk-card-header bold">
                            상품노출순위 <br><br>
                            <span class="small">* 배치되어있는 순서대로 DB에 저장됩니다. <br>* JSON형식으로 저장됩니다.<br>* 상품갯수는 제한이 없지만 가급적 16개로 맞춰주시기 바랍니다.<br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요</span>
                        </div>
                        <div class="uk-card-body">
                            <div id="group1" uk-sortable="group: sortable-group" class="uk-sortable">
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="uk-card uk-card-default">
                        <div class="uk-card-header bold">
                            상품리스트<br><br>
                            <span class="small">* 상품을 끌어다 옆에있는 상품노출차트에 삽입해주세요 <br>* 위에 있는 서치바를 사용해 상품을 찾아주세요. <br>* 상품갯수는 제한이 없지만 가급적 16개로 맞춰주시기 바랍니다.<br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요</span>
                        </div>
                        <div class="uk-card-body">
                            <div id="group2" uk-sortable="group: sortable-group" class="uk-sortable">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-1@l">
                <div>
                    <div class="uk-card uk-card-default">
                        <div class="uk-card-header bold">
                            JSON 확인 <br><br>
                            <span class="small">* 위 상품노출 순위에 대한 JSON입니다. <br>* JSON 수정사항이 있다면 여기서 수정해주세요 <br>* 여기있는 JSON 그대로 DB에 삽입됩니다..<br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요</span>
                        </div>
                        <form id="popProduct" method="get"
                              action="<%=request.getContextPath()%>/admin/popProduct_ok.do">
                            <div class="uk-card-body">
                                <div class="uk-form-controls">
                                    <input id="output_group1" class="uk-input" type="text"
                                           name="popular_product">
                                </div>
                                <div class="uk-width-1-1">
                                    <div class="uk-flex uk-flex-right margin_top">
                                        <button type="submit" value="submit" id="addProduct"
                                                onclick="trim(this)"
                                                class="uk-button uk-button-secondary">등록
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Jquery 3.4.1 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.transit/0.9.12/jquery.transit.min.js"></script>
<!-- UI-kit Script-->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<!-- Handlebar Script-->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!-- Plain Script-->
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.nice-select.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.form.min.js"></script>
<script src="<%=request.getContextPath()%>/admin/control_popular/control_popular.js"></script>
<!-- AJAX -->
<script id="search-tmpl" type="text/x-handlebars-template">
    {{#each item}}
    <div id={{id}} class="uk-margin">
        <div class="uk-card uk-card-default uk-card-body uk-card-small">{{productNameEN}}</div>
    </div>
    {{/each}}
</script>
<script>
    $(document).ready(function () {
        $.post("${pageContext.request.contextPath}/admin/popProductList?search=1", function (json) {
            var source1 = $("#search-tmpl").html(); // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1); // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#group2").html(result1); // 최종 결과물을 #list 요소에 추가한다.
        });
    });

    $(function () {
        $("#search").ajaxForm(function (json) {
            var source1 = $("#search-tmpl").html(); // 템플릿 코드 가져오기
            var template1 = Handlebars.compile(source1); // 템플릿 코드 컴파일
            var result1 = template1(json); // 템플릿 컴파일 결과물에 json 전달
            $("#group2").html(result1); // 최종 결과물을 #list 요소에 추가한다.
        });
    });
</script>
</body>
</html>