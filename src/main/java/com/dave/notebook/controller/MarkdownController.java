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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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

    @RequestMapping("doGetMarkdownData")
    public JsonResult doGetMarkdownData(String fileName){
        String username = ShiroUtil.getCurrentUser().getUsername();
        String markdownContent = FileUtils.getFileData(username, fileName);
        JsonResult json = new JsonResult();
        json.setData(markdownContent);
        json.setState(1);
        return json;
    }

    @RequestMapping("doAddMarkdown")
    public JsonResult doAddMarkdown(String markdownContent, String fileName){
        if(StringUtils.isEmpty(fileName)){
            return new JsonResult("文件名不能为空！！");
        }
        String username = ShiroUtil.getCurrentUser().getUsername();
        int count = markdownService.selectFileName(username, fileName);
        if(count > 0){
            return new JsonResult("文件名已存在！！");
        }
        FileUtils.exportMarkDown(username, fileName, markdownContent);
        int rows = markdownService.addMaekdown(username, fileName);
        JsonResult json = new JsonResult();
        if(rows == 1){
            json.setState(1);
            json.setMessage("添加成功！！");
        }else{
            json.setState(0);
            json.setMessage("添加失败！！");
        }
        return json;
    }

    @RequestMapping("doUpdateMarkdown")
    public JsonResult doUpdateMarkdown(String markdownContent, String fileName, String oldFileName, Integer markdownId){
        if(StringUtils.isEmpty(fileName)){
            return new JsonResult("文件名不能为空！！");
        }
        String username = ShiroUtil.getCurrentUser().getUsername();
        if(!fileName.equals(oldFileName)){
            int count = markdownService.selectFileName(username, fileName);
            if(count > 0){
                return new JsonResult("文件名已存在！！");
            }
        }
        int row = 0;
        if(FileUtils.deleteMarkdownFile(username, oldFileName)){
            FileUtils.exportMarkDown(username, fileName, markdownContent);
            row = markdownService.updateMaekdown(markdownId, fileName);
        }else{
            return new JsonResult("删除文件失败！！");
        }
        if(row == 1){
            return new JsonResult("修改成功！！", 1);
        }
        return new JsonResult("修改失败！！");
    }

    @RequestMapping("doDeleteMarkdown")
    public JsonResult doDeleteMarkdown(Integer markdownId, String fileName){
        String username = ShiroUtil.getCurrentUser().getUsername();
        if(!FileUtils.deleteMarkdownFile(username, fileName)){
            return new JsonResult("删除文件失败！！");
        }
        int row = markdownService.deleteMarkdown(markdownId);
        if(row == 1){
            return new JsonResult("删除成功！！", 1);
        }
        return new JsonResult("删除失败！！");
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
