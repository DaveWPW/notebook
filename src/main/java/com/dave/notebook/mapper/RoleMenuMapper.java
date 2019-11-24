package com.dave.notebook.mapper;

import com.dave.notebook.entity.RoleMenu;
import common.tk.mybatis.MyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMenuMapper extends MyMapper<RoleMenu> {

    @Select("select menu_id from nb_role_menu where role_id = #{roleId}")
    List<Integer> findMenuIdsByRoleId(int roleId);

}