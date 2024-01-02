/*
* FileName: moreBtn.js
* Description: detailSearch.jsp의 더보기 버튼을 위한 JS 입니다.
* Author: 최성준
* */
var imgFolder = "assets/img/shoes/10";
    var imgSources = [
        "Yeezy-Boost-380-Alien",
        "Jordan-1-Mid-Shattered-Backboard",
        "adidas-Yeezy-500-Soft-Vision",
        "Air-Max-97-Silver-Bullet-20162017-W",
        "adidas-Yeezy-Boost-700-Teal-Blue",
        "Air-Fear-of-God-1-Oatmeal",
        "Air-Force-1-Gore-tex-High-White-Ice",
        "Converse-Run-Star-Hike-Hi-JW-Anderson-Black",
        "Jordan-1-Retro-High-Off-White-Chicago",
        "Nike-Blazer-Mid-sacai-Black-Grey-1",
        "Jordan-6-Retro-Travis-Scott-PS",
        "Jordan-11-Retro-Playoffs-2019"
    ];
    var Lowest_price = 1234567;
    var link = "product.jsp";
    
    $("#moreBtn").click(function() {
        for (var i = 0; i < imgSources.length; i++) {
            $('#more_product').append(
            		"<a href=\" " 
            		+ link + 
            		" \">  <div>\n" 
            		+
                    "        <div class=\"uk-card uk-card-default uk-card-body\"> " 
                    + '<img src=\"' + imgFolder + '/' + imgSources[i] + '.jpg">' + 
                    "" 
                    +
                    "<div class='index_product_name'>" 
                    + shoeName + 
                    "</div>" 
                    +
                    "<div class='index_lowprice_name'>최저판매가</div>" 
                    +
                    "<div class='index_lowprice-value'>₩" 
                    + comma(Lowest_price) + 
                    "</div></div>\n" 
                    +
                    "    </div></a>  "
                    );
        }
    });