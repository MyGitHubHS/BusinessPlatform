package com.yaorange.service;


import com.yaorange.entity.Dept;
import utils.Pagination;

public interface DeptService {


    void addDept(Dept dept);

    Pagination getPageDept(Integer pageNo, Integer pageSize,String likeName);

    void delDepts(String[] ids);

    void update(Dept dept);
}
