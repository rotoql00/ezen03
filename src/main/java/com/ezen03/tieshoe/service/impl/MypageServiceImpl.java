package com.ezen03.tieshoe.service.impl;

import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class MypageServiceImpl implements MypageService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public userBeans getbuyInfo(userBeans input) throws Exception {
        userBeans result = null;

        try {
            result = sqlSession.selectOne("userMapper.getBuyInfo", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");

        }
        return result;
    }

    @Override
    public List<buyBeans> getBuyList(buyBeans input) throws Exception {
        List<buyBeans> result = null;
        try {
            result = sqlSession.selectList("buyMapper.getBuyList", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<buyBeans> getBuyProcessList(buyBeans input) throws Exception {
        List<buyBeans> result = null;
        try {
            result = sqlSession.selectList("buyMapper.getBuyProcessList", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<sellBeans> getSellProcessList(sellBeans input) throws Exception {
        List<sellBeans> result = null;
        try {
            result = sqlSession.selectList("sellMapper.getSellProcessList", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public userBeans getSellInfo(userBeans input) throws Exception {
        userBeans result = null;

        try {
            result = sqlSession.selectOne("userMapper.getSellInfo", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");

        }
        return result;
    }

    @Override
    public List<sellBeans> getSellList(sellBeans input) throws Exception {
        List<sellBeans> result = null;
        try {
            result = sqlSession.selectList("sellMapper.getsellList", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }
}
