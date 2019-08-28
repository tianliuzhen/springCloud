package com.aaa.security_oauth2.domain.dto;

import lombok.Data;

import javax.persistence.Column;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/28
 */
@Data
public class UserLoginDTO {

    private String userName;

    private String password;

}
