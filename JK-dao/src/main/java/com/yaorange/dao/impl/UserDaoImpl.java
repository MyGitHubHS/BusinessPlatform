package com.yaorange.dao.impl;


import com.yaorange.entity.Dept;
import com.yaorange.entity.User;
import com.yaorange.dao.UserDao;
import org.springframework.stereotype.Repository;
import utils.JPADaoUtils;
import utils.Pagination;


import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * @description: 持久层实现类
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 17-10-12 17:23:08
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private JPADaoUtils jpaDaoUtils;


    public User getOne(String id) {
        if(id==null){
            System.out.println("id为空");
            return null;
        }
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(String[] id) {
        for(String string: id){
            deleteById(string);
        }
    }


    public void delete(User user) {
        entityManager.remove(user);
    }


    public void deleteById(String id) {
        User user = getOne(id);
        if ( user!= null) {
            delete(user);
        }
    }

    @Override
    public void updataUser(User user) {
      jpaDaoUtils.updata(user,user.getId());
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public Pagination getPage(Integer pageNo, Integer pageSize, String likeName) {
        String hqlCount=null;
        String hql=null;
        if (likeName.equals("")){
            //获取总条数
            hqlCount ="select count(u.id) from User u";
        }else{
            //根据名字查询的条数
            hqlCount ="select count(u.id) from User u where u.userName like '%"+likeName+"%'";
        }

        Long count= (Long) this.entityManager.createQuery(hqlCount).getSingleResult();
        //创建分页对象,指定当前的页码，每页显示条数，数据总条数（查询）
        Pagination pagination = new Pagination(pageNo, pageSize, count.intValue());
        if (likeName.equals("")){
            //获取当前分页的内容
            hql = "select u from User u";
        }else {
            hql="select u from User u where u.userName like '%"+likeName+"%'";
        }

        Query query = this.entityManager.createQuery(hql);
        //设置开始位置:(pageNo - 1) * pageSize;
        query.setFirstResult(pagination.getFirstResult());
        //设置每次查询的条数
        query.setMaxResults(pageSize);
        List<User> list = query.getResultList();
        //将分页内容设置给分页对象
        pagination.setList(list);
        return  pagination;
    }
}
