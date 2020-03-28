package com.aaa.bootthymeleaf.web;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/28
 */
import com.aaa.bootthymeleaf.domain.CommonResponseDto;
import com.aaa.bootthymeleaf.domain.TestRequestDto;
import com.aaa.bootthymeleaf.domain.ThObject;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;


@Controller
public class HtmlController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        // 这里的路径指的是   resources/templates/test.html
        return "/test";
    }

  /*  @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(){
        return "success";
    }
*/
    @RequestMapping("/thymeleaf")
    public String thymeleaf(ModelMap map) {
        map.put("thText", "th:text 设置文本内容 <b>加粗</b>");
        map.put("thUText", "th:utext 设置文本内容 <b>加粗</b>");
        map.put("thValue", "thValue 设置当前元素的value值");
        map.put("thEach", Arrays.asList("th:each", "遍历列表"));
        map.put("thIf", "msg is not null");
        map.put("thObject", new ThObject(1, "th:object", "用来偷懒的th属性"));
        return "grammar/thymeleaf";
    }

    @RequestMapping(value = "/successV2", method = RequestMethod.GET)
    public ModelAndView success(HttpSession session){
        ModelAndView model = new ModelAndView();
        model.addObject("name", "tom");
        model.setViewName("success");
        session.setAttribute("sessionName", "session-tom");
        return model;
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String successV3(Map map){
        map.put("name", "tom");
        return "success";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public @ResponseBody
    CommonResponseDto test(@RequestBody String request){
        TestRequestDto requestDto = JSONObject.parseObject(request, TestRequestDto.class);
        System.out.println(JSONObject.toJSONString(requestDto));
        return new CommonResponseDto().code(0).success(true).data(JSONObject.toJSONString(requestDto));
    }
}
