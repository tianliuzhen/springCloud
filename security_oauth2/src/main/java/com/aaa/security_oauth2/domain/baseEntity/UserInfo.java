package com.aaa.security_oauth2.domain.baseEntity;

import lombok.Data;

import java.io.Serializable;

/**
 * description: 自定义用户
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/26
 */
@Data
public class UserInfo  implements Serializable {
    private static final long serialVersionUID = 12L;
    private Integer id;
    private String email;
    private String code;
    private String username;
    private String password;
    private String telephone;
}
