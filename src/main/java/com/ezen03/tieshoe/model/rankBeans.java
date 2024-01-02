package com.ezen03.tieshoe.model;

import lombok.Data;

@Data
public class rankBeans {
    private String edit_date;  //
    private int ID;  //인기 검색어의 날자를 저장합니다.
    private String popularFifth;  //인기 검색어의 5순위 입니다.
    private String popularFirst;  //인기검색어의 1순위 입니다.
    private String popularFourth;  //인기 검색어의 4순위 입니다.
    private String popularSecond;  //인기검색어의 2순위 입니다.
    private String popularSeventh;  //인기 검색어의 7순위 입니다.
    private String popularSixth;  //인기 검색어의 6순위 입니다.
    private String popularThird;  //인기 검색어의 3순위 입니다.
    private String reg_date;  //
}
