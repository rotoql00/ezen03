/** 
 * @filename : LoginService.java
 * @description : 로그인 beans를 가져온  Service입니다.
 * @author : 임채린
 */
package com.ezen03.tieshoe.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.model.userReset;

public interface LoginService {
	/**
	 * 로그인
	 * @param userBeans 로그인하는 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	
	public userBeans login_ok(userBeans input) throws Exception;

	
	/**
	 * 아이디 찾기
	 * @param userBeans 아이디 찾기 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public userBeans findID(userBeans input) throws Exception;
	
	/**
	 * buy 거래 성사 체크
	 * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public int checkBuyProcessY(int input) throws Exception;
	
	/**
	 * buy 거래 철회 체크
	 * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public int checkBuyProcessA(int input) throws Exception;
	
	
	/**
	 * sell 거래 성사 체크
	 * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public int checkSellProcessY(int input) throws Exception;
	
	/**
	 * sell 거래 철회 체크
	 * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public int checkSellProcessA(int input) throws Exception;
	
	
	/**
	 *  
	 * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public userReset checkUserAuthEmail(int input) throws Exception;

	/**
	 *소셜로그인을 확인하기 위한 구문
	 * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public int sociaLogin_ok(userBeans userInput) throws Exception;
}

