/*
 * FileName: MypageController.java
 * Description: 사용자 마이페이지에 접근할 수 있는 Controller입니다.
 * Author: 임석현
 **/

package com.ezen03.tieshoe;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.RetrofitHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.service.BuyService;
import com.ezen03.tieshoe.service.SellService;
import com.ezen03.tieshoe.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class MypageController {

    private static final Logger logger = LoggerFactory.getLogger(MypageController.class);


    @Autowired
    WebHelper webHelper;

    @Autowired
    RegexHelper regexHelper;

    @Autowired
    RetrofitHelper retrofitHelper;

    @Autowired
    UserService userService;
    
    @Autowired
    BuyService buyService;
    
    @Autowired
    SellService sellService;

    @Value("#{servletContext.contextPath}")
    String contextPath;

    /**
     * Simply selects the home view to render by returning its name.
     */

    /**
     * 회원 정보를 가져와 myPage의 view에 노출 시킵니다.
     */
    @RequestMapping(value = "/myPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView myPage(Model model, HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "ID", defaultValue = "0", required = false) int ID,
                               @RequestParam(value = "userId", required = false) String userId,
                               @RequestParam(value = "userPw", required = false) String userPw,
                               @RequestParam(value = "userName", required = false) String userName,
                               @RequestParam(value = "userPhonenum", required = false) String userPhonenum,
                               @RequestParam(value = "userBirthDate", required = false) String userBirthDate,
                               @RequestParam(value = "userEmail", required = false) String userEmail,
                               @RequestParam(value = "userZipcode", required = false) String userZipcode,
                               @RequestParam(value = "userAddress1", required = false) String userAddress1,
                               @RequestParam(value = "userAddress2", required = false) String userAddress2,
                               @RequestParam(value = "userAccountBank", defaultValue = "NULL", required = false) String userAccountBank,
                               @RequestParam(value = "userAccountNum", defaultValue = "NULL", required = false) String userAccountNum,
                               @RequestParam(value = "userdropTF", defaultValue = "N", required = false) String userdropTF

    ) {

        ID = (Integer) webHelper.getSession("loginID");

        // 이 값이 존재하지 않는다면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (ID == 0) {
            return webHelper.redirect("/error", null);
        }

        /** 2) 데이터 조회하기 */
        // 데이터 조회에 필요한 조건값을 Beans에 저장하기
        userBeans input = new userBeans();
        input.setID(ID);
        input.setUserId(userId);
        input.setUserPw(userPw);
        input.setUserName(userName);
        input.setUserPhonenum(userPhonenum);
        input.setUserBirthDate(userBirthDate);
        input.setUserEmail(userEmail);
        input.setUserZipcode(userZipcode);
        input.setUserAddress1(userAddress1);
        input.setUserAddress2(userAddress2);
        input.setUserAccountBank(userAccountBank);
        input.setUserAccountNum(userAccountNum);

        // 조회결과를 저장할 객체 선언
        userBeans output = null;

        try {
            output = userService.getUserItem(input);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }

        /** 3) View 처리 */
        model.addAttribute("output", output);
        return new ModelAndView("myPage/myPage");
    }

    /**
     * 회원정보의 수정하는 action 페이지 입니다.
     */
    @RequestMapping(value = "/myPage/edit_ok", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editPW_ok(Model model,
                                  @RequestParam(value = "ID", required = false) int ID,
                                  @RequestParam(value = "userId", required = false) String userId,
                                  @RequestParam(value = "userPw", required = false) String userPw,
                                  @RequestParam(value = "userName", required = false) String userName,
                                  @RequestParam(value = "userPhonenum", required = false) String userPhonenum,
                                  @RequestParam(value = "userBirthDate", required = false) String userBirthDate,
                                  @RequestParam(value = "userEmail", required = false) String userEmail,
                                  @RequestParam(value = "userZipcode", required = false) String userZipcode,
                                  @RequestParam(value = "userAddress1", required = false) String userAddress1,
                                  @RequestParam(value = "userAddress2", required = false) String userAddress2

    ) {

        if (userId == null) {
            return webHelper.redirect(null, "회원 아이디를 입력하세요.");
        }

        if (userPw == null) {
            return webHelper.redirect(null, "비밀번호를 입력하세요.");
        }

        if (userName == null) {
            return webHelper.redirect(null, "이름를 입력하세요.");
        }

        if (userPhonenum == null) {
            return webHelper.redirect(null, "핸드폰번호를 입력하세요.");
        }

        if (userBirthDate == null) {
            return webHelper.redirect(null, "생년월일을 입력하세요.");
        }

        if (userEmail == null) {
            return webHelper.redirect(null, "이메일을 입력하세요.");
        }

        if (userZipcode == null) {
            return webHelper.redirect(null, "집주소(우편번호)를 입력하세요.");
        }

        if (userAddress1 == null) {
            return webHelper.redirect(null, "집주소(도로명주소)를 입력하세요.");
        }

        if (userAddress2 == null) {
            return webHelper.redirect(null, "집주소(상세주소)를 입력하세요.");
        }

        userBeans input = new userBeans();
        input.setID(ID);
        input.setUserId(userId);
        input.setUserPw(userPw);
        input.setUserName(userName);
        input.setUserPhonenum(userPhonenum);
        input.setUserBirthDate(userBirthDate);
        input.setUserEmail(userEmail);
        input.setUserZipcode(userZipcode);
        input.setUserAddress1(userAddress1);
        input.setUserAddress2(userAddress2);

        try {
        	userService.editUser(input);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }

        String redirectUrl = contextPath + "/myPage?ID=" + input.getID();
        return webHelper.redirect(redirectUrl, "회원정보가 수정 되었습니다.");
    }


    /**
     * 회원정보의 은행정보 수정하는 action 페이지 입니다.
     */
    @RequestMapping(value = "/myPage/edit_bank", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editPW_ok(Model model,
                                  @RequestParam(value = "ID", required = false) int ID,
                                  @RequestParam(value = "userAccountBank", defaultValue = "NULL", required = false) String userAccountBank,
                                  @RequestParam(value = "userAccountNum", defaultValue = "NULL", required = false) String userAccountNum

    ) {


        userBeans input = new userBeans();
        input.setID(ID);
        input.setUserAccountBank(userAccountBank);
        input.setUserAccountNum(userAccountNum);

        try {
        	userService.updateBank(input);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }

        String redirectUrl = contextPath + "/myPage?ID=" + input.getID();
        return webHelper.redirect(redirectUrl, "은행정보가 수정 되었습니다.");
    }


    /**
     * 회원탈퇴 페이지
     */
	/*
	 * @RequestMapping(value = "/dropUser", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView dropUser(Locale locale, Model model,
	 * HttpServletRequest request, HttpServletResponse response,
	 * 
	 * @RequestParam(value = "ID", required = false) int ID,
	 * 
	 * @RequestParam(value = "userdropTF", required = false) String userdropTF ) {
	 * 
	 * userBeans input = new userBeans(); input.setID(ID);
	 * input.setUserdropTF(userdropTF);
	 * 
	 * buyBeans buy = new buyBeans(); int findBuy = 0; sellBeans sell = new
	 * sellBeans(); int findSell = 0;
	 * 
	 * buy.setUser_ID(input.getID());
	 * 
	 * 
	 * try { findBuy = buyService.buyUserCount(buy); //findSell =
	 * sellService.sellUserCount(sell); if(findBuy == 0) { // && findSell == 0
	 * userService.dropUser(input); } else { return webHelper.redirect(null,
	 * "회원님께서는 현재 거래중인 상품이 있어 탈퇴가 불가능 합니다."); } } catch (Exception e) { return
	 * webHelper.redirect(null, e.getLocalizedMessage()+"시스템 에러 발생."); }
	 * 
	 * model.addAttribute("findBuy", findBuy);
	 * 
	 * String redirectUrl = contextPath; return webHelper.redirect(redirectUrl,
	 * "회원탈퇴 완료."); }
	 */
    
    

    @RequestMapping(value = "/myPage/sell", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView myPageSell(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        Object ID = webHelper.getSession("loginID");

        if (ID == null || ID == "") {
            return webHelper.redirect(contextPath + "/error", null);

        }

        return new ModelAndView("myPage/myPage_sell");
    }
    
    

    @RequestMapping(value = "/myPage/buy", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView myPageBuy(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        Object ID = webHelper.getSession("loginID");

        if (ID == null || ID == "") {
            return webHelper.redirect(contextPath + "/error", null);

        }

        return new ModelAndView("myPage/myPage_buy");
    }
}
