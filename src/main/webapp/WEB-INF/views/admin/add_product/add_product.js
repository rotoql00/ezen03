/*
*FileName: add_product.js
*Description: 상품등록을 위한 JS입니다.
*Author: 임석현
*/


$('#releaseDate').datepicker({
    language: 'ko',
    multipleDatesSeparator: " - "
})

$.fn.datepicker.language['ko'] = {
    days: ['일', '월', '화', '수', '목', '금', '토'],
    daysShort: ['일', '월', '화', '수', '목', '금', '토'],
    daysMin: ['일', '월', '화', '수', '목', '금', '토'],
    months: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthsShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    today: '오늘',
    clear: 'Clear',
    dateFormat: 'yyyy/mm/dd',
    timeFormat: 'hh:ii aa',
    firstDay: 0
};

$(function () {
    $('select').selectric({
        maxHeight: 200
    });
});

$(function () {
    let $input = $("#releasePrice");
    $input.on('keyup', function () {
        // 입력 값 알아내기
        let _this = this;
        numberFormat(_this)
        console.log("up");
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

function numberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

//탭키와 엔터키로 정규화 검사
document.addEventListener('keydown', function (event) {
    if (event.keyCode === 13 || event.keyCode === 9) {

        var productName_kr = document.getElementById("productName_kr").value;
        var productName_en = document.getElementById("productName_en").value;
        var styleCode = document.getElementById("styleCode").value;
        var productColor = document.getElementById("productColor").value;

        var check_num = /[0-9]/; // 숫자
        var check_eng = /[a-zA-Z]/;
        var check_spc = /[~!@#$%^&*(),._+|<>?:{}]/; // 특수문자
        var check_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크
        var check_Date = /[\d{2}|\d{4}][\.|\/|\-]\d{1,2}[\.|\/|\-]\d{1,2}/;

        var Toast = Swal.mixin({
            toast: true,
            position: 'bottom-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            onOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })

        //productName_kr에 대한 정규화 검사
        if (!check_eng.test(productName_kr) && !check_spc.test(productName_kr)) {
        } else {
            Toast.fire({
                icon: 'error',
                title: '한글과 숫자만 입력해주세요 (상품명 - 한글).'
            })
            event.preventDefault();
        }

        if (!check_kor.test(productName_en) && !check_spc.test(productName_en)) {
        } else {
            Toast.fire({
                icon: 'error',
                title: '영어과 숫자만 입력해주세요 (상품명 - 영어).'
            })
            event.preventDefault();
        }

        if (!check_kor.test(styleCode) && !check_spc.test(styleCode)) {
        } else {
            Toast.fire({
                icon: 'error',
                title: '영어과 숫자만 입력해주세요 (상품코드).'
            })
            event.preventDefault();
        }

        if (!check_kor.test(productColor) && !check_spc.test(productColor) && !check_num.test(productColor)) {
        } else {
            Toast.fire({
                icon: 'error',
                title: '영어만 입력해주세요 (색상).'
            })
            event.preventDefault();
        }


    }
    ;
}, true);

// 엔터를 막았습니다
document.addEventListener('keydown', function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
    }
    ;
}, true);

function validate() {
    var x = document.getElementById("productName_kr");
    var verify = /^[가-힣0-9]+$/;
    if (verify.test(x)) {
        return true;
    } else {
        return false;
    }
}

function checkAll() {
    var productName_kr = document.getElementById("productName_kr").value;
    var productName_en = document.getElementById("productName_en").value;
    var styleCode = document.getElementById("styleCode").value;
    var productColor = document.getElementById("productColor").value;
    var releaseDate = document.getElementById("releaseDate").value;
    var releasePrice = document.getElementById("releasePrice").value;
    var productBrand = document.getElementById("productBrand").value;
    var image = document.getElementById("image").value;
    var Toast = Swal.mixin({
        toast: true,
        position: 'bottom-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        onOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })

    if (productName_kr == null || productName_kr == "") {
        Toast.fire({
            icon: 'error',
            title: '상품명(한글)이 입력되지 않았습니다.'
        })
        return false;
    } else if (productName_en == null || productName_en == "") {
        Toast.fire({
            icon: 'error',
            title: '상품명(영어)이 입력되지 않았습니다.'
        })
        return false;
    } else if (styleCode == null || styleCode == "") {
        Toast.fire({
            icon: 'error',
            title: '상품코드가 입력되지 않았습니다.'
        })
        return false;
    } else if (productColor == null || productColor == "") {
        Toast.fire({
            icon: 'error',
            title: '상품 색상이 입력되지 않았습니다'
        })
        return false;
    } else if (releaseDate == null || releaseDate == "") {
        Toast.fire({
            icon: 'error',
            title: '발매일이 입력되지 않았습니다'
        })
        return false;
    } else if (releasePrice == null || releasePrice == "") {
        Toast.fire({
            icon: 'error',
            title: '발매가가 입력되지 않았습니다'
        })
        return false;
    } else if (productBrand == "default") {
        Toast.fire({
            icon: 'error',
            title: '브랜드가 입력되지 않았습니다.'
        })
        return false;
    } else if (image == null || image == "") {
        Toast.fire({
            icon: 'error',
            title: '이미지가 등록되지 않았습니다.'
        })
        return false;
    } else {
        addProduct();
    }
}