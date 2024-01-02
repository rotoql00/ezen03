/*
FileName: myPage_buy.js
Description: 개인 정보(입찰) 테이블에 속성 추가하는 JS
Author: 임석현
 */

function month(month) {
    console.log(month);
    document.getElementById("start").value = month;
}

$('#start').datepicker({
    language: 'ko',
});

$('#end').datepicker({
    language: 'ko',
});

$.fn.datepicker.language['ko'] = {
    days: ['일', '월', '화', '수', '목', '금', '토'],
    daysShort: ['일', '월', '화', '수', '목', '금', '토'],
    daysMin: ['일', '월', '화', '수', '목', '금', '토'],
    months: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthsShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    today: '오늘',
    clear: 'Clear',
    dateFormat: 'yyyy-mm-dd',
    timeFormat: 'hh:ii aa',
    firstDay: 0
};