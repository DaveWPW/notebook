package com.dave.notebook.service;

import com.dave.notebook.entity.Markdown;
import common.vo.Node;

import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/9 15:55
 * @Description: TODO
 */
public interface MdMenuService {

    List<Markdown> findMdMenuList(String username);

    List<Node> findZtreeMenuNodes(String username);

    String findFileNameByMarkdownId(String username, int markdownId);

}
