package com.dave.notebook.service;

import com.dave.notebook.entity.User;
import com.github.pagehelper.PageInfo;

/**
 * @Author: Dave
 * @Date: 2019/11/9 0:03
 * @Description: TODO
 */
public interface UserService {

    int findUserByUserName(String username);

    int addUser(User user);

    PageInfo<User> findUserList(Integer pageCurrent, String username);

    User findUserById(int userId);

    int updateUser(User user);

    int deleteUser(int userId);

    int updatePassword(int userId, String password);

}
