package com.ezen03.tieshoe.socialLogin;

import com.ezen03.tieshoe.helper.WebHelper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpSession;


@Slf4j
public class NaverLoginBO {

    @Autowired
    WebHelper webHelper;
    /* 인증 요청문을 구성하는 파라미터 */
//client_id: 애플리케이션 등록 후 발급받은 클라이언트 아이디
//response_type: 인증 과정에 대한 구분값. code로 값이 고정돼 있습니다.
//redirect_uri: 네이버 로그인 인증의 결과를 전달받을 콜백 URL(URL 인코딩). 애플리케이션을 등록할 때 Callback URL에 설정한 정보입니다.
//state: 애플리케이션이 생성한 상태 토큰


    private final static String CLIENT_ID = "1jWueGaqcGgC4VeGbsmu";
    private final static String CLIENT_SECRET = "uepSN6Sh43";
    private final static String REDIRECT_URI = "http://andrew.pe.kr/portfolio/naverCallback";
    private final static String SESSION_STATE = "oauth_state";

    /* 프로필 조회 API URL */
    private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";

    /* 네이버 아이디로 인증 URL 생성 Method */
    public String getAuthorizationUrl() {
        /* 세션 유효성 검증을 위하여 난수를 생성 */
        String state = generateRandomString();
        /* 생성한 난수 값을 session에 저장 */
        webHelper.setSession("state", state);
        OAuth20Service oauthService = new ServiceBuilder(CLIENT_ID)
                .callback(REDIRECT_URI)
                .build(NaverLoginApi.instance());

        log.info("=== NAVER(네이버)s Async OAuth Workfolw ===");
        /* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
        log.info("Fetching the Authorization URL...");
        String authorizationUrl = oauthService.getAuthorizationUrl(state);
        log.info("Got the Authorize URL");
        return authorizationUrl;
    }

    /* 네이버아이디로 Callback 처리 및 AccessToken 획득 Method */
    public OAuth2AccessToken getAccessToken(String code, String state) throws IOException {
        /* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
        String sessionState = (String) webHelper.getSession("state");
        log.info("-------------------------------------------------------------------------------");
        log.info(sessionState);
        log.info("-------------------------------------------------------------------------------");
        if (StringUtils.pathEquals(sessionState, state)) {
            OAuth20Service oauthService = new ServiceBuilder(CLIENT_ID)
                    .apiSecret(CLIENT_SECRET)
                    .callback(REDIRECT_URI)
                    .debug()
                    .build(NaverLoginApi.instance());
            /* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
            OAuth2AccessToken accessToken = null;
            try {
                accessToken = oauthService.getAccessToken(code);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return accessToken;
        }
        return null;
    }

    /* 세션 유효성 검증을 위한 난수 생성기 */
    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    /* Access Token을 이용하여 네이버 사용자 프로필 API를 호출 */
    public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
        OAuth20Service oauthService = new ServiceBuilder(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI)
                .build(NaverLoginApi.instance());
        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL);
        oauthService.signRequest(oauthToken, request);
        Response response = null;
        try {
            response = oauthService.execute(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }
}