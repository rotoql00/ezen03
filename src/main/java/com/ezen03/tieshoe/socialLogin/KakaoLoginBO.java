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

@Slf4j
public class KakaoLoginBO {
    @Autowired
    WebHelper webHelper;

    private final static String CLIENT_ID = "4738bb0baa423601ea804b7e5e66b00d";
    private final static String REDIRECT_URI = "http://andrew.pe.kr/portfolio/kakaoCallback";
    private final static String SESSION_STATE = "oauth_state";

    /* 프로필 조회 API URL */
    private final static String PROFILE_API_URL = "https://kapi.kakao.com/v2/user/me";

    /* 카카오 아이디로 인증 URL 생성 Method */
    public String getAuthorizationUrl() {
        /* 세션 유효성 검증을 위하여 난수를 생성 */
        String state = generateRandomString();
        /* 생성한 난수 값을 session에 저장 */
        OAuth20Service oauthService = new ServiceBuilder(CLIENT_ID)
                .callback(REDIRECT_URI)
                .build(KakaoLoginApi.instance());
        log.info("=== KAKAO(카카오)s Async OAuth Workfolw ====");
        /* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
        log.info("Fetching the Authorization URL...");
        String authorizationUrl = oauthService.getAuthorizationUrl(state);
        log.info("Got the Authorize URL");
        return authorizationUrl;
    }

    /* 카카오아이디로 Callback 처리 및 AccessToken 획득 Method */
    public OAuth2AccessToken getAccessToken(String code) throws IOException {
        OAuth20Service oauthService = new ServiceBuilder(CLIENT_ID)
                .callback(REDIRECT_URI)
                .build(KakaoLoginApi.instance());
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


    /* 세션 유효성 검증을 위한 난수 생성기 */
    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    /* Access Token을 이용하여 카카오 사용자 프로필 API를 호출 */
    public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
        OAuth20Service oauthService = new ServiceBuilder(CLIENT_ID)
                .callback(REDIRECT_URI)
                .build(KakaoLoginApi.instance());
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
