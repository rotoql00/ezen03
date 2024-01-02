package com.ezen03.tieshoe.service.impl;

import com.ezen03.tieshoe.model.*;
import com.ezen03.tieshoe.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public int setPopular(popularBeans input) throws Exception {

        int result = 0;

        try {
            result = sqlSession.insert("popularMapper.setList", input);
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
    public int setRank(rankBeans input) throws Exception {

        int result = 0;

        try {
            result = sqlSession.insert("rankMapper.setRank", input);
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
    public List<productBeans> getProductCode(productBeans input) throws Exception {

        List<productBeans> result = null;
        try {
            result = sqlSession.selectList("productMapper.selectList", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;


    }

    @Override
    public List<orderBeans> orderStatus(orderBeans input) throws Exception {
        List<orderBeans> result = null;
        try {
            result = sqlSession.selectList("orderMapper.adminAll", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int orderStatusCount(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("orderMapper.adminAllCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int orderUpdate(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("orderMapper.orderUpdate", input);
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
    public int userCount(userBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("userMapper.userCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<userBeans> userStatus(userBeans input) throws Exception {
        List<userBeans> result = null;
        try {
            result = sqlSession.selectList("userMapper.userAll", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int productListCount(productBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("productMapper.productListCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<productBeans> getProductList(productBeans input) throws Exception {
        List<productBeans> result = null;
        try {
            result = sqlSession.selectList("productMapper.productList", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int buyListCount(buyBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("buyMapper.buyListCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<buyBeans> getBuyList(buyBeans input) throws Exception {
        List<buyBeans> result = null;
        try {
            result = sqlSession.selectList("buyMapper.buyList", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int updateOrderStatus(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("orderMapper.updateOrderStatus", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int sellListCount(sellBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("sellMapper.sellListCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<sellBeans> getSellList(sellBeans input) throws Exception {
        List<sellBeans> result = null;
        try {
            result = sqlSession.selectList("sellMapper.sellList", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int updateOrderStatusSell(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("orderMapper.updateOrderStatusSell", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<orderBeans> buyPayList(orderBeans input) throws Exception {
        List<orderBeans> result = null;
        try {
            result = sqlSession.selectList("orderMapper.buyPayList", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int buyPayListCount(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("orderMapper.buyPayListCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int updateOrderStatusBoth(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("orderMapper.updateOrderBoth", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int updateOrderPayBuy(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("orderMapper.updateOrderPayBuy", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int sellPayListCount(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("orderMapper.sellPayListCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<orderBeans> sellPayList(orderBeans input) throws Exception {
        List<orderBeans> result = null;
        try {
            result = sqlSession.selectList("orderMapper.sellPayList", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int updateOrderSellBuy(orderBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("orderMapper.updateOrderPaySell", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int adminCount(userBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("userMapper.adminCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<userBeans> adminStatus(userBeans input) throws Exception {
        List<userBeans> result = null;
        try {
            result = sqlSession.selectList("userMapper.adminAll", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int addProduct(productBeans input) throws Exception {

        int result = 0;

        try {
            result = sqlSession.insert("productMapper.addProduct", input);
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
    public List<reviewBeans> interestGraph() throws Exception {
        List<reviewBeans> result = null;
        try {
            result = sqlSession.selectList("reviewMapper.interestGraph");
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<recentCheck> recentFirst() throws Exception {
        List<recentCheck> result = null;
        try {
            result = sqlSession.selectList("recentCheckMapper.first");
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<recentCheck> recentSecond() throws Exception {
        List<recentCheck> result = null;
        try {
            result = sqlSession.selectList("recentCheckMapper.second");
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<recentCheck> recentThird() throws Exception {
        List<recentCheck> result = null;
        try {
            result = sqlSession.selectList("recentCheckMapper.third");
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }
}
