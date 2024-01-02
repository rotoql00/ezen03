package com.ezen03.tieshoe.socialLogin;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class KakaoLoginApi extends DefaultApi20 {
    protected KakaoLoginApi() {
    }

    private static class InstanceHolder {
        private static final KakaoLoginApi INSTANCE = new KakaoLoginApi();
    }

    public static KakaoLoginApi instance() {
        return InstanceHolder.INSTANCE;
    }

    // plugin 작동이 제대로 되지 않아 주소에 강제로 값을 입력했습니다
    @Override
    public String getAccessTokenEndpoint() {
        return "https://kauth.kakao.com/oauth/token?client_id=4738bb0baa423601ea804b7e5e66b00d";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://kauth.kakao.com/oauth/authorize";
    }
}
