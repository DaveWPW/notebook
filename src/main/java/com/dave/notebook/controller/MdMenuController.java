package com.dave.notebook.controller;

import com.dave.notebook.entity.MarkdownMenu;
import com.dave.notebook.service.MdMenuService;
import common.util.ShiroUtil;
import common.vo.JsonResult;
import common.vo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/23 12:13
 * @Description: TODO
 */
@RestController
@RequestMapping("md_menu")
public class MdMenuController {

    @Autowired
    private MdMenuService mdMenuService;

    @RequestMapping("doFindMdMenuList")
    public JsonResult doFindMdMenuList(){
        String username = ShiroUtil.getCurrentUser().getUsername();
        List<MarkdownMenu> mdMenus = mdMenuService.findMdMenuList(username);
        return new JsonResult(mdMenus);
    }

    @RequestMapping("doFindZtreeMenuNodes")
    public JsonResult doFindZtreeMenuNodes() {
        String username = ShiroUtil.getCurrentUser().getUsername();
        List<Node> zMdMenus = mdMenuService.findZtreeMenuNodes(username);
        return new JsonResult(zMdMenus);
    }

}
