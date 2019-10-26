package com.yaorange.service.impl;

import com.yaorange.dao.ModelDao;
import com.yaorange.entity.Module;
import com.yaorange.service.ModelService;
import org.springframework.stereotype.Service;
import utils.Pagination;

import javax.annotation.Resource;

/**
 * @description: Model类逻辑业务层
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 21-10-20 21:24:14
 */
@Service
public class ModuleServiceImpl implements ModelService {
    @Resource
    private ModelDao modelDao;
    @Override
    public Pagination getPage(Integer pageNo, Integer pageSize, String likeName) {
        return  modelDao.getPage(pageNo, pageSize, likeName);

    }

    @Override
    public void addModel(Module model) {
        modelDao.addModel(model);
    }

    @Override
    public void delModel(String[] id) {
        modelDao.delModel(id);
    }

    @Override
    public void update(Module model) {
        modelDao.update(model);
    }
}
