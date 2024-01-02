<!--
FileName: popupCurrent.html
Description: 시세확인에 대한 HTML입니다.
Author: 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/nice-select.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/popupCurrent.css">
</head>
<body>

<!-- 판매가에 대한 팝업-->
<div class="current_statusPopup_Wrapper Popup_Wrapper_current">
    <div class="current_statusPopup Popup_current">
        <div class="popupline"></div>
        <div class="current_statusPopup_close close"></div>
        <div class="current_statusPopupContent">
            <h1>시세확인</h1>

            <div class="tabbed">
                <input type="radio" name="product_tabs" id="tab-nav-1">
                <label for="tab-nav-1" onclick="connector(1)">희망 판매가</label>
                <input type="radio" name="product_tabs" id="tab-nav-2">
                <label for="tab-nav-2" onclick="connector(2)">희망 입찰가</label>
                <input type="radio" name="product_tabs" id="tab-nav-3">
                <label for="tab-nav-3" onclick="connector(3)">거래가격</label>

                <div class="product_option">
                    <select id="sizeOption" class="nice-select small" name="size" onchange="sizeControl()">
                        <option value="100" selected>전체</option>
                        <option value="220">220</option>
                        <option value="225">225</option>
                        <option value="230">230</option>
                        <option value="235">235</option>
                        <option value="240">240</option>
                        <option value="245">245</option>
                        <option value="250">250</option>
                        <option value="255">255</option>
                        <option value="260">260</option>
                        <option value="265">265</option>
                        <option value="270">270</option>
                        <option value="275">275</option>
                        <option value="280">280</option>
                        <option value="285">285</option>
                        <option value="290">290</option>
                        <option value="295">295</option>
                        <option value="300">300</option>
                    </select>
                </div>

                <div class="product_tabs">
                    <div>
                        <table>
                            <tr>
                                <th>옵션</th>
                                <th>희망 판매가</th>
                                <th>가능 수량</th>
                            </tr>
                            <tbody id="sellPopup"></tbody>
                        </table>
                        <ul class="uk-pagination uk-flex-center push" uk-margin id="paginationSell">
                        </ul>
                    </div>

                    <div>
                        <table>
                            <tr>
                                <th>옵션</th>
                                <th>희망 입찰가</th>
                                <th>가능 수량</th>
                            </tr>
                            <tbody id="buyPopup"></tbody>
                        </table>
                        <ul class="uk-pagination uk-flex-center push" uk-margin id="paginationBuy">
                        </ul>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th>옵션</th>
                                <th>거래가</th>
                                <th>거래일</th>
                            </tr>
                            <tbody id="orderPopup"></tbody>
                        </table>
                        <ul class="uk-pagination uk-flex-center push" uk-margin id="paginationOrder">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Handle Bar -->
<script id="sellPopup-tmpl" type="text/x-handlebars-template">
    {{#each sellAllPrice}}
    <tr>
        <td>{{sellSize}}</td>
        <td>{{comma sellPrice}}</td>
        <td>{{countProduct}}</td>
    </tr>
    {{/each}}
</script>

<script id="buyPopup-tmpl" type="text/x-handlebars-template">
    {{#each buyAllPrice}}
    <tr>
        <td>{{buySize}}</td>
        <td>{{comma buyPrice}}</td>
        <td>{{countProduct}}</td>
    </tr>
    {{/each}}
</script>

<script id="orderPopup-tmpl" type="text/x-handlebars-template">
    {{#each orderAllPrice}}
    <tr>
        <td>{{orderSize}}</td>
        <td>{{comma orderPrice}}</td>
        <td>{{reg_date}}</td>
    </tr>
    {{/each}}
</script>
<!-- EOF 판매가에 대한 팝업 끝 -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.2/handlebars.min.js"></script>
<!-- 드롭다운을 위해 가져옵니다.-->
<script src="<%=request.getContextPath()%>/assets/js/popupCurrent.js"></script>

</body>
</html>
