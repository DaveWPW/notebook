package com.dave.notebook.controller;

import com.dave.notebook.service.MarkdownService;
import common.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Dave
 * @Date: 2019/11/8 23:27
 * @Description: TODO
 */
@Controller
public class PageController {

    @Autowired
    private MarkdownService markdownService;

    /**
     * 登录页
     *
     * @return
     */
    @RequestMapping("doLoginUI")
    public String doLoginUI(){
        return "login";
    }

    /**
     * 主页
     *
     * @param model
     * @return
     */
    @RequestMapping("doIndexUI")
    public String doIndexUI(Model model){
        String username = ShiroUtil.getCurrentUser().getUsername();
        model.addAttribute("username", username);
        String fileName = "README";
        String markdownContent = markdownService.getMarkdownFile(username, fileName);
        model.addAttribute("markdownContent", markdownContent);
        model.addAttribute("fileName", fileName);
        return "index";
    }

    /**
     * 分页组件
     *
     * @return
     */
    @RequestMapping("doPageUI")
    public String doPageUI(){
        return "common/page";
    }

    /**
     * 修改用户密码页面
     *
     * @return system/update_password
     */
    @RequestMapping("doPasswordUI")
    public String doPasswordUI() {
        return "system/password";
    }

    @RequestMapping("doMarkdownUI")
    public String doMarkdownUI(Model model, Integer markdownId) {
        String username = ShiroUtil.getCurrentUser().getUsername();
        String markdownContent = markdownService.findMarkdownById(username, markdownId);
        model.addAttribute("markdownContent", markdownContent);
        return "markdown";
    }

    /**
     * 用户管理页面
     *
     * @return system/user_list
     */
    @RequiresPermissions(value = {"admin", "user"})
    @RequestMapping("/user/doUserListUI")
    public String doUserListUI(){
        return "system/user_list";
    }

    /**
     * 用户编辑页面
     *
     * @return system/user_edit
     */
    @RequestMapping("/user/doUserEditUI")
    public String doUserEditUI(){
        return "system/user_edit";
    }

    /**
     * 角色管理页面
     *
     * @return system/role_list
     */
    @RequiresPermissions(value = {"admin", "role"})
    @RequestMapping("/role/doRoleListUI")
    public String doRoleListUI() {
        return "system/role_list";
    }

    /**
     * 角色编辑页面
     *
     * @return system/role_edit
     */
    @RequestMapping("/role/doRoleEditUI")
    public String doRoleEditUI(){
        return "system/role_edit";
    }

    @RequestMapping("/md_menu/doMdMenuListUI")
    public String doMdMenuListUI(){
        return "system/md_menu_list";
    }

    @RequestMapping("/md_menu/doMdMenuEditUI")
    public String doMdMenuEditUI(){
        return "system/md_menu_edit";
    }

    @RequestMapping("/markdown/doMarkdownListUI")
    public String doMarkdownListUI(){
        return "system/markdown_list";
    }

    @RequestMapping("/markdown/doMarkdownEditUI")
    public String doMarkdownEditUI() {
        return "system/markdown_edit";
    }

}
