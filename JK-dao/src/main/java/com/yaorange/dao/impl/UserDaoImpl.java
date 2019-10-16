package com.yaorange.dao.impl;


import com.yaorange.entity.User;
import com.yaorange.dao.UserDao;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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
        @Override
        public User selectUser(User user) {
//        //原生SQL语句
            String sql = "select * from tb_user where id =:us and name=:ps";
            Query query1 = entityManager.createNativeQuery(sql, User.class);
            query1.setParameter("us", user.getId());
            query1.setParameter("ps", user.getName());
            return (User) query1.getResultList().get(0);
        }
}
