package com.dave.notebook.controller;

import com.dave.notebook.entity.Markdown;
import com.dave.notebook.entity.MarkdownMenu;
import com.dave.notebook.entity.Menu;
import com.dave.notebook.entity.User;
import com.dave.notebook.service.MdMenuService;
import com.dave.notebook.service.MenuService;
import com.dave.notebook.service.UserService;
import common.util.FileUtils;
import common.util.ShiroUtil;
import common.vo.JsonResult;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/9 15:49
 * @Description: TODO
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MdMenuService mdMenuService;

    @RequestMapping(value = "doFindUserMenuList", method = RequestMethod.POST)
    public JsonResult doFindUserMenuList(){
        int roleId = ShiroUtil.getCurrentUser().getRoleId();
        List<Menu> menus = menuService.findUserMenuList(roleId);
        return new JsonResult(menus);
    }

    @RequestMapping(value = "doFindMdMenuList", method = RequestMethod.POST)
    public JsonResult doFindMdMenuList(){
        String username = ShiroUtil.getCurrentUser().getUsername();
        List<MarkdownMenu> mdMenus = mdMenuService.findMdMenuList(username);
        return new JsonResult(mdMenus);
    }

    /**
     * 修改用户密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("doUpdatePassword")
    @ResponseBody
    public JsonResult doUpdatePassword(String oldPassword, String newPassword){
        if(StringUtils.isEmpty(oldPassword)) {
            return new JsonResult("旧密码不能为空");
        }
        if(StringUtils.isEmpty(newPassword)) {
            return new JsonResult("新密码不能为空");
        }
        User user = ShiroUtil.getCurrentUser();
        SimpleHash oldHash = new SimpleHash("MD5", oldPassword, user.getPasswordSalt());
        String oldHex = oldHash.toHex();
        if(!oldHex.equals(user.getPassword())) {
            return new JsonResult("旧密码错误！！");
        }
        SimpleHash newHash = new SimpleHash("MD5", newPassword, user.getPasswordSalt());
        String newHex = newHash.toHex();
        if(newHex.equals(user.getPassword())) {
            return new JsonResult("新密码和旧密码不能相同！！");
        }
        int row = userService.updatePassword(user.getUserId(), newHex);
        if(row == 1) {
            return new JsonResult("Update Succeed!", row);
        }
        return new JsonResult("Update Failed!!");
    }

}
