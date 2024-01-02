/*
* FileName: detailSearch.js
* Description: detailSearch.html에 대한 JS입니다.
* Author: 임채린
*/

$(function() {
    let $input1 = $("#minPrice");
    let $input2 = $("#maxPrice");
    $input1.on('keyup', function() {
        // 입력 값 알아내기
        let _this = this;
        numberFormat(_this)
    })

    $input2.on('keyup', function() {
        // 입력 값 알아내기
        let _this = this;
        numberFormat(_this)
    })
});

// 콤마 찍기
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

// 콤마 풀기
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

// 숫자만!
function numberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

function replace(obj) {
	var a = $("#minPrice").val().replace(/\,/gi, "");
	var b = $("#maxPrice").val().replace(/\,/gi, "");
	$("#minPrice").val(a);
	$("#maxPrice").val(b);

}