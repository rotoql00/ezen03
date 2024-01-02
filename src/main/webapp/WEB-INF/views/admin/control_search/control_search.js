function check() {
    var first = document.getElementById("first").value;
    var second = document.getElementById("second").value;
    var third = document.getElementById("third").value;
    var fourth = document.getElementById("fourth").value;
    var fifth = document.getElementById("fifth").value;
    var sixth = document.getElementById("sixth").value;
    var seventh = document.getElementById("seventh").value;

    if (first==="" || second==="" || third==="" || fourth==="" || fifth==="" || sixth==="" || seventh==="") {
        var Toast = Swal.fire({
            icon: 'error',
            title: '에러',
            text: '하나 이상의 검색어를 입력하지 않았습니다.',
        })
        event.preventDefault();
        return false;
    }
} 