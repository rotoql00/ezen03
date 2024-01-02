/*
 * FunctionName: jQuery validate
 * Description: join.html에서 입력값의 유효성 정규식 검사를 위한 기능입니다.
 * Author: 최성준
 * */

/* validate plugin을 사용 하고 있습니다.
 * 사용법: body Tag 하단에 아래와 같은 CDN을 추가해주세요.
 * 
<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
		
	이후 form안에 원하는 내용을 작성해주세요.
	원하는 form의 id값으로 validate를 설정해준뒤 확인이 필요한 input box의 name으로 rules과 message를 추가하여 사용하면 됩니다.
 */

// 회원가입 정규식 검사
$(function() {
	// validate 이름 정규식 추가
	$.validator.addMethod("kor", function(value, element) {
		return this.optional(element) || /^[가-힣]*$/i.test(value);
	});

	// validate 전화번호 정규식 추가
	$.validator.addMethod("phone", function(value, element) {
		return this.optional(element)
				|| /^01(?:0|1|[6-9])(?:-)(?:\d{3}|\d{4})(?:-)\d{4}$/i.test(value);
		///(/^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3"
		///^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/i.test(value); 
		// 일반 전화 졍규식 ||/^\d{2,3}\d{3,4}\d{4}$/i.test(value);
	});

});

// 회원가입 유효성 검사 validate plugin 사용
$(function() {
	$("#signUp_Form").validate({
		rules : {
			// name 속성으로 사용합니다.
			userId : {
				required : true,
				alphanumeric : true,
				minlength : 3,
				maxlength : 20
			},
			userPw : {
				required : true,
				minlength : 6,
				maxlength : 20
			},
			userPwCheck : {
				required : true,
				equalTo : "#userPw"
			},
			userName : {
				required : true,
				kor : true,
				minlength : 2,
				maxlength : 15

			},
			userPhonenum : {
				required : true,
				phone : true
			},
			userBirthDate : {
				required : true,
				date : true
			},
			userEmail : {
				required : true,
				email : true
			},
			userZipcode : {
				required : true

			},
			userAddress1 : {
				required : true

			},
			userAddress2 : {
				required : true

			}

		},
		messages : {
			userId : {
				required : "아이디를 입력하세요.",
				alphanumeric : "아이디는 공백없이 영어,숫자로만 설정해주세요.",
				minlength : "아이디는 3~20글자로 설정해주세요.",
				maxlength : "아이디는 3~20글자로 설정해주세요."
			},
			userPw : {
				required : "비밀번호를 입력해주세요.",
				minlength : "비밀번호는 6~20글자로 설정해주세요.",
				maxlength : "비밀번호는 6~20글자로 설정해주세요."
			},
			userPwCheck : {
				required : "재확인할 비밀번호를 입력해주세요.",
				equalTo : "비밀번호가 일치 하지 않습니다. 다시 입력해주세요."
			},
			userName : {
				required : "이름을 입력해주세요.",
				kor : "이름은 공백없이 한글로 입력해주세요.",
				minlength : "이름은 2~15글자로 설정해주세요.",
				maxlength : "이름은 2~15글자로 설정해주세요."
			},
			userPhonenum : {
				required : "핸드폰 번호를 입력해주세요.",
				phone : "핸드폰 번호의 형식이 맞지 않습니다."
			},
			userBirthDate : {
				required : "생일을 입력해주세요....",
				date : "날짜가 맞지 않습니다." // 기준이 정해 미만은 가입 못하도록
			},
			userEmail : {
				required : "이메일을 입력해주세요.",
				email : "이메일 형식이 맞지 않습니다."
			},

			userZipcode : {
				required : "주소찾기 버튼을 이용하여 주소를 입력해주세요."

			},
			userAddress1 : {
				required : "주소찾기 버튼을 이용하여 주소를 입력해주세요."

			},
			userAddress2 : {
				required : "상세주소를 입력해주세요."

			}

		}
	}); // end validate()

});


/*
 * FunctionName: myPage_change_addr
 * Description: myPage.jsp에서 배송정보를 수정하는 modal에 적용되는 입력값의 유효성 정규식 검사를 위한 기능입니다.
 * Author: 최성준
 * */

$(function() {
	$("#reset_addr_form").validate({
		rules : {
			userZipcode : {
				required : true
			},
			userAddress1 : {
				required : true
			},
			userAddress2 : {
				required : true
			}
		},
		messages : {
			userZipcode : {
				required : "주소찾기 버튼을 이용하여 주소를 입력해주세요."

			},
			userAddress1 : {
				required : "주소찾기 버튼을 이용하여 주소를 입력해주세요."

			},
			userAddress2 : {
				required : "상세주소를 입력해주세요."

			}

		}
	}); // end validate()

});

/*
 * myPage.jsp의 비밀번호 수정 modal에서 reset_pw_form에 적용되는 입력값의 유효성 검사를 위한 기능입니다. - 임채린
 */

$(function() {
    $("#reset_pw_form").validate({
        rules: {
        	userPw: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            newPWCheck: {
                required: true,
                equalTo: "#new_PW"

            }
        },

        messages: {
        	userPw: {
                required: "비밀번호를 입력해주세요.",
                minlength: "비밀번호는 6~20글자로 설정해주세요.",
                maxlength: "비밀번호는 6~20글자로 설정해주세요."
            },
            newPWCheck: {
                required: "재확인할 비밀번호를 입력해주세요.",
                equalTo: "비밀번호가 일치 하지 않습니다. 다시 입력해주세요."
            }

        }
    }); // end validate()

});

/*
 * myPage.jsp의 핸드폰번호 수정 modal에서 reset_PH_form에 적용되는 입력값의 유효성 검사를 위한 기능입니다.
 */
$(function() {
    $("#reset_PH_form").validate({
        rules: {
        	userPhonenum: {
				required : true,
				phone : true

            }
        },

        messages: {
        	userPhonenum: {
				required : "핸드폰 번호를 입력해주세요.",
				phone : "핸드폰 번호의 형식이 맞지 않습니다."
            }

        }
    }); // end validate()

});

$(function() {
	$("#changePWForm").validate({
		rules : {
			// name 속성으로 사용합니다.					
			userPw : {
				required : true,
				minlength : 6,
				maxlength : 20
			},
			userPwCheck : {
				required : true,
				equalTo : "#User_PW"
			}

		},
		messages : {					
			userPw : {
				required : "비밀번호를 입력해주세요.",
				minlength : "비밀번호는 6~20글자로 설정해주세요.",
				maxlength : "비밀번호는 6~20글자로 설정해주세요."
			},
			userPwCheck : {
				required : "재확인할 비밀번호를 입력해주세요.",
				equalTo : "비밀번호가 일치 하지 않습니다. 다시 입력해주세요."
			}
		}
	}); // end validate()

});