package com.dave.notebook.mapper;

import com.dave.notebook.entity.Markdown;
import com.dave.notebook.entity.Menu;
import common.tk.mybatis.MyMapper;
import common.vo.Node;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MdMenuMapper extends MyMapper<Markdown> {

    @Select("select menu_num as id, menu_name as name, parent_num as parent_id from nb_markdown where create_user = #{username}")
    List<Node> findZtreeMenuNodes(@Param("username")String username);

    @Select("select m.*,(select menu_name from nb_markdown p where m.parent_num = p.menu_num and p.create_user = #{username})parentname from nb_markdown m where m.create_user = #{username}")
    List<Markdown> findMdMenuList(@Param("username")String username);

    @Select("select file_name from nb_markdown where create_user = #{username} and markdown_id = #{markdownId}")
    String findFileNameByMarkdownId(@Param("username")String username, @Param("markdownId")int markdownId);

}