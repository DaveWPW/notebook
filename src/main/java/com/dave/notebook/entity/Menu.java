package com.dave.notebook.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "nb_menu")
public class Menu {
    @Id
    @Column(name = "menu_id")
    private Integer menuId;
    private String permission;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "parent_id")
    private Integer parentId;
    private Integer status;

    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public String getPermission() {
        return permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
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
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}