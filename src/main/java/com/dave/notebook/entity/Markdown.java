package com.dave.notebook.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "nb_markdown")
public class Markdown {
    @Id
    @Column(name = "markdown_id")
    private Integer markdownId;
    @Column(name = "menu_num")
    private Integer menuNum;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "parent_num")
    private Integer parentNum;
    @Transient
    private String parentName;
    @Column(name = "menu_type")
    private Integer menuType;
    @Column(name = "file_name")
    private String fileName;
    private Integer status;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "modify_user")
    private String modifyUser;
    @Column(name = "modify_time")
    private Date modifyTime;

    public Integer getMarkdownId() {
        return markdownId;
    }
    public void setMarkdownId(Integer markdownId) {
        this.markdownId = markdownId;
    }
    public Integer getMenuNum() {
        return menuNum;
    }
    public void setMenuNum(Integer menuNum) {
        this.menuNum = menuNum;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public Integer getParentNum() {
        return parentNum;
    }
    public void setParentNum(Integer parentNum) {
        this.parentNum = parentNum;
    }
    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    public Integer getMenuType() {
        return menuType;
    }
    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getModifyUser() {
        return modifyUser;
    }
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}