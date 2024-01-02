package com.ezen03.tieshoe.service.impl;

import com.ezen03.tieshoe.model.visitBeans;
import com.ezen03.tieshoe.service.visitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VisitServiceImpl implements visitService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public int putVisit(visitBeans input) throws Exception {
        int result = 0;
        log.info(String.valueOf(input));
        try {
            result = sqlSession.insert("visitMapper.addVisitLog", input);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }
}
