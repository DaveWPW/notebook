package com.dave.notebook.controller;

import com.alibaba.fastjson.JSONObject;
import com.dave.notebook.entity.Markdown;
import com.dave.notebook.service.MdMenuService;
import common.util.FileUtils;
import common.util.ShiroUtil;
import common.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: Dave
 * @Date: 2019/11/23 14:35
 * @Description: TODO
 */
@Controller
@RequestMapping("/markdown/")
public class MarkdownController {

    @Autowired
    private MdMenuService mdMenuService;

    @RequestMapping("doMarkdownUI")
    public String doMarkdownUI(Model model, Integer markdownId) {
        String username = ShiroUtil.getCurrentUser().getUsername();
        String fileName = mdMenuService.findFileNameByMarkdownId(username, markdownId);
        String markdownContent = FileUtils.getFileData(username, fileName);
        model.addAttribute("markdownContent", markdownContent);
        return "system/markdown";
    }

    @RequestMapping("doMarkdownEditUI")
    public String doMarkdownEditUI() {
        return "system/markdown_edit";
    }

    @RequestMapping("doGetMarkdownData")
    @ResponseBody
    public JsonResult doGetMarkdownData(String fileName){
        String username = ShiroUtil.getCurrentUser().getUsername();
        String markdownContent = FileUtils.getFileData(username, fileName);
        JsonResult json = new JsonResult();
        json.setData(markdownContent);
        json.setState(1);
        return json;
    }

    @RequestMapping("doAddMarkdownCache")
    public String doAddMarkdownCache(Model model, @RequestParam("markdownContent") String markdownContent, @RequestParam("markdownName") String markdownName){
        String username = ShiroUtil.getCurrentUser().getUsername();
        FileUtils.exportMarkDown(username, markdownName, markdownContent);
        model.addAttribute("markdownContent", markdownContent);
        return "markdown";
    }

    @RequestMapping("doImageUpload")
    public void doImageUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter wirte = null;
        JSONObject json = new JSONObject();
        try {
            wirte = response.getWriter();
            //文件存放的路径
            String path = request.getSession().getServletContext().getRealPath("upload");
            String url = "http://localhost:8080" + request.getContextPath() + "/upload/"
                    + FileUtils.upload(file, path);
            json.put("success", 1);
            json.put("message", "hello");
            json.put("url", url);
        } catch (Exception e) {
        } finally {
            wirte.print(json);
            wirte.flush();
            wirte.close();
        }
    }

}
