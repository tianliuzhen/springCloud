package com.aaa.security_oauth2.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Create date 2017/6/8
 * <p>授权服务器入口</p>
 *
 * @author zhiheng.li
 * @since 1.0.0
 */
@Controller
@RequestMapping("/")
public class DashboardController {


//    @Value("${site.oauth.website}")

    private String website="http://localhost:8080.com";
    /**
     * 客户端首页
     */
    @RequestMapping(value = "/hello.html")
    public String HelloWorld() {
        return "hello world";
    }

    @GetMapping
    public String index() {

        return "redirect:" +  website;
    }


    /**
     * session清除接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/oauth/removesession")
    @CrossOrigin
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (session != null)
            session.invalidate();

        return "logout";
    }

    /**
     * 第三方退出过度
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/oauth/logout")
    @CrossOrigin
    public String thirdLogout(HttpServletRequest request, Model model) {

        model.addAttribute("callBack", website);



        HttpSession session = request.getSession();

        if (session != null)
            session.invalidate();

        return "login";
    }


}
