package com.ezen03.tieshoe.service;

import com.ezen03.tieshoe.model.recentCheck;

public interface checkService {

    /**
     * 최근 검색 상품을 가져옵니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public recentCheck getCheck(recentCheck checkInput) throws Exception;

    /**
     *최근 검색 상품을 업데이트합니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int update(recentCheck checkInput) throws Exception;


    /**
     * 최근 상품이 존재하지 않는다면 새로 삽입합니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int insert(recentCheck checkInput) throws Exception;
}
