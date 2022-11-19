package com.aaa.bootthymeleaf.web;

import com.aaa.bootthymeleaf.domain.User;
import com.aaa.bootthymeleaf.service.IUserService;
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
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 跳转到userList的页面
     * @return
     */
    @RequestMapping("/toUserList")
    public String toUserList(Model model) throws Exception {
        List<User> userList = userService.findUserAll();
        model.addAttribute("userList",userList);
        return "crud/userList";
    }


    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/deleteUser")
    public String deleteUser(Integer id){
        userService.deleteUserById(id);
        return "redirect:/toUserList";
    }

    /**
     * 跳转到添加数据的页面
     * @return
     */
    @RequestMapping("/toAddUser")
    public String toAddUser(){
        return "/crud/addUser";
    }

    /**
     * 跳转到更新页面
     * @return
     */
    @RequestMapping("/toEditUser")
    public String toUpdateUser(Integer id,Model model) throws Exception {
        User user=userService.findUserById(id);
        model.addAttribute("user",user);
        return "/crud/updateUser";
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(User user) throws Exception{
        userService.addUser(user);
        return "redirect:/toUserList";
    }


    /**
     * 通过id更新用户
     * @param user
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUser(User user) throws Exception {
        userService.updateUserById(user);
        return "redirect:/toUserList";
    }

}
