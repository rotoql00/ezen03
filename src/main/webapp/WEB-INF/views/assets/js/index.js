//인기상품
for (let j = 0; j < 12; j++) {

    //루프를 통해 이미지와 정보를 가져옵니다.
    var img = "<img src=\"assets/img/shoes/09/adidas-Yeezy-500-Bone-White_shadowed2.jpg\" alt=\"Yeezy-Boost-380-Alien\">"
    var shoeName = "Yeezy Boost 380 Alien"
    var Lowest_price = 1234567;
    var link = "/product";

    $("#index_slider").append("<li><a href=\"<%=request.getContextPath()%> " + link + " \">" + img + "\n" +
        "                        <div class=\"index_product_name\">" + shoeName + "</div>\n" +
        "                        <div class=\"index_lowprice_name\">최저판매가</div>\n" +
        "                        <div class=\"index_lowprice-value\">₩" + comma(Lowest_price) + "</div>\n" +
        "                    </a></li>");
}

//추천 상품

for (let i = 0; i < 12; i++) {

    //루프를 통해 이미지와 정보를 가져옵니다.
    var img = "<img src=\"assets/img/shoes/10/Yeezy-Boost-380-Alien.jpg\" alt=\"Yeezy-Boost-380-Alien\">"
    var shoeName = "Yeezy Boost 380 Alien"
    var Lowest_price = 1234567;
    var link = "/product";

    $("#index_gridList").append
    (
        "<a href=\"<%=request.getContextPath()%> " + link + " \">  <div>\n" +
        "        <div class=\"uk-card uk-card-default uk-card-body\">" + img + "" +
        "<div class='index_product_name'>" + shoeName + "</div>" +
        "<div class='index_lowprice_name'>최저판매가</div>" +
        "<div class='index_lowprice-value'>₩" + comma(Lowest_price) + "</div></div>\n" +
        "    </div></a>  "
    );
}

for (let i = 0; i < 12; i++) {

    //루프를 통해 이미지와 정보를 가져옵니다.
    var img = "<img src=\"assets/img/shoes/10/Yeezy-Boost-380-Alien.jpg\" alt=\"Yeezy-Boost-380-Alien\">"
    var shoeName = "Yeezy Boost 380 Alien"
    var Lowest_price = 1234567;
    var link = "/product";

    $("#index_gridList_another").append
    (
        "<a href=\"<%=request.getContextPath()%> " + link + " \">  <div>\n" +
        "        <div class=\"uk-card uk-card-default uk-card-body\">" + img + "" +
        "<div class='index_product_name'>" + shoeName + "</div>" +
        "<div class='index_lowprice_name'>최저판매가</div>" +
        "<div class='index_lowprice-value'>₩" + comma(Lowest_price) + "</div></div>\n" +
        "    </div></a>  "
    );
}

$(document).ready(function () {
    // hide #back-top first
    $("#recentItem").hide();
    // fade in #back-top
    $(function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 200) {
                $('#recentItem').fadeIn();
            } else {
                $('#recentItem').fadeOut();
            }
        });

    });
});
