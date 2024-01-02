package com.ezen03.tieshoe.model;

import lombok.Data;

/** 
 * @filename : User.java
 * @description : 회원정보 DB연동을 위한 java beans입니다.
 * @author : 최성준
 */
@Data
public class boardBeans {

	//필수 입력항목
    private int ID;  //공지사항의 PK입니다.
    private String boardTitle;  //글 제목.
    private String boardDate;  //게시글 등록일
    private String boardContext;  //게시글 내용.
    private String reg_date;  //작성일.
    private String edit_date;  //수정일.
    private String boardImgPath; // 공지 이미지 경로.
    
	
	/** 페이지 구현이 필요한 경우 아래 속성들을 추가한다. (static)  */
    private static int offset;      // LIMIT 절에서 사용할 검색 시작 위치
    private static int listCount;   // LIMIT 절에서 사용할 검색할 데이터 수
    
    public static int getOffset() {
        return offset;
    }
    
    public static void setOffset(int offset) {
        boardBeans.offset = offset;
    }
    
    public static int getListCount() {
        return listCount;
    }
    
    public static void setListCount(int listCount) {
        boardBeans.listCount = listCount;
    }
   
}