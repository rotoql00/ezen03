/*
* FileName: user_manage.js
* Description: 회원관리를 위한 JS 입니다.
* Author: 임석현
* */
$(function () {
    $('select').selectric({
        maxHeight: 200
    });
});

Handlebars.registerHelper('convertTF', function (t) {
    if (t == "true") {
        return "Y";
    } else {
        return "N";
    }
})