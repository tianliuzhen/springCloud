package com.aaa.security_oauth2.domain.baseEntity;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/27
 */
@Data
public class Principal {
    private  UserInfo userInfo;
    private  List<SimpleGrantedAuthority> authorities;
}
