package com.dave.notebook.mapper;

import com.dave.notebook.entity.Role;
import common.tk.mybatis.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends MyMapper<Role> {

    @Select("select * from nb_role where status = 1 and role_name like concat('%', #{roleName}, '%')order by role_id asc")
    List<Role> findListByName(@Param("roleName")String roleName);

    @Select("select username from nb_user where status = 1 and role_id = #{roleId}")
    List<String> findRoleUse(@Param("roleId") int roleId);

    @Select("select role_id, role_name from nb_role where status = 1")
    List<Map<String, Object>> findRoles();

}