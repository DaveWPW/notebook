package com.dave.notebook.controller;

import common.util.FileUtils;
import common.util.ShiroUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Dave
 * @Date: 2019/11/8 23:27
 * @Description: TODO
 */
@Controller
public class PageController {

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
    @RequestMapping(value = "doIndexUI", method = RequestMethod.GET)
    public String doIndexUI(Model model){
        String username = ShiroUtil.getCurrentUser().getUsername();
        model.addAttribute("username", username);
        String fileName = "README";
        String markdownContent = FileUtils.getFileData(username, fileName);
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

}
