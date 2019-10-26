package com.yaorange.service.impl;

import com.yaorange.dao.RoleDao;
import com.yaorange.entity.Module;
import com.yaorange.entity.Role;
import com.yaorange.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import utils.Pagination;
import utils.ZtreeVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description: Role类逻辑业务层
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 21-10-20 21:24:14
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /**
    * @description:新增时查询所有module
    * @param
    * @return java.util.List<utils.ZtreeVO>
    * @author 土匪
    * @date 2019/10/23 23:56
    * @exception
    */
    @Override
    public List<ZtreeVO> getModeles() {
        List<Module> moduleSet=roleDao.getModules();
        List<ZtreeVO> ztrees=new ArrayList<>(moduleSet.size());
        for (Module module : moduleSet){
            ZtreeVO ztreeVO = new ZtreeVO();
            ztreeVO.setId(module.getId());
            ztreeVO.setName(module.getName());
            //如果当前节点是根节点
            ztreeVO.setpId(module.getParentId()==null?"0":module.getParentId());
            ztreeVO.setChecked(false);
            ztreeVO.setOpen(true);
            ztrees.add(ztreeVO);
        }
        return ztrees;
    }

    /**
    * @description:修改时查询所有module和选中的对象的对应module做对比，所选对象有的则直接变为选中状态
    * @param id
    * @return java.util.List<utils.ZtreeVO>
    * @author 土匪
    * @date 2019/10/23 23:57
    * @exception
    */
    @Override
    public List<ZtreeVO> getModulesForUpdate(String id) {
        List<Module> moduleSet=roleDao.getModules();
        List<ZtreeVO> ztrees=new ArrayList<>(moduleSet.size());
        Role role=roleDao.getOne(id);
        Set<Module> moduleSet1 = role.getModuleSet();
        for (Module module : moduleSet){
            ZtreeVO ztreeVO = new ZtreeVO();
            ztreeVO.setId(module.getId());
            ztreeVO.setName(module.getName());
            //如果当前节点是根节点
            ztreeVO.setpId(module.getParentId()==null?"0":module.getParentId());
            //遍历role对象的module，如果有，则将选择状态置为true
            boolean flag=true;
            for(Module module1:moduleSet1){
                if(module.getId().equals(module1.getId())){
                    ztreeVO.setChecked(true);
                    flag=false;
                   break;
                }
            }
            if(flag){
                ztreeVO.setChecked(false);
            }
            ztreeVO.setOpen(true);
            ztrees.add(ztreeVO);
        }
        return ztrees;
    }

    @Override
    public Pagination getPage(Integer pageNo, Integer pageSize, String likeName) {
        return  roleDao.getPage(pageNo, pageSize, likeName);

    }

    @Override
    public void updataRole(Role role,Object...values) {
        if(values.length==0){
            role.setModuleSet(null);
        }else{
            for(String id:(String[]) values){
                Module module=roleDao.getOneModule(id);
                role.getModuleSet().add(module);
            }
        }
        roleDao.updataRole(role);
    }

    @Override
    public void deleteRole(String[] id) {
        roleDao.deleteRole(id);
    }

    /**
    * @description:新增角色
    * @param role 角色对象
    * @param values 新增时是否给角色赋Module，有values不为空，且第一个而参数为module的id数组
    * @return void
    * @author 土匪
    * @date 2019/10/23 17:56
    * @exception
    */
    @Override
    public void addRole(Role role,Object... values) {
        if(values!=null&&values.length!=0){
            for(String id:(String[]) values){
                Module module = new Module();
                module.setId(id);
                role.getModuleSet().add(module);
            }
        }
        roleDao.addRole(role);
    }
}
