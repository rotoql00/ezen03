package com.ezen03.tieshoe.service.impl;

import com.ezen03.tieshoe.model.recentCheck;
import com.ezen03.tieshoe.model.reviewBeans;
import com.ezen03.tieshoe.service.checkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class checkServiceImpl implements checkService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public recentCheck getCheck(recentCheck checkInput) throws Exception {
        recentCheck result = null;

        try {
            result = sqlSession.selectOne("recentCheckMapper.getCheck", checkInput);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int update(recentCheck checkInput) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("recentCheckMapper.updateCheck", checkInput);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int insert(recentCheck checkInput) throws Exception {
        int result = 0;
        try {
            result = sqlSession.insert("recentCheckMapper.insertCheck", checkInput);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }
}
