package com.yaorange.service.impl;

import com.yaorange.service.UserService;
import com.yaorange.entity.User;
import com.yaorange.dao.UserDao;

import org.springframework.stereotype.Service;
import utils.Pagination;


import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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

    @Override
    public void deleteUser(String[] id) {
        userDao.deleteUser(id);
    }

    @Override
    public void updataUser(User user) {
        userDao.updataUser(user);
    }

    @Override
    public void addUser(User user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        try {
            Date parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userDao.addUser(user);
    }

    @Override
    public Pagination getPage(Integer pageNo, Integer pageSize, String likeName) {
      return userDao.getPage(pageNo,pageSize,likeName);
    }
}
