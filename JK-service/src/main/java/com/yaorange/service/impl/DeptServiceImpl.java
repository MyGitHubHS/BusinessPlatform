package com.yaorange.service.impl;

import com.yaorange.dao.DeptDao;
import com.yaorange.entity.Dept;
import com.yaorange.service.DeptService;
import org.springframework.stereotype.Service;
import utils.Pagination;

import javax.annotation.Resource;


/**
 * @description: 关于部门的相关业务层
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 14-10-17 14:37:03
 */

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;

    /**
    * @description: 分页查询获取当前页
    * @param pageNo 当前页码
    * @param pageSize 每页有多少项
    * @param likeName 查询的名字
    * @return utils.Pagination
    * @author 土匪
    * @date 2019/10/21 14:54
    * @exception
    */
    @Override
    public Pagination getPageDept(Integer pageNo, Integer pageSize,String likeName) {
        return deptDao.getPageByHql(pageNo,pageSize,likeName);
    }


    /**
    * @description:添加部门
    * @param dept
    * @return int
    * @author 土匪
    * @date 2019/10/17 15:05
    * @exception
    */
    @Override
    public void addDept(Dept dept) {
        deptDao.addDept(dept);

    }



    @Override
    public void delDepts(String[] ids) { for(String id : ids){ deptDao.deleteById(id); } }


    @Override
    public void update(Dept dept) { deptDao.update(dept); }
}
