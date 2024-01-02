/*
* FileName: login.js
* Description: login.html에 대한 JS입니다.
* Author: 임채린
* */


    $(function() {
    	/*로그인 유효성 검사 시작*/
    	/** 유효성 검사 플러그인이 ajaxForm보다 먼저 명시되어야 한다. */
        $("#login_form").validate({
            rules: {
                userId: {
                    required: true,
                    alphanumeric: true,
                    minlength: 3,
                    maxlength: 20
                },
                userPw: {
                    required: true,
                    minlength: 6,
                    maxlength: 20

                }

            },
            messages: {
            	userId: {
                    required: "아이디를 입력하세요.",
                    	alphanumeric: "아이디를 입력하세요.",
                        minlength: "아이디를 입력하세요.",
                        maxlength: "아이디를 입력하세요."
                },
                userPw: {
                    required: "비밀번호를 입력하세요.",
                    minlength: "비밀번호를 입력하세요.",
                     maxlength: "비밀번호를 입력하세요."
                }
            }
        });
        
	});

    
/*아이디찾기 유효성 검사 시작*/
    $(function() {
    	// validate 이름 정규식 추가
    	$.validator.addMethod("kor", function(value, element) {
    		return this.optional(element) || /^[가-힣]*$/i.test(value);
    	});
    });
    
    $(function() {
        $("#find_id_form").validate({
            rules: {
                userName: {
                	required : true,
    				kor : true,
    				minlength : 2,
    				maxlength : 15
                 
                },
                userEmail: {
                	required : true,
    				email : true

                }

            },
            messages: {
            	userName: {
            		required : "이름을 입력해주세요.",
    				kor : "이름은 한글로 입력해주세요.",
    				minlength : "이름은 2~15글자입니다.",
    				maxlength : "이름은 2~15글자입니다."
    					
                },
                userEmail: {
                	required : "이메일을 입력해주세요.",
    				email : "이메일 형식이 맞지 않습니다."
                  
                }
            }
        });

    });

/*아이디찾기 유효성 검사 끝 */
/*비밀번호 재설정 유효성 검사 시작*/
    $(function() {
        $("#reset_pw_form").validate({
            rules: {
                resetPWID: {
                    required: true,
                    alphanumeric: true,
                    minlength: 3,
                    maxlength: 20
                },
                resetPWMail: {
                	required : true,
    				email : true

                }

            },
            messages: {
            	resetPWID: {
                    required: "아이디를 입력하세요.",
                    	alphanumeric: "아이디를 입력하세요.",
                        minlength: "아이디를 입력하세요.",
                        maxlength: "아이디를 입력하세요."
                },
                resetPWMail: {
                		required : "이메일을 입력해주세요.",
                		email : "이메일 형식이 맞지 않습니다."
                }
            }
        });

    });
/*아이디찾기 유효성 검사 끝 */

    
		
    /*
     * FunctionName: enter_move_input
     * Description: enter키로 input박스를 이동하기 위한 기능입니다. 최종 enter키로 submit.
     * Author: 최성준, 임채린
     * */

    // enter 키 속성 잠그기
    document.addEventListener('keydown', function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
        }
        ;
    }, true);

    // input box 이동
    $('input').keydown(function (e) {
        var idx = $('input').index(this);
        if (e.keyCode === 13) {
            $('input').eq(idx + 1).focus();
      
       //keydown function blur 처리
        this.blur();
        
       //마지막 form element에서 submit
        $(e.target).closest('form').submit();

        }
        ;
        
    });
    
   