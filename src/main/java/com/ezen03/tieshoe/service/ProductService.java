/*
 * FileName: productService.java
 * Description: 상품정보를 가져오기위한 service pattern 입니다.
 * Author: 임석현(sjsh1623@naver.com)
 * */
package com.ezen03.tieshoe.service;


import com.ezen03.tieshoe.model.orderBeans;
import com.ezen03.tieshoe.model.productBeans;
import com.ezen03.tieshoe.model.sellBeans;

import java.util.*;

public interface ProductService {
    /**
     * 상품정보를 가져오기 위한 service pattern (Product Page)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public productBeans getProduct(productBeans input) throws Exception;

    /**
     * 해당 상품에 260사이즈의 최신가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public orderBeans getRecent260(orderBeans input) throws Exception;

    /**
     * 해당 상품에 265사이즈의 최신가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public orderBeans getRecent265(orderBeans input) throws Exception;

    /**
     * 해당 상품에 270사이즈의 최신가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public orderBeans getRecent270(orderBeans input) throws Exception;

    /**
     * 해당 상품에 높은가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public orderBeans getRecentHigh(orderBeans input) throws Exception;

    /**
     * 해당 상품에 낮은가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public orderBeans getRecentLow(orderBeans input) throws Exception;

    /**
     * 해당 상품에 낮은가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> searchAll(productBeans input) throws Exception;

    /**
     * 해당 상품에 낮은가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int searchAllCount(productBeans input) throws Exception;

    /**
     * 상세검색 결과 상품정보를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<productBeans> detailSearch(Map<String, Object> map) throws Exception;

    /**
     * 상세검색 결과 상품 개수를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int detailSearchCount(Map<String, Object> map) throws Exception;

    /**
     * Buy의 넘어온 값으로 이미밑 상품명을 조회합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public productBeans getBuyProductInfo(productBeans input) throws Exception;

    /**
     * 상품을 DROP 하거나 복구합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int dropProduct(productBeans input) throws Exception;

    /**
     *  최근 검색을 가져오기한 구문입니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public productBeans getRecentProduct(productBeans productInput) throws Exception;

    /**
     *  모든 상품 갯수를 불러옵니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int countAllProduct() throws Exception;
}