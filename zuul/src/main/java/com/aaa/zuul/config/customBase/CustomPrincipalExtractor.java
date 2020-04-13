package com.aaa.zuul.config.customBase;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/26
 */

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import java.util.Map;

public class CustomPrincipalExtractor implements PrincipalExtractor {
    private static final String[] PRINCIPAL_KEYS = new String[]{"user", "username", "principal", "userid", "user_id", "login", "id", "name", "uuid", "code", "email", "telephone"};

    public CustomPrincipalExtractor() {
    }

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String[] var2 = PRINCIPAL_KEYS;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String key = var2[var4];
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }

        return null;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setForcePrincipalAsString(false);
        return daoAuthenticationProvider;
    }
}

