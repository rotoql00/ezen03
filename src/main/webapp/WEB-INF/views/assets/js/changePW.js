// 비밀번호 변경
$("#CPW").submit(function() {
	var CPW = {
		// ID : ${loginID}, //parseInt(${loginID}), //${loginID},
		// //${"#ID"}.val(),${sessionScope.loginID}
		userPw : $("#User_PW").val()
	}
	$.ajax({
		url : "./changPW.do",
		type : "POST",
		data : CPW,
		success : function(data) {
			alert("비밀번호 변경 완료. 재로그인 해주세요.");
			window.location.href = "./";
		}
	})// ajax
});