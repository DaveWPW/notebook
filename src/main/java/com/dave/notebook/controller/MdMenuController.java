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

    @RequestMapping("doFindZtreeMenuShow")
    public JsonResult doFindZtreeMenuShow() {
        String username = ShiroUtil.getCurrentUser().getUsername();
        List<Node> zMdMenus = mdMenuService.findZtreeMenuShow(username);
        return new JsonResult(zMdMenus);
    }

    @RequestMapping("doAddMdMenu")
    public JsonResult doAddMdMenu(MarkdownMenu markdownMenu){
        String username = ShiroUtil.getCurrentUser().getUsername();
        int conut = mdMenuService.selectConutMdMenu(username, markdownMenu.getMenuName());
        if(conut > 0){
            return new JsonResult("菜单名称已存在！！");
        }
        int row = mdMenuService.addMdMenu(username, markdownMenu);
        if(row == 1){
            return new JsonResult("添加成功！！", 1);
        }else{
            return new JsonResult("添加失败！！");
        }
    }

    @RequestMapping("doUpdateMdMenu")
    public JsonResult doUpdateMdMemu(MarkdownMenu markdownMenu){
        String username = ShiroUtil.getCurrentUser().getUsername();
        int conut = mdMenuService.selectConutMdMenu(username, markdownMenu.getMenuName(), markdownMenu.getMenuId());
        if(conut > 0){
            return new JsonResult("菜单名称已存在！！");
        }
        int conut2 = mdMenuService.selectConutMdMenu(username, markdownMenu.getMenuId());
        if(conut2 > 0){
            return new JsonResult("存在下级文件不能修改！！");
        }
        int row = mdMenuService.updateMdMenu(username, markdownMenu);
        if(row == 1){
            return new JsonResult("修改成功！！", 1);
        }else{
            return new JsonResult("修改失败！！");
        }
    }

    @RequestMapping("doDeleteMdMenu")
    public JsonResult doDeleteMdMenu(Integer menuId){
        int row = mdMenuService.deleteMdMenu(menuId);
        if(row == 1){
            return new JsonResult("删除成功！！", 1);
        }else{
            return new JsonResult("删除失败！！");
        }
    }

}
