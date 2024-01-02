/*
* FileName: popupCurrent.js
* Description: popupCurrent.html에 대한 JS입니다.
* Author: 임석현
* */

// 판매가에 대한 팝업입니다.
let current_status_button_buy = document.querySelector('.buy_button');
let current_status_button_sell = document.querySelector('.sell_button');
let current_status_button_deal = document.querySelector('.deal_button')
let current_status_popup = document.querySelector('.current_statusPopup_Wrapper');
let current_status_close = document.querySelector('.current_statusPopup_close');
let current_status_buy = document.querySelector('#tab-nav-2');
let current_status_sell = document.querySelector('#tab-nav-1');
let current_status_deal = document.querySelector('#tab-nav-3');


current_status_button_buy.addEventListener('click', () => {
    current_status_popup.style.display = "block";
    current_status_buy.checked = true;
    $('body').css('overflow', 'hidden');

});

current_status_button_sell.addEventListener('click', () => {
    current_status_popup.style.display = "block";
    current_status_sell.checked = true;
    $('body').css('overflow', 'hidden');
});

current_status_button_deal.addEventListener('click', () => {
    current_status_popup.style.display = "block";
    current_status_deal.checked = true;
    $('body').css('overflow', 'hidden');
});

current_status_close.addEventListener('click', () => {
    current_status_popup.style.display = "none";
    $('body').css('overflow', 'auto');
});

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

$(function() {
    $('select').selectric({
        maxHeight: 200
    });
});

