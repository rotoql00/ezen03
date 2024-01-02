package com.ezen03.tieshoe.service.impl;

import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.complexBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.service.BuyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class buyServiceImpl implements BuyService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public buyBeans getHighPrice260(buyBeans input) throws Exception {
        buyBeans result = null;

        try {
            result = sqlSession.selectOne("buyMapper.selectHighest260", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public buyBeans getHighPrice265(buyBeans input) throws Exception {
        buyBeans result = null;

        try {
            result = sqlSession.selectOne("buyMapper.selectHighest265", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public buyBeans getHighPrice270(buyBeans input) throws Exception {
        buyBeans result = null;

        try {
            result = sqlSession.selectOne("buyMapper.selectHighest270", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<buyBeans> getBuyAllPrice(buyBeans input) throws Exception {
        List<buyBeans> result = null;

        try {
            result = sqlSession.selectList("buyMapper.selectBuyAllPrice", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int getBuyCount(buyBeans input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.selectOne("buyMapper.selectCountAll", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public int getBuyListCount(buyBeans input) throws Exception {
        int result = 0;

        result = sqlSession.selectOne("buyMapper.selectListCount", input);

        return result;
    }

    @Override
    public int getBuyProcessListCount(buyBeans input) throws Exception {
        int result = 0;

        result = sqlSession.selectOne("buyMapper.selectProcessListCount", input);

        return result;
    }

    @Override
    public List<complexBeans> buyButton(complexBeans input) throws Exception {
        List<complexBeans> result = null;
        try {
            result = sqlSession.selectList("complexMapper.buyButtonBuy", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public complexBeans highPrice(complexBeans input) throws Exception {
        complexBeans result = null;

        try {
            //최고 입찰가
            result = sqlSession.selectOne("complexMapper.buyPageHigh", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public complexBeans lowPrice(complexBeans input) throws Exception {
        complexBeans result = null;

        try {
            //최고 입찰가
            result = sqlSession.selectOne("complexMapper.buyPageLow", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int addBuy(buyBeans inputBuy) throws Exception {
        int result = 0;

        try {
            result = sqlSession.insert("buyMapper.addBuy", inputBuy);
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
    public buyBeans selectOne(buyBeans finalResult) throws Exception {
        buyBeans result = null;

        try {
            result = sqlSession.selectOne("buyMapper.selectOne", finalResult);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int updateBuy(buyBeans inputBuy) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("buyMapper.updateBuyStatus", inputBuy);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public buyBeans check(buyBeans check) throws Exception {
        buyBeans result = null;

        try {
            result = sqlSession.selectOne("buyMapper.check", check);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int updateBuyPrice(buyBeans inputBuy) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("buyMapper.updateBuyPrice", inputBuy);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public int deleteBuy(buyBeans input) throws Exception {
        int result = 0;
        try {
            result = sqlSession.delete("buyMapper.deleteBuy", input);
        } catch (Exception e) {
            throw new Exception("데이터 삭제에 실패했습니다");
        }
        return result;
    }

    @Override
    public int buyTerminate(buyBeans buyInput) throws Exception {
        int result = 0;
        try {
            result = sqlSession.update("buyMapper.terminate", buyInput);
        } catch (Exception e) {
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
	public int buyUserCount(buyBeans input) throws Exception {
		int result = 0;
        try {
            result = sqlSession.selectOne("buyMapper.buyUserCount", input);
        } catch (Exception e) {
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        return result;
	}
}
