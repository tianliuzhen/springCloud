package com.aaa.bootthymeleaf.web;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/28
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HtmlController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){

        return "test";
    }


    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(){
        return "success";
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public @ResponseBody  CommonResponseDto test(@RequestBody String request){
        TestRequestDto requestDto = JSONObject.parseObject(request, TestRequestDto.class);
        System.out.println(JSONObject.toJSONString(requestDto));
        return new CommonResponseDto().code(0).success(true).data(JSONObject.toJSONString(requestDto));
    }
}
