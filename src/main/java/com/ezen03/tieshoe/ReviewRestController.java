package com.ezen03.tieshoe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezen03.tieshoe.helper.MailHelper;
import com.ezen03.tieshoe.helper.PageData;
import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.RetrofitHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.model.reviewBeans;
import com.ezen03.tieshoe.service.AdminService;
import com.ezen03.tieshoe.service.MypageService;
import com.ezen03.tieshoe.service.ProductService;
import com.ezen03.tieshoe.service.ReviewService;
import com.ezen03.tieshoe.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReviewRestController {

    @Autowired
    WebHelper webHelper;

    @Autowired
    RegexHelper regexHelper;

    @Autowired
    MailHelper mailHelper;

    @Autowired
    RetrofitHelper retrofitHelper;

    @Autowired
    ProductService productService;
    
    @Autowired
    UserService userService;

    @Autowired
    MypageService mypageService;

    @Autowired
    AdminService adminService;

    @Autowired
    ReviewService reviewService;


    @Value("#{servletContext.contextPath}")
    String contextPath;
    
    /** 전체 리뷰 목록 페이지 */
    @RequestMapping(value = "/review/all", method = RequestMethod.GET)
    public Map<String, Object> get_allReviewList(
    		@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,	// 검색어
    		@RequestParam(value = "page", defaultValue = "1", required = false) int nowPage,	// 페이지 번호 (기본값 1)
    		@RequestParam(value = "totalCount", defaultValue = "0", required = false) int totalCount,	// 전체 게시글수 
    		@RequestParam(value = "listCount", defaultValue = "5", required = false) int listCount,	//한 페이지당 표시할 목록수 
    		@RequestParam(value = "pageCount", defaultValue = "5", required = false) int pageCount,	// 한그룹당 표시할 페이지 번호수
    		@RequestParam(value = "reviewNum", defaultValue = "0", required = false) int reviewNum
    		) {
      
        /** 2) 데이터 조회하기 */
        // 조회에 필요한 조건값(검색어)를 Beans에 담는다.
        reviewBeans input = new reviewBeans();
        input.setReviewNum(reviewNum);

        List<reviewBeans> output = null; // 조회결과가 저장될 객체
        PageData pageData = null;       // 페이지 번호를 계산한 결과가 저장될 객체

        try {
            // 전체 게시글 수 조회
            totalCount = reviewService.getReviewAllCount(input);
            // 페이지 번호 계산 --> 계산결과를 로그로 출력될 것이다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            reviewBeans.setOffset(pageData.getOffset());
            reviewBeans.setListCount(pageData.getListCount());
            
            // 데이터 조회하기
            output = reviewService.getReviewList(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        /** 3) JSON 출력하기 */
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("keyword", keyword);
        data.put("reviewOPT", output);
        data.put("meta", pageData);

        return webHelper.getJsonData(data);
    }
    
    /** 유저 리뷰 목록 페이지 */
    @RequestMapping(value = "/review/user", method = RequestMethod.GET)
    public Map<String, Object> get_userReviewList(
    		@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,	// 검색어
    		@RequestParam(value = "page", defaultValue = "1", required = false) int nowPage,	// 페이지 번호 (기본값 1)
    		@RequestParam(value = "totalCount", defaultValue = "0", required = false) int totalCount,	// 전체 게시글수 
    		@RequestParam(value = "listCount", defaultValue = "5", required = false) int listCount,	//한 페이지당 표시할 목록수 
    		@RequestParam(value = "pageCount", defaultValue = "5", required = false) int pageCount,	// 한그룹당 표시할 페이지 번호수
    		@RequestParam(value = "user_ID", defaultValue = "0", required = false) int user_ID
    		) {
      
        /** 2) 데이터 조회하기 */
        // 조회에 필요한 조건값(검색어)를 Beans에 담는다.
        reviewBeans input = new reviewBeans();
        input.setUser_ID(user_ID);

        List<reviewBeans> output = null; // 조회결과가 저장될 객체
        PageData pageData = null;       // 페이지 번호를 계산한 결과가 저장될 객체

        try {
            // 전체 게시글 수 조회
            totalCount = reviewService.getReviewUserCount(input);
            // 페이지 번호 계산 --> 계산결과를 로그로 출력될 것이다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            reviewBeans.setOffset(pageData.getOffset());
            reviewBeans.setListCount(pageData.getListCount());
            
            // 데이터 조회하기
            output = reviewService.getReviewUserList(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        /** 3) JSON 출력하기 */
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("keyword", keyword);
        data.put("reviewOPT", output);
        data.put("meta", pageData);

        return webHelper.getJsonData(data);
    }
    
    /** 상품 리뷰 목록 페이지 */
    @RequestMapping(value = "/review/product", method = RequestMethod.GET)
    public Map<String, Object> get_productReviewList(
    		@RequestParam(value = "page", defaultValue = "1", required = false) int nowPage,	// 페이지 번호 (기본값 1)
    		@RequestParam(value = "totalCount", defaultValue = "0", required = false) int totalCount,	// 전체 게시글수 
    		@RequestParam(value = "listCount", defaultValue = "5", required = false) int listCount,	//한 페이지당 표시할 목록수 
    		@RequestParam(value = "pageCount", defaultValue = "5", required = false) int pageCount,	// 한그룹당 표시할 페이지 번호수
    		@RequestParam(value = "product_ID", defaultValue = "0", required = false) int product_ID
    		) {
      
        /** 2) 데이터 조회하기 */
        // 조회에 필요한 조건값(검색어)를 Beans에 담는다.
        reviewBeans input = new reviewBeans();
        input.setProduct_ID(product_ID);

        List<reviewBeans> output = null; // 조회결과가 저장될 객체
        PageData pageData = null;       // 페이지 번호를 계산한 결과가 저장될 객체

        try {
            // 전체 게시글 수 조회
            totalCount = reviewService.getReviewProductCount(input);
            // 페이지 번호 계산 --> 계산결과를 로그로 출력될 것이다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            reviewBeans.setOffset(pageData.getOffset());
            reviewBeans.setListCount(pageData.getListCount());
            
            // 데이터 조회하기
            output = reviewService.getReviewProductList(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        /** 3) JSON 출력하기 */
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("reviewOPT", output);
        data.put("meta", pageData);

        return webHelper.getJsonData(data);
    }
    
    
    /** 리뷰 상세 조회 */
    @RequestMapping(value = "/review/{reviewNum}", method = RequestMethod.GET)
    public Map<String, Object> get_item(@PathVariable("reviewNum") int reviewNum) {
        /** 1) 필요한 변수값 생성 */
        // 조회할 대상에 대한 PK값
        //int profno = webHelper.getInt("profno");

        // 이 값이 존재하지 않는다면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (reviewNum == 0) {
            return webHelper.getJsonWarning("교수번호가 없습니다.");
        }

        /** 2) 데이터 조회하기 */
        // 데이터 조회에 필요한 조건값을 Beans에 저장하기
        reviewBeans input = new reviewBeans();
        input.setReviewNum(reviewNum);

        // 조회결과를 저장할 객체 선언
        reviewBeans output = null;

        try {
            // 데이터 조회
            output = reviewService.getReviewItem(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }
        
        /** 3) JSON 출력하기 */
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("reviewOPT", output);

        return webHelper.getJsonData(data);
    }
    
    /** 리뷰 입력 */
    @RequestMapping(value="/review/add", method = RequestMethod.POST)
    public Map<String,Object> reviewAdd (
    		@RequestParam(value = "reviewRating", defaultValue = "5", required = false) float reviewRating,
    		@RequestParam String reviewContext,
    		@RequestParam(value = "user_ID", defaultValue = "0", required = false) int user_ID,
    		@RequestParam(value = "reg_date", required = false) String reg_date,
    		@RequestParam(value = "edit_date", required = false) String edit_date,
    		@RequestParam(value = "product_ID", defaultValue = "0", required = false) int product_ID
    		) {
    	
    	if ( reviewContext == "") {
    		return webHelper.getJsonWarning("리뷰 글을 작성해주세요.");
    	}
    	
    	if ( user_ID == 0) {
    		return webHelper.getJsonWarning("리뷰를 작성하시려면 로그인 해주세요.");
    	}
    	
    	if ( product_ID == 0) {
    		return webHelper.getJsonWarning("리뷰를 작성할 제품을 선택해주세요.");
    	}
    	
    	reviewBeans input = new reviewBeans();
    	input.setReviewRating(reviewRating);
    	input.setReviewContext(reviewContext);
    	input.setUser_ID(user_ID);
    	input.setReg_date(reg_date);
    	input.setEdit_date(edit_date);
    	input.setProduct_ID(product_ID);
    	
    	int checkReview = 0;
    	
    	reviewBeans output = null;
    	
    	try {    		
    		checkReview = reviewService.getCheckReview(input);
    		if (checkReview == 0) {
    			reviewService.addReview(input);
    			output = reviewService.getReviewItem(input);
    		}
			/* 백엔드 단에서 막기 
			 * else { return webHelper.getJsonWarning("리뷰가 이미 작성 되었습니다."); }
			 */	
		} catch (Exception e) {
			return webHelper.getJsonError(e.getLocalizedMessage());
		}
    	
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("checkReview",checkReview);
    	map.put("reviewOPT",output);
    	return webHelper.getJsonData(map);
    }
    
    
    /** 리뷰 수정 */
    @RequestMapping(value="/review/edit", method = RequestMethod.PUT)
    public Map<String,Object> reviewEdit (
    		@RequestParam(value = "reviewRating", defaultValue = "1", required = false) int reviewRating,
    		@RequestParam(value = "reviewContext", defaultValue = "", required = false) String reviewContext,
    		@RequestParam(value = "user_ID", defaultValue = "0", required = false) int user_ID,
    		@RequestParam(value = "reg_date", required = false) String reg_date,
    		@RequestParam(value = "edit_date", required = false) String edit_date,
    		@RequestParam(value = "product_ID", defaultValue = "0", required = false) int product_ID
    		) {
    	
    	if ( reviewContext == "") {
    		return webHelper.getJsonWarning("리뷰 글을 작성해주세요.");
    	}
    	
    	if ( user_ID == 0) {
    		return webHelper.getJsonWarning("리뷰를 작성하시려면 로그인 해주세요.");
    	}
    	
    	if ( product_ID == 0) {
    		return webHelper.getJsonWarning("리뷰를 작성할 제품을 선택해주세요.");
    	}
    	
    	reviewBeans input = new reviewBeans();
    	input.setReviewRating(reviewRating);
    	input.setReviewContext(reviewContext);
    	input.setUser_ID(user_ID);
    	input.setReg_date(reg_date);
    	input.setEdit_date(edit_date);
    	input.setProduct_ID(product_ID);
    	
    	reviewBeans output = null;
    	
    	try {
			reviewService.editReview(input);
			output = reviewService.getReviewItem(input);		
		} catch (Exception e) {
			return webHelper.getJsonError(e.getLocalizedMessage());
		}
    	
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("reviewOPT",output);
    	return webHelper.getJsonData(map);
    }    
    
    
    /** 리뷰 삭제 */
    @RequestMapping(value="/review/delete", method = RequestMethod.DELETE)
    public Map<String,Object> reviewDelete (
    		@RequestParam(value = "reviewNum", defaultValue = "0", required = false) int reviewNum
    		) {
    	
    	if ( reviewNum == 0) {
    		return webHelper.getJsonWarning("삭제할 리뷰가 없습니다.");
    	}
    	
    	reviewBeans input = new reviewBeans();
    	input.setReviewNum(reviewNum);
    	
    	reviewBeans output = null;
    	
    	try {
			reviewService.deleteReview(input);		
		} catch (Exception e) {
			return webHelper.getJsonError(e.getLocalizedMessage());
		}
    	
    	return webHelper.getJsonData();
    }    
    
    
}