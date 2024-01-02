package com.ezen03.tieshoe.model;

import lombok.Data;

@Data
public class visitBeans {
    private int ID;  //기본키, 시퀸스 달것임
    private String visit_IP;  //접속자 아이피
    private String visit_time;  //접속자 접속시간
    private String visit_refer;  //접속자가 어느사이트를 타고 들어왔는지
    private String visit_agent;  //접속자 브라우저 정보
}
