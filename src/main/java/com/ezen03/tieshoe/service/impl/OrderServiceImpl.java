package com.ezen03.tieshoe.service.impl;

import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.complexBeans;
import com.ezen03.tieshoe.model.orderBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<orderBeans> getChartData(orderBeans input) throws Exception {
        List<orderBeans> result = null;

        try {
            result = sqlSession.selectList("orderMapper.getChartData", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<orderBeans> getOrderInfo(orderBeans input) throws Exception {
        List<orderBeans> result = null;

        try {
            result = sqlSession.selectList("orderMapper.getOrderAllPrice", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int orderCount(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("orderMapper.selectCountAll", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<complexBeans> buyButton(complexBeans input) throws Exception {
        List<complexBeans> result = null;
        try {
            result = sqlSession.selectList("complexMapper.buyButtonOrder", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int createOrder(orderBeans orderInput) throws Exception {
        int result = 0;

        try {
            result = sqlSession.insert("orderMapper.insertOrder", orderInput);
            if (result == 0) {
                throw new NullPointerException("result=0");
            }

        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("저장된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 저장에 실패했습니다.");
        }

        return result;
    }

    @Override
    public orderBeans selectOrder(orderBeans orderInput) throws Exception {
        orderBeans result = null;

        try {
            result = sqlSession.selectOne("orderMapper.selectOrder", orderInput);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int orderTerminate(orderBeans orderInput) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("orderMapper.terminate", orderInput);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 수정에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<orderBeans> countIncome() throws Exception {
        List<orderBeans> result = null;
        try {
            result = sqlSession.selectList("orderMapper.getAllOrderInfo");
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<orderBeans> getGraph(orderBeans input) throws Exception {
        List<orderBeans> result = null;
        try {
            result = sqlSession.selectList("orderMapper.getGraphData", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int complete(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("orderMapper.complete", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 수정에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<orderBeans> dailyIncome() throws Exception {
        List<orderBeans> result = null;
        try {
            result = sqlSession.selectList("orderMapper.dailyIncome");
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }
}
