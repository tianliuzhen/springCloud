package com.aaa.security_oauth2.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/20
 */
@Data
public class User {
    private String name;
    private String username;
    private String password;
    private String tel;
    private LocalDateTime createTime;
}
