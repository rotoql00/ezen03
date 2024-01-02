package com.ezen03.tieshoe.service;

import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.model.userBeans;

import java.util.List;

public interface MypageService {
    /**
     * buy한 유저 DB를 가져옵니다.
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public userBeans getbuyInfo(userBeans input) throws Exception;

    /**
     * buy한 상품을 가져옵니다.
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<buyBeans> getBuyList(buyBeans input) throws Exception;

    /**
     * buy한 유저 DB를 가져옵니다.
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public userBeans getSellInfo(userBeans input) throws Exception;

    /**
     * sell한 상품을 가져옵니다.
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<sellBeans> getSellList(sellBeans input) throws Exception;

    /**
     * buy한 상품을 가져옵니다.
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<buyBeans> getBuyProcessList(buyBeans input) throws Exception;

    /**
     * sell한 상품을 가져옵니다.
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<sellBeans> getSellProcessList(sellBeans input) throws Exception;

}
