package com.ezen03.tieshoe.model;
/*
 * FileName: productBeans.java
 * Description: 상품정보를 담고 있는 자바 빈즈 입니다.
 * Author: 임석현
 */

import lombok.Data;

@Data
public class productBeans {

    private String edit_date;  //
    private int ID;  //product의 PK입니다.
    private String product_thumb;  // 썸네일
    private String productBrandEN;  // 상품의 브랜드를 영어로 저장합니다.
    private String productBrandKR;  // 상품의 브랜드를 한국어로 저장합니다.
    private String productCode;  // 상품 스타일 코드 (고유번호)를 저장합니다.
    private String productColor;  // 상품의 색상을 저장합니다.
    private String productImgPath;  // 상품 이미지의 위치를 저장합니다.
    private String productNameEN;  // 상품의 이름을 영어로 저장합니다.
    private String productNameKR;  // 상품의 이름을 한국어로 저장합니다.
    private String productReleaseDate;  // 상품의 발매일을 저장합니다.
    private int productReleasePrice;  // 상품의 가격을 저장합니다.
    private String productUnit;  // 발매가의 단위 (한국 원인지 미화의 달러인지 구분)
    private String reg_date;
    private String index_thumb;
    private String List;
    private String productDrop;

    private String search;
    private int category;
    private int count;


    /**
     * 페이지 구현이 필요한 경우 아래 속성들을 추가한다. (static)
     */
    private static int offset;      // LIMIT 절에서 사용할 검색 시작 위치
    private static int listCount;   // LIMIT 절에서 사용할 검색할 데이터 수

    public static int getOffset() {
        return offset;
    }

    public static void setOffset(int offset) {
        productBeans.offset = offset;
    }

    public static int getListCount() {
        return listCount;
    }

    public static void setListCount(int listCount) {
        productBeans.listCount = listCount;
    }

}