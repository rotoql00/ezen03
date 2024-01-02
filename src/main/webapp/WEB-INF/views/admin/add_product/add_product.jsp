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
    <link rel="stylesheet" href=<%=request.getContextPath()%>"/assets/css/nice-select.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/selectric.css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assets/plugin/air-datepicker/datepicker.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/admin/admin.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/admin.png">
</head>

<body>
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
                    <div class="uk-card uk-card-default">
                        <div class="uk-card-header bold">
                            상품등록 <br><br>
                            <span class="small">* 상품을 찾으시려면 아래로 스크롤해주세요. <br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요<br>* <mark>Enter</mark> 와 <mark>Tab</mark> 버튼을 통해 정규화 검사를 합니다 오른쪽 하단에 에러메세지가 출력됩니다.
                                <br>* 상품을 등록할때에는 항상 주의바랍니다.</span>
                        </div>
                        <div class="uk-card-body">

                            <!-- FORM 시작 -->
                            <form id="addProductInfo" method="post" class="uk-grid-small uk-form-stacked" uk-grid
                                  uk-margin enctype="multipart/form-data">
                                <!-- 상품명(한글) -->
                                <div class="uk-width-1-2">
                                    <label class="uk-form-label bold" for="productName_kr">상품명(한글)</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="productName_kr" type="text"
                                               placeholder="ex) 이지부스트 380 에일리언" onkeyup="validate()"
                                               name="productNameKR">
                                    </div>
                                </div>
                                <!-- 상품명(영어) -->
                                <div class="uk-width-1-2">
                                    <label class="uk-form-label bold" for="productName_en">상품명(영어)</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="productName_en" type="text"
                                               placeholder="ex) Yeezy Boost 380 Alien" name="productNameEN">
                                    </div>
                                </div>
                                <!-- 상품코드(스타일코드) -->
                                <div class="uk-width-1-2">
                                    <label class="uk-form-label bold" for="styleCode">상품코드(스타일코드)</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="styleCode" type="text"
                                               placeholder="ex) FB6878"
                                               name="productCode">
                                    </div>
                                </div>
                                <!-- 색상 -->
                                <div class="uk-width-1-4@s">
                                    <label class="uk-form-label bold " for="productColor">색상</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="productColor" type="text"
                                               placeholder="ex) Alien/Alien/Alien" name="productColor">
                                    </div>
                                </div>

                                <!-- 발매일 -->
                                <div class="uk-width-1-4@s">
                                    <label class="uk-form-label bold" for="releaseDate">발매일</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input datepicker-here enterDetection" id="releaseDate"
                                               type="text"
                                               placeholder="ex) 12/29/2019" name="productReleaseDate">
                                    </div>
                                </div>

                                <!-- 발매가 -->
                                <div class="uk-width-1-4@s">
                                    <label class="uk-form-label bold" for="releasePrice">발매가</label>
                                    <input class="uk-input enterDetection" id="releasePrice" type="text"
                                           placeholder="ex) 126,222" name="productReleasePrice">
                                </div>

                                <!-- 단위 -->
                                <div class="uk-width-auto@m">
                                    <label class="uk-form-label bold" for="unit">단위</label>
                                    <div class="uk-form-controls">
                                        <select name="productUnit" class="optionTag" id="unit">
                                            <option selected value="$">$</option>
                                            <option value="₩">₩</option>
                                        </select>
                                    </div>
                                </div>

                                <!-- 상품종류 -->
                                <div class="uk-width-1-5@s">
                                    <label class="uk-form-label bold" for="productBrand">상품종류</label>
                                    <div class="uk-form-controls">
                                        <select name="productBrand" class="optionTag" id="productBrand">
                                            <option value="default" disabled selected>선택</option>
                                            <option value="Nike">나이키</option>
                                            <option value="Adidas">아디다스</option>
                                            <option value="Jordan">조던</option>
                                            <option value="Converse">컨버스</option>
                                            <option value="Vans">반스</option>
                                            <option value="Others">기타</option>
                                        </select>
                                    </div>
                                </div>

                                <!-- 이미지 업로드 -->
                                <div class="uk-width-1-1">
                                    <label class="uk-form-label bold" for="image">상품이미지 업로드&nbsp;&nbsp;&nbsp;<span
                                            class="small">* 텍스트 박스를 클릭하여
                                        업로드해주세요</span></label>
                                    <div uk-form-custom="target: true">
                                        <input type="file" name="image" id="image">
                                        <input class="uk-input uk-form-width-large" type="text"
                                               placeholder="Select file" disabled>
                                    </div>
                                </div>
                            </form>
                            <!-- submit -->
                            <div class="uk-width-1-1">
                                <div class="uk-flex uk-flex-right bottom_tab">
                                    <button onclick="checkAll()" class="uk-button uk-button-secondary">등록
                                    </button>
                                </div>
                            </div>
                        </div>
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
<script src="<%=request.getContextPath()%>/assets/js/jquery.selectric.min.js"></script>
<!-- Handlebar Script-->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!-- air-datepicker -->
<script src="<%=request.getContextPath()%>/assets/plugin/air-datepicker/datepicker.min.js"></script>
<!-- sweet alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- Plain Script-->
<script src="<%=request.getContextPath()%>/admin/add_product/add_product.js"></script>
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>

<script>
    function addProduct() {
        const form = new FormData(document.getElementById("addProductInfo"));
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/adminAddProduct.do", //컨트롤러 URL
            data: form,
            dataType: 'json',
            processData: false,
            contentType: false,
            type: 'POST',
            success: function () {
                Swal.fire(
                    '상품등록 완료',
                    '상품목록에서 상품을 확인해주세요',
                    'success'
                )
            },
            error: function () {
                Swal.fire(
                    '상품등록 실패',
                    '로그를 확인해주세요',
                    'error'
                )
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
</script>
</body>

</html>