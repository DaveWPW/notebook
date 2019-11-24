package com.dave.notebook.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "nb_role")
public class Role {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id")
    @GeneratedValue(generator = "JDBC")
    private Integer roleId;

    private Integer[] menuIds;

    /**
     * 角色名
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "role_note")
    private String roleNote;

    /**
     * 使用状态
     */
    private Integer status;

    /**
     * 创建的用户
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建的时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改的用户
     */
    @Column(name = "modify_user")
    private String modifyUser;

    /**
     * 修改的时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

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

    public Integer[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Integer[] menuIds) {
        this.menuIds = menuIds;
    }

    /**
     * 获取角色名
     *
     * @return role_name - 角色名
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名
     *
     * @param roleName 角色名
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色描述
     *
     * @return role_note - 角色描述
     */
    public String getRoleNote() {
        return roleNote;
    }

    /**
     * 设置角色描述
     *
     * @param roleNote 角色描述
     */
    public void setRoleNote(String roleNote) {
        this.roleNote = roleNote;
    }

    /**
     * 获取使用状态
     *
     * @return status - 使用状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置使用状态
     *
     * @param status 使用状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建的用户
     *
     * @return create_user - 创建的用户
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建的用户
     *
     * @param createUser 创建的用户
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建的时间
     *
     * @return create_time - 创建的时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建的时间
     *
     * @param createTime 创建的时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改的用户
     *
     * @return modify_user - 修改的用户
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * 设置修改的用户
     *
     * @param modifyUser 修改的用户
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    /**
     * 获取修改的时间
     *
     * @return modify_time - 修改的时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改的时间
     *
     * @param modifyTime 修改的时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}