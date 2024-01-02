<!--
@filename : dropUser.jsp
@description : 회원탈퇴를 위한 jsp입니다. 탈퇴 안내사항과 탈퇴 버튼이 있씁니다.
@author : 최성준
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원탈퇴 페이지</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <!-- dropUser.css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/dropUser.css">
    <!-- UIkit CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/index.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
</head>

<body>
<!-- HEADER -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<div id="header"></div>
<!-- 탈퇴 페이지 전체 -->
<div>
    <div class="infoPage">회원탈퇴 페이지</div>
    <!-- 탈퇴 본문 -->
    <div class="dropUserArea">
        <!-- 회원탈퇴 문구 -->
        <div class="infoText">회원탈퇴 안내</div>
        <!-- 바깥 area -->
        <div class="outArea">
            <!-- 본문 박스 -->
            <div class="inArea">
                <ul>
                    <li>진행중인 판매정보가 있는 경우 판매가 완료되야 탈퇴할 수 있습니다.</li>
                    <li>진행중인 구매정보가 있는 경우 구매가 완료되야 탈퇴할 수 있습니다.</li>
                    <li>예치금이 있는 경우 모두 환금하신 후 탈퇴할 수 있습니다.</li>
                    <li>회원 탈퇴시 회원님께서 보유하셨던 &nbsp;<strong>쿠폰, 회원정보</strong> 등은 모두
                        삭제 됩니다.
                    </li>
                    <li>회원 탈퇴 후 재가입 시에는 신규 회원 가입으로 처리 되며 탈퇴 전 사용한 아이디는 다시 사용할수
                        없고, 신규 아이디 생성을 해야합니다.
                    </li>
                    <li>탈퇴 시 현재 사용 휴대폰 번호를 사용할 수 없습니다. 단, 탈퇴 후 한달 뒤부터 사용가능 합니다.</li>
                </ul>
            </div>
        </div>
        <button id="dropUserBtn" class="uk-button uk-button-danger" href="#dropUserFinalBtn">회원탈퇴</button>
    </div>
</div>
<!-- FOOTER -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>

<!--Script-->
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    $("#dropUserBtn").click(function () {
        // 확인, 취소버튼에 따른 후속 처리 구현
        Swal.fire({
            title: '회원탈퇴',
            text: "정말 저희 TIE SHOE 회원을 탈퇴 하시겠습니까?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '탈퇴',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.value) {
                Swal.fire({
                    title: '회원정보가 삭제되었습니다.',
                    text: '그동안 저희 TIE SHOE를 이용해 주셔서 감사합니다.',
                    icon: 'success',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '<a href="/" style="color: white;">확인</a>'

                })
            }
        });
    });
</script>
</body>
</html>