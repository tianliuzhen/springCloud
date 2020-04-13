package com.aaa.zuul.config.customBase;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/27
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.Assert;

import java.util.*;

public class CustomUserInfoTokenServices implements ResourceServerTokenServices {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private final String userInfoEndpointUrl;
    private final String clientId;
    private OAuth2RestOperations restTemplate;
    private String tokenType = "Bearer";
    private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();
    private PrincipalExtractor principalExtractor = new CustomPrincipalExtractor();

    public CustomUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
        this.userInfoEndpointUrl = userInfoEndpointUrl;
        this.clientId = clientId;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setRestTemplate(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setAuthoritiesExtractor(AuthoritiesExtractor authoritiesExtractor) {
        Assert.notNull(authoritiesExtractor, "AuthoritiesExtractor must not be null");
        this.authoritiesExtractor = authoritiesExtractor;
    }

    public void setPrincipalExtractor(PrincipalExtractor principalExtractor) {
        Assert.notNull(principalExtractor, "PrincipalExtractor must not be null");
        this.principalExtractor = principalExtractor;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        Map<String, Object> map = this.getMap(this.userInfoEndpointUrl, accessToken);
        if (map.containsKey("error")) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("userinfo returned error: " + map.get("error"));
            }

            throw new InvalidTokenException(accessToken);
        } else {
            return this.extractAuthentication(map);
        }
    }

    private OAuth2Authentication extractAuthentication(Map<String, Object> map) {
        Object principal = this.getPrincipal(map);
        List<GrantedAuthority> authorities = this.authoritiesExtractor.extractAuthorities(map);
        OAuth2Request request = new OAuth2Request((Map)null, this.clientId, (Collection)null, true, (Set)null, (Set)null, (String)null, (Set)null, (Map)null);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
        token.setDetails(map);
        return new OAuth2Authentication(request, token);
    }

    protected Object getPrincipal(Map<String, Object> map) {
        CustomPrincipal customPrincipal = new CustomPrincipal();
        if (map.containsKey("principal")) {
            Map<String, Object> principalMap = (Map)map.get("principal");
            customPrincipal.setEmail((String)principalMap.get("email"));
            customPrincipal.setId((Integer) principalMap.get("id"));
            customPrincipal.setTelephone((String)principalMap.get("telephone"));
            customPrincipal.setUsername((String)principalMap.get("username"));
            customPrincipal.setCode((String)principalMap.get("code"));
        }

        return customPrincipal;
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    private Map<String, Object> getMap(String path, String accessToken) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Getting user info from: " + path);
        }

        try {
            OAuth2RestOperations restTemplate = this.restTemplate;
            if (restTemplate == null) {
                BaseOAuth2ProtectedResourceDetails resource = new BaseOAuth2ProtectedResourceDetails();
                resource.setClientId(this.clientId);
                restTemplate = new OAuth2RestTemplate(resource);
            }

            OAuth2AccessToken existingToken = ((OAuth2RestOperations)restTemplate).getOAuth2ClientContext().getAccessToken();
            if (existingToken == null || !accessToken.equals(existingToken.getValue())) {
                DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
                token.setTokenType(this.tokenType);
                ((OAuth2RestOperations)restTemplate).getOAuth2ClientContext().setAccessToken(token);
            }

            return (Map)((OAuth2RestOperations)restTemplate).getForEntity(path, Map.class, new Object[0]).getBody();
        } catch (Exception var6) {
            this.logger.warn("Could not fetch user details: " + var6.getClass() + ", " + var6.getMessage());
            return Collections.singletonMap("error", "Could not fetch user details");
        }
    }

    public Log getLogger() {
        return this.logger;
    }

    public String getUserInfoEndpointUrl() {
        return this.userInfoEndpointUrl;
    }

    public String getClientId() {
        return this.clientId;
    }

    public OAuth2RestOperations getRestTemplate() {
        return this.restTemplate;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public AuthoritiesExtractor getAuthoritiesExtractor() {
        return this.authoritiesExtractor;
    }

    public PrincipalExtractor getPrincipalExtractor() {
        return this.principalExtractor;
    }
}
