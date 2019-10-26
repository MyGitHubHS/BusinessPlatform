package com.yaorange.dao.impl;

import com.yaorange.dao.RoleDao;
import com.yaorange.entity.Module;
import com.yaorange.entity.Role;
import com.yaorange.entity.User;
import org.springframework.stereotype.Repository;
import utils.JPADaoUtils;
import utils.Pagination;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @description: Role持久层
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 21-10-20 21:25:22
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private JPADaoUtils jpaDaoUtils;

    /**
    * @description:查询所有模块
    * @param
    * @return java.util.List<com.yaorange.entity.Module>
    * @author 土匪
    * @date 2019/10/23 16:33
    * @exception
    */
    @Override
    public List<Module> getModules() {
        String hql="select m from Module m";
        Query query = this.entityManager.createQuery(hql);
        List<Module> modules = query.getResultList();
        return  modules;
    }

    @Override
    public Module getOneModule(String id) {
        Module module = this.entityManager.find(Module.class, id);
        return module;
    }

    @Override
    public Pagination getPage(Integer pageNo, Integer pageSize, String likeName) {
        String hqlCount=null;
        String hql=null;
        if (likeName==null||likeName.equals("")){
            //获取总条数
            hqlCount ="select count(r.id) from Role r";

        }else{
            //根据名字查询的条数
            hqlCount ="select count(r.id) from Role r where r.name like '%"+likeName+"%'";
        }

        Long count= (Long) this.entityManager.createQuery(hqlCount).getSingleResult();
        //创建分页对象,指定当前的页码，每页显示条数，数据总条数（查询）
        Pagination pagination = new Pagination(pageNo, pageSize, count.intValue());
        if (likeName==null||likeName.equals("")){
            //获取当前分页的内容
            hql = "select r from Role r";
        }else {
            hql="select r from Role r where r.name like '%"+likeName+"%'";
        }

        Query query = this.entityManager.createQuery(hql);
        //设置开始位置:(pageNo - 1) * pageSize;
        query.setFirstResult(pagination.getFirstResult());
        //设置每次查询的条数
        query.setMaxResults(pageSize);
        List<User> list = query.getResultList();
        //将分页内容设置给分页对象
        pagination.setList(list);
        return pagination;
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void deleteRole(String[] id) {
        for(String string: id){
            deleteById(string);
        }
    }

    @Override
    public void updataRole(Role role) {
        //jpaDaoUtils.updata(role,role.getId());
       entityManager.merge(role);
    }

    @Override
    public Role getOne(String id) {
        if(id!=null){

            return entityManager.find(Role.class, id);
        }
        return null;
    }




    public void delete(Role role) {
        entityManager.remove(role);
    }


    public void deleteById(String id) {
        Role role = getOne(id);
        if ( role!= null) {
            delete(role);
        }
    }

}
