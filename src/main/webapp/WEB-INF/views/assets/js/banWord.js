/**
 * 금칙어 필터링 inputBox가 여러개인 경우도 같이 상정하여 작성했습니다. 여려개일경우 class명을 동일하게 맞춰주세요
 * 
 * EX <form calss="testform" action=""> <input class="testinput" id="inputWord1"
 * type="text"> <input class="testinput" id="inputWord2" type="text"> <input
 * class="testinput" id="inputWord3" type="text"> <button class="check123"
 * type="button" id="check">확인</button> </form>
 */
banWordArr = [ "쓰발", "씨발", "시발", "시펄", "admin" ];

AlertArr = [];

AlertCount = 0;

$(".check123").click(function() {

	var inputTxt = $(".testinput");

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
				$(inputTxt[i]).select();
				break;
			}
		}

		if (find) {
			break;
		}
	}

	return;

});// submit
