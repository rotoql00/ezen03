banWordArr = [ "fuck", "쓰발", "씨발", "시발", "시펄", "admin" ];

function trim(obj) {
	var a = $("#userId").val().replace(/ /gi, "");
	var b = $("#userName").val().replace(/ /gi, "");
	var c = $("#userEmail").val().replace(/ /gi, "");
	$("#userId").val(a);
	$("#userName").val(b);
	$("#userEmail").val(c);
}

function reInputId() {
	$("input[id=checkIdBtn]").val("N");
};

function reInputPhonenum() {
	$("input[id=checkPhonenumBtn]").val("N");
};

function reInputEmail() {
	$("input[id=checkEmailBtn]").val("N");
};

// submit이벤트 중복 체크 여부
$("#signUp_Form").submit(function() {
	var checkIdBtn = $("#checkIdBtn").val();
	var checkPhonenumBtn = $("#checkPhonenumBtn").val();
	var checkEmailBtn = $("#checkEmailBtn").val();

	if (checkIdBtn == "N") {
		var Toast = Swal.fire({
			icon : 'error',
			title : '중복체크',
			text : '아이디 중복체크 확인을 해주세요.',
		})
		return false;

	} else if (checkPhonenumBtn == "N") {
		var Toast = Swal.fire({
			icon : 'error',
			title : '중복체크',
			text : '휴대폰번호 중복체크 확인을 해주세요.',
		})
		return false;
	} else if (checkEmailBtn == "N") {
		var Toast = Swal.fire({
			icon : 'error',
			title : '중복체크',
			text : '이메일 중복체크 확인을 해주세요.',
		})
		return false;
	} 
	return true;

});

// 아이디 중복 검사
$("#checkId").click(function() {
	$("input[id=checkIdBtn]").val("Y");

	if ($("#userId").val() != "") {
		var query = {
			userId : $("#userId").val()
		};
		var check = $("#userId").val();
		let j = 0;
		for (; j < banWordArr.length; j++) {
			if (check.indexOf(banWordArr[j]) > -1) {
				var Toast = Swal.fire({
					icon : 'error',
					title : '금칙어 입력',
					text : banWordArr[j] + '은(는) 금칙어 입니다.',
				})
				$("#userId").select();
				return false;
			}
		}
	} else {
		var Toast = Swal.fire({
			icon : 'error',
			title : '공백 불가',
			text : '아이디를 입력해주세요.',
		})
		return false;
	}
	;

	$.ajax({
		url : "./checkId", // ${pageContext.request.contextPath} or
		// <%=request.getContextPath()%> or .
		type : "post",
		data : query,
		success : function(data) {
			if (data == 1) {
				var Toast = Swal.fire({
					icon : 'error',
					title : '아이디 중복',
					text : '입력한 아이디가 중복됩니다.',
				})

			} else {
				var Toast = Swal.fire({
					icon : 'success',
					title : '사용가능',
					text : '입력한 아이디를 사용할수 있습니다.',
				})

			}
		} // success
	}); // ajax 끝
});

// 이메일 중복 검사
$("#checkEmail").click(function() {
	$("input[id=checkEmailBtn]").val("Y");

	if ($("#userEmail").val() != "") {
		var query = {
			userEmail : $("#userEmail").val()
		}
	} else {
		var Toast = Swal.fire({
			icon : 'error',
			title : '공백 불가',
			text : '이메일을 입력해주세요.',
		})
		return false;
	}
	;

	$.ajax({
		url : "./checkEmail",
		type : "post",
		data : query,
		success : function(data) {
			if (data == 1) {
				var Toast = Swal.fire({
					icon : 'error',
					title : '이메일 중복',
					text : '입력한 이메일이 중복됩니다.',
				})
			} else {
				var Toast = Swal.fire({
					icon : 'success',
					title : '사용가능',
					text : '입력한 이메일을 사용할수 있습니다.',
				})
			}
		} // success
	}); // ajax 끝
});

// 휴대폰번호 중복 검사
$("#checkPhonenum").click(function() {
	$("input[id=checkPhonenumBtn]").val("Y");

	if ($("#userPhonenum").val() != "") {
		var query = {
			userPhonenum : $("#userPhonenum").val()
		}
	} else {
		var Toast = Swal.fire({
			icon : 'error',
			title : '공백 불가',
			text : '핸드폰 번호을 입력해주세요.',
		})
		return false;
	}
	;

	$.ajax({
		url : "./checkPhonenum",
		type : "post",
		data : query,
		success : function(data) {
			if (data == 1) {
				var Toast = Swal.fire({
					icon : 'error',
					title : '휴대폰번호 중복',
					text : '입력한 휴대폰번호가 중복됩니다.',
				})
			} else {
				var Toast = Swal.fire({
					icon : 'success',
					title : '사용가능',
					text : '입력한 휴대폰번호를 사용할수 있습니다.',
				})
			}
		} // success
	}); // ajax 끝
});

// 이름 욕설 검사

$("#userName").blur(function() {

	var inputTxt = $("#userName");

	let i = 0;
	let j = 0;
	for (; i < inputTxt.length; i++) {
		let find = false;
		j = 0;
		for (; j < banWordArr.length; j++) {
			find = $(inputTxt[i]).val().indexOf(banWordArr[j]) > -1;
			if (find) {
				var Toast = Swal.fire({
					icon : 'error',
					title : '금칙어 입력',
					text : banWordArr[j] + '은(는) 금칙어 입니다.',
				})
				$(inputTxt[i]).val("");
				break;
			}
		}

		if (find) {
			break;
		}
	}

	return;

});// submit

// datePicker
$('#userBirthDate').datepicker({
	language : 'ko',
	multipleDatesSeparator : " - "
});

$.fn.datepicker.language['ko'] = {
	days : [ '일', '월', '화', '수', '목', '금', '토' ],
	daysShort : [ '일', '월', '화', '수', '목', '금', '토' ],
	daysMin : [ '일', '월', '화', '수', '목', '금', '토' ],
	months : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
			'11월', '12월' ],
	monthsShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월',
			'10월', '11월', '12월' ],
	today : '오늘',
	clear : 'Clear',
	dateFormat : 'yyyy/mm/dd',
	timeFormat : 'hh:ii aa',
	firstDay : 0
};

// 핸드폰 번호 하이픈
function inputHyphen(obj) {

	var number = obj.value.replace(/[^0-9]/g, "");
	var phone = "";

	if (number.length < 4) {
		return number;
	} else if (number.length < 7) {
		phone += number.substr(0, 3);
		phone += "-";
		phone += number.substr(3);
	} else if (number.length < 11) {
		phone += number.substr(0, 3);
		phone += "-";
		phone += number.substr(3, 3);
		phone += "-";
		phone += number.substr(6);
	} else {
		phone += number.substr(0, 3);
		phone += "-";
		phone += number.substr(3, 4);
		phone += "-";
		phone += number.substr(7);
	}
	obj.value = phone;
};