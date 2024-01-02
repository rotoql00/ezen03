package com.ezen03.tieshoe.service;

import com.ezen03.tieshoe.model.*;

import java.util.*;

public interface AdminService {

    /**
     * 인기상품을 설정합니다.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public int setPopular(popularBeans input) throws Exception;

    /**
     * 검색어를 설정합니다.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public int setRank(rankBeans input) throws Exception;

    /**
     * 상품코드를 검색합니다.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public List<productBeans> getProductCode(productBeans input) throws Exception;

    /**
     * 관리자의 주문정보를 가져오기 위한 구문입니다.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public List<orderBeans> orderStatus(orderBeans input) throws Exception;

    /**
     * 관리자의 주문정보를 가져오기 위한 구문입니다. (페이징을 위함)
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public int orderStatusCount(orderBeans input) throws Exception;

    /**
     * 현황 업데이트를 위한 구문입니다.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public int orderUpdate(orderBeans input) throws Exception;

    /**
     * 모든 사용자의 수를 가져오기 위한 구문.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public int userCount(userBeans input) throws Exception;

    /**
     * 사용자의 정보를 가져오기 위한 구문.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public List<userBeans> userStatus(userBeans input) throws Exception;

    /**
     * 상품의 개수를 가져오기 위한 구문(페이징)
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public int productListCount(productBeans input) throws Exception;

    /**
     * 모든 상품정보를 가져옵니다.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public List<productBeans> getProductList(productBeans input) throws Exception;

    /**
     * 모든 입찰정보를  Count합니다
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public int buyListCount(buyBeans input) throws Exception;

    /**
     * 모든 입찰정보를 가져옵니다.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public List<buyBeans> getBuyList(buyBeans input) throws Exception;

    /**
     * 구매 주문을 업데이트합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int updateOrderStatus(orderBeans input) throws Exception;

    /**
     * 모든 입찰정보를  Count합니다
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public int sellListCount(sellBeans input) throws Exception;

    /**
     * 모든 입찰정보를 가져옵니다.
     *
     * @param input 상품의 beans
     * @param
     * @return int
     * @throws Exception
     */
    public List<sellBeans> getSellList(sellBeans input) throws Exception;

    /**
     * 판매 주문을 업데이트합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int updateOrderStatusSell(orderBeans input) throws Exception;

    /**
     * 입찰대기중인 상품을 카운트합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<orderBeans> buyPayList(orderBeans input) throws Exception;

    /**
     * 입찰 대기중읜 상품의 리스트를 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int buyPayListCount(orderBeans input) throws Exception;

    /**
     * 판매자 입찰자의 상태를 검수하기위해 동시에 3으로 업데이트합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int updateOrderStatusBoth(orderBeans input) throws Exception;

    /**
     * 입금확인
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int updateOrderPayBuy(orderBeans input) throws Exception;

    /**
     * 송금해야하는 리스트를 카운트 합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int sellPayListCount(orderBeans input) throws Exception;

    /**
     * 송금해야하는 리스트를 리스트로 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<orderBeans> sellPayList(orderBeans input) throws Exception;

    /**
     * 송금완료로 업데이트를 합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int updateOrderSellBuy(orderBeans input) throws Exception;

    /**
     * 관리자의 수를 리턴
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int adminCount(userBeans input) throws Exception;

    /**
     * 관리자 리스트
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<userBeans> adminStatus(userBeans input) throws Exception;

    /**
     * 상품을 추가합니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int addProduct(productBeans input) throws Exception;

    /**
     * 상위 리뷰 5개를 선택해 카운트합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<reviewBeans> interestGraph() throws Exception;

    public List<recentCheck> recentFirst() throws Exception;

    public List<recentCheck> recentSecond() throws Exception;

    public List<recentCheck> recentThird() throws Exception;
}