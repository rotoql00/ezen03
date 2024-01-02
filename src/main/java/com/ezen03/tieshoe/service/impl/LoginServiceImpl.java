/**
 * @filename : LoginServiceimpl.java
 * @description : Login Service를 가져와 상세 기능을 명시한 implement 입니다.
 * @author : 임채린
 */
package com.ezen03.tieshoe.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.model.userReset;
import com.ezen03.tieshoe.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * mybatis
     */
    @Autowired
    SqlSession sqlSession;

    /**
     * 로그인
     *
     * @param userBeans 로그인하는 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */

    @Override
    public userBeans login_ok(userBeans input) throws Exception {
        userBeans result = null;

        try {

            result = sqlSession.selectOne("loginMapper.login", input);

            if (result == null) {
                throw new NullPointerException("result=null");
            }

        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("아이디나 비밀번호가 잘못되었습니다. 아이디와 비밀번호를 확인해주세요.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("로그인에 실패했습니다.");
        }

        return result;

    }


    /**
     * 아이디 찾기
     *
     * @param userBeans 아이디 찾기 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */

    @Override
    public userBeans findID(userBeans input) throws Exception {
        userBeans result = null;

        try {
            result = sqlSession.selectOne("loginMapper.findID", input);

            if (result == null) {
                throw new NullPointerException("result=null");
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("이름이나 이메일주소가 잘못되었습니다. 이름과 이메일을 확인해주세요");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("아이디 찾기에 실패했습니다.");
        }

        return result;
    }


    /**
     * buy 거래 성사 체크
     *
     * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int checkBuyProcessY(int input) throws Exception {

        int result = 0;

        try {
            result = sqlSession.update("buyMapper.checkBuyProcessY", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 수정에 실패했습니다.");
        }

        return result;
    }


    /**
     * buy 거래 철회 체크
     *
     * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */

    public int checkBuyProcessA(int input) throws Exception {

        int result = 0;

        try {
            result = sqlSession.update("buyMapper.checkBuyProcessA", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 수정에 실패했습니다.");
        }

        return result;
    }


    /**
     * sell 거래 성사 체크
     *
     * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int checkSellProcessY(int input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("sellMapper.checkSellProcessY", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 수정에 실패했습니다.");
        }
        return result;
    }


    /**
     * sell 거래 철회 체크
     *
     * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int checkSellProcessA(int input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("sellMapper.checkSellProcessA", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 수정에 실패했습니다.");
        }
        return result;
    }


    /**
     * @param userBeans 로그인 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public userReset checkUserAuthEmail(int input) throws Exception {

        userReset result = null;

        try {

            result = sqlSession.selectOne("resetMapper.checkUserAuthEmail", input);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public int sociaLogin_ok(userBeans userInput) throws Exception {
        int result = 0;
        try {
            result = sqlSession.selectOne("userMapper.checkSocial", userInput);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

}