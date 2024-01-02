/*
 * FileName: AccountController.java
 * Description: 사용자 정보에 대한 접근이 가능하게 하는 controller 입니다.
 * Author: 임석현
 **/
package com.ezen03.tieshoe;

import java.lang.ProcessBuilder.Redirect;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen03.tieshoe.helper.MailHelper;
import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.RetrofitHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.model.userReset;
import com.ezen03.tieshoe.service.UserService;
import com.ezen03.tieshoe.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	WebHelper webHelper;

	@Autowired
	RegexHelper regexHelper;

	@Autowired
	RetrofitHelper retrofitHelper;

	@Autowired
	MailHelper mailHelper;

	@Autowired
	UserService userService;

	@Value("#{servletContext.contextPath}")
	String contextPath;

	/**
	 * 회원가입 폼 페이지
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join(Model model, HttpServletRequest request) {
		return new ModelAndView("account/join");
	}

	@RequestMapping(value = "/naverRegister", method = RequestMethod.GET)
	public ModelAndView naverRegister(Model model) {

		if (webHelper.getSession("naverID") == null) {
			return webHelper.redirect("/error", null);
		}
		return new ModelAndView("account/naverRegister");
	}

	@RequestMapping(value = "/kakaoRegister", method = RequestMethod.GET)
	public ModelAndView kakaoRegister(Model model) {

		if (webHelper.getSession("kakaoID") == null) {
			return webHelper.redirect("/error", null);
		}
		return new ModelAndView("account/kakaoRegister");
	}

	/**
	 * 네이버 간편회원가입
	 */
	@RequestMapping(value = "/joinNaverConfirm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView joinNaverConfirm(Locale locale, Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "userPhonenum", required = false) String userPhonenum,
			@RequestParam(value = "userBirthDate", required = false) String userBirthDate) {
		if (userPhonenum == "") {
			return webHelper.redirect(null, "핸드폰번호를 입력하세요.");
		}

		if (userBirthDate == "") {
			return webHelper.redirect(null, "생년월일을 입력하세요.");
		}

		String userId = (String) webHelper.getSession("naverID");
		String userPw = (String) webHelper.getSession("naverPW");
		String userEmail = (String) webHelper.getSession("naverEmail");
		String userName = (String) webHelper.getSession("naverName");

		/** 2) 데이터 저장하기 */
		userBeans input = new userBeans();
		input.setUserId(userId);
		input.setUserPw(userPw);
		input.setUserName(userName);
		input.setUserPhonenum(userPhonenum);
		input.setUserBirthDate(userBirthDate);
		input.setUserEmail(userEmail);

		try {
			userService.addNaverUser(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		// 로그인 페이지에서 회원 축하 페이지로 넘아가기 위해 세션을 생성합니다 (로그인에서 세션을 확인해 인덱스페이지 혹은 회원 축하페이지로
		// 보냅니다)
		webHelper.setStringSession("register", "Y");
		// 로그인을 합니다
		return webHelper.redirect(contextPath + "/login_ok?userId=" + userId + "&userPw=" + userPw, null);
	}

	@RequestMapping(value = "/joinKakaoConfirm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView joinKakaoConfirm(Locale locale, Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "userPhonenum", required = false) String userPhonenum,
			@RequestParam(value = "userBirthDate", required = false) String userBirthDate,
			@RequestParam(value = "userName", required = false) String userName) {
		if (userPhonenum == "") {
			return webHelper.redirect(null, "핸드폰번호를 입력하세요.");
		}

		if (userName == "") {
			return webHelper.redirect(null, "이름를 입력하세요.");
		}

		if (userBirthDate == "") {
			return webHelper.redirect(null, "생년월일을 입력하세요.");
		}

		String userId = (String) webHelper.getSession("kakaoID");
		String userPw = (String) webHelper.getSession("kakaoPW");
		String userEmail = (String) webHelper.getSession("kakaoEmail");

		/** 2) 데이터 저장하기 */
		userBeans input = new userBeans();
		input.setUserId(userId);
		input.setUserPw(userPw);
		input.setUserName(userName);
		input.setUserPhonenum(userPhonenum);
		input.setUserBirthDate(userBirthDate);
		input.setUserEmail(userEmail);

		try {
			userService.addNaverUser(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		// 로그인 페이지에서 회원 축하 페이지로 넘아가기 위해 세션을 생성합니다 (로그인에서 세션을 확인해 인덱스페이지 혹은 회원 축하페이지로
		// 보냅니다)
		webHelper.setStringSession("register", "Y");
		// 로그인을 합니다
		return webHelper.redirect(contextPath + "/login_ok?userId=" + userId + "&userPw=" + userPw, null);
	}

	/**
	 * 회원가입 폼에 대한 action 페이지
	 */
	@RequestMapping(value = "/joinConfirm", method = RequestMethod.POST)
	public ModelAndView joinConfirm(Locale locale, Model model, HttpSession session, HttpServletRequest request,

			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "userPw", required = false) String userPw,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "userPhonenum", required = false) String userPhonenum,
			@RequestParam(value = "userBirthDate", required = false) String userBirthDate,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "userZipcode", required = false) String userZipcode,
			@RequestParam(value = "userAddress1", required = false) String userAddress1,
			@RequestParam(value = "userAddress2", required = false) String userAddress2,
			@RequestParam(value = "reg_date", required = false) String reg_date, // reg_date userResigisterDate
			@RequestParam(value = "edit_date", defaultValue = "9999-12-31", required = false) String edit_date,

			/** default value 항목 */
			@RequestParam(value = "userAdmin", defaultValue = "N", required = false) String userAdmin,
			@RequestParam(value = "userForm[]", defaultValue = "NULL", required = false) String userForm,
			@RequestParam(value = "userAccountBank", defaultValue = "NULL", required = false) String userAccountBank,
			@RequestParam(value = "userAccountNum", defaultValue = "NULL", required = false) String userAccountNum,
			@RequestParam(value = "userdropTF", defaultValue = "N", required = false) String userdropTF,
			@RequestParam(value = "user_penalty", defaultValue = "N", required = false) String user_penalty,
			@RequestParam(value = "emailCheck", defaultValue = "N", required = false) String emailCheck

	) {

		if (userId == "") {
			return webHelper.redirect(null, "회원 아이디를 입력하세요.");
		}

		if (userPw == "") {
			return webHelper.redirect(null, "비밀번호를 입력하세요.");
		}

		if (userName == "") {
			return webHelper.redirect(null, "이름를 입력하세요.");
		}

		if (userPhonenum == "") {
			return webHelper.redirect(null, "핸드폰번호를 입력하세요.");
		}

		if (userBirthDate == "") {
			return webHelper.redirect(null, "생년월일을 입력하세요.");
		}

		if (userEmail == "") {
			return webHelper.redirect(null, "이메일을 입력하세요.");
		}

		if (userZipcode == "") {
			return webHelper.redirect(null, "집주소(우편번호)를 입력하세요.");
		}

		if (userAddress1 == "") {
			return webHelper.redirect(null, "집주소(도로명주소)를 입력하세요.");
		}

		if (userAddress2 == "") {
			return webHelper.redirect(null, "집주소(상세주소)를 입력하세요.");
		}

		/** 2) 데이터 저장하기 */
		userBeans input = new userBeans();
		input.setUserId(userId);
		input.setUserPw(userPw);
		input.setUserName(userName);
		input.setUserPhonenum(userPhonenum);
		input.setUserBirthDate(userBirthDate);
		input.setUserEmail(userEmail);
		input.setUserZipcode(userZipcode);
		input.setUserAddress1(userAddress1);
		input.setUserAddress2(userAddress2);
		input.setReg_date(reg_date);
		input.setEdit_date(edit_date);
		// defalut 항목
		input.setUserAdmin(userAdmin);
		input.setUserForm(userForm);
		input.setUserAccountBank(userAccountBank);
		input.setUserAccountNum(userAccountNum);
		input.setUserdropTF(userdropTF);
		input.setUser_penalty(user_penalty);
		input.setEmailCheck(emailCheck);

		try {
			userService.addUser(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		webHelper.setSession("welcomeID", input.getID());

		String redirectUrl = contextPath + "/welcome";
		return webHelper.redirect(redirectUrl, "회원가입이 완료되었습니다.");
	}

	/**
	 * 로컬 회원 가입 환영 페이지
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome(Model model, @RequestParam(value = "ID", defaultValue = "0", required = false) int ID) {

		ID = (Integer) webHelper.getSession("welcomeID");

		// 이 값이 존재하지 않는다면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
		if (ID == 0) {
			return webHelper.redirect(null, "회원번호가 없습니다.");
		}

		/** 2) 데이터 조회하기 */
		// 데이터 조회에 필요한 조건값을 Beans에 저장하기
		userBeans input = new userBeans();
		input.setID(ID);

		// 조회결과를 저장할 객체 선언
		userBeans output = null;

		try {
			output = userService.getUserItem(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		/** 3) View 처리 결과값을 "output에 담아 view단에 전달" */
		model.addAttribute("output", output);
		return new ModelAndView("account/welcome");
	}

	/**
	 * 회원가입 이메일 인증 전송 action
	 */
	@RequestMapping(value = "/emailAuth", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView emailAuth(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "ID", defaultValue = "0", required = true) int ID,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "userEmail", required = true) String userEmail) {

		if (ID == 0 || userId == null || userEmail == null) {
			return webHelper.redirect(null, "회원정보를 가지고 오는도중 에러가 발생했습니다.");
		}

		userBeans input = new userBeans();
		userReset inputReset = new userReset();
		input.setID(ID);
		input.setUserId(userId);
		input.setUserEmail(userEmail);
		input.setUserName(userName);

		int success = 0;

		String randomCode = createRandomCode(20, "qwertyuiopasdfghjklzxcvbnm1234567890");

		String subject = "TIE SHOE || 가입인증메일";

		String localHost = "http://localhost:8080";

		String contextPath = request.getContextPath();

		String Email_carrier = userEmail.substring(userEmail.lastIndexOf("@") + 1);

		String link = localHost + contextPath + "/emailAuthCheck?auth=" + ID + "T" + randomCode;

		String content = emailForm(link, userId, userName, userEmail);

		// 사용자 번호 + 시간(ms) + 랜덤 코드 조합으로 암호키를 생성합니다.
		String randomString = input.getID() + "T" + randomCode;

		inputReset.setEmail_key(randomString);
		inputReset.setUser_ID(input.getID());

		try {
			success = userService.authEmailKey(inputReset);
			// mailHelper.sendMail(userEmail, subject, content);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage() + "메일전송 컨트롤러 인증입력 문제 발생.");
		}

		try {
			mailHelper.sendMail(userEmail, subject, content);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage() + "메일전송 컨트롤러 이메일전송 문제 발생.");
		}

		session.setAttribute("userId", userId);
		session.setAttribute("Email_carrier", Email_carrier);
		session.setAttribute("userEmail", userEmail);
		session.setAttribute("ID", ID);
		session.setAttribute("userName", userName);

		String redirectUrl = contextPath + "/welcome";
		return webHelper.redirect(redirectUrl, "가입 인증 메일을 전송했습니다.");
	}

	/**
	 * 인증 email 내용
	 */
	public String emailForm(String link, String userId, String userName, String userEmail) {
		return "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <title>Title</title>\n" + "</head>\n" + "<body>\n" + "<div>\r\n" + "    <div class=\"adM\">\r\n"
				+ "    </div>\r\n" + "</div>\r\n" + "<div class=\"adM\">\r\n" + "</div>\r\n"
				+ "<div style=\"padding-top:90px;padding-bottom:70px;text-align:center\">\r\n"
				+ "    <div class=\"adM\">\r\n" + "    </div>\r\n"
				+ "    <div style=\"width:375px;min-height:100px;margin-right:auto;margin-left:auto;font-family:'Nanum Gothic',sans-serif\">\r\n"
				+ "        <div class=\"adM\">\r\n" + "        </div>\r\n"
				+ "        <div style=\"margin-bottom:6px;color:#000;font-size:27px;line-height:37px;text-align:left\">\r\n"
				+ "            <div class=\"adM\">\r\n" + "            </div>\r\n"
				+ "            <div style=\"display:inline-table\">\r\n" + "                <div class=\"adM\">\r\n"
				+ "                </div>\r\n"
				+ "                <div id=\"email-name\" style=\"display:inline-block\">\r\n" + userName + "\r\n"
				+ "                </div>\r\n" + "                <div style=\"display:inline-block\">님,</div>\r\n"
				+ "            </div>\r\n" + "            <div style=\"display:flex\">\r\n"
				+ "                <div style=\"display:inline-block;color:#e72525\">회원가입</div>\r\n"
				+ "                <div style=\"display:inline-block\">을 환영합니다.</div>\r\n" + "            </div>\r\n"
				+ "        </div>\r\n"
				+ "        <div style=\"color:#a3a3a3;font-size:11px;line-height:15px;text-align:left\">\r\n"
				+ "            <div style=\"display:flex\">\r\n"
				+ "                <div id=\"email-name-sub\" style=\"display:inline-block\">\r\n" + userName + "\r\n"
				+ "                </div>\r\n"
				+ "                <div style=\"display:inline-block\">님 회원가입이 완료되었습니다.</div>\r\n"
				+ "            </div>\r\n" + "            <div>\r\n"
				+ "                <div style=\"display:inline-block\">TIE SHOE의 다양한 서비스를 마음껏 경험해 보세요.</div>\r\n"
				+ "            </div>\r\n" + "        </div>\r\n" + "        <div style=\"margin-top:50px\">\r\n"
				+ "            <div style=\"display:grid;padding-bottom:6px;border-bottom:1px solid #e8e8e8\">\r\n"
				+ "                <div id=\"joinInfo\" style=\"margin-bottom:32px;padding-bottom:9px;border-bottom:2px solid #000;color:#000;font-size:15px;line-height:17px;font-weight:700;text-align:left\">가입정보</div>\r\n"
				+ "                <div id=\"joinEmailInfo\" style=\"margin-top:6px;margin-bottom:13px;margin-left:10px;font-size:11px;line-height:12px;text-align:left\">아이디</div>\r\n"
				+ "                <div id=\"joinInfoEmail\" style=\"display:inline;margin-top:6px;margin-right:10px;margin-bottom:13px;font-size:13px;line-height:15px;font-weight:700;text-align:right\"><a href=\"mailto:userEmail\" target=\"_blank\">\r\n"
				+ userId + "\r\n" + "                    </a></div>\r\n"
				+ "                <div id=\"joinNameInfo\" style=\"display:inline;margin-top:6px;margin-right:10px;margin-bottom:13px;font-size:13px;line-height:15px;font-weight:700;text-align:right\">\r\n"
				+ userName + "\r\n" + "                </div>\r\n"
				+ "                <div id=\"joinInfoName\" style=\"margin-top:6px;margin-bottom:13px;margin-left:10px;font-size:11px;line-height:12px;text-align:left\">이름</div>\r\n"
				+ "            </div>\r\n" + "        </div><a href=\"" + link
				+ "\" style=\"width:100%;margin-top:45px;padding-top:12px;padding-bottom:12px;border-radius:4px;background-color:#000;font-size:13px;line-height:15px;text-decoration:none;color:white;display:inline-block;border:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://xxblue.com&amp;source=gmail&amp;ust=1585729413248000&amp;usg=AFQjCNFQwTRGBthPJ94BaYCXyjP_11Iw0A\"><span class=\"il\">TIE SHOE</span>&nbsp;인증하기</a>\r\n"
				+ "    </div>\r\n" + "</div>\r\n" + "<div style=\"font-family:'Nanum Gothic',sans-serif\">\r\n"
				+ "    <div style=\"width:100%;min-height:100px;margin-right:auto;margin-left:auto;padding:26px 12px 20px;background-color:#f5f5f5;font-family:'Nanum Gothic',sans-serif\">\r\n"
				+ "        <p style=\"color:#999;font-size:9px;line-height:14px\">본 메일은 발신전용메일이며 문의에 대한 회신이 되지 않습니다. <br>궁금한점은 고객센터로 문의해 주세요.</p>\r\n"
				+ "        <p style=\"color:#999;font-size:9px;line-height:14px\">서울특별시 서초구, 1303-37 서초W타워 13층 &nbsp;| &nbsp;대표이사 임채린, 최성준, 임석현<br>사업자 등록번호 123-45-67890 &nbsp; | &nbsp;통신판매업 신고 2019-서울강남-12345<br>문의전화 02-123-4567 &nbsp;| &nbsp;개인정보관리책임: TIE SHOE<br><a href=\"mailto:info@xxblue.com\" target=\"_blank\">tieshoe@gmail.com</a></p>\r\n"
				+ "        <div class=\"yj6qo\"></div>\r\n" + "        <div class=\"adL\">\r\n" + "        </div>\r\n"
				+ "    </div>\r\n" + "    <div class=\"adL\">\r\n" + "    </div>\r\n" + "</div>\r\n"
				+ "<div class=\"adL\">\r\n" + "</div>\r\n" + "</div>" + "</body>\n" + "</html>";

	}

	/**
	 * link로 접속하여 인증완료 (권한변경) 를 위한 action
	 */
	@RequestMapping(value = "/emailAuthCheck", method = RequestMethod.GET)
	public ModelAndView emailAuthCheck(Model model, HttpServletRequest request,
			@RequestParam(required = true) String auth, @RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "emailCheck", defaultValue = "Y", required = false) String emailCheck) {

		
	
		
		// 암호화 풀기 (ID 과 encrypted key)
		// encrypted Key
		String key = auth.substring(auth.lastIndexOf("T") + 1);

		// UserNum
		String IDString = auth.substring(0, auth.indexOf("T"));

		// 형변환
		int ID = Integer.parseInt(IDString);

		// set ID to java beans
		userBeans input = new userBeans();
		userReset inputReset = new userReset();
		input.setID(ID);
		int output = 0;
		int success = 0;
		
		inputReset.setUser_ID(input.getID());


		try {
			success = userService.authEmailKey(inputReset);
			output = userService.emailAuth(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		String redirectUrl = contextPath;
		return webHelper.redirect(redirectUrl, "이메일 인증완료. 재로그인 해주세요.");
	}

	/**
	 * 아이디 중복검사 action
	 */
	@ResponseBody
	@RequestMapping(value = "/checkId", method = { RequestMethod.GET, RequestMethod.POST })
	public int checkId(Model model, HttpServletRequest request,
			@RequestParam(value = "userId", required = false) String userId) throws Exception {

		userId = request.getParameter("userId");

		if (userId == null) {
			webHelper.redirect(null, "아이디를 입력해주세요.");
		}

		int result = 0;

		result = userService.checkId(userId);

		return result;
	}

	/**
	 * 이메일 중복검사 action
	 */
	@ResponseBody
	@RequestMapping(value = "/checkEmail", method = { RequestMethod.GET, RequestMethod.POST })
	public int userEmail(Model model, HttpServletRequest request,
			@RequestParam(value = "userEmail", required = false) String userEmail) throws Exception {

		userEmail = request.getParameter("userEmail");

		int result = 0;

		result = userService.checkEmail(userEmail);

		return result;
	}

	/**
	 * 휴대폰 번호 중복검사 action
	 */
	@ResponseBody
	@RequestMapping(value = "/checkPhonenum", method = { RequestMethod.GET, RequestMethod.POST })
	public int userPhonenum(Model model, HttpServletRequest request,
			@RequestParam(value = "userPhonenum", required = false) String userPhonenum) throws Exception {

		userPhonenum = request.getParameter("userPhonenum");

		int result = 0;

		result = userService.checkPhonenum(userPhonenum);

		return result;
	}

	/**
	 * 랜덤키 생성
	 */
	public String createRandomCode(int codeLength, String id) {
		List<Character> temp = id.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
		Collections.shuffle(temp, new SecureRandom());
		return temp.stream().map(Object::toString).limit(codeLength).collect(Collectors.joining());
	}

	@RequestMapping(value = "/survey", method = RequestMethod.GET)
	public ModelAndView survey(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		return new ModelAndView("account/survey");
	}

	@RequestMapping(value = "/surveyOk", method = RequestMethod.POST)
	public ModelAndView surveyOk(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ID", defaultValue = "0", required = false) int ID,
			@RequestParam(value = "userForm", defaultValue = "null", required = false) String userForm) {

		ID = (Integer) webHelper.getSession("loginID");

		if (userForm == null) {
			webHelper.redirect(null, "설문 조사 내용이 없습니다.");
		}

		userBeans input = new userBeans();
		input.setUserForm(userForm);
		input.setID(ID);
		String UserFormOutput = null;

		try {
			userService.addUserForm(input);
			UserFormOutput = "Y";
			webHelper.setSession("UserForm", UserFormOutput);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		String redirectUrl = contextPath;
		return webHelper.redirect(redirectUrl, "설문조사 완료");
	}

	/**
	 * 관리자 등록
	 */
	@RequestMapping(value = "/adminConfirm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminConfirm(Locale locale, Model model, HttpSession session, HttpServletRequest request,

			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "userPw", required = false) String userPw,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "userPhonenum", required = false) String userPhonenum,
			@RequestParam(value = "userBirthDate", defaultValue = "9999-12-31", required = false) String userBirthDate,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "userZipcode", defaultValue = "admin", required = false) String userZipcode,
			@RequestParam(value = "userAddress1", defaultValue = "admin", required = false) String userAddress1,
			@RequestParam(value = "userAddress2", defaultValue = "admin", required = false) String userAddress2,
			@RequestParam(value = "reg_date", required = false) String reg_date, // reg_date userResigisterDate
			@RequestParam(value = "edit_date", defaultValue = "9999-12-31", required = false) String edit_date,
			@RequestParam(value = "userLevel", defaultValue = "5", required = false) int userLevel,

			/** default value 항목 */
			@RequestParam(value = "userAdmin", defaultValue = "Y", required = false) String userAdmin,
			@RequestParam(value = "userForm[]", defaultValue = "NULL", required = false) String userForm,
			@RequestParam(value = "userAccountBank", defaultValue = "NULL", required = false) String userAccountBank,
			@RequestParam(value = "userAccountNum", defaultValue = "NULL", required = false) String userAccountNum,
			@RequestParam(value = "userdropTF", defaultValue = "N", required = false) String userdropTF,
			@RequestParam(value = "user_penalty", defaultValue = "N", required = false) String user_penalty,
			@RequestParam(value = "emailCheck", defaultValue = "N", required = false) String emailCheck

	) {

		if (userId == "") {
			return webHelper.redirect(null, "회원 아이디를 입력하세요.");
		}

		if (userPw == "") {
			return webHelper.redirect(null, "비밀번호를 입력하세요.");
		}

		if (userName == "") {
			return webHelper.redirect(null, "이름를 입력하세요.");
		}

		if (userPhonenum == "") {
			return webHelper.redirect(null, "핸드폰번호를 입력하세요.");
		}

		if (userEmail == "") {
			return webHelper.redirect(null, "이메일을 입력하세요.");
		}

		if (userLevel == 0) {
			return webHelper.redirect(null, "유저 레벨을 등록해주세요.");
		}

		/** 2) 데이터 저장하기 */
		userBeans input = new userBeans();
		input.setUserId(userId);
		input.setUserPw(userPw);
		input.setUserName(userName);
		input.setUserPhonenum(userPhonenum);
		input.setUserBirthDate(userBirthDate);
		input.setUserEmail(userEmail);
		input.setUserZipcode(userZipcode);
		input.setUserAddress1(userAddress1);
		input.setUserAddress2(userAddress2);
		input.setReg_date(reg_date);
		input.setUserLevel(userLevel);
		// defalut 항목
		input.setUserAdmin("Y");

		try {
			userService.addAdmin(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		String redirectUrl = contextPath + "/admin/adminList";
		return webHelper.redirect(redirectUrl, "관리자등록이 완료되었습니다.");
	}

	@RequestMapping(value = "/registerSuccess", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView registerOk() {

		return new ModelAndView("account/registerSuccess");
	}

}