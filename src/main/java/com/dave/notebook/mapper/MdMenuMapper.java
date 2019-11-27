package com.dave.notebook.mapper;

import com.dave.notebook.entity.MarkdownMenu;
import common.tk.mybatis.MyMapper;
import common.vo.Node;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MdMenuMapper extends MyMapper<MarkdownMenu> {

    @Select("select menu_id as id, menu_name as name, parent_id from nb_markdown_menu where create_user = #{username} and menu_type = 1")
    List<Node> findZtreeMenuNodes(@Param("username")String username);

    @Select("select menu_id as id, menu_name as name, parent_id from nb_markdown_menu where create_user = #{username}")
    List<Node> findZtreeMenuShow(@Param("username")String username);

    @Select("select mm.*,(select menu_name from nb_markdown_menu p where mm.parent_id = p.menu_id and p.create_user = #{username}) parentName, (select file_name from nb_markdown m where mm.markdown_id = m.markdown_id) fileName from nb_markdown_menu mm where mm.create_user = #{username}")
    List<MarkdownMenu> findMdMenuList(@Param("username")String username);

    @Select("select count(*) from nb_markdown_menu where create_user = #{username} and menu_name = #{menuName} and menu_id != #{menuId}")
    int selectCountMdMenu(@Param("username")String username, @Param("menuName")String menuName, @Param("menuId")Integer menuId);
}