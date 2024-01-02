/** 
 * @filename : reviewInfoService.java
 * @description : 회원정보 beans를 가져온  Service입니다.
 * @author : 최성준, 임석현
 */
package com.ezen03.tieshoe.service;

import java.util.List;

import com.ezen03.tieshoe.model.reviewBeans;

public interface ReviewService {
	/**
	 * 리뷰 데이터 상세 조회
	 * @param reviewBeans 조회할 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public reviewBeans getReviewItem(reviewBeans input) throws Exception;
	
	/**
	 * 제품 리뷰 중복  조회
	 * @param reviewBeans 조회할 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	public int getCheckReview(reviewBeans input) throws Exception;
	
	/**
	 * 리뷰 데이터 목록 조회
	 * @return 조회된 결과에 대한 컬렉션
	 * @throws Exception
	 */
	public List<reviewBeans> getReviewList(reviewBeans input) throws Exception;
	
	/**
	 * 리뷰 데이터 목록 조회
	 * @return 조회된 결과에 대한 컬렉션
	 * @throws Exception
	 */
	public List<reviewBeans> getReviewUserList(reviewBeans input) throws Exception;
	
	/**
	 * 리뷰 데이터 목록 조회
	 * @return 조회된 결과에 대한 컬렉션
	 * @throws Exception
	 */
	public List<reviewBeans> getReviewProductList(reviewBeans input) throws Exception;
	
	/**
	 * 전체 리뷰 갯수 조회
	 * @return int
	 * @throws Exception
	 */
	public int getReviewAllCount(reviewBeans input) throws Exception;
		
	/**
	 * 유저가 작성한 리뷰 갯수 조회
	 * @return int
	 * @throws Exception
	 */
	public int getReviewUserCount(reviewBeans input) throws Exception;
	
	/**
	 * 상품에 작성된 리뷰 갯수 조회
	 * @return int
	 * @throws Exception
	 */
	public int getReviewProductCount(reviewBeans input) throws Exception;
	
	/**
	 * 상품에 대해 유저의 리뷰 작성 여부 조회
	 * @return int
	 * @throws Exception
	 */
	public int getOverlapCheckReview(reviewBeans input) throws Exception;	
	
	/**
	 * 리뷰가 작성된 상품의 평균 점수
	 * @return int
	 * @throws Exception
	 */
	public int getSumPointProduct(reviewBeans input) throws Exception;
	
	 /**
     * 리뷰 데이터 등록하기
     * @param input 저장할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int addReview(reviewBeans input) throws Exception;
    
    /**
     * 리뷰 데이터 수정하기
     * @param input  수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int editReview(reviewBeans input) throws Exception;
    
    /**
     * 리뷰 데이터 삭제하기
     * @param input  삭제할 유저(회원)의 일련번호를 담고 있는 Beans
     * @throws Exception
     */
    public int deleteReview(reviewBeans input) throws Exception;
    
}
