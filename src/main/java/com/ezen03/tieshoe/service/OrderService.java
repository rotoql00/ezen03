package com.ezen03.tieshoe.service;

import com.ezen03.tieshoe.model.complexBeans;
import com.ezen03.tieshoe.model.orderBeans;
import com.ezen03.tieshoe.model.sellBeans;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    /**
     * 거래 완료 가격을 가져옵니다. (차트를 위한 Interface)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<orderBeans> getChartData(orderBeans input) throws Exception;

    /**
     * 주문한 유저 DB를 가져옵니다. (팝업창을 위한 Interface)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<orderBeans> getOrderInfo(orderBeans input) throws Exception;

    /**
     * 주문한 유저 DB를 가져옵니다. (팝업창을 위한 Interface)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int orderCount(orderBeans input) throws Exception;

    /**
     * 해당 상품에 낮은가격을 가져옵니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<complexBeans> buyButton(complexBeans input) throws Exception;

    /**
     * 주문건을 생성합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int createOrder(orderBeans orderInput) throws Exception;

    /**
     * 해당 주문 번호에 대한 주문정보를 불러옵니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public orderBeans selectOrder(orderBeans orderInput) throws Exception;

    /**
     * 해당 주문 번호에 상태를 철회로 업데이트합니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int orderTerminate(orderBeans orderInput) throws Exception;

    /**
     * 주문 카운트와 fee를 가져옵니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<orderBeans> countIncome() throws Exception;

    /**
     * 최근 주문건의 데이터 베이스를 가져옵니다 (그래프)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<orderBeans> getGraph(orderBeans input) throws Exception;


    /**
     * 주문건을 7로 업데이트 합니다.
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int complete(orderBeans input) throws Exception;

    /**
     * 일일 매출을 가져옵니다
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public List<orderBeans> dailyIncome() throws Exception;
}
