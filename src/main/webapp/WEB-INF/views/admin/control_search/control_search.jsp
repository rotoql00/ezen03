<!--
FileName: user_manage.jsp
Description: 검색어 순위 관리를 위한 페이지입니다.
Author: 임석현, 임채린
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/nice-select.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/admin/admin.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/admin.png">
</head>

<body>
<%@ include file="../inc/admin_header.jsp" %>
<div class="content-padder content-background">
    <div class="uk-section-xsmall uk-section-default header">
        <div class="uk-container uk-container-large">
            <h1 class="bold"><span class="ion-speedometer"></span>검색어노출순위</h1>
        </div>
    </div>
    <div class="uk-section-small">
        <!-- 내용 -->
        <div class="uk-container uk-container-large">
            <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-1@l">
                <div>
                    <div class="uk-card uk-card-default">
                        <div class="uk-card-header bold">
                            검색어노출순위 <br><br>
                            <span class="small">* 공지사항 제목을 작성한 후에 본문을 작성해주세요 <br>* 특정 픽셀로 내려가면 깨질수 있으니 사이드 바를 닫아주세요</span>
                        </div>
                        <div class="uk-card-body">

                            <!-- FORM 시작 -->
                            <form id="searchRank" method="get" class="uk-grid-small uk-form-stacked" uk-grid uk-margin action="<%=request.getContextPath()%>/admin/popSearch_ok.do">
                                <!-- 상품명(한글) -->
                                <div class="uk-width-1-1">
                                    <label class="uk-form-label bold" for="first">1 순위</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="first" type="text"
                                               placeholder="검색어를 입력해주세요" name="popularFirst">
                                    </div>
                                </div>
                                <div class="uk-width-1-1">
                                    <label class="uk-form-label bold" for="second">2 순위</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="second" type="text"
                                               placeholder="검색어를 입력해주세요" name="popularSecond">
                                    </div>
                                </div>
                                <div class="uk-width-1-1">
                                    <label class="uk-form-label bold" for="third">3 순위</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="third" type="text"
                                               placeholder="검색어를 입력해주세요" name="popularThird">
                                    </div>
                                </div>
                                <div class="uk-width-1-1">
                                    <label class="uk-form-label bold" for="fourth">4 순위</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="fourth" type="text"
                                               placeholder="검색어를 입력해주세요" name="popularFourth">
                                    </div>
                                </div>
                                <div class="uk-width-1-1">
                                    <label class="uk-form-label bold" for="fifth">5 순위</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="fifth" type="text"
                                               placeholder="검색어를 입력해주세요" name="popularFifth">
                                    </div>
                                </div>
                                <div class="uk-width-1-1">
                                    <label class="uk-form-label bold" for="sixth">6 순위</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="sixth" type="text"
                                               placeholder="검색어를 입력해주세요" name="popularSixth">
                                    </div>
                                </div>
                                <div class="uk-width-1-1">
                                    <label class="uk-form-label bold" for="seventh">7 순위</label>
                                    <div class="uk-form-controls">
                                        <input class="uk-input enterDetection" id="seventh" type="text"
                                               placeholder="검색어를 입력해주세요" name="popularSeventh">
                                    </div>
                                </div>
                                <div class="uk-width-1-1">
                                    <div class="uk-flex uk-flex-right margin_top">
                                        <button type="submit" value="submit" id="addRank"
                                                class="uk-button uk-button-secondary" onclick="check()">등록
                                        </button>
                                    </div>
                                </div>
                            </form>

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
<!-- Handlebar Script-->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!-- ckeditor for Email-->
<script src="https://cdn.ckeditor.com/ckeditor5/16.0.0/classic/ckeditor.js"></script>
<!-- 드롭다운 -->
<script src="<%=request.getContextPath()%>/assets/js/jquery.nice-select.js"></script>
<!-- Plain Script-->>
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="<%=request.getContextPath()%>/admin/control_search/control_search.js"></script>
</body>

</html>