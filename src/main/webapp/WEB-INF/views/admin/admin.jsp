<!--
FileName: admin.jsp
Description: 관리자 페이지의 index입니다.
Author: 임석현
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/admin/admin.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/admin.png">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/admin.png">
    <link rel="stylesheet"
          href="https://naver.github.io/billboard.js/release/latest/dist/billboard.min.css">
    <link rel="stylesheet"
          href="https://naver.github.io/billboard.js/release/latest/dist/theme/insight.min.css">
</head>

<body>
<%@ include file="inc/admin_header.jsp" %>

<div class="content-padder content-background">
    <div class="uk-section-xsmall uk-section-default header">
        <div class="uk-container uk-container-large">
            <h1 class="bold"><span class="ion-speedometer"></span> Admin Home </h1>
        </div>
    </div>
    <div class="uk-section-small">
        <div class="uk-container uk-container-large">
            <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-4@m uk-child-width-1-4@xl">
                <div>
                    <div class="uk-card uk-card-default uk-card-body">
                        <span class="statistics-text">총 회원 수</span><br/>
                        <span class="statistics-number"><span>${userCount}</span>명</span>
                    </div>
                </div>
                <div>
                    <div class="uk-card uk-card-default uk-card-body">
                        <span class="statistics-text">총 상품 수</span><br/>
                        <span class="statistics-number"><span>${productCount}</span>개</span>
                    </div>
                </div>
                <div>
                    <div class="uk-card uk-card-default uk-card-body">
                        <span class="statistics-text">오늘 거래 건수</span><br/>
                        <span class="statistics-number"><span>${orderTodayCount}</span>건</span>
                    </div>
                </div>
                <div>
                    <div class="uk-card uk-card-default uk-card-body">
                        <span class="statistics-text">총 거래 건수</span><br/>
                        <span class="statistics-number"><span>${orderCount}</span>건</span>
                    </div>
                </div>
                <div>
                    <div class="uk-card uk-card-default uk-card-body">
                        <span class="statistics-text">하루 수익</span><br/>
                        <span class="statistics-number"><span><fmt:formatNumber value="${orderTodayIncome}"
                                                                                pattern="#,###"/></span>원</span>
                    </div>
                </div>
                <div>
                    <div class="uk-card uk-card-default uk-card-body">
                        <span class="statistics-text">총 수익</span><br/>
                        <span class="statistics-number"><span><fmt:formatNumber value="${orderTotalIncome}"
                                                                                pattern="#,###"/></span>원</span>
                    </div>
                </div>
            </div>
            <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-1@l">
                <div>
                    <div class="uk-card uk-card-default">
                        <div class="uk-card-header admin_home_header">
                            일별 매출 (최근거래를 기준으로 3주간의 매출)
                            <!-- 현재 total 조회수-->
                        </div>
                        <div class="uk-card-body">
                            <!-- d3 들어갈 자리-->
                            <div id="dailyIncomeGraph"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div uk-grid class="uk-child-width-1-1@s uk-child-width-1-2@l">
                <div>
                    <div class="uk-card uk-card-default">
                        <div class="uk-card-header admin_home_header">
                            사용자 상품 관심도 (상위 5개)
                            <!-- 현재 total 조회수-->
                        </div>
                        <div class="uk-card-body">

                            <div id="interestChart"></div>
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
<!-- Script-->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<!-- Plain Script-->
<script src="<%=request.getContextPath()%>/admin/admin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<script src="https://d3js.org/d3.v5.min.js"></script>
<script src="https://naver.github.io/billboard.js/release/latest/dist/billboard.min.js"></script>

<script>
    $.post("${pageContext.request.contextPath}/dailyIncomeGraph.do", {}, function (json) {
        var xtickArray = [];
        var totalArray = [];
        for (var i = 0; i < json.info.length; i++) {
            var data = json.info[i];
            var xtick = data.reg_date;
            var sell = data.sell_ID;
            var buy = data.buy_ID;
            xtickArray.push(xtick);
            totalArray.push(sell + buy);
        }

        var chart2 = bb.generate({
            data: {
                json: {
                    x: xtickArray,
                    매출: totalArray
                },
                x: "x",
                type: "area",
                xFormat: "%Y-%m-%d"
            },
            size: {
                height: 350,
            },
            zoom: {
                enabled: {
                    type: "drag"
                }
            },
            axis: {
                x: {
                    tick: {
                        fit: true,
                        multiline: false,
                        culling: false,
                        rotate: 90,
                        format: "%m-%d"
                    },
                    type: "timeseries"
                }
            },
            bindto: "#dailyIncomeGraph"
        })
    });

    $.post("${pageContext.request.contextPath}/interestGraph.do", {}, function (json) {
        var array = [];
        var totalCount = 0;

        for (var i = 0; i < json.info.length; i++) {

            var data = json.info[i];
            var name = data.productName;
            var percentage = data.product_ID;
            array.push(name);
            array.push(percentage);
        }

        var first = array.splice(0, 2);
        var second = array.splice(0, 2);
        var third = array.splice(0, 2);
        var fourth = array.splice(0, 2);
        var fifth = array.splice(0, 2);

        var chart = bb.generate({
            data: {
                columns: [
                    first,
                    second,
                    third,
                    fourth,
                    fifth
                ],
                type: "pie"
            },
            pie: {
                padding: 3
            },
            bindto: "#interestChart"
        });
    });

</script>
</body>

</html>