package com.yaorange.service.impl;

import com.yaorange.service.UserService;
import com.yaorange.entity.User;
import com.yaorange.dao.UserDao;

import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 业务层实现类
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 17-10-12 17:20:33
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    private Set HashSet=new HashSet();

    @Override
    public User login(User user) {
        return userDao.selectUser(user);
    }
}
