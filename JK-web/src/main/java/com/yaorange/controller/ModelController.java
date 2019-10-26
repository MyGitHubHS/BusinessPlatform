package com.yaorange.controller;

import com.yaorange.entity.Module;
import com.yaorange.service.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.Pagination;

import javax.annotation.Resource;

/**
 * @description: 部门相关处理
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 14-10-17 14:29:30
 */
@Controller
@RequestMapping("/model")
@ResponseBody
public class ModelController {

    @Resource
    private ModelService modelService;

    /**
    * @description:分页查询展示
    * @param pageNo 页数
    * @param pageSize 每页个数
    * @return utils.Pagination
    * @author 土匪
    * @date 2019/10/19 15:55
    * @exception
    */
    @GetMapping// @RequestMapping(method = RequestMethod.GET)
    public Pagination getAll(Integer pageNo, Integer pageSize,String likeName) {
        Pagination pageDept = modelService.getPage(pageNo, pageSize, likeName);
        return pageDept;
    }

    /**
    * @description:添加部门
    * @param dept 部门对象
    * @return java.lang.String
    * @author 土匪
    * @date 2019/10/19 15:57
    * @exception
    */
    @PostMapping
    public String add(@RequestBody Module model) {
        modelService.addModel(model);
        return "1";
    }

    /**
    * @description: 删除
    * @param id 删除对象的id
    * @return java.lang.String
    * @author 土匪
    * @date 2019/10/19 15:57
    * @exception
    */
    @DeleteMapping
    public String del(@RequestBody String[] id) {
        modelService.delModel(id);
        return "1";
    }

    /**
    * @description: 修改
    * @param dept 修改数据后的dept对象
    * @return java.lang.String
    * @author 土匪
    * @date 2019/10/19 15:58
    * @exception
    */
    @PutMapping
    public String update(@RequestBody Module model) {
        modelService.update(model);
        return "1";
    }


}
