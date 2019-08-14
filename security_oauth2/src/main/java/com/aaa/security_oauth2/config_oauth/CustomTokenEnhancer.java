package com.aaa.security_oauth2.config_oauth;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * description: token自定义
 *
 * @version 1.0
 * @date 2019/8/4
 */

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        if (oAuth2AccessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) oAuth2AccessToken);
            token.setValue(getTypeToken(token.getValue(),oAuth2Authentication.getOAuth2Request().getClientId()));
            Map<String, Object> additionalInformation = new HashMap<String, Object>();
            additionalInformation.put("client_id", oAuth2Authentication.getOAuth2Request().getClientId());
            additionalInformation.put("username", oAuth2Authentication.getName());
            token.setAdditionalInformation(additionalInformation);

            return token;
        }
        return oAuth2AccessToken;
    }

    private String getTypeToken(String token,String clientId) {
        if(clientId.contains("demo")){
            return "demo_"+token;
        } else {
            return "real_" + token;
        }
    }
}

