package com.ezen03.tieshoe.model;

import lombok.Data;

@Data
public class buyBeans {
    private int buyEnd;  //
    private int buyFee;  //판매 수수료 값을 저장합니다.
    private int buyPrice;  //
    private String buyProcess;  //
    private int buyProductSize;  //
    private int buySize;  //
    private String edit_date;  //
    private int ID;  //buy의 PK입니다.
    private int productNum;  //
    private String reg_date;  //
    private int user_ID;  //

    private String search;
    private int category;

    private int orderNum;
    private String productCode;
    private String product_thumb;
    private String productNameEN;
    private String start;
    private String orderStatus;
    private int buyHighest260; //최저 판매가 (현재 입찰중 포함)
    private int buyHighest265; //최저 판매가 (현재 입찰중 포함)
    private int buyHighest270; //최저 판매가 (현재 입찰중 포함)
    private int countProduct;
    private String rangeStart;
    private String rangeEnd;
    private String orderReg_date;
    /**
     * 페이지 구현이 필요한 경우 아래 속성들을 추가한다. (static)
     */

    private static int offset;      // LIMIT 절에서 사용할 검색 시작 위치
    private static int listCount;   // LIMIT 절에서 사용할 검색할 데이터 수

    public static int getOffset() {
        return offset;
    }

    public static void setOffset(int offset) {
        buyBeans.offset = offset;
    }

    public static int getListCount() {
        return listCount;
    }

    public static void setListCount(int listCount) {
        buyBeans.listCount = listCount;
    }
}
