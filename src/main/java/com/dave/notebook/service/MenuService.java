package com.dave.notebook.service;

import com.dave.notebook.entity.Menu;
import common.vo.Node;

import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/9 15:55
 * @Description: TODO
 */
public interface MenuService {

    List<Menu> findUserMenuList(int roleId);

    List<Node> findMenuNodes();

}
