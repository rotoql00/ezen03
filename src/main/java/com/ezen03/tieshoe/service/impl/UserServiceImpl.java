/**
 * @filename : UserInfoService.java
 * @description : User Service를 가져와 상세 기능을 명시한 implement 입니다.
 * @author : 최성준, 임석현
 */
package com.ezen03.tieshoe.service.impl;

import java.util.List;

import com.ezen03.tieshoe.model.couponBeans;
import com.ezen03.tieshoe.model.userReset;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	/**
	 * mybatis
	 */
	@Autowired
	SqlSession sqlSession;

	/**
	 * 유저(회원) 데이터 상세 조회
	 *
	 * @param input 조회할 유저(회원)의 일련번호를 담고 있는 beans
	 * @return 조회된 데이터가 저장된 beans
	 * @throws Exception
	 */
	@Override
	public userBeans getUserItem(userBeans input) throws Exception {
		userBeans result = null;

		try {
			result = sqlSession.selectOne("userMapper.selectItem", input);

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
	 * 유저(회원) 데이터 목록 조회
	 *
	 * @return 조회된 결과에 대한 컬렉션
	 * @throws Exception
	 */
	@Override
	public List<userBeans> getUserList(userBeans input) throws Exception {
		List<userBeans> result = null;

		try {
			result = sqlSession.selectList("userMapper.selectList", input);

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
	 * 유저(회원) 데이터 저장되어 있는 갯수 조회
	 *
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getUserCount(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("userMapper.selectCountAll", input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	/**
	 * 유저(회원) 데이터 등록하기
	 *
	 * @param input 저장할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	@Override
	public int addUser(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.insert("userMapper.insertItem", input);
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
	 * 유저(회원) 데이터 수정하기
	 *
	 * @param input 수정할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	@Override
	public int editUser(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("userMapper.updateItem", input);
			if (result == 0) {
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
	 * 유저(회원) 데이터 삭제하기
	 *
	 * @param input 삭제할 유저(회원)의 일련번호를 담고 있는 Beans
	 * @throws Exception
	 */
	@Override
	public int deleteUser(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.delete("userMapper.deleteItem", input);
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
	public int checkId(String userId) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("userMapper.checkId", userId);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public userBeans checkIDEamil(userBeans input) throws Exception {
		userBeans result = null;

		try {
			result = sqlSession.selectOne("userMapper.checkIDEmail", input);

		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public int insertKey(userReset input) throws Exception {
		int result = 0;

		try {
			// 값이 있을수 있으니 업데이트를 먼저 진행합니다.
			result = sqlSession.update("resetMapper.updateKey", input);

			if (result == 0) {
				// 값이 없다면 데이터를 삽입합니다.
				result = sqlSession.insert("resetMapper.insertKey", input);
			}

			// 그래도 값이 없다면 에러 메세지를 호출합니다.
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
	public userReset check(userReset input) throws Exception {
		userReset result = null;

		try {
			result = sqlSession.selectOne("resetMapper.check", input);
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
	public int checkPhonenum(String userPhonenum) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("userMapper.checkPhonenum", userPhonenum);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;

	}

	@Override
	public int checkEmail(String userEmail) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("userMapper.checkEmail", userEmail);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;

	}

	@Override
	public int emailAuth(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("userMapper.emailAuth", input);
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
	 * 유저(회원) 설문 데이터 등록하기
	 *
	 * @param input 저장할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	@Override
	public int addUserForm(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.insert("userMapper.insertUserForm", input);
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
	 * 유저(회원) 비밀번호 수정하기
	 *
	 * @param input 수정할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	@Override
	public int changeUserPw(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("userMapper.changeUserPw", input);
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
	public int updateBank(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("userMapper.updateBank", input);
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
	public int dropUser(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("userMapper.dropUser", input);
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
	public couponBeans checkCoupon(couponBeans input) throws Exception {
		couponBeans result = null;
		try {
			result = sqlSession.selectOne("couponMapper.getShipping", input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		return result;
	}

	@Override
	public couponBeans checkCoupon2(couponBeans input) throws Exception {
		couponBeans result = null;
		try {
			result = sqlSession.selectOne("couponMapper.getDec", input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		return result;
	}

	@Override
	public int dropAdmin(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.delete("userMapper.dropAdmin", input);

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
	public int editUserAddr(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("userMapper.updateAddr", input);
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
	public int updateCoupon(couponBeans inputCoupon) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("couponMapper.useCoupon", inputCoupon);
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
	public userBeans getUserEmail(userBeans userInput) throws Exception {
		userBeans result = null;

		try {
			result = sqlSession.selectOne("userMapper.getEmail", userInput);
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
	public int terminate(userBeans userInput) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("userMapper.terminate", userInput);
			if (result == 0) {
				throw new NullPointerException("result=0");
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 수정에 실패했습니다.");
		}
		return result;
	}

	@Override
	public int countAllUsers() throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("userMapper.countAllUser");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터를 불러오지 못했습니다");
		}
		return result;
	}

	@Override
	public int addNaverUser(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.insert("userMapper.addSocialUser", input);
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
	public int addAdmin(userBeans input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.insert("userMapper.addAdmin", input);
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
	public int authEmailKey(userReset input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update("resetMapper.successEmailAuth", input);
			if (result == 0) {
				result = sqlSession.insert("resetMapper.authEmailKey", input);
			} else if (result == 0) {
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
}
