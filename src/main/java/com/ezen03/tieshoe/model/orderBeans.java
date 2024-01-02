package com.ezen03.tieshoe.model;
/*
 * FileName: orderBeans.java
 * Description: 주문정보를 담고 있는 자바 빈즈 입니다.
 * Author: 임석현
 */

import lombok.Data;

@Data
public class orderBeans {
    private int ID;  //orederNum의 PK입니다.
    private int orderProductNum;  //주문 상품에 대한 정보입니다.
    private String orderNumber;  //주문번호를 생성해 저장합니다.
    private int orderPrice;  //거래가 이루어지는 가격을 저장합니다.
    private int orderSize;  //신발 사이즈를 저장합니다.
    private int sell_user_ID;  //sell_user_ID
    private int buy_user_ID;  //입찰자의 정보를 저장합니다.
    private int sell_ID;  //판매의 주문번호를 복사합니다.
    private int buy_ID;  //입찰의 주문번호를 복사합니다.
    private String reg_date;  //거래 시작일을 저장합니다.
    private String edit_date;  //거래 종료일을 저장합니다.
    private int BuyOrderStatus;  //
    private int SellOrderStatus;  //
    private String search;
    private int category;
    private String productName;
    private String buyer;
    private String seller;
    private String productImg;

    /**
     * 페이지 구현이 필요한 경우 아래 속성들을 추가한다. (static)
     */
    private static int offset;      // LIMIT 절에서 사용할 검색 시작 위치
    private static int listCount;   // LIMIT 절에서 사용할 검색할 데이터 수

    public static int getOffset() {
        return offset;
    }

    public static void setOffset(int offset) {
        orderBeans.offset = offset;
    }

    public static int getListCount() {
        return listCount;
    }

    public static void setListCount(int listCount) {
        orderBeans.listCount = listCount;
    }

}
