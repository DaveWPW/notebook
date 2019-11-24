package com.dave.notebook.mapper;

import com.dave.notebook.entity.Menu;
import common.tk.mybatis.MyMapper;
import common.vo.Node;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends MyMapper<Menu> {

    @Select("select permission from nb_menu where menu_id in (select menu_id from nb_role_menu where role_id = #{roleId})")
    List<String> getUserPermissions(@Param("roleId")int roleId);

    @Select("select * from nb_menu where menu_id in (select menu_id from nb_role_menu where role_id = #{roleId})")
    List<Menu> findUserMenuList(@Param("roleId")int roleId);

    @Select("select menu_id as id, menu_name as name, parent_id from nb_menu where status = 1")
    List<Node> findMenuNodes();

}