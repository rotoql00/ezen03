package com.ezen03.tieshoe.service;


import com.ezen03.tieshoe.model.*;

import java.util.List;

public interface indexService {


    /**
     * 아디다스 상품의 상위 4개를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> getAdidas(productBeans input) throws Exception;

    /**
     * 나이키 상품의 상위 4개를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> getNike(productBeans input) throws Exception;

    /**
     * 조던 상품의 상위 4개를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> getJordan(productBeans input) throws Exception;

    /**
     * 컨버스 상품의 상위 4개의 제품을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> getConverse(productBeans input) throws Exception;

    /**
     * 신상품들을 가져옵니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> newArrival(productBeans input) throws Exception;

    /**
     * 신상품들을 가져옵니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> bestProduct(productBeans input) throws Exception;

    /**
     * 인기상품을 가져옵니다.
     *
     * @param input    상품의 beans
     * @param codeList
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> getPopular(List<String> codeList) throws Exception;

    /**
     * 인기상품을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public popularBeans getPopularRank(popularBeans input) throws Exception;

    /**
     * 인기상품을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public userBeans getSurvey(userBeans input) throws Exception;

    /**
     * 인기상품을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> getMdPick(List<String> codeList) throws Exception;

    /**
     * 인기상품을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public rankBeans rank(rankBeans input) throws Exception;


}
