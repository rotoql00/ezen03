package com.ezen03.tieshoe.model;

import lombok.Data;

/**
 * @author : 최성준
 * @filename : User.java
 * @description : 회원정보 DB연동을 위한 java beans입니다.
 */
@Data
public class reviewBeans {

    //필수 입력항목
    private int reviewNum;        // rivew의 PK입니다.
    private float reviewRating;    // 상품의 평가를 1~5로 설정합니다.
    private String reviewContext;    // 리뷰의 내용을 저장합니다. 200글자
    private int user_ID;        // 사용자의 넘버를 가져옵니다. \n또한 FK가 아니며 복사를 해서 저장합니다.
    private String reg_date;        // 리뷰가 언제 작성되었는지 파악하기위해 작성 시간을 저장합니다
    private String edit_date;        // 수정일
    private int product_ID;        // 평가한 상품 번호
    private String productName;

    //user 테이블 join
    private String userId;            // 회원 계정 아이디
    private String userName;        // 회원 이름

    /**
     * 페이지 구현이 필요한 경우 아래 속성들을 추가한다. (static)
     */
    private static int offset;      // LIMIT 절에서 사용할 검색 시작 위치
    private static int listCount;   // LIMIT 절에서 사용할 검색할 데이터 수

    public static int getOffset() {
        return offset;
    }

    public static void setOffset(int offset) {
        reviewBeans.offset = offset;
    }

    public static int getListCount() {
        return listCount;
    }

    public static void setListCount(int listCount) {
        reviewBeans.listCount = listCount;
    }

}