package com.ezen03.tieshoe.service.impl;

import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.complexBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.service.SellService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SellServiceImpl implements SellService {

    /**
     * MyBatis
     */
    // --> import org.apache.ibatis.session.SqlSession
    @Autowired

    SqlSession sqlSession;


    @Override
    public sellBeans getALLHighPrice(sellBeans input) throws Exception {
        sellBeans result = null;
        try {
            result = sqlSession.selectOne("sellMapper.selectAllHighPrice", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public sellBeans getALLLowPrice(sellBeans input) throws Exception {
        sellBeans result = null;

        try {
            result = sqlSession.selectOne("sellMapper.selectAllLowPrice", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public sellBeans getLowPrice260(sellBeans input) throws Exception {
        sellBeans result = null;

        try {
            result = sqlSession.selectOne("sellMapper.selectLowest260", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public sellBeans getLowPrice265(sellBeans input) throws Exception {
        sellBeans result = null;

        try {
            result = sqlSession.selectOne("sellMapper.selectLowest265", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public sellBeans getLowPrice270(sellBeans input) throws Exception {
        sellBeans result = null;

        try {
            result = sqlSession.selectOne("sellMapper.selectLowest270", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public sellBeans getRecentPrice(sellBeans input) throws Exception {
        sellBeans result = null;
        try {
            result = sqlSession.selectOne("sellMapper.selectRecentPrice", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public List<sellBeans> getSellAllPrice(sellBeans input) throws Exception {
        List<sellBeans> result = null;

        try {
            result = sqlSession.selectList("sellMapper.selectSellAllPrice", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int getSellCount(sellBeans input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.selectOne("sellMapper.selectCountAll", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public sellBeans getSellInfo(sellBeans input) throws Exception {
        sellBeans result = null;
        try {
            result = sqlSession.selectOne("sellMapper.selectOne", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public int getSellListCount(sellBeans input) throws Exception {
        int result = 0;

        result = sqlSession.selectOne("sellMapper.selectListCount", input);

        return result;
    }

    @Override
    public int getSellProcessListCount(sellBeans input) throws Exception {
        int result = 0;

        result = sqlSession.selectOne("sellMapper.selectProcessListCount", input);

        return result;
    }

    @Override
    public List<complexBeans> buyButton(complexBeans input) throws Exception {
        List<complexBeans> result = null;
        try {
            result = sqlSession.selectList("complexMapper.buyButtonSell", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int updateSell(sellBeans inputSell) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("sellMapper.updateSellStatus", inputSell);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int addSell(sellBeans inputSell) throws Exception {
        int result = 0;

        try {
            result = sqlSession.insert("sellMapper.addSell", inputSell);
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
    public complexBeans highPrice(complexBeans input) throws Exception {
        complexBeans result = null;

        try {
            //최고 입찰가
            result = sqlSession.selectOne("complexMapper.sellPageHigh", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public sellBeans check(sellBeans check) throws Exception {
        sellBeans result = null;
        try {
            result = sqlSession.selectOne("sellMapper.check", check);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public int updateSellPrice(sellBeans inputSell) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("sellMapper.updateSellPrice", inputSell);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int deleteBuy(sellBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.delete("sellMapper.deleteSell", input);
        } catch (Exception e) {
            throw new Exception("데이터 삭제에 실패했습니다");
        }
        return result;
    }

    @Override
    public int sellTerminate(sellBeans sellInput) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("sellMapper.terminate", sellInput);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public complexBeans lowPrice(complexBeans input) throws Exception {
        complexBeans result = null;

        try {
            //최고 입찰가
            result = sqlSession.selectOne("complexMapper.sellPageLow", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    /**
     * 유저의 구매 거래 여부를 확인합니다.(유저 삭제를 위한 기능)
     *
     * @param input 상품의 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
	@Override
	public int sellUserCount(sellBeans input) throws Exception {
		int result = 0;
        try {
            result = sqlSession.selectOne("sellMapper.sellUserCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
	}


}
