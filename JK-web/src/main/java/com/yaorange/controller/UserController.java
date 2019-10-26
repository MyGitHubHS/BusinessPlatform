package com.yaorange.controller;

import com.yaorange.entity.Dept;
import com.yaorange.service.DeptService;
import com.yaorange.service.UserService;
import com.yaorange.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.Pagination;


import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 17-10-12 17:26:14
 */
@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;


    /**
    * @description:新增用户时进行查询所有部门名称
    * @param void
    * @return java.lang.String
    * @author 土匪
    * @date 2019/10/16 13:44
    * @exception
    */
    @RequestMapping("/getDepts")
    public List<Dept> getDepts(){
        Pagination pageDept = deptService.getPageDept(1, 100, "");
        return (List<Dept>)pageDept.getList();
    }

    /**
    * @description:获取user列表
    * @param pageNo 页码
    * @param pageSize 每页个数
    * @param likeName 查询名字
    * @return utils.Pagination
    * @author 土匪
    * @date 2019/10/20 20:07
    * @exception
    */
    @RequestMapping("getUsers")
    public Pagination getPage(Integer pageNo, Integer pageSize,String likeName){
        Pagination page = userService.getPage(pageNo, pageSize, likeName);
        List<User> list = (List<User>) page.getList();
        return page;
    }


    @PostMapping
    public String addUser(@RequestBody User user){
        System.out.println("获取前端上传的user");
        System.out.println(user);
        userService.addUser(user);
        return "1";
    }

    @PutMapping
    public String updataUser(@RequestBody User user){
        System.out.println(user);
        System.out.println("修改");
        userService.updataUser(user);
        return "1";
    }

    @DeleteMapping
    public String deletaUser(@RequestBody String[] id){
        System.out.println("删除");
        System.out.println(id);
        userService.deleteUser(id);
        return "1";
    }


}
