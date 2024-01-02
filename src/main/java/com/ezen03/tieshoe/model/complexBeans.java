package com.ezen03.tieshoe.model;
import lombok.Data;
/*
 * FileName: buyComplex.java
 * Description: BUY 버튼에 최고입찰가, 최근거래가, 최저판매가를 위한 자바빈즈입니다.
 * Author: 임석현
 */

@Data
public class complexBeans {
    private int productNum;
    //ForBuyButton
    private int buyHighPrice;
    private int buyRecentPrice;
    private int buyLowPrice;
    private int size;
    private int user_ID;
    private int ID;
}
