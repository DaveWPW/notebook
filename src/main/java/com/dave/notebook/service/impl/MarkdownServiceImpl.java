package com.dave.notebook.service.impl;

import com.dave.notebook.entity.Markdown;
import com.dave.notebook.mapper.MarkdownMapper;
import com.dave.notebook.service.MarkdownService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

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
    public PageInfo<Markdown> findMarkdownList(int pageCurrent, String username, String fileName) {
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
    public String findFileNameByMarkdownId(String username, int markdownId) {
        String fileName = markdownMapper.findFileNameByMarkdownId(username, markdownId);
        return fileName;
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
    public int addMaekdown(String username, String fileName) {
        Markdown markdown = new Markdown();
        markdown.setFileName(fileName);
        markdown.setCreateUser(username);
        markdown.setCreateTime(new Date());
        markdown.setModifyUser(username);
        markdown.setModifyTime(new Date());
        int insert = markdownMapper.insert(markdown);
        return insert;
    }

    @Override
    public int updateMaekdown(int markdownId, String fileName) {
        Markdown markdown = new Markdown();
        markdown.setMarkdownId(markdownId);
        markdown.setFileName(fileName);
        markdown.setModifyTime(new Date());
        int update = markdownMapper.updateByPrimaryKeySelective(markdown);
        return update;
    }

    @Override
    public int deleteMarkdown(Integer markdownId) {
        int delete = markdownMapper.deleteByPrimaryKey(markdownId);
        return delete;
    }
}
