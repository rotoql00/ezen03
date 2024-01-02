/*
* FileName: buy.js
* Description: buy.html에 대한 JS입니다.
* Author: 임석현
* */

$(function () {
    let $input = $("#price");
    $input.on('keyup', function () {
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

function calculation() {
    let buy_price = document.getElementById("price").value;
    let shipping_Price = document.getElementById("buy_shipping").textContent;
    if (document.getElementById("buy_shipping").classList.contains("couponApplied")) {
        shipping_Price = 0;
    }
    let consideration = document.getElementById("buy_cons").textContent;
    let buy_totalPrice = "₩&nbsp" + comma(String(parseInt(uncomma(shipping_Price)) + parseInt(uncomma(consideration)) + parseInt(uncomma(buy_price))));

    //1000원 이상 입력하지 않으면 0으로 되돌립니다.
    if (parseInt(uncomma(buy_price)) < 1000) {
        buy_totalPrice = "₩&nbsp" + 0;
    }


    //buy_total_value를 HTML에 삽입합니다.
    document.getElementById("buy_totalPrice").innerHTML = buy_totalPrice;
}


//엔터키 제한
document.addEventListener('keydown', function (event) {
    if (event.keyCode === 13) {
        $('#price').blur();
        event.preventDefault();
    }
    ;
}, true);

// Warning
$(window).on('beforeunload', function () {
    return "Any changes will be lost";
});

// Form Submit
$(document).on("submit", "form", function (event) {
    // disable unload warning
    $(window).off('beforeunload');
});

function validate(sell, check) {

    var x = parseInt(uncomma(document.getElementById("price").value));
    var addr = parseInt(document.getElementById("addrCheck").innerHTML);
    var y = document.getElementById("price").value;
    console.log(addr);

    if (y == "" || y == null || y == 0) {
        var Toast = Swal.fire({
            icon: 'error',
            title: '에러',
            text: '가격이 입력되지 않았습니다.',
        })
        return false;
    } else if (y == "" || y == null || y == 0) {
        var Toast = Swal.fire({
            icon: 'error',
            title: '에러',
            text: '가격이 입력되지 않았습니다.',
        })
        return false;
    } else if (x < 1000) {
        var Toast = Swal.fire({
            icon: 'error',
            title: '에러',
            text: '1000원 이상 금액을 입력해주세요.',
        })
        return false;
    } else if (addr == 1 || addr == undefined) {
        var Toast = Swal.fire({
            icon: 'error',
            title: '에러',
            text: '배송정보를 입력해주세요.',
        })
        return false;
    } else if (x == sell) {
        var Toast = Swal.fire({
            icon: 'warning',
            title: "바로 구매가격을 입력하셨습니다.",
            text: '입찰하시면 바로 거래가 진행됩니다.',
        }).then((result) => {
                if (result.value) {
                    UIkit.modal('#check').show()
                    return true;
                }
            }
        )
    } else if (x > sell && sell != null) {
        var Toast = Swal.fire({
            icon: 'error',
            title: '에러',
            text: '바로 구매가격보다 높은 가격을 입력하셨습니다',
        })
        return false;
    } else if (check == x) {
        var Toast = Swal.fire({
            icon: 'error',
            title: '에러',
            text: '수정하기 전 가격과 동일합니다',
        })
        return false;
    } else {
        UIkit.modal('#check').show()
        return true;
    }
}

function validateCheckBox() {
    if ($("#firstCheck:checked, #secondCheck:checked, #thirdCheck:checked").length == 3) {
        return true;
    } else {
        var Toast = Swal.fire({
            icon: 'error',
            title: '에러',
            text: '입찰 동의 사항에 동의하지 않으셨습니다',
        })
        return false;
    }
}

$(function () {
    $('select').selectric({
        maxHeight: 200
    });
});
