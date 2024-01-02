package com.ezen03.tieshoe;

import com.ezen03.tieshoe.helper.MailHelper;
import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.RetrofitHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.model.userReset;
import com.ezen03.tieshoe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ResetController {

	@Autowired
	WebHelper webHelper;

	@Autowired
	RegexHelper regexHelper;

	@Autowired
	MailHelper mailHelper;

	@Autowired
	RetrofitHelper retrofitHelper;

	@Autowired
	UserService userService;

	@Value("#{servletContext.contextPath}")
	String contextPath;

	// 이메일 확인 창
	@RequestMapping(value = "/resetEmail.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String resetPW(Model model) {

		return "changePW/sendPW";
	}

	@RequestMapping(value = "/reset.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView reset(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(required = true) String user_ID, @RequestParam(required = true) String Email) {

		if (user_ID == null || Email == null) {
			return webHelper.redirect(null, "회원정보를 가지고 오는도중 에러가 발생했습니다.");
		}

		userBeans input = new userBeans();
		userReset inputReset = new userReset();

		input.setUserId(user_ID);
		input.setUserEmail(Email);

		userBeans valid = null;
		int success = 0;

		try {
			valid = userService.checkIDEamil(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (valid == null) {
			return webHelper.redirect(null, " 회원정보를 조회할 수 없습니다.");
		}
		
		 if (valid.getUserdropTF().equals("Y")) { // 탈퇴회원일 경우 에러 메시지
	            return webHelper.redirect(contextPath + "/login", "회원 정보가 없습니다. 아이디와 비밀번호를 확인해주세요.");
		 }
		 
		// Key 값 생성 하고 INSERT
		byte[] array = new byte[8]; // length is bounded by 8
		new Random().nextBytes(array);
		String randomCode = createRandomCode(20, "qwertyuiopasdfghjklzxcvbnm1234567890");

		// 사용자 번호 + 시간(ms) + 랜덤 코드 조합으로 암호키를 생성합니다.
		String randomString = valid.getID() + "T" + randomCode;

		inputReset.setEmail_key(randomString);
		inputReset.setUser_ID(valid.getID());

		try {
			success = userService.insertKey(inputReset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 아이디 마스킹
		String ID_masking = user_ID.replace("(?<=.{3}).", "*");

		// 로컬 호스트 (로컬 서버 실행을 위한 String 입니다.
		String localHost = "http://localhost:8080";

		// 링크 경로 가져오기
		String contextPath = request.getContextPath();

		// 이메일 페이지 (NAVE; YAHOO... 가져오기)
		String Email_carrier = Email.substring(Email.lastIndexOf("@") + 1);

		// 이메일의 제목
		String subject = "TIE SHOE || 비밀번호 변경";

		// 비밀번호 변경 주소 페이지
		String link = localHost + contextPath + "/changePW?auth=" + randomString;

		String emailContent = emailForm(link, user_ID);
		// 이메일을 주소를 포함해서 전송합니다.
		try {
			mailHelper.sendMail(Email, subject, emailContent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.setAttribute("user_ID", ID_masking);
		session.setAttribute("Email_carrier", Email_carrier);
		session.setAttribute("Email", Email);
		session.setAttribute("ID", valid.getID());

		String redirectUrl = contextPath + "/resetEmail.do";

		return webHelper.redirect(redirectUrl, null);
	}

	public String emailForm(String link, String ID) {
		return "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <title>Title</title>\n" + "</head>\n"
				+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css\"/>\n"
				+ "<style>\n" + "    body {\n" + "        width: 70%;\n" + "        margin: auto;\n"
				+ "        height: 1000px;\n" + "    }\n" + "\n" + "    .title {\n" + "        font-size: 50px;\n"
				+ "        color: black;\n" + "        text-align: center;\n" + "    }\n" + "\n" + "    .sub_title {\n"
				+ "        text-align: center;\n" + "        color: black;\n" + "        font-size: 20px;\n"
				+ "        margin-bottom: 30px;\n" + "    }\n" + "\n" + "    .context {\n"
				+ "        margin-bottom: 70px;\n" + "    }\n" + "\n" + "    .space {\n" + "        padding: 40px;\n"
				+ "    }\n" + "\n" + "    p {\n" + "        word-break: keep-all;\n" + "    }\n" + "\n"
				+ "    .center {\n" + "        text-align: center;\n" + "    }\n" + "\n" + "</style>\n" + "\n"
				+ "<body>\n" + "<div class=\"header\">\n" + "    <div class=\"title\">TIE SHOE</div>\n"
				+ "    <div class=\"sub_title\">Connect C2C</div>\n" + "</div>\n" + "<div class=\"container\">\n"
				+ "    <div class=\"uk-child-width-1-1@s uk-grid-match\" uk-grid>\n"
				+ "        <div class=\"uk-card uk-card-secondary uk-card-hover uk-card-body uk-light\">\n"
				+ "            <h3 class=\"uk-card-title\">" + ID + "님, 비밀번호를 분실하셨나요?</h3>\n"
				+ "            <p class=\"context\">아래있는 버튼을 클릭해서 비밀번호를 변경해주세요.</p>\n" + "            <a href=\"" + link
				+ " \"><button class=\"uk-width-1-3 uk-button uk-button-default\">비밀번호 변경</button></a>\n"
				+ "            <div class=\"space\"></div>\n" + "            <p>" + ID
				+ "님이 아니신가요? <a href=\"\">여기를 클릭해주세요</a></p>\n"
				+ "            <div>비밀번호를 신청하신적이 없으시다면 비밀번호를 변경후 문의주세요.</div>\n" + "\n" + "        </div>\n"
				+ "    </div>\n" + "\n" + "\n" + "    <div class=\"uk-child-width-1-1@s uk-grid-match\" uk-grid>\n"
				+ "        <div class=\"uk-card uk-card-secondary uk-card-hover uk-card-body uk-light center\">\n"
				+ "            <p>Tieshoe 타이슈 서울특별시 서초구,1303-37 서초 W타워 13층  |  고객센터 02-123-5678  |  tieshoec2c@gmail.com</p>\n"
				+ "            <div>Copyright 2020©. tieshoe, All rights reserved.</div>\n" + "\n" + "        </div>\n"
				+ "    </div>\n" + "</div>\n" + "\n" + "<!-- UIkit JS -->\n"
				+ "<script src=\"https://cdn.jsdelivr.net/npm/uikit@3.3.1/dist/js/uikit.min.js\"></script>\n"
				+ "<script src=\"https://cdn.jsdelivr.net/npm/uikit@3.3.1/dist/js/uikit-icons.min.js\"></script>\n"
				+ "</body>\n" + "</html>";
	}

	// 랜덤키 만들기
	public String createRandomCode(int codeLength, String id) {
		List<Character> temp = id.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
		Collections.shuffle(temp, new SecureRandom());
		return temp.stream().map(Object::toString).limit(codeLength).collect(Collectors.joining());
	}

	// 사용자 validation
	@RequestMapping(value = "/changePW", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView find_id(Model model, HttpServletRequest request, @RequestParam(required = true) String auth) {

		userReset resetOutput = null;

		// 암호화 풀기 (ID 과 encrypted key)
		// encrypted Key
		String key = auth.substring(auth.lastIndexOf("T") + 1);

		// UserNum
		String IDString = auth.substring(0, auth.indexOf("T"));

		// 형변환
		int ID = Integer.parseInt(IDString);

		// set ID to java beans
		userReset input = new userReset();
		input.setUser_ID(ID);

		try {
			resetOutput = userService.check(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String authKey = resetOutput.getEmail_key();

		// 링크 경로 가져오기
		String contextPath = request.getContextPath();

		String home = contextPath + "/";

		// 키 검사
		if (!authKey.equals(auth)) {
			return webHelper.redirect(home, "잘못된 접근입니다.");
		}

		model.addAttribute("ID", resetOutput);
		webHelper.setSession("CPWID",ID);

		return new ModelAndView("changePW/changePW");
	}

	@RequestMapping(value = "/resetAll", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView resetPassWord(Model model, HttpServletRequest request,
			@RequestParam(required = true) String password) {

		return new ModelAndView("changePW/changePW");
	}

	// 비밀번호 변경
	@RequestMapping(value = "/changPW.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView changPW(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ID", defaultValue = "0", required = false) int ID,
			@RequestParam(value = "userPw", defaultValue = "null", required = false) String userPw) {

		ID = (Integer) webHelper.getSession("CPWID");
		
		if (ID == 0) {
			webHelper.redirect(null, "세션 만료 다시 발급 신청해주세요.");
		}
		
		if (userPw == null) {
			webHelper.redirect(null, "변경할 비밀번호가 없습니다.");
		}

		userBeans input = new userBeans();
		input.setUserPw(userPw);
		input.setID(ID);

		try {
			userService.changeUserPw(input);
			 webHelper.removeAllSession();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String redirectUrl = contextPath;
		return webHelper.redirect(redirectUrl, "비밀번호 변경 완료. 재로그인 해주세요.");	
		//return new ModelAndView(contextPath + "/");
	}

}
