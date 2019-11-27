package com.dave.notebook.service;

import com.dave.notebook.entity.Markdown;
import com.dave.notebook.entity.MarkdownMenu;
import common.vo.Node;

import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/9 15:55
 * @Description: TODO
 */
public interface MdMenuService {

    List<MarkdownMenu> findMdMenuList(String username);

    List<Node> findZtreeMenuNodes(String username);

    List<Node> findZtreeMenuShow(String username);

    int addMdMenu(String username, MarkdownMenu markdownMenu);

    int selectConutMdMenu(String username, String menuName);

    int selectConutMdMenu(String username, Integer menuId);

    int selectConutMdMenu(String username, String menuName, Integer menuId);

    int updateMdMenu(String username, MarkdownMenu markdownMenu);

    int deleteMdMenu(Integer menuId);
}
