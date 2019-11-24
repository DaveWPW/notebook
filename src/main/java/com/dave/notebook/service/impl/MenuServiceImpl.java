package com.dave.notebook.service.impl;

import com.dave.notebook.entity.Menu;
import com.dave.notebook.mapper.MenuMapper;
import com.dave.notebook.service.MenuService;
import common.vo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/9 15:56
 * @Description: TODO
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findUserMenuList(int roleId) {
        List<Menu> menus = menuMapper.findUserMenuList(roleId);
        return menus;
    }

    @Override
    public List<Node> findMenuNodes() {
        return menuMapper.findMenuNodes();
    }

}
