package com.ezen03.tieshoe.service;

import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.complexBeans;
import com.ezen03.tieshoe.model.sellBeans;

import java.util.List;

public interface BuyService {
    /**
     * 상품의 가장 낮은 입찰 가격을 가져옵니다. (판매중인것 까지 포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public buyBeans getHighPrice260(buyBeans input) throws Exception;

    /**
     * 상품의 가장 낮은 입찰 가격을 가져옵니다. (판매중인것 까지 포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public buyBeans getHighPrice265(buyBeans input) throws Exception;

    /**
     * 상품의 가장 낮은 입찰 가격을 가져옵니다. (판매중인것 까지 포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public buyBeans getHighPrice270(buyBeans input) throws Exception;

    /**
     * 상품의 모든 입찰 가격을 가져옵니다. (판매중인것 까지 포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<buyBeans> getBuyAllPrice(buyBeans input) throws Exception;

    /**
     * 상품의 페이지를 위해 카운트를 가져옵니다. (판매중인것 까지 포함)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int getBuyCount(buyBeans input) throws Exception;

    /**
     * 상품의 페이지를 위해 카운트를 가져옵니다. (개인 사용자)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */

    public int getBuyListCount(buyBeans input) throws Exception;

    /**
     * 상품의 페이지를 위해 카운트를 가져옵니다. (개인 사용자)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */

    public int getBuyProcessListCount(buyBeans input) throws Exception;

    /**
     * 상품의 페이지를 위해 카운트를 가져옵니다. (개인 사용자)
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */

    /**
     * 해당 상품에 낮은가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<complexBeans> buyButton(complexBeans input) throws Exception;

    /**
     * 가격정보를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public complexBeans highPrice(complexBeans input) throws Exception;

    /**
     * 가격정보를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public complexBeans lowPrice(complexBeans input) throws Exception;

    /**
     * BUY에 추가합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int addBuy(buyBeans inputBuy) throws Exception;

    /**
     * 해당 ID의 BUY를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public buyBeans selectOne(buyBeans finalResult) throws Exception;

    /**
     * 판매자가 바로판매를 선택했을경우 판매자의 상태를 업데이트합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int updateBuy(buyBeans inputBuy) throws Exception;

    /**
     * 판매하려고 하는 사람이 해당 상품에 대해 입찰을 했는지 확인합니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public buyBeans check(buyBeans check) throws Exception;

    /**
     * 입찰자가 수정을 원할떄 수행됩니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int updateBuyPrice(buyBeans inputBuy) throws Exception;

    /**
     * 입찰자가 입찰건을 삭제할떄 사용됩니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int deleteBuy(buyBeans input) throws Exception;

    /**
     * 입찰자가을 철회했을떄 사용됩니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int buyTerminate(buyBeans buyInput) throws Exception;
    
    /**
     * 유저의 구매 거래 여부를 확인합니다.(유저 삭제를 위한 기능)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */

    public int buyUserCount(buyBeans input) throws Exception;
}