/*
 * FileName: LoginController.java
 * Description: 로그인/아이디찾기/로그아웃 controller입니다.
 * Author: 임채린
 **/

package com.ezen03.tieshoe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import com.ezen03.tieshoe.socialLogin.KakaoLoginBO;
import com.ezen03.tieshoe.socialLogin.NaverLoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.model.buyBeans;
import com.ezen03.tieshoe.model.sellBeans;
import com.ezen03.tieshoe.model.userReset;
import com.ezen03.tieshoe.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    WebHelper webHelper;

    @Autowired
    RegexHelper regexHelper;

    @Autowired
    LoginService loginService;

    /* NaverLoginBO */
    private NaverLoginBO naverLoginBO;
    private String NaverApiResult = null;

    /* KakaoLoginBO */
    private KakaoLoginBO kakaoLoginBO;
    private String KakaoApiResult = null;

    @Autowired
    private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }

    @Autowired
    private void setKakaoLoginBO(KakaoLoginBO kakaoLoginBO) {
        this.kakaoLoginBO = kakaoLoginBO;
    }

    @Value("#{servletContext.contextPath}")
    String contextPath;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model) {
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO 클래스의 getAuthorizationUrl 메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl();
        log.info("네이버:" + naverAuthUrl);

        String kakaoAuthUrl = kakaoLoginBO.getAuthorizationUrl();
        log.info("카카오:" + kakaoAuthUrl);

        //네이버
        model.addAttribute("urlNaver", naverAuthUrl);

        //카카오
        model.addAttribute("urlKakao", kakaoAuthUrl);
        return new ModelAndView("account/login");
    }

    @RequestMapping(value = "/naverLogin", method = RequestMethod.GET)
    public ModelAndView naverLogin(Model model,
                                   @RequestParam(value = "id", required = false) String socialId,
                                   @RequestParam(value = "name", required = false) String naverName,
                                   @RequestParam(value = "email", required = false) String naverEmail,
                                   @RequestParam(value = "nickname", required = false) String nickname) {

        //네이버에서 제공하는 값으로 네이버 안에서는 겹칠일이 없으므로 앞에 naver을 붙혀 다른 로그인과 겹치지 않게 합니다
        String password = "naver" + socialId;
        // 로그인 사용자 정보 저장
        userBeans userInput = new userBeans();
        userInput.setUserId(nickname);
        userInput.setUserPw(password);

        //소셜로그인을 했을 경우 (임석현)
        //회원가입이 되어있는지 여부를 판단하고 되어있다면 다음 단계로 안되어있다면 회원가입페이지로 넘깁니다
        int approved = 0;
        try {
            approved = loginService.sociaLogin_ok(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //조회 결과가 0일떄 (즉, 조회결과가 없을때) 정보를 세션화 시킨후 회원가입 페이지로 이동합니다
        // 사용자의 아이디가 될 부분
        // 사용자의 패스워드가 될 부분

        if (approved == 0) {
            webHelper.setStringSession("naverID", nickname); // 사용자의 아이디가 될 부분
            webHelper.setStringSession("naverPW", password); // 사용자의 패스워드가 될 부분
            webHelper.setStringSession("naverEmail", naverEmail); //사용자의 이메일주소
            webHelper.setStringSession("naverName", naverName); //사용자의 실명


            return webHelper.redirect(contextPath + "/closeTab?approve=false&loginType=naver", null);
        }
        //소셜 로그인의 경우 ID는 nickname(한글 || 영어)  PW는 socialID로 대체됩니다 (네이버)
        return webHelper.redirect(contextPath + "/closeTab?approve=true&ID=" + nickname + "&check=" + password + "&loginType=naver", null);
    }

    @RequestMapping(value = "/kakaoLogin", method = RequestMethod.GET)
    public ModelAndView kakaoLogin(Model model,
                                   @RequestParam(value = "id", required = false) String socialId,
                                   @RequestParam(value = "email", required = false) String kakaoEmail,
                                   @RequestParam(value = "nickName", required = false) String nickName) {

        //네이버에서 제공하는 값으로 네이버 안에서는 겹칠일이 없으므로 앞에 naver을 붙혀 다른 로그인과 겹치지 않게 합니다
        String password = "kakao" + socialId;
        // 로그인 사용자 정보 저장
        userBeans userInput = new userBeans();
        userInput.setUserId(nickName);
        userInput.setUserPw(password);

        //소셜로그인을 했을 경우 (임석현)
        //회원가입이 되어있는지 여부를 판단하고 되어있다면 다음 단계로 안되어있다면 회원가입페이지로 넘깁니다
        int approved = 0;
        try {
            approved = loginService.sociaLogin_ok(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //조회 결과가 0일떄 (즉, 조회결과가 없을때) 정보를 세션화 시킨후 회원가입 페이지로 이동합니다
        // 사용자의 아이디가 될 부분
        // 사용자의 패스워드가 될 부분

        if (approved == 0) {
            webHelper.setStringSession("kakaoID", nickName); // 사용자의 아이디가 될 부분
            webHelper.setStringSession("kakaoPW", password); // 사용자의 패스워드가 될 부분
            webHelper.setStringSession("kakaoEmail", kakaoEmail); //사용자의 이메일주소

            return webHelper.redirect(contextPath + "/closeTab?approve=false&loginType=kakao", null);
        }
        //소셜 로그인의 경우 ID는 nickname(한글 || 영어)  PW는 socialID로 대체됩니다 (네이버)
        return webHelper.redirect(contextPath + "/closeTab?approve=true&ID=" + nickName + "&check=" + password + "&loginType=kakao", null);
    }


    @RequestMapping(value = "/login_ok", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login_ok(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "userId", required = false) String userId,
                                 @RequestParam(value = "userPw", required = false) String userPw) {

        if (userId == "" || userPw == "") {
            return webHelper.redirect(null, "아이디와 비밀번호를 정확하게 입력해주세요.");
        }
        //세션이 많을수 있으니 모든 세션을 삭제하고 정리합니다 (임석현)
        boolean newUser = false;
        // 새로운 유저라면 AccountController 에서 설정한 세션이 있는지 확인합니다
        if (webHelper.getSession("register") != null) {
            newUser = true;
        }

        // =====정리 끝=====

        // 로그인 사용자 정보 저장
        userBeans userInput = new userBeans();
        userInput.setUserId(userId);
        userInput.setUserPw(userPw);

        // 로그인 사용자 객체 저장
        userBeans userOutput = null;
        int SellOutputY = 0;
        int SellOutputA = 0;
        int BuyOutputY = 0;
        int BuyOutputA = 0;
        String UserFormOutput = null;
        userReset UserResetOutput = null;

        try {
            userOutput = loginService.login_ok(userInput);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }
        if (userOutput.getUserdropTF().equals("Y")) { // 탈퇴회원일 경우 에러 메시지
            return webHelper.redirect(contextPath + "/logout", "회원 정보가 없습니다. 아이디와 비밀번호를 확인해주세요.");
        }
        if (userOutput.getEmailCheck().equals("N")) { // 회원가입시 이메일 인증이 안되어있을 경우
            return webHelper.redirect(contextPath + "/logout", "이메일 인증이 완료되지 않았습니다. 회원가입시 입력한 이메일을 확인해주세요.");
        }
        try {
            UserResetOutput = loginService.checkUserAuthEmail(userOutput.getID());
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }
        if (UserResetOutput != null && UserResetOutput.getEmail_type().equals("1") && UserResetOutput.getKey_used().equals("N")) { //회원가입 이메일 인증 여부 확인
            return webHelper.redirect(contextPath + "/logout", "이메일 인증이 완료되지 않았습니다. 회원가입시 입력한 이메일을 확인해주세요.");
        } else if (UserResetOutput != null && UserResetOutput.getEmail_type().equals("3") && UserResetOutput.getKey_used().equals("N")) { //비번변경 이메일 인증 여부 확인
            return webHelper.redirect(contextPath + "/logout", "비밀번호 변경 이메일이 발송되었습니다. 회원가입시 입력한 이메일을 확인해주세요.");
        } else { //발송된 이메일이 없을 경우

            try {
                BuyOutputY = loginService.checkBuyProcessY(userOutput.getID()); //거래성사여부 확인
            } catch (Exception e) {
                return webHelper.redirect(null, e.getLocalizedMessage());
            }

            try {
                BuyOutputA = loginService.checkBuyProcessA(userOutput.getID()); //거래철회여부 확인
            } catch (Exception e) {
                return webHelper.redirect(null, e.getLocalizedMessage());
            }

            try {
                SellOutputY = loginService.checkSellProcessY(userOutput.getID()); //거래성사여부 확인
            } catch (Exception e) {
                return webHelper.redirect(null, e.getLocalizedMessage());
            }

            try {
                SellOutputA = loginService.checkSellProcessA(userOutput.getID()); //거래성사여부 확인
            } catch (Exception e) {
                return webHelper.redirect(null, e.getLocalizedMessage());
            }

            String remember_userId = request.getParameter("remember_userId"); // 아이디 저장 체크박스 체크여부 확인
            if (remember_userId != null) {
                webHelper.setCookie("user_check", userId, (7 * 24 * 60 * 60)); // 아이디 7일동안 저장
            } else {
                webHelper.removeCookie("user_check");
            }
            if (userOutput.getUserForm() == null) {
                UserFormOutput = "N";
            } else {
                UserFormOutput = "Y";
            }

            /** 필요 없는 세션은 제거가 필요합니다 (임석현)*/
            webHelper.setSession("loginID", userOutput.getID());
            webHelper.setSession("UserId", userOutput.getUserId());
            webHelper.setSession("UserName", userOutput.getUserName());
            webHelper.setSession("UserEmail", userOutput.getUserEmail());
            webHelper.setSession("UserAdmin", userOutput.getUserAdmin());
            webHelper.setSession("UserdropTF", userOutput.getUserdropTF());
            webHelper.setSession("EmailCheck", userOutput.getEmailCheck());
            webHelper.setSession("UserPenalty", userOutput.getUser_penalty());
            webHelper.setSession("userLevel", userOutput.getUserLevel()); //admin을 위해 추가 (임석현)
            webHelper.setSession("UserForm", UserFormOutput);
            webHelper.setSession("BuyProcessY", BuyOutputY);
            webHelper.setSession("BuyProcessA", BuyOutputA);
            webHelper.setSession("SellProcessY", SellOutputY);
            webHelper.setSession("SellProcessA", SellOutputA);

            if (BuyOutputY != 0 || SellOutputY != 0 || BuyOutputA != 0 || SellOutputA != 0) { // 변경된 거래 건이 있을 경우  확인 메시지
                return webHelper.redirect(contextPath + "/", "변경된 거래 내역이 있습니다. 마이페이지를 확인해주세요.");
            }
        }

        // 최초 회원인지 확인하여 회원 환영페이지로 보냅니다
        if (newUser) {
            webHelper.removeSession("register");
            return webHelper.redirect(contextPath + "/registerSuccess", null);
        }

        return webHelper.redirect(contextPath + "/", null);
    }

    // 로그아웃 처리
    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               HttpSession session) {

        Object object = webHelper.getSession("loginID");

        if (object != null) {
            webHelper.removeAllSession();
        }
        return webHelper.redirect(contextPath + "/", null);
    }


    /**
     * 아이디찾기 페이지
     */
    @RequestMapping(value = "/findID", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView find_id(Model model,
                                @RequestParam(value = "userName", required = false) String userName,
                                @RequestParam(value = "userEmail", required = false) String userEmail
    ) throws Exception {
        // 값이 존재하지 않으면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (userName == "" || userEmail == "") {
            return webHelper.redirect(null, "회원정보가 없습니다.");
        }

        // 데이터 조회에 필요한 조건값을 Beans에 저장하기
        userBeans input = new userBeans();
        input.setUserName(userName);
        input.setUserEmail(userEmail);

        // 아이디와 비밀번호가 가입된 정보와 일치하는지 검사
        if (!userName.equals(input.getUserName()) || !userEmail.equals(input.getUserEmail())) {
            return webHelper.redirect(null, "이름이나 이메일이 잘못되었습니다. 이름과 이메일을 확인해주세요");
        }

        // 조회결과를 저장할 객체 선언
        userBeans output = null;

        try {
            // 데이터 조회
            output = loginService.findID(input);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }

        if (output.getUserdropTF().equals("Y")) { // 탈퇴회원일 경우 에러 메시지
            return webHelper.redirect(contextPath + "/login", "회원 정보가 없습니다. 아이디와 비밀번호를 확인해주세요.");
        }

        model.addAttribute("output", output);
        return new ModelAndView("account/findID");


    }
}