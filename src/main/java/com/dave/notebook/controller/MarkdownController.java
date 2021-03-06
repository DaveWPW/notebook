package com.dave.notebook.controller;

import com.alibaba.fastjson.JSONObject;
import com.dave.notebook.entity.Markdown;
import com.dave.notebook.service.MarkdownService;
import com.github.pagehelper.PageInfo;
import common.util.FileUtils;
import common.util.ShiroUtil;
import common.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/23 14:35
 * @Description: TODO
 */
@RestController
@RequestMapping("/markdown/")
public class MarkdownController {

    @Autowired
    private MarkdownService markdownService;

    @RequestMapping("doFindMarkdownList")
    public JsonResult doFindMarkdownList(Integer pageCurrent, String fileName){
        String username = ShiroUtil.getCurrentUser().getUsername();
        PageInfo<Markdown> markdownPage = markdownService.findMarkdownList(pageCurrent, username, fileName);
        return new JsonResult(markdownPage);
    }

    @RequestMapping("doGetMarkdownFile")
    public JsonResult doGetMarkdownFile(String fileName){
        if(StringUtils.isEmpty(fileName)){
            return new JsonResult("文件名不能为空!!");
        }
        String username = ShiroUtil.getCurrentUser().getUsername();
        String markdownContent = markdownService.getMarkdownFile(username, fileName);
        JsonResult json = new JsonResult();
        json.setData(markdownContent);
        json.setState(1);
        return json;
    }

    @RequestMapping("doAddMarkdown")
    public JsonResult doAddMarkdown(String markdownContent, String fileName){
        if(StringUtils.isEmpty(fileName)){
            return new JsonResult("文件名不能为空!!");
        }
        String username = ShiroUtil.getCurrentUser().getUsername();
        int count = markdownService.selectFileName(username, fileName);
        if(count > 0){
            return new JsonResult("文件名已存在!!");
        }
        int row = markdownService.addMaekdown(username, fileName, markdownContent);
        if(row == 1 && fileName.equals("README")){
            return new JsonResult("Edit Successfully!", 1);
        }
        if(row == 1){
            return new JsonResult("Added Successfully!", 1);
        }else{
            return new JsonResult("Add Failed!!");
        }
    }

    @RequestMapping("doUpdateMarkdown")
    public JsonResult doUpdateMarkdown(String markdownContent, String fileName, String oldFileName, Integer markdownId){
        if(null == markdownId){
            return new JsonResult("非法参数");
        }
        if(StringUtils.isEmpty(fileName)){
            return new JsonResult("文件名不能为空!!");
        }
        String username = ShiroUtil.getCurrentUser().getUsername();
        if(!fileName.equals(oldFileName)){
            int count = markdownService.selectFileName(username, fileName);
            if(count > 0){
                return new JsonResult("文件名已存在!!");
            }
        }
        int row = markdownService.updateMaekdown(username, fileName, oldFileName, markdownContent, markdownId);
        if(row == 1){
            return new JsonResult("Updated Successfully!", 1);
        }
        return new JsonResult("Update Failed!!");
    }

    @RequestMapping("doDeleteMarkdown")
    public JsonResult doDeleteMarkdown(Integer markdownId, String fileName){
        if(null == markdownId){
            return new JsonResult("非法参数");
        }
        if(StringUtils.isEmpty(fileName)){
            return new JsonResult("文件名不能为空!!");
        }
        String username = ShiroUtil.getCurrentUser().getUsername();
        int row = markdownService.deleteMarkdown(markdownId, username, fileName);
        if(row == 1){
            return new JsonResult("Deleted Successfully!", 1);
        }
        return new JsonResult("Delete Failed!!");
    }

    @RequestMapping("doImageUploadFile")
    public void doUploadImageFile(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = ShiroUtil.getCurrentUser().getUsername();
        String fileName = markdownService.uploadImageFile(username, file);
        //全路径（协议类型://域名/项目名/命名空间/文件名）
        String url = request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/markdown/upload/"+username+"/"+fileName;
        JSONObject json = new JSONObject();
        PrintWriter wirte = null;
        try {
            wirte = response.getWriter();
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

    @RequestMapping("upload/{username}/{fileName}")
    private void doGetImageFile(@PathVariable String username, @PathVariable String fileName, HttpServletResponse response){
        FileUtils.getImageFile(username, fileName, response);
    }

    @RequestMapping("doFindMarkdownListName")
    public JsonResult doFindMarkdownListName(){
        String username = ShiroUtil.getCurrentUser().getUsername();
        List<Markdown> markdownList =  markdownService.findMarkdownListName(username);
        return new JsonResult(markdownList);
    }

}
