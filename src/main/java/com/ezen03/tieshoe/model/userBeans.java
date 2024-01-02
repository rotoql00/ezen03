package com.ezen03.tieshoe.model;

import lombok.Data;

/** 
 * @filename : User.java
 * @description : 회원정보 DB연동을 위한 java beans입니다.
 * @author : 최성준
 */
@Data
public class userBeans {

	//필수 입력항목
    private int ID;  //사용자의 PK입니다.
    private String userId;  //사용자의 아이디 입니다.
    private String userPw;  //사용자의 비밀번호를 md5형식으로 저장합니다
    private String userName;  //사용자의 이름을 저장합니다.
    private String userPhonenum;  //사용자의 휴대번호를 저장합니다.
    private String userBirthDate;  //사용자의 생일을 저장합니다.
    private String userEmail;  //사용자의 이메일을 저장합니다.
    private String userZipcode;  //사용자의 우편번호를 저장합니다.
    private String userAddress1;  //사용자의 주소를 저장합니다
    private String userAddress2;  //사용자의 주소를 저장합니다
    private String reg_date;  //사용자 회원가입 날짜를 저장합니다.
    private String edit_date;  //사용자 정보 수정 날짜를 저장합니다.
    private int userLevel;
    private String penaltyDate;

    //defalut 항목
    private String userAdmin;  //현재 계정이 관리자인지 확인하는 구문입니다.
    private String userForm;  //설문지를 JSON형식으로 저장합니다.
    private String userAccountBank;  //사용자의 계좌의 은행을 저장합니다.
    private String userAccountNum;  //사용자의 계좌번호 정보를 저장합니다.
    private String userdropTF;  //현재 사용자가 탈퇴했는지의 여부를 확인합니다.
    private String user_penalty;  //사용자 패널티 여부를 결정합니다.
    private String emailCheck; //사용자 이메일 인증여부를 파악합니다.
    private String couponCount;

    private String search; // 관리자의 검색 키워드를 저장합니다.
    private int category; //관리자의 검색 타입을 저장합니다.

   /** 페이지 구현이 필요한 경우 아래 속성들을 추가한다. (static)  */

	private String hiddenEmail; //가려져서 나오는 Email입니다.
    private int countShipping;
    private int countCoupon;
    private String buyProcess;
    private String sellProcess;
    private int email_type;
    private String key_used;

	
	/** 페이지 구현이 필요한 경우 아래 속성들을 추가한다. (static)  */
    private static int offset;      // LIMIT 절에서 사용할 검색 시작 위치
    private static int listCount;   // LIMIT 절에서 사용할 검색할 데이터 수
    
    public static int getOffset() {
        return offset;
    }
    
    public static void setOffset(int offset) {
        userBeans.offset = offset;
    }
    
    public static int getListCount() {
        return listCount;
    }
    
    public static void setListCount(int listCount) {
        userBeans.listCount = listCount;
    }
   
}