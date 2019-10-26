package com.yaorange.dao;


import com.yaorange.entity.Module;
import utils.Pagination;

public interface ModelDao {
    Pagination getPage(Integer pageNo, Integer pageSize, String likeName);

    void addModel(Module model);

    void delModel(String[] id);

    void update(Module model);
}
