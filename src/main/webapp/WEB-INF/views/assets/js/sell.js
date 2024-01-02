/*
* FileName: sell.js
* Description: sell.html에 대한 JS입니다.
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

function numberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

function calculation(level, fee) {
    var percentage;
    let consideration;

    switch (level) {
        case 1:
            percentage = 0.09;
            break;
        case 2:
            percentage = 0.1;
            break;
        case 3:
            percentage = 0.11;
            break;
        case 4:
            percentage = 0.12;
            break;
        case 5:
            percentage = 0.13;
    }

    let sell_price = document.getElementById("price").value;
    consideration = parseInt(uncomma(sell_price)) * percentage;


    let final_consideration = "₩&nbsp" + comma(String(Math.ceil(((consideration + 10) / 100)) * 100));
    let sell_totalPrice = "₩&nbsp" + comma(String((Math.ceil(((consideration + 10) / 100)) * 100) + parseInt(uncomma(sell_price))));

    if (parseInt(uncomma(sell_price)) < 1000) {
        final_consideration = "₩&nbsp0";
        sell_totalPrice = "₩&nbsp0";
    }

    //Sell_total_value를 HTML에 삽입합니다.
    document.getElementById("Sell_cons").innerHTML = final_consideration;
    document.getElementById("Sell_totalPrice").innerHTML = sell_totalPrice;
}

// 엔터키 제한
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

function validate(buy) {

    var x = parseInt(uncomma(document.getElementById("price").value));
    var addr = parseInt(document.getElementById("addrCheck").innerHTML);
    var y = document.getElementById("price").value;
    if (y == "" || y == null || y == 0) {
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
    } else if (x == buy) {
        var Toast = Swal.fire({
            icon: 'warning',
            title: "바로 판매가격을 입력하셨습니다.",
            text: '판매하시면 바로 거래가 진행됩니다.',
        }).then((result) => {
                if (result.value) {
                    UIkit.modal('#check').show()
                    return true;
                }
            }
        )
    } else if (x > buy && buy != null) {
        var Toast = Swal.fire({
            icon: 'error',
            title: '에러',
            text: '바로 판매가격보다 높은 가격을 입력하셨습니다',
        })
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
