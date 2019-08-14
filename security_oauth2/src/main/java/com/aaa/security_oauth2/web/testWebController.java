package com.aaa.security_oauth2.web;

import com.aaa.security_oauth2.aop.annotation.MyComponent;
import com.aaa.security_oauth2.aop.annotation.MyMethodsComponent;
import com.aaa.security_oauth2.entity.TestDTO;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/9
 */
@ToString
@RestController
public class testWebController {
    public  static TestDTO testDTO;
    public  static String str;

    @RequestMapping("/test")
    @MyMethodsComponent
    public String addData1() {
        System.out.println(testDTO);
        return "success_test";
    }

    @RequestMapping("/add1")
    @MyMethodsComponent
    public String addData1(String deviceId) {
        System.out.println(testDTO);
        return "success";
    }

    @RequestMapping("/add2")
    @MyMethodsComponent
    public String addData2(String deviceId) {
        System.out.println(testDTO);
        return "success";
    }
    @RequestMapping("/add3")
    @MyMethodsComponent
    public String addData3(String deviceId) {
        System.out.println(testDTO);
        System.out.println(str);
        System.out.println("热部署test");
        return "success";
    }
}
