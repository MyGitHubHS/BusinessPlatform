package com.yaorange.dao;

import com.yaorange.entity.Dept;
import utils.Pagination;


public interface DeptDao {

    void addDept(Dept dept);


    Pagination getPageByHql(Integer pageNo, Integer pageSize, Object... values);
    void delete(Dept dept);
    void deleteById(String id);
    Dept getOne(String id);
    void update(Dept dept);
}
