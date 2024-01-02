/**
 * @filename : UserInfoService.java
 * @description : User Service를 가져와 상세 기능을 명시한 implement 입니다.
 * @author : 최성준, 임석현
 */
package com.ezen03.tieshoe.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen03.tieshoe.model.boardBeans;
import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	/**
     * mybatis
     */
    @Autowired
    SqlSession sqlSession;
	
	@Override
	public boardBeans getBoardItem(boardBeans input) throws Exception {
		boardBeans result = null;

        try {
            result = sqlSession.selectOne("boardMapper.selectItem", input);

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
	public List<boardBeans> getBoardList(boardBeans input) throws Exception {
		 List<boardBeans> result = null;

	        try {
	            result = sqlSession.selectList("boardMapper.selectList", input);

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
	public int getBoardCount(boardBeans input) throws Exception {
		 int result = 0;

	        try {
	            result = sqlSession.selectOne("boardMapper.selectCountAll", input);
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 조회에 실패했습니다.");
	        }

	        return result;
	}

	@Override
	public int addBoard(boardBeans input) throws Exception {
		int result = 0;

        try {
            result = sqlSession.insert("boardMapper.insertItem", input);
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
	public int editBoard(boardBeans input) throws Exception {
		int result = 0;

        try {
            result = sqlSession.update("boardMapper.updateItem", input);
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
	public int deleteBoard(boardBeans input) throws Exception {
		 int result = 0;

	        try {
	            result = sqlSession.delete("boardMapper.deleteItem", input);
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

}

