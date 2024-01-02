<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>자주 묻는 질문</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/faq.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
</head>
<body>
<!-- Header -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<!-- 게시판 선택 탭 카테고리 -->
<div id="faqcontainer">
    <div id="list">
        <!-- 탭 카테고리-->
        <div id="totaltab">
            <ul class="uk-tab">
                <li><a href="${pageContext.request.contextPath}/board">공지사항</a></li>
                <li class="uk-active"><a href="<%=request.getContextPath()%>/faq">FAQ</a></li>
                <li><a href="<%=request.getContextPath()%>/userGuide">이용안내</a></li>
            </ul>
        </div>
        <!-- FAQ게시판 목록 -->

        <!-- FAQ 게시판 형식
        <input type="checkbox" id="answer글번호">
          <label for="answer번호"><p>Q.</p>자주 묻는 질문<em></em></label>
          <div><p>답변</p></div> -->


        <div id="faqlist">

            <input type="checkbox" id="answer09">
            <label for="answer09"><p>Q.</p>Tie Shoe는 어떤 서비스인가요?<em></em></label>
            <div><p>Tie Shoe는 거래량이 적고 가치가 높은 아이템을 손쉽게 거래할 수 있는 온라인 플랫폼이며, 소비자가 구매한 제품을 Tie Shoe에서 리세일되는 거래방식입니다.
                착용 및 구성품 누락 된 제품은 거래품목에서 제외 되며, 철저한 검수방식을 거쳐 신속하고 정확한 제품을 거래 할 수 있도록 만든 체계적이고 안전한 거래플랫폼입니다.</p></div>

            <input type="checkbox" id="answer08">
            <label for="answer08"><p>Q.</p>회원탈퇴는 어떻게 하나요?<em></em></label>
            <div><p>당사 홈페이지 우측상단의 MY>개인정보관리에서 회원탈퇴가 가능합니다.
                다만, 다음의 경우에는 회원탈퇴가 제한 됩니다.
                - 진행중인 판매건이 있을 경우 탈퇴처리 불가하며 종료 후 탈퇴 가능
                - 진행중인 구매/입찰건이 있을 경우 탈퇴처리 불가하며 종료 후 탈퇴 가능
                - 예치금이 남아 있을 경우 또는 마이너스일 경우(예치금 정산 또는 마이너스 결제 후 처리가능)</p></div>

            <input type="checkbox" id="answer07">
            <label for="answer07"><p>Q.</p>거래성사 후 본사배송은 언제까지 해야 하나요?<em></em></label>
            <div><p>판매자는 거래성사 후 2영업일 이내, 송장번호를 입력하셔야 합니다.
                고의적인 배송지연 및 배송하지 않는 경우 패널티 부과규정에 따라 패널티 수수료가 부과될 수 있습니다.
                판매자의 경우 신속한 판매를 위하여 최적의 택배사를 자유롭게 선택하여 배송하여 주시기 바랍니다. (단, 배송비는 판매자 사전 부담)</p></div>

            <input type="checkbox" id="answer06">
            <label for="answer06"><p>Q.</p>입찰취소가 가능한가요?<em></em></label>
            <div><p>거래성사 되기전 까지의 취소는 가능합니다. 하지만 거래 성사 후 취소는 되지 않습니다.
                즉, 희망입찰가 등록 후 거래성사전까지는 입찰취소가 가능합니다.
                마이>구매/입찰>입찰가등록현황 메뉴에서 진행중 입찰의 수정버튼을 클릭 후 입찰기간에서 '입찰취소'를 설정하면됩니다.
                단, 거래성사 된 입찰건은 취소할 수 없습니다. 입찰시 상품모델을 신중하게 확인하시고 입찰정보인 가격, 기간등을 확인 후 등록하십시오.</p></div>

            <input type="checkbox" id="answer05">
            <label for="answer05"><p>Q.</p>입찰기간이란 무엇인가요?<em></em></label>
            <div><p>희망입찰가격 등록 시 입찰기간을 설정할 수 있습니다.
                입찰기간동안 판매자들에게 희망입찰가격이 노출되고, 입찰기간동안 입찰정보를 수정할 수 있습니다.
                입찰기간이 종료될 동안 거래가 성사되지 않으면 결제는 진행되지 않고, 입찰이 종료됩니다.
                입찰기간동안 거래가 성사되면 등록해둔 결제정보로 자동결제처리됩니다.</p></div>

            <input type="checkbox" id="answer04">
            <!-- FAQ게시판 제목-->
            <label for="answer04"><p>Q.</p> 검수는 무엇인가요?<em></em></label>
            <!-- FAQ 게시판 내용-->
            <div><p>당사에서는 각 카테고리(Nike, Adidas, Jordan, Other) 전문팀이 검수과정을 진행하고 있습니다.
                거래가 성사되면 판매자는 구매자가 아닌 Tieshoe로 판매상품을 배송합니다.
                판매자 상품이 도착하면 검수팀이 제품을 검수하여 제품의하자, 구성품의 누락, 사이즈 오류, 가품배송 등의 사유를 확인 후 검수통과한 상품만 구매자에게 배송해드립니다.
                검수과정을 통과하지 못한 판매자의 상품음 거래취소되어 판매자에게 반송되고, 구매자의 결제정보는 취소처리되어 환불됩니다.
                Tieshoe는 가치있는 상품의 안전한 거래를 보장해 드립니다.
            </p></div>

            <input type="checkbox" id="answer03">
            <!-- FAQ게시판 제목-->
            <label for="answer03"><p>Q.</p> 구매 후 사이즈 교환이 가능한가요?<em></em></label>
            <!-- FAQ 게시판 내용-->
            <div><p>거래상품 특성 상 검수 후 배송되는 상품의 거래가 끝난 후 교환 또는 반품은 없을 것입니다. 당사 플렛폼을 이용한 리세일을 권유드립니다.</p></div>

            <input type="checkbox" id="answer02">
            <!-- FAQ게시판 제목-->
            <label for="answer02"><p>Q.</p> 구매시 수수료가 있나요?<em></em></label>
            <!-- FAQ 게시판 내용-->
            <div><p>Tie Shoe 는 구매자에게 별도 수수료를 적용하지 않습니다. 판매자에게 수수료가 적용되며, 판매가 많을 수록 수수료는 낮아 집니다.</p></div>

            <input type="checkbox" id="answer01">
            <!-- FAQ게시판 제목-->
            <label for="answer01"><p>Q.</p> 반품은 가능한가요?<em></em></label>
            <!-- FAQ 게시판 내용-->
            <div><p>Tie Shoe 에서는거래상품 특성 상 검수 후 배송되는 상품의 거래가 끝난 후 반품은 허용되지 않습니다.
                단, 배송과정에서 제품의 하자가 생겼을 경우 또는 기타사항으로 제품의 하자가 분명하여 반품을 원하실 경우 당사 고객센터와 사전 연락을 하여 주십시오.</p></div>
        </div>
    </div>
    <!-- <div id="bordernum">
        <ul class="uk-pagination" uk-margin>
            <li><a href="#"><span uk-pagination-previous></span></a></li>
            <li><a href="#numlist1" class = "numlist active">1</a></li>
            <li><a href="#numlist2" class = "numlist">2</a></li>
            <li><a href="#numlist3" class = "numlist">3</a></li>
            <li><a href="#numlist4" class = "numlist">4</a></li>
            <li><a href="#numlist5" class = "numlist">5</a></li>
            <li><a href="#"><span uk-pagination-next></span></a></li>
        </ul>
    </div> -->
</div>
</div>
<!--Footer -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- UIkit JS -->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/board.js"></script>
</body>
</html>