/*
* FileName: product.js
* Description: product.html에 대한 JS입니다.
* Author: 임석현
* */

// 이용안내에 대한 팝업입니다.
let userGuidebutton = document.querySelector('.userGuidebutton');
let userGuidepopup = document.querySelector('.userGuidePopup_Wrapper');
let userGuideclose = document.querySelector('.userGuidePopup_close');

userGuidebutton.addEventListener('click', () => {
    userGuidepopup.style.display = "block";
});

userGuideclose.addEventListener('click', () => {
    userGuidepopup.style.display = "none";
});


// BUY에대한 popup입니다.
let buypopup = document.querySelector('.buyPopup_Wrapper');
let buyclose = document.querySelector('.buyPopup_close');

function buyButton() {
    buyButtonDB();
    buypopup.style.display = "block";
    $('body').css('overflow', 'hidden');
}


buyclose.addEventListener('click', () => {
    buypopup.style.display = "none";
    $('body').css('overflow', 'auto');
});

//SELL에 대한 popup입니다.
let sellpopup = document.querySelector('.sellPopup_Wrapper');
let sellclose = document.querySelector('.sellPopup_close');

function sellButton() {
    buyButtonDB();
    sellpopup.style.display = "block";
    $('body').css('overflow', 'hidden');
};

sellclose.addEventListener('click', () => {
    sellpopup.style.display = "none";
    $('body').css('overflow', 'auto');
});

let more_option = document.querySelector('.product_more');

more_option.addEventListener('click', () => {
    current_status_popup.style.display = "block";
    current_status_buy.checked = true;
});

function submitfunction(size) {
    document.getElementById("buyPopup").submit(size);
}


// 콤마 찍기
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function login() {
    var Toast = Swal.fire({
        icon: 'error',
        title: '로그인',
        text: '로그인이 필요한 서비스입니다.',
    })
    return false;
}

function penalty() {
    var Toast = Swal.fire({
        icon: 'error',
        title: '패널티',
        text: '패널티 회원은 지정 날짜까지 입찰 판매가 불가능 합니다',
    })
    return false;
}

