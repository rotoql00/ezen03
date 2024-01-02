/*
FileName: common.js
Description: 여러가지 필요한 기능을 가지고 있는 공통적인 JS 입니다.
Author: 임석현
Additional Info: 필요한 JS는 각자 주석 달아서 삽입해주세요.
*/

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

// 전화번호 정규화
// type에 0을 입력할 경우 가운데 *표시
function phoneFomatter(num, type) {
    var formatNum = '';
    if (num.length == 11) {
        if (type == 0) {
            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
        } else {
            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
        }
    } else if (num.length == 8) {
        formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
    } else {
        if (num.indexOf('02') == 0) {
            if (type == 0) {
                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-****-$3');
            } else {
                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
            }
        } else {
            if (type == 0) {
                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-***-$3');
            } else {
                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
            }
        }
    }
    return formatNum;
}


/*
 * FunctionName: enter_move_input
 * Description: enter키로 input박스를 이동하기 위한 기능입니다.
 * Author: 최성준
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
        
       /* this.blur();

        // Submit the form.
        $(e.target).closest('form').submit();*/
    }
    ;
});

/*
 * FunctionName: Daum_address_api
 * Description: 다음 주소 찾기 api입니다..
 * Author: 최성준
 * */

function openAddSearch() {
    new daum.Postcode({
        oncomplete: function (data) {
            /* jQuery
            $('[name=zipcode]').val(data.zonecode); // 우편번호 (5자리)
            $('[name=addr1]').val(data.address);
            $('[name=addr2]').val(data.buildingName);
            */

            document.getElementById('zip_code').value = data.zonecode;
            document.getElementById('addr_1').value = data.address, data.buildingName;
            document.getElementById('addr_2').focus();
        }
    }).open();
}

