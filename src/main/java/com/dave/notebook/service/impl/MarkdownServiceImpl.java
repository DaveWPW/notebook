package com.dave.notebook.service.impl;

import com.dave.notebook.entity.Markdown;
import com.dave.notebook.mapper.MarkdownMapper;
import com.dave.notebook.service.MarkdownService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/24 10:54
 * @Description: TODO
 */
@Service
public class MarkdownServiceImpl implements MarkdownService {

    @Autowired
    private MarkdownMapper markdownMapper;

    @Override
    public PageInfo<Markdown> findMarkdownList(Integer pageCurrent, String username, String fileName) {
        PageHelper.startPage(pageCurrent, 10);
        PageInfo<Markdown> pageInfo = null;
        Markdown markdown = new Markdown();
        if(StringUtils.isEmpty(fileName)){
            markdown.setCreateUser(username);
            pageInfo = new PageInfo<>(markdownMapper.select(markdown));
        }else{
            pageInfo = new PageInfo<>(markdownMapper.findMarkdownListByName(username, fileName));
        }
        return pageInfo;
    }

    @Override
    public String getMarkdownFile(String username, String fileName) {
        String markdownContent = FileUtils.getMarkdownFile(username, fileName);
        return markdownContent;
    }

    @Override
    public int selectFileName(String username, String fileName) {
        Markdown markdown = new Markdown();
        markdown.setFileName(fileName);
        markdown.setCreateUser(username);
        int count = markdownMapper.selectCount(markdown);
        return count;
    }

    @Override
    public int addMaekdown(String username, String fileName, String markdownContent) {
        boolean add = FileUtils.uploadMarkDownFile(username, fileName, markdownContent);
        int insert = 0;
        if(add && fileName.equals("README")){
            return 1;
        }
        if(add){
            Markdown markdown = new Markdown();
            markdown.setFileName(fileName);
            markdown.setCreateUser(username);
            markdown.setCreateTime(new Date());
            markdown.setModifyUser(username);
            markdown.setModifyTime(new Date());
            insert = markdownMapper.insert(markdown);
        }
        return insert;
    }

    @Override
    public int updateMaekdown(String username, String fileName, String oldFileName, String markdownContent, Integer markdownId) {
        boolean del = FileUtils.deleteMarkdownFile(username, oldFileName);
        int update = 0;
        if(del){
            boolean add = FileUtils.uploadMarkDownFile(username, fileName, markdownContent);
            if(add){
                Markdown markdown = new Markdown();
                markdown.setMarkdownId(markdownId);
                if(!fileName.equals(oldFileName)){
                    markdown.setFileName(fileName);
                }
                markdown.setModifyUser(username);
                markdown.setModifyTime(new Date());
                update = markdownMapper.updateByPrimaryKeySelective(markdown);
            }
        }
        return update;
    }

    @Override
    public int deleteMarkdown(Integer markdownId, String username, String fileName) {
        boolean del = FileUtils.deleteMarkdownFile(username, fileName);
        int delete = 0;
        if(del){
            delete = markdownMapper.deleteByPrimaryKey(markdownId);
        }
        return delete;
    }

    @Override
    public String findMarkdownById(String username, Integer markdownId) {
        String fileName = markdownMapper.findFileNameByMarkdownId(username, markdownId);
        if(StringUtils.isEmpty(fileName)){
            return null;
        }
        String markdownContent = FileUtils.getMarkdownFile(username, fileName);
        return markdownContent;
    }

    @Override
    public String uploadImageFile(String username, MultipartFile file) {
        String url = FileUtils.uploadImageFile(username, file);
        return url;
    }

    @Override
    public List<Markdown> findMarkdownListName(String username) {
        Markdown markdown = new Markdown();
        markdown.setCreateUser(username);
        List<Markdown> select = markdownMapper.select(markdown);
        return select;
    }

}
