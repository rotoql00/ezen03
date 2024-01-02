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

import com.ezen03.tieshoe.model.reviewBeans;
import com.ezen03.tieshoe.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	SqlSession sqlSession;

	/**
	 * 유저가 작성한 상품에 대한 리뷰 상세 조회
	 *
	 * @param input 조회할 리뷰의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	@Override
	public reviewBeans getReviewItem(reviewBeans input) throws Exception {
		reviewBeans result = null;

		try {
			result = sqlSession.selectOne("reviewMapper.selectReview", input);

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
	
	
	/**
	 * 제품에 작성된 리뷰 중복 조회
	 *
	 * @param input 조회할 리뷰의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	@Override
	public int getCheckReview(reviewBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("reviewMapper.checkReview", input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	

	/**
	 * 작성된 리뷰 목록 조회
	 *
	 * @return 조회된 결과에 대한 컬렉션
	 * @throws Exception
	 */
	@Override
	public List<reviewBeans> getReviewList(reviewBeans input) throws Exception {
		List<reviewBeans> result = null;

		try {
			result = sqlSession.selectList("reviewMapper.selectListReview", input);

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
	

	/**
	 * 유저 기준 자신이 작성한 리뷰 모아 보기
	 *
	 * @return 조회된 결과에 대한 컬렉션
	 * @throws Exception
	 */
	@Override
	public List<reviewBeans> getReviewUserList(reviewBeans input) throws Exception {
		List<reviewBeans> result = null;

		try {
			result = sqlSession.selectList("reviewMapper.selectListUserReview", input);

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
	

	/**
	 * 상품에 작성된 리뷰 모아 보기
	 *
	 * @return 조회된 결과에 대한 컬렉션
	 * @throws Exception
	 */
	@Override
	public List<reviewBeans> getReviewProductList(reviewBeans input) throws Exception {
		List<reviewBeans> result = null;

		try {
			result = sqlSession.selectList("reviewMapper.selectListProductReview", input);

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
	

	/**
	 * 전체 리뷰 갯수 조회
	 *
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getReviewAllCount(reviewBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("reviewMapper.countReviewAll", input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	

	/**
	 * 유저가 작성한 리뷰 갯수 조회
	 *
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getReviewUserCount(reviewBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("reviewMapper.countUserReview", input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	

	/**
	 * 상품에 작성된 리뷰 갯수 조회
	 *
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getReviewProductCount(reviewBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("reviewMapper.countProductReview", input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	
	
	/**
	 * 상품에 대해 유저의 리뷰 작성 여부 조회
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getOverlapCheckReview(reviewBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("reviewMapper.reviewOverlapCheck", input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	
	
	/**
	 * 상품에 등록된 리뷰의 총점 조회
	 *
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getSumPointProduct(reviewBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("reviewMapper.productAvgPoint", input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	

	/**
	 * 리뷰 등록하기
	 *
	 * @param input 저장할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	@Override
	public int addReview(reviewBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.insert("reviewMapper.insertReview", input);
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
	

	/**
	 * 리뷰 수정하기
	 *
	 * @param input 수정할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	@Override
	public int editReview(reviewBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("reviewMapper.updateReview", input);
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
	

	/**
	 * 리뷰 삭제하기
	 *
	 * @param input 삭제할 리뷰의 일련번호를 담고 있는 Beans
	 * @throws Exception
	 */
	@Override
	public int deleteReview(reviewBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.delete("reviewMapper.deleteReview", input);
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