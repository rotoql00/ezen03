/**
 * @filename : UserInfoService.java
 * @description : 회원정보 beans를 가져온  Service입니다.
 * @author : 최성준, 임석현
 */
package com.ezen03.tieshoe.service;

import java.util.List;

import com.ezen03.tieshoe.model.couponBeans;
import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.model.userReset;

public interface UserService {
    /**
     * 유저(회원) 데이터 상세 조회
     *
     * @param userBeans 조회할 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public userBeans getUserItem(userBeans input) throws Exception;

    /**
     * 유저(회원) 데이터 목록 조회
     *
     * @return 조회된 결과에 대한 컬렉션
     * @throws Exception
     */
    public List<userBeans> getUserList(userBeans input) throws Exception;

    /**
     * 유저(회원) 데이터 저장되어 있는 갯수 조회
     *
     * @return int
     * @throws Exception
     */
    public int getUserCount(userBeans input) throws Exception;

    /**
     * 유저(회원) 데이터 등록하기
     *
     * @param input 저장할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int addUser(userBeans input) throws Exception;

    /**
     * 유저(회원) 데이터 수정하기
     *
     * @param input 수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int editUser(userBeans input) throws Exception;

    /**
     * 유저(회원) 데이터 삭제하기
     *
     * @param input 삭제할 유저(회원)의 일련번호를 담고 있는 Beans
     * @throws Exception
     */
    public int deleteUser(userBeans input) throws Exception;

    /**
     * 유저(회원) 아이디 중복 검사
     *
     * @param input 회원가입시 입력하는 uesrId input value
     * @throws Exception
     */
    public int checkId(String userId) throws Exception;

    /**
     * 유저(회원) 핸드폰번호 중복 검사
     *
     * @param input 회원가입시 입력하는 uesrPhonenum input value
     * @throws Exception
     */
    public int checkPhonenum(String userPhonenum) throws Exception;

    /**
     * 유저(회원) 이메일 중복 검사
     *
     * @param input 회원가입시 입력하는 uesrEmail input value
     * @throws Exception
     */
    public int checkEmail(String userEmail) throws Exception;

    /**
     * 유저(회원) 아이디 중복 검사
     *
     * @param input 회원가입시 입력하는 uesrEmail input value
     * @throws Exception
     */
    public userBeans checkIDEamil(userBeans input) throws Exception;

    /**
     * 모든 재설정 타입 삽입
     *
     * @param input 삭제할 유저(회원)의 일련번호를 담고 있는 Beans
     * @throws Exception
     */
    public int insertKey(userReset input) throws Exception;

    /**
     * 모든 재설정 타입 삽입
     *
     * @param input 삭제할 유저(회원)의 일련번호를 담고 있는 Beans
     * @throws Exception
     */
    public userReset check(userReset input) throws Exception;

    /**
     * 유저(회원) 이메일 인증 권한 수정
     *
     * @param input
     * @throws Exception
     */
    public int emailAuth(userBeans input) throws Exception;

    /**
     * 유저(회원) 설문 데이터 등록하기
     *
     * @param input 저장할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int addUserForm(userBeans input) throws Exception;

    /**
     * 유저(회원) 비밀번호 수정하기
     *
     * @param input 수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int changeUserPw(userBeans input) throws Exception;

    /**
     * 유저(회원) 데이터 수정하기
     *
     * @param input 수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int updateBank(userBeans input) throws Exception;

    /**
     * 유저(회원) 탈퇴 등록하기
     *
     * @param input 저장할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int dropUser(userBeans input) throws Exception;

    /**
     * 회원의 배송쿠폰 가져오기
     *
     * @param userBeans 조회할 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public couponBeans checkCoupon(couponBeans input) throws Exception;

    /**
     * 회원의 배송쿠폰 가져오기
     *
     * @param userBeans 조회할 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public couponBeans checkCoupon2(couponBeans input) throws Exception;

    /**
     * 관리자를 삭제합니다.
     *
     * @param userBeans 조회할 유저(회원)의 일련번호를 담고 있는 beans
     * @return 조회된 데이터가 저장된 beans
     * @throws Exception
     */
    public int dropAdmin(userBeans input) throws Exception;

    /**
     * 유저(회원) 배송정보 수정하기
     *
     * @param input 수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int editUserAddr(userBeans input) throws Exception;

    /**
     * 쿠폰을 사용할때 업데이트 되는 구문
     *
     * @param input 수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int updateCoupon(couponBeans inputCoupon) throws Exception;

    /**
     * 해당 사용자의 이메일을 가져옵니다
     *
     * @param input 수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public userBeans getUserEmail(userBeans userInput) throws Exception;

    /**
     * 사용자 패널티를 통제합니다
     *
     * @param input 수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int terminate(userBeans userInput) throws Exception;

    /**
     * 모든 사용자의 수를 가져옵니다
     *
     * @param input 수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int countAllUsers() throws Exception;

    /**
     * 소셜로 회원가입한 회운을 저장합니다
     *
     * @param input 수정할 정보를 담고 있는 Beans
     * @throws Exception
     */
    public int addNaverUser(userBeans input) throws Exception;
    
    
    /**
     * 관리자 추가생성
     */
    public int addAdmin(userBeans input) throws Exception;
    
    /**
     * 인증메일 DB생성
     *
     * @param input 삭제할 유저(회원)의 일련번호를 담고 있는 Beans
     * @throws Exception
     */
    public int authEmailKey(userReset input) throws Exception;
}
