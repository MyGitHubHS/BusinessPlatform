package com.yaorange.dao;

import com.yaorange.entity.Module;
import com.yaorange.entity.Role;
import utils.Pagination;

import java.util.List;

public interface RoleDao {
    Pagination getPage(Integer pageNo, Integer pageSize, String likeName);

    void addRole(Role role);

    void deleteRole(String[] id);

    void updataRole(Role role);

    List<Module> getModules();

    Role getOne(String id);

    Module getOneModule(String id);
}
