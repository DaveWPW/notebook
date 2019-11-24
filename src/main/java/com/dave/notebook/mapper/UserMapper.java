package com.dave.notebook.mapper;

import com.dave.notebook.entity.User;
import common.tk.mybatis.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends MyMapper<User> {

    @Select("select * from nb_user where status = 1 and username like concat('%', #{username}, '%')order by role_id asc")
    List<User> findListByName(@Param("username")String username);

    @Update("update nb_user set status = 0 where user_id = #{userId}")
    int deleteUser(@Param("userId") int userId);

}