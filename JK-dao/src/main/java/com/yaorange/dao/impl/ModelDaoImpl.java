package com.yaorange.dao.impl;

import com.yaorange.dao.ModelDao;
import com.yaorange.entity.Module;
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
 * @description: Model类持久层
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 21-10-20 21:25:22
 */
@Repository
public class ModelDaoImpl implements ModelDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private JPADaoUtils jpaDaoUtils;

    @Override
    public Pagination getPage(Integer pageNo, Integer pageSize, String likeName) {
        String hqlCount=null;
        String hql=null;
        if (likeName.equals("")){
            //获取总条数
            hqlCount ="select count(m.modelId) from Model m";
        }else{
            //根据名字查询的条数
            hqlCount ="select count(m.modelId) from Model m where m.modelName like '%"+likeName+"%'";
        }

        Long count= (Long) this.entityManager.createQuery(hqlCount).getSingleResult();
        //创建分页对象,指定当前的页码，每页显示条数，数据总条数（查询）
        Pagination pagination = new Pagination(pageNo, pageSize, count.intValue());
        if (likeName.equals("")){
            //获取当前分页的内容
            hql = "select m from Module m";
        }else {
            hql="select m from Module m where m.modelName like '%"+likeName+"%'";
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
    public void addModel(Module model) {
        entityManager.persist(model);
    }

    @Override
    public void delModel(String[] id) {
        for(String string: id){
            deleteById(string);
        }
    }

    @Override
    public void update(Module model) {
       jpaDaoUtils.updata(model,model.getId());
    }



    public Module getOne(String id) {
        return entityManager.find(Module.class, id);
    }




    public void delete(Module model) {
        entityManager.remove(model);
    }


    public void deleteById(String id) {
        Module model = getOne(id);
        if ( model!= null) {
            delete(model);
        }
    }

}
