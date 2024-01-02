$(document).ready(function () {
    // hide #back-top first
    $("#scroll-top").hide();
    // fade in #back-top
    $(function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 900) {
                $('#scroll-top').fadeIn();
            } else {
                $('#scroll-top').fadeOut();
            }
        });
    });
});

function addFavorite() {
    alert("Ctrl+D 키를 누르면 즐겨찾기에 추가하실 수 있습니다.");
}

function summitSearch() {
    document.getElementById('search').submit();
}

function submitEnter(e) {
    if (e.keyCode === 13) {
        e.preventDefault();
        document.getElementById('search').submit();
    }
}

$('.headerRank').vTicker({
    speed: 500,
    pause: 3000,
    showItems: 1,
    mousePause: true,
    height: 20,
    direction: 'up'
});
