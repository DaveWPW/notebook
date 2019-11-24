package com.dave.notebook.service;

import com.dave.notebook.entity.Markdown;
import com.github.pagehelper.PageInfo;

/**
 * @Author: Dave
 * @Date: 2019/11/24 10:54
 * @Description: TODO
 */
public interface MarkdownService {

    PageInfo<Markdown> findMarkdownList(int pageCurrent, String username, String fileName);

    String findFileNameByMarkdownId(String username, int markdownId);

    int selectFileName(String username, String fileName);

    int addMaekdown(String username, String fileName);

    int updateMaekdown(int markdownId, String fileName);

    int deleteMarkdown(Integer markdownId);

}
