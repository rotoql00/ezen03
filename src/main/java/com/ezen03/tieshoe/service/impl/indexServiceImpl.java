package com.ezen03.tieshoe.service.impl;

import com.ezen03.tieshoe.model.*;
import com.ezen03.tieshoe.service.indexService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class indexServiceImpl implements indexService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<productBeans> getAdidas(productBeans input) throws Exception {
        List<productBeans> result = null;

        try {
            result = sqlSession.selectList("productMapper.getAdidas", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<productBeans> getNike(productBeans input) throws Exception {
        List<productBeans> result = null;

        try {
            result = sqlSession.selectList("productMapper.getNike", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<productBeans> getJordan(productBeans input) throws Exception {
        List<productBeans> result = null;

        try {
            result = sqlSession.selectList("productMapper.getJordan", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<productBeans> getConverse(productBeans input) throws Exception {
        List<productBeans> result = null;

        try {
            result = sqlSession.selectList("productMapper.getConverse", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<productBeans> newArrival(productBeans input) throws Exception {
        List<productBeans> result = null;

        try {
            result = sqlSession.selectList("productMapper.newArrival", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<productBeans> bestProduct(productBeans input) throws Exception {
        List<productBeans> result = null;

        try {
            result = sqlSession.selectList("productMapper.bestProduct", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<productBeans> getPopular(List<String> codeList) throws Exception {
        List<productBeans> result = null;

        try {
            result = sqlSession.selectList("productMapper.getPopular", codeList);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public popularBeans getPopularRank(popularBeans input) throws Exception {
        popularBeans result = null;

        try {
            result = sqlSession.selectOne("popularMapper.getList", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");

        }
        return result;
    }

    @Override
    public userBeans getSurvey(userBeans input) throws Exception {
        userBeans result = null;

        try {
            result = sqlSession.selectOne("userMapper.getSurvey", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");

        }
        return result;
    }

    @Override
    public List<productBeans> getMdPick(List<String> codeList) throws Exception {
        List<productBeans> result = null;

        try {
            result = sqlSession.selectList("productMapper.getMdPick", codeList);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public rankBeans rank(rankBeans input) throws Exception {
        rankBeans result = null;

        try {
            result = sqlSession.selectOne("rankMapper.getRank", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");

        }
        return result;
    }
}
