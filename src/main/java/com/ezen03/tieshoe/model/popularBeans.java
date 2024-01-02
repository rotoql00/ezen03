package com.ezen03.tieshoe.model;

import lombok.Data;

@Data
public class popularBeans {
    private int ID;  //
    private String popular_product;  //인기 상품 JSON 방식으로 저장합니다.
    private String reg_date;  //
    private String edit_date;  //
}
