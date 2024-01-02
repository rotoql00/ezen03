package com.ezen03.tieshoe.service;


import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.complexBeans;
import com.ezen03.tieshoe.model.sellBeans;

import java.util.List;

public interface SellService {

    /**
     * 상품의 가장 높은 판매 가격을 가져옵니다. (판매중인것 비포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public sellBeans getALLHighPrice(sellBeans input) throws Exception;

    /**
     * 상품의 가장 낮은 판매 가격을 가져옵니다. (판매중인것 비포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public sellBeans getALLLowPrice(sellBeans input) throws Exception;

    /**
     * 상품의 가장 낮은 판매 가격을 가져옵니다. (판매중인것 까지 포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public sellBeans getLowPrice260(sellBeans input) throws Exception;

    /**
     * 상품의 가장 낮은 판매 가격을 가져옵니다. (판매중인것 까지 포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public sellBeans getLowPrice265(sellBeans input) throws Exception;

    /**
     * 상품의 가장 낮은 판매 가격을 가져옵니다. (판매중인것 까지 포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public sellBeans getLowPrice270(sellBeans input) throws Exception;

    /**
     * 상품의 최근 거래가를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public sellBeans getRecentPrice(sellBeans input) throws Exception;

    /**
     * 모든 상품거래가를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<sellBeans> getSellAllPrice(sellBeans input) throws Exception;

    /**
     * 모든 상품거래가가 몇개인지 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int getSellCount(sellBeans input) throws Exception;

    /**
     * 해당 ID의 상품정보를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public sellBeans getSellInfo(sellBeans input) throws Exception;

    /**
     * 모든 상품거래가가 몇개인지 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */

    public int getSellListCount(sellBeans input) throws Exception;

    /**
     * 모든 상품거래가가 몇개인지 가져옵니다. (사용자 개인)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int getSellProcessListCount(sellBeans input) throws Exception;

    /**
     * 해당 상품에 낮은가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<complexBeans> buyButton(complexBeans input) throws Exception;

    /**
     * 입찰자가 바로구매를 선택했을경우 판매자의 상태를 업데이트합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int updateSell(sellBeans inputSell) throws Exception;

    /**
     * 판매자가 판매를 선택했을경우 판매자의 상태를 업데이트합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int addSell(sellBeans inputSell) throws Exception;

    /**
     * 최저 입찰가를 불러옵니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public complexBeans lowPrice(complexBeans inputNum) throws Exception;


    /**
     * 최고 판매가를 불러옵니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public complexBeans highPrice(complexBeans inputNum) throws Exception;

    /**
     * 입찰하려고 하는 사람이 해당 상품에 대해 판매을 했는지 확인합니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public sellBeans check(sellBeans check) throws Exception;

    /**
     * 판매건을 업데이트 합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int updateSellPrice(sellBeans inputBuy) throws Exception;

    /**
     * 입찰건을 삭제합니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int deleteBuy(sellBeans input) throws Exception;

    /**
     * 입찰자가 판매건을 업데이트 합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int sellTerminate(sellBeans sellInput) throws Exception;
    
    /**
     * 유저의 판매 거래 여부를 확인합니다.(유저 삭제를 위한 기능)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int sellUserCount(sellBeans input) throws Exception;
}
