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

    @RequestMapping("doAddMdMemu")
    public JsonResult doAddMdMemu(MarkdownMenu markdownMenu){
        String username = ShiroUtil.getCurrentUser().getUsername();
        int row = mdMenuService.addMdMenu(username, markdownMenu);
        if(row == 1){
            return new JsonResult("添加成功！！", 1);
        }else{
            return new JsonResult("添加失败！！");
        }
    }

    @RequestMapping("doUpdateMdMemu")
    public JsonResult doUpdateMdMemu(MarkdownMenu markdownMenu){
        String username = ShiroUtil.getCurrentUser().getUsername();
        int row = mdMenuService.updateMdMem(username, markdownMenu);
        if(row == 1){
            return new JsonResult("修改成功！！", 1);
        }else{
            return new JsonResult("修改失败！！");
        }
    }

}
