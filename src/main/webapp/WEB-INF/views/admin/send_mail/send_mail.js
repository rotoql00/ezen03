/*
* FileName: send_mail.js
* Description: 회원관리를 위한 JS 입니다.
* Author: 임석현
* */

$(function () {
    $('select').niceSelect();
});

document.getElementById("user_search").disabled = true;
document.getElementById("user_search").value = "전체 발송";
document.getElementById("user_search").style.backgroundColor = "#e9f0ea";

function changeSelect() {
    var target = document.getElementById("user_category");
    var all = target.options[target.selectedIndex].value;

    if (all != "ALL") {
        document.getElementById("user_search").disabled = false;
        document.getElementById("user_search").value = "";
        document.getElementById("user_search").style.backgroundColor = "white"
    } else {
        document.getElementById("user_search").disabled = true;
        document.getElementById("user_search").value = "전체 발송";
        document.getElementById("user_search").style.backgroundColor = "#e9f0ea";
    }

}

ClassicEditor
    .create( document.querySelector( '#editor' ), {
        toolbar: [ 'bold', 'italic', 'link' ]
    } )
    .then( editor => {
        console.log( editor );
    } )
    .catch( error => {
        console.error( error );
    } );

$(function () {

// Initialize ajax autocomplete:
    $('#autocomplete-id').autocomplete({
        serviceUrl: '../../simpleJSON/user.json',
        lookupFilter: function (suggestion, originalQuery, queryLowerCase) {
            var re = new RegExp('\\b' + $.Autocomplete.utils.escapeRegExChars(queryLowerCase), 'gi');
            return re.test(suggestion.value);
        },
        onSelect: function (suggestion) {
            $('#selction-ajax').html('You selected: ' + suggestion.value + ', ' + suggestion.data);
        },
        onInvalidateSelection: function () {
            $('#selction-ajax').html('You selected: none');
        }
    })
});