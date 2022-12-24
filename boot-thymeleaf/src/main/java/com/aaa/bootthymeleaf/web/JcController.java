package com.aaa.bootthymeleaf.web;

import com.aaa.bootthymeleaf.domain.JcModel;
import com.aaa.bootthymeleaf.service.JcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 UserController.java  2022/11/19 19:16
 */
@Controller
public class JcController {

    @Autowired
    private JcService jcService;

    /**
     * 跳转到List的页面
     * @return
     */
    @RequestMapping("/toJcList")
    public String toUserList(Model model) throws Exception {
        List<JcModel> userList = jcService.findUserAll();
        model.addAttribute("userList",userList);
        return "crud/userList";
    }


    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/jcUser")
    public String deleteUser(Integer id){
        jcService.deleteUserById(id);
        return "redirect:/toUserList";
    }

    /**
     * 跳转到添加数据的页面
     * @return
     */
    @RequestMapping("/toAddJc")
    public String toAddUser(){
        return "/crud/addJc";
    }

    /**
     * 跳转到更新页面
     * @return
     */
    @RequestMapping("/toEditJc")
    public String toUpdateUser(Integer id,Model model) throws Exception {
        JcModel user=jcService.findUserById(id);
        model.addAttribute("user",user);
        return "/crud/updateUser";
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/addJc")
    public String addUser(JcModel user) throws Exception{
        jcService.addUser(user);
        return "redirect:/toUserList";
    }


    /**
     * 通过id更新用户
     * @param user
     * @return
     */
    @RequestMapping("/updateJc")
    public String updateUser(JcModel user) throws Exception {
        jcService.updateUserById(user);
        return "redirect:/toUpdateJc";
    }

}
