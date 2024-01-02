<!--
FileName: myPage.html
Description: 개인정보관리.
Author: 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/myPage.css">
    <!-- 배송정보 수정을 위한 modal의 css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/myPage_change_addr.css">
    <!-- 비밀번호 수정을 위한 modal의 css - 임채린 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/myPage_reset_pw.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/nice-select.css">
    <link rel="stylesheet"
          href="https://cdn.rawgit.com/InventPartners/flex-responsive-burger-menu/master/css/nav.min.css"/>
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
    <style type="text/css">
.wrap-loading { /*화면 전체를 어둡게 합니다.*/
	position: fixed;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	background: rgba(0, 0, 0, 0.2); /*not in ie */
	filter: progid:DXImageTransform.Microsoft.Gradient(startColorstr='#20000000',
		endColorstr='#20000000'); /* ie */
}

.wrap-loading div { /*로딩 이미지*/
	position: fixed;
	top: 50%;
	left: 50%;
	margin-left: -210px;
	margin-top: -210px;
}

.display-none { /*감추기*/
	display: none;
}

.infoLoading {
	color: white;
	font-size: 50px;
	font-weight: 200;
}
</style>
</head>
<!--
사용되는 id:
#change_user_addr : 배송정보 수정 modal에 사용되는 form의 id (최성준)
#change_Name : 배송정보 수정 modal에 사용되는 이름속성 (최성준)
#change_PhoneNum : 배송정보 수정 modal에 사용되는 연락처속성 (최성준)
#zip_code : 배송정보 수정 modal에 사용되는 우편번호 속성 (최성준)
#addr_1 : 배송정보 수정 modal에 사용되는 도로명주소 속성 (최성준)
#addr_2 : 배송정보 수정 modal에 사용되는 상세주소 속성 (최성준)
 -->
<body>
<!-- Header -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

	<!-- 로그인 상태 확인 -->
	<c:if test="${empty sessionScope.loginID}">
		<script>
			alert("MyPage를 사용하기 위해서 로그인 해주세요.");
			 location.href = "<%=request.getContextPath()%>/login";
		</script>
	</c:if>
	
	<!--  로딩 이미지 -->
	<div class="wrap-loading display-none">
		<div>
			<img src="<%=request.getContextPath()%>/assets/img/loadingJordan.gif" />
			<h1 class="infoLoading">인증 메일 전송중...</h1>
		</div>
	</div>

<!-- Container -->
<div class="container">
    <div class="myPage_title">MY</div>

    <div class="myPage_subnav">
        <ul class="uk-subnav uk-subnav-divider mypage_center">
            <li><a href="<%=request.getContextPath()%>/myPage/buy">구매/입찰</a></li>
            <li><a href="<%=request.getContextPath()%>/myPage/sell">판매</a></li>
            <li class="uk-active"><a href="#">개인정보관리</a></li>
        </ul>
    </div>


    <div class="myPage_info_container">
        <div class="myPage_info_title">회원 &amp; 배송정보</div>
        <hr class="hr_bold">
        <!-- 이메일 주소 (아이디)-->
        <div class="myPage_email">
            <div class="myPage_subtitle">이메일 (ID)</div>
            <div class="myPage_subtext" id="userEmail">이메일 : ${sessionScope.UserEmail}	(ID : ${sessionScope.UserId})</div>
            <div class="myPage_button">
            
            <form method="post" id="sendEmailAuth" style="display: inline;">
				<!-- action="${pageContext.request.contextPath}/Welcome?ID=${output.ID}" -->
					<input type="hidden" id="MailOverlap" value="0"> 
					<input type="hidden" id="ID" value="${sessionScope.loginID}"> 
					<input type="hidden" id="userId" value="${sessionScope.UserId}"> 
					<input type="hidden" id="userEmail2" value="${sessionScope.UserEmail}">
					<input type="hidden" id="userName" value="${sessionScope.UserName}">
					<button id="reSendEmail" class="uk-button uk-button-primary">인증 메일 전송
               		 </button>
			</form>
			    
                <button class="uk-button uk-button-primary" href="#myPage_password"
                        uk-toggle="">비밀번호 수정
                </button>

                <!-- 비밀번호 수정 Modal-->
                <!-- 임채린님 시작-->
                
                <div id="myPage_password" uk-modal="" class="uk-modal">
                    <div class="uk-modal-dialog">
                        <button class="uk-modal-close-default uk-icon uk-close"
                                type="button" uk-close="">
                            <svg width="14" height="14" viewBox="0 0 14 14"
                                 xmlns="http://www.w3.org/2000/svg" data-svg="close-icon">
                                <line fill="none" stroke="#000"
                                      stroke-width="1.1" x1="1" y1="1" x2="13" y2="13"></line>
                                <line fill="none" stroke="#000"
                                      stroke-width="1.1" x1="13" y1="1" x2="1" y2="13"></line>
                            </svg>
                        </button>
                        <div class="uk-modal-header">
                            <h2 class="uk-modal-title">비밀번호 변경</h2>
                        </div>
                        <!-- Body of the Modal-->
                        <div class="uk-modal-body">
                            <form class="uk-margin-auto uk-width-large" id="reset_pw_form" action="${pageContext.request.contextPath}/myPage/edit_ok" return false;>
                                <input type="hidden" name="ID" value="${sessionScope.loginID}">
                                <input type="hidden" name="userId" value="${sessionScope.UserId}">
                                <input type="hidden" name="userName" value="${sessionScope.UserName}">
                                <input type="hidden" name="userPhonenum" value="${output.userPhonenum}">
                                <input type="hidden" name="userBirthDate" value="${output.userBirthDate}">
                                <input type="hidden" name="userEmail" value="${sessionScope.UserEmail}">
                                <input type="hidden" name="userZipcode" value="${output.userZipcode}">
                                <input type="hidden" name="userAddress1" value="${output.userAddress1}">
                                <input type="hidden" name="userAddress2" value="${output.userAddress2}">
                                <!-- 새 비밀번호 입력 -->
                                <label class="reset_new_pw">새로운 비밀번호</label>
                                <div class="uk-margin">
                                    <div class="uk-inline">
                                        <span class="uk-form-icon" uk-icon="icon:lock"></span> <input
                                            class="uk-input uk-form-width-large" id="new_PW" name="userPw"
                                            type="password" placeholder="비밀번호는 6~20글자입니다">
                                    </div>
                                </div>
                                <label class="uk-alert-danger error" for="new_PW" generated="true" uk-alert></label>
                                <!-- 비밀번호 확인 -->
                                <label class="reset_new_pw">비밀번호 확인</label>
                                <div class="uk-margin">
                                    <div class="uk-inline">
                                        <span class="uk-form-icon" uk-icon="icon:lock"></span> <input
                                            class="uk-input uk-form-width-large" id="new_PW_Check" name="newPWCheck"
                                            type="password"
                                            placeholder="다시 한번 입력해주세요">
                                    </div>
                                </div>
                                <label class="uk-alert-danger error" for="new_PW_Check" generated="true"
                                       uk-alert></label>

                        <div class="uk-modal-footer uk-text-right">
                            <button class="uk-button uk-button-default uk-modal-close"
                                    type="button">취소
                            </button>
                            <button class="uk-button uk-button-primary" type="submit">변경</button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- 임채린님 끝-->

        <hr class="hr_fade">

        <!-- 이름 -->
        <div class="myPage_name">
            <div class="myPage_subtitle">이름</div>
            <!-- 이름과 추천인 span 처리-->
            <div class="myPage_subtext">
                <span id="userName">${output.userName}</span>&nbsp;(내 추천인 코드:
                <span id="userRecommendCode">${output.userEmail}</span>)
            </div>
            <div class="myPage_button">
                <button class="uk-button uk-button-primary" onclick="copyText()">링크복사</button>
            </div>
        </div>
        <hr class="hr_fade">
        <!-- 휴대폰 번호 -->
        <div class="mypage_phonenum">
            <div class="myPage_subtitle">휴대폰 번호</div>
            <div class="myPage_subtext" id="userPhoneNum">${output.userPhonenum}</div>
            <div class="myPage_button">
                <button class="uk-button uk-button-primary"
                        href="#myPage_edit_phonenum" uk-toggle="">편집
                </button>
                <!-- 휴대폰 번호 수정 Modal-->
                <div id="myPage_edit_phonenum" uk-modal="" class="uk-modal">
                    <div class="uk-modal-dialog">
                        <button class="uk-modal-close-default uk-icon uk-close"
                                type="button" uk-close=""></button>
                        <div class="uk-modal-header">
                            <h2 class="uk-modal-title">휴대폰 번호 수정</h2>
                        </div>
                        <!-- Body of the Modal-->
                        <div class="uk-modal-body">
                            <div class="phone_number_info">변경할 휴대폰 번호</div>
                            <form class="uk-margin-auto uk-width-large" id="reset_PH_form" action="${pageContext.request.contextPath}/myPage/edit_ok" return false;>
                                <input type="hidden" name="ID" value="${sessionScope.loginID}">
                                <input type="hidden" name="userId" value="${sessionScope.UserId}">
                                <input type="hidden" name="userName" value="${sessionScope.UserName}">
                                <input type="hidden" name="userPw" value="${output.userPw}">
                                <input type="hidden" name="userBirthDate" value="${output.userBirthDate}">
                                <input type="hidden" name="userEmail" value="${sessionScope.UserEmail}">
                                <input type="hidden" name="userZipcode" value="${output.userZipcode}">
                                <input type="hidden" name="userAddress1" value="${output.userAddress1}">
                                <input type="hidden" name="userAddress2" value="${output.userAddress2}">

                               <!-- 핸드폰번호 입력 -->
								<div class="uk-margin uk-position-relative">
									<div class="uk-inline Phonenum">
										<span class="uk-form-icon" uk-icon="icon:receiver"></span> <input
										class="uk-input Phonenum" id="userPhonenum" name="userPhonenum"
										type="text" maxlength="13" placeholder="핸드폰번호 "
										onchange="reInputPhonenum();" onkeyup="inputHyphen(this);">
									</div>
									<input type="hidden" id="checkPhonenumBtn" value="N">
									<button class="uk-button uk-button-secondary uk-position-right "
									type="button" id="checkPhonenum">중복확인</button>
								</div>
								<label class="uk-alert-danger error" for="userPhonenum"
						generated="true" uk-alert></label>   
						<br/>                    

                       			 <div class="uk-modal-footer uk-text-right">
                            		<button class="uk-button uk-button-default uk-modal-close"
                                 	   type="button">취소
                            		</button>
                            		<button class="uk-button uk-button-primary" type="submit">변경</button>
                        		</div>
                        	</form>
                        	
                        </div><!-- uk-modal-body -->
                    </div> <!-- uk-modal-dialog -->
                </div> <!-- myPage_edit_phonenum -->
            </div> <!-- myPage_button -->
        </div> <!-- mypage_phonenum -->
        <hr class="hr_fade">
        <!-- 배송 정보 -->
        <div class="myPage_shipping">
            <div class="myPage_subtitle">배송 정보</div>
            <div class="myPage_subtext" id="userAddress">(${output.userZipcode}) &nbsp ${output.userAddress1} &nbsp ${output.userAddress2}</div>
            <div class="myPage_button">
                <button class="uk-button uk-button-primary"
                        href="#myPage_edit_shipping" uk-toggle="">편집
                </button>
                <!-- 배송정보 수정 Modal-->
                <!--
                        작성자: 최성준
                    내용: 배송정보 수정 modal.
                    추가 설명: myPage_change_addr.css, myPage_change_addr.js를 사용하고 있습니다.
                -->
                <div id="myPage_edit_shipping" uk-modal>
                    <div class="uk-modal-dialog">
                        <button class="uk-modal-close-default" type="button" uk-close></button>
                        <div class="uk-modal-header">
                            <h2 class="uk-modal-title">배송 정보 변경</h2>
                        </div>
                        <!-- Body of the Modal-->
                        <div class="uk-modal-body">
                           <form class="uk-margin-auto uk-width-large" id="reset_addr_form" action="${pageContext.request.contextPath}/myPage/edit_ok" return false;>
 								<input type="hidden" name="ID" value="${sessionScope.loginID}">
                                <input type="hidden" name="userId" value="${sessionScope.UserId}">
                                <input type="hidden" name="userName" value="${sessionScope.UserName}">
                                <input type="hidden" name="userPw" value="${output.userPw}">
                                <input type="hidden" name="userPhonenum" value="${output.userPhonenum}">
                                <input type="hidden" name="userBirthDate" value="${output.userBirthDate}">
                                <input type="hidden" name="userEmail" value="${sessionScope.UserEmail}">
                                
                                <!-- 주소 -->
                                <label class="change_addr_info" for="change_user_addr">주소 *</label>
                                <div class="changeAddrArea">
                                    <br>
                                    <div class="uk-margin uk-position-relative">
                                        <div class="uk-inline zip_code">
                                            <span class="uk-form-icon" uk-icon="icon:location"></span> <input
                                                class="uk-input zip_code" type="text" id="zip_code"
                                                name="userZipcode" style="cursor: not-allowed;"
                                                placeholder="우편번호 Zone Code" readonly><br>
                                        </div>
                                        <button class="uk-button uk-button-secondary uk-position-right"
                                                type="button" onclick="openAddSearch()">주소찾기
                                        </button>
                                    </div>
                                    <label class="uk-alert-danger error" for="zip_code"
                                           generated="true" uk-alert></label>
                                    <div class="uk-margin">
                                        <div class="uk-inline">
                                            <span class="uk-form-icon" uk-icon="icon:location"></span> <input
                                                class="uk-input addr_1 change_user_info" type="text"
                                                id="addr_1" name="userAddress1" style="cursor: not-allowed;"
                                                placeholder="도로명 주소 Address_1" readonly>
                                        </div>
                                    </div>
                                    <label class="uk-alert-danger error" for="addr_1"
                                           generated="true" uk-alert></label>
                                    <div class="uk-margin">
                                        <div class="uk-inline">
                                            <span class="uk-form-icon" uk-icon="icon:pencil"></span> <input
                                                class="uk-input change_user_info" type="text" id="addr_2"
                                                name="userAddress2" placeholder="상세주소 Address_2">
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
                                    <button class="uk-button uk-button-secondary" type="submit">변경</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- 최성준님 끝-->
                </div>
            </div><!-- 배송정보 수정 modal 끝! -->
            <!-- End of  개인정보관리 내용-->
            <hr class="hr_fade">
        </div>
        <!-- 판매 & 구매정보-->
        <div class="myPage_bank_container">
            <div class="myPage_info_title">판매 &amp; 구매정보</div>
            <hr class="hr_bold">
            <div class="myPage_bank">
                <div class="myPage_subtitle">출금계좌</div>
                <div class="myPage_subtext"/>
                 	<c:choose>
						<c:when test='${output.userAccountBank eq \'null\'}' > 
							거래를 하시려면 출금 계좌 정보를 등록해주세요.																	
						</c:when>						
						 <c:when test='${output.userAccountBank ne \'null\'}'>
						 	${output.userAccountBank}&nbsp&nbsp&nbsp&nbsp${output.userAccountNum}
						</c:when> 
					</c:choose>					
                 </div>
                <div class="myPage_button">
                    <button class="uk-button uk-button-primary"
                            href="#myPage_edit_account" uk-toggle="">편집
                    </button>

                    <!-- 배송정보 수정 Modal-->
                    <div id="myPage_edit_account" uk-modal="" class="uk-modal">
                        <div class="uk-modal-dialog">
                            <button class="uk-modal-close-default uk-icon uk-close"
                                    type="button" uk-close="">
                                <svg width="14" height="14" viewBox="0 0 14 14"
                                     xmlns="http://www.w3.org/2000/svg" data-svg="close-icon">
                                    <line fill="none" stroke="#000"
                                          stroke-width="1.1" x1="1" y1="1" x2="13" y2="13"></line>
                                    <line fill="none" stroke="#000"
                                          stroke-width="1.1" x1="13" y1="1" x2="1" y2="13"></line>
                                </svg>
                            </button>
                            <div class="uk-modal-header">
                                <h2 class="uk-modal-title">계좌번호 변경</h2>
                            </div>
                            <!-- Body of the Modal-->
                            <div class="uk-modal-body">
                             <form class="uk-margin-auto uk-width-large" id="reset_Account_form" action="${pageContext.request.contextPath}/myPage/edit_bank" return false;>
                                <input type="hidden" name="ID" value="${sessionScope.loginID}">

                               <!-- 계좌번호 입력 userAccountBank userAccountNum -->
								<div class="uk-margin uk-position-relative">
									<!-- 은행명 -->
									<label class="userBank">은행선택 *</label>
									<br/>															
										<!-- <input class="userBankInput" id="userAccountBank" name="userAccountBank"
										type="text" maxlength="8" placeholder="은행명 "> -->
										<select class="userBankselect" id="userAccountBank" name="userAccountBank">
											<option value="">------은행명------</option>
											<option value="국민은행">국민은행</option>
											<option value="기업은행">기업은행</option>
											<option value="카카오뱅크">카카오뱅크</option>
											<option value="우리은행">우리은행</option>
											<option value="신한은행">신한은행</option>
											<option value="농협">농협</option>
										</select>
									
									<label class="uk-alert-danger error" for="userAccountBank"
									generated="true" uk-alert></label>   
									<br/>
									
									<!-- 예금주 -->
									<label class="userBank">예금주 *</label>
									<br/>
									<input class="userBankInput" name="userName" value="${sessionScope.UserName}" type="text"  style="cursor: not-allowed;  background: #f0f0f0;" readonly>
									<br/>
									
									<!-- 계좌번호 -->
									<label class="userBank">계좌번호 *</label>
									<br/>																		
										<input class="userBankInput" id="userAccountNum" name="userAccountNum"
										type="text" maxlength="20" placeholder="계좌번호 ">
									<br/>									
								</div>
								
								<label class="uk-alert-danger error" for="userAccountNum"
									generated="true" uk-alert></label>   
								<br/>                    

                       			 <div class="uk-modal-footer uk-text-right">
                            		<button class="uk-button uk-button-default uk-modal-close"
                                 	   type="button">취소
                            		</button>
                            		<button class="uk-button uk-button-primary" type="submit">변경</button>
                        		</div>
                        	</form>
                            </div><!-- modal-body -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr class="hr_fade">
        <!-- 알림 설정-->
        <div class="myPage_noti_container">
            <div class="myPage_info_title">알림설정</div>
            <hr class="hr_bold">
            <div class="myPage_noti">
                <div class="myPage_subtitle mypage_expain_info">정보수신동의</div>
                <div class="myPage_subtext mypage_expain_info">
                    <input type="checkbox">이메일 수신동의
                </div>
                <div class="myPage_subtext mypage_expain">마케팅 수신에 동의 합니다. 각종
                    마케팅 정보를 받아 보실수 있습니다.
                </div>
            </div>
        </div>
        <hr class="hr_fade">

        <div class="myPage_submit">
            <div class="myPage_exit">
                <!-- 최성준 담당 시작 -->
                <input type="hidden" id="userdropTF" value="Y">
                <button class="uk-button uk-button-danger red" id="dropUser">회원탈퇴</a>
                </button>
                <!-- 최성준 담당 시작 -->
                <!-- <button class="uk-button uk-button-primary">변경완료</button> -->
            </div>
        </div>
        <!-- End of Container -->
    </div>
</div>
</div>
<!-- Footer -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<!-- 스크립트 구간-->
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- UIkit -->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/js/uikit-icons.min.js"></script>
<!-- daum 주소찾기 CDN -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- validate plugin CDN -->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
<!-- Sweet Alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- checkbox2 -->
<script src="https://cdn.rawgit.com/InventPartners/flex-responsive-burger-menu/master/js/nav.min.js"></script>
<!-- plain js -->
<script src="<%=request.getContextPath()%>/assets/js/validate.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/myPage.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.nice-select.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/join.js"></script>
<script src="<%=request.getContextPath()%>/assets/AJAX_helper/ajax_helper.js"></script>
<script type="text/javascript">
$("#dropUser").click(function(){
	//$("input[id=userdropTF]").val("Y");
	
	var drop = {
			ID : ${sessionScope.loginID},
			userdropTF : $("#userdropTF").val()
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/dropUser", // RestAPIController line : 1418
		type : "post",
		data : drop,
		dataType:"json",
		success : function(data) {
			console.log(data)
			console.log(data.findBuy)
			//if (data.findBuy > 0 && data.findSell > 0) {alert(탈퇴 불가)} else {alert("회원 탈퇴 완료");}
			if (data.findBuy > 0 || data.findSell > 0) {
				var Toast = Swal.fire({
                    icon: 'error',
                    title: '회원 탈퇴 불가',
                    text: '회원님께서는 현재 거래중인 상품이 있어 탈퇴가 불가능 합니다.',
                })
                return false;
			}  else {
				 var Toast = Swal.fire({
                     icon: 'success',
                     title: '회원 탈퇴 완료',
                     text: '그동안 TIE SHOE를 애용해주셔서 감사합니다. 24시간동안 불가능.',
                 })
                 window.location.href = "${pageContext.request.contextPath}";
			} 						
		}// end success 
	});// end ajax	
});// end dropUser

$("#reSendEmail").click(function(){

	var ID = $("#ID").val()
	var userId = $("#userId").val()
	var userEmail = $("#userEmail2").val()
	var userName = $("#userName").val()
	
	var query = {
			"ID" : ID,
			"userId" : userId, 
			"userEmail" : userEmail,
			"userName" : userName
		};
	
	$.ajax({
		url : "${pageContext.request.contextPath}/emailAuth",
		type : "POST",
		data : query,
		success : function(data) {
			var Toast = Swal.fire({
				icon : 'success',
				title : '이메일 전송완료',
				text : '회원정보 이메일로 인증메일을 발송했습니다.',
			})// end fire
			$("#MailOverlap").val("1");
		},
		beforeSend : function() {
			//(이미지 보여주기 처리)
			$('.wrap-loading').removeClass('display-none');
		},

		complete : function() {
			//(이미지 감추기 처리)
			$('.wrap-loading').addClass('display-none');
		}
	})// end ajax
})// end reSendEmail
</script>
</body>
</html>