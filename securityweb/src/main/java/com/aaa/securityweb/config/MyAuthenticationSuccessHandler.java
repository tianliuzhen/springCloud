package com.aaa.securityweb.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 处理登录成功的。
 * description: 项目有不同客户端需要不同的返回界面,
 *  比如Android的登录返回json格式数据.网页登录跳转到登录成功页面.
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/1
 */

@Component("MyAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {

        //这里可以根据实际情况，来确定是跳转到页面或者json格式。
        //如果是返回json格式，那么我们这么写
        String f = request.getParameter("f");
        if (StringUtils.isNotBlank(f)) {
            if(f.equals("android")){
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("登录成功");
            }

        }else{

            //request.getRequestDispatcher("/whoim").forward(request, response);
            //如果是要跳转到某个页面的，比如我们的那个whoim的则
            new DefaultRedirectStrategy().sendRedirect(request, response, "/whoim");

        }
    }
}
