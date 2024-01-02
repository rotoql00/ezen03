/** 
 * @filename : boardService.java
 * @description : 공지사항 beans를 가져온  Service입니다.
 * @author : 최성준
 */
package com.ezen03.tieshoe.service;

import java.util.List;

import com.ezen03.tieshoe.model.boardBeans;

public interface BoardService {
	
	/**
	 * 공지사항 게시판 데이터 상세 조회
	 * @param BoardBeans 
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public boardBeans getBoardItem(boardBeans input) throws Exception;
	
	/**
	 * 공지사항 게시판 데이터 목록 조회
	 * @return 조회된 결과에 대한 컬렉션
	 * @throws Exception
	 */
	public List<boardBeans> getBoardList(boardBeans input) throws Exception;
	
	/**
	 * 공지사항 게시판 데이터 저장되어 있는 갯수 조회
	 * @return int
	 * @throws Exception
	 */
	public int getBoardCount(boardBeans input) throws Exception;
	
	 /**
     * 공지사항 게시판 데이터 등록하기
     * @param input 
     * @throws Exception
     */
    public int addBoard(boardBeans input) throws Exception;
    
    /**
     * 공지사항 게시판 데이터 수정하기
     * @param input  
     * @throws Exception
     */
    public int editBoard(boardBeans input) throws Exception;
    
    /**
     * 공지사항 게시판 데이터 삭제하기
     * @param input  
     * @throws Exception
     */
    public int deleteBoard(boardBeans input) throws Exception;
    
}
