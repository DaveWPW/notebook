package com.dave.notebook.entity;

import javax.persistence.*;

@Table(name = "nb_role_menu")
public class RoleMenu {
    /**
     * 角色菜单ID
     */
    @Id
    @Column(name = "role_menu_id")
    private Integer roleMenuId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 获取角色菜单ID
     *
     * @return role_menu_id - 角色菜单ID
     */
    public Integer getRoleMenuId() {
        return roleMenuId;
    }

    /**
     * 设置角色菜单ID
     *
     * @param roleMenuId 角色菜单ID
     */
    public void setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取菜单ID
     *
     * @return menu_id - 菜单ID
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单ID
     *
     * @param menuId 菜单ID
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}