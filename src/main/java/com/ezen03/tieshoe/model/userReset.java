package com.ezen03.tieshoe.model;

import lombok.Data;

@Data
public class userReset {
    private int ID;  //현재 테이블의 PK입니다
    private String email_key;  //랜덤으로 생성된 이메일 키값입니다.
    private String email_type;  //인증종류
    private String key_used;  //
    private String reg_date;  //이메일 키가 생성된 시간을 저장합니다.
    private String edit_date;  //사용자가 키를 사용한 시간을 기록합니다.
    private int user_ID;
}

