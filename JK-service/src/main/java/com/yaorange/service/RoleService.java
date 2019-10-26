package com.yaorange.service;

import com.yaorange.entity.Module;
import com.yaorange.entity.Role;
import com.yaorange.entity.User;
import utils.Pagination;
import utils.ZtreeVO;

import java.util.List;

public interface RoleService {
    Pagination getPage(Integer pageNo, Integer pageSize, String likeName);

    void addRole(Role role,Object... values);


    void updataRole(Role role,Object...values);

    void deleteRole(String[] id);

    List<ZtreeVO> getModeles();

    List<ZtreeVO> getModulesForUpdate(String id);
}
