package com.ezen03.tieshoe.model;
/*
 * FileName: sellBeans.java
 * Description: 판매정보를 담고 있는 자바 빈즈 입니다.
 * Author: 임석현
 */

import lombok.Data;

@Data
public class sellBeans {
    private int ID;  //
    private int sellPrice;  //판매 가격을 저장합니다.
    private int sellFee;  //판매 수수료
    private int productNum;  //상품의 넘버를 가져옵니다.
    private String reg_date;  //판매건이 생성된 날자를 저장합니다.
    private String edit_date;  //판매가 종료된 시간을 저장합니다.
    private int user_ID;  //
    private String sellProcess;  //
    private int sellSize;  //
    private int sellEnd;  //
    private int orderNum;

    // 최고, 최저 판매가 그리고 최근거래가를 가져오기위해 아래의 속성을 추가합니다.
    private int sellAllLowest; //최저 판매가 (현재 입찰중 미 포함)
    private int sellAllHighest; //최고 판매가 (현재 입찰중 미 포함)
    private int sellLowest260; //최저 판매가 (현재 입찰중 포함)
    private int sellLowest265; //최저 판매가 (현재 입찰중 포함)
    private int sellLowest270; //최저 판매가 (현재 입찰중 포함)
    private int sellRecent; //최근 거래가
    private int countProduct;
    private String productCode;
    private String product_thumb;
    private String productNameEN;
    private String orderStatus;
    private String rangeStart;
    private String rangeEnd;

    private String search;
    private int category;

    /**
     * 페이지 구현이 필요한 경우 아래 속성들을 추가한다. (static)
     */
    private static int offset;      // LIMIT 절에서 사용할 검색 시작 위치
    private static int listCount;   // LIMIT 절에서 사용할 검색할 데이터 수

    public static int getOffset() {
        return offset;
    }

    public static void setOffset(int offset) {
        sellBeans.offset = offset;
    }

    public static int getListCount() {
        return listCount;
    }

    public static void setListCount(int listCount) {
        sellBeans.listCount = listCount;
    }

}
