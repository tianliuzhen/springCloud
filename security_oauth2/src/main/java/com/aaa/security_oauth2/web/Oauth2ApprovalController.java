package com.aaa.security_oauth2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/**
 * @author lvhaibao
 * @description 处理登录和授权的控制器  自定义授权的页面
 * @date 2018/12/26 0026 17:31
 */
@Slf4j
@Controller
@SessionAttributes({"authorizationRequest"})
public class Oauth2ApprovalController {
    /**
     * 自定义授权页面，注意：一定要在类上加@SessionAttributes({"authorizationRequest"})
     *
     * @param model   model
     * @param request request
     * @return String
     * @throws Exception Exception
     */
    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("confirmation");
        view.addObject("clientId", authorizationRequest.getClientId());
        System.out.println(authorizationRequest.getClientId());
        return view;
    }
}