package com.ezen03.tieshoe;

import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.model.kakao.kakaoBeans;
import com.ezen03.tieshoe.model.naverLoginBeans;
import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.socialLogin.KakaoLoginBO;
import com.ezen03.tieshoe.socialLogin.NaverLoginBO;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Call;

import java.io.IOException;
import java.text.ParseException;

@Slf4j
@Controller
public class SocialLoginController {

    /* KakaoLoginBO */
    private NaverLoginBO naverLoginBO;
    private KakaoLoginBO kakaoLoginBO;
    private String apiResult = null;

    @Autowired
    WebHelper webHelper;

    @Value("#{servletContext.contextPath}")
    String contextPath;

    @Autowired
    private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }

    @Autowired
    private void setKakaoLoginBO(KakaoLoginBO kakaoLoginBO) {
        this.kakaoLoginBO = kakaoLoginBO;
    }


    //https://localhost:8080/naverLogin?code=ytWDu4ARpfQksraYdQ&state=c2131fef-b547-4f35-b938-529b7312b071
    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/naverCallback", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView naverCallback(Model model, @RequestParam String code, @RequestParam String state) throws IOException, ParseException {
        log.info("토큰을 생성중...");
        OAuth2AccessToken oauthToken;
        log.info("토큰을 가져오는중");
        oauthToken = naverLoginBO.getAccessToken(code, state);
        //1. 로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
        log.info(apiResult);

        //우선 제이슨 파싱 객체를 받고, 제이슨을 오브젝트로 업캐시팅하고, 그걸 제이슨오브젝트로 다운캐스팅한다.
        JSONParser parsing = new JSONParser();
        Object obj = null;
        try {
            obj = parsing.parse(apiResult.toString());
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject responseObj = (JSONObject) jsonObject.get("response");
        String name = (String) responseObj.get("name");
        String email = (String) responseObj.get("email");
        String id = (String) responseObj.get("id");
        String nickName = (String) responseObj.get("nickname");

        return webHelper.redirect(contextPath + "/naverLogin?id=" + id + "&name=" + name + "&email=" + email + "&nickname=" + nickName, null);
    }

    @RequestMapping(value = "/kakaoCallback", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView kakaoCallback(Model model, @RequestParam String code) throws IOException {
        log.info("토큰을 생성중...");
        log.info("코드: " + code);
        OAuth2AccessToken oauthToken;
        log.info("토큰을 가져오는중...");
        oauthToken = kakaoLoginBO.getAccessToken(code);
        apiResult = kakaoLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
        log.info(apiResult);

        //Gson으로 json 가져오기
        Gson gson = new Gson();
        kakaoBeans kakao = gson.fromJson(apiResult, kakaoBeans.class);
        log.info(String.valueOf(kakao.getId()));
        log.info(String.valueOf(1));

        String id = String.valueOf(kakao.getId()); //카카오 고유 아이디
        String nickName = kakao.getProperties().getNickname(); // 카카오에서 제공되는 사용자의 닉네임
        String email = kakao.getKakaoAccount().getEmail(); // 카카오에서 제공되는 이메일

        if (nickName == null || email == null) {
            return webHelper.redirect(contextPath + "/login", "죄송합니다, 현재 상태가 개발중이라... 밑에 선택사항을 체크해주셔야합니다.");
        }

        log.info(id);
        log.info(nickName);
        log.info(email);

        return webHelper.redirect(contextPath + "/kakaoLogin?id=" + id + "&nickName=" + nickName + "&email=" + email, null);
    }
}
