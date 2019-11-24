package com.dave.notebook.mapper;

import com.dave.notebook.entity.Markdown;
import common.tk.mybatis.MyMapper;
import common.vo.Node;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MarkdownMapper extends MyMapper<Markdown> {

    @Select("select * from nb_markdown where create_user = #{username} and file_name like concat('%', #{fileName}, '%')")
    List<Markdown> findMarkdownListByName(@Param("username") String username, @Param("fileName") String fileName);

    @Select("select file_name from nb_markdown where create_user = #{username} and markdown_id = #{markdownId}")
    String findFileNameByMarkdownId(@Param("username") String username, @Param("markdownId") int markdownId);

}