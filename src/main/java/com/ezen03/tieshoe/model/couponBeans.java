package com.ezen03.tieshoe.model;

import lombok.Data;

@Data
public class couponBeans {
    private int ID;  //쿠폰 PK로 쿠폰키를 생성합니다.
    private String couponType;  //어떠한 타입의 쿠폰인지 확인합니다
    private String coupon_used;  // 1:배송쿠폰 2. 상품판정비 쿠폰
    private int user_ID;  //
    private String reg_date;  //쿠폰이 생성된 시점을 저장합니다.
    private String edit_date;  //쿠폰을 사용한 시점을 저장합니다.
}
