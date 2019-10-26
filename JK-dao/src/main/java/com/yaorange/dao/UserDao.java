package com.yaorange.dao;


import com.yaorange.entity.User;
import utils.Pagination;



public interface UserDao {

  Pagination getPage(Integer pageNo, Integer pageSize, String likeName);

    void addUser(User user);

  void updataUser(User user);

  void deleteUser(String[] id);
}
