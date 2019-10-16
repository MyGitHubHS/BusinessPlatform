package com.yaorange.controller;

import com.yaorange.service.UserService;
import com.yaorange.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

/**
 * @description:
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 17-10-12 17:26:14
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    /**
    * @description:
    * @param user
    * @return java.lang.String
    * @author 土匪
    * @date 2019/10/16 13:44
    * @exception
    */
    @RequestMapping("login")
    public String login(User user){
        System.out.println("login");
        System.out.println(user);
        user = userService.login(user);
        System.out.println(user);
        return "showUser";
    }

    @RequestMapping("login1")
    @ResponseBody
    public User login1(User user){
        System.out.println("login1");
        return userService.login(user);
    }
}
