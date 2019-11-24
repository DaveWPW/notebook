package com.dave.notebook.service.impl;

import com.dave.notebook.entity.Markdown;
import com.dave.notebook.entity.MarkdownMenu;
import com.dave.notebook.entity.Menu;
import com.dave.notebook.mapper.MdMenuMapper;
import com.dave.notebook.service.MdMenuService;
import common.vo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/23 12:16
 * @Description: TODO
 */
@Service
public class MdMenuServiceImpl implements MdMenuService {

    @Autowired
    private MdMenuMapper mdMenuMapper;

    @Override
    public List<MarkdownMenu> findMdMenuList(String username) {
        List<MarkdownMenu> mdMenus = mdMenuMapper.findMdMenuList(username);
        return mdMenus;
    }

    @Override
    public List<Node> findZtreeMenuNodes(String username) {
        List<Node> zMdMenus = mdMenuMapper.findZtreeMenuNodes(username);
        return zMdMenus;
    }

}
