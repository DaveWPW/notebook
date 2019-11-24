package com.dave.notebook.service.impl;

import com.dave.notebook.entity.User;
import com.dave.notebook.mapper.UserMapper;
import com.dave.notebook.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.util.ShiroUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: Dave
 * @Date: 2019/11/9 0:03
 * @Description: TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int findUserByUserName(String username) {
        User params = new User();
        params.setUsername(username);
        return userMapper.selectCount(params);
    }

    @Override
    public int addUser(User user) {
        int rows = 0;
        String password = "12345678";
        String salt = UUID.randomUUID().toString();
        SimpleHash sHash = new SimpleHash("MD5", password, salt);
        user.setPassword(sHash.toHex());
        user.setPasswordSalt(salt);
        User createUser = ShiroUtil.getCurrentUser();
        user.setCreateUser(createUser.getUsername());
        user.setCreateTime(new Date());
        user.setModifyUser(createUser.getUsername());
        user.setModifyTime(new Date());
        user.setStatus(1);
        rows = userMapper.insert(user);
        return rows;
    }

    @Override
    public PageInfo<User> findUserList(Integer pageCurrent, String username) {
        PageHelper.startPage(pageCurrent, 10);
        PageInfo<User> pageInfo = null;
        if(StringUtils.isEmpty(username)){
            Example example = new Example(User.class);
            pageInfo = new PageInfo<>(userMapper.selectByExample(example));
        }else{
            pageInfo = new PageInfo<>(userMapper.findListByName(username));
        }
        return pageInfo;
    }

    @Override
    public User findUserById(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public int updateUser(User user) {
        int rows = 0;
        User params = new User();
        params.setUsername(user.getUsername());
        User userData = userMapper.selectOne(params);
        if(user.getIsRestPassword() == 1){
            String password = "12345678";
            SimpleHash sHash = new SimpleHash("MD5", password, userData.getPasswordSalt());
            user.setPassword(sHash.toHex());
        }
        User updateUser = ShiroUtil.getCurrentUser();
        user.setModifyUser(updateUser.getUsername());
        user.setModifyTime(new Date());
        rows = userMapper.updateByPrimaryKeySelective(user);
        return rows;
    }

    @Override
    public int deleteUser(int userId) {
        int rows = userMapper.deleteUser(userId);
        return rows;
    }

    @Override
    public int updatePassword(int userId, String password) {
        User params = new User();
        params.setUserId(userId);
        params.setPassword(password);
        return userMapper.updateByPrimaryKeySelective(params);
    }
}
