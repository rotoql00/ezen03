package com.ezen03.tieshoe.service.impl;

import com.ezen03.tieshoe.model.orderBeans;
import com.ezen03.tieshoe.model.productBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public productBeans getProduct(productBeans input) throws Exception {

        productBeans result = null;


        try {
            result = sqlSession.selectOne("productMapper.selectProduct", input);

            if (result == null) {
                throw new NullPointerException("result=null");
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("조회된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public orderBeans getRecent260(orderBeans input) throws Exception {
        orderBeans result = null;
        try {
            result = sqlSession.selectOne("orderMapper.getRecent260", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }


    @Override
    public orderBeans getRecent265(orderBeans input) throws Exception {
        orderBeans result = null;
        try {
            result = sqlSession.selectOne("orderMapper.getRecent265", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public orderBeans getRecent270(orderBeans input) throws Exception {
        orderBeans result = null;
        try {
            result = sqlSession.selectOne("orderMapper.getRecent270", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public orderBeans getRecentHigh(orderBeans input) throws Exception {
        orderBeans result = null;
        try {
            result = sqlSession.selectOne("orderMapper.getRecentHigh", input);
        } catch (Exception e) {

            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public orderBeans getRecentLow(orderBeans input) throws Exception {
        orderBeans result = null;
        try {
            result = sqlSession.selectOne("orderMapper.getRecentLow", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<productBeans> searchAll(productBeans input) throws Exception {
        List<productBeans> result = null;
        try {
            result = sqlSession.selectList("productMapper.search", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int searchAllCount(productBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("productMapper.searchCount", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

@Override
public List<productBeans> detailSearch(Map<String, Object> map) throws Exception {
    List<productBeans> result = null;
    try {
        result = sqlSession.selectList("productMapper.detailSearch", map);
        
    } catch (Exception e) {
        throw new Exception("데이터 조회에 실패했습니다.");
    }
    return result;
}

@Override
public int detailSearchCount(Map<String, Object> map) throws Exception {
    int result = 0;
    try {
        result = sqlSession.selectOne("productMapper.detailSearchCount", map);
    } catch (Exception e) {
        log.error(e.getLocalizedMessage());
        throw new Exception("데이터 조회에 실패했습니다.");
    }
    return result;
}

    @Override
    public productBeans getBuyProductInfo(productBeans input) throws Exception {
        productBeans result = null;
        try {
            result = sqlSession.selectOne("productMapper.getBuyProductInfo", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int dropProduct(productBeans input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.update("productMapper.dropProduct", input);
            if (result == 0) {
                throw new NullPointerException("result=0");
            }

        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("수정된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 수정에 실패했습니다.");
        }
        return result;
    }

    @Override
    public productBeans getRecentProduct(productBeans productInput) throws Exception {
        productBeans result = null;
        try {
            result = sqlSession.selectOne("productMapper.getRecentProduct", productInput);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int countAllProduct() throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("productMapper.countAllProduct");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }
}