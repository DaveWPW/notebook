package com.dave.notebook.controller;

import com.dave.notebook.entity.Markdown;
import com.dave.notebook.entity.MarkdownMenu;
import com.dave.notebook.service.MdMenuService;
import common.util.ShiroUtil;
import common.vo.JsonResult;
import common.vo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/23 12:13
 * @Description: TODO
 */
@Controller
@RequestMapping("md_menu")
public class MdMenuController {

    @Autowired
    private MdMenuService mdMenuService;

    @RequestMapping("doMdMenuListUI")
    public String doMdMenuListUI(){
        return "system/md_menu_list";
    }

    @RequestMapping("doMdMenuEditUI")
    public String doMdMenuEditUI(){
        return "system/md_menu_edit";
    }

    @RequestMapping("doFindMdMenuList")
    @ResponseBody
    public JsonResult doFindMdMenuList(){
        String username = ShiroUtil.getCurrentUser().getUsername();
        List<MarkdownMenu> mdMenus = mdMenuService.findMdMenuList(username);
        return new JsonResult(mdMenus);
    }

    @RequestMapping("doFindZtreeMenuNodes")
    @ResponseBody
    public JsonResult doFindZtreeMenuNodes() {
        String username = ShiroUtil.getCurrentUser().getUsername();
        List<Node> zMdMenus = mdMenuService.findZtreeMenuNodes(username);
        return new JsonResult(zMdMenus);
    }

}
