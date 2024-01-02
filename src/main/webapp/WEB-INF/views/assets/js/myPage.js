/*
 * controller 속성 보다 우선 순위가 높아서  주석 처리 했습니다. by 최성준
 * 
 * let Email = "sjsh1623@naver.com"; //이메일
let userName = "임석현"; //사용자 이름
let userCode = "ABCDEFG"; //추천인 코드
let phoneNumber = "01055291629" //사용자 휴대폰 번호
let userAddress = "" //사용자 주소
let accountNum = ""
if (userAddress === "") { //사용자 주소가 입력이 되어있지 않다면 아래의 메세지를 출력합니다.
    userAddress = "구매&판매를 하시려면 배송정보를 먼저 등록하세요";
}
if (accountNum === "") { //사용자 계좌번호가 입력이 되어있지 않다면 아래의 메세지를 출력합니다.
    accountNum = "거래를 하시려면 입/출금 계좌 정보를 등록해주세요.편집";
}


$('#userEmail').html(Email);
$('#userName').html(userName);
$('#userRecommandCode').html(userCode);
$('#userPhoneNum').html(phoneFomatter(phoneNumber));
$('#userAddress').html(userAddress);
$('#userAccountNum').html(accountNum);*/

function copyText() {
    const value = document.getElementById("userRecommendCode").innerHTML;
    const input_temp = document.createElement("input");
    input_temp.value = value;
    document.body.appendChild(input_temp);
    input_temp.select();
    document.execCommand("copy");
    document.body.removeChild(input_temp);


    var Toast = Swal.mixin({
        toast: true,
        position: 'bottom-start',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        onOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })

    Toast.fire({
        icon: 'success',
        title: '클립보드에 복사하였습니다.'
    })

}

$(document).ready(function () {
    $('select').niceSelect();
});
