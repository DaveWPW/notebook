package com.dave.notebook.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "nb_markdown_menu")
public class MarkdownMenu {
    @Id
    @Column(name = "menu_id")
    private Integer menuId;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "parent_id")
    private Integer parentId;
    @Transient
    private String parentName;
    @Column(name = "menu_type")
    private Integer menuType;
    @Column(name = "markdown_id")
    private Integer markdownId;
    @Transient
    private String fileName;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "modify_user")
    private String modifyUser;
    @Column(name = "modify_time")
    private Date modifyTime;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Integer getMarkdownId() {
        return markdownId;
    }

    public void setMarkdownId(Integer markdownId) {
        this.markdownId = markdownId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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