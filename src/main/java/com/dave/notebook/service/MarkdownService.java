package com.dave.notebook.service;

import com.dave.notebook.entity.Markdown;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: Dave
 * @Date: 2019/11/24 10:54
 * @Description: TODO
 */
public interface MarkdownService {

    PageInfo<Markdown> findMarkdownList(Integer pageCurrent, String username, String fileName);

    String getMarkdownFile(String username, String fileName);

    int selectFileName(String username, String fileName);

    int addMaekdown(String username, String fileName, String markdownContent);

    int updateMaekdown(String username, String fileName, String oldFileName, String markdownContent, Integer markdownId);

    int deleteMarkdown(Integer markdownId, String username, String fileName);

    String findMarkdownById(String username, Integer markdownId);

    String uploadImageFile(String username, MultipartFile file);

    List<Markdown> findMarkdownListName(String username);
}
