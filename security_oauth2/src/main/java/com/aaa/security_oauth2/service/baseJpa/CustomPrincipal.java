package com.aaa.security_oauth2.service.baseJpa;

import java.beans.ConstructorProperties;
import java.io.Serializable;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/26
 */
public class CustomPrincipal implements Serializable {
    private static final long serialVersionUID = -5602992002925539036L;
    private String id;
    private String email;
    private String code;
    private String username;
    private String telephone;

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCode() {
        return this.code;
    }

    public String getUsername() {
        return this.username;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public CustomPrincipal() {
    }

    @ConstructorProperties({"id", "email", "code", "username", "telephone"})
    public CustomPrincipal(String id, String email, String code, String username, String telephone) {
        this.id = id;
        this.email = email;
        this.code = code;
        this.username = username;
        this.telephone = telephone;
    }
}
