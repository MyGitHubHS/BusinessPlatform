package com.yaorange.dao.impl;

import com.yaorange.dao.DeptDao;
import com.yaorange.entity.Dept;
import org.springframework.stereotype.Repository;
import utils.JPADaoUtils;
import utils.Pagination;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @description: 关于Dept的持久层实现类
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 14-10-17 14:41:30
 */

@Repository
public class DeptDaoImpl implements DeptDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private JPADaoUtils jpaDaoUtils;

    @Override
    public Pagination getPageByHql(Integer pageNo, Integer pageSize, Object... values) {
        String name="deptName";
        Pagination pageByHql = jpaDaoUtils.getPageByHql(pageNo, pageSize, Dept.class, values[0], name);
        return pageByHql;
//        String hqlCount=null;
//        String hql=null;
//        if (values[0].equals("")){
//            //获取总条数
//            hqlCount ="select count(d.deptId) from Dept d";
//        }else{
//            //根据名字查询的条数
//            hqlCount ="select count(d.deptId) from Dept d where d.deptName like '%"+values[0]+"%'";
//        }
//
//        Long count= (Long) this.entityManager.createQuery(hqlCount).getSingleResult();
//        //创建分页对象,指定当前的页码，每页显示条数，数据总条数（查询）
//        Pagination pagination = new Pagination(pageNo, pageSize, count.intValue());
//        if (values[0].equals("")){
//            //获取当前分页的内容
//            hql = "select d from Dept d";
//        }else {
//            hql="select d from Dept d where d.deptName like '%"+values[0]+"%'";
//        }
//
//       Query query = this.entityManager.createQuery(hql);
//        //设置开始位置:(pageNo - 1) * pageSize;
//        query.setFirstResult(pagination.getFirstResult());
//        //设置每次查询的条数
//        query.setMaxResults(pageSize);
//        List<Dept> list = query.getResultList();
//        //将分页内容设置给分页对象
//        pagination.setList(list);
//        return pagination;
    }

    @Override
    public void delete(Dept dept) {
        entityManager.remove(dept);
    }

    @Override
    public void deleteById(String id) {
        Dept dept = getOne(id);
        if (dept != null) {
            delete(dept);
        }
    }

    @Override
    public Dept getOne(String id) {
        return entityManager.find(Dept.class, id);
    }

    @Override
    public void update(Dept dept){
//        Dept dept1 = getOne(dept.getDeptId());
//        dept1.setDeptName(dept.getDeptName());
//        dept1.setState(dept.getState());
//        entityManager.merge(dept1);
        jpaDaoUtils.updata(dept,dept.getDeptId());
    }


    @Override
    public void addDept(Dept dept) {
        entityManager.persist(dept);
    }
}
