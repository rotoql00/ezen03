<!--
FileName: sell.html
Description: 판매 페이지이며 가격을 입력합니다.
Author: 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sell</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/sell.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/selectric.css">
    <link rel="icon"
          href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
    <!-- 배송정보 수정을 위한 modal의 css -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/myPage_change_addr.css">
</head>
<!--
#SellSize = 옵션 (사이즈)
#SellHighbid = 최고 입찰가
#SellLowsell = 최저 판매가
-->
<body onload="userAddrInfo();">
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<div id="container">
    <div class="product_title-fake">
        <h2 id=product_name_fake>${product.productNameEN}</h2>
        <h3 id=product_name_ko_fake>${product.productNameKR}</h3>
    </div>
    <div class="left_container">
        <div class="product_title">
            <h2 id=product_name>${product.productNameEN}</h2>
            <h3 id=product_name_ko>${product.productNameKR}</h3>
        </div>
        <!-- 상품 이미지 -->
        <img src="${context}${product.productImgPath}"
             alt="${product.productNameKR}">
        <div class="Sell_information">
            <p>
                <i class="fas fa-angle-right"></i> 거래성사 후 상품을 배송하지 않으면 패널티 처리됩니다.
            </p>
            <p>
                <i class="fas fa-angle-right"></i> 거래성사 후 2일(영업일 기준)이내 배송번호를 입력하지
                않으면 패널티 처리됩니다.
            </p>
            <p>
                <i class="fas fa-angle-right"></i> 박스불량, 상품상태불량, 가품 등 비정상 상품을 배송하면
                패널티 처리됩니다..
            </p>
        </div>
    </div>

    <!-- 오른쪽 부분-->
    <div class="right_container">
        <p class="Sell_title">
            <c:choose>
                <c:when test="${sellInfo.ID eq 0}">
                    판매 가격 등록
                </c:when>
                <c:otherwise>
                    판매 정보 수정
                </c:otherwise>
            </c:choose>
        </p>

        <!-- 선택사항 (옵션, 최고입찰가 등등...-->
        <div class="option">
            <div class="option_adjustment">
                <div class="Sell_left">
                    옵션 &nbsp <span id="SellSize" class="option_font_size">${param.size}</span>
                </div>
                <div class="Sell_left">
                    최고 입찰가: &nbsp <span id="SellLowbid" class="option_font">₩ <c:choose>
                    <c:when test="${empty highest.buyHighPrice}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${highest.buyHighPrice}"
                                          pattern="#,###"/>
                    </c:otherwise>
                </c:choose>
						</span>
                </div>
                <div class="Sell_left">
                    최저 판매가: <span id="SellHighsell" class="option_font">₩ <c:choose>
                    <c:when test="${empty lowest.buyLowPrice}">
                        0
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${lowest.buyLowPrice}" pattern="#,###"/>
                    </c:otherwise>
                </c:choose>
						</span>
                </div>
            </div>
        </div>
        <hr class="Sell_hr_bold">

        <!-- 시세확인-->
        <div class="product_allprice">
            <span class="option_2">옵션별 가격</span> <span>
					<button type="button" class="current_status buy_button"
                            id="product_currentBuy" onclick="connector(2)">입찰가 가격</button>
				</span> <span>
					<button type="button" class="current_status sell_button"
                            id="product_currentSell" onclick="connector(1)">판매가 현황</button>
				</span> <span>
					<button type="button" class="current_status deal_button"
                            id="product_currentTranscrion" onclick="connector(3)">거래
						가격</button>
				</span>
        </div>


        <!-- 판매가에 대한 팝업-->
        <%@ include file="/WEB-INF/views/inc/popupCurrent.jsp" %>

        <hr class="Sell_hr_bold">

        <!--입찰가에 대한 HTML-->
        <form action="<%=request.getContextPath()%>/sellProcess.do"
              onsubmit="return validateCheckBox()" id="Sell_price_form"
              METHOD="post">
            <div class="Sell_first_input">
                <div class="Sell_final_price">판매 가격</div>
                <!--input tag(가격 입력)-->
                <div class="Sell_input_price">
                    <label for="price" class="won">₩</label> <input type="text"
                                                                    name="productNum" value="${product.ID}" hidden>
                    <input
                            type="text" name="size" value="${param.size}" hidden> <input
                        type="text" name="myPagePrice" value="${sellInfo.sellPrice}"
                        hidden> <input type="text" name="myPageID"
                                       value="${sellInfo.ID}" hidden> <input type="text"
                                                                             class="Sell_input_price_text Sell_all_input"
                                                                             placeholder=
                                                                             <c:choose>
                                                                             <c:when test="${sellInfo.ID eq 0}">
                                                                                     "0"
                    </c:when>
                    <c:otherwise>
                        "<fmt:formatNumber value="${sellInfo.sellPrice}" pattern="#,###"/>"
                    </c:otherwise>
                    </c:choose>
                    id="price" name="user_price"
                    onkeyup="calculation(${user.userLevel})">

                    <div class="Sell_instance_info">
                        <c:choose>
                        <c:when test="${empty highest.buyHighPrice}">
                        <!-- 위 option에서 최저가 판매가를 가져옵니다-->
                        <div class="Sell_instance_get" id="Sell_instance"
                             style="color: red">입찰가격이 존재하지 않습니다
                            </c:when>
                            <c:otherwise>
                            <div class="Sell_instance_price">바로판매가격</div>
                            <!-- 위 option에서 최저가 판매가를 가져옵니다-->
                            <div class="Sell_instance_get" id="Sell_instance">
                                <fmt:formatNumber value="${highest.buyHighPrice}"
                                                  pattern="#,###"/>
                                </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>

                <hr class="Sell_hr_bold">

                <div class="Sell_shipping_price_container">
                    <div class="Sell_shipping_price_text">
                        거래수수료(
                        <!-- 사용자의 레벨에 따라 할인율이 바뀝니다.-->
                        <c:choose>
                            <c:when test="${user.userLevel eq 5}">13%</c:when>
                            <c:when test="${user.userLevel eq 4}">12%</c:when>
                            <c:when test="${user.userLevel eq 3}">11%</c:when>
                            <c:when test="${user.userLevel eq 2}">10%</c:when>
                            <c:otherwise>9%</c:otherwise>
                        </c:choose>
                        )
                    </div>
                    <div class="Sell_shipping_price" id="Sell_cons">₩ 0</div>
                </div>


                <hr class="Sell_hr_fade">

                <div class="Sell_shipping_price_container">
                    <div class="Sell_shipping_totalprice_text">합계</div>
                    <div class="Sell_shipping_totalprice" id="Sell_totalPrice">₩ 0</div>
                </div>

                <hr class="Sell_hr_bold">

                <!-- 입찰 마감일-->
                <div class="Sell_bidDate">
                    <div class="Sell_bidDate_text">판매 마감일</div>
                    <div class="Sell_bidDate_dropdown" id="select">
                        <select name="bidDate">
                            <option value="1">1일</option>
                            <option value="2">3일</option>
                            <option selected value="7">7일</option>
                            <option value="15">15일</option>
                            <option value="30">30일</option>
                            <option value="60">60일</option>
                        </select>
                    </div>
                </div>
        </form>

        <hr class="Sell_hr_fade">

        <div class="Sell_address_container" id="address_container">
            <input id="userIDNum" type="hidden" name="ID"
                   value="${sessionScope.loginID}">
            <div class="Sell_address">배송</div>
            <div class="Sell_address_info" id="userAddrInfo"></div>
            <!-- 배송정보 수정 시작 -->
            <div class="address_edit">
                <button class="Sell_edit_size" href="#myPage_edit_shipping"
                        uk-toggle="">편집
                </button>
                <!-- <button type="button" class="buy_edit_size">수정</button> -->
                <div id="myPage_edit_shipping" uk-modal>
                    <div class="uk-modal-dialog">
                        <button class="uk-modal-close-default" type="button" uk-close></button>
                        <div class="uk-modal-header">
                            <h2 class="uk-modal-title">배송 정보 변경</h2>
                        </div>
                        <!-- Body of the Modal-->
                        <div class="uk-modal-body" id="userAddrChangeModal">
                            <form class="uk-margin-auto uk-width-large" id="reset_addr_form"
                                  onsubmit="editUserAddr()">
                                <!-- action="${pageContext.request.contextPath}/buy/editUserAddr_ok" -->
                                <input type="hidden" name="ID" id="loginID"
                                       value="${sessionScope.loginID}">
                                <!-- 주소 -->
                                <label class="change_addr_info" for="change_user_addr">주소
                                    *</label>
                                <div class="changeAddrArea">
                                    <br>
                                    <div class="uk-margin uk-position-relative">
                                        <div class="uk-inline zip_code" style="width: 50%;">
                                            <span class="uk-form-icon" uk-icon="icon:location"></span> <input
                                                class="uk-input zip_code" type="text" id="zip_code"
                                                name="userZipcode" style="cursor: not-allowed;"
                                                placeholder="우편번호 Zone Code" readonly><br>
                                        </div>
                                        <button
                                                class="uk-button uk-button-secondary uk-position-right"
                                                type="button" style="padding: 0px 20px 0px 20px;"
                                                onclick="openAddSearch()">주소찾기
                                        </button>
                                    </div>
                                    <label class="uk-alert-danger error" for="zip_code"
                                           generated="true" uk-alert></label>
                                    <div class="uk-margin">
                                        <div class="uk-inline" style="width: 100%;">
                                            <span class="uk-form-icon" uk-icon="icon:location"></span> <input
                                                class="uk-input addr_1 change_user_info" type="text"
                                                id="addr_1" name="userAddress1" style="width: 100%;"
                                                style="cursor: not-allowed;" placeholder="도로명 주소 Address_1"
                                                readonly>
                                        </div>
                                    </div>
                                    <label class="uk-alert-danger error" for="addr_1"
                                           generated="true" uk-alert></label>
                                    <div class="uk-margin">
                                        <div class="uk-inline" style="width: 100%;">
                                            <span class="uk-form-icon" uk-icon="icon:pencil"></span> <input
                                                class="uk-input change_user_info" type="text" id="addr_2"
                                                name="userAddress2" style="width: 100%;"
                                                placeholder="상세주소 Address_2">
                                        </div>
                                    </div>
                                    <label class="uk-alert-danger error" for="addr_2"
                                           generated="true" uk-alert></label>
                                </div>
                                <div
                                        class="uk-modal-footer uk-text-right myPage_change_addr_button">
                                    <button class="uk-button uk-button-default uk-modal-close"
                                            type="reset">취소
                                    </button>
                                    <button type="button" class="uk-button uk-button-secondary"
                                            id="changeAddrBTN">변경
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- 배송정보 수정 끝 -->
            </div>
            <hr class="Sell_hr_fade">


            <div class="Sell_submit_container">
                <div class="Sell_cancel">
                    <button type="button" class="Sell_cancel_button"
                            onclick="history.back()">취소
                    </button>
                </div>
                <div class="Sell_submit">
                    <button type="button" class="Sell_submit_button" value="submit"
                            onclick="validate(${highest.buyHighPrice})">판매내용 확인
                    </button>
                </div>

                <div id="check" uk-modal>
                    <div class="uk-modal-dialog">
                        <button class="uk-modal-close-default" type="button" uk-close></button>
                        <div class="uk-modal-header">
                            <h2 class="uk-modal-title">판매 주의사항</h2>
                        </div>
                        <div class="uk-modal-body">
                            <div class="bold">
                                <span uk-icon="nut"></span>고객님께서 물건을 입찰 하실시 다음과 같은 주의사항이 있으므로<br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;꼭 확인해 주신뒤 해당 사항에 체크를하여 입찰을 진행해 주시길
                                바랍니다.
                            </div>
                            <div class="bold checkbox">
                                <label><input type="checkbox" class="uk-checkbox"
                                              id="firstCheck"> 오리지널 박스가 포함된 정상품을 배송합니다. </label>
                            </div>
                            <div class="bold checkbox">
                                <label><input type="checkbox" class="uk-checkbox"
                                              id="secondCheck"> 기간내 배송하지 않으면 패널티를 감수하겠습니까?</label>
                            </div>
                            <div class="bold checkbox">
                                <label><input type="checkbox" class="uk-checkbox"
                                              id="thirdCheck"> 위 내용을 확인하고 제품판매를 하시겠습니까? </label>
                            </div>

                            <div class="bold info">
                                위와 같은 주의사항을 모두 확인 하셨고 이에 동의 하신다면 <br> 판매를 마저 진행 해 주시길 바랍니다.
                            </div>
                        </div>

                        <div class="uk-modal-footer uk-text-right">
                            <button type="submit"
                                    class="uk-button uk-button-default sell_final_submit_button"
                                    value="submit" id="submit_every" form="Sell_price_form">판매
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="addrCheck" hidden></div>
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- validate plugin CDN -->
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
<!-- 드롭다운을 위해 가져옵니다.-->
<script
        src="<%=request.getContextPath()%>/assets/js/jquery.selectric.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/sell.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/popupCurrent.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- daum 주소찾기 CDN -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/validate.js"></script>
<script>
    /** 배송정보 불러오기 */
    function userAddrInfo() {
        var ID = {
            ID: $("#userIDNum").val()
        }

        $.ajax({
            url: "${pageContext.request.contextPath}/callUserInfo.do",
            type: "GET",
            data: ID,
            dataType: "json",
            success: function (data) {
                console.log(data);
                var str = "";
                str += "( " + data.output.userZipcode + " )";
                str += "&nbsp" + data.output.userAddress1;
                str += "&nbsp" + data.output.userAddress2;
                if (data.output.userZipcode == "00000") {
                    $("#userAddrInfo").html("구매&판매를 하시려면 배송정보를 먼저 등록하세요.");
                    $("#addrCheck").html("1");
                } else {
                    $("#userAddrInfo").html(str);
                    $("#addrCheck").html("0");
                }
            }// sucess
        });// ajax
    }// end userAddrInfo


    /** 배송 정보 수정 하기 */
    $("#changeAddrBTN").click(function () {
        if ($("#zip_code").val() == "" || $("#addr_1").val() == "") {
            var Toast = Swal.fire({
                icon: 'error',
                title: '주소 공백 불가',
                text: '주소 찾기를 사용해주세요.',
            })
        } else if ($("#addr_2").val() == "") {
            var Toast = Swal.fire({
                icon: 'error',
                title: '주소 공백 불가',
                text: '상세 주소를 입력해주세요.',
            })
        }

        var query = {
            ID: $("#loginID").val(),
            userZipcode: $("#zip_code").val(),
            userAddress1: $("#addr_1").val(),
            userAddress2: $("#addr_2").val()
        };

        $.ajax({
            url: "${pageContext.request.contextPath}/buy/editUserAddr_ok",
            type: "post",
            data: query,
            success: function (data) {
                $("#addrCheck").html("0");
                userAddrInfo();
                //$("#userAddrChangeModal").modal("hide");
                UIkit.modal("#myPage_edit_shipping").hide();
            } // success
        }); // ajax 끝
        return true;
    });


    var onoff = 0;
    var sizeControlVal = 0;

    //AJAX를 하기위해 1 2 3으로 나누어 호출합니다.
    function connector(option) {
        //페이징이 겹치지 않게 하기 위한 page 변수 입니다.
        $('#sizeOption').prop('selectedIndex', 0).selectric('refresh');
        if (option === 1) {
            sizeControlVal = 1;
            //판매가 가격
            sellPopup(1);
        } else if (option === 2) {
            sizeControlVal = 2;
            //입찰가 가격
            buyPopup(1);
        } else {
            sizeControlVal = 3;
            orderPopup(1);
        }
    }

    function sizeControl() {
        if (sizeControlVal == 1) {
            sellPopup(1);
        } else if (sizeControlVal == 2) {
            buyPopup(1);
        } else {
            orderPopup(1);
        }
    }

    function sellPopup(page) {
        var value = ($('#sizeOption').val());
        // Restful API에 GET 방식 요청'
        $("#paginationSell").empty();
        $.post("${pageContext.request.contextPath}/sellPopupCurrent.do", {
            productNum: ${product.ID},
            page: page,
            size: value
        }, function (json) {
            var source = $("#sellPopup-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            $("#sellPopup").html(result); // 최종 결과물을 #list 요소에 추가한다.

            //페이징을 위한 구문입니다.
            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#paginationSell").append("<li style='cursor: pointer' onclick='sellPopup(" + json.page.prevPage + ")'><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#paginationSell").append("<li class=\"uk-active\" style='cursor: pointer'><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#paginationSell").append("<li style='cursor: pointer' onclick='sellPopup(" + i + ")'>" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#paginationSell").append("<li style='cursor: pointer' onclick='sellPopup(" + json.page.nextPage + ")'><span uk-pagination-next></span></a></li>")
            }
        });

        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        })
    }

    function buyPopup(page) {
        var value = ($('#sizeOption').val());
        $("#paginationBuy").empty();
        // Restful API에 Post 방식 요청
        $.post("${pageContext.request.contextPath}/buyPopupCurrent.do", {
            productNum: ${product.ID},
            page: page,
            size: value
        }, function (json) {
            var source = $("#buyPopup-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            $("#buyPopup").html(result); // 최종 결과물을 #list 요소에 추가한다.

            //페이징을 위한 구문입니다.
            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#paginationBuy").append("<li style='cursor: pointer' onclick='buyPopup(" + json.page.prevPage + ")'><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#paginationBuy").append("<li class=\"uk-active\" style='cursor: pointer'><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#paginationBuy").append("<li style='cursor: pointer' onclick='buyPopup(" + i + ")'>" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#paginationBuy").append("<li style='cursor: pointer' onclick='buyPopup(" + json.page.nextPage + ")'><span uk-pagination-next></span></a></li>")
            }
        });

        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        })
    }

    function orderPopup(page) {
        var value = ($('#sizeOption').val());
        $("#paginationOrder").empty();
        $.post("${pageContext.request.contextPath}/orderPopupCurrent.do", {
            productNum: ${product.ID},
            page: page,
            size: value
        }, function (json) {
            var source = $("#orderPopup-tmpl").html();   // 템플릿 코드 가져오기
            var template = Handlebars.compile(source);  // 템플릿 코드 컴파일
            var result = template(json); // 템플릿 컴파일 결과물에 json 전달
            $("#orderPopup").html(result); // 최종 결과물을 #list 요소에 추가한다.

            //페이징을 위한 구문입니다.
            //이전 그룹으로 이동 링크
            if (json.page.prevPage > 0) {
                $("#paginationOrder").append("<li style='cursor: pointer' onclick='orderPopup(" + json.page.prevPage + ")'><span uk-pagination-previous></span></a></li>")
            }

            //현재 그룹에 대한 페이지 번호 표시
            for (var i = json.page.startPage; i <= json.page.endPage; i++) {
                // 출력하려는 페이지 번호가 지금 머물고 있는 페이지라면 링크를 적용하지 않고 굵게 표시.
                if (i === json.page.nowPage) {
                    $("#paginationOrder").append("<li class=\"uk-active\" style='cursor: pointer'><span>" + i + "</span></li>")
                } else {
                    //주소를 현재 파라미터를 가져와 세팅하고 페이지넘버를 부여합니다.
                    $("#paginationOrder").append("<li style='cursor: pointer' onclick='orderPopup(" + i + ")'>" + i + "</a></li>")
                }

            }

            //다음 그룹으로 이동 링크
            if (json.page.nextPage > 0) {
                $("#paginationOrder").append("<li style='cursor: pointer' onclick='orderPopup(" + json.page.nextPage + ")'><span uk-pagination-next></span></a></li>")
            }

        });

        Handlebars.registerHelper('comma', function (text) {
            str = String(text);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        })
    }
</script>
</body>
</html>