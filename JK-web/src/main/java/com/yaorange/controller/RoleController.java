package com.yaorange.controller;

import com.sun.org.glassfish.gmbal.ParameterNames;
import com.yaorange.entity.Module;
import com.yaorange.entity.Role;
import com.yaorange.entity.User;
import com.yaorange.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.Pagination;
import utils.ZtreeVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 角色类处理类
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 21-10-20 21:17:35
 */
@Controller
@RequestMapping("role")
@ResponseBody
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping
    public Pagination getPage(Integer pageNo, Integer pageSize, String likeName){
        Pagination page = roleService.getPage(pageNo, pageSize, likeName);
        return page;
    }

    /**
    * @description:新增角色中查询所有模块以数结构形式展示
    * @param
    * @return java.util.List<utils.ZtreeVO>
    * @author 土匪
    * @date 2019/10/23 17:45
    * @exception
    */
    @GetMapping("/getModules")
    public List<ZtreeVO> getModules(){
        return roleService.getModeles();
    }


    /**
    * @description:更新角色查询对应模块
    * @param
    * @return java.util.List<utils.ZtreeVO>
    * @author 土匪
    * @date 2019/10/23 23:47
    * @exception
    */
    @GetMapping("/getModulesForUpdate")
    public List<ZtreeVO> getModulesForUpdate(String id){
        System.out.println(id);
        return roleService.getModulesForUpdate(id);
    }


    @PostMapping
    public String addRole(@RequestBody Role role, @RequestParam(value = "moduleIds" ,required = false) String[] moduleIds){
        if(moduleIds!=null||moduleIds.length!=0){
            System.out.println(moduleIds[0]+moduleIds[1]);
            roleService.addRole(role,moduleIds);

        }else {
            roleService.addRole(role);
        }
        return "1";
    }

    @PutMapping
    public String updataRole(@RequestBody Role role,@RequestParam(value = "moduleIds" ,required = false) String[] moduleIds){
        System.out.println(moduleIds);
        if(moduleIds!=null){
            System.out.println(moduleIds[0]+moduleIds[1]);
            roleService.updataRole(role,moduleIds);
        }else {
            roleService.updataRole(role);
        }
        return "1";
    }

    @DeleteMapping
    public String deletaUser(@RequestBody String[] id){
        roleService.deleteRole(id);
        return "1";
    }



}
